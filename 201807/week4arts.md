# algorithm
## [合并两个有序链表](https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/6/linked-list/44/)
```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null) return l2;
        if (l2==null) return l1;
        ListNode preListNode = null;
        if (l1.val < l2.val) {
            preListNode = l1;
            preListNode.next = mergeTwoLists(l1.next, l2);
        } else {
            preListNode = l2;
            preListNode.next = mergeTwoLists(l1, l2.next);
        }
        return preListNode;
    }
```
一直徘徊在while循环的胡同里，想了好久也没理清思路，还是参考了人家答案，才知道递归，
对于的递归的理解还是不明白，没有由浅入深的概念

# review
## [How to Make Your Code Readable](https://medium.com/s/story/the-importance-of-readable-code-165895e939c7)
## 增强自己的代码可读性
作者解释了为什么可读性比高性能的代码更重要
主要是变得可维护，特别是团队合作和长久维护的代码
并举例了一段代码，从头优化，主要是:
1:消除不可读的变量名
2：消除不必要的中间变量
3：修改有歧义的变量


# share
## 递归的原理


# tip
## 为什么使用16进制
因为二进制的倍数表示可以直接拆分转换，一个字节是8位，如果二进制的话需要8个0或1表示，而16进制则两位就可以，并已0x开头
而且计算机只要根据4位对应1位替换就能转换成二进制，省去了计算，相比于10进制转换要快很多

cpu调用指令是使用后进先出的栈，这样可以根据程序执行的递归性方便的调用和删除内存空间
