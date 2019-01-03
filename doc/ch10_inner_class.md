#### Thinking in Java
##### Ch10: Inner Class
Java的嵌套类分为两种：静态和非静态的。声明为static的嵌套类被称为**静态嵌套类(static nested class)**，而非静态的嵌套类，被称为**内部类(inner class)**。
创建这两种嵌套类的对象的方式也不相同。

###### 内部类(inner class)
内部类的对象只能在与其**外围类(enclosing class)**对象相关联的情况下才被创建，因为构建内部类对象时，需要一个外围类对象的引用。如果编译器访问不到这个外围类对象引用，就会报错。一般情况下，我们不需要显示提供这个外围类对象引用，只需通过一个外围类对象引用去创建一个内部类对象就行，比如在一个非static方法中创建内部类对象或者通过.new。内部类对象对它所属的外围类对象的methods和fields有完全的访问权限。

```Java

// Chars.java
interface iterator{
    Object    refValue();
    void      forward();
    void      backward();
    void      set(int i);
    boolean   equal(int i);
}

public class Chars {
    private String str;
    public class CharsIterator implements iterator {
        private int index = 0;
        public Object refValue() {
            return str.charAt(index);
        }

        public void forward() {
            ++index;
        }

        public void backward() {
            --index;
        }

        public void set(int i) {
            index = i;
        } 

        public boolean equal(int i) {
            return index == i;
        }
    }

    public int begin() {
        return 0;
    }

    public int end() {
        return str.length();
    }

    public Chars(String s) {
        str = s;
    }

    public CharsIterator getCharsIterator() {
        return new CharsIterator();
    }
}

// App.java
public class App 
{
    public static void main( String[] args )
    {
        Chars cs = new Chars("Hello, World.");
        iterator iter = cs.getCharsIterator();
        for(iter.set(cs.begin()); !iter.equal(cs.end()); iter.forward()) {
            System.out.println(iter.refValue());
        }
    }
}

```
注意上面的内部类**CharsIterator**可以直接访问外围类**Chars** 的 **private** str 字段。

因为内部类对象和外围类对象之间是相互关联的，所以Java也提供了通过外围类对象直接new内部类对象，以及通过内部类对象获取对应的外围类对象的方法：即.this和.new。

```Java

// Data.java
interface IProcess{
    void process();
}

public class Data {
    private String value;

    public class UpperCaseProcess implements IProcess {
        public void process() {
            value = value.toUpperCase();
        }

        public void printData() {
            System.out.println("From Inner Class:" + Data.this.value);
        }

        public void printString(String str){
            System.out.println("From Inner Class:" + str);
        }
    }

    public Data(String str) {
        value = str;
    }
}

// App.java
public class App 
{
     public static void main( String[] args )
     {
        Data d = new Data("Hello, World.");
        d.new UpperCaseProcess().process();
        d.new UpperCaseProcess().printData();

        String str = "Just a Test.";
        d.new UpperCaseProcess().printString(str);
     }
}

```
如上面所示：内部类对象里面可以用**Data.this**获取到外围类对象的引用，而外围类对象也可以用**obj.new**的方式创建内部类对象。上面的代码同样也表明了，内部类对象对外围类对象的字段（即便是private字段）有完全的访问权。


###### 静态嵌套类(static nested class)
静态嵌套类不需要与一个外围类对象关联，可以认为这只是把外围类当做一个名字作用域。
```Java

// Data.java
interface IMessage{
    void message(String msg);
}

public class Data {
    public static class StdMessage implements IMessage {
        public void message(String msg) {
            System.out.println(msg);
        }
    }
}

// App.java
public class App 
{
    public static void main( String[] args )
    {
        Data.StdMessage msg = new Data.StdMessage();
        msg.message("Hello, world.");
    }
}

```
如上面所示：对于静态嵌套类的情况，外围类基本只充当一个名字作用域的作用。

对比上面对内部类和静态嵌套类的说明：
1. 内部类对象的创建需要一个外围类对象，因此不能简单地通过 new OutClass.InnerClass()的形式创建一个内部类对象。另外很重要的一点是，内部类对象对外围类对象的方法和字段有完全的访问权。

2. 静态嵌套类的对象的创建，不需要提供一个外围类对象。因此可以简单地通过 new OutClass.InnerClass()的形式创建一个静态嵌套类对象。

