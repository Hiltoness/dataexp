package dataexp;

import javax.swing.*;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dataexp.LoginFrame.buttonListenner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ttttt extends JFrame{

	String user;
	Statement state;
	Container c=getContentPane();
	 JScrollPane scrollPane = new JScrollPane();
	public JButton add1Button;
	public JButton dButton;
	public JButton upButton;
	JFrame jf = new JFrame("欢迎登录教师界面");
	
	public ttttt(String userName)
	{
		
		
		user=userName;
		
        jf.setSize(900, 900);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        
    
		
        // 创建选项卡面板
        final JTabbedPane tabbedPane = new JTabbedPane();


        // 创建第 1 个选项卡（选项卡只包含 标题）
        tabbedPane.addTab("个人信息", createTextPanel1(userName,statement));

        // 创建第 2 个选项卡
    
               
        tabbedPane.addTab("已开课程信息",createTextPanel2 (userName,  statement));

        // 创建第 3 个选项卡
        tabbedPane.addTab("学生选课信息",  createTextPanel3(userName,  statement));
        tabbedPane.addTab("修改授课信息",  createTextPanel3(userName,  statement));


        // 添加选项卡选中状态改变的监听器
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("当前选中的选项卡: " + tabbedPane.getSelectedIndex());
            }
        });
        
        
        

        // 设置默认选中的选项卡
        tabbedPane.setSelectedIndex(0);
        
        jf.setContentPane(tabbedPane);
        
        jf.setVisible(true);
		
        
       
        
	}
	
	
	
	
	
	
	
	
	
	private  JComponent createTextPanel1(String userName, Statement statement) {
		  // 创建面板
	        JPanel panel = new JPanel();
	        panel.setLayout(null);
	        Font font=new Font("仿宋",Font.PLAIN,20);
	        
	        
	        
			
	        // 创建标签
	        JLabel usernamelabel = new JLabel("姓名：");
	        usernamelabel.setFont(font);
	        usernamelabel.setBounds(10, 10, 60, 60);
	        // 添加标签到面板
	        panel.add(usernamelabel);
	        
	        JLabel numlabel = new JLabel("工号：");
	        numlabel.setFont(font);
	        numlabel.setBounds(10, 80, 60, 60);
	        panel.add(numlabel);
	        
	        JLabel classlabel = new JLabel("院系：");
	        classlabel.setBounds(10, 150, 60, 60);
	        classlabel.setFont(font);
	        panel.add(classlabel);

	        
		try {
			
			
			
			String sql1 = "SELECT * FROM t where tno="+userName;// 选择用户表
			/* 获取结果集 */
			ResultSet resultSet1 = statement.executeQuery(sql1);
			resultSet1.next();
			String sno = resultSet1.getString(1);
			ResultSet resultSet2 = statement.executeQuery(sql1);
			resultSet2.next();
			String sname = resultSet2.getString(2);
			ResultSet resultSet4 = statement.executeQuery(sql1);
			resultSet4.next();
			String yuanxi = resultSet4.getString(3);

			
	        JLabel usernamelabel1 = new JLabel(sname);
	        usernamelabel1.setFont(font);
	        usernamelabel1.setBounds(75, 10, 60, 60);
	        panel.add(usernamelabel1);
	        
	        JLabel numlabel1 = new JLabel(sno);
	        numlabel1.setFont(font);
	        numlabel1.setBounds(75, 80, 60, 60);
	        panel.add(numlabel1);

	        
	        JLabel deptlabel1 = new JLabel(yuanxi);
	        deptlabel1.setBounds(75, 220, 60, 60);
	        deptlabel1.setFont(font);
	        panel.add(deptlabel1);
	        
	
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "个人信息错误!");
		}
		
  return panel;

		
    
	}
	
	
	
	
	
	
	
	private  JComponent createTextPanel2 (String userName, Statement statement) {
		// 创建面板
       
       JLayeredPane laypane = new JLayeredPane();
       
	   
	try {
		Object[][] columsStrings1;
		Object[][] columsStrings;
		Object[] cno;
		
		int count=0;
		
		int nColus=5;
   		
   		String sql1 = "SELECT cno FROM tc where tno="+userName;
   		/* 获取结果集 */
   		ResultSet resultSet1 = statement.executeQuery(sql1);
   		while(resultSet1.next())
   		{  		
   			count++;
   		}
   			
   		cno = new Object[count];
   		
   		sql1 = "SELECT cno FROM tc where tno="+userName;
   		/* 获取结果集 */
   		resultSet1 = statement.executeQuery(sql1);
   		int b=0;
   		while(resultSet1.next())
   		{  		
   			cno[b]=new String(resultSet1.getString(1));
   			b++;
   		}
   		
   		
   		columsStrings = new Object[count][nColus];
   		
   		for (int i = 0; i<count; i++) {
   			sql1 = "SELECT * FROM c where cno="+cno[i];
   	   		resultSet1 = statement.executeQuery(sql1);
   			resultSet1.next();
   			
   			for (int j = 1; j <= nColus; j++) {
   				
   				
   			columsStrings[i][j - 1] = new String(resultSet1.getString(j));
   				
   			}
   		}
		
		
		
		String[] columnNames = { "课程号", "课程名称", "学分", "上课时间","上课地点"};
    //以二维数组和一维数组来创建一个JTable对象
	    JTable table = new JTable(columsStrings,columnNames);
    //将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来
	    
	    

        // 创建显示表格的滚动面板
	    JScrollPane jp = new JScrollPane(table);
	    jp.setBounds(70, 150, 650, 450);

	    laypane.add(jp);
	   

	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "获取课程的数据错误2!");
		e.printStackTrace();
	}

	
  
       return laypane;
		

    }
	


	
	
	
	
	private  JComponent createTextPanel3(String userName, Statement statement) {
        
	       
		
		JLayeredPane laypane = new JLayeredPane();
        
	   	try {
	   		
	   		String[][] columsStrings; 		
	   		int count=0;

	   		int nColus=6;
	   		
	   		String sql1 = "SELECT * FROM c";
	   		/* 获取结果集 */
	   		ResultSet resultSet1 = statement.executeQuery(sql1);
	   		while(resultSet1.next())
	   		{
	   			count++;
	   		}
	   		columsStrings = new String[count][nColus];
	   		
	   		int count1=0;
	   		sql1 = "SELECT cno FROM tc where tno="+userName;
	   		/* 获取结果集 */
	   		resultSet1 = statement.executeQuery(sql1);
	   		while(resultSet1.next())
	   		{  		
	   			count1++;
	   		}
	   			
	   		
	   		
	   		String[] cno;
	   		cno = new String[count1];
	   		
	   		sql1 = "SELECT cno FROM tc where tno="+userName;
	   		/* 获取结果集 */
	   		resultSet1 = statement.executeQuery(sql1);
	   		int b=0;
	   		while(resultSet1.next())
	   		{  		
	   			cno[b]=new String(resultSet1.getString(1));
	   			b++;
	   			
	   		}
	   		
	 
	
	   		sql1 = "SELECT * FROM c";
	   		resultSet1 = statement.executeQuery(sql1);
	   		
	
	   		for (int i = 0; i<count; i++) {
	   		      
	   			resultSet1.next();
	   			
	   			for (int j = 1; j < nColus; j++) {
	   				
	   				
	   			columsStrings[i][j - 1] = new String(resultSet1.getString(j));


	   		}
	   			columsStrings[i][5] ="未选";		
	   			}
	   	
	   		String n1;
	   		String n2;
	   	
	   		for(int i=0;i<count;i++)
	   		{
	   			
	   			for(int j=0;j<count1;j++)
	   			{
	   				n1=columsStrings[i][0];
	   				n2=cno[j];
	   				if(n1.equals(n2))
	   				{
	   					
	   					columsStrings[i][5]="已选";
	   			}
	   		}
	   		
	   		}
	   		resultSet1.close();

	   	 JLabel add1Label;
			JLabel dLabel;
			 JPasswordField dField;
			 JTextField add1Field;

			add1Label = new JLabel("添加课程号:");
			add1Label.setBounds(30, 30, 120, 20);
		

			add1Field = new JTextField("");
			add1Field.setBounds(200, 30, 120, 20);
			

		dLabel = new JLabel("取消选择课程号:");
		dLabel.setBounds(30, 60, 120, 20);

		dField = new JPasswordField("");
		dField.setBounds(200, 60, 120, 20);
		
		
		add1Button = new JButton("添加");
		add1Button.setBounds(30, 100, 70, 30);
		

		dButton = new JButton("删除");
		dButton.setBounds(110, 100, 70, 30);
		
		dButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String sql2 = "delete FROM sc where cno='"+dField.getText()+"'and sno="+userName;
				try {
					statement.executeUpdate(sql2);
					JOptionPane.showMessageDialog(null, "删除成功!");
					
					new test2(userName,statement);
					dispose();
					jf.dispose();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		add1Button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String sql3 = "insert into sc(sno,cno)  values('"+userName+"','"+add1Field.getText()+"')";
				try {
					 statement.executeUpdate(sql3);
					 JOptionPane.showMessageDialog(null, "添加成功!");

						new test2(userName,statement);
						dispose();
						
						jf.dispose();
						
					 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		);
		

		
		
		laypane.add(add1Label);      
		 laypane.add(add1Button);
		laypane.add(add1Field);
		
		laypane.add(dLabel);
		laypane.add(dButton);
		laypane.add(dField);
 		
	   		
	   		String[] columnNames = { "课程号", "课程名称", "学分", "上课时间","上课地点","选择"};
	       //以二维数组和一维数组来创建一个JTable对象
	   	    JTable table = new JTable(columsStrings,columnNames);
	       //将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来
	   	
	           // 创建显示表格的滚动面板
	   	    JScrollPane jp = new JScrollPane(table);
	   	    jp.setBounds(70, 150, 650, 450);
	   	    
	     
	   	laypane.add(jp);
	          
	           


	   	} catch (Exception e) {
	   		JOptionPane.showMessageDialog(null, "获取课程的数据错误2!");
	   		e.printStackTrace();
	   	}
	         
	           
	           return laypane;
	  

        }

	
}
