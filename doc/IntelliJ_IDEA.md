## IntelliJ IDEA
### 配置指定的Maven版本
IntelliJ IDEA 自带一个Maven，但是版本不一定是我们想要的，因此最好将IDEA使用的Maven配置成我们开发环境的Maven。
具体方式是点击 File -> Settings 打开设置框。在设置框搜索“maven”，定位到maven相关设置项后，设置一下“Maven home directory”为开发环境的Maven主目录，也就是配置的环境变量M2_HOME的值。

另外，还可以设置Maven，让它下载依赖包时自动下载源码，便于开发学习。具体方式是在Maven设置项下的Importting栏下，勾选“Automatically download”后面的 “Sources”。

### Spring Initializr
Spring Initializr插件可以方便地创建Spring boot项目。IDEA可能默认没有这个插件，也可能在Plugins里面搜索不出来Spring Initializr。

解决方式是在设置框里面搜索“HTTP Proxy”，勾选“Auto-detect proxy settings”。然后点击下面的“Check connection”，输入 [https://start.spring.io/](https://start.spring.io/) 。点击OK之后IDEA应该就会显示连接成功。

重启IDEA，此时应该能正常使用Spring Initializr了。

### IDEA 右键New子菜单没有“Java Class”或者“Package”
这是因为当前的目录没有被设置为源码目录。设置方式是打开Project Structure窗口(快捷键：Ctrl+Alt+Shift+S )，然后选中src目录并且选中“Mark as”后面的“Sources”标签，即可将src目录设置为源码目录。此后右键就能创建Java包或者类。如图：
![new package](https://github.com/ttyrion/Java/blob/master/doc/img/idea/project_structure.jpg)

### IDEA 添加类方法快捷键
IDEA可以方便地给Java类添加一些常见方法，如Getter, Setter, toString等待。

具体快捷键是ALT+Insert：

![insert_method](https://github.com/ttyrion/Java/blob/master/doc/img/idea/insert_method.png)
