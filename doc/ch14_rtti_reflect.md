#### RTTI
《Java编程思想》：**面向对象编程**的基本目的是：让代码只操纵对基类对象的引用。这使得代码对客户端程序员实现的各种泛化的类对象的引用都能正确地起作用。

因此，很多时候都是创建各种派生类对象，然后向上转型为基类对象。然后，这个过程也丢失了对象的具体类型信息，它们被当作了基类类型的对象。通常我们的目的是不需要关心对象具体的类型信息，这样的代码更简单、易读、易于维护，设计更合理、简单。但有时候又需要获知一个基类对象引用，引用的具体对象是什么类型，即**downcast**，这就是RTTI的基本形式。

非一个类层次之间的对象类型转换，**属于编译期错误，不属于RTTI**。如：
```Java
public class AppTest 
{
    @Test
    public void onTest()
    {
        Derived1 d = new Derived1();
        Base b = d;
        b.doTask();

        // A compile error, it's not a kind of RTTI
        //CTest c = (CTest)b;

        // RTTI: downcast
        try {
            System.out.println("Cast base to Derived2.");
            // it would throw a ClassCastException
            Derived2 d2 = (Derived2)b;
        }
        catch (ClassCastException exp) {
            System.out.println("ClassCastException to Derived2.");
        }

        try {
            System.out.println("Cast base to Derived1.");
            // it would not throw a ClassCastException
            Derived1 d1 = (Derived1)b;
        }
        catch (ClassCastException exp) {
            System.out.println("ClassCastException to Derived1.");
        }
    }
}

```

##### Class对象
如果Java能做到运行时类型识别，那肯定是有某种东西能表示运行时对象的类型信息的。这就是特殊的对象：**Class对象**，它包含了与类有关的信息。

每个类都有一个Class对象：每当编译了一个类，就会生成一个.class文件，这就是Class对象。Class对象就是用来创建类的**所有的**常规对象的。为了生成一个类的Class对象，JVM将使用“**类加载器**”子系统。

###### 类的加载(类的对象怎么创建？)
所有的类都是在它第一次被使用时，**动态加载**到JVM中的：当程序创建第一个对类的**静态成员**的引用时，就会加载这个类。很明显，**构造器**当然也是类的静态方法，即便在构造器前面没有static关键字。因为调用构造器时不需要先创建一个对象，构造器本身就是用于创建对象。所以使用构造器构造一个对象时，这个类肯定也是被加载了，因为这也是对类的静态成员的引用。

**加载过程**：类加载器先检查一个类的Class对象是否已经加载，如果尚未加载，默认的类加载器就会根据类名查找相应的.class文件。一旦一个类的Class对象被载入内存，这个Class对象就能被用来创建这个类的所有对象。

无论何时，只要想在运行时使用类型信息，就必须首先获得**恰当的**Class对象的引用。Class.forName()可以实现这个目的，并且很简单，它不会创建对应那个类的须，也不需要我们持有那个类的对象，只需要提供一个类名即可（**类名包含包路径，即全限定名**）。当然，如果已经持有一个对象，可以通过getClass()来获取该对象类型的Class对象引用：getClass()属于Object类的一部分，它将返回表示该对象的**实际类型**的Class对象引用。比如下面的示例代码，会输出对象b的具体类名，即派生类名"rtti.Derived1"：

```Java
public class AppTest 
{
    @Test
    public void onTest()
    {
        Derived1 d = new Derived1();
        Base b = d;
        b.doTask();

        Class<?> c = b.getClass();
        // "rtti.Derived1" would be printed.
        System.out.println(c.getName());
    }
}
```

###### Class字面常量
Class字面常量是另一种获取一个类的Class对象引用的方式：TestClass.class。这种方式不只是**更简单**，因为这在编译期会被检查，因此**更安全**，不像Class.forName那样需要捕获异常。另外，它也去掉了forName调用，所以**更高效**。

Class字面常量不仅可以作用于普通的类，还能用于接口、数组、基本数据类型。比如int.class等价于Integer.TYPE。只不过有一点需要注意：通过.class字段获取Class对象引用时，**不会自动初始化这个Class对象**。

使用一个类分三个步骤：**加载、链接、初始化**。类加载器加载一个类，创建一个Class对象，但此时还没初始化这个Class对象。Class对象的初始化**被延迟**到了对静态方法（如构造器）或者非常数静态域进行首次访问时。这里也可以看出，通过一个对象的getClass()获取的Class对象，肯定是经过初始化的。

###### Class引用的泛化
首先：Class引用总是指向某个Class对象（虽然可能还未初始化），Class对象可以创建类的实例，它包含可作用于该类实例的所有方法，还包含该类的静态成员。因此，Class引用表示的就是它所指向的对象的确切类型，而该对象就是Class类的一个对象。但是这个对象的类型（Class）并不具体。Java SE5 允许我们用**泛型语法**限定一个Class对象的具体类型，如：**Class\<Integer\>**，这种泛型语法可以强制编译器进行类型检查（对Class类型的检查）。泛化时也可以放松限制：**Class<?>** 使用通配符表示任何一个Class类。通配符作为类型参数在泛型编程中是很常见的，比如：一个创建Base的派生类对象的泛型工厂类可以表示为：Factory<? extends Base>。

