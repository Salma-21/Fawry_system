
public class TotalDiscount {
	int timeOfPay;
	double amount;
	public TotalDiscount()
	{
		this.timeOfPay=0;
		this.amount= 0;
	}
	public void setOverallDiscount(int timeOfPay,double amount)
	{
		this.timeOfPay=timeOfPay;
		this.amount= amount;
	}

	public double getTotalCost(User user,Service service)
	{
		double cost=service.getCost();//after specific
		if(user.gettimesOfPay()==timeOfPay)
		{
			double discountvalue=amount*cost;
			cost-=discountvalue;
		}
		
		return cost;
			
	}
}
