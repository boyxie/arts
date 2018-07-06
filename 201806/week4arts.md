# algorithm
## [存在重复](https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/24/)
给定一个整数数组，判断是否存在重复元素。
如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。

先实现一个，感觉自己的思维还是停留在业务处理的方式，先坚持下去，不管结果，继续看书

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums.length < 1)
            return false;
        Set set = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        return set.size()!=nums.length;
    }
}
```

# review
## [5-Hour Rule: If you’re not spending 5 hours per week learning, you’re being irresponsible](https://medium.com/the-mission/the-5-hour-rule-if-youre-not-spending-5-hours-per-week-learning-you-re-being-irresponsible-791c3f18f5e6)
## 5小时规则：如果你不是每周花5个小时学习，那你就是不负责任的
文章开始就抛出了一个无法反驳的问句：（其实对于学习的人这一句就可以了）
为什么世界上最聪明，最忙碌的人每天都会花一小时进行刻意学习（5小时规则），而其他人则为他们的忙碌找借口？
文章概要：
知识是最好的价值投资和财富
现代社会不断有新的技术和设备帮助人们解决各类问题
比如自动驾驶等自动化技术正在取代低级的重复性劳动，社会需要更高阶的技术性工种
作者介绍了掌握新知识经济的6项基本技能
1. 对的时间学对的知识
2. 快速学习和掌握知识
3. 展现自己的技能
4. 将知识变现
5. 用于金融投资
6. 掌握如何学习的技巧
随后列举了几位如何挤出时间看书学习的案例
列举了三个方法如果保证每周学习五小时


# share
## 分布式集群的高并发下出现并发插入的重复数据
1. 在秒杀的情况下，肯定不能如此高频率的去读写数据库，会严重造成性能问题的
必须使用缓存，将需要秒杀的商品放入缓存中，并使用锁来处理其并发情况。当接到用户秒杀提交订单的情况下，先将商品数量递减（加锁/解锁）后再进行其他方面的处理，处理失败在将数据递增1（加锁/解锁），否则表示交易成功。
当商品数量递减到0时，表示商品秒杀完毕，拒绝其他用户的请求。
2. 这个肯定不能直接操作数据库的，会挂的。直接读库写库对数据库压力太大，要用缓存。
把你要卖出的商品比如10个商品放到缓存中；然后在memcache里设置一个计数器来记录请求数，这个请求书你可以以你要秒杀卖出的商品数为基数，比如你想卖出10个商品，只允许100个请求进来。那当计数器达到100的时候，后面进来的就显示秒杀结束，这样可以减轻你的服务器的压力。然后根据这100个请求，先付款的先得后付款的提示商品以秒杀完。
3. 首先，多用户并发修改同一条记录时，肯定是后提交的用户将覆盖掉前者提交的结果了。
这个直接可以使用加锁机制去解决，乐观锁或者悲观锁。
乐观锁，就是在数据库设计一个版本号的字段，每次修改都使其+1，这样在提交时比对提交前的版本号就知道是不是并发提交了，但是有个缺点就是只能是应用中控制，如果有跨应用修改同一条数据乐观锁就没办法了，这个时候可以考虑悲观锁。
悲观锁，就是直接在数据库层面将数据锁死，类似于oralce中使用select xxxxx from xxxx where xx=xx for update，这样其他线程将无法提交数据。
除了加锁的方式也可以使用接收锁定的方式，思路是在数据库中设计一个状态标识位，用户在对数据进行修改前，将状态标识位标识为正在编辑的状态，这样其他用户要编辑此条记录时系统将发现有其他用户正在编辑，则拒绝其编辑的请求，类似于你在操作系统中某文件正在执行，然后你要修改该文件时，系统会提醒你该文件不可编辑或删除。
4. 不建议在数据库层面加锁，建议通过服务端的内存锁（锁主键）。当某个用户要修改某个id的数据时，把要修改的id存入memcache，若其他用户触发修改此id的数据时，读到memcache有这个id的值时，就阻止那个用户修改。
5. 实际应用中，并不是让mysql去直面大并发读写，会借助“外力”，比如缓存、利用主从库实现读写分离、分表、使用队列写入等方法来降低并发读写。


# tip
## MySQL分页查询优化总结
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




