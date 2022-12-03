
public class TransactionInfo {
	int TID;
	static int id;
	static {
		id = 1;
	}
	int userID;
	String servicename;
	String providername;
	String paymentmethod;
	double payamount;
	public TransactionInfo(int id,String servicename,String providername,String paymentmethod,double payamount) {
		this.servicename = servicename;
		this.providername = providername;
		this.paymentmethod = paymentmethod;
		this.payamount = payamount;
		userID=id;
		TID = id;
		id++;
	}
	
}
