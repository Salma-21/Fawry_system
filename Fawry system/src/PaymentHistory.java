import java.util.*;

public class PaymentHistory {
	Vector<TransactionInfo>transactionInfo =new Vector();
	
	public void addTrancation(TransactionInfo trans) {
		transactionInfo.add(trans);
	}
	
}
