# algorithm
## []()
```java

```


# review
## []()


# share
## 

# tip
## 
linux安装mysql的时候由于没注意以下权限问题，造成启动失败：The innodb_system data file 'ibdata1' must be writable

如果我是root身份登录Linux系统，可以执行：mysqld --initialize --user=mysql或者mysqld --initialize-insecure --user=mysql。
如果我是以mysql用户登录Linux系统，可以执行：mysqld --initialize或者mysqld --initialize-insecure。
初始化mysql:
mysqld --initialize --user=mysql
启动mysql
systemctl  start  mysqld

当我们在不通过yum(CentOS<redhat>)、apt-get(Ubuntu<debian>)来安装MySQL的时候，通常执行以下命令来改变目录的拥有者：
chown -R mysql:mysql /var/lib/mysql

赋予新账号权限
SHOW DATABASES ;
use mysql;
CREATE USER 'admin'@'%' IDENTIFIED WITH mysql_native_password BY 'Aa123456';
GRANT ALL ON *.* TO 'admin'@'%';
FLUSH PRIVILEGES;
