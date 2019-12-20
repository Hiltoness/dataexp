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



public class cou_add extends JFrame{
	private JButton[] btn = new JButton[] {
			new JButton("确认添加"),new JButton("返回")
	};
	private JLabel[] labell = new JLabel[] {
			new JLabel("课程号"),new JLabel("课程名"),
			new JLabel("学分"),new JLabel("上课时间"),new JLabel("上课地点")
	};
	private JTextField[] tf = new JTextField[]{
		new JTextField(10),new JTextField(10),new JTextField(10),
		new JTextField(10),new JTextField(10),
	};
	
	public void init() {

	    setTitle("课程信息录入");
	    setSize(400, 180);
	    setLocationRelativeTo(null);

	}
	public void add1(String man_name) {
		JPanel jp=new JPanel();
		JPanel jp1=new JPanel();
		JPanel jp2=new JPanel();
		JPanel jp3=new JPanel();
		btn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					add_info(tf[0].getText(),tf[1].getText(),tf[2].getText(),tf[3].getText(),tf[4].getText());
				    int x=JOptionPane.showConfirmDialog(cou_add.this, "添加成功","系统提示", JOptionPane.OK_CANCEL_OPTION,JOptionPane.CANCEL_OPTION);
				    if(x==JOptionPane.OK_OPTION){
				    	cou_add.this.dispose();
	                    new cou_add( man_name);

	                }
				}catch(Exception e1) {
	                // TODO Auto-generated catch block
	                e1.printStackTrace();
				}
			}
		});
		
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
//			labell[i].setBounds(30, 30+40*i, 100, 30);
			
		}
		jp.add(jp2,BorderLayout.CENTER);
		jp.add(jp1,BorderLayout.SOUTH);
	
	}
	
	public cou_add(String man_name) {
		JPanel cp=(JPanel) getContentPane();
	    cp.setLayout(new FlowLayout());

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    init();
	    add1(man_name);
	    setVisible(true);
		
	}
			

	public void add_info(String id,String name,String cscore,String ctime,String cloc) {
		Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=dbconn.getConn();
            String sql="insert into c (cno,cname,cscore,ctime,cloc) values(?,?,?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, cscore);
            ps.setString(4, ctime);
            ps.setString(5, cloc);
            int a = ps.executeUpdate();
        } catch (Exception e) {
        	// TODO Auto-generated catch block
            e.printStackTrace();
	}
	
	}
public void fh(String man_name){
	cou_add.this.dispose();
	new cou_frame(man_name);
}
}
