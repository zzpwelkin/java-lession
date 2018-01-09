# 网络编程 #

** 学习内容 **
- 计算机网络基本概念介绍;
- URL理解和JAVA URL对象使用; 
- TCP/IP网络协议通信模型和`socket`编程; 
- UDP网络协议模型和`datagram`编程; 

** 实践目标: **
- 爬虫; 
- http服务器; 
- QQ聊天工具; 
- 局域网通信(如飞鸽)聊天工具; 

## 网络基础知识 ##
1. 计算机网络的概念和历史([百度百科][]和[wikipedia](https://en.m.wikepedia.org/wiki/Computer_network))

	>> 计算机网络就是把分布在不同地理位置的计算机和专门的外部设备使用专门的通讯线路互联成一个规模大功能强的网络。从而使得众多的计算机可以方便的相互传递信息，共享资源.

2. 计算机网络的功能

	- 资源共享
	- 信息传输与集中处理
	- 均匀负荷与分布处理
	- 综合信息服务

3. 计算机网络的分类

	按照地理范围: 

		- 局域网 LAN
		- 城域网 MAN
		- 广域网 WAN

	按照通信介质分类: 

		- 有限网络
		- 无限网络

4. 计算机分层模型介绍: 

![网络层结构1][Layer_model_simple]
![网络层结构2][Layer_model_wiki]

[Layer_model_simple]: 网络层结构.jpg "简单的网络层结构"
[Layer_model_wiki]: network_layer_model.jpg "Network file layer model"

## URL(Unifieds Resource Location) ##
1. 概念
	URL是统一资源定位器的首字母缩写，是Internet上资源的参考(地址)。 

	>> URL is an acronym for Uniform Resource Locator and is a reference (an address) to a resource on the Internet.  

	_注意：网络资源不仅仅指文件（file）资源，可以是硬件资源，甚至是命令(rpc远程访问)._

2. 组成部分：［协议]：//［资源位置］

	协议如：`file,http,ftp,pop3`

	资源位置包括: 主机名[:端口]/资源位置/[#文件中参考点] 

3. JAVA中 [URL对象](https://docs.oracle.com/javase/tutorial/networking/urls/urlInfo.html)用法

	1step. 创建URL对象`url = URL(_url_)`; 
	2step. 通过URL对象`url`获取资源定位符中包含的信息;

	|接口名称|接口描述|
	|!-------|!------|
	|getProtocol|使用的协议｜
	|getHost|主机名|
	|getPort|端口号|
	|getPath|资源位置|
	|getRef|文件中参考点|
	|getQuery|查询字符串|

	练习例子:

	```
	http://search.dangdang.com/?key=简明微积分
	```

	3setp. 通过URL对象`url`连接资源并读取资源;

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

	_注意：Remember that not all URL addresses contain these components. The URL class provides these methods because HTTP URLs do contain these components and are perhaps the most commonly used URLs. The URL class is somewhat HTTP-centric._ 

5. ** 实践: **

	* 从指定URL上并获取内容，然后存到文本中;
	* 只保存有用的信息到文件中;
	* 多线程实现多个页面的获取;
	* 商品详细信息的爬取;


### TCP/IP网络协议通信模型和`socket`编程 ### 
### UDP网络协议模型和`datagram`编程 ### 

