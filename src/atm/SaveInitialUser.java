package atm;
import java.io.*;
import java.util.ArrayList;
public class SaveInitialUser { //用于私下存储初始用户，并测试是否成功，亦可查看所有用户信息
	static ArrayList<User> users=new ArrayList<User>(); //用于储存所有用户
	public static void main(String[] args) {
		init(); //仅查看用户信息，请注释此
		write(); //仅查看用户信息，请注释此
		print();
	}
	
	public static void init() { //用于初始化或自定义用户（想修改初始用户信息，请修改此）
		boolean lock=false; //账户状态
		int lockDays=0,errorNum=0,transferNum=10000,credit=100; //锁定天数，当日密码出错次数，当日转账限额，信用分
		String account="2020202012345678", //卡号
		       password="123456", //密码
		       name="小明", //姓名
		       sex="男", //性别
		       idCard="445221200003310216", //身份证
		       phone="13602161617", //手机
		       money="100000.0", //余额
		       debt="0", //欠款
		       time="2020-08-25"; //建卡日期
		User user=new User(lock,lockDays,errorNum,transferNum,credit,account,password,name,sex, idCard,phone,money,debt,time);
		users.add(user);
		User user0=new User(false,0,0,10000,80,"2020202011223344","112233","小红","女", "445221200101010216","13600021116","100.0","0","2020-08-25");
		users.add(user0);
//		users.add(new User(password,name,sex,idCard,phone)); //系统赋其他初值	
	}
	
	public static void write() { //用于将用户信息写入至文件
		ObjectOutputStream oos;
		try {
			oos=new ObjectOutputStream(new FileOutputStream("用户信息.txt",false));
			oos.writeObject(users);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void print() { //用于读取文件中的用户信息，并输出至控制台
		ArrayList<User> test_users=null; //用于储存从文件中读取的所有用户
		ObjectInputStream ois;
		try {
			ois=new ObjectInputStream(new FileInputStream("用户信息.txt"));
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
			User test_user=test_users.get(i); //用于储存单个用户
			System.out.println(i+"号 ======================================================");
			System.out.println("账户状态："+test_user.lock);
			System.out.println("锁定天数："+test_user.lockDays);
			System.out.println("当日密码出错次数："+test_user.errorNum);
			System.out.println("当日转账限额："+test_user.transferNum);
			System.out.println("信用分："+test_user.credit);
			System.out.println("密码锁定时间："+test_user.lockTime);
			System.out.println("密码出错时间："+test_user.errorTime);
			System.out.println("转账时间："+test_user.transferTime);
			System.out.println("贷款时间："+test_user.loanTime);
			System.out.println("贷款时信用分和欠款记录："+test_user.dai);
			System.out.println("卡号："+test_user.account);
			System.out.println("密码："+test_user.password);
			System.out.println("姓名："+test_user.name);
			System.out.println("性别："+test_user.sex);
			System.out.println("身份证："+test_user.idCard);
			System.out.println("手机："+test_user.phone);
			System.out.println("余额："+test_user.money);
			System.out.println("欠款："+test_user.debt);
			System.out.println("建卡日期："+test_user.time);
			System.out.println("交易记录："+test_user.record);
		}
	}
}