####网络编程####
1. 计算机网络的概念和历史(百度百科和wikipedia)
2. 计算机网络现在的发转状况和应用(一句话: 无所不在)
3. 现在通用的计算机五层网络模型介绍.

由应用层和网络层引入下面要讲的内容;同时
以最终的两个实践例子为目标:
- 爬虫模型实现(可以应用到前面刚学习的多线程和文件I/O课程.)
- http服务器模型实现.
- QQ聊天Demo实现

###URL(Unifieds Resource Location)###
1. 概念
	URL is an acronym for Uniform Resource Locator and is a reference (an address) to a resource on the Internet.
	URL是统一资源定位器的首字母缩写，是Internet上资源的参考(地址)。 
	*注意：网络资源不仅仅指文件（file）资源，可以是硬件资源，甚至是命令(rpc远程访问).*

2. 组成部分：［协议］：//［资源位置］
	协议如：file:/,http,ftp,
	资源位置包括: 主机名(:接口)/资源位置/（#文件中的参考点）

3. JAVA中URL对象用法
注意：（https://docs.oracle.com/javase/tutorial/networking/urls/urlInfo.html）Remember that not all URL addresses contain these components. The URL class provides these methods because HTTP URLs do contain these components and are perhaps the most commonly used URLs. The URL class is somewhat HTTP-centric.

3. 实践1: 连接并获取内容，然后存到文本中;
4. 和xpath，xml内容联系起来，实现简单的爬虫Demo;
5. 和jdbc(数据库)联系起来，实现更可适用一些的爬虫Demo;

###IP表示###
1. 概念和用途
2. 由URL对象引出InetIpAddress对象;
3. 实践用法

###Socket编程###
1. http服务器Demo实现;



