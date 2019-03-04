# algorithm
## []()
```java

```


# review
## []()


# share
## 

# tip
## springmvc线程安全
springmvc里每个请求默认都是新起一个servlet线程，所以调用后面的controller和service（都是单例）里面的方法, 方法是在虚拟机栈中执行的
每个线程都有各自的栈空间, 所以没有线程安全问题,
但是如果在 controller类中 定义了 属性, 并且在方法中使用, 这个时候就存在线程安全问题了.因为多个线程共享 controller的对象实例的属性.
