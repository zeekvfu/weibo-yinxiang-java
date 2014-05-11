#weibo-yinxiang-java

store your favorited weibo ([Sina Weibo](http://weibo.com/)) to [yinxiang](http://www.yinxiang.com/)(Chinese version of [Evernote](http://www.evernote.com/))  
将新浪微博中收藏的微博转存到印象笔记中  

###使用方法：  
1. 下载并配置新浪微博提供的 Java SDK，并获取 `access_token`。具体过程可以参考我的[这篇博客](http://mindcache.info/2014/05/11/sina-weibo-oauth2-java-edition.html)  
2. 下载 [weibo-yinxiang-java](https://github.com/zeekvfu/weibo-yinxiang-java) 项目代码，并将 `.java` 文件所在目录重命名为 `weibo_yinxiang`  
3. 将 `weibo_yinxiang` 移动至 `weibo4j-oauth2/examples` 目录下，相当于在 `examples` 目录下增加了一个新的 package  
4. 运行 `weibo_yinxiang` 这个 package 下的 `weibo_yinxiang.java` 文件，运行时需要以 `access_token` 值作为参数  

###注意事项：  
* 用「@我的印象笔记」的方式、评论后保存微博，可能保存失败
如果保存失败，@我的印象笔记 账户将会给用户发送微博提醒，其中包含了原微博的 URL 或 id。
但是，目前新浪微博 API 禁止了第三方软件的私信访问权限。
如果新浪微博 API 允许第三方软件访问私信，程序可以通过私信，对之前保存失败的微博重新保存。
因此，目前只能手工处理保存失败的微博。具体方法可以参考 `weibo_yinxiang.java` 源文件中 `main()` 函数相关部分代码。
* 新浪微博 API 调用次数限制问题
如果应用程序没有通过新浪微博的审核，则新浪对于接口的调用频次有一定的[限制](http://open.weibo.com/apps/1076129385/privilege/limit)。本应用程序主要使用的的接口是发评论接口，具体限制是：每小时至多 15 次，每天至多 50 次。

###参考链接：  
[新浪微博 OAuth2.0 接口认证之 Java 版](http://mindcache.info/2014/05/11/sina-weibo-oauth2-java-edition.html)
[用 Java SDK 将收藏的微博转存到印象笔记](http://mindcache.info/2014/05/11/use-java-sdk-to-store-favorited-weibo-to-yinxiang.html)
[新浪微博开放私信接口是个好主意吗？](http://www.zhihu.com/question/20649834)


