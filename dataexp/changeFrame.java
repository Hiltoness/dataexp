package dataexp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



class changeFrame extends JFrame{
	
	private JPanel content;
	private JLabel userLabel;
	private JTextField userNameField;
	private JLabel passwordLabel1;
	private JPasswordField passwordField1;
	private JLabel passwordLabel2;
	private JPasswordField passwordField2;

	
	private JButton loginButton;
	private JButton exitButton;
	JComboBox id;
	private JLabel idLabel;
 
	public changeFrame() {
		super("");
		setSize(350, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = new JPanel();
		content.setLayout(null);
		add(content);
 
		userLabel = new JLabel("学号:");
		userLabel.setBounds(30, 30, 40, 20);
		content.add(userLabel);
 
		userNameField = new JTextField("");
		userNameField.setBounds(80, 30, 120, 20);
		content.add(userNameField);
 
		passwordLabel1 = new JLabel("原密码:");
		passwordLabel1.setBounds(30, 60, 70, 20);
		content.add(passwordLabel1);
 
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(80, 60, 120, 20);
		content.add(passwordField1);
		
		passwordLabel2 = new JLabel("新密码:");
		passwordLabel2.setBounds(30, 90, 70, 20);
		content.add(passwordLabel2);
 
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(80, 90, 120, 20);
		content.add(passwordField2);
		
		idLabel = new JLabel("你的身份是:");
		idLabel.setBounds(30, 200, 70, 30);
		content.add(idLabel);
		String []ct= {"学生","老师","管理员"};
		id=new JComboBox(ct);
		id.setBounds(100, 200, 70, 30);
		content.add(id);
 
		loginButton = new JButton("修改");
		loginButton.setBounds(30, 150, 70, 30);
		loginButton.addActionListener(new buttonListenner());
		content.add(loginButton);
 
		exitButton = new JButton("退出");
		exitButton.setBounds(240, 150, 70, 30);
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
		String sql1, sql2,sql3;
		@Override
		public void actionPerformed(ActionEvent e) {
			if (exitButton == e.getSource()) {
				System.exit(0);
			}
			
			
			if (loginButton == e.getSource()) {
				/* 对数据库的提取验证操作; */
				try {
					
					Connection conection = dbconn.getConn();
					Statement statement = conection.createStatement();
					
					ch=id.getSelectedIndex(); 
					System.out.printf("下拉框%d下拉2222",ch);
					if(ch==0)
					{sql1 = "SELECT sno FROM s";// 选择用户表}
					
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
						{sql2 = "SELECT spassword FROM s where sno="+userName;// 选择用户表
						
						}
						/* 获取结果集 */
						resultSet1 = statement.executeQuery(sql2);
						
						resultSet1.next();
						String password = resultSet1.getString(1);
						
						
						if (password.equals(new String(passwordField1
								.getPassword()))) {
							
							
							String password1=new String(passwordField2.getPassword());
							
							
							if(ch==0)
								{sql3="update s set spassword='"+password1+"'where sno="+userName;
								}
								
								statement.executeUpdate(sql3);
								JOptionPane.showMessageDialog(null, "修改成功！");
								dispose();
								
							
					
							}
						else {
							JOptionPane.showMessageDialog(null, "原密码错误!");
						}
						
					}
					resultSet1.close();
					conection.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "数据库链接出错");
				}
				
				
			}
		}
	}

	
	
	
	
		
	
	
}
