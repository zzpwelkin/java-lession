####并发(Concurrency)编程####

_概念理解_

a. 进程(Processes)
	分布式计算;网络服务(如http,ftp协议等);
b. 线程(Threads)

	<<{从小到大}各方面应用举例.>>
	在线视频app;网络服务;

###线程(Thread)编程###

##线程对象基本使用方法##
1. _定义和启动一个线程_
2. _挂起(halt)执行_
3. _中断(Interrupts)处理_
4. _等待处理_
	`Joins`
	- Producter/Consumer模式重实现

5. _综合回顾和实例_


##一致性问题(Synchronization)##
1. _线程扰动(Thread Interference)现象_
2. _内存不一致问题_

#解决方法:#
1. _内部锁解决一致性问题(Intrinsic Locks)_
2. 原子操作访问(Atomic Access)*[原则]*

##线程锁问题(Liveness)##
1. _死锁(DeadLock)_
2. _解饿和频繁锁现象(Starvation and Livelock)_

###高级并发变成###
##Lock Objects##
##Executors##
##Concurrent Collections##
- Producter/Consumer模式重实现

