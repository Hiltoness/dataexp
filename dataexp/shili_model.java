package dataexp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class shili_model extends JFrame{
	private JButton[] btn = new JButton[] {
			new JButton("添加"),new JButton("删除"),new JButton("返回")
	};
	private JLabel tfn1 = new JLabel("请输入课程号：");
	private JTextField tfid = new JTextField(10);
	
	public void init() {

	    /*setLayout(new FlowLayout());*/
	    setTitle("管理授课信息");
	    setSize(400, 180);
	    setLocationRelativeTo(null);

	}
	
	public void add1(String man_name) {
		JPanel jp=new JPanel();
		JPanel jp1=new JPanel();
		JPanel jp2=new JPanel();
		btn[0].addActionListener(new ActionListener() {
			//添加
			public void actionPerformed(ActionEvent e) {
				try {
					add_info(man_name,tfid.getText());
				    int x=JOptionPane.showConfirmDialog(shili_model.this, "添加成功","系统提示", JOptionPane.OK_CANCEL_OPTION,JOptionPane.CANCEL_OPTION);
				    if(x==JOptionPane.OK_OPTION){
				    	new shili_model(man_name);

	                }
				}catch(Exception e1) {
	                // TODO Auto-generated catch block
	                e1.printStackTrace();
					JOptionPane.showConfirmDialog(shili_model.this, "添加失败，请咨询管理员或尝试重新操作", "系统提示", JOptionPane.OK_CANCEL_OPTION,JOptionPane.CANCEL_OPTION);
		            shili_model.this.dispose();
		            new shili_model(man_name);
				}
			}
		});
		
		btn[1].addActionListener(new ActionListener() {
			//删除
			public void actionPerformed(ActionEvent e) {
				try {
					del_info(man_name,tfid.getText());
				    int x=JOptionPane.showConfirmDialog(shili_model.this, "删除成功","系统提示", JOptionPane.OK_CANCEL_OPTION,JOptionPane.CANCEL_OPTION);
				    if(x==JOptionPane.OK_OPTION){
				    	new shili_model(man_name);

	                }
				}catch(Exception e1) {
	                // TODO Auto-generated catch block
	                e1.printStackTrace();
					JOptionPane.showConfirmDialog(shili_model.this, "删除失败，请咨询管理员或尝试重新操作", "系统提示", JOptionPane.OK_CANCEL_OPTION,JOptionPane.CANCEL_OPTION);
		            shili_model.this.dispose();
		            new shili_model(man_name);
				}
			}
			
		});
		btn[2].addActionListener(new ActionListener() {
			//返回
			public void actionPerformed(ActionEvent e) {
				fh(man_name);
			}
			
		});
		
		this.setContentPane(jp);
		jp.setLayout(new BorderLayout());
		jp1.setLayout(new FlowLayout());
		jp2.setLayout(new GridLayout(3,2));
		for(int i=0;i<btn.length;i++) {
			btn[i].setBounds(30, 30+40*i, 100, 30);
			jp2.add(btn[i]);
		}
		jp1.add(tfn1);jp1.add(tfid);
		jp.add(jp1,BorderLayout.CENTER);
		jp.add(jp2,BorderLayout.SOUTH);
	}
	
	public shili_model(String man_name) {
		JPanel cp=(JPanel) getContentPane();
	    cp.setLayout(new FlowLayout());

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    init();
	    add1(man_name);
	    setVisible(true);
	}
	
	public void add_info(String t_id, String c_id) {
		//在数据表中添加授课信息
		Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=dbconn.getConn();
            String sql="insert into tc(tno,cno) values(?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, t_id);
            ps.setString(2, c_id);
            int a = ps.executeUpdate();
           
        } catch (Exception e) {
        	// TODO Auto-generated catch block
            e.printStackTrace();
	}
	
	}
	public void del_info(String t_id, String c_id) {
		//在数据表中删除授课信息
		Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=dbconn.getConn();
            String sql="delete from tc where cno=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, c_id);
            int a = ps.executeUpdate();
           
        } catch (Exception e) {
        	// TODO Auto-generated catch block
            e.printStackTrace();
	}
	
	}
	
	public void fh(String man_name){
		//返回
		shili_model.this.dispose();
		new shili(man_name);
	}

}
