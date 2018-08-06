# algorithm
## [字符串中的第一个唯一字符](https://leetcode-cn.com/submissions/detail/4908606/)
想来想去还是双重循环吧，结果速度确实不快，看了答案，人家直接字符操作，毕竟只有26个小写字母，
思维很重要，特定问题还是要把边界搞清楚，才能找到最优化，
业务代码写多了，往往会固定思维方式
```java
public int firstUniqChar(String s) {
        if(s.length()>0){
            if(s.length()==1) return 0;
            boolean repeated = false;
            for(int i=0;i<s.length();i++){
                repeated = false;
                int j=0;
                for(;j<s.length();j++){
                    if(j!=i&&s.charAt(j)==s.charAt(i)){
                        repeated = true;
                        break;
                    }
                }
                if(!repeated)
                    return i;
            }
        }
        return -1;
    }
```
```java
public int firstUniqChar(String s) {
     int result = -1;
	        for(char c = 'a';c<='z';c++){
	            int index = s.indexOf(c);
	            if(index != -1 && index == s.lastIndexOf(c)){
	                result = result != -1?Math.min(result,index):index;
	            }
	        }
	        return result;
    }
```


# review
## [What I learned from doing 1000 code reviews](https://hackernoon.com/what-i-learned-from-doing-1000-code-reviews-fe28d4d11c71)
## 我从一百次代码审查中学会了什么
Suggestion 1: Throw an exception when things go wrong
尽可能抛出问题，可以尽早发现问题并修改
Suggestion 2: Use the most specific type possible
使用具体的类型和名字（URL和urn），已表示明确的参数传值
Suggestion 3: Use Optionals instead of nulls
Bonus Suggestion: “Unlift” methods whenever possible
```java
// AVOID:
CompletableFuture<T> method(CompletableFuture<S> param);
// PREFER: 
T method(S param);

// AVOID:
List<T> method(List<S> param);
// PREFER:
T method(S param);

// AVOID: 
T method(A param1, B param2, Optional<C> param3);
// PREFER:
T method(A param1, B param2, C param3);
T method(A param1, B param2);
// This method is clearly doing two things, it should be two methods
// The same is true for boolean parameters
```

# share
## RPC原理
<img src="https://static001.geekbang.org/resource/image/85/25/8534c52daf3682cd1cfe5a3375ec9525.jpg" width="800" hegiht="313" align=center />
超哥这个图，真的很明了，有时间再深入理解一下


# tip
## 记几个常用的长命令
ssh -p 8022 root@1.2.3.4 加端口的ssh连接

scp -r /Users/docker/*  root@1.2.3.4:/usr/docker/  上传所有文件到服务器

redis-cli -h host -p 6379 -a password  远程连接redis
