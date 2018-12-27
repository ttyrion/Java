#### Maven(3.5.4)
##### Maven Home目录
```javasript

me:~/usr/apache-maven-3.5.4> ls
bin  boot  conf  lib  LICENSE  NOTICE  README.txt

```
其他目录没什么特别，主要说以下两个目录：
1. conf目录，它包含了一个非常重要的文件： **settings.xml** 。直接修改此文件，可在本机上全局地定制Maven的行为。不过一般情况下，最好是复制此文件至 **$HOME/.m2** 目录下，再修改此文件，只在当前用户范围内修改Maven的行为。

2. lib目录，这里面包含了Maven依赖的Java类库，包括Maven自己的类库，以及第三方类库。

##### Maven $HOME/.m2目录
默认情况下， .m2目录内部是一个repository目录，这是**Maven本地仓库**目录。所有的Maven构件都会被存放在这个本地仓库中，以方便各个项目之间重用它们。比如，如果执行过mvn help:system的话，就能在$HOME/.m2／repository/org/apache/maven/plugins目录下面找到maven-help-plugin目录，内部是这个插件的jar以及POM文件等。简单来说，有两点：
1. Maven会把项目依赖项从中央仓库下载到本地仓库中，供本地项目开发用，开发者只需配置项目直接依赖项。
2. Maven可以根据坐标(**groupId,artifactId,version**)来唯一地定位maven仓库中的一个构件，也就是根据这个坐标确定构件的存储位置。groupId,artifactId,version是maven坐标的三要素。

#### 生命周期和插件
**maven生命周期就是对项目所有的构建过程的抽象和统一。** maven生命周期包含了项目的清理、初始化、编译、测试、打包、集成测试、验证、部署、站点生成等几乎所有的构建步骤。也就是说，几乎所有的项目构建过程，都能映射到一个maven生命周期上。

maven生命周期是一个抽象概念，它本身并不做任何实际工作。实际的工作，如编译代码，是由maven插件来完成的。这种思想与设计模式中的Template Method 很相似。模板方法模式在父类中定义算法的结构，子类通过实现或者重写父类的方法来控制实际的行为。这样称为“分离算法与策略”。

maven为大多数的构建过程绑定了默认的插件来完成对应的工作。尽管实际使用maven的过程，大部分情况下我们都察觉不到插件的存在，但实际工作确实是由插件完成的，比如负责编译的插件maven-compiler-plugin。

##### maven生命周期
**maven生命周期并不是一个整体！** maven拥有三套相互独立的生命周期：clean, default, site。 clean生命周期的目的是清理项目，default生命周期的目的是构建项目，site生命周期的目的是建立项目站点。

每个生命周期包含一些阶段（phase），**这些阶段是有顺序的：后面的阶段依赖前面的阶段** 。 我们使用maven时，最多的，也是最直接的方式就是调用这些生命周期阶段。
1. **clean**: clean生命周期包含三个阶段：pre-clean，clean, post-clean.
2. **default**: default生命周期定义了真正**构建**时所需要执行的所有步骤。


