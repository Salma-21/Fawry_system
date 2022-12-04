import java.util.Vector;

public class TransactionInfo {
	int TID;  // tranaction ID
	static int id;
	static {
		id = 1;
	}
	int userID;
	String servicename;
	String providername;
	String paymentmethod;
	double payamount;
	Vector<String> answer;
	
	public TransactionInfo(int id,String servicename,String providername,String paymentmethod,double payamount,Vector<String> answer)
	{
		this.servicename = servicename;
		this.providername = providername;
		this.paymentmethod = paymentmethod;
		this.payamount = payamount;
		this.answer=answer;
		userID=id;
		TID = id;
		id++;
	}
	
}
