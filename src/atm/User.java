package atm;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class User implements Serializable { //�û���
	boolean lock ; //�˻�״̬���Ƿ���������
	int lockDays,errorNum,transferNum,credit; //������������������������(ָ�����������ն��ǽ���)������ת���޶�(ָ����¼����)�����÷�
	ArrayList<String> lockTime=new ArrayList<String>(); //����ʱ��
	ArrayList<String> errorTime=new ArrayList<String>(); //�������ʱ��
	ArrayList<String> transferTime=new ArrayList<String>(); //ת��ʱ��
	ArrayList<String> loanTime=new ArrayList<String>(); //����ʱ��
	ArrayList<String> dai=new ArrayList<String>(); //����ʱ���÷ֺ�Ƿ���¼��ÿ����Ԫ��Ϊһ��
	String account, //����
	       password, //����
	       name, //����
	       sex, //�Ա�
	       idCard, //���֤
	       phone, //�ֻ�
	       money, //���
	       debt, //Ƿ��
	       time; //��������
	ArrayList<String> record=new ArrayList<String>(); //���׼�¼��ÿ����Ԫ�����һ�����׼�¼
	public User(String password, String name, String sex, String idCard, String phone) { //Ϊ�˻�����Ϣ��ֵ
		ArrayList<User> users=null;
		ObjectInputStream ois;
		try {
			ois=new ObjectInputStream(new FileInputStream("�û���Ϣ.txt"));
			users=(ArrayList<User>)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		lock=false; //�˻�״̬��ʼ����������������
		lockDays=0; //����������ʼ��0
		errorNum=0; //����������������ʼ��0
		transferNum=10000; //����ת���޶��ʼ��10000
		credit=90; //���÷ֳ�ʼ��90
		lockTime.add("0000-00-00"); //����ʱ���ʼ��
		errorTime.add("0000-00-00");
		errorTime.add("0000-00-00");
		errorTime.add("0000-00-00"); //�������ʱ���ʼ��
		transferTime.add("0000-00-00"); //ת��ʱ���ʼ��
		loanTime.add("0000-00-00"); //����ʱ���ʼ��
		this.password=password; //���븳ֵ
		this.name=name; //������ֵ
		this.sex=sex; //�Ա�ֵ
		this.idCard=idCard; //���֤��ֵ
		this.phone=phone; //�ֻ���ֵ
		money="0.0"; //����ʼ��0
		debt="0"; //Ƿ���ʼ��0
		SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
		time=timeFormat.format(System.currentTimeMillis()); //�������ڸ�ֵ
		Random r=new Random();
		boolean y=false; //�Ƿ��ظ�
		do { //���Ÿ�ֵ��������
			account="20202020";
			for(int i=0;i<8;i++) //��ֵ
				account=account+r.nextInt(10);
			for(int i=0;i<users.size();i++) { //����
				if(account.equals(users.get(i).account)) {
					y=true;
					break;
				}
				else
					y=false;
			}
		} while(y);
	}
	
	public User(boolean lock,int lockDays,int errorNum,int transferNum,int credit,String account,String password,String name,String sex,String idCard,String phone,String money,String debt,String time) { 
		//����˽�´洢��ʼ�û�
		this.lock=lock;
		this.lockDays=lockDays;
		this.errorNum=errorNum;
		this.transferNum=transferNum;
		this.credit=credit;
		lockTime.add("0000-00-00");
		errorTime.add("0000-00-00");
		errorTime.add("0000-00-00");
		errorTime.add("0000-00-00");
		transferTime.add("0000-00-00");
		loanTime.add("0000-00-00");
		this.account=account;
		this.password=password;
		this.name=name;
		this.sex=sex;
		this.idCard=idCard;
		this.phone=phone;
		this.money=money;
		this.debt=debt;
		this.time=time;
	}
}