**注意：内部类并不是只能在类内定义，我们还能在一个类的方法内，在一个方法内的一个代码块作用域内定义一个内部类。** 这并不是说，只有执行了对应作用域的代码，才会创建那个内部类，该内部类是在编译期与其他类一起创建的，只不过它的作用域受限。

##### 匿名内部类
有如下代码：

```Java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CDPlayerConfig.class)
public class CDPlayerTest {
    @Test
    public void onTest() {
        getDC().play();
    }

    public CompactDisc getDC() {
        return new CompactDisc() {
            private String title = "龙卷风";
            private String artist = "Jay";

            public void play() {
                System.out.println("Playing " + title + " by " + artist);
            }
        }; //这里的分号是必须的，不像其他的内部类，不用分号。
    }
}
```
上面的代码咋看之下可能有点奇怪。实际上，上面的代码getDC()只是返回了一个匿名的内部类（这里用X指代）的对象。匿名内部类X实现了CompactDisc接口，X的定义和实现在{}之间。这里匿名内部类定义结束的"}"之后必须要有一个";"，不像其他内部类定义。可以理解为，这个分号是结束return new 这一表达式的，并且new返回的X对象被向上转型为CompactDisc对象。

上面的代码其实与下面有名的内部类是一样的：

```Java

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CDPlayerConfig.class)
public class CDPlayerTest {
    @Test
    public void onTest() {
        getDC().play();
    }

    public CompactDisc getDC() {
        class XCompactDisc implements CompactDisc {
            private String title = "龙卷风";
            private String artist = "Jay";

            public void play() {
                System.out.println("Playing " + title + " by " + artist);
            }
        }

        return new XCompactDisc();
    }
}

```
可以看到，上面定义内部类时，"}"后面不需要分号";"，因为这是一个类定义，而不是一个表达式。

上面两个例子都使用了默认的构造器去构造CompactDisc对象。实际上，我们也可以使用带参数的构造器。因为匿名内部类不一定是某接口的实现类，还可以是一个普通基类的派生类，即匿名内部类可以把普通基类当成“接口”：

```Java

// Printer.java
public class Printer{
    protected String id;
    public Printer(String id) {
        this.id = id;
    }
    public void print(){
        System.out.println(id);
    }
}

// CDPlayerTest.java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CDPlayerConfig.class)
public class CDPlayerTest {
    @Test
    public void onTest() {
        getPrinter("Test-Printer").print();
    }

    public Printer getPrinter(String id) {
        return new Printer(id) {
            private String content = "Hello, world.";
            
            public void print() {
                super.print();
                System.out.println(content);
            }
        };
    }
}

```
上面的代码展示了匿名内部类是怎么调用基类Printer的带参数构造器的。同时值得注意的是，匿名内部类里面还对content字段进行了初始化。

###### 匿名内部类构造器
匿名内部类其实没有构造器，因为它们没有名字，那就不会有“有名”构造器。但是我们有时候需要给匿名内部类提供一个类似构造器的操作，这可以通过**实例初始化器**来提供。
这里插入一点讲实例初始化：[instance initializer](https://github.com/ttyrion/Java/blob/master/doc/instance_initializer.md)

```Java
class Printer{
    protected String id;
    public Printer() {
        
    }
    public void print(){

    }
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CDPlayerConfig.class)
public class CDPlayerTest {
    @Test
    public void onTest() {
        getPrinter("Hello, world.").print();
    }

    public Printer getPrinter(final String str) {
        return new Printer(){
            private String content;
            //it behaves like a constuctor
            {
                content = str;
            }

            public void print(){
                System.out.println(content);
            }
        };
    }
}
```
上面是一个匿名内部类把实例初始化器作为构造器使用的例子。其中还有一点值得注意的是：如果匿名内部类中访问到了局部变量，比如这里的getPrinter()的参数str，参数必须声明为final，不包括将参数提供给基类构造器使用的情况。

**使用匿名内部类，相比之下，没有使用继承灵活。** 因为虽然匿名内部类可以扩展（继承）一个类，也可以实现一个接口，但它不能在扩展一个类的同时去实现接口，实现接口的时候也只能实现一个接口。

内部类提供了一种“多重继承”的方式：在只有抽象基类或者具体的基类，而不是接口的情况下，只有通过内部类才能实现“多重继承”。有时候，一个外围类继承了某个基类，基类有个方法methodA();这个外围类现在需要实现一个接口InterfaceA，该接口也有一个方法methodA()。此时，就得使用内部类了。