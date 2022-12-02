
public class User {
	Wallet wallet;
	String userName;
	String password;
	String email;
	Boolean verified=false;
	public User(String userName,String password,String email,Boolean verified )
	{
		this.userName=userName;
		this.password=password;
		this.email=email;
		this.verified=true;
		wallet=new Wallet();
		
	}
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

}
