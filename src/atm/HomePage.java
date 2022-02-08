package atm;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class HomePage { //��ҳ��
	static JFrame mainJFrame=new JFrame("ATM��Ա������ϵͳ");
	static Container con=mainJFrame.getContentPane();
	static boolean flag; //�Ƿ�Ϊ����Ա
	static int appearance=0; //��ǰ���
	static JLabel lb_appearance=new JLabel("<html>��ǰ��ۣ�<br>Ĭ��</html>"); //��ǰ���
	public static void main(String[] args) {
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainJFrame.setResizable(false);
		con.setLayout(null);
		welcomePage();
	}
	
	public static void welcomePage() { //��ӭ����
		mainJFrame.setSize(550,400);
		mainJFrame.setLocationRelativeTo(null);
		JPanel pn=new JPanel();
		pn.setSize(550,400);
		pn.setLayout(null);
		JLabel lb1=new JLabel("��ӭʹ��"),lb2=new JLabel("ATM��Ա������ϵͳ");
		JButton bt1=new JButton("�û�ģʽ"),bt2=new JButton("ϵͳ����ģʽ"),bt_changeAppearance=new JButton("�������",new ImageIcon("image/�������.png"));
		lb1.setFont(new Font("����",0,35));
		lb1.setBounds(200,30,150,100);
		lb2.setFont(new Font("����",0,35));
		lb2.setBounds(125,100,300,80);
		bt1.setFont(new Font("����",0,22));
		bt1.setBounds(70,210,170,70);
		bt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lb_appearance.setFont(new Font("����",0,17));
		lb_appearance.setBounds(5,5,150,40);
		bt2.setFont(new Font("����",0,22));
		bt2.setBounds(310,210,170,70);
		bt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_changeAppearance.setFont(new Font("����",0,17));
		bt_changeAppearance.setBounds(420,10,130,25);
		bt_changeAppearance.setContentAreaFilled(false);
		bt_changeAppearance.setBorderPainted(false);
		bt_changeAppearance.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn.add(lb1);
		pn.add(lb2);
		pn.add(lb_appearance);
		pn.add(bt1);
		pn.add(bt2);
		pn.add(bt_changeAppearance);
		con.add(pn);
		mainJFrame.setVisible(true);
		bt1.addActionListener(new ActionListener() { //�����û���¼����
			public void actionPerformed(ActionEvent e) {
				flag=false;
				con.remove(pn);
				mainJFrame.repaint();
				loginPage();
				mainJFrame.validate();
			}
		});
		bt2.addActionListener(new ActionListener() { //�������Ա��¼����
			public void actionPerformed(ActionEvent e) {
				flag=true;
				con.remove(pn);
				mainJFrame.repaint();
				loginPage();
				mainJFrame.validate();
			}
		});
		bt_changeAppearance.addMouseListener(new MouseListener() { //��������������
			public void mouseEntered(MouseEvent arg0) {
				bt_changeAppearance.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_changeAppearance.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				try {
					appearance++;
					String lookAndFeel=null;
					switch(appearance) {
					case 1: //Windows���
						lookAndFeel="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
						lb_appearance.setText("<html>��ǰ��ۣ�<br>Windows</html>");
						break;
					case 2: //Nimbus���
						lookAndFeel="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
						lb_appearance.setText("<html>��ǰ��ۣ�<br>Nimbus</html>");
						break;
					case 3: //Windows Classic���
						lookAndFeel="com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
						lb_appearance.setText("<html>��ǰ��ۣ�<br>Windows Classic</html>");
						break;
					case 4://Motif���
						lookAndFeel="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
						lb_appearance.setText("<html>��ǰ��ۣ�<br>Motif</html>");
						break; 
					case 5: //Ĭ�Ϸ��
						lookAndFeel="javax.swing.plaf.metal.MetalLookAndFeel";
						lb_appearance.setText("<html>��ǰ��ۣ�<br>Ĭ��</html>");
						break;
					}
					UIManager.setLookAndFeel(lookAndFeel);
					SwingUtilities.updateComponentTreeUI(mainJFrame);
					if(appearance==5)
						appearance=0;
		        } catch(Exception ex) {
		        	System.out.println(ex);
		        }
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
	}
	
	public static void loginPage() { //��¼����
		mainJFrame.setSize(550,400);
		mainJFrame.setLocationRelativeTo(null);
		JPanel pn=new JPanel();
		pn.setSize(550,400);
		pn.setLayout(null);
		JButton bt1=new JButton("����",new ImageIcon("image/����.png")),bt2=new JButton("ע���˺�"),bt3=new JButton("��  ¼");
		JTextField tf=new JTextField();
		JPasswordField pf=new JPasswordField();
		JLabel lb1,lb2,lb3=new JLabel("���룺");
		if(flag) {
			lb1=new JLabel("����Ա��¼");
			lb2=new JLabel("�˺ţ�");
			lb1.setFont(new Font("����",0,35));
			lb1.setBounds(185,30,180,100);
		}
		else {
			lb1=new JLabel("�û���¼");
			lb2=new JLabel("���ţ�");
			lb1.setFont(new Font("����",0,35));
			lb1.setBounds(200,30,150,100);
		}
		lb2.setFont(new Font("����",0,25));
		lb2.setBounds(100,100,80,100);
		lb3.setFont(new Font("����",0,25));
		lb3.setBounds(100,150,80,100);
		tf.setFont(new Font("����",0,25));
		tf.setBounds(170,130,230,40);
		pf.setFont(new Font(null,0,25));
		pf.setBounds(170,180,230,40);
		bt3.setFont(new Font("����",0,25));
		bt3.setBounds(200,250,150,60);
		bt3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt1.setFont(new Font("����",0,17));
		bt1.setBounds(1,10,92,25);
		bt1.setContentAreaFilled(false);
		bt1.setBorderPainted(false);
		bt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt2.setFont(new Font("����",0,17));
		bt2.setBounds(440,330,105,25);
		bt2.setContentAreaFilled(false);
		bt2.setBorderPainted(false);
		bt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn.add(lb1);
		pn.add(lb2);
		pn.add(lb3);
		pn.add(tf);
		pn.add(pf);
		pn.add(bt1);
		pn.add(bt3);
		if(!flag)
			pn.add(bt2);
		con.add(pn);
		bt1.addMouseListener(new MouseListener() { //���ص���ӭ����
			public void mouseEntered(MouseEvent arg0) {
				bt1.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt1.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				con.remove(pn);
				mainJFrame.repaint();
				welcomePage();
				mainJFrame.validate();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt2.addMouseListener(new MouseListener() { //����ע�����
			public void mouseEntered(MouseEvent arg0) {
				bt2.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt2.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				con.remove(pn);
				mainJFrame.repaint();
				con.add(new Register().registerPage());
				mainJFrame.validate();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt3.addActionListener(new ActionListener() { //��¼����ȡ�������˺����룬��ȷ����빦�ܽ���
			public void actionPerformed(ActionEvent e) {
				if(flag) //���й���Ա�˺��������
					new Login().adminLogin(tf.getText(),String.valueOf(pf.getPassword()));
				else //�����û��˺��������
					new Login().userLogin(tf.getText(),String.valueOf(pf.getPassword()));
			}
		});
	}
}