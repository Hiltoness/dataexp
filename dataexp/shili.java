package dataexp;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class shili extends JFrame{
// 定义组件
private JScrollPane scpDemo;
private JTableHeader jth;
private JTable tabDemo;
private JButton btnShow1,btnShow2,btnShow3,btnShow4,btn_modi,btn_del;
private JLabel cou_id = new JLabel("请输入课程号：");
private JTextField cou_inid = new JTextField(10);
// 构造方法
public shili(String userName){
	
// 窗体的相关属性的定义
super("教师信息管理");
this.setSize(900,600);
this.setLayout(null);
this.setLocation(250,100);
final JTabbedPane tabbedPane = new JTabbedPane();
tabbedPane.addTab("管理授课信息",  createTextPanel3(userName));
// 创建组件
this.scpDemo = new JScrollPane();
this.scpDemo.setBounds(10,50,630,500);

this.btnShow1 = new JButton("个人信息");
this.btnShow1.setBounds(10,10,150,30);
this.btnShow2 = new JButton("已开课程信息");
this.btnShow2.setBounds(200,10,150,30);
this.btnShow3 = new JButton("学生选课信息");
this.btnShow3.setBounds(400,10,150,30);
this.btnShow4 = new JButton("修改授课信息");
this.btnShow4.setBounds(600,10,150,30);

this.cou_id = new JLabel("请输入课程号：");
this.cou_id.setBounds(100,50,100,30);
this.cou_inid = new JTextField(10);
this.cou_inid.setBounds(300,50,100,30);
this.btn_del = new JButton("删除");
this.btn_del.setBounds(500,80,100,30);
this.btn_modi = new JButton("增加");
this.btn_modi.setBounds(700,80,100,30);

//this.btnShow4 = new JButton("删除授课信息");
//this.btnShow4.setBounds(650,10,200,30);
// 给按钮注册监听
this.btnShow1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
btnShow1_ActionPerformed(ae,userName);
}
});
this.btnShow2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
btnShow2_ActionPerformed(ae,userName);
}
});
this.btnShow3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
btnShow3_ActionPerformed(ae,userName);
}
});
this.btnShow4.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ae){
		shili.this.dispose();
		new shili_model(userName);
		}
	});


// 将组件加入到窗体中
add(this.scpDemo);
add(this.btnShow1);
add(this.btnShow2);
add(this.btnShow3);
add(this.btnShow4);

// 显示窗体
this.setVisible(true);
}

private Component createTextPanel3(String userName) {
	// TODO Auto-generated method stub
	return null;
}

// 点击按钮时的事件处理
public void btnShow1_ActionPerformed(ActionEvent ae,String userName){
// 以下是连接数据源和显示数据的具体处理方法，请注意下
	try{
		// 获得连接
	Connection conn = dbconn.getConn();
	// 建立查询条件
	String sql = "select * from t where tno="+userName; 
	PreparedStatement pstm = conn.prepareStatement(sql);
	// 执行查询
	ResultSet rs = pstm.executeQuery();
	// 计算有多少条记录
	int count = 0;
	while(rs.next()){
		count++;
	}
	rs = pstm.executeQuery();
	// 将查询获得的记录数据，转换成适合生成JTable的数据形式
	Object[][] info = new Object[count][4];
	count = 0;
	while(rs.next()){
		info[count][0] = rs.getString("tno");
		info[count][1] = rs.getString("tname");
		info[count][2] = rs.getString("tdept");
		info[count][3] = rs.getString("tpassword");
		count++;
	}
	// 定义表头
	String[] title = {"工号","姓名","院系","密码"};
	// 创建JTable
	this.tabDemo = new JTable(info,title);
	// 显示表头
	this.jth = this.tabDemo.getTableHeader();
	// 将JTable加入到带滚动条的面板中
	this.scpDemo.getViewport().add(tabDemo); 
	}catch(SQLException sqle){
		JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
	}
}

public void btnShow2_ActionPerformed(ActionEvent ae,String userName){
	// 以下是连接数据源和显示数据的具体处理方法，请注意下
	try{
	// 获得连接
		Connection conn = dbconn.getConn();
		// 建立查询条件
	String sql = "select c.cno,cname,cscore,ctime,cloc from c,tc where c.cno=tc.cno and tc.tno="+userName; 
	
	PreparedStatement pstm = conn.prepareStatement(sql);
	
	// 执行查询
	ResultSet rs = pstm.executeQuery();
	// 计算有多少条记录
	int count = 0;
	while(rs.next()){
	count++;
	}
	rs = pstm.executeQuery();
	// 将查询获得的记录数据，转换成适合生成JTable的数据形式
	Object[][] info = new Object[count][5];
	count = 0;
	while(rs.next()){
	info[count][0] = rs.getString("cno");
	info[count][1] = rs.getString("cname");
	info[count][2] = rs.getString("cscore");
	info[count][3] = rs.getString("ctime");
	info[count][4] = rs.getString("cloc");
	count++;
	}
	// 定义表头
	String[] title = {"课程号","课程名","学分","上课时间","上课地点"};
	// 创建JTable
	this.tabDemo = new JTable(info,title);
	// 显示表头
	this.jth = this.tabDemo.getTableHeader();
	// 将JTable加入到带滚动条的面板中
	this.scpDemo.getViewport().add(tabDemo); 
	}catch(SQLException sqle){
	JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
	}
}

public void btnShow3_ActionPerformed(ActionEvent ae,String userName){
	// 以下是连接数据源和显示数据的具体处理方法，请注意下
	try{
	// 获得连接
		Connection conn = dbconn.getConn();
		// 建立查询条件
	String sql = "select sc.cno,s.sno,sname,sclass,sdept from s,sc,tc where sc.cno=tc.cno and s.sno=sc.sno and tc.tno="+userName; 
	
	PreparedStatement pstm = conn.prepareStatement(sql);
	
	// 执行查询
	ResultSet rs = pstm.executeQuery();
	// 计算有多少条记录
	int count = 0;
	while(rs.next()){																								
	count++;
	}
	rs = pstm.executeQuery();
	// 将查询获得的记录数据，转换成适合生成JTable的数据形式
	Object[][] info = new Object[count][5];
	count = 0;
	while(rs.next()){
	info[count][0] = rs.getString("cno");
	info[count][1] = rs.getString("sno");
	info[count][2] = rs.getString("sname");
	info[count][3] = rs.getString("sclass");
	info[count][4] = rs.getString("sdept");
	count++;
	}
	// 定义表头
	String[] title = {"课程号","学号","姓名","班级","院系"};
	// 创建JTable
	this.tabDemo = new JTable(info,title);
	// 显示表头
	this.jth = this.tabDemo.getTableHeader();
	// 将JTable加入到带滚动条的面板中
	this.scpDemo.getViewport().add(tabDemo); 
	}catch(SQLException sqle){
	JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
	}
	}


public static void main(String userName){
new shili(userName);
}
}
