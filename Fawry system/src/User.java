
public class User {
	boolean refundRequestState = false;
	Wallet wallet;
	String userName;
	String password;
	String email;
	int timesOfPay;
	int id;
	static int SId;
	static {
		SId = 1;
	}
	public User(String userName,String password,String email )
	{
		this.userName=userName;
		this.password=password;
		this.email=email;
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
	public int gettimesOfPay()
	{
		return timesOfPay;
	}
	public String getPassword()
	{
		return password;
	}
	public String getEmail()
	{
		return email;
	}
	public double getWalletAmount()
	{
		return wallet.amount;
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
	
	public void increaseTimesOfPay() {
		timesOfPay++;
	}
	public void addToWallet(double funds) {
		wallet.addAmount(funds);;
	}


}


