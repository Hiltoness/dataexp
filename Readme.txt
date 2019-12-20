run LoginFrame.java
运行LoginFrame.java文件

LoginFrame--登录，Change--修改密码
StudentFrame--学生登录页面
Shili--教师登录页面, Shi_model--教师管理授课信息
mainframe--管理员登录页面,  stu_,tea_,cou_--各类信息管理

这是学生选课系统，包含学生、教师、管理员页面及操作权限。不同身份的页面功能操作将跳转至不同的功能函数。

管理员分为学生stu、教师tea、课程cou的信息管理，先跳转到各对象信息管理的主界面_frame，再跳转各功能，
实现了对MySQL数据库的增add删del查searh改modi操作。

若有需要，应对dbconn.java中连接mysql的语句进行用户名、密码的修改，还有对stu、tea、cou中的四份代码
里面的sql语句进行修改。

附数据库表设计：
学生表：
create table s(
    
sno varchar(20) not null,
    
sname varchar(20) not null,
    
sclass varchar(20),
    
sdept varchar(20),
    
spassword varchar(20),
    
primary key(sno)
    );

教师表：
create table t(
    
tno varchar(20) not null,
    
tname varchar(20) not null,
    
tdept varchar(20),
    
tpassword varchar(20),

primary key(tno)
    );

课程表：
create table c(
    
cno varchar(20) not null,
    
cname varchar(20) not null,
    
cscore varchar(20),
    
ctime varchar(40),
    
cloc varchar(20),
    
primary key(cno)
    );

create table sc(
    
sno varchar(20) not null,
    
cno varchar(20) not null,
    
primary key(sno,cno),
    
foreign key(sno) references s(sno),
    
foreign key(cno) references c(cno)
    );



create table tc(
    
tno varchar(20) not null,
    
cno varchar(20) not null,
    
primary key(tno,cno),
    
foreign key(tno) references t(tno),
    
foreign key(cno) references c(cno)
    );