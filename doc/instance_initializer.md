#### Instance Initializer
先看如下的一个类：

```Java
class InitializerTest {
    //instance variable initializer
	String s = "abc";
 
	//constructor
	public InitializerTest() {
		System.out.println("InitializerTest constructor called");
    }
    
    public InitializerTest(String str) {
		System.out.println("InitializerTest constructor called with " + str);
    }
 
	//static initializer
	static {
		System.out.println("InitializerTest static initializer called");
	}
 
	//instance initializer
	{
		System.out.println("InitializerTest instance initializer called");
	}
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CDPlayerConfig.class)
public class CDPlayerTest {
    @Test
    public void onTest() {
        new InitializerTest();
        new InitializerTest("hello");
    }
}

```

命令行输出的内容如下：

```Java
InitializerTest static initializer called
InitializerTest instance initializer called
InitializerTest constructor called
InitializerTest instance initializer called
InitializerTest constructor called with hello
```

从命令行输出可以看出：一个类的静态初始化器最先被调用，并且只调用一次。创建对象时，先是实例初始化器被调用；再接着是构造器被调用。并且，不论使用哪个构造器去构造一个对象，实例初始化器都会被调用。另外，instance variable initializer 和 instance initializer 基本差不多。

这里主要说一下**实例初始化器（Instance Initializer）**。首先，实例初始化器可以在如下场景中使用：
1. 初始化代码需要处理异常。
2. 提供一些无法在实例变量初始化表达式中做的操作。

不过，实际上，上面所说的情况，比较少见。实例初始化器最有用的地方，还是在使用匿名内部类时。它可以为匿名内部类提供一个类似构造器的操作：因为这种内部类是匿名的，所以根本没有构造器（“有名的”）。