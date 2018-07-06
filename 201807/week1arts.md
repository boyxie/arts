# algorithm
## [两数之和](https://leetcode-cn.com/problems/two-sum/description/)
给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
实现了一个两遍哈希表，看了下答案还可以优化成一遍哈希表，仔细想想这个平时在工作中就用到过
提出一个缺点：不能为了完成而完成，需要总结并不断改进，以前的知识也是业务逼迫自己去寻找最优方案，而没有自己主动改进

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
           Map<Integer,Integer> map = new HashMap<>();
           for(int i=0;i<nums.length;i++){
               map.put(nums[i],i);
           }
           for(int i=0;i<nums.length;i++){
               int p = target - nums[i];
               if (map.containsKey(p) && map.get(p)!=i) {
                   arr[0] = i;
                   arr[1] = map.get(p);
                   return arr;
               }
           }
           return arr;
    }
}
```

# review
## [SQL or NoSQL](https://engineering.salesforce.com/sql-or-nosql-9eaf1d92545b)
作者还原了他们在选择数据库和更换数据库的整个过程
一开始他们选择了社区流行的MongoDB作为日志的存储，且并未做任何的测试，直到遇到性能瓶颈
在尝试了优化和加中间层处理都失败后，他们开始重新选型：
首页基于业务，确定适合的性能指标：一亿条数据达到亚秒级别的查询
SELECT DEVICE_TYPE, COUNT(*), AVG(speed)  FROM logs GROUP-BY DEVICE_TYPE;
最后他们基于sql的优势，选择了支持sql的分析数据库Redshift
总结：基于自己业务，选择的合适的数据库，并进行性能测试


# share
## springAOP的实现
spring在实现自己的AOP是在实例初始化的过程中，判断实例是否根据注解或者其他的方式实现了它提供的模板方法中的
前置和后置方法，通过jdk动态代理和cglib代理在代理类里加入实现的相应方法


# tip
## 通过使用Byte Buddy，便捷地创建Java Agent
```java
//指定需要拦截的对象和方法
class SecurityAgent {
  public static void premain(String arg, Instrumentation inst) {
    new AgentBuilder.Default()
    .type(ElementMatchers.any())
    .transform((builder, type) -> builder
    .method(ElementMatchers.isAnnotatedBy(Secured.class)
    .intercept(MethodDelegation.to(SecurityInterceptor.class)
               .andThen(SuperMethodCall.INSTANCE))))
    .installOn(inst);
  }
}

//安全方法需要拦截的做的事情
class SecurityInterceptor {

  static String user = “ANONYMOUS”

  static void intercept(@Origin Method method) {
    if (!method.getAnnotation(Secured.class).user().equals(user)) {
      throw new IllegalStateException(“Wrong user”);
    }
  }
}

//声明一个安全控制的注解
@interface Secured {
  String user();
}

//调用一个有安全控制的方法
class Service {
  @Secured(user = “ADMIN”)
  void doSensitiveAction() {
    // 运行敏感代码...
  }
}


```



