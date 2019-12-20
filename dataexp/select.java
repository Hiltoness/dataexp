package dataexp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class select {
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	// 构造方法
	public select() throws SQLException{
		// 窗体的相关属性的定义
//		super("JTable数据绑定示例");
		this.setSize(900,500);
		this.setLayout(null);
		this.setLocation(100,50);
		// 创建组件
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(10,70,800,270);

		// 显示窗体
		this.setVisible(true);

		Connection con = null;
		PreparedStatement selps= null;
		ResultSet rs=null;

		 con = dbconn.getConn();
		 String sel_sql ="select s.sno,sname,sclass,sdept,c.*,t.tname from s,sc,c,tc,t where s.sno=sc.sno AND sc.cno=c.cno AND c.cno=tc.cno AND tc.tno=t.tno";
		 selps = con.prepareStatement(sel_sql);
		 // 执行查询
		 rs = selps.executeQuery();
		 while(rs.next()) {
			 System.out.println(rs.getString("sno"));
		 System.out.println(rs.getString("sname"));
		 System.out.println(rs.getString("sclass"));
		 System.out.println(rs.getString("sdept"));
		 System.out.println(rs.getString("cno"));
		 System.out.println(rs.getString("cname"));
		 }
//		 
	
		// 计算有多少条记录
		int count = 0;
		rs = selps.executeQuery();
		while(rs.next()){
			
		  count++;
		}
		System.out.println(count);
		rs = selps.executeQuery();
		// 将查询获得的记录数据，转换成适合生成JTable的数据形式
		Object[][] info = new Object[count][10];
		count = 0;
		while(rs.next()){
			info[count][0] = rs.getString("sno");
			info[count][1] = rs.getString("sname");
		    info[count][2] = rs.getString("sclass");
		    info[count][3] = rs.getString("sdept");
		    info[count][4] = rs.getString("cno");
			info[count][5] = rs.getString("cname");
		    info[count][6] = rs.getString("tname");
		    info[count][7] = rs.getString("score");
		    info[count][8] = rs.getString("ctime");
		    info[count][9] = rs.getString("cloc");
		count++;
		}
		// 定义表头
		String[] title = {"学生学号","学生姓名","学生班级","学生院系","课程号","课程名","任课老师","学分","上课时间","上课地点"};
		// 创建JTable
		this.tabDemo = new JTable(info,title);
		// 显示表头
		this.jth = this.tabDemo.getTableHeader();
		// 将JTable加入到带滚动条的面板中
		this.scpDemo.getViewport().add(tabDemo); 

	}
	private void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
	private void setLocation(int i, int j) {
		// TODO Auto-generated method stub
		
	}
	private void setLayout(Object object) {
		// TODO Auto-generated method stub
		
	}
	private void setSize(int i, int j) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws SQLException {
		new select();
	}

}
