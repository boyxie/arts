# algorithm
## [从排序数组中删除重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/description/)

刚开始做算法，很容易直接想到用set去重，还没有适应算法的概念，很容易跑偏，需要补充算法基础思想
方法：双指针法
循环比较当前值与下一个值是否相同，不同的话就把值赋值给当前数组的下一个，直到循环结束

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[size]) {
                nums[++size] = nums[i];
            }
        }
        return size + 1;
    }
}
```

# review
## [People Who Have “Too Many Interests” Are More Likely To Be Successful According To Research](https://medium.com/the-mission/modern-polymath-81f882ce52db)
## 通过研究表明有足够多兴趣的人更有可能成功（耗叔推荐）
### 文章分为三部分
1. 第一部分介绍了博学者的前世和今生
如以前人们常说不要学而不精，不要面面俱到，不然不会成功还列举了中国的例子
后来又列举了多位历史和现在的成功人士，都是结合了不同领域的杰出技术，才发现或发明了新的领域或产品
他们会自发的去学习相关领域的知识用于突破自己的核心领域
他定义一个现代博学者是指至少在三个不同领域中胜任并将其整合到最高1％技能组合中的人（翻译不通）。
2. 第二部分为什么需要博学者
对于当代：社会更需要通才
作者建议我们做至少两个行业的前25%，那样比做一个行业的佼佼者容易，也更可能成功
最具创建性的突破更多来自于多个领域的组合
掌握多技能后更容易学习新技能
作者举例技能就像是积木，积木越多，越能组合出不可思议的事物
现代社会职位日新月异，我们更需要博学的知识，以适应多年后更多新兴的职业
3. 第三部分如果成为博学者
最后作者简单介绍一下如何成为博学者，并顺便推荐了他的博学者俱乐部

# share
## 微信订阅号开发的一些坑
### 问题1
服务上线，配置都调好的情况下，在微信后台启用开发者模式后，一直没有收到微信推送的消息。
首页我先在微信后台重新保存了一下配置，等了一会还是没收到。
继续尝试用另外一个订阅号开启服务，结果刚开启收到了消息，以为是原订阅号有问题
仔细发现是原订阅号的消息，而刚启用的订阅号地址配到了测试环境（线上排查，不够冷静）
结论：暂时认定微信消息服务器的启用应该存在一定的延时，可能会在几分钟到十几分钟
但是测试号没有这样的问题，后续继续观察吧
### 问题2
微信服务器的白名单：获取各类型token都要添加各自的白名单，各不相同，需要看日志配置
### 问题3
线上服务用户发现报错，是一个数据重复的问题，而且报错没有catch，进行口语化提示
分析原因：查询数据发现，数据一样，包括创建时间，初步分析用户在网络不好的情况下，导致重复请求，插入两条数据
改进：暂时数据库加唯一键，后期分析一下加锁的各种类型和适用场景
### 总结
1. 上线发布，总会出现各类问题，需要冷静对待，跟踪详细日志排除问题
2. 需要改进：分布式集群服务日志收集需要跟进，日志埋点和级别需要统一和全面

# tip
## spring基础知识复习要点
1. Spring 依赖注入
Setter注入、构造函数注入、（避免循环依赖）
2. Spring的自动装配有三种模式：byTpye(根据类型)，byName(根据名称)、constructor(根据构造函数)。
@Autowired是按类型匹配的(byType)，如果需要按名称(byName)匹配的话，可以使用@Qualifier
@Autowired @Qualifier("userDao") = @Resource(name=“userDao”)
3. @Value注解的自动装配以及properties文件读取
4. @Component与@Service和@Repository相同效果
@Component与@Service的含义并无差异，只不过@Service更能让我们明白该类为业务类罢了。
@Repository在表示数据访问层含义的同时还能够启用与Spring数据访问相关链的其他功能
5. Bean的重写机制：当声明的bean的名称一样时，后者会覆盖前者
6. Bean的作用域：默认Singleton，prototype作用域
一般情况下，对有状态的bean应该使用prototype作用域，而对无状态的bean则应该使用singleton作用域。
所谓有状态就是该bean有保存信息的能力，不能共享，否则会造成线程安全问题，
而无状态则不保存信息，是线程安全的，可以共享，spring中大部分bean都是Singleton，整个生命周期过程只会存在一个。
7. request与session作用域
关于request作用域，对于每次HTTP请求到达应用程序，Spring容器会创建一个全新的Request作用域的bean实例，
且该bean实例仅在当前HTTP request内有效，整个请求过程也只会使用相同的bean实例，因此我们可以根据需要放心的更改所建实例的内部状态，
而其他请求HTTP请求则创建新bean的实例互不干扰，当处理请求结束，request作用域的bean实例将被销毁，
如在接收参数时可能需要一个bean实例来装载一些参数，显然每次请求参数几乎不会相同，
因此希望bean实例每次都是足够新的而且只在request作用域范围内有效。
关于Session可能你也已猜到，每当创建一个新的HTTP Session时就会创建一个Session作用域的Bean，并该实例bean伴随着会话的存在而存在。
8. IOC 与依赖注入的区别
IOC：控制反转:将对象的创建权,由Spring管理。
DI(依赖注入)：在Spring创建对象的过程中,把对象依赖的属性注入到类中。


