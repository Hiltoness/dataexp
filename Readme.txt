run LoginFrame.java
����LoginFrame.java�ļ�

LoginFrame--��¼��Change--�޸�����
StudentFrame--ѧ����¼ҳ��
Shili--��ʦ��¼ҳ��, Shi_model--��ʦ�����ڿ���Ϣ
mainframe--����Ա��¼ҳ��,  stu_,tea_,cou_--������Ϣ����

����ѧ��ѡ��ϵͳ������ѧ������ʦ������Աҳ�漰����Ȩ�ޡ���ͬ��ݵ�ҳ�湦�ܲ�������ת����ͬ�Ĺ��ܺ�����

����Ա��Ϊѧ��stu����ʦtea���γ�cou����Ϣ��������ת����������Ϣ�����������_frame������ת�����ܣ�
ʵ���˶�MySQL���ݿ����addɾdel��searh��modi������

������Ҫ��Ӧ��dbconn.java������mysql���������û�����������޸ģ����ж�stu��tea��cou�е��ķݴ���
�����sql�������޸ġ�

�����ݿ����ƣ�
ѧ����
create table s(
    
sno varchar(20) not null,
    
sname varchar(20) not null,
    
sclass varchar(20),
    
sdept varchar(20),
    
spassword varchar(20),
    
primary key(sno)
    );

��ʦ��
create table t(
    
tno varchar(20) not null,
    
tname varchar(20) not null,
    
tdept varchar(20),
    
tpassword varchar(20),

primary key(tno)
    );

�γ̱�
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