#### Java
[Thinking in Java](https://github.com/ttyrion/Java/blob/master/doc/Thinking_in_Java.md) 

[Maven(3.5.4)](https://github.com/ttyrion/Java/blob/master/doc/Maven.md) 

[VSCode调试Maven Webapp项目](https://github.com/ttyrion/Java/blob/master/doc/vscode_debug_servlet.md) 

#### CentOS install VSCode
```javascript
$ sudo rpm -ivh code-1.47.2-1594838045.el7.x86_64.rpm 
error: Failed dependencies:
	libXss.so.1()(64bit) is needed by code-1.47.2-1594838045.el7.x86_64
  
$ repoquery --nvr --whatprovides libXss.so.1
libXScrnSaver-1.2.2-6.1.el7

$ sudo yum install libXScrnSaver

```

#### CentOS update VSCode
```javascript
// uninstall old version
$ yum -y remove code

// install latest version
$ rpm -ivh code-1.47.2-1594838045.el7.x86_64.rpm 

```

#### CentOS install OpenJDK
```javascript
$ yum -y install java-11-openjdk java-11-openjdk-devel
...
Complete!

$ java -version
openjdk version "1.8.0_181"

$ alternatives --config java 

There are 3 programs which provide 'java'.

  Selection    Command
-----------------------------------------------
   1           java-1.7.0-openjdk.x86_64 (/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.191-2.6.15.5.el7.x86_64/jre/bin/java)
*+ 2           java-1.8.0-openjdk.x86_64 (/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.181-7.b13.el7.x86_64/jre/bin/java)
   3           java-11-openjdk.x86_64 (/usr/lib/jvm/java-11-openjdk-11.0.7.10-4.el7_8.x86_64/bin/java)

Enter to keep the current selection[+], or type selection number: 
(type the right selection number, 3 for here)

$ java -version
openjdk version "11.0.7" 2020-04-14 LTS
OpenJDK Runtime Environment 18.9 (build 11.0.7+10-LTS)
OpenJDK 64-Bit Server VM 18.9 (build 11.0.7+10-LTS, mixed mode, sharing)

```
如果系统已经安装了其他版本的JDK，就按照上面的命令来选择一个默认的JDK版本。

安装完 JDK 11 之后，配置JAVA_HOME 和 PATH。
