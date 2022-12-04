import java.util.*;

public class PaymentHistory {
	Vector<TransactionInfo>transactionInfo =new Vector();
	
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
