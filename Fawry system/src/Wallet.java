public class Wallet {
	double amount;
	public Wallet()
	{
		amount=20;
	}
	public void addAmount(double x)
	{
		amount+=x;
	}
	public Boolean consumeAmount(double x)
	{
		System.out.println("commm");
		if(amount>=x)
		{
			amount-=x;
			return true;
		}
		else //not valid 
		{
			return false;
		}
		
	}
	public double getAmount()
	{
		return amount;
	}

}
