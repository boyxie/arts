# algorithm
## []()
```java

```


# review
## []()


# share
## java内存模型
锁操作的happen-before
volatile变量的happen-before
线程的happen-before
构造器的happen-before
主要是为了防止编译器的重排序
java采用的是内存屏障，实现方式是在代码前后加上内存强制刷新等手段，已确保拿到最新值
这样的话寄存器就不能缓存当前值，需要从内存实时读取，对于高并发来说性能影响较大


# tip
## 云服务
公网IP和SLB的区别
服务器如果需要外网访问就需要公网IP，公网IP可以单台设置也可以VPC网络统一设置
SLB是负载均衡，外部流量访问SLB的时候会分发给后端服务器
所以访问入口IP是SLB的公网IP，而出口IP则是VPC的公网IP或者服务器的弹性IP
如果VPC不提供外网访问则统一SLB出入



