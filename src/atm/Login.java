package atm;
import java.io.*;
import java.util.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Login { //登录类
	static ArrayList<User> allUsers=null; //所有用户
	static User user=null; //进行登录/操作的用户
	static int userNum=-1; //进行登录/操作的用户在文件中的集合中的位置
	JLabel lb_tips=new JLabel(); //提示窗口的内容
	public void adminLogin(String account,String password) { //管理员登录
		if(account.equals("Admin") && password.equals("Admin")) { //检验账号密码，若正确，则进入管理员功能界面
			try {
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream("用户信息.txt"));
				allUsers=(ArrayList<User>)ois.readObject(); //从文件中读取所有用户
				ois.close();
				for(int i=0;i<allUsers.size();i++) { //更新用户欠款金额及信用分
					user=allUsers.get(i);
					userNum=i;
					//若欠款，更新欠款金额及信用分
					if(Double.parseDouble(user.debt)!=0) {
						SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
						Date date1=timeFormat.parse(timeFormat.format(System.currentTimeMillis()));
						Date date2=timeFormat.parse(user.loanTime.get(user.loanTime.size()-1));
						int difference=(int)((date1.getTime()-date2.getTime())/(1000*3600*24)); //计算贷款天数
						if(difference>=30) { //达到30天，计算信用分、利息及欠款金额
							//获取贷款时信用分和欠款
							int credit=Integer.parseInt(user.dai.get(user.dai.size()-2));
							String debt=user.dai.get(user.dai.size()-1); 
							if(credit>difference%30) //信用分足够扣取
								user.credit=credit-(int)(difference%30); //计算信用分，每30天-1;
							else //信用分不足扣取，置0
								user.credit=0;
							user.debt=String.valueOf(Double.parseDouble(debt)+Double.parseDouble(debt)*0.015*(difference%30)); //计算欠款，每30天收取月利息
							allUsers.remove(userNum);
							allUsers.add(userNum,user);
							ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("用户信息.txt"));
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
			lb_tips.setText("登录成功！");
			loginTips();
			new AdminFunction();
			HomePage.mainJFrame.dispose();
		}
		else { //若错误，则进行提示
			lb_tips.setText("账号或密码错误！");
			loginTips();
		}
	}
	
	public void userLogin(String account,String password) { //用户登录
		if(account.equals("")) { //未输入卡号，进行提示
			lb_tips.setText("请输入卡号！");
			loginTips();
		}
		else if(password.equals("")) { //已输入卡号，但未输入密码，进行提示
			lb_tips.setText("请输入密码！");
			loginTips();
		}
		else { //已输入卡号和密码
			int i=0; //临时循环变量，代表元素在集合中的位置
			ArrayList<User> users=null;
			try {
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream("用户信息.txt"));
				users=(ArrayList<User>)ois.readObject();
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			for(i=0;i<users.size();i++) { //查询相应卡号是否存在
				if(account.equals(users.get(i).account)) {
					user=users.get(i);
					userNum=i;
					break;
				}
			}
			if(i==users.size()) { //已输入卡号和密码，但输入的卡号错误，进行提示
				lb_tips.setText("该卡号不存在！");
				loginTips();
			}
			else { //已输入卡号和密码，且输入的卡号正确，密码随意
				if(user.lock) { //账户已永久锁定，进行提示
					lb_tips.setText("账户已永久锁定，请联系管理员解锁！");
					loginTips();
				}
				else { //账户未锁定，进行当日密码出错次数计算及判断
					try {
						int n=0; //今日密码出错次数
						SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
						for(i=user.errorTime.size()-1;i>=user.errorTime.size()-3;i--) { //进行当日密码出错次数计算
							if(timeFormat.parse(timeFormat.format(System.currentTimeMillis())).equals(timeFormat.parse(user.errorTime.get(i)))) {
								n++;
							}
						}
						user.errorNum=n;
						if(user.errorNum==3) { //若当日密码出错次数达3次，继续进行登录，将进行提示
							lb_tips.setText("今日密码出错达3次，请明日再登录！");
							loginTips();
						}
						else { //若当日密码出错次数未达3次，可继续尝试登录
							if(password.equals(user.password)) { //密码正确，登录成功，进入用户功能界面
								//若今日与最后转账日不同，今日转账限额初始化10000
								if(!(timeFormat.parse(timeFormat.format(System.currentTimeMillis())).equals(timeFormat.parse(user.transferTime.get(user.transferTime.size()-1))))) {
									user.transferNum=10000;
									users.remove(userNum);
									users.add(userNum,user);
									ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("用户信息.txt"));
									oos.writeObject(users);
									oos.close();
								}
								//若欠款，计算贷款天数和欠款金额及信用分
								if(Double.parseDouble(user.debt)!=0) {
									Date date1=timeFormat.parse(timeFormat.format(System.currentTimeMillis()));
									Date date2=timeFormat.parse(user.loanTime.get(user.loanTime.size()-1));
									int difference=(int)((date1.getTime()-date2.getTime())/(1000*3600*24)); //计算贷款天数
									if(difference>=30) { //达到30天，计算信用分、利息及欠款金额
										//获取贷款时信用分和欠款
										int credit=Integer.parseInt(user.dai.get(user.dai.size()-2));
										String debt=user.dai.get(user.dai.size()-1); 
										if(credit>difference%30) //信用分足够扣取
											user.credit=credit-(int)(difference%30); //计算信用分，每30天-1;
										else //信用分不足扣取，置0
											user.credit=0;
										user.debt=String.valueOf(Double.parseDouble(debt)+Double.parseDouble(debt)*0.015*(difference%30)); //计算欠款，每30天收取月利息
										users.remove(userNum);
										users.add(userNum,user);
										ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("用户信息.txt"));
										oos.writeObject(users);
										oos.close();
									}
								}
								lb_tips.setText("登录成功！");
								loginTips();
								new UserFunction();
								HomePage.mainJFrame.dispose();
							}
							else { //密码错误
								user.errorTime.add(timeFormat.format(System.currentTimeMillis())); //记录密码错误时间
								user.errorNum=n+1; //当日密码出错次数+1
								//若当日密码出错次数达3次，记录锁定时间，锁定天数+1
								if(user.errorNum==3&&!(timeFormat.parse(timeFormat.format(System.currentTimeMillis())).equals(timeFormat.parse(user.lockTime.get(user.lockTime.size()-1))))) {
									user.lockTime.add(timeFormat.format(System.currentTimeMillis())); //记录锁定时间
									user.lockDays++; //锁定天数+1
									if(user.lockDays==3) //若锁定天数达3天，将永久锁定账户
										user.lock=true;
								}
								users.remove(userNum);
								users.add(userNum,user);
								ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("用户信息.txt"));
								oos.writeObject(users);
								oos.close();
								lb_tips.setText("密码错误，今日剩余次数："+(3-user.errorNum));
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

	public void loginTips() { //登录时弹出的提示信息窗口
		JDialog tips=new JDialog(HomePage.mainJFrame,"  提示",true);
		JPanel pn_tips=new JPanel();
		JButton bt_tips=new JButton("确 定");
		tips.setSize(450,200);
		tips.setLocationRelativeTo(null);
		tips.setResizable(false);
		tips.setLayout(null);
		pn_tips.setBounds(0,30,450,70);
		lb_tips.setFont(new Font("黑体",0,25));
		bt_tips.setFont(new Font("黑体",0,20));
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