# algorithm
## [反转字符]()
```java
public static String reverseString(String s) {
        if (s == null || "".equals(s.trim())) {return s;}
        if (s.length() < 2) {return s;}
        char[] arr = s.toCharArray();
        int size = arr.length - 1;
        for (int i = 0; i <= size; i++) {
            if (arr[i] == arr[size - i]) {
                continue;
            }
            if (i >= (size + 1) / 2) {
                break;
            }
            char temp = arr[i];
            arr[i] = arr[size - i];
            arr[size - i] = temp;
        }
        return String.valueOf(arr);
    }
```


# review
## []()


# share
## 
oracle数据库迁移小计
对数据库的维护比较少，特别是oracle很多特性不知道
由于oracle导出时默认会带上用户名和表空间，所以，迁移是最好设置一样的数据库信息
不然 可以通过数据库连接在线传递，或者sql导出（小数据）上万就吃力了
或者，先导出表结构相关的，再单纯导出数据文件，导入数据文件
Tomcat多开需要配置Catalina启动相关属性，/etc/profile ，source生效


# tip
## 
替换字符
sed -i 's/替换前字符/替换后字符/g' 大文件（Linux）
sed -i "" 's/替换前字符/替换后字符/g' 大文件（mac os）
split -b 大小单位（100m） 大文件 分割后文件前缀
split -l 行数 大文件 分割后文件前缀
cat 小文件前缀*  > 合并后文件名
wc -l 大文件  （统计行数）
chmod +x 文件 （赋予执行权限）





