package dataexp;
import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class tea_search extends JFrame{
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	private JButton btnShow;
	private JButton btnall;
	// 构造方法
	public tea_search(String id,String man_name){
		// 窗体的相关属性的定义
//		super("JTable数据绑定示例");
		this.setTitle("查询教师信息");
		this.setSize(900,500);
		this.setLayout(null);
		this.setLocation(100,50);
		// 创建组件
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(10,70,800,270);
		this.btnShow = new JButton("返回");
		this.btnShow.setBounds(10,40,300,30);
		this.btnall = new JButton("查看所有教工");
		this.btnall.setBounds(10, 10, 300, 30);
		// 给按钮注册监听
		this.btnShow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				tea_search.this.dispose();
                new tea_frame(man_name);
		}
		});
		this.btnall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			    search_all();	
			}
		});
			// 将组件加入到窗体中
			add(this.scpDemo);
			add(this.btnShow);
			add(this.btnall);
			// 显示窗体
			this.setVisible(true);
		
			Connection con = null;
	        PreparedStatement selps= null;
	        ResultSet rs=null;
			try {
				 con = dbconn.getConn();
				 String sel_sql ="select s.sno,sname,sclass,sdept,c.*,t.* from s,sc,c,tc,t where t.tno=tc.tno AND tc.cno=c.cno AND c.cno=sc.cno AND sc.sno=s.sno AND t.tno=?";
				 selps=con.prepareStatement(sel_sql);
				 selps.setString(1, id);
				 // 执行查询
				 rs=selps.executeQuery();
			
				// 计算有多少条记录
				int count = 0;
				while(rs.next()){
				  count++;
				}
				rs = selps.executeQuery();
				// 将查询获得的记录数据，转换成适合生成JTable的数据形式
				Object[][] info = new Object[count][12];
				count = 0;
				while(rs.next()){
					info[count][0] = rs.getString("tno");
					info[count][1] = rs.getString("tname");
				    info[count][2] = rs.getString("tdept");
				    info[count][3] = rs.getString("cno");
					info[count][4] = rs.getString("cname");
				    info[count][5] = rs.getString("cscore");
				    info[count][6] = rs.getString("ctime");
				    info[count][7] = rs.getString("cloc");
				    info[count][8] = rs.getString("sno");
					info[count][9] = rs.getString("sname");
				    info[count][10] = rs.getString("sclass");
				    info[count][11] = rs.getString("sdept");
				count++;
				}
				// 定义表头
				String[] title = {"教工号","教工姓名","教工院系","授课课程号","授课课程名","课程学分","上课时间","上课地点","学生学号","学生姓名","学生班级","学生院系"};
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

			public void search_all() {
				Connection con = null;
		        PreparedStatement selps= null;
		        ResultSet rs=null;
				try {
					 con = dbconn.getConn();
					 String sel_sql ="select s.sno,sname,sclass,sdept,c.*,t.* from s,sc,c,tc,t where t.tno=tc.tno AND tc.cno=c.cno AND c.cno=sc.cno AND sc.sno=s.sno";
					 selps=con.prepareStatement(sel_sql);
					 // 执行查询
					 rs=selps.executeQuery();
				
					// 计算有多少条记录
					int count = 0;
					while(rs.next()){
					  count++;
					}
					rs = selps.executeQuery();
					// 将查询获得的记录数据，转换成适合生成JTable的数据形式
					Object[][] info = new Object[count][12];
					count = 0;
					while(rs.next()){
						info[count][0] = rs.getString("tno");
						info[count][1] = rs.getString("tname");
					    info[count][2] = rs.getString("tdept");
					    info[count][3] = rs.getString("cno");
						info[count][4] = rs.getString("cname");
					    info[count][5] = rs.getString("cscore");
					    info[count][6] = rs.getString("ctime");
					    info[count][7] = rs.getString("cloc");
					    info[count][8] = rs.getString("sno");
						info[count][9] = rs.getString("sname");
					    info[count][10] = rs.getString("sclass");
					    info[count][11] = rs.getString("sdept");
					count++;
					}
					// 定义表头
					String[] title = {"教工号","教工姓名","教工院系","授课课程号","授课课程名","课程学分","上课时间","上课地点","学生学号","学生姓名","学生班级","学生院系"};
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

}


