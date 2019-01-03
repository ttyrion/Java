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