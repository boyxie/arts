# algorithm
## [最后一个单词的长度](https://leetcode-cn.com/problems/length-of-last-word/description/)
```java
class Solution {
    public int lengthOfLastWord(String s) {
        if(s==null || s.trim().length()<1) return 0;
        String[] arr = s.split(" ");
        return arr[arr.length-1].length();
    }
}
```
这个题目确实很简单，不过在边界问题上还是忘记了空格字符串的问题
看了下被人的思路，确实有优化的空间，思维上的固定，还是偏向于可阅读的代码风格
s.trim().length()-s.trim().lastIndexOf(" ")-1;

# review
## [How not to be a mediocre developer!](https://hackernoon.com/how-not-to-be-a-mediocre-developer-c59a49f97fc5)
## 如何成为一名不平庸的工程师
1. 写足够多的代码，只有不断的实践和练习
2. 写测试用例
3. 实话实话，不会的东西就说不会
4. 参与开源项目，增加自己的广度和深度，新建一个自己的开源项目
5. 不要高估自己，始终保持学习心态
6. 理解为什么这么做，而不是怎么做，不要偷懒
7. 敢于挑战难题，保持创新，敢于承担责任

# share
## MySQL索引背后的数据结构及算法原理
1. 索引的本质是通过特定算法可以快速高效查询的特定数据结构（哈）
2. B-Tree和B+Tree（数据结构需要补充）
B-Tree有许多变种，其中最常见的是B+Tree，例如MySQL就普遍使用B+Tree实现其索引结构。
与B-Tree相比，B+Tree有以下不同点：每个节点的指针上限为2d而不是2d+1。内节点不存储data（重要），只存储key；叶子节点不存储指针。
一般来说，B+Tree比B-Tree更适合实现外存储索引结构
在B+Tree的每个叶子节点增加一个指向相邻叶子节点的指针，就形成了带有顺序访问指针的B+Tree。做这个优化的目的是为了提高区间访问的性能
由于磁盘IO读取非常耗时，一般是内存的几百倍，所以读取时会预读，预读的长度一般为页（page）的整倍数
每次新建节点时，直接申请一个页的空间，这样就保证一个节点物理上也存储在一个页里，加之计算机存储分配都是按页对齐的，就实现了一个node只需一次I/O。
B-Tree中一次检索最多需要h-1次I/O（根节点常驻内存），渐进复杂度为O(h)=O(logdN)。一般实际应用中，出度d是非常大的数字，通常超过100，因此h非常小（通常不超过3）。
综上所述，用B-Tree作为索引结构效率是非常高的。
3. MySQL索引实现
MyISAM引擎使用B+Tree作为索引结构，叶节点的data域存放的是数据记录的地址
InnoDB索引实现，叶节点data域保存了完整的数据记录，这种索引叫做聚集索引，第二个与MyISAM索引的不同是InnoDB的辅助索引data域存储相应记录主键的值而不是地址
因为InnoDB的数据文件本身要按主键聚集，所以InnoDB要求表必须有主键（MyISAM可以没有）
聚集索引这种实现方式使得按主键的搜索十分高效，但是辅助索引搜索需要检索两遍索引：首先检索辅助索引获得主键，然后用主键到主索引中检索获得记录。
不建议使用过长的字段作为主键，因为所有辅助索引都引用主索引，过长的主索引会令辅助索引变得过大
不建议使用非单调的字段作为主键，会造成在插入新记录时数据文件为了维持B+Tree的特性而频繁的分裂调整，十分低效
4. 索引使用策略及优化
情况一：全列匹配。理论上索引对顺序是敏感的，但是由于MySQL的查询优化器会自动调整where子句的条件顺序以使用适合的索引
情况二：最左前缀匹配。左边连续一个或几个列时（如果非最左连续不行），如<emp_no>或<emp_no, title>，所以可以被用到，但是只能用到一部分
情况三：查询条件用到了索引中列的精确匹配，但是中间某个条件未提供。只用到最左一个，如果中间值不多，可以使用in（全部值），填补中间索引
情况四：查询条件没有指定索引第一列。索引无效
情况五：匹配某列的前缀字符串。不是前模糊查询可以使用索引
情况六：范围查询。范围列可以用到索引（必须是最左前缀），但是范围列后面的列无法用到索引。同时，索引最多用于一个范围列，因此如果查询条件中有两个范围列则无法全用到索引。
特殊：字符串的“BETWEEN”实际上相当于“IN”，不属于范围查询，因此在MySQL中要谨慎地区分多值匹配和范围匹配
情况七：查询条件中含有函数或表达式。无法为其使用索引，MySQL也不会优化
以上基于单索引，多索引机制复杂，需要具体实际优化
5. 索引选择性与前缀索引
第一种情况是表记录比较少，记录数不超过 2000可以考虑不建索引，超过2000条可以酌情考虑索引。
另一种不建议建索引的情况是索引的选择性较低，如果0，1
有一种与索引选择性有关的索引优化策略叫做前缀索引，就是用列的前缀代替整个列作为索引key，
当前缀长度合适时，可以做到既使得前缀索引的选择性接近全列索引，同时因为索引key变短而减少了索引文件的大小和维护开销。
```mysql
SELECT count(DISTINCT(concat(first_name, left(last_name, 4))))/count(*) AS Selectivity FROM employees.employees;
```
前缀索引兼顾索引大小和查询速度，但是其缺点是不能用于ORDER BY和GROUP BY操作，也不能用于Covering index
6. InnoDB的主键选择与插入优化
在使用InnoDB存储引擎时，如果没有特别的需要，请永远使用一个与业务无关的自增字段作为主键。
InnoDB使用聚集索引，数据记录本身被存于主索引（一颗B+Tree）的叶子节点上。
这就要求同一个叶子节点内（大小为一个内存页或磁盘页）的各条数据记录按主键顺序存放
由于每次插入时也不需要移动已有数据，因此效率很高，也不会增加很多开销在维护索引上
(主要是主索引维护上的开销要小，如果不是自增，就相当于随机数字，需要频繁的移动和分页，造成大量的碎片)