##### 类型转换前的安全检查
目前，我们已经见过的RTTI形式有：
1. 传统的类型转换，如 (Shape)，这是有RTTI确保类型转换的正确性的，如果类型转换有错误，Java会抛出**ClassCastException**。C++里这种传统的类型转换，并不使用RTTI，因此它肯定也不会抛出转换异常。

2. 代表对象类型的Class对象。

Java还有第三种形式的RTTI：**instanceof**，它告诉我们对象是不是某个特定类型的实例。但是instanceof并不常用，如果项目中大量使用了instanceof，那说明项目的设计可能比较糟糕。

##### 反射：违反了类访问权限，破坏了封装性
如果不知道某个对象的具体类型，RTTI是可以告诉我们的。但是这有一个限制：我们想要知道的对象的具体类型必须是编译期已知的。换句话说：**编译器在编译期必须知道所有需要通过RTTI来处理的类**。

这看起来好像不算是限制，难道还有编译期间不知道的类型吗？答案是有。我们可能需要从一个磁盘文件或者网络中的代表一个类的字节序列来加载一个类。还有比如“远程方法调用”，即在运行时跨网络在远程平台上创建和运行对象。

反射提供了一种机制：用来检查可用的方法（返回方法名），还能获取和设置类的Fields（甚至包括private fields）。反射是有Class类和java.lang.reflect类库一起提供支持的。reflect类库包括Field、Method、Constructor类，这些类型的对象是由JVM在运行时创建的。用于表示未知类的成员。这样我们就能
1. 使用Constructor创建新对象
2. 使用get()/set()方法获取和设置与Field对象关联的字段
3. 使用invoke()方法调用与Method对象关联的方法

Class类提供了一些方法让我们可以取到一个类的Field、Method、Constructor数组。通过Class类和reflect类库，我们可以在运行时确定一个匿名对象的类型信息，编译期不需要知道任何事情。使用反射时，通常通过Class.forName()来获得一个Class对象引用，但是这个Class对象在编译期是未知的，所有的方法特征签名信息都是在运行时被提取出来的。

总之，**RTTI和反射的区别**仅仅只是：RTTI是编译器在编译时打开和检查.class文件，而反射时，编译器编译时无法获取.class文件，而是在运行时打开和检查.class文件。


```Java

class Base {
    private int b = 0;
    public Base() {

    }

    public void doTask() {
        System.out.println("Base doTask.");
    }
}

class Derived1 extends Base implements IMessage {
    private String name = "Derived1_123";
    public Derived1() {

    }

    public String getMessage() {
        return "Derived1 Message.";
    }

    public void sayHello() {
        System.out.println("Hello.");
    }

    private String lowerCase(String str) {
        return str.toLowerCase();
    }
}

class Derived2 extends Base {
    public Derived2() {

    }
}

class CTest {
    
}

public class AppTest 
{
    @Test
    public void onTest()
    {
        Derived1 d = new Derived1();
        Base b = d;
        b.doTask();

        Class<? extends Base> c = b.getClass();
        try {
            //Method sayHello = c.getMethod("sayHello", new Class[]{int.class});  // NoSuchMethodException
            Method sayHello = c.getMethod("sayHello", (Class<?>[])null);
            //sayHello.invoke(b, "Am I Right?");  // IllegalArgumentException
            sayHello.invoke(b, (Object[])null);

            //Method lowerCase = c.getMethod("lowerCase", new Class[]{String.class});  // NoSuchMethodException
            Method lowerCase = c.getDeclaredMethod("lowerCase", new Class[]{String.class});
            String str = (String)lowerCase.invoke(b,"Just test Reflection."); // IllegalAccessException
            System.out.println("lowerCase.invoke: " + str);

            //Field name = c.getField("name"); // NoSuchFieldException
            Field name = c.getDeclaredField("name");
            System.out.println("Field name = " + name.get(b));  // IllegalAccessException
        }
        catch (NoSuchMethodException exp) {
            System.out.println("NoSuchMethodException");
        }
        catch (NoSuchFieldException exp) {
            System.out.println("NoSuchFieldException");
        }
        catch (IllegalAccessException exp) {
            System.out.println("IllegalAccessException");
        }
        catch (IllegalArgumentException exp) {
            System.out.println("IllegalArgumentException");
        }
        catch (InvocationTargetException exp) {
            System.out.println("InvocationTargetException");
        }
    }
}

```

上面是测试反射的例子。从输出能看出：虽然反射能获取到类的private方法和域，但是并不能访问它们。但是，先在Method或Filed对象上调用setAccessible(true)，之后就能不受限制地访问它们。可见：**反射破坏了类的封装性**。