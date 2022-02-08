package atm;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Register { //ע����
	User user=null;
	JPanel pn=new JPanel();
	JLabel lb=new JLabel("�û�ע��"),
		   lb_name=new JLabel("������"),
		   lb_sex=new JLabel("�Ա�"),
		   lb_idCard=new JLabel("���֤��"),
		   lb_phone=new JLabel("�ֻ���"),
		   lb_password=new JLabel("(����6λ��������)"),
		   lb_password1=new JLabel("���룺"),
		   lb_password2=new JLabel("ȷ�����룺");
	JTextField tf_name=new JTextField(),tf_idCard=new JTextField(),tf_phone=new JTextField();
	JPasswordField pf_password1=new JPasswordField(),pf_password2=new JPasswordField();
	JButton bt1=new JButton("����",new ImageIcon("image/����.png")),bt2=new JButton("ע  ��");
	String[] sexx= {"------��ѡ��------","        ��","        Ů"};
	JComboBox<String> cb_sex=new JComboBox<String>(sexx);
	String password1,password2,name,sex,idCard,phone;
	JLabel lb_tips=new JLabel();
	public JPanel registerPage() { //ע�����
		HomePage.mainJFrame.setSize(550,450);
		HomePage.mainJFrame.setLocationRelativeTo(null);
		pn.setSize(550,450);
		pn.setLayout(null);
		lb.setFont(new Font("����",0,35));
		lb.setBounds(200,5,150,70);
		lb_name.setFont(new Font("����",0,20));
		lb_name.setBounds(140,80,70,30);
		lb_sex.setFont(new Font("����",0,20));
		lb_sex.setBounds(140,120,70,30);
		lb_idCard.setFont(new Font("����",0,20));
		lb_idCard.setBounds(120,160,80,30);
		lb_phone.setFont(new Font("����",0,20));
		lb_phone.setBounds(140,200,70,30);
		lb_password.setFont(new Font("����",0,16));
		lb_password.setBounds(400,240,150,30);
		lb_password1.setFont(new Font("����",0,20));
		lb_password1.setBounds(140,240,70,30);
		lb_password2.setFont(new Font("����",0,20));
		lb_password2.setBounds(100,280,100,30);
		tf_name.setFont(new Font("����",0,20));
		tf_name.setBounds(195,80,205,30);
		cb_sex.setFont(new Font("����",0,20));
		cb_sex.setBounds(195,120,205,30);
		tf_idCard.setFont(new Font("����",0,20));
		tf_idCard.setBounds(195,160,205,30);
		tf_phone.setFont(new Font("����",0,20));
		tf_phone.setBounds(195,200,205,30);
		tf_phone.setDocument(new NumLimit());
		pf_password1.setFont(new Font(null,0,20));
		pf_password1.setBounds(195,240,205,30);
		pf_password2.setFont(new Font(null,0,20));
		pf_password2.setBounds(195,280,205,30);
		bt1.setFont(new Font("����",0,17));
		bt1.setBounds(1,10,92,25);
		bt1.setContentAreaFilled(false);
		bt1.setBorderPainted(false);
		bt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt2.setFont(new Font("����",0,25));
		bt2.setBounds(200,330,150,60);
		bt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn.add(lb);
		pn.add(lb_name);
		pn.add(lb_sex);
		pn.add(lb_idCard);
		pn.add(lb_phone);
		pn.add(lb_password);
		pn.add(lb_password1);
		pn.add(lb_password2);
		pn.add(tf_name);
		pn.add(cb_sex);
		pn.add(tf_idCard);
		pn.add(tf_phone);
		pn.add(pf_password1);
		pn.add(pf_password2);
		pn.add(bt1);
		pn.add(bt2);
		bt1.addMouseListener(new MouseListener() { //���ص��û���¼����
			public void mouseEntered(MouseEvent arg0) {
				bt1.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt1.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				HomePage.con.remove(pn);
				HomePage.mainJFrame.repaint();
				HomePage.loginPage();
				HomePage.mainJFrame.validate();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt2.addActionListener(new ActionListener() { //ע���˺�
			public void actionPerformed(ActionEvent e) {
				getInfo();
			}
		});
		return pn;
	}
	
	public void getInfo() { //��ȡ�û���Ϣ
		boolean y1,y2=false,y3,y4,y5,y6=false;
		//y1��ʾ�Ƿ�ȫ����д��y2��ʾ�����ʽ�Ƿ���ȷ��y3��ʾ���������Ƿ���ͬ��y4��ʾ�����Ƿ�Ϊ���֣�y5��ʾ�����Ƿ�Ϊ6λ��y6��ʾ��λ�����Ƿ���ȫ��ͬ
		y1=(!tf_name.getText().trim().equals("")) //�ж���Ϣ�Ƿ�ȫ����д
				&&(!tf_idCard.getText().trim().equals(""))
				&&(!tf_phone.getText().trim().equals(""))
				&&(!String.valueOf(pf_password1.getPassword()).equals(""))
				&&(!String.valueOf(pf_password2.getPassword()).equals(""))
				&&(cb_sex.getSelectedIndex()!=0);
		if(!y1) { //��δ������д��Ϣ����������ʾ
			lb_tips.setText("��������Ϣ��");
			registerTips();
		}
		else { //��������д��Ϣ�������������ʽ�ж�
			password1=String.valueOf(pf_password1.getPassword());
			password2=String.valueOf(pf_password2.getPassword());
			if(password1.equals(password2)) { //�ж����������Ƿ���ͬ
				y3=true;
				if(password1.length()==6) { //�ж������Ƿ�Ϊ6λ
					y5=true;
					y4=true;
					int[] chr=new int[6]; //���ڴ����λ�����ֵ
					for(int i=0;i<password1.length();i++) { //�ж������Ƿ�Ϊ����
						chr[i]=password1.charAt(i);
						if(chr[i]<48||chr[i]>57) { //�����벻Ϊ���֣���������ʾ
							y4=false;
							lb_tips.setText("������6λ�������룡");
							registerTips();
							break;
						}
						if(i==5) { //������Ϊ6λ�������룬�򽫽���6λ�����Ƿ���ȫ��ͬ���ж�
							int x=1;
							for(x=1;x<6;x++) {
								if(chr[x-1]==chr[x])
									continue;
								else
									break;
							}
							if(x==6) {  //��6λ������ȫ��ͬ����������ʾ
								y6=false;
								lb_tips.setText("��������6λ��ȫ��ͬ�����룡");
								registerTips();
							}
							else
								y6=true;
						}
					}
					y2=y3&&y4&&y5&&y6; //������Ϊ����ȫ��ͬ��6λ�������룬�������ʽ��ȷy2Ϊtrue������y2Ϊfalse
				}
				else { //�����벻Ϊ6λ����������ʾ
					y5=false;
					lb_tips.setText("������6λ�������룡");
					registerTips();
				}
			}
			else { //���������벻��ͬ����������ʾ
				y3=false;
				lb_tips.setText("�������벻ͬ��");
				registerTips();
			}
			if(y2) { //����ע�������������ע��
				lb_tips.setText("ע��ɹ���");
				registerTips();
				password1=String.valueOf(pf_password1.getPassword());
				name=tf_name.getText();
				if((String)cb_sex.getSelectedItem()=="        ��")
					sex="��";
				else if((String)cb_sex.getSelectedItem()=="        Ů")
					sex="Ů";
				idCard=tf_idCard.getText();
				phone=tf_phone.getText();
				register();
				completePage();
			}
		}
	}
	
	public void registerTips() { //ע��ʱ��������ʾ��Ϣ����
		JDialog tips=new JDialog(HomePage.mainJFrame,"  ��ʾ",true);
		JPanel pn_tips=new JPanel();
		JButton bt_tips=new JButton("ȷ ��");
		tips.setSize(450,200);
		tips.setLocationRelativeTo(null);
		tips.setResizable(false);
		tips.setLayout(null);
		pn_tips.setBounds(0,30,450,70);
		lb_tips.setFont(new Font("����",0,25));
		bt_tips.setFont(new Font("����",0,20));
		bt_tips.setBounds(175,100,100,50);
		bt_tips.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_tips.add(lb_tips);
		tips.add(pn_tips);
		tips.add(bt_tips);
		bt_tips.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tips.dispose();
			}
		});
		tips.setVisible(true);
	}
	
	public void register() { //ע���˺ţ������˺���Ϣ���ļ���
		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("�û���Ϣ.txt"));
			ArrayList<User> users=(ArrayList<User>)ois.readObject();
			ois.close();
			user=new User(password1,name,sex,idCard,phone);
			users.add(user);
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("�û���Ϣ.txt"));
			oos.writeObject(users);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void completePage() { //ע����ɽ���
		JButton bt=new JButton("���ص�¼",new ImageIcon("image/����.png"));
		JLabel lb0=new JLabel("ע�����"),lb1=new JLabel("���μ����Ŀ��ź�����"),lb2=new JLabel("���ţ�"+user.account),lb3=new JLabel("���룺"+user.password);
		pn.removeAll();
		HomePage.mainJFrame.repaint();
		HomePage.mainJFrame.setSize(550,400);
		HomePage.mainJFrame.setLocationRelativeTo(null);
		pn.setSize(550,400);
		pn.setLayout(null);
		lb0.setFont(new Font("����",0,35));
		lb0.setBounds(200,30,150,100);
		bt.setFont(new Font("����",0,17));
		bt.setBounds(1,10,135,25);
		bt.setContentAreaFilled(false);
		bt.setBorderPainted(false);
		bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lb1.setFont(new Font("����",0,20));
		lb1.setForeground(Color.red);
		lb1.setBounds(170,70,200,100);
		lb2.setFont(new Font("����",0,25));
		lb2.setBounds(125,140,300,100);
		lb3.setFont(new Font("����",0,25));
		lb3.setBounds(125,190,300,100);
		pn.add(lb0);
		pn.add(lb1);
		pn.add(lb2);
		pn.add(lb3);
		pn.add(bt);
		bt.addMouseListener(new MouseListener() { //���ص��û���¼����
			public void mouseEntered(MouseEvent arg0) {
				bt.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				HomePage.con.remove(pn);
				HomePage.mainJFrame.repaint();
				HomePage.loginPage();
				HomePage.mainJFrame.validate();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		HomePage.mainJFrame.validate();
	}
}