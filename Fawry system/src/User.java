
public class User {
	boolean refundRequestState = false;
	Wallet wallet;
	String userName;
	String password;
	String email;
	int timesOfPay;
	boolean verified=false;
	int id;
	static int SId;
	static {
		SId = 1;
	}
	public User(String userName,String password,String email,Boolean verified )
	{
		this.userName=userName;
		this.password=password;
		this.email=email;
		this.verified=true;
		timesOfPay=0;
		wallet=new Wallet();
		id=SId;
		SId++;
		
	}
	public User() {}
	
	public String getUserName()
	{
		return userName;
	}
	public String getPassword()
	{
		return password;
	}
	public String getEmail()
	{
		return email;
	}
	public Boolean getVerified()
	{
		return verified;
	}
	public void setUserName(String s)
	{
		userName=s;
	}
	public void setPassword(String s)
	{
		password=s;
	}
	public void setEmail(String s)
	{
		 email=s;
	}
	public void setVerified(Boolean b)
	{
		verified=b;
	}
	public void increaseTimesOfPay() {
		timesOfPay++;
	}


}