7. MySQL索引与Index Condition Pushdown
ICP只能用于二级索引，不能用于主索引。
也不是全部where条件都可以用ICP筛选，如果某where条件的字段不在索引中，当然还是要读取整条记录做筛选，在这种情况下，仍然要到server端做where筛选。
ICP的加速效果取决于在存储引擎内通过ICP筛选掉的数据的比例。


# tip
## 算法第一章学习
将自己做的每个项目，每个类，每个方法抽象出来，可以重复使用，并经常更新优化它，写好相应的文档（积累和心得）
System.out.println(1.0/0.0);  结果是无穷大

关于字符编码，你所需要知道的（ASCII,Unicode,Utf-8,GB2312…）
ASCII字符集由95个可打印字符（0x20-0x7E）和33个控制字符（0x00-0x19，0x7F）组成。（0x20这种16进制表示法，只是为了可读性，最终还是转为01） 
ASCII占用了1个字节8个bit为2的8次方，但是只使用了128个字符，剩下最高位1比特被用作一些通讯系统的奇偶校验。所以有了OEM字符集的衍生（利用后128位）
多字节字符集（MBCS）和中文字符集，使用1-2个字节，根据第一个字节来选择不同的码表进行解析，
如GB2312的第一个字节的最高位是0，类似单字节，如果为1，则根据第一个字节寻找不同的字码表，进行解码
Unicode字符集涵盖了目前人类使用的所有字符，并为每个字符进行统一编号，分配唯一的字符码（Code Point）。
Unicode字符集将所有字符按照使用上的频繁度划分为17个层面（Plane），每个层面上有2e16=65536个字符码空间。
UCS-2和UTF-16对于BMP层面的字符均是使用2个字节来表示，并且编码得到的结果完全一致，不同的是UTF-16可以对其他层编码，2-4个字节可变长编码
UTF-8编码方案采用1-4个字节来编码字符，对于ASCII字符的编码使用单字节，和ASCII编码一摸一样，这样所有原先使用ASCII编解码的文档就可以直接转到UTF-8编码了。
对于其他字符，则使用2-4个字节来表示，其中，首字节前置1的数目代表正确解析所需要的字节数，剩余字节的高2位始终是10。
例如首字节是1110yyyy，前置有3个1，说明正确解析总共需要3个字节，需要和后面2个以10开头的字节结合才能正确解析得到字符。
字符集（Character Set），字面上的理解就是字符的集合，例如ASCII字符集
字符码（Code Point）指的就是字符集中每个字符的数字编号。

工作中遇到的小问题
```java
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = 
        new MappingJackson2HttpMessageConverter();
        /**
         * 序列换成json时,将所有的long变成string
         * 因为js中得数字类型不能包含所有的java long值
         * 第18位开始默认为0
         */
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }

}
```
### 又发现新问题，全局修改后，微信公众号消息回复中的换行符不能被解析，原样输出了
#[解决办法](http://orchidflower.oschina.io/2018/06/22/Handling-Bigint-using-Jackson-in-Springboot/)
