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
2. Maven可以根据坐标(groupId,artifactId,version)来唯一地定位maven仓库中的一个构件，也就是根据这个坐标确定构件的存储位置。groupId,artifactId,version是maven坐标的三要素。






