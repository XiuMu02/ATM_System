package atm;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class AdminFunction { //管理员功能类
	JFrame adminJFrame=new JFrame("ATM柜员机线上系统-----[系统管理模式]");
	Container adminCon=adminJFrame.getContentPane();
	JPanel pn_function=new JPanel(); //放置各种功能页面的容器
	JLabel lb_tips=new JLabel(); //提示窗口的内容
	JPanel pn_singleRecords=new JPanel(); //[历史记录]-[个人记录]界面
	JPanel pn_people=new JPanel(); //[历史记录]-[个人记录]界面
	public AdminFunction() { //整体界面
		adminJFrame.setSize(1300,800);
		adminJFrame.setLocationRelativeTo(null);
		adminJFrame.setResizable(false);
		adminJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminCon.setLayout(null);
		pn_function.setLayout(null);
		pn_function.setBorder(BorderFactory.createEtchedBorder());
		pn_function.setBounds(250,30,1045,735);
		JPanel pn1=new JPanel(); //顶部信息栏，操作者
		pn1.setBackground(Color.white);
		pn1.setBounds(0,0,250,30);
		JLabel lb1=new JLabel("管理员：Admin");
		lb1.setFont(new Font("黑体",0,18));
		lb1.setForeground(Color.blue);
		pn1.add(lb1);
		JPanel pn2=new JPanel(); //顶部信息栏，当前功能
		pn2.setBackground(Color.white);
		pn2.setBounds(250,0,1045,30);
		JLabel lb2=new JLabel("");
		lb2.setFont(new Font("黑体",0,20));
		lb2.setForeground(Color.black);
		pn2.add(lb2);
		JPanel pn_menu=new JPanel(); //菜单，进行功能选择
		pn_menu.setBackground(new Color(249,250,252));
		pn_menu.setBorder(BorderFactory.createEtchedBorder());
		pn_menu.setLayout(null);
		pn_menu.setBounds(0,30,250,735);
		JButton bt1=new JButton("用户管理"); //[用户管理]
		bt1.setFont(new Font("黑体",0,20));
		bt1.setContentAreaFilled(false);
		bt1.setBounds(0,50,249,50);
		JButton bt2=new JButton("历史记录"); //[历史记录]
		bt2.setFont(new Font("黑体",0,20));
		bt2.setContentAreaFilled(false);
		bt2.setBounds(0,105,249,50);
		JButton bt3=new JButton("退出"); //[退出]
		bt3.setFont(new Font("黑体",0,20));
		bt3.setContentAreaFilled(false);
		bt3.setBounds(0,160,249,50);
		pn_menu.add(bt1);
		pn_menu.add(bt2);
		pn_menu.add(bt3);
		JPanel pn_welcome=new JPanel(); //欢迎页
		pn_welcome.setBorder(BorderFactory.createEtchedBorder());
		pn_welcome.setLayout(new BorderLayout());
		pn_welcome.setBounds(0,0,1045,735);
		JLabel lb_welcome=new JLabel("欢迎使用");
		lb_welcome.setFont(new Font("黑体",0,100));
		lb_welcome.setHorizontalAlignment(SwingConstants.CENTER);
		pn_welcome.add(lb_welcome,BorderLayout.CENTER);
		adminCon.add(pn1);
		adminCon.add(pn2);
		adminCon.add(pn_menu);
		adminCon.add(pn_function);
		pn_function.add(pn_welcome);
		adminJFrame.setVisible(true);
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //进行用户管理
				lb2.setText("[用户管理]");
				pn_function.removeAll();
				adminJFrame.repaint();
				pn_function.add(manage());
				adminJFrame.validate();
			}
		});
		bt2.addActionListener(new ActionListener() { //查看历史记录
			public void actionPerformed(ActionEvent e) {
				lb2.setText("[历史记录]");
				pn_function.removeAll();
				adminJFrame.repaint();
				pn_function.add(records());
				adminJFrame.validate();
			}
		});
		bt3.addActionListener(new ActionListener() { //退出系统管理模式
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("是否退出系统管理模式？");
				choiceTips();
			}
		});
	}
	
	public JPanel manage() { //[用户管理]功能
		String[] columnNames={"账户","姓名","性别","账户状态","操作"};
		User user=null;
		int n=Login.allUsers.size(); //表示用户个数
		String[][] rowData=new String[n][5];
		for(int i=0;i<Login.allUsers.size();i++) { //获取各用户的信息
			user=Login.allUsers.get(i);
			rowData[i][0]=user.account; //账户
			rowData[i][1]=user.name; //姓名
			rowData[i][2]=user.sex; //性别
			if(user.lock) //账户状态
				rowData[i][3]="<html><font color='red'>锁定</font></html>";
			else
				rowData[i][3]="正常";
		}
		//界面
		JPanel pn_manage=new JPanel();
		pn_manage.setLayout(null);
		pn_manage.setSize(1045,735);
		pn_manage.setBorder(BorderFactory.createEtchedBorder());
		JTable table=new JTable(new MyTableModel(columnNames,rowData));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",0,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		MyEvent e=new MyEvent() { //点击“查看”按钮，查看用户信息，可修改用户信息
            public void invoke(ActionEvent e) {
                MyButton button=(MyButton)e.getSource();
                visitInfo(button.getRow());
            }
        };
		table.getColumnModel().getColumn(4).setCellRenderer(new MyButtonRender()); //设置表格的渲染器
		MyButtonEditor editor=new MyButtonEditor(e);
		table.getColumnModel().getColumn(4).setCellEditor(editor); //设置表格的编辑器
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(1,0,1045,735);
		pn_manage.add(scrollPane);
		return pn_manage;
	}
	
	public void visitInfo(int num) { //查看用户信息，可修改用户信息
		User user=Login.allUsers.get(num);
		JPanel pn_info=new JPanel();
		pn_info.setLayout(null);
		pn_info.setSize(1045,735);
		pn_info.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_modify=new JButton("修改用户信息",new ImageIcon("image/modify.png")),
				bt_save=new JButton("保存",new ImageIcon("image/save.png")),bt_cancel=new JButton("取消",new ImageIcon("image/cancel.png"));
		bt_modify.setFont(new Font("黑体",0,17));
		bt_modify.setBounds(765,345,170,40);
		bt_modify.setContentAreaFilled(false);
		bt_modify.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_save.setFont(new Font("黑体",0,17));
		bt_save.setBounds(755,345,95,40);
		bt_save.setContentAreaFilled(false);
		bt_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_cancel.setFont(new Font("黑体",0,17));
		bt_cancel.setBounds(855,345,95,40);
		bt_cancel.setContentAreaFilled(false);
		bt_cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JToggleButton tbt_lock=new JToggleButton();
		tbt_lock.setBounds(655,50,65,40);
		tbt_lock.setBorderPainted(false);
		tbt_lock.setContentAreaFilled(false);
		tbt_lock.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tbt_lock.setIcon(new ImageIcon("image/解锁.png"));
		tbt_lock.setSelectedIcon(new ImageIcon("image/锁定.png"));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(750,130,200,200);
		JLabel[] lb=new JLabel[12];
		for(int i=0;i<12;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("黑体",0,25));
			lb[i].setBounds(250,45+i*55,130,50);
			pn_info.add(lb[i]);
		}
		lb[0].setText("账户状态：");
		lb[1].setText("锁定天数：");
		lb[2].setText("卡    号：");
		lb[3].setText("密    码：");
		lb[4].setText("姓    名：");
		lb[5].setText("性    别：");
		lb[6].setText("身 份 证：");
		lb[7].setText("手    机：");
		lb[8].setText("信 用 分：");
		lb[9].setText("余    额：");
		lb[10].setText("欠    款：");
		lb[11].setText("建卡日期：");
		JTextField[] tf=new JTextField[12];
		for(int i=0;i<12;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("黑体",0,25));
			tf[i].setBounds(385,50+i*55,260,40);
			tf[i].setEditable(false);
			pn_info.add(tf[i]);
		}
		if(user.lock) {
			tbt_lock.setSelected(true); //选中为锁定
			tf[0].setText("锁定");
		}
		else {
			tbt_lock.setSelected(false); //为选中为正常
			tf[0].setText("正常");
		}
		tf[1].setText(String.valueOf(user.lockDays));
		tf[2].setText(user.account);
		tf[3].setText(user.password);
		tf[4].setText(user.name);
		tf[5].setText(user.sex);
		tf[6].setText(user.idCard);
		tf[7].setText(user.phone);
		tf[8].setText(String.valueOf(user.credit));
		tf[9].setText(user.money);
		tf[10].setText(user.debt);
		tf[11].setText(user.time);
		pn_info.add(bt_back);
		pn_info.add(bt_modify);
		pn_info.add(lb_infoImage);
		bt_back.addMouseListener(new MouseListener() { //返回到用户管理界面
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				pn_function.removeAll();
				adminJFrame.repaint();
				pn_function.add(manage());
				adminJFrame.validate();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		bt_modify.addActionListener(new ActionListener() { //修改用户信息
			public void actionPerformed(ActionEvent e) {
				for(int i=3;i<12;i++)
					tf[i].setEditable(true);
				pn_info.remove(bt_modify);
				adminJFrame.repaint();
				pn_info.add(bt_save);
				pn_info.add(bt_cancel);
				pn_info.add(tbt_lock);
				adminJFrame.validate();
			}
		});
		bt_save.addActionListener(new ActionListener() { //保存
			public void actionPerformed(ActionEvent e) {
				for(int i=3;i<12;i++)
					tf[i].setEditable(false);
				//修改用户信息
				if(tbt_lock.isSelected()) //锁定用户
					user.lock=true;
				else { //解锁用户
					user.lock=false;
					user.lockDays=0;
					user.errorNum=0;
					user.lockTime.clear();
					user.lockTime.add("0000-00-00");
					user.errorTime.clear();
					user.errorTime.add("0000-00-00");
					user.errorTime.add("0000-00-00");
					user.errorTime.add("0000-00-00");
				}
				user.password=tf[3].getText();
				user.name=tf[4].getText();
				user.sex=tf[5].getText();
				user.idCard=tf[6].getText();
				user.phone=tf[7].getText();
				user.credit=Integer.parseInt(tf[8].getText());
				user.money=tf[9].getText();
				user.debt=tf[10].getText();
				user.time=tf[11].getText();
				tf[1].setText(String.valueOf(user.lockDays));
				try { //保存用户信息
					Login.allUsers.remove(num);
					Login.allUsers.add(num,user);
					ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("用户信息.txt"));
					oos.writeObject(Login.allUsers);
					oos.close();
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				pn_info.remove(bt_save);
				pn_info.remove(bt_cancel);
				pn_info.remove(tbt_lock);
				adminJFrame.repaint();
				pn_info.add(bt_modify);
				adminJFrame.validate();
			}
		});
		bt_cancel.addActionListener(new ActionListener() { //取消
			public void actionPerformed(ActionEvent e) {
				for(int i=3;i<12;i++)
					tf[i].setEditable(false);
				if(user.lock) {
					tbt_lock.setSelected(true);
					tf[0].setText("锁定");
				}
				else {
					tbt_lock.setSelected(false);
					tf[0].setText("正常");
				}
				tf[1].setText(String.valueOf(user.lockDays));
				tf[2].setText(user.account);
				tf[3].setText(user.password);
				tf[4].setText(user.name);
				tf[5].setText(user.sex);
				tf[6].setText(user.idCard);
				tf[7].setText(user.phone);
				tf[8].setText(String.valueOf(user.credit));
				tf[9].setText(user.money);
				tf[10].setText(user.debt);
				tf[11].setText(user.time);
				pn_info.remove(bt_save);
				pn_info.remove(bt_cancel);
				pn_info.remove(tbt_lock);
				adminJFrame.repaint();
				pn_info.add(bt_modify);
				adminJFrame.validate();
			}
		});
		tbt_lock.addChangeListener(new ChangeListener() { //锁定、解锁
            public void stateChanged(ChangeEvent e) {
                if(tbt_lock.isSelected())
                	tf[0].setText("锁定");
                else
                	tf[0].setText("正常");
            }
        });
		pn_function.removeAll();
		adminJFrame.repaint();
		pn_function.add(pn_info);
		adminJFrame.validate();
	}
	
	public JTabbedPane records() { //[历史记录]功能
		JTabbedPane tp_records=new JTabbedPane();
		tp_records.setFont(new Font("黑体",0,25));
		tp_records.setBounds(0,0,1045,735);
		tp_records.addTab(" 所有记录 ",allRecords());
		singleRecords();
		tp_records.addTab(" 个人记录 ",pn_singleRecords);
		return tp_records;
	}
	
	public JPanel allRecords() { //所有记录
		//获取交易记录数据
		User user=null;
		int n=0; //表示交易次数
		String[][] recordData;
		for(int i=0;i<Login.allUsers.size();i++) { //计算历史交易记录次数
			user=Login.allUsers.get(i);
			n+=user.record.size()/3;
		}
		if(n==0) { //无历史交易记录
			recordData=new String[1][5];
			for(int i=0;i<5;i++)
				recordData[0][i]="无";
		}
		else { //有历史交易记录
			int num=0; //表示交易记录的序号
			recordData=new String[n][5];
			for(int i=0;i<Login.allUsers.size();i++) { //获取各用户的历史交易记录
				user=Login.allUsers.get(i);
				for(int j=0;j<user.record.size();j+=3,num++) { //格式化每条交易记录
					recordData[num][0]=user.account; //账户
					recordData[num][1]=user.record.get(j); //明细
					recordData[num][2]=user.record.get(j+1); //时间
					recordData[num][3]=user.record.get(j+2); //余额
					recordData[num][4]=user.name; //操作人
				}
			}
			SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				String[] re=new String[5];
				for(int i=0;i<n-1;i++) { //按时间排序
					for(int j=i+1;j<n;j++) {
						if(timeFormat.parse(recordData[i][2]).getTime()>timeFormat.parse(recordData[j][2]).getTime()) {
							re=recordData[i];
							recordData[i]=recordData[j];
							recordData[j]=re;
						}
					}
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		//界面
		JPanel pn_all=new JPanel(),pn_top=new JPanel();
		pn_all.setLayout(null);
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel("共"+n+"条历史记录！");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_export=new JButton("导出");
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(recordData));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",0,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_all.add(pn_top);
		pn_all.add(scrollPane);
		bt_export.addActionListener(new ActionListener() { //将交易记录导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(adminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		return pn_all;
	}
	
	public void singleRecords() { //个人记录
		String[] columnNames={"账户","姓名","性别","余额","查看个人记录"};
		User user=null;
		String[][] rowData=new String[Login.allUsers.size()][5];
		for(int i=0;i<Login.allUsers.size();i++) { //获取各用户的信息
			user=Login.allUsers.get(i);
			rowData[i][0]=user.account; //账户
			rowData[i][1]=user.name; //姓名
			rowData[i][2]=user.sex; //性别
			rowData[i][3]=user.money; //余额
		}
		//界面
		pn_singleRecords.setLayout(null);
		pn_people.setBounds(0,0,1045,695);
		pn_people.setLayout(null);
		JTable table=new JTable(new MyTableModel(columnNames,rowData));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",0,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		MyEvent e=new MyEvent() { //点击“查看”按钮，查看用户个人交易记录
            public void invoke(ActionEvent e) {
                MyButton button=(MyButton)e.getSource();
                visitRecords(button.getRow());
            }
        };
		table.getColumnModel().getColumn(4).setCellRenderer(new MyButtonRender()); //设置表格的渲染器
		MyButtonEditor editor=new MyButtonEditor(e);
		table.getColumnModel().getColumn(4).setCellEditor(editor); //设置表格的编辑器
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(1,0,1045,695);
		pn_people.add(scrollPane);
		pn_singleRecords.add(pn_people);
	}
	
	public void visitRecords(int num) { //查看用户个人交易记录
		//获取个人交易记录
		User user=Login.allUsers.get(num);
		int n; //表示交易次数
		String[][] recordData;
		if(Login.user.record.isEmpty()) { //无交易记录
			n=0;
			recordData=new String[1][5];
			for(int i=0;i<5;i++)
				recordData[0][i]="无";
		}
		else { //有交易记录
			n=user.record.size()/3;
			recordData=new String[n][5];
			for(int i=0,j=0;i<user.record.size();i+=3,j++) { //格式化每条交易记录
				recordData[j][0]=user.account; //账户
				recordData[j][1]=user.record.get(i); //明细
				recordData[j][2]=user.record.get(i+1); //时间
				recordData[j][3]=user.record.get(i+2); //余额
				recordData[j][4]=user.name; //操作人
			}
		}
		//界面
		JPanel pn_single=new JPanel(),pn_top=new JPanel();
		pn_single.setLayout(null);
		pn_single.setBounds(0,0,1045,695);
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel("共"+n+"条个人交易记录！");
		lb_num.setFont(new Font("黑体",0,25));
		JButton bt_export=new JButton("导出"),bt_back=new JButton("返回",new ImageIcon("image/返回.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("黑体",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_back.setFont(new Font("黑体",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(recordData));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //设置表头高度
		header.setFont(new Font("黑体",0,23));
		table.setRowHeight(35); //设置各行高度
		table.setFont(new Font("黑体",0,20));
		table.getTableHeader().setReorderingAllowed(false); //不允许移动各列
		JScrollPane scrollPane=new JScrollPane(table); //滚动条
		scrollPane.setBounds(0,50,1045,645);
		pn_single.add(pn_top);
		pn_single.add(scrollPane);
		bt_export.addActionListener(new ActionListener() { //将交易记录导出成Excel文件
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(adminJFrame,"请设置导出位置和文件名！",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		bt_back.addMouseListener(new MouseListener() { //返回
			public void mouseEntered(MouseEvent arg0) {
				bt_back.setForeground(Color.blue);
			}
			public void mouseExited(MouseEvent arg0) {
				bt_back.setForeground(null);
			}
			public void mouseClicked(MouseEvent arg0) {
				pn_singleRecords.removeAll();
				adminJFrame.repaint();
				pn_singleRecords.add(pn_people);
				adminJFrame.validate();
			}
			public void mousePressed(MouseEvent arg0){}
			public void mouseReleased(MouseEvent arg0){}
		});
		pn_singleRecords.removeAll();
		adminJFrame.repaint();
		pn_singleRecords.add(pn_single);
		adminJFrame.validate();
	}
	
	public void functionTips() { //操作时弹出的提示信息窗口
		JDialog tips=new JDialog(adminJFrame,"  提示",true);
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
		JDialog choiceTips=new JDialog(adminJFrame,"  提示",true);
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
		if(lb_tips.getText().equals("是否退出系统管理模式？")) { //退出系统管理模式，返回到欢迎界面
			adminJFrame.dispose();
			HomePage.con.removeAll();
			HomePage.mainJFrame.repaint();
			HomePage.welcomePage();
			HomePage.mainJFrame.validate();
		}
	}
}