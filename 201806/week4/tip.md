# MySQL分页查询优化总结
detail表11个字段，只有时间类型和数字类型
SELECT * FROM detail limit 1,20; 7ms
SELECT id FROM detail limit 1,20; 22.1ms
数据在百万以内影响不大
SELECT * FROM detail limit 1001000,20; 378ms
SELECT id FROM detail limit 1001000,20; 152ms

几种解决方式：
覆盖索引：
SELECT * FROM detail WHERE id >= (select id from detail limit 1000000,1) limit 20
可以达到 SELECT id FROM detail limit 1001000,20; 的查询效果
查询时间为0.2秒！
另一种写法
SELECT * FROM detail a JOIN (select id from detail limit 1000000,20) b on a.id=b.id

复合索引优化方法
例如：select id from detail where type=1 limit 1000000,20  
加上复合索引(type,id) 或者(id,type) 可以达到效果，

以上测试并非普通情况，需要根据表字段类型和大小实际调整

综上：如果出现性能问题，首先尝试建立合适的索引，如果查询字段较多，可以先查出ID，再根据ID去查询相应的详情


