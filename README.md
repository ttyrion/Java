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
