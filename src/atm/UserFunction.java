package atm;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class UserFunction implements ActionListener { //�û�������
	JFrame userJFrame=new JFrame("ATM��Ա������ϵͳ-----[�û�ģʽ]");
	Container userCon=userJFrame.getContentPane();
	JPanel pn_function=new JPanel(); //���ø��ֹ���ҳ�������
	JLabel lb_topFunction=new JLabel(); //������Ϣ������ǰ����
	JLabel lb_tips=new JLabel(); //��ʾ���ڵ�����
	User payee=null; //������
	int payeeNum; //���������ļ��еļ����е�λ��
	String money; //�������
	public UserFunction() { //�������
		userJFrame.setSize(1300,800);
		userJFrame.setLocationRelativeTo(null);
		userJFrame.setResizable(false);
		userJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userCon.setLayout(null);
		pn_function.setLayout(null);
		pn_function.setBorder(BorderFactory.createEtchedBorder());
		pn_function.setBounds(250,30,1045,735);
		JPanel pn_topUser=new JPanel(); //������Ϣ����������
		pn_topUser.setBackground(Color.white);
		pn_topUser.setBounds(0,0,250,30);
		JLabel lb_topUser=new JLabel("�û���"+Login.user.account);
		lb_topUser.setFont(new Font("����",0,18));
		lb_topUser.setForeground(Color.blue);
		pn_topUser.add(lb_topUser);
		JPanel pn_topFunction=new JPanel(); //������Ϣ������ǰ����
		pn_topFunction.setBackground(Color.white);
		pn_topFunction.setBounds(250,0,1045,30);
		lb_topFunction.setFont(new Font("����",0,20));
		lb_topFunction.setForeground(Color.black);
		pn_topFunction.add(lb_topFunction);
		JPanel pn_menu=new JPanel(); //�˵������й���ѡ��
		pn_menu.setBackground(new Color(249,250,252));
		pn_menu.setBorder(BorderFactory.createEtchedBorder());
		pn_menu.setLayout(null);
		pn_menu.setBounds(0,30,250,735);
		JButton bt_info=new JButton("������Ϣ"); //[������Ϣ]
		bt_info.setFont(new Font("����",0,20));
		bt_info.setContentAreaFilled(false);
		bt_info.setBounds(0,50,249,50);
		JButton bt_cunqu=new JButton("���/ȡ��"); //[���/ȡ��]
		bt_cunqu.setFont(new Font("����",0,20));
		bt_cunqu.setContentAreaFilled(false);
		bt_cunqu.setBounds(0,105,249,50);
		JButton bt_transfer =new JButton("ת��");//[ת��]
		bt_transfer.setFont(new Font("����",0,20));
		bt_transfer.setContentAreaFilled(false);
		bt_transfer.setBounds(0,160,249,50);
		JButton bt_daihuan=new JButton("����/����"); //[����/����]
		bt_daihuan.setFont(new Font("����",0,20));
		bt_daihuan.setContentAreaFilled(false);
		bt_daihuan.setBounds(0,215,249,50);
		JButton bt_record=new JButton("���׼�¼"); //[���׼�¼]
		bt_record.setFont(new Font("����",0,20));
		bt_record.setContentAreaFilled(false);
		bt_record.setBounds(0,270,249,50);
		JButton bt_change=new JButton("�޸�����"); //[�޸�����]
		bt_change.setFont(new Font("����",0,20));
		bt_change.setContentAreaFilled(false);
		bt_change.setBounds(0,325,249,50);
		JButton bt_out=new JButton("�˳�"); //[�˳�]
		bt_out.setFont(new Font("����",0,20));
		bt_out.setContentAreaFilled(false);
		bt_out.setBounds(0,380,249,50);
		bt_info.addActionListener(this);
		bt_cunqu.addActionListener(this);
		bt_transfer.addActionListener(this);
		bt_daihuan.addActionListener(this);
		bt_record.addActionListener(this);
		bt_change.addActionListener(this);
		bt_out.addActionListener(this);
		pn_menu.add(bt_info);
		pn_menu.add(bt_cunqu);
		pn_menu.add(bt_transfer);
		pn_menu.add(bt_daihuan);
		pn_menu.add(bt_record);
		pn_menu.add(bt_change);
		pn_menu.add(bt_out);
		JPanel pn_welcome=new JPanel(); //��ӭҳ
		pn_welcome.setBorder(BorderFactory.createEtchedBorder());
		pn_welcome.setLayout(new BorderLayout());
		pn_welcome.setBounds(0,0,1045,735);
		JLabel lb_welcome=new JLabel("��ӭʹ��");
		lb_welcome.setFont(new Font("����",0,100));
		lb_welcome.setHorizontalAlignment(SwingConstants.CENTER);
		pn_welcome.add(lb_welcome,BorderLayout.CENTER);
		userCon.add(pn_topUser);
		userCon.add(pn_topFunction);
		userCon.add(pn_menu);
		userCon.add(pn_function);
		pn_function.add(pn_welcome);
		userJFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) { //����ѡ��Ĳ˵����ܣ�������Ӧ����
		if(e.getActionCommand().equals("�˳�")) {
			lb_tips.setText("�Ƿ��˳��û�ģʽ��");
			choiceTips();
		}
		else {
			pn_function.removeAll();
			userJFrame.repaint();
			if(e.getActionCommand().equals("������Ϣ")) {
				lb_topFunction.setText("[������Ϣ]");
				pn_function.add(info());
			}
			else if(e.getActionCommand().equals("���/ȡ��")) {
				lb_topFunction.setText("[���/ȡ��]");
				pn_function.add(cunqu());
			}
			else if(e.getActionCommand().equals("ת��")) {
				lb_topFunction.setText("[ת��]");
				pn_function.add(transfer());
			}
			else if(e.getActionCommand().equals("����/����")) {
				lb_topFunction.setText("[����/����]");
				pn_function.add(daihuan());
			}
			else if(e.getActionCommand().equals("���׼�¼")) {
				lb_topFunction.setText("[���׼�¼]");
				pn_function.add(record());
			}
			else if(e.getActionCommand().equals("�޸�����")) {
				lb_topFunction.setText("[�޸�����]");
				pn_function.add(changePassword());
			}
			userJFrame.validate();
		}
	}
	
	public JPanel info() { //[������Ϣ]����
		JPanel pn_info=new JPanel();
		pn_info.setLayout(null);
		pn_info.setSize(1045,735);
		pn_info.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(700,130,200,200);
		JLabel[] lb=new JLabel[10];
		for(int i=0;i<10;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,30));
			lb[i].setBounds(100,72+i*60,500,50);
			pn_info.add(lb[i]);
		}
		lb[0].setText("�˻�״̬�� ����");
		lb[1].setText("��    �ţ� "+Login.user.account);
		lb[2].setText("��    ���� "+Login.user.name);
		lb[3].setText("��    �� "+Login.user.sex);
		lb[4].setText("�� �� ֤�� "+Login.user.idCard);
		lb[5].setText("��    ���� "+Login.user.phone);
		lb[6].setText("�� �� �֣� "+Login.user.credit);
		lb[7].setText("��    � "+Login.user.money);
		lb[8].setText("Ƿ    � "+Login.user.debt);
		lb[9].setText("�������ڣ� "+Login.user.time);
		pn_info.add(lb_infoImage);
		return pn_info;
	}
	
	public JTabbedPane cunqu() { //[���/ȡ��]����
		JLabel lb_cun=new JLabel("����"),lb_qu=new JLabel("ȡ���"),
				lb_money1=new JLabel("��"+Login.user.money),lb1=new JLabel("������100�ı�����"),
				lb_money2=new JLabel("��"+Login.user.money),lb2=new JLabel("������100�ı�����ÿ��ȡ��ó���5000Ԫ��");
		lb_cun.setFont(new Font("����",0,25)); lb_cun.setBounds(275,260,125,50);
		lb_qu.setFont(new Font("����",0,25)); lb_qu.setBounds(275,260,125,50);
		lb_money1.setFont(new Font("����",0,20)); lb_money1.setBounds(10,0,200,50);
		lb_money2.setFont(new Font("����",0,20)); lb_money2.setBounds(10,0,200,50);
		lb1.setFont(new Font("����",0,18)); lb1.setBounds(650,260,200,50);
		lb2.setFont(new Font("����",0,18)); lb2.setBounds(650,260,400,50);
		JTextField tf_cun=new JTextField(),tf_qu=new JTextField();
		tf_cun.setFont(new Font(null,0,25)); tf_cun.setBounds(400,265,250,40);
		tf_cun.setDocument(new NumLimit());
		tf_qu.setFont(new Font(null,0,25)); tf_qu.setBounds(400,265,250,40);
		tf_qu.setDocument(new NumLimit());
		JButton bt_cun=new JButton("���"),bt_qu=new JButton("ȡ��");
		bt_cun.setFont(new Font("����",0,20)); bt_cun.setBounds(472,345,100,50);
		bt_cun.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_qu.setFont(new Font("����",0,20)); bt_qu.setBounds(472,345,100,50);
		bt_qu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JPanel pn_cun=new JPanel(),pn_qu=new JPanel();
		pn_cun.setLayout(null);
		pn_cun.add(lb_money1); pn_cun.add(lb_cun); pn_cun.add(lb1); pn_cun.add(tf_cun); pn_cun.add(bt_cun);
		pn_qu.setLayout(null);
		pn_qu.add(lb_money2); pn_qu.add(lb_qu); pn_qu.add(lb2); pn_qu.add(tf_qu); pn_qu.add(bt_qu);
		JTabbedPane tp_cunqu=new JTabbedPane();
		tp_cunqu.setFont(new Font("����",0,25));
		tp_cunqu.setBounds(0,0,1045,735);
		tp_cunqu.addTab(" ��� ",pn_cun);
		tp_cunqu.addTab(" ȡ�� ",pn_qu);
		tp_cunqu.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	tf_cun.setText("");
            	tf_qu.setText("");
            }
        });
		bt_cun.addActionListener(new ActionListener() { //���
			public void actionPerformed(ActionEvent e) {
				money=tf_cun.getText();
				if(money.equals("")) { //��δ���������������ʾ
					lb_tips.setText("���������");
					functionTips();
				}
				else if(money.substring(0,1).equals("0")||Double.parseDouble(money)%100!=0) { //�������������Ľ���ʽ���󣬽�������ʾ
					lb_tips.setText("�����������100�ı�����");
					functionTips();
				}
				else { //���ϴ�����������д����ʾ
					lb_tips.setText("�Ƿ�ȷ����");
					choiceTips();
				}
			}
		});
		bt_qu.addActionListener(new ActionListener() { //ȡ��
			public void actionPerformed(ActionEvent e) {
				money=tf_qu.getText();
				if(money.equals("")) { //��δ����ȡ�����������ʾ
					lb_tips.setText("������ȡ���");
					functionTips();
				}
				else if(money.substring(0,1).equals("0")||Double.parseDouble(money)%100!=0) { //�������������Ľ���ʽ���󣬽�������ʾ
					lb_tips.setText("ȡ����������100�ı�����");
					functionTips();
				}
				else if(Double.parseDouble(money)>5000) { //����Ľ���ʽ��ȷ��������5000��������ʾ
					lb_tips.setText("ÿ��ȡ��ó���5000Ԫ��");
					functionTips();
				}
				else if(Double.parseDouble(money)>Double.parseDouble(Login.user.money)) { //�����˻���������ʾ
					lb_tips.setText("���㣡");
					functionTips();
				}
				else { //����ȡ������������ȡ����ʾ
					lb_tips.setText("�Ƿ�ȷ��ȡ�");
					choiceTips();
				}
			}
		});
		return tp_cunqu;
	}
	
	public JPanel transfer() { //[ת��]����
		JPanel pn_transfer=new JPanel();
		pn_transfer.setLayout(null);
		pn_transfer.setSize(1045,735);
		pn_transfer.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_account=new JLabel("�����˿��ţ�"),lb_money=new JLabel("ת�˽�"),
				lb0=new JLabel("��"+Login.user.money),lb1=new JLabel("����ת���޶"+Login.user.transferNum),lb2=new JLabel("������100�ı�����");
		JTextField tf_account=new JTextField(),tf_money=new JTextField();
		lb_account.setFont(new Font("����",0,25));
		lb_account.setBounds(250,260,150,50);
		lb_money.setFont(new Font("����",0,25));
		lb_money.setBounds(275,320,125,50);
		lb0.setFont(new Font("����",0,20));
		lb0.setBounds(10,0,200,50);
		lb1.setFont(new Font("����",0,20));
		lb1.setBounds(840,0,200,50);
		lb2.setFont(new Font("����",0,18));
		lb2.setBounds(650,320,200,50);
		tf_account.setFont(new Font(null,0,25));
		tf_account.setBounds(400,265,250,40);
		tf_account.setDocument(new NumLimit());
		tf_money.setFont(new Font(null,0,25));
		tf_money.setBounds(400,325,250,40);
		tf_money.setDocument(new NumLimit());
		JButton bt_transfer=new JButton("ת��");
		bt_transfer.setFont(new Font("����",0,20));
		bt_transfer.setBounds(472,400,100,50);
		bt_transfer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_transfer.add(lb_account);pn_transfer.add(lb_money);pn_transfer.add(lb0);pn_transfer.add(lb1);pn_transfer.add(lb2);
		pn_transfer.add(tf_account);pn_transfer.add(tf_money);pn_transfer.add(bt_transfer);
		bt_transfer.addActionListener(new ActionListener() { //ת��
			public void actionPerformed(ActionEvent e) {
				if(tf_account.getText().equals("")) { //Ϊ���������˿��ţ�������ʾ
					lb_tips.setText("�����������˿��ţ�");
					functionTips();
				}
				else if(tf_money.getText().equals("")) { //δ����ת�˽�������ʾ
					lb_tips.setText("������ת�˽�");
					functionTips();
				}
				else { //�����뿨�źͽ��
					try {
						String account=tf_account.getText();
						money=tf_money.getText();
						ObjectInputStream ois=new ObjectInputStream(new FileInputStream("�û���Ϣ.txt"));
						ArrayList<User> users=(ArrayList<User>)ois.readObject();
						ois.close();
						for(payeeNum=0;payeeNum<users.size();payeeNum++) { //��ѯ��Ӧ�����Ƿ����
							if(account.equals(users.get(payeeNum).account)) {
								payee=users.get(payeeNum);
								break;
							}
						}
						if(payeeNum==users.size()) { //����Ŀ��Ŵ��󣬽�����ʾ
							lb_tips.setText("���Ŵ���");
							functionTips();
						}
						else { //����Ŀ�����ȷ
							if(account.equals(Login.user.account)) { //����Ŀ���Ϊ�Լ��Ŀ��ţ�������ʾ
								lb_tips.setText("���������Լ��Ŀ��ţ�");
								functionTips();
							}
							else { //����Ŀ��ŷ��Լ���
								if(money.substring(0,1).equals("0")||Double.parseDouble(money)%100!=0) { //�����ת�˽���ʽ���󣬽�����ʾ
									lb_tips.setText("ת�˽��������100�ı�����");
									functionTips();
								}
								else { //���źͽ����ȷ
									if(Double.parseDouble(money)>Login.user.transferNum) { //��������ת���޶������ʾ
										lb_tips.setText("��������ת���޶");
										functionTips();
									}
									else {
										if(Double.parseDouble(money)>Double.parseDouble(Login.user.money)) { //�����˻���������ʾ
											lb_tips.setText("���㣡");
											functionTips();
										}
										else { //����ת������
											lb_tips.setText("�Ƿ�ȷ����Է�ת�ˣ�");
											choiceTips();
										}
									}
								}
							}
						}
					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					} catch (ClassNotFoundException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		return pn_transfer;
	}
	
	public JTabbedPane daihuan() { //[����/����]����
		JLabel lb_dai=new JLabel("�����"),lb_huan=new JLabel("�����"),
				lb_notdai=new JLabel(),lb_nothuan=new JLabel(),
				lb_money1=new JLabel("<html>��"+Login.user.money+"<br>Ƿ�"+Login.user.debt+"</html>"),lb11=new JLabel("������100�ı�����"),
				lb_money2=new JLabel("<html>��"+Login.user.money+"<br>Ƿ�"+Login.user.debt+"</html>"),lb21=new JLabel("������Ƿ���"),
				lb12=new JLabel("<html>���ã�"+Login.user.credit+"<br>��ȣ�"+Login.user.credit*1000+"</html>"),lb22=new JLabel("<html>���ã�"+Login.user.credit+"<br>��ȣ�"+Login.user.credit*1000+"</html>"),
				lb_daiTips=new JLabel("<html>��������<br>��ϵͳ�����û����÷֡�80��δǷ��ʱ���ſɽ��д��<br>������Ϊ�û����÷�*1000��<br>������Ϣ����(30��)���㣬������Ϊ1.5%��<br>�Դ�������ÿ30��δ������÷�-1��<br>������һ�λ��壬�������÷�+1��</html>"),
				lb_huanTips=new JLabel("<html>��������<br>��ϵͳ�����û����÷֡�80��δǷ��ʱ���ſɽ��д��<br>������Ϊ�û����÷�*1000��<br>������Ϣ����(30��)���㣬������Ϊ1.5%��<br>�Դ�������ÿ30��δ������÷�-1��<br>������һ�λ��壬�������÷�+1��</html>");
		lb_dai.setFont(new Font("����",0,25)); lb_dai.setBounds(275,260,125,50);
		lb_huan.setFont(new Font("����",0,25)); lb_huan.setBounds(275,260,125,50);
		lb_notdai.setFont(new Font("����",0,25));lb_notdai.setBounds(465,200,125,40);lb_notdai.setForeground(Color.red);
		lb_nothuan.setFont(new Font("����",0,25));lb_nothuan.setBounds(465,200,125,40);lb_nothuan.setForeground(Color.red);
		lb_money1.setFont(new Font("����",0,20)); lb_money1.setBounds(10,9,200,50);
		lb_money2.setFont(new Font("����",0,20)); lb_money2.setBounds(10,9,200,50);
		lb_daiTips.setFont(new Font("����",0,20)); lb_daiTips.setBounds(300,400,500,250);
		lb_huanTips.setFont(new Font("����",0,20)); lb_huanTips.setBounds(300,400,500,250);
		lb11.setFont(new Font("����",0,18)); lb11.setBounds(650,260,200,50);
		lb21.setFont(new Font("����",0,18)); lb21.setBounds(650,260,400,50);
		lb12.setFont(new Font("����",0,20)); lb12.setBounds(900,9,200,50);
		lb22.setFont(new Font("����",0,20)); lb22.setBounds(900,9,200,50);
		JTextField tf_dai=new JTextField(),tf_huan=new JTextField();
		tf_dai.setFont(new Font(null,0,25)); tf_dai.setBounds(400,265,250,40);
		tf_dai.setDocument(new NumLimit());
		tf_huan.setFont(new Font(null,0,25)); tf_huan.setBounds(400,265,250,40);
		JButton bt_dai=new JButton("����"),bt_huan=new JButton("����");
		bt_dai.setFont(new Font("����",0,20)); bt_dai.setBounds(472,345,100,50);
		bt_dai.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_huan.setFont(new Font("����",0,20)); bt_huan.setBounds(472,345,100,50);
		bt_huan.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JPanel pn_dai=new JPanel(),pn_huan=new JPanel();
		pn_dai.setLayout(null);
		pn_dai.add(lb_money1);
		pn_dai.add(lb_daiTips);
		pn_dai.add(lb12);
		pn_dai.add(lb_dai);
		pn_dai.add(lb11);
		pn_dai.add(tf_dai);
		pn_dai.add(bt_dai);
		pn_huan.setLayout(null);
		pn_huan.add(lb_money2);
		pn_huan.add(lb_huanTips);
		pn_huan.add(lb22);
		pn_huan.add(lb_huan);
		pn_huan.add(lb21);
		pn_huan.add(tf_huan);
		pn_huan.add(bt_huan);
		JTabbedPane tp_daihuan=new JTabbedPane();
		tp_daihuan.setFont(new Font("����",0,25));
		tp_daihuan.setBounds(0,0,1045,735);
		tp_daihuan.addTab(" ���� ",pn_dai);
		tp_daihuan.addTab(" ���� ",pn_huan);
		if(Login.user.credit<80) { //���÷ֲ���80����ֹ����
			lb_notdai.setText("���ò��㣡");
			pn_dai.add(lb_notdai);
			tf_dai.setEditable(false);
			bt_dai.setEnabled(false);
		}
		else if(Double.parseDouble(Login.user.debt)!=0) { //Ƿ���У���ֹ����
			lb_notdai.setText("���Ȼ��");
			pn_dai.add(lb_notdai);
			tf_dai.setEditable(false);
			bt_dai.setEnabled(false);
		}
		if(Double.parseDouble(Login.user.debt)==0) { //δǷ����軹��
			lb_nothuan.setText("���軹�");
			pn_huan.add(lb_nothuan);
			tf_huan.setEditable(false);
			bt_huan.setEnabled(false);
		}
		tp_daihuan.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	tf_dai.setText("");
            	tf_huan.setText("");
            }
        });
		bt_dai.addActionListener(new ActionListener() { //����
			public void actionPerformed(ActionEvent e) {
				money=tf_dai.getText();
				if(money.equals("")) { //��δ����������������ʾ
					lb_tips.setText("����������");
					functionTips();
				}
				else if(money.substring(0,1).equals("0")||Double.parseDouble(money)%100!=0) { //�������������Ľ���ʽ���󣬽�������ʾ
					lb_tips.setText("������������100�ı�����");
					functionTips();
				}
				else if(Double.parseDouble(money)>Login.user.credit*1000) { //�������������Ľ����ڴ����ȣ���������ʾ
					lb_tips.setText("���������ȣ�");
					functionTips();
				}
				else { //���ϴ������������д�����ʾ
					lb_tips.setText("�Ƿ�ȷ�����");
					choiceTips();
				}
			}
		});
		bt_huan.addActionListener(new ActionListener() { //����
			public void actionPerformed(ActionEvent e) {
				money=tf_huan.getText();
				if(money.equals("")) { //��δ���뻹�����������ʾ
					lb_tips.setText("�����뻹���");
					functionTips();
				}
				else if(!Login.user.debt.equals(money)) { //�������������Ľ���ʽ���󣬽�������ʾ
					lb_tips.setText("���������������������Ƿ���");
					functionTips();
				}
				else if(Double.parseDouble(money)>Double.parseDouble(Login.user.money)) { //�����˻���������ʾ
					lb_tips.setText("���㣡");
					functionTips();
				}
				else { //���ϻ������������л�����ʾ
					lb_tips.setText("�Ƿ�ȷ�����");
					choiceTips();
				}
			}
		});
		return tp_daihuan;
	}
	
	public JPanel record() { //[���׼�¼]����
		//��ȡ���׼�¼����
		int n; //��ʾ���״���
		String[][] recordData;
		if(Login.user.record.isEmpty()) { //�޽��׼�¼
			n=0;
			recordData=new String[1][5];
			for(int i=0;i<5;i++)
				recordData[0][i]="��";
		}
		else { //�н��׼�¼
			n=Login.user.record.size()/3;
			recordData=new String[n][5];
			for(int i=0,j=0;i<Login.user.record.size();i+=3,j++) { //��ʽ��ÿ�����׼�¼
				recordData[j][0]=Login.user.account; //�˻�
				recordData[j][1]=Login.user.record.get(i); //��ϸ
				recordData[j][2]=Login.user.record.get(i+1); //ʱ��
				recordData[j][3]=Login.user.record.get(i+2); //���
				recordData[j][4]=Login.user.name; //������
			}
		}
		//����
		JPanel pn_record=new JPanel();
		pn_record.setLayout(null);
		pn_record.setSize(1045,735);
		pn_record.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_export=new JButton("����");
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setBounds(945,0,100,50);
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JTable table=new JTable(new MyTableModel(recordData));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",0,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(1,50,1044,685);
		pn_record.add(bt_export);
		pn_record.add(scrollPane);
		bt_export.addActionListener(new ActionListener() { //�����׼�¼������Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(userJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		return pn_record;
	}
	
	public JPanel changePassword() { //[�޸�����]����
		JPanel pn_changePassword=new JPanel();
		pn_changePassword.setLayout(null);
		pn_changePassword.setSize(1045,735);
		pn_changePassword.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_old=new JLabel("�����룺"),lb_new1=new JLabel("�����룺"),lb_new2=new JLabel("ȷ�����룺"),lb=new JLabel("������6λ�������룩");
		JPasswordField pf_old=new JPasswordField(),pf_new1=new JPasswordField(),pf_new2=new JPasswordField();
		lb_old.setFont(new Font("����",0,25));
		lb_old.setBounds(300,200,100,50);
		lb_new1.setFont(new Font("����",0,25));
		lb_new1.setBounds(300,260,100,50);
		lb_new2.setFont(new Font("����",0,25));
		lb_new2.setBounds(275,320,125,50);
		lb.setFont(new Font("����",0,18));
		lb.setBounds(650,260,200,50);
		pf_old.setFont(new Font(null,0,30));
		pf_old.setBounds(400,205,250,40);
		pf_new1.setFont(new Font(null,0,30));
		pf_new1.setBounds(400,265,250,40);
		pf_new2.setFont(new Font(null,0,30));
		pf_new2.setBounds(400,325,250,40);
		pn_changePassword.add(lb_old);pn_changePassword.add(lb_new1);pn_changePassword.add(lb_new2);pn_changePassword.add(lb);
		pn_changePassword.add(pf_old);pn_changePassword.add(pf_new1);pn_changePassword.add(pf_new2);
		JButton bt_confirm=new JButton("ȷ��"),bt_reset=new JButton("����");
		bt_confirm.setFont(new Font("����",0,20));
		bt_confirm.setBounds(405,410,100,50);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_reset.setFont(new Font("����",0,20));
		bt_reset.setBounds(540,410,100,50);
		bt_reset.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_changePassword.add(bt_confirm);pn_changePassword.add(bt_reset);
		bt_reset.addActionListener(new ActionListener() { //���ã���������������
			public void actionPerformed(ActionEvent e) {
				pf_old.setText("");
				pf_new1.setText("");
				pf_new2.setText("");
			}
		});
		bt_confirm.addActionListener(new ActionListener() { //ȷ�ϣ��ο���ע��ʱ���㷨��
			public void actionPerformed(ActionEvent e) {
				boolean y1,y2=false,y3,y4,y5,y6=false;
				//y1��ʾ�Ƿ�ȫ����д��y2��ʾ�����ʽ�Ƿ���ȷ��y3��ʾ���������Ƿ���ͬ��y4��ʾ�����Ƿ�Ϊ���֣�y5��ʾ�����Ƿ�Ϊ6λ��y6��ʾ��λ�����Ƿ���ȫ��ͬ
				y1=(!String.valueOf(pf_old.getPassword()).equals("")) //�ж���Ϣ�Ƿ�ȫ����д
						&&(!String.valueOf(pf_new1.getPassword()).equals(""))
						&&(!String.valueOf(pf_new2.getPassword()).equals(""));
				if(!y1) { //��δ������д��Ϣ����������ʾ
					lb_tips.setText("����д������");
					functionTips();
				}
				else { //��������д��Ϣ�������������ж�
					if(!String.valueOf(pf_old.getPassword()).equals(Login.user.password)) { //������ľ�������󣬽�������ʾ
						lb_tips.setText("���������");
						functionTips();
					}
					else { //������ľ�������ȷ�������������ʽ�ж�
						String password1=String.valueOf(pf_new1.getPassword()),password2=String.valueOf(pf_new2.getPassword());
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
										functionTips();
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
											functionTips();
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
								functionTips();
							}
						}
						else { //���������벻��ͬ����������ʾ
							y3=false;
							lb_tips.setText("�������벻ͬ��");
							functionTips();
						}
						if(y2) { //���ϸ�������������и���
							try {
								password1=String.valueOf(pf_new1.getPassword());
								Login.user.password=password1;
								ObjectInputStream ois=new ObjectInputStream(new FileInputStream("�û���Ϣ.txt"));
								ArrayList<User> users=(ArrayList<User>)ois.readObject();
								ois.close();
								users.remove(Login.userNum);
								users.add(Login.userNum,Login.user);
								ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("�û���Ϣ.txt"));
								oos.writeObject(users);
								oos.close();
								pf_old.setText("");
								pf_new1.setText("");
								pf_new2.setText("");
								lb_tips.setText("�����޸ĳɹ���");
								functionTips();
								pn_function.removeAll();
								userJFrame.repaint();
								pn_function.add(changePassword());
								userJFrame.validate();
							} catch (FileNotFoundException ex) {
								ex.printStackTrace();
							} catch (IOException ex) {
								ex.printStackTrace();
							} catch (ClassNotFoundException ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}
		});
		return pn_changePassword;
	}
	
	public void functionTips() { //����ʱ��������ʾ��Ϣ����
		JDialog tips=new JDialog(userJFrame,"  ��ʾ",true);
		JPanel pn_tips=new JPanel();
		JButton bt_tips=new JButton("ȷ��");
		tips.setSize(500,200);
		tips.setLocationRelativeTo(null);
		tips.setResizable(false);
		tips.setLayout(null);
		pn_tips.setBounds(0,30,500,70);
		lb_tips.setFont(new Font("����",0,25));
		bt_tips.setFont(new Font("����",0,20));
		bt_tips.setBounds(200,100,100,50);
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
	
	public void choiceTips() { //����ʱ��������ʾѡ�񴰿�
		JDialog choiceTips=new JDialog(userJFrame,"  ��ʾ",true);
		JPanel pn_tips=new JPanel();
		JButton bt_yes=new JButton("��(Y)");
		JButton bt_no=new JButton("��(N)");
		choiceTips.setSize(500,200);
		choiceTips.setLocationRelativeTo(null);
		choiceTips.setResizable(false);
		choiceTips.setLayout(null);
		pn_tips.setBounds(0,30,500,70);
		lb_tips.setFont(new Font("����",0,25));
		bt_yes.setFont(new Font("����",0,20));
		bt_yes.setBounds(135,100,100,50);
		bt_yes.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_no.setFont(new Font("����",0,20));
		bt_no.setBounds(260,100,100,50);
		bt_no.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_tips.add(lb_tips);
		choiceTips.add(pn_tips);
		choiceTips.add(bt_yes);
		choiceTips.add(bt_no);
		bt_yes.addActionListener(new ActionListener() { //ѡ���ǡ���������Ӧ����
			public void actionPerformed(ActionEvent e) {
				choiceTips.dispose();
				yesOperation();
			}
		});
		bt_no.addActionListener(new ActionListener() { //ѡ�񡰷񡱣��ر���ʾѡ�񴰿�
			public void actionPerformed(ActionEvent e) {
				choiceTips.dispose();
			}
		});
		bt_yes.addKeyListener(new KeyListener() { //���ǡ���ť�Ŀ�ݼ���Y�������񡱰�ť�Ŀ�ݼ���N��
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_Y) {
					choiceTips.dispose();
					yesOperation();
				}
				if(e.getKeyCode()==KeyEvent.VK_N) {
					choiceTips.dispose();
				}
			}
			public void keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){}
		});
		bt_no.addKeyListener(new KeyListener() { //���ǡ���ť�Ŀ�ݼ���Y�������񡱰�ť�Ŀ�ݼ���N��
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_Y) {
					choiceTips.dispose();
					yesOperation();
				}
				if(e.getKeyCode()==KeyEvent.VK_N) {
					choiceTips.dispose();
				}
			}
			public void keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){}
		});
		choiceTips.setVisible(true);
	}
	
	public void yesOperation() { //ѡ���ǡ�֮�󣬸�����ʾ���ݣ�ѡ��Ҫ���еĲ���
		if(lb_tips.getText().equals("�Ƿ��˳��û�ģʽ��")) { //ȷ���˳��û�ģʽ�����ص���ӭ����
			userJFrame.dispose();
			HomePage.con.removeAll();
			HomePage.mainJFrame.repaint();
			HomePage.welcomePage();
			HomePage.mainJFrame.validate();
		}
		if(lb_tips.getText().equals("�Ƿ�ȷ����Է�ת�ˣ�")) { //ȷ������ת�ˣ��޸��û������Ϣ
			SimpleDateFormat timeFormat1=new SimpleDateFormat("yyyy-MM-dd"),timeFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			payee.money=String.valueOf(Double.parseDouble(payee.money)+Double.parseDouble(money)); //����
			Login.user.money=String.valueOf(Double.parseDouble(Login.user.money)-Double.parseDouble(money)); //ת��
			Login.user.transferNum=(int)(Login.user.transferNum-Double.parseDouble(money)); //ת�˷������޶����
			Login.user.transferTime.add(timeFormat1.format(System.currentTimeMillis())); //��¼ת��ʱ��
			//��¼���׼�¼
			Login.user.record.add("ת��  "+money);
			Login.user.record.add(timeFormat2.format(System.currentTimeMillis()));
			Login.user.record.add(Login.user.money);
			payee.record.add("����  "+money);
			payee.record.add(timeFormat2.format(System.currentTimeMillis()));
			payee.record.add(payee.money);
			try {
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream("�û���Ϣ.txt"));
				ArrayList<User> users=(ArrayList<User>)ois.readObject();
				ois.close();
				users.remove(Login.userNum);
				users.add(Login.userNum,Login.user);
				users.remove(payeeNum);
				users.add(payeeNum,payee);
				ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("�û���Ϣ.txt"));
				oos.writeObject(users);
				oos.close();
				lb_tips.setText("ת�˳ɹ���");
				functionTips();
				pn_function.removeAll();
				userJFrame.repaint();
				pn_function.add(transfer());
				userJFrame.validate();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		if(lb_tips.getText().equals("�Ƿ�ȷ����")||lb_tips.getText().equals("�Ƿ�ȷ��ȡ�")) { //ȷ�����д��/ȡ��޸��û������Ϣ
			SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(lb_tips.getText().equals("�Ƿ�ȷ����")) { //���
				Login.user.money=String.valueOf(Double.parseDouble(Login.user.money)+Double.parseDouble(money)); //���������
				//��¼���׼�¼
				Login.user.record.add("���  "+money);
				Login.user.record.add(timeFormat.format(System.currentTimeMillis()));
				Login.user.record.add(Login.user.money);
				lb_tips.setText("���ɹ���");
			}
			else { //ȡ��
				Login.user.money=String.valueOf(Double.parseDouble(Login.user.money)-Double.parseDouble(money)); //ȡ�������
				//��¼���׼�¼
				Login.user.record.add("ȡ��  "+money);
				Login.user.record.add(timeFormat.format(System.currentTimeMillis()));
				Login.user.record.add(Login.user.money);
				lb_tips.setText("ȡ��ɹ���");
			}
			try {
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream("�û���Ϣ.txt"));
				ArrayList<User> users=(ArrayList<User>)ois.readObject();
				ois.close();
				users.remove(Login.userNum);
				users.add(Login.userNum,Login.user);
				ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("�û���Ϣ.txt"));
				oos.writeObject(users);
				oos.close();
				functionTips();
				pn_function.removeAll();
				userJFrame.repaint();
				pn_function.add(cunqu());
				userJFrame.validate();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		if(lb_tips.getText().equals("�Ƿ�ȷ�����")||lb_tips.getText().equals("�Ƿ�ȷ�����")) { //ȷ�����д���/����޸��û������Ϣ
			SimpleDateFormat timeFormat1=new SimpleDateFormat("yyyy-MM-dd"),timeFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(lb_tips.getText().equals("�Ƿ�ȷ�����")) { //����
				Login.user.money=String.valueOf(Double.parseDouble(Login.user.money)+Double.parseDouble(money)); //����������
				Login.user.debt=money; //�������Ƿ����
				Login.user.loanTime.add(timeFormat1.format(System.currentTimeMillis())); //��¼����ʱ��
				Login.user.dai.add(String.valueOf(Login.user.credit));Login.user.dai.add(Login.user.debt); //����ʱ���÷ֺ�Ƿ���¼
				//��¼���׼�¼
				Login.user.record.add("����  "+money);
				Login.user.record.add(timeFormat2.format(System.currentTimeMillis()));
				Login.user.record.add(Login.user.money);
				lb_tips.setText("����ɹ���");
			}
			else { //����
				Login.user.money=String.valueOf(Double.parseDouble(Login.user.money)-Double.parseDouble(money)); //���������
				Login.user.debt="0"; //���Ƿ������0
				if(Login.user.credit<100) //���÷�+1
					Login.user.credit++;
				//��¼���׼�¼
				Login.user.record.add("����  "+money);
				Login.user.record.add(timeFormat2.format(System.currentTimeMillis()));
				Login.user.record.add(Login.user.money);
				lb_tips.setText("����ɹ���");
			}
			try {
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream("�û���Ϣ.txt"));
				ArrayList<User> users=(ArrayList<User>)ois.readObject();
				ois.close();
				users.remove(Login.userNum);
				users.add(Login.userNum,Login.user);
				ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("�û���Ϣ.txt"));
				oos.writeObject(users);
				oos.close();
				functionTips();
				pn_function.removeAll();
				userJFrame.repaint();
				pn_function.add(daihuan());
				userJFrame.validate();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}
}