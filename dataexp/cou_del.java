package dataexp;

import java.awt.FlowLayout;
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



public class cou_del extends JFrame{
	private JPanel jp3=new JPanel(); 
	private JButton[] btn = new JButton[] {
			new JButton("确认删除"),new JButton("返回")
	};
	private JLabel tfn1 = new JLabel("请输入课程号：");
	private JTextField tfid = new JTextField(10);
	
	public cou_del(String man_name) {
		jp3.setLayout(null);
		for(int i=0;i<btn.length;i++) {
			btn[i].setBounds(30, 30+40*i, 100, 30);
			jp3.add(btn[i]);
			btn[i].addActionListener((ActionListener) jp3);
		}
		jp3.add(tfn1);
		jp3.add(tfid);
		jp3.setLayout(getLayout());
		this.add(jp3);
		this.setVisible(true);
	
	btn[0].addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				del_info(tfid.getText());
			    int x=JOptionPane.showConfirmDialog(cou_del.this, "删除成功","系统提示", JOptionPane.OK_CANCEL_OPTION,JOptionPane.CANCEL_OPTION);
			    if(x==JOptionPane.OK_OPTION){
			    	fh(man_name);

                }
			}catch(Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
				JOptionPane.showConfirmDialog(cou_del.this, "删除失败，请重新操作", "系统提示", JOptionPane.OK_CANCEL_OPTION,JOptionPane.CANCEL_OPTION);
	            cou_del.this.dispose();
	            new cou_del(man_name);
		}
	}
	});
	btn[1].addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			fh(man_name);
		}
	});
}

	public void init() {
		setLayout(new FlowLayout());
	    setTitle("课程信息删除");
	    setSize(500, 300);
	    setLocationRelativeTo(null);
	}
	public void del_info(String id) {
		Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=dbconn.getConn();
            String sql="delete from c where cno=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, id);
            int a = ps.executeUpdate();
            if(a>0) {
            	String sql2="delete from sc where cno=?";
            	ps=con.prepareStatement(sql2);
                ps.setString(1, id);
                int b=ps.executeUpdate();
            }
            else {
            	
            }
        } catch (Exception e) {
        	// TODO Auto-generated catch block
            e.printStackTrace();
	}
	
	}
public void fh(String man_name){
	cou_del.this.dispose();
	new cou_frame(man_name);
}
}
