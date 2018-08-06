# algorithm
## [删除链表中的节点](https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/6/linked-list/41/)
```java
public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
```
原理底层的链表结构比较特殊哈，需要联系链表的增删改查熟悉一下


# review
## [I Love My Mac, but Apple Just Isn’t Fun Anymore](https://medium.com/s/story/apple-has-lost-its-charm-66d94e2152c7)
总的来说就是，苹果依然很出色，但是没有那种让人兴奋的感觉

# share
## 链接的增删改查


# tip
## 深入理解计算机系统-性能优化
优化的同时要尽量保持代码的可读性
1、优化重复，不必要的代码
2、循环里的变量，引用最好只有一份
3、循环展开，尽量减少多层循环，用空间换时间
4、并行行，使用可预测的代码




