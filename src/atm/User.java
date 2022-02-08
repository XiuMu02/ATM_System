package atm;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class User implements Serializable { //用户类
	boolean lock ; //账户状态，是否永久锁定
	int lockDays,errorNum,transferNum,credit; //锁定天数，当日密码出错次数(指密码最后出错当日而非今日)，当日转账限额(指最后登录当日)，信用分
	ArrayList<String> lockTime=new ArrayList<String>(); //锁定时间
	ArrayList<String> errorTime=new ArrayList<String>(); //密码出错时间
	ArrayList<String> transferTime=new ArrayList<String>(); //转账时间
	ArrayList<String> loanTime=new ArrayList<String>(); //贷款时间
	ArrayList<String> dai=new ArrayList<String>(); //贷款时信用分和欠款记录，每两个元素为一组
	String account, //卡号
	       password, //密码
	       name, //姓名
	       sex, //性别
	       idCard, //身份证
	       phone, //手机
	       money, //余额
	       debt, //欠款
	       time; //建卡日期
	ArrayList<String> record=new ArrayList<String>(); //交易记录，每三个元素组成一条交易记录
	public User(String password, String name, String sex, String idCard, String phone) { //为账户各信息赋值
		ArrayList<User> users=null;
		ObjectInputStream ois;
		try {
			ois=new ObjectInputStream(new FileInputStream("用户信息.txt"));
			users=(ArrayList<User>)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		lock=false; //账户状态初始化正常非永久锁定
		lockDays=0; //锁定天数初始化0
		errorNum=0; //当日密码出错次数初始化0
		transferNum=10000; //当日转账限额初始化10000
		credit=90; //信用分初始化90
		lockTime.add("0000-00-00"); //锁定时间初始化
		errorTime.add("0000-00-00");
		errorTime.add("0000-00-00");
		errorTime.add("0000-00-00"); //密码出错时间初始化
		transferTime.add("0000-00-00"); //转账时间初始化
		loanTime.add("0000-00-00"); //贷款时间初始化
		this.password=password; //密码赋值
		this.name=name; //姓名赋值
		this.sex=sex; //性别赋值
		this.idCard=idCard; //身份证赋值
		this.phone=phone; //手机赋值
		money="0.0"; //余额初始化0
		debt="0"; //欠款初始化0
		SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
		time=timeFormat.format(System.currentTimeMillis()); //建卡日期赋值
		Random r=new Random();
		boolean y=false; //是否重复
		do { //卡号赋值，并查重
			account="20202020";
			for(int i=0;i<8;i++) //赋值
				account=account+r.nextInt(10);
			for(int i=0;i<users.size();i++) { //查重
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
		//用于私下存储初始用户
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