package atm;
import java.io.*;
import java.util.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Login { //��¼��
	static ArrayList<User> allUsers=null; //�����û�
	static User user=null; //���е�¼/�������û�
	static int userNum=-1; //���е�¼/�������û����ļ��еļ����е�λ��
	JLabel lb_tips=new JLabel(); //��ʾ���ڵ�����
	public void adminLogin(String account,String password) { //����Ա��¼
		if(account.equals("Admin") && password.equals("Admin")) { //�����˺����룬����ȷ����������Ա���ܽ���
			try {
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream("�û���Ϣ.txt"));
				allUsers=(ArrayList<User>)ois.readObject(); //���ļ��ж�ȡ�����û�
				ois.close();
				for(int i=0;i<allUsers.size();i++) { //�����û�Ƿ������÷�
					user=allUsers.get(i);
					userNum=i;
					//��Ƿ�����Ƿ������÷�
					if(Double.parseDouble(user.debt)!=0) {
						SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
						Date date1=timeFormat.parse(timeFormat.format(System.currentTimeMillis()));
						Date date2=timeFormat.parse(user.loanTime.get(user.loanTime.size()-1));
						int difference=(int)((date1.getTime()-date2.getTime())/(1000*3600*24)); //�����������
						if(difference>=30) { //�ﵽ30�죬�������÷֡���Ϣ��Ƿ����
							//��ȡ����ʱ���÷ֺ�Ƿ��
							int credit=Integer.parseInt(user.dai.get(user.dai.size()-2));
							String debt=user.dai.get(user.dai.size()-1); 
							if(credit>difference%30) //���÷��㹻��ȡ
								user.credit=credit-(int)(difference%30); //�������÷֣�ÿ30��-1;
							else //���÷ֲ����ȡ����0
								user.credit=0;
							user.debt=String.valueOf(Double.parseDouble(debt)+Double.parseDouble(debt)*0.015*(difference%30)); //����Ƿ�ÿ30����ȡ����Ϣ
							allUsers.remove(userNum);
							allUsers.add(userNum,user);
							ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("�û���Ϣ.txt"));
							oos.writeObject(allUsers);
							oos.close();
						}
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			lb_tips.setText("��¼�ɹ���");
			loginTips();
			new AdminFunction();
			HomePage.mainJFrame.dispose();
		}
		else { //�������������ʾ
			lb_tips.setText("�˺Ż��������");
			loginTips();
		}
	}
	
	public void userLogin(String account,String password) { //�û���¼
		if(account.equals("")) { //δ���뿨�ţ�������ʾ
			lb_tips.setText("�����뿨�ţ�");
			loginTips();
		}
		else if(password.equals("")) { //�����뿨�ţ���δ�������룬������ʾ
			lb_tips.setText("���������룡");
			loginTips();
		}
		else { //�����뿨�ź�����
			int i=0; //��ʱѭ������������Ԫ���ڼ����е�λ��
			ArrayList<User> users=null;
			try {
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream("�û���Ϣ.txt"));
				users=(ArrayList<User>)ois.readObject();
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			for(i=0;i<users.size();i++) { //��ѯ��Ӧ�����Ƿ����
				if(account.equals(users.get(i).account)) {
					user=users.get(i);
					userNum=i;
					break;
				}
			}
			if(i==users.size()) { //�����뿨�ź����룬������Ŀ��Ŵ��󣬽�����ʾ
				lb_tips.setText("�ÿ��Ų����ڣ�");
				loginTips();
			}
			else { //�����뿨�ź����룬������Ŀ�����ȷ����������
				if(user.lock) { //�˻�������������������ʾ
					lb_tips.setText("�˻�����������������ϵ����Ա������");
					loginTips();
				}
				else { //�˻�δ���������е����������������㼰�ж�
					try {
						int n=0; //��������������
						SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
						for(i=user.errorTime.size()-1;i>=user.errorTime.size()-3;i--) { //���е�����������������
							if(timeFormat.parse(timeFormat.format(System.currentTimeMillis())).equals(timeFormat.parse(user.errorTime.get(i)))) {
								n++;
							}
						}
						user.errorNum=n;
						if(user.errorNum==3) { //������������������3�Σ��������е�¼����������ʾ
							lb_tips.setText("������������3�Σ��������ٵ�¼��");
							loginTips();
						}
						else { //����������������δ��3�Σ��ɼ������Ե�¼
							if(password.equals(user.password)) { //������ȷ����¼�ɹ��������û����ܽ���
								//�����������ת���ղ�ͬ������ת���޶��ʼ��10000
								if(!(timeFormat.parse(timeFormat.format(System.currentTimeMillis())).equals(timeFormat.parse(user.transferTime.get(user.transferTime.size()-1))))) {
									user.transferNum=10000;
									users.remove(userNum);
									users.add(userNum,user);
									ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("�û���Ϣ.txt"));
									oos.writeObject(users);
									oos.close();
								}
								//��Ƿ��������������Ƿ������÷�
								if(Double.parseDouble(user.debt)!=0) {
									Date date1=timeFormat.parse(timeFormat.format(System.currentTimeMillis()));
									Date date2=timeFormat.parse(user.loanTime.get(user.loanTime.size()-1));
									int difference=(int)((date1.getTime()-date2.getTime())/(1000*3600*24)); //�����������
									if(difference>=30) { //�ﵽ30�죬�������÷֡���Ϣ��Ƿ����
										//��ȡ����ʱ���÷ֺ�Ƿ��
										int credit=Integer.parseInt(user.dai.get(user.dai.size()-2));
										String debt=user.dai.get(user.dai.size()-1); 
										if(credit>difference%30) //���÷��㹻��ȡ
											user.credit=credit-(int)(difference%30); //�������÷֣�ÿ30��-1;
										else //���÷ֲ����ȡ����0
											user.credit=0;
										user.debt=String.valueOf(Double.parseDouble(debt)+Double.parseDouble(debt)*0.015*(difference%30)); //����Ƿ�ÿ30����ȡ����Ϣ
										users.remove(userNum);
										users.add(userNum,user);
										ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("�û���Ϣ.txt"));
										oos.writeObject(users);
										oos.close();
									}
								}
								lb_tips.setText("��¼�ɹ���");
								loginTips();
								new UserFunction();
								HomePage.mainJFrame.dispose();
							}
							else { //�������
								user.errorTime.add(timeFormat.format(System.currentTimeMillis())); //��¼�������ʱ��
								user.errorNum=n+1; //��������������+1
								//������������������3�Σ���¼����ʱ�䣬��������+1
								if(user.errorNum==3&&!(timeFormat.parse(timeFormat.format(System.currentTimeMillis())).equals(timeFormat.parse(user.lockTime.get(user.lockTime.size()-1))))) {
									user.lockTime.add(timeFormat.format(System.currentTimeMillis())); //��¼����ʱ��
									user.lockDays++; //��������+1
									if(user.lockDays==3) //������������3�죬�����������˻�
										user.lock=true;
								}
								users.remove(userNum);
								users.add(userNum,user);
								ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("�û���Ϣ.txt"));
								oos.writeObject(users);
								oos.close();
								lb_tips.setText("������󣬽���ʣ�������"+(3-user.errorNum));
								loginTips();
							}
						}
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void loginTips() { //��¼ʱ��������ʾ��Ϣ����
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
}