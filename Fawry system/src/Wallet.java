
public class Wallet {
	double amount;
	public Wallet()
	{
		amount=0;
	}
	public void addAmount(double x)
	{
		amount+=x;
	}
	public Boolean consumeAmount(double x)
	{
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
}
