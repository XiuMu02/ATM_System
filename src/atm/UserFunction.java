package atm;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class UserFunction implements ActionListener { //用户功能类
	JFrame userJFrame=new JFrame("ATM柜员机线上系统-----[用户模式]");
	Container userCon=userJFrame.getContentPane();
	JPanel pn_function=new JPanel(); //放置各种功能页面的容器
	JLabel lb_topFunction=new JLabel(); //顶部信息栏，当前功能
	JLabel lb_tips=new JLabel(); //提示窗口的内容
	User payee=null; //收账人
	int payeeNum; //收账人在文件中的集合中的位置
	String money; //流动金额
	public UserFunction() { //整体界面
		userJFrame.setSize(1300,800);
		userJFrame.setLocationRelativeTo(null);
		userJFrame.setResizable(false);
		userJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userCon.setLayout(null);
		pn_function.setLayout(null);
		pn_function.setBorder(BorderFactory.createEtchedBorder());
		pn_function.setBounds(250,30,1045,735);
		JPanel pn_topUser=new JPanel(); //顶部信息栏，操作者
		pn_topUser.setBackground(Color.white);
		pn_topUser.setBounds(0,0,250,30);
		JLabel lb_topUser=new JLabel("用户："+Login.user.account);
		lb_topUser.setFont(new Font("黑体",0,18));
		lb_topUser.setForeground(Color.blue);
		pn_topUser.add(lb_topUser);
		JPanel pn_topFunction=new JPanel(); //顶部信息栏，当前功能
		pn_topFunction.setBackground(Color.white);
		pn_topFunction.setBounds(250,0,1045,30);
		lb_topFunction.setFont(new Font("黑体",0,20));
		lb_topFunction.setForeground(Color.black);
		pn_topFunction.add(lb_topFunction);
		JPanel pn_menu=new JPanel(); //菜单，进行功能选择
		pn_menu.setBackground(new Color(249,250,252));
		pn_menu.setBorder(BorderFactory.createEtchedBorder());
		pn_menu.setLayout(null);
		pn_menu.setBounds(0,30,250,735);
		JButton bt_info=new JButton("个人信息"); //[个人信息]
		bt_info.setFont(new Font("黑体",0,20));
		bt_info.setContentAreaFilled(false);
		bt_info.setBounds(0,50,249,50);
		JButton bt_cunqu=new JButton("存款/取款"); //[存款/取款]
		bt_cunqu.setFont(new Font("黑体",0,20));
		bt_cunqu.setContentAreaFilled(false);
		bt_cunqu.setBounds(0,105,249,50);
		JButton bt_transfer =new JButton("转账");//[转账]
		bt_transfer.setFont(new Font("黑体",0,20));
		bt_transfer.setContentAreaFilled(false);
		bt_transfer.setBounds(0,160,249,50);
		JButton bt_daihuan=new JButton("贷款/还款"); //[贷款/还款]
		bt_daihuan.setFont(new Font("黑体",0,20));
		bt_daihuan.setContentAreaFilled(false);
		bt_daihuan.setBounds(0,215,249,50);
		JButton bt_record=new JButton("交易记录"); //[交易记录]
		bt_record.setFont(new Font("黑体",0,20));
		bt_record.setContentAreaFilled(false);
		bt_record.setBounds(0,270,249,50);
		JButton bt_change=new JButton("修改密码"); //[修改密码]
		bt_change.setFont(new Font("黑体",0,20));
		bt_change.setContentAreaFilled(false);
		bt_change.setBounds(0,325,249,50);
		JButton bt_out=new JButton("退出"); //[退出]
		bt_out.setFont(new Font("黑体",0,20));
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
		JPanel pn_welcome=new JPanel(); //欢迎页
		pn_welcome.setBorder(BorderFactory.createEtchedBorder());
		pn_welcome.setLayout(new BorderLayout());
		pn_welcome.setBounds(0,0,1045,735);
		JLabel lb_welcome=new JLabel("欢迎使用");
		lb_welcome.setFont(new Font("黑体",0,100));
		lb_welcome.setHorizontalAlignment(SwingConstants.CENTER);
		pn_welcome.add(lb_welcome,BorderLayout.CENTER);
		userCon.add(pn_topUser);
		userCon.add(pn_topFunction);
		userCon.add(pn_menu);
		userCon.add(pn_function);
		pn_function.add(pn_welcome);
		userJFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) { //依据选择的菜单功能，进行相应功能
		if(e.getActionCommand().equals("退出")) {
			lb_tips.setText("是否退出用户模式？");
			choiceTips();
		}
		else {
			pn_function.removeAll();
			userJFrame.repaint();
			if(e.getActionCommand().equals("个人信息")) {
				lb_topFunction.setText("[个人信息]");
				pn_function.add(info());
			}
			else if(e.getActionCommand().equals("存款/取款")) {
				lb_topFunction.setText("[存款/取款]");
				pn_function.add(cunqu());
			}
			else if(e.getActionCommand().equals("转账")) {
				lb_topFunction.setText("[转账]");
				pn_function.add(transfer());
			}
			else if(e.getActionCommand().equals("贷款/还款")) {
				lb_topFunction.setText("[贷款/还款]");
				pn_function.add(daihuan());
			}
			else if(e.getActionCommand().equals("交易记录")) {
				lb_topFunction.setText("[交易记录]");
				pn_function.add(record());
			}
			else if(e.getActionCommand().equals("修改密码")) {
				lb_topFunction.setText("[修改密码]");
				pn_function.add(changePassword());
			}
			userJFrame.validate();
		}
	}
	
	public JPanel info() { //[个人信息]功能
		JPanel pn_info=new JPanel();
		pn_info.setLayout(null);
		pn_info.setSize(1045,735);
		pn_info.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(700,130,200,200);
		JLabel[] lb=new JLabel[10];
		for(int i=0;i<10;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("黑体",0,30));
			lb[i].setBounds(100,72+i*60,500,50);
			pn_info.add(lb[i]);
		}
		lb[0].setText("账户状态： 正常");
		lb[1].setText("卡    号： "+Login.user.account);
		lb[2].setText("姓    名： "+Login.user.name);
		lb[3].setText("性    别： "+Login.user.sex);
		lb[4].setText("身 份 证： "+Login.user.idCard);
		lb[5].setText("手    机： "+Login.user.phone);
		lb[6].setText("信 用 分： "+Login.user.credit);
		lb[7].setText("余    额： "+Login.user.money);
		lb[8].setText("欠    款： "+Login.user.debt);
		lb[9].setText("建卡日期： "+Login.user.time);
		pn_info.add(lb_infoImage);
		return pn_info;
	}
	
	public JTabbedPane cunqu() { //[存款/取款]功能
		JLabel lb_cun=new JLabel("存款金额："),lb_qu=new JLabel("取款金额："),
				lb_money1=new JLabel("余额："+Login.user.money),lb1=new JLabel("（输入100的倍数）"),
				lb_money2=new JLabel("余额："+Login.user.money),lb2=new JLabel("（输入100的倍数，每次取款不得超过5000元）");
		lb_cun.setFont(new Font("黑体",0,25)); lb_cun.setBounds(275,260,125,50);
		lb_qu.setFont(new Font("黑体",0,25)); lb_qu.setBounds(275,260,125,50);
		lb_money1.setFont(new Font("黑体",0,20)); lb_money1.setBounds(10,0,200,50);
		lb_money2.setFont(new Font("黑体",0,20)); lb_money2.setBounds(10,0,200,50);
		lb1.setFont(new Font("黑体",0,18)); lb1.setBounds(650,260,200,50);
		lb2.setFont(new Font("黑体",0,18)); lb2.setBounds(650,260,400,50);
		JTextField tf_cun=new JTextField(),tf_qu=new JTextField();
		tf_cun.setFont(new Font(null,0,25)); tf_cun.setBounds(400,265,250,40);
		tf_cun.setDocument(new NumLimit());
		tf_qu.setFont(new Font(null,0,25)); tf_qu.setBounds(400,265,250,40);
		tf_qu.setDocument(new NumLimit());
		JButton bt_cun=new JButton("存款"),bt_qu=new JButton("取款");
		bt_cun.setFont(new Font("黑体",0,20)); bt_cun.setBounds(472,345,100,50);
		bt_cun.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_qu.setFont(new Font("黑体",0,20)); bt_qu.setBounds(472,345,100,50);
		bt_qu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JPanel pn_cun=new JPanel(),pn_qu=new JPanel();
		pn_cun.setLayout(null);
		pn_cun.add(lb_money1); pn_cun.add(lb_cun); pn_cun.add(lb1); pn_cun.add(tf_cun); pn_cun.add(bt_cun);
		pn_qu.setLayout(null);
		pn_qu.add(lb_money2); pn_qu.add(lb_qu); pn_qu.add(lb2); pn_qu.add(tf_qu); pn_qu.add(bt_qu);
		JTabbedPane tp_cunqu=new JTabbedPane();
		tp_cunqu.setFont(new Font("黑体",0,25));
		tp_cunqu.setBounds(0,0,1045,735);
		tp_cunqu.addTab(" 存款 ",pn_cun);
		tp_cunqu.addTab(" 取款 ",pn_qu);
		tp_cunqu.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	tf_cun.setText("");
            	tf_qu.setText("");
            }
        });
		bt_cun.addActionListener(new ActionListener() { //存款
			public void actionPerformed(ActionEvent e) {
				money=tf_cun.getText();
				if(money.equals("")) { //若未输入存款金额，将进行提示
					lb_tips.setText("请输入存款金额！");
					functionTips();
				}
				else if(money.substring(0,1).equals("0")||Double.parseDouble(money)%100!=0) { //已输入金额，若输入的金额格式错误，将进行提示
					lb_tips.setText("存款金额请输入100的倍数！");
					functionTips();
				}
				else { //符合存款条件，进行存款提示
					lb_tips.setText("是否确定存款？");
					choiceTips();
				}
			}
		});
		bt_qu.addActionListener(new ActionListener() { //取款
			public void actionPerformed(ActionEvent e) {
				money=tf_qu.getText();
				if(money.equals("")) { //若未输入取款金额，将进行提示
					lb_tips.setText("请输入取款金额！");
					functionTips();
				}
				else if(money.substring(0,1).equals("0")||Double.parseDouble(money)%100!=0) { //已输入金额，若输入的金额格式错误，将进行提示
					lb_tips.setText("取款金额请输入100的倍数！");
					functionTips();
				}
				else if(Double.parseDouble(money)>5000) { //输入的金额格式正确，但金额超过5000，进行提示
					lb_tips.setText("每次取款不得超过5000元！");
					functionTips();
				}
				else if(Double.parseDouble(money)>Double.parseDouble(Login.user.money)) { //超过账户余额，进行提示
					lb_tips.setText("余额不足！");
					functionTips();
				}
				else { //符合取款条件，进行取款提示
					lb_tips.setText("是否确定取款？");
					choiceTips();
				}
			}
		});
		return tp_cunqu;
	}
	
	public JPanel transfer() { //[转账]功能
		JPanel pn_transfer=new JPanel();
		pn_transfer.setLayout(null);
		pn_transfer.setSize(1045,735);
		pn_transfer.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_account=new JLabel("收账人卡号："),lb_money=new JLabel("转账金额："),
				lb0=new JLabel("余额："+Login.user.money),lb1=new JLabel("今日转账限额："+Login.user.transferNum),lb2=new JLabel("（输入100的倍数）");
		JTextField tf_account=new JTextField(),tf_money=new JTextField();
		lb_account.setFont(new Font("黑体",0,25));
		lb_account.setBounds(250,260,150,50);
		lb_money.setFont(new Font("黑体",0,25));
		lb_money.setBounds(275,320,125,50);
		lb0.setFont(new Font("黑体",0,20));
		lb0.setBounds(10,0,200,50);
		lb1.setFont(new Font("黑体",0,20));
		lb1.setBounds(840,0,200,50);
		lb2.setFont(new Font("黑体",0,18));
		lb2.setBounds(650,320,200,50);
		tf_account.setFont(new Font(null,0,25));
		tf_account.setBounds(400,265,250,40);
		tf_account.setDocument(new NumLimit());
		tf_money.setFont(new Font(null,0,25));
		tf_money.setBounds(400,325,250,40);
		tf_money.setDocument(new NumLimit());
		JButton bt_transfer=new JButton("转账");
		bt_transfer.setFont(new Font("黑体",0,20));
		bt_transfer.setBounds(472,400,100,50);
		bt_transfer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_transfer.add(lb_account);pn_transfer.add(lb_money);pn_transfer.add(lb0);pn_transfer.add(lb1);pn_transfer.add(lb2);
		pn_transfer.add(tf_account);pn_transfer.add(tf_money);pn_transfer.add(bt_transfer);
		bt_transfer.addActionListener(new ActionListener() { //转账
			public void actionPerformed(ActionEvent e) {
				if(tf_account.getText().equals("")) { //为输入收账人卡号，进行提示
					lb_tips.setText("请输入收账人卡号！");
					functionTips();
				}
				else if(tf_money.getText().equals("")) { //未输入转账金额，进行提示
					lb_tips.setText("请输入转账金额！");
					functionTips();
				}
				else { //已输入卡号和金额
					try {
						String account=tf_account.getText();
						money=tf_money.getText();
						ObjectInputStream ois=new ObjectInputStream(new FileInputStream("用户信息.txt"));
						ArrayList<User> users=(ArrayList<User>)ois.readObject();
						ois.close();
						for(payeeNum=0;payeeNum<users.size();payeeNum++) { //查询相应卡号是否存在
							if(account.equals(users.get(payeeNum).account)) {
								payee=users.get(payeeNum);
								break;
							}
						}
						if(payeeNum==users.size()) { //输入的卡号错误，进行提示
							lb_tips.setText("卡号错误！");
							functionTips();
						}
						else { //输入的卡号正确
							if(account.equals(Login.user.account)) { //输入的卡号为自己的卡号，进行提示
								lb_tips.setText("请勿输入自己的卡号！");
								functionTips();
							}
							else { //输入的卡号非自己的
								if(money.substring(0,1).equals("0")||Double.parseDouble(money)%100!=0) { //输入的转账金额格式错误，进行提示
									lb_tips.setText("转账金额请输入100的倍数！");
									functionTips();
								}
								else { //卡号和金额正确
									if(Double.parseDouble(money)>Login.user.transferNum) { //超过今日转账限额，进行提示
										lb_tips.setText("超过今日转账限额！");
										functionTips();
									}
									else {
										if(Double.parseDouble(money)>Double.parseDouble(Login.user.money)) { //超过账户余额，进行提示
											lb_tips.setText("余额不足！");
											functionTips();
										}
										else { //符合转账条件
											lb_tips.setText("是否确定向对方转账？");
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
	
	public JTabbedPane daihuan() { //[贷款/还款]功能
		JLabel lb_dai=new JLabel("贷款金额："),lb_huan=new JLabel("还款金额："),
				lb_notdai=new JLabel(),lb_nothuan=new JLabel(),
				lb_money1=new JLabel("<html>余额："+Login.user.money+"<br>欠款："+Login.user.debt+"</html>"),lb11=new JLabel("（输入100的倍数）"),
				lb_money2=new JLabel("<html>余额："+Login.user.money+"<br>欠款："+Login.user.debt+"</html>"),lb21=new JLabel("（输入欠款金额）"),
				lb12=new JLabel("<html>信用："+Login.user.credit+"<br>额度："+Login.user.credit*1000+"</html>"),lb22=new JLabel("<html>信用："+Login.user.credit+"<br>额度："+Login.user.credit*1000+"</html>"),
				lb_daiTips=new JLabel("<html>贷款还款规则：<br>本系统仅当用户信用分≥80且未欠款时，才可进行贷款；<br>贷款额度为用户信用分*1000；<br>贷款利息按月(30天)计算，月利率为1.5%；<br>自贷款日起每30天未还款，信用分-1；<br>还款须一次还清，还款信用分+1。</html>"),
				lb_huanTips=new JLabel("<html>贷款还款规则：<br>本系统仅当用户信用分≥80且未欠款时，才可进行贷款；<br>贷款额度为用户信用分*1000；<br>贷款利息按月(30天)计算，月利率为1.5%；<br>自贷款日起每30天未还款，信用分-1；<br>还款须一次还清，还款信用分+1。</html>");
		lb_dai.setFont(new Font("黑体",0,25)); lb_dai.setBounds(275,260,125,50);
		lb_huan.setFont(new Font("黑体",0,25)); lb_huan.setBounds(275,260,125,50);
		lb_notdai.setFont(new Font("黑体",0,25));lb_notdai.setBounds(465,200,125,40);lb_notdai.setForeground(Color.red);
		lb_nothuan.setFont(new Font("黑体",0,25));lb_nothuan.setBounds(465,200,125,40);lb_nothuan.setForeground(Color.red);
		lb_money1.setFont(new Font("黑体",0,20)); lb_money1.setBounds(10,9,200,50);
		lb_money2.setFont(new Font("黑体",0,20)); lb_money2.setBounds(10,9,200,50);
		lb_daiTips.setFont(new Font("黑体",0,20)); lb_daiTips.setBounds(300,400,500,250);
		lb_huanTips.setFont(new Font("黑体",0,20)); lb_huanTips.setBounds(300,400,500,250);
		lb11.setFont(new Font("黑体",0,18)); lb11.setBounds(650,260,200,50);
		lb21.setFont(new Font("黑体",0,18)); lb21.setBounds(650,260,400,50);
		lb12.setFont(new Font("黑体",0,20)); lb12.setBounds(900,9,200,50);
		lb22.setFont(new Font("黑体",0,20)); lb22.setBounds(900,9,200,50);
		JTextField tf_dai=new JTextField(),tf_huan=new JTextField();
		tf_dai.setFont(new Font(null,0,25)); tf_dai.setBounds(400,265,250,40);
		tf_dai.setDocument(new NumLimit());
		tf_huan.setFont(new Font(null,0,25)); tf_huan.setBounds(400,265,250,40);
		JButton bt_dai=new JButton("贷款"),bt_huan=new JButton("还款");
		bt_dai.setFont(new Font("黑体",0,20)); bt_dai.setBounds(472,345,100,50);
		bt_dai.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_huan.setFont(new Font("黑体",0,20)); bt_huan.setBounds(472,345,100,50);
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
		tp_daihuan.setFont(new Font("黑体",0,25));
		tp_daihuan.setBounds(0,0,1045,735);
		tp_daihuan.addTab(" 贷款 ",pn_dai);
		tp_daihuan.addTab(" 还款 ",pn_huan);
		if(Login.user.credit<80) { //信用分不足80，禁止贷款
			lb_notdai.setText("信用不足！");
			pn_dai.add(lb_notdai);
			tf_dai.setEditable(false);
			bt_dai.setEnabled(false);
		}
		else if(Double.parseDouble(Login.user.debt)!=0) { //欠款中，禁止贷款
			lb_notdai.setText("请先还款！");
			pn_dai.add(lb_notdai);
			tf_dai.setEditable(false);
			bt_dai.setEnabled(false);
		}
		if(Double.parseDouble(Login.user.debt)==0) { //未欠款，无需还款
			lb_nothuan.setText("无需还款！");
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
		bt_dai.addActionListener(new ActionListener() { //贷款
			public void actionPerformed(ActionEvent e) {
				money=tf_dai.getText();
				if(money.equals("")) { //若未输入贷款金额，将进行提示
					lb_tips.setText("请输入贷款金额！");
					functionTips();
				}
				else if(money.substring(0,1).equals("0")||Double.parseDouble(money)%100!=0) { //已输入金额，若输入的金额格式错误，将进行提示
					lb_tips.setText("贷款金额请输入100的倍数！");
					functionTips();
				}
				else if(Double.parseDouble(money)>Login.user.credit*1000) { //已输入金额，若输入的金额大于贷款额度，将进行提示
					lb_tips.setText("超过贷款额度！");
					functionTips();
				}
				else { //符合贷款条件，进行贷款提示
					lb_tips.setText("是否确定贷款？");
					choiceTips();
				}
			}
		});
		bt_huan.addActionListener(new ActionListener() { //还款
			public void actionPerformed(ActionEvent e) {
				money=tf_huan.getText();
				if(money.equals("")) { //若未输入还款金额，将进行提示
					lb_tips.setText("请输入还款金额！");
					functionTips();
				}
				else if(!Login.user.debt.equals(money)) { //已输入金额，若输入的金额格式错误，将进行提示
					lb_tips.setText("还款数额输入错误，请输入欠款金额！");
					functionTips();
				}
				else if(Double.parseDouble(money)>Double.parseDouble(Login.user.money)) { //超过账户余额，进行提示
					lb_tips.setText("余额不足！");
					functionTips();
				}
				else { //符合还款条件，进行还款提示
					lb_tips.setText("是否确定还款？");
					choiceTips();
				}
			}
		});
		return tp_daihuan;
	}
	
	public JPanel record() { //[交易记录]功能
		//获取交易记录数据
		int n; //表示交易次数
		String[][] recordData;
		if(Login.user.record.isEmpty()) { //无交易记录
			n=0;
			recordData=new String[1][5];
			for(int i=0;i<5;i++)
				recordData[0][i]="无";
		}
		else { //有交易记录
			n=Login.user.record.size()/3;
			recordData=new String[n][5];
			for(int i=0,j=0;i<Login.user.record.size();i+=3,j++) { //格式化每条交易记录
				recordData[j][0]=Login.user.account; //账户
				recordData[j][1]=Login.user.record.get(i); //明细
				recordData[j][2]=Login.user.record.get(i+1); //时间
				recordData[j][3]=Login.user.record.get(i+2); //余额
				recordData[j][4]=Login.user.name; //操作人
			}
		}
		//界面
		JPanel pn_record=new JPanel();
		pn_record.setLayout(null);
		pn_record.setSize(1045,735);
		pn_record.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_export=new JButton("导出");
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setBounds(945,0,100,50);
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JTable table=new JTable(new MyTableModel(recordData));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",0,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(1,50,1044,685);
		pn_record.add(bt_export);
		pn_record.add(scrollPane);
		bt_export.addActionListener(new ActionListener() { //将交易记录导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(userJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		return pn_record;
	}
	
	public JPanel changePassword() { //[修改密码]功能
		JPanel pn_changePassword=new JPanel();
		pn_changePassword.setLayout(null);
		pn_changePassword.setSize(1045,735);
		pn_changePassword.setBorder(BorderFactory.createEtchedBorder());
		JLabel lb_old=new JLabel("旧密码："),lb_new1=new JLabel("新密码："),lb_new2=new JLabel("确认密码："),lb=new JLabel("（设置6位数字密码）");
		JPasswordField pf_old=new JPasswordField(),pf_new1=new JPasswordField(),pf_new2=new JPasswordField();
		lb_old.setFont(new Font("黑体",0,25));
		lb_old.setBounds(300,200,100,50);
		lb_new1.setFont(new Font("黑体",0,25));
		lb_new1.setBounds(300,260,100,50);
		lb_new2.setFont(new Font("黑体",0,25));
		lb_new2.setBounds(275,320,125,50);
		lb.setFont(new Font("黑体",0,18));
		lb.setBounds(650,260,200,50);
		pf_old.setFont(new Font(null,0,30));
		pf_old.setBounds(400,205,250,40);
		pf_new1.setFont(new Font(null,0,30));
		pf_new1.setBounds(400,265,250,40);
		pf_new2.setFont(new Font(null,0,30));
		pf_new2.setBounds(400,325,250,40);
		pn_changePassword.add(lb_old);pn_changePassword.add(lb_new1);pn_changePassword.add(lb_new2);pn_changePassword.add(lb);
		pn_changePassword.add(pf_old);pn_changePassword.add(pf_new1);pn_changePassword.add(pf_new2);
		JButton bt_confirm=new JButton("确认"),bt_reset=new JButton("重置");
		bt_confirm.setFont(new Font("黑体",0,20));
		bt_confirm.setBounds(405,410,100,50);
		bt_confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_reset.setFont(new Font("黑体",0,20));
		bt_reset.setBounds(540,410,100,50);
		bt_reset.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_changePassword.add(bt_confirm);pn_changePassword.add(bt_reset);
		bt_reset.addActionListener(new ActionListener() { //重置，将密码框内容清空
			public void actionPerformed(ActionEvent e) {
				pf_old.setText("");
				pf_new1.setText("");
				pf_new2.setText("");
			}
		});
		bt_confirm.addActionListener(new ActionListener() { //确认（参考了注册时的算法）
			public void actionPerformed(ActionEvent e) {
				boolean y1,y2=false,y3,y4,y5,y6=false;
				//y1表示是否全部填写，y2表示密码格式是否正确，y3表示两次密码是否相同，y4表示密码是否为数字，y5表示密码是否为6位，y6表示六位密码是否不完全相同
				y1=(!String.valueOf(pf_old.getPassword()).equals("")) //判断信息是否全部填写
						&&(!String.valueOf(pf_new1.getPassword()).equals(""))
						&&(!String.valueOf(pf_new2.getPassword()).equals(""));
				if(!y1) { //若未完整填写信息，将进行提示
					lb_tips.setText("请填写完整！");
					functionTips();
				}
				else { //若完整填写信息，将进行密码判断
					if(!String.valueOf(pf_old.getPassword()).equals(Login.user.password)) { //若输入的旧密码错误，将进行提示
						lb_tips.setText("旧密码错误！");
						functionTips();
					}
					else { //若输入的旧密码正确，将进行密码格式判断
						String password1=String.valueOf(pf_new1.getPassword()),password2=String.valueOf(pf_new2.getPassword());
						if(password1.equals(password2)) { //判断两次密码是否相同
							y3=true;
							if(password1.length()==6) { //判断密码是否为6位
								y5=true;
								y4=true;
								int[] chr=new int[6]; //用于储存各位密码的值
								for(int i=0;i<password1.length();i++) { //判断密码是否为数字
									chr[i]=password1.charAt(i);
									if(chr[i]<48||chr[i]>57) { //若密码不为数字，将进行提示
										y4=false;
										lb_tips.setText("请设置6位数字密码！");
										functionTips();
										break;
									}
									if(i==5) { //若密码为6位数字密码，则将进行6位密码是否完全相同的判断
										int x=1;
										for(x=1;x<6;x++) {
											if(chr[x-1]==chr[x])
												continue;
											else
												break;
										}
										if(x==6) {  //若6位密码完全相同，将进行提示
											y6=false;
											lb_tips.setText("请勿设置6位完全相同的密码！");
											functionTips();
										}
										else
											y6=true;
									}
								}
								y2=y3&&y4&&y5&&y6; //若密码为非完全相同的6位数字密码，则密码格式正确y2为true，否则y2为false
							}
							else { //若密码不为6位，将进行提示
								y5=false;
								lb_tips.setText("请设置6位数字密码！");
								functionTips();
							}
						}
						else { //若两次密码不相同，将进行提示
							y3=false;
							lb_tips.setText("两次密码不同！");
							functionTips();
						}
						if(y2) { //符合改密条件，则进行改密
							try {
								password1=String.valueOf(pf_new1.getPassword());
								Login.user.password=password1;
								ObjectInputStream ois=new ObjectInputStream(new FileInputStream("用户信息.txt"));
								ArrayList<User> users=(ArrayList<User>)ois.readObject();
								ois.close();
								users.remove(Login.userNum);
								users.add(Login.userNum,Login.user);
								ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("用户信息.txt"));
								oos.writeObject(users);
								oos.close();
								pf_old.setText("");
								pf_new1.setText("");
								pf_new2.setText("");
								lb_tips.setText("密码修改成功！");
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
	
	public void functionTips() { //操作时弹出的提示信息窗口
		JDialog tips=new JDialog(userJFrame,"  提示",true);
		JPanel pn_tips=new JPanel();
		JButton bt_tips=new JButton("确定");
		tips.setSize(500,200);
		tips.setLocationRelativeTo(null);
		tips.setResizable(false);
		tips.setLayout(null);
		pn_tips.setBounds(0,30,500,70);
		lb_tips.setFont(new Font("黑体",0,25));
		bt_tips.setFont(new Font("黑体",0,20));
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
	
	public void choiceTips() { //操作时弹出的提示选择窗口
		JDialog choiceTips=new JDialog(userJFrame,"  提示",true);
		JPanel pn_tips=new JPanel();
		JButton bt_yes=new JButton("是(Y)");
		JButton bt_no=new JButton("否(N)");
		choiceTips.setSize(500,200);
		choiceTips.setLocationRelativeTo(null);
		choiceTips.setResizable(false);
		choiceTips.setLayout(null);
		pn_tips.setBounds(0,30,500,70);
		lb_tips.setFont(new Font("黑体",0,25));
		bt_yes.setFont(new Font("黑体",0,20));
		bt_yes.setBounds(135,100,100,50);
		bt_yes.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_no.setFont(new Font("黑体",0,20));
		bt_no.setBounds(260,100,100,50);
		bt_no.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_tips.add(lb_tips);
		choiceTips.add(pn_tips);
		choiceTips.add(bt_yes);
		choiceTips.add(bt_no);
		bt_yes.addActionListener(new ActionListener() { //选择“是”，进行相应操作
			public void actionPerformed(ActionEvent e) {
				choiceTips.dispose();
				yesOperation();
			}
		});
		bt_no.addActionListener(new ActionListener() { //选择“否”，关闭提示选择窗口
			public void actionPerformed(ActionEvent e) {
				choiceTips.dispose();
			}
		});
		bt_yes.addKeyListener(new KeyListener() { //“是”按钮的快捷键“Y”，“否”按钮的快捷键“N”
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
		bt_no.addKeyListener(new KeyListener() { //“是”按钮的快捷键“Y”，“否”按钮的快捷键“N”
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
	
	public void yesOperation() { //选择“是”之后，根据提示内容，选择要进行的操作
		if(lb_tips.getText().equals("是否退出用户模式？")) { //确定退出用户模式，返回到欢迎界面
			userJFrame.dispose();
			HomePage.con.removeAll();
			HomePage.mainJFrame.repaint();
			HomePage.welcomePage();
			HomePage.mainJFrame.validate();
		}
		if(lb_tips.getText().equals("是否确定向对方转账？")) { //确定进行转账，修改用户相关信息
			SimpleDateFormat timeFormat1=new SimpleDateFormat("yyyy-MM-dd"),timeFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			payee.money=String.valueOf(Double.parseDouble(payee.money)+Double.parseDouble(money)); //收账
			Login.user.money=String.valueOf(Double.parseDouble(Login.user.money)-Double.parseDouble(money)); //转账
			Login.user.transferNum=(int)(Login.user.transferNum-Double.parseDouble(money)); //转账方今日限额减少
			Login.user.transferTime.add(timeFormat1.format(System.currentTimeMillis())); //记录转账时间
			//记录交易记录
			Login.user.record.add("转账  "+money);
			Login.user.record.add(timeFormat2.format(System.currentTimeMillis()));
			Login.user.record.add(Login.user.money);
			payee.record.add("收账  "+money);
			payee.record.add(timeFormat2.format(System.currentTimeMillis()));
			payee.record.add(payee.money);
			try {
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream("用户信息.txt"));
				ArrayList<User> users=(ArrayList<User>)ois.readObject();
				ois.close();
				users.remove(Login.userNum);
				users.add(Login.userNum,Login.user);
				users.remove(payeeNum);
				users.add(payeeNum,payee);
				ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("用户信息.txt"));
				oos.writeObject(users);
				oos.close();
				lb_tips.setText("转账成功！");
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
		if(lb_tips.getText().equals("是否确定存款？")||lb_tips.getText().equals("是否确定取款？")) { //确定进行存款/取款，修改用户相关信息
			SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(lb_tips.getText().equals("是否确定存款？")) { //存款
				Login.user.money=String.valueOf(Double.parseDouble(Login.user.money)+Double.parseDouble(money)); //存款，余额增加
				//记录交易记录
				Login.user.record.add("存款  "+money);
				Login.user.record.add(timeFormat.format(System.currentTimeMillis()));
				Login.user.record.add(Login.user.money);
				lb_tips.setText("存款成功！");
			}
			else { //取款
				Login.user.money=String.valueOf(Double.parseDouble(Login.user.money)-Double.parseDouble(money)); //取款，余额减少
				//记录交易记录
				Login.user.record.add("取款  "+money);
				Login.user.record.add(timeFormat.format(System.currentTimeMillis()));
				Login.user.record.add(Login.user.money);
				lb_tips.setText("取款成功！");
			}
			try {
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream("用户信息.txt"));
				ArrayList<User> users=(ArrayList<User>)ois.readObject();
				ois.close();
				users.remove(Login.userNum);
				users.add(Login.userNum,Login.user);
				ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("用户信息.txt"));
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
		if(lb_tips.getText().equals("是否确定贷款？")||lb_tips.getText().equals("是否确定还款？")) { //确定进行贷款/还款，修改用户相关信息
			SimpleDateFormat timeFormat1=new SimpleDateFormat("yyyy-MM-dd"),timeFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(lb_tips.getText().equals("是否确定贷款？")) { //贷款
				Login.user.money=String.valueOf(Double.parseDouble(Login.user.money)+Double.parseDouble(money)); //贷款，余额增加
				Login.user.debt=money; //贷款，设置欠款金额
				Login.user.loanTime.add(timeFormat1.format(System.currentTimeMillis())); //记录贷款时间
				Login.user.dai.add(String.valueOf(Login.user.credit));Login.user.dai.add(Login.user.debt); //贷款时信用分和欠款记录
				//记录交易记录
				Login.user.record.add("贷款  "+money);
				Login.user.record.add(timeFormat2.format(System.currentTimeMillis()));
				Login.user.record.add(Login.user.money);
				lb_tips.setText("贷款成功！");
			}
			else { //还款
				Login.user.money=String.valueOf(Double.parseDouble(Login.user.money)-Double.parseDouble(money)); //还款，余额减少
				Login.user.debt="0"; //还款，欠款金额置0
				if(Login.user.credit<100) //信用分+1
					Login.user.credit++;
				//记录交易记录
				Login.user.record.add("还款  "+money);
				Login.user.record.add(timeFormat2.format(System.currentTimeMillis()));
				Login.user.record.add(Login.user.money);
				lb_tips.setText("还款成功！");
			}
			try {
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream("用户信息.txt"));
				ArrayList<User> users=(ArrayList<User>)ois.readObject();
				ois.close();
				users.remove(Login.userNum);
				users.add(Login.userNum,Login.user);
				ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("用户信息.txt"));
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