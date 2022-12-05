
public class TotalDiscount {
	Service service;
	int timeOfPay;
	double amount;
	public void setOverallDiscount(int timeOfPay,double amount)
	{
		this.timeOfPay=timeOfPay;
		this.amount= amount;
	}

	public double getTotalCost(User user,Service service)
	{
		this.service=service;
		double cost=service.getCost();//after specific
		if(user.gettimesOfPay()==timeOfPay)
		{
			double discountvalue=amount*cost;
			cost-=discountvalue;
		}
		return cost;
			
	}
}
