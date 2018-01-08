[TOP]


###内容复习###
ArrayList

###LinkedList学习###
1. link单词学习
2. 生活中常见例子：自行车链条，火车，过山车

##使用方法介绍:##
|size()|获取列表中数据个数|
|add(Object obj)|在列表末尾添加数据`obj`|
|add(int index, Object obj)|在指定位置`index`上添加数据`obj`|
|remove(int index)|删除指定位置`index`处的数据|
|iterator()|返回迭代器`Iterator`|

##使用实例##

```
List exam = new LinkedList();
System.out.format("取经团队人数%d", exam.size());

// 添加取经人员
exam.add("悟空");
exam.add("师傅");
exam.add("悟能");
exam.add("悟净");

System.out.format("取经团队人数%d", exam.size());

// 八戒要回高老庄
exam.remove(2)

// 当前取经人数
Iterator it = exam.iterator();
while(it.hasnext()){
    System.out.println(it.next());
}
```

添加新成员 _小龙马_
```
exam.add(1, "小龙马");
```

##练习题##

1. 超市出纳子系统。

    *需求描述:*
    a. 管理员查看当天的商品销售情况;
    b. 出纳人员输入的商品名称只能是系统中应有的;

    要求:
    查看销售情况输出格式如下:
    ```
    {当天日期} 下单总数为 {下单数目}
    ===============================
    {订单1｝
    -----
    {订单2}
    ------
    {订单m}
    ```
    *订单*格式如下：
    ```
    {下单时间} {商品名词} {数目}
    ```
##思考##
1. LinkedList和ArrayList不同列表存储结构的差别
