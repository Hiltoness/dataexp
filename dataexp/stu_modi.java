package dataexp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class stu_modi extends JFrame{
	private JTextField[] tf = new JTextField[]{
			new JTextField(10),new JTextField(10),new JTextField(10),
			new JTextField(10),new JTextField(10),
		};//学号//姓名//班级//院系//密码
	private JButton[] btn = new JButton[] {
			new JButton("确认修改"),new JButton("返回")
	};
	private JLabel[] labell = new JLabel[] {
			new JLabel("请输入学生学号："), new JLabel("姓名"),
			new JLabel("班级"),new JLabel("院系"),new JLabel("密码")
	};
	public void init() {

	    /*setLayout(new FlowLayout());*/
	    setTitle("学生信息修改");
	    setSize(400, 180);
	    setLocationRelativeTo(null);

	}
	public void add1(String man_name) {
		JPanel jp=new JPanel();
		JPanel jp1=new JPanel();
		JPanel jp2=new JPanel();
		//确认修改按钮的操作
		btn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modi_info(tf[0].getText(),tf[1].getText(),tf[2].getText(),tf[3].getText(),tf[4].getText());
				    int x=JOptionPane.showConfirmDialog(stu_modi.this, "修改成功","系统提示", JOptionPane.OK_CANCEL_OPTION,JOptionPane.CANCEL_OPTION);
				    if(x==JOptionPane.OK_OPTION){
				    	fh(man_name);
	
	                }
				}catch(Exception e1) {
	                // TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(stu_modi.this, "修改失败，请重新操作", "系统提示", JOptionPane.OK_CANCEL_OPTION,JOptionPane.CANCEL_OPTION);
		            stu_modi.this.dispose();
		            new stu_modi(man_name);
	                e1.printStackTrace();
				}
			}
		});
		//返回按钮的操作
		btn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fh(man_name);
			}
		});
		
		this.setContentPane(jp);
		jp.setLayout(new BorderLayout());
		jp2.setLayout(new GridLayout(5,2));
		jp1.setLayout(new FlowLayout());
		for(int i=0;i<btn.length;i++) {
			btn[i].setBounds(30, 30+40*i, 100, 30);
			jp1.add(btn[i]);
		}
		for(int i=0;i<labell.length;i++) {
			jp2.add(labell[i]);
			jp2.add(tf[i]);
		}
		jp.add(jp2,BorderLayout.CENTER);
		jp.add(jp1,BorderLayout.SOUTH);
	}
	
	public stu_modi(String man_name) {
		JPanel cp=(JPanel) getContentPane();
	    cp.setLayout(new FlowLayout());

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    init();
	    add1(man_name);
	    setVisible(true);
		}
		
			

	public void modi_info(String id,String name,String sclass,String dept,String password) {
		Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=dbconn.getConn();
            String sql="update s set sname=?,sclass=?,sdept=?,spassword=? where sno=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, sclass);
            ps.setString(3, dept);
            ps.setString(4, password);
            ps.setString(5, id);
            int a = ps.executeUpdate();

        } catch (Exception e) {
        	// TODO Auto-generated catch block
            e.printStackTrace();
	}
	
	}
public void fh(String man_name){
	stu_modi.this.dispose();
	new stu_frame(man_name);
}
}
