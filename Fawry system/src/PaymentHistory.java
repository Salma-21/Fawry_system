import java.util.*;

public class PaymentHistory {
	Vector<TransactionInfo>transactionInfo =new Vector();
	private static PaymentHistory obj;
	private PaymentHistory() {}
	public static PaymentHistory getInstance()
    {
        if (obj==null)
            obj = new PaymentHistory();
        return obj;
    }
	public boolean addTrancation(TransactionInfo trans) {
		transactionInfo.add(trans);
		return true;
	}
	  
	public Vector<TransactionInfo> getAllTransInfo(){
		return transactionInfo;
	}
	public boolean removeTrancation(TransactionInfo trans) {
		return transactionInfo.remove(trans);
	}
	
	
	
	
}
