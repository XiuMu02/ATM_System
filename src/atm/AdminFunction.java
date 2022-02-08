package atm;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class AdminFunction { //����Ա������
	JFrame adminJFrame=new JFrame("ATM��Ա������ϵͳ-----[ϵͳ����ģʽ]");
	Container adminCon=adminJFrame.getContentPane();
	JPanel pn_function=new JPanel(); //���ø��ֹ���ҳ�������
	JLabel lb_tips=new JLabel(); //��ʾ���ڵ�����
	JPanel pn_singleRecords=new JPanel(); //[��ʷ��¼]-[���˼�¼]����
	JPanel pn_people=new JPanel(); //[��ʷ��¼]-[���˼�¼]����
	public AdminFunction() { //�������
		adminJFrame.setSize(1300,800);
		adminJFrame.setLocationRelativeTo(null);
		adminJFrame.setResizable(false);
		adminJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminCon.setLayout(null);
		pn_function.setLayout(null);
		pn_function.setBorder(BorderFactory.createEtchedBorder());
		pn_function.setBounds(250,30,1045,735);
		JPanel pn1=new JPanel(); //������Ϣ����������
		pn1.setBackground(Color.white);
		pn1.setBounds(0,0,250,30);
		JLabel lb1=new JLabel("����Ա��Admin");
		lb1.setFont(new Font("����",0,18));
		lb1.setForeground(Color.blue);
		pn1.add(lb1);
		JPanel pn2=new JPanel(); //������Ϣ������ǰ����
		pn2.setBackground(Color.white);
		pn2.setBounds(250,0,1045,30);
		JLabel lb2=new JLabel("");
		lb2.setFont(new Font("����",0,20));
		lb2.setForeground(Color.black);
		pn2.add(lb2);
		JPanel pn_menu=new JPanel(); //�˵������й���ѡ��
		pn_menu.setBackground(new Color(249,250,252));
		pn_menu.setBorder(BorderFactory.createEtchedBorder());
		pn_menu.setLayout(null);
		pn_menu.setBounds(0,30,250,735);
		JButton bt1=new JButton("�û�����"); //[�û�����]
		bt1.setFont(new Font("����",0,20));
		bt1.setContentAreaFilled(false);
		bt1.setBounds(0,50,249,50);
		JButton bt2=new JButton("��ʷ��¼"); //[��ʷ��¼]
		bt2.setFont(new Font("����",0,20));
		bt2.setContentAreaFilled(false);
		bt2.setBounds(0,105,249,50);
		JButton bt3=new JButton("�˳�"); //[�˳�]
		bt3.setFont(new Font("����",0,20));
		bt3.setContentAreaFilled(false);
		bt3.setBounds(0,160,249,50);
		pn_menu.add(bt1);
		pn_menu.add(bt2);
		pn_menu.add(bt3);
		JPanel pn_welcome=new JPanel(); //��ӭҳ
		pn_welcome.setBorder(BorderFactory.createEtchedBorder());
		pn_welcome.setLayout(new BorderLayout());
		pn_welcome.setBounds(0,0,1045,735);
		JLabel lb_welcome=new JLabel("��ӭʹ��");
		lb_welcome.setFont(new Font("����",0,100));
		lb_welcome.setHorizontalAlignment(SwingConstants.CENTER);
		pn_welcome.add(lb_welcome,BorderLayout.CENTER);
		adminCon.add(pn1);
		adminCon.add(pn2);
		adminCon.add(pn_menu);
		adminCon.add(pn_function);
		pn_function.add(pn_welcome);
		adminJFrame.setVisible(true);
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //�����û�����
				lb2.setText("[�û�����]");
				pn_function.removeAll();
				adminJFrame.repaint();
				pn_function.add(manage());
				adminJFrame.validate();
			}
		});
		bt2.addActionListener(new ActionListener() { //�鿴��ʷ��¼
			public void actionPerformed(ActionEvent e) {
				lb2.setText("[��ʷ��¼]");
				pn_function.removeAll();
				adminJFrame.repaint();
				pn_function.add(records());
				adminJFrame.validate();
			}
		});
		bt3.addActionListener(new ActionListener() { //�˳�ϵͳ����ģʽ
			public void actionPerformed(ActionEvent e) {
				lb_tips.setText("�Ƿ��˳�ϵͳ����ģʽ��");
				choiceTips();
			}
		});
	}
	
	public JPanel manage() { //[�û�����]����
		String[] columnNames={"�˻�","����","�Ա�","�˻�״̬","����"};
		User user=null;
		int n=Login.allUsers.size(); //��ʾ�û�����
		String[][] rowData=new String[n][5];
		for(int i=0;i<Login.allUsers.size();i++) { //��ȡ���û�����Ϣ
			user=Login.allUsers.get(i);
			rowData[i][0]=user.account; //�˻�
			rowData[i][1]=user.name; //����
			rowData[i][2]=user.sex; //�Ա�
			if(user.lock) //�˻�״̬
				rowData[i][3]="<html><font color='red'>����</font></html>";
			else
				rowData[i][3]="����";
		}
		//����
		JPanel pn_manage=new JPanel();
		pn_manage.setLayout(null);
		pn_manage.setSize(1045,735);
		pn_manage.setBorder(BorderFactory.createEtchedBorder());
		JTable table=new JTable(new MyTableModel(columnNames,rowData));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",0,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		MyEvent e=new MyEvent() { //������鿴����ť���鿴�û���Ϣ�����޸��û���Ϣ
            public void invoke(ActionEvent e) {
                MyButton button=(MyButton)e.getSource();
                visitInfo(button.getRow());
            }
        };
		table.getColumnModel().getColumn(4).setCellRenderer(new MyButtonRender()); //���ñ�����Ⱦ��
		MyButtonEditor editor=new MyButtonEditor(e);
		table.getColumnModel().getColumn(4).setCellEditor(editor); //���ñ��ı༭��
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(1,0,1045,735);
		pn_manage.add(scrollPane);
		return pn_manage;
	}
	
	public void visitInfo(int num) { //�鿴�û���Ϣ�����޸��û���Ϣ
		User user=Login.allUsers.get(num);
		JPanel pn_info=new JPanel();
		pn_info.setLayout(null);
		pn_info.setSize(1045,735);
		pn_info.setBorder(BorderFactory.createEtchedBorder());
		JButton bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setBounds(1,10,92,25);
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton bt_modify=new JButton("�޸��û���Ϣ",new ImageIcon("image/modify.png")),
				bt_save=new JButton("����",new ImageIcon("image/save.png")),bt_cancel=new JButton("ȡ��",new ImageIcon("image/cancel.png"));
		bt_modify.setFont(new Font("����",0,17));
		bt_modify.setBounds(765,345,170,40);
		bt_modify.setContentAreaFilled(false);
		bt_modify.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_save.setFont(new Font("����",0,17));
		bt_save.setBounds(755,345,95,40);
		bt_save.setContentAreaFilled(false);
		bt_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_cancel.setFont(new Font("����",0,17));
		bt_cancel.setBounds(855,345,95,40);
		bt_cancel.setContentAreaFilled(false);
		bt_cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JToggleButton tbt_lock=new JToggleButton();
		tbt_lock.setBounds(655,50,65,40);
		tbt_lock.setBorderPainted(false);
		tbt_lock.setContentAreaFilled(false);
		tbt_lock.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tbt_lock.setIcon(new ImageIcon("image/����.png"));
		tbt_lock.setSelectedIcon(new ImageIcon("image/����.png"));
		JLabel lb_infoImage=new JLabel(new ImageIcon("image/info.png"));
		lb_infoImage.setBounds(750,130,200,200);
		JLabel[] lb=new JLabel[12];
		for(int i=0;i<12;i++) {
			lb[i]=new JLabel();
			lb[i].setFont(new Font("����",0,25));
			lb[i].setBounds(250,45+i*55,130,50);
			pn_info.add(lb[i]);
		}
		lb[0].setText("�˻�״̬��");
		lb[1].setText("����������");
		lb[2].setText("��    �ţ�");
		lb[3].setText("��    �룺");
		lb[4].setText("��    ����");
		lb[5].setText("��    ��");
		lb[6].setText("�� �� ֤��");
		lb[7].setText("��    ����");
		lb[8].setText("�� �� �֣�");
		lb[9].setText("��    �");
		lb[10].setText("Ƿ    �");
		lb[11].setText("�������ڣ�");
		JTextField[] tf=new JTextField[12];
		for(int i=0;i<12;i++) {
			tf[i]=new JTextField();
			tf[i].setFont(new Font("����",0,25));
			tf[i].setBounds(385,50+i*55,260,40);
			tf[i].setEditable(false);
			pn_info.add(tf[i]);
		}
		if(user.lock) {
			tbt_lock.setSelected(true); //ѡ��Ϊ����
			tf[0].setText("����");
		}
		else {
			tbt_lock.setSelected(false); //Ϊѡ��Ϊ����
			tf[0].setText("����");
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
		bt_back.addMouseListener(new MouseListener() { //���ص��û��������
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
		bt_modify.addActionListener(new ActionListener() { //�޸��û���Ϣ
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
		bt_save.addActionListener(new ActionListener() { //����
			public void actionPerformed(ActionEvent e) {
				for(int i=3;i<12;i++)
					tf[i].setEditable(false);
				//�޸��û���Ϣ
				if(tbt_lock.isSelected()) //�����û�
					user.lock=true;
				else { //�����û�
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
				try { //�����û���Ϣ
					Login.allUsers.remove(num);
					Login.allUsers.add(num,user);
					ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("�û���Ϣ.txt"));
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
		bt_cancel.addActionListener(new ActionListener() { //ȡ��
			public void actionPerformed(ActionEvent e) {
				for(int i=3;i<12;i++)
					tf[i].setEditable(false);
				if(user.lock) {
					tbt_lock.setSelected(true);
					tf[0].setText("����");
				}
				else {
					tbt_lock.setSelected(false);
					tf[0].setText("����");
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
		tbt_lock.addChangeListener(new ChangeListener() { //����������
            public void stateChanged(ChangeEvent e) {
                if(tbt_lock.isSelected())
                	tf[0].setText("����");
                else
                	tf[0].setText("����");
            }
        });
		pn_function.removeAll();
		adminJFrame.repaint();
		pn_function.add(pn_info);
		adminJFrame.validate();
	}
	
	public JTabbedPane records() { //[��ʷ��¼]����
		JTabbedPane tp_records=new JTabbedPane();
		tp_records.setFont(new Font("����",0,25));
		tp_records.setBounds(0,0,1045,735);
		tp_records.addTab(" ���м�¼ ",allRecords());
		singleRecords();
		tp_records.addTab(" ���˼�¼ ",pn_singleRecords);
		return tp_records;
	}
	
	public JPanel allRecords() { //���м�¼
		//��ȡ���׼�¼����
		User user=null;
		int n=0; //��ʾ���״���
		String[][] recordData;
		for(int i=0;i<Login.allUsers.size();i++) { //������ʷ���׼�¼����
			user=Login.allUsers.get(i);
			n+=user.record.size()/3;
		}
		if(n==0) { //����ʷ���׼�¼
			recordData=new String[1][5];
			for(int i=0;i<5;i++)
				recordData[0][i]="��";
		}
		else { //����ʷ���׼�¼
			int num=0; //��ʾ���׼�¼�����
			recordData=new String[n][5];
			for(int i=0;i<Login.allUsers.size();i++) { //��ȡ���û�����ʷ���׼�¼
				user=Login.allUsers.get(i);
				for(int j=0;j<user.record.size();j+=3,num++) { //��ʽ��ÿ�����׼�¼
					recordData[num][0]=user.account; //�˻�
					recordData[num][1]=user.record.get(j); //��ϸ
					recordData[num][2]=user.record.get(j+1); //ʱ��
					recordData[num][3]=user.record.get(j+2); //���
					recordData[num][4]=user.name; //������
				}
			}
			SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				String[] re=new String[5];
				for(int i=0;i<n-1;i++) { //��ʱ������
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
		//����
		JPanel pn_all=new JPanel(),pn_top=new JPanel();
		pn_all.setLayout(null);
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel("��"+n+"����ʷ��¼��");
		lb_num.setFont(new Font("����",0,25));
		JButton bt_export=new JButton("����");
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(recordData));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",0,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(0,50,1045,645);
		pn_all.add(pn_top);
		pn_all.add(scrollPane);
		bt_export.addActionListener(new ActionListener() { //�����׼�¼������Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(adminJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		return pn_all;
	}
	
	public void singleRecords() { //���˼�¼
		String[] columnNames={"�˻�","����","�Ա�","���","�鿴���˼�¼"};
		User user=null;
		String[][] rowData=new String[Login.allUsers.size()][5];
		for(int i=0;i<Login.allUsers.size();i++) { //��ȡ���û�����Ϣ
			user=Login.allUsers.get(i);
			rowData[i][0]=user.account; //�˻�
			rowData[i][1]=user.name; //����
			rowData[i][2]=user.sex; //�Ա�
			rowData[i][3]=user.money; //���
		}
		//����
		pn_singleRecords.setLayout(null);
		pn_people.setBounds(0,0,1045,695);
		pn_people.setLayout(null);
		JTable table=new JTable(new MyTableModel(columnNames,rowData));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",0,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.setBackground(null);
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		MyEvent e=new MyEvent() { //������鿴����ť���鿴�û����˽��׼�¼
            public void invoke(ActionEvent e) {
                MyButton button=(MyButton)e.getSource();
                visitRecords(button.getRow());
            }
        };
		table.getColumnModel().getColumn(4).setCellRenderer(new MyButtonRender()); //���ñ�����Ⱦ��
		MyButtonEditor editor=new MyButtonEditor(e);
		table.getColumnModel().getColumn(4).setCellEditor(editor); //���ñ��ı༭��
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(1,0,1045,695);
		pn_people.add(scrollPane);
		pn_singleRecords.add(pn_people);
	}
	
	public void visitRecords(int num) { //�鿴�û����˽��׼�¼
		//��ȡ���˽��׼�¼
		User user=Login.allUsers.get(num);
		int n; //��ʾ���״���
		String[][] recordData;
		if(Login.user.record.isEmpty()) { //�޽��׼�¼
			n=0;
			recordData=new String[1][5];
			for(int i=0;i<5;i++)
				recordData[0][i]="��";
		}
		else { //�н��׼�¼
			n=user.record.size()/3;
			recordData=new String[n][5];
			for(int i=0,j=0;i<user.record.size();i+=3,j++) { //��ʽ��ÿ�����׼�¼
				recordData[j][0]=user.account; //�˻�
				recordData[j][1]=user.record.get(i); //��ϸ
				recordData[j][2]=user.record.get(i+1); //ʱ��
				recordData[j][3]=user.record.get(i+2); //���
				recordData[j][4]=user.name; //������
			}
		}
		//����
		JPanel pn_single=new JPanel(),pn_top=new JPanel();
		pn_single.setLayout(null);
		pn_single.setBounds(0,0,1045,695);
		pn_top.setBounds(0,0,1045,50);
		JLabel lb_num=new JLabel("��"+n+"�����˽��׼�¼��");
		lb_num.setFont(new Font("����",0,25));
		JButton bt_export=new JButton("����"),bt_back=new JButton("����",new ImageIcon("image/����.png"));
		bt_export.setBackground(Color.green.darker());
		bt_export.setFont(new Font("����",0,20));
		bt_export.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bt_back.setFont(new Font("����",0,17));
		bt_back.setContentAreaFilled(false);
		bt_back.setBorderPainted(false);
		bt_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn_top.add(bt_back);
		pn_top.add(lb_num);
		pn_top.add(bt_export);
		JTable table=new JTable(new MyTableModel(recordData));
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(1,35)); //���ñ�ͷ�߶�
		header.setFont(new Font("����",0,23));
		table.setRowHeight(35); //���ø��и߶�
		table.setFont(new Font("����",0,20));
		table.getTableHeader().setReorderingAllowed(false); //�������ƶ�����
		JScrollPane scrollPane=new JScrollPane(table); //������
		scrollPane.setBounds(0,50,1045,645);
		pn_single.add(pn_top);
		pn_single.add(scrollPane);
		bt_export.addActionListener(new ActionListener() { //�����׼�¼������Excel�ļ�
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(adminJFrame,"�����õ���λ�ú��ļ�����",FileDialog.SAVE);
			    fd.setVisible(true);
			    String file=fd.getDirectory()+fd.getFile()+".xls";
			    if(fd.getFile()!=null)
			    	JTableToExcel.export(new File(file),table);
			}
		});
		bt_back.addMouseListener(new MouseListener() { //����
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
	
	public void functionTips() { //����ʱ��������ʾ��Ϣ����
		JDialog tips=new JDialog(adminJFrame,"  ��ʾ",true);
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
		JDialog choiceTips=new JDialog(adminJFrame,"  ��ʾ",true);
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
		if(lb_tips.getText().equals("�Ƿ��˳�ϵͳ����ģʽ��")) { //�˳�ϵͳ����ģʽ�����ص���ӭ����
			adminJFrame.dispose();
			HomePage.con.removeAll();
			HomePage.mainJFrame.repaint();
			HomePage.welcomePage();
			HomePage.mainJFrame.validate();
		}
	}
}