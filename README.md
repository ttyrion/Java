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
1. **clean**: clean生命周期包含三个阶段：pre-clean，**clean**, post-clean.
2. **default**: default生命周期定义了真正**构建**时所需要执行的所有步骤。default生命周期包含的阶段会列在后面。
3. **site**: site生命周期的目的是建立站点和发布项目站点。其包括这几个阶段：pre-site, site（生成项目站点文档）, post-site, site-deploy。
```javascript
default生命周期的阶段：
 2.1  validate
 2.2  initialize
 2.3  generate-sources
 2.4  process-sources ：处理项目主资源文件。一般来说，是对src/main/resources目录
      的内容进行变量替换等工作后，复制到项目输出的主classpath目录中。
 2.5  generate-resources
 2.6  process-resources
 2.7  compile  ：编译项目主代码。一般来说，是编译src/main/java目录下
      的Java代码文件到项目输出的主classpath目录中。
 2.8  process-classes
 2.9  generate-test-sources
 2.10 process-test-sources ：处理项目测试资源文件。一般来说，是对src/test/resources目录
      的内容进行变量替换等工作后，复制到项目输出的测试classpath目录中。
 2.11 generate-test-resources
 2.12 process-test-resources
 2.13 test-compile ：编译项目测试代码。一般来说，是编译src/test/java目录
      的Java代码文件到项目输出的测试classpath目录中。
 2.14 process-test-calsses
 2.15 test ：使用单元测试框架运行测试，不过测试代码不会被打包或者部署。
 2.16 prepare-package
 2.17 package ：接收编译好的代码，打包成可发布的格式，如jar。
 2.18 pre-integration-test
 2.19 integration-test ：集成测试。
 2.20 post-integration-test
 2.21 verify
 2.22 install ：将包安装到Maven本地仓库，供本地其他Maven项目使用。
 2.23 deploy ：将最终的包复制到远程仓库，供其他开发人员和Maven项目使用。
```

###### maven命令和生命周期
在命令行中执行maven任务的最主要方式就是使用Maven的生命周期阶段。需要注意的是：maven的各个生命周期之间是相互独立的，但是一个生命周期内的各个阶段是有前后依赖关系的。比如在命令行中执行 mvn test: 该命令表示调用maven的default生命周期的test阶段。实际执行的阶段是default生命周期中从validate，initialize开始，一直到test的所有阶段。结果就是执行测试的时候，项目的代码会自动被编译，因为中间会调用compile阶段，即执行compile构建过程。

###### maven插件和生命周期
maven核心仅仅定义了抽象的生命周期，具体的构建过程中的工作，是由maven插件完成的。插件以独立的形式存在，因此我们才会发现maven的安装包只有不到3M大小。maven会在需要的时候（比如，执行一个maven命令）下载对应的插件并使用插件完成实际构建工作。

每个插件可以完成多个功能，每个功能就是一个**插件目标**，一般以 “插件前缀:插件目标”这样的形式在maven中调用。比如compiler:compile 表示maven-compiler-plugin的compile目标。

maven的生命周期是与插件相互绑定的，用于完成实际的构建工作。具体地说，其实是**生命周期的各个阶段与插件的目标相互绑定**，以完成某个具体的构建任务。

