package atm;
import java.io.*;
import java.util.ArrayList;
public class SaveInitialUser { //����˽�´洢��ʼ�û����������Ƿ�ɹ�����ɲ鿴�����û���Ϣ
	static ArrayList<User> users=new ArrayList<User>(); //���ڴ��������û�
	public static void main(String[] args) {
		init(); //���鿴�û���Ϣ����ע�ʹ�
		write(); //���鿴�û���Ϣ����ע�ʹ�
		print();
	}
	
	public static void init() { //���ڳ�ʼ�����Զ����û������޸ĳ�ʼ�û���Ϣ�����޸Ĵˣ�
		boolean lock=false; //�˻�״̬
		int lockDays=0,errorNum=0,transferNum=10000,credit=100; //������������������������������ת���޶���÷�
		String account="2020202012345678", //����
		       password="123456", //����
		       name="С��", //����
		       sex="��", //�Ա�
		       idCard="445221200003310216", //���֤
		       phone="13602161617", //�ֻ�
		       money="100000.0", //���
		       debt="0", //Ƿ��
		       time="2020-08-25"; //��������
		User user=new User(lock,lockDays,errorNum,transferNum,credit,account,password,name,sex, idCard,phone,money,debt,time);
		users.add(user);
		User user0=new User(false,0,0,10000,80,"2020202011223344","112233","С��","Ů", "445221200101010216","13600021116","100.0","0","2020-08-25");
		users.add(user0);
//		users.add(new User(password,name,sex,idCard,phone)); //ϵͳ��������ֵ	
	}
	
	public static void write() { //���ڽ��û���Ϣд�����ļ�
		ObjectOutputStream oos;
		try {
			oos=new ObjectOutputStream(new FileOutputStream("�û���Ϣ.txt",false));
			oos.writeObject(users);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void print() { //���ڶ�ȡ�ļ��е��û���Ϣ�������������̨
		ArrayList<User> test_users=null; //���ڴ�����ļ��ж�ȡ�������û�
		ObjectInputStream ois;
		try {
			ois=new ObjectInputStream(new FileInputStream("�û���Ϣ.txt"));
			test_users=(ArrayList<User>)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		for(int i=0;i<test_users.size();i++) {
			User test_user=test_users.get(i); //���ڴ��浥���û�
			System.out.println(i+"�� ======================================================");
			System.out.println("�˻�״̬��"+test_user.lock);
			System.out.println("����������"+test_user.lockDays);
			System.out.println("����������������"+test_user.errorNum);
			System.out.println("����ת���޶"+test_user.transferNum);
			System.out.println("���÷֣�"+test_user.credit);
			System.out.println("��������ʱ�䣺"+test_user.lockTime);
			System.out.println("�������ʱ�䣺"+test_user.errorTime);
			System.out.println("ת��ʱ�䣺"+test_user.transferTime);
			System.out.println("����ʱ�䣺"+test_user.loanTime);
			System.out.println("����ʱ���÷ֺ�Ƿ���¼��"+test_user.dai);
			System.out.println("���ţ�"+test_user.account);
			System.out.println("���룺"+test_user.password);
			System.out.println("������"+test_user.name);
			System.out.println("�Ա�"+test_user.sex);
			System.out.println("���֤��"+test_user.idCard);
			System.out.println("�ֻ���"+test_user.phone);
			System.out.println("��"+test_user.money);
			System.out.println("Ƿ�"+test_user.debt);
			System.out.println("�������ڣ�"+test_user.time);
			System.out.println("���׼�¼��"+test_user.record);
		}
	}
}