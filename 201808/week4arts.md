# algorithm
## []()
```java

```


# review
## []()


# share
## mysql

sum(if(color='red',1,0))=sum(color='red')=count(color='red' or null)
order by null
char总是分配最大定长字节数，varchar则是可变的
查询缓存需要sql全匹配

# tip
## mysql性能优化

查询过程：客户端->服务端->解析->生成执行计划->执行（重要）->返回结果

大查询分小查询，切分查询（批量删除新增），分解关联查询（主子表类型）

查询成本选择执行计划，但是缓存，信息不准等需要综合考虑
优化范围：表顺序，外关联转内关联，等价规则转变，优化count、min、max，
in不等同于or，优化器处理方式不同，in的处理会比较快
5.6版本以上关联查询和子查询性能一致



