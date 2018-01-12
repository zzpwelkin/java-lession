# 网络编程 #
1. 计算机网络的概念和历史([百度百科][]和[wikipedia](https://en.m.wikepedia.org/wiki/Computer_network));
2. 计算机网络现在的发转状况和应用;
3. 现在通用的计算机五层网络模型介绍:
![网络层结构1][Layer_model_simple]
![网络层结构2][Layer_model_wiki]

[Layer_model_simple]: 网络层结构.jpg "简单的网络层结构"
[Layer_model_wiki]: network_layer_model.jpg "Network file layer model"

**实践目标:**
- 爬虫模型实现(可以应用到前面刚学习的多线程和文件I/O课程.)
- http服务器模拟.
- QQ聊天Demo实现

## URL(Unifieds Resource Location) ##
1. 概念
	URL是统一资源定位器的首字母缩写，是Internet上资源的参考(地址)。 

	>> URL is an acronym for Uniform Resource Locator and is a reference (an address) to a resource on the Internet.  

	_注意：网络资源不仅仅指文件（file）资源，可以是硬件资源，甚至是命令(rpc远程访问)._

2. 组成部分：［协议］：//［资源位置］
	协议如：file,http,ftp,
	资源位置包括: 主机名(:接口)/资源位置/（#文件中的参考点） 

3. JAVA中 [URL对象](https://docs.oracle.com/javase/tutorial/networking/urls/urlInfo.html)用法

	```
	两种构造方法创建URL对象;
	获取协议、主机名称、端口、文件名、参考名等信息;

	练习url:
	file:///D:/lession4/
	http://example.com/a b/c.html
	http://example.com:8080/a.html#hello
	```

	_注意：Remember that not all URL addresses contain these components. The URL class provides these methods because HTTP URLs do contain these components and are perhaps the most commonly used URLs. The URL class is somewhat HTTP-centric._ 

4. 创建连接并读/写数据

	1step. 创建URL对象`url = URL(_url_)` 

		```
		try {
			URL url = new URL(...);
		} catch(MalformedURLException e) {
		// exception handler code
		//...
		}
		```

	2step. 获得`URLConnect`. 

		```
		URLConnection con = url.openConnection();
		```

	3step. 设置`URLConnect`对象属性(**可选**) 

		```
		// set connect can post data to service
		con.setDoOutput(true); 
		```

	4step. 读(or写)数据 

		```
		BufferedReader br = new BufferedReader(new InputStreamReader(
								con.getInputStream()));
		String inputLine;
		while( (inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
		}
		in.close();
		```

5. **实践:**爬虫实现－获取数据及持久化
	* 从指定URL上并获取内容，然后存到文本中;
	* 只保存有用的信息到文件中;
	* 多线程实现多个页面的获取;
	* 商品详细信息的爬取;


### Socket编程 ###
### IP表示 ###
1. 概念和用途
2. 由URL对象引出InetIpAddress对象;
3. 实践用法
1. http服务器Demo实现;

