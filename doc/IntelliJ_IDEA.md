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

### IDEA 设置注释模板
IDEA可以添加文件头注释模板以及类、方法模板等等。

#### 1. 设置文件头注释模板
打开IDEA设置框，找到Editor设置栏下的“File and Code Templates”，编辑Includes下面的FileHeader的内容即可。如图：

![fileheader](https://github.com/ttyrion/Java/blob/master/doc/img/idea/comment_fileheader.png)

添加了上面的文件头注释模板后，新建Java类时，类文件开头会自动加入如下注释：
```javascript
/**
 * @Description:
 * @Date: Created on 15:26 2020/7/27
 */

```

#### 2. 设置类方法注释模板
打开IDEA设置框，找到Editor设置栏下的“Live Templates”，点击右侧“+”按钮，添加一个“Template Group”，这里命名为MyGroup。完成后选中这个新添加的MyGroup，
然后再次点击右侧“+”按钮，添加一个“Live Template”。我们只需要给这个Live Template指定一个缩写词、描述语气、模板内容。默认情况下是键盘输入缩写词后按下Tab键（右侧的Expand with可设置），IDEA即自动插入注释。

![methodcomment](https://github.com/ttyrion/Java/blob/master/doc/img/idea/comment_method.png)

这里编写的模板内容为：
```javascript
/**
* @Description: 
$params$
* @Return $return_type$
*/
```
上面使用了变量的形式，因此需要点击右侧的“Edit variables”给这些变量一个表达式来计算其值。如果“Edit variables”按你是灰色不可点击，那么表示模板内容格式不对。变量编辑过程如下：

![methodcomment_edit_variables](https://github.com/ttyrion/Java/blob/master/doc/img/idea/comment_method_edit_variables.png)

params变量的值为：
```javascript
groovyScript("if(\"${_1}\".length() == 2) {return '';} else {def result=''; def params=\"${_1}\".replaceAll('[\\\\[|\\\\]|\\\\s]', '').split(',').toList();for(i = 0; i < params.size(); i++) {if(i<(params.size()-1)){result+='* @param ' + params[i] + ' : ' + '\\n'}else{result+='* @param ' + params[i] + ' : '}}; return result;}", methodParameters());
```

这里，我给MyGroup添加了两个Live Templates：cc(comment for class) 以及 mc(comment for method)。编写代码时，只要鼠标落在类或者方法内部，输入cc或者mc后再输入一个Tab键，就可以自动插入注释模板。
