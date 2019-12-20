package dataexp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


 
class LoginFrame extends JFrame {
 
	private JPanel content;
	private JLabel userLabel;
	private JLabel idLabel;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JTextField userNameField;
	private JButton loginButton;
	private JButton exitButton;
	private JButton changeButton;
	JComboBox id;
	
 
	public LoginFrame() {
		super("学生选课系统");
		setSize(350, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = new JPanel();
		content.setLayout(null);
		add(content);
		
		idLabel = new JLabel("你的身份是:");
		idLabel.setBounds(30, 160, 70, 30);
		content.add(idLabel);
		String []ct= {"学生","老师","管理员"};
		id=new JComboBox(ct);
		id.setBounds(100, 160, 70, 30);
		content.add(id);
 
		userLabel = new JLabel("学号:");
		userLabel.setBounds(30, 30, 40, 20);
		content.add(userLabel);
 
		userNameField = new JTextField("");
		userNameField.setBounds(80, 30, 120, 20);
		content.add(userNameField);
 
		passwordLabel = new JLabel("密码:");
		passwordLabel.setBounds(30, 60, 40, 20);
		content.add(passwordLabel);
 
		passwordField = new JPasswordField();
		passwordField.setBounds(80, 60, 120, 20);
		content.add(passwordField);
		
		
		
		loginButton = new JButton("登录");
		loginButton.setBounds(30, 100, 70, 30);
		loginButton.addActionListener(new buttonListenner());
		content.add(loginButton);
		
		changeButton = new JButton("修改密码");
		changeButton.setBounds(110, 100, 120, 30);
		changeButton.addActionListener(new buttonListenner());
		content.add(changeButton);
 
		exitButton = new JButton("退出");
		exitButton.setBounds(240, 100, 70, 30);
		exitButton.addActionListener(new buttonListenner());
		content.add(exitButton);
 
		/* 设置窗体可见和居中 */
		setVisible(true);
		setLocationRelativeTo(null);
 
	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}
	

 
	class buttonListenner implements ActionListener {
		
		int ch;
		String  sql1,sql2,sql3;
 
		@Override
		public void actionPerformed(ActionEvent e) {
			if (exitButton == e.getSource()) {
				System.exit(0);
			}
			if (changeButton == e.getSource()) {
				new changeFrame();
			}
			
			
			
			if (loginButton == e.getSource()) {
				/* 对数据库的提取验证操作; */
				Connection conection = null;
				try {
					conection = dbconn.getConn();
//					Class.forName("com.mysql.cj.jdbc.Driver");
//					String url = "jdbc:mysql://localhost:3306/sele?user=root";
//					Connection conection = DriverManager.getConnection(url);
					Statement statement = conection.createStatement();
					ch=id.getSelectedIndex(); 
					System.out.printf("下拉框%d下拉",ch);
					if(ch==0)
					{	
					sql1 = "SELECT sno FROM s";// 选择用户表
					}
					if(ch==1)
					{	
					sql1 = "SELECT tno FROM t";// 选择用户表
					}
					if(ch==2)
					{
						sql1="SELECT mno FROM m";
					}
					/* 获取结果集 */
					ResultSet resultSet1 = statement.executeQuery(sql1);
					
					resultSet1.next();
					String userName = resultSet1.getString(1);
					while (!userName.equals(userNameField.getText())) {
						resultSet1.next();
						userName = resultSet1.getString(1);
					}
					if (userName.equals(userNameField.getText())) {
					   if(ch==0)
						{sql2="SELECT spassword FROM s where sno ="+userName;// 选择用户表
						}
					   if(ch==1)
						{sql2="SELECT tpassword FROM t where tno ="+userName;// 选择用户表
						}
					   if(ch==2)
					   {sql2="SELECT mpassword FROM m where mno ="+userName;
					   }
						/* 获取结果集 */
						resultSet1 = statement.executeQuery(sql2);
						
						resultSet1.next();
						String password = resultSet1.getString(1);
						
						
						if (password.equals(new String(passwordField
								.getPassword()))) {
							JOptionPane.showMessageDialog(null, "登录成功!");
							//new MainFrame(userName,statement);
							if(ch==0)
							{
							new StudentFrame(userName,statement);
							}
							if(ch==1)
							{
								new shili(userName);
							}
							if(ch==2)
							{
								new mainframe(userName);
							}
							
							dispose();
							
						} else {
							JOptionPane.showMessageDialog(null, "密码错误!");
						}
					}
					resultSet1.close();
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "数据库链接出错");
				}
			}
			
			
		}
	}
}
