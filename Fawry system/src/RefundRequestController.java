import java.util.Vector;

public class RefundRequestController {
	PaymentHistory P;
	RefundRequests refundrequest = new RefundRequests() ; 
	SignController signController = new SignController();
	
	 Vector<TransactionInfo>refundRequestList = refundrequest.getAllRefund();
	public RefundRequestController(PaymentHistory P) {
		this.P = P;
	}
	// Add refund to the list
	public boolean add_refund_request(int IDTransaction) {
		 Vector <TransactionInfo> transactionInfo = P.getAllTransInfo();
		 for(int i = 0 ;i <transactionInfo.size();i++ ) {
			 if(P.transactionInfo.get(i).TID == IDTransaction) {
				 refundrequest.refundRequestList.add((P.transactionInfo.get(i)));
				 return  true;
				 
			 }
		 }
		 
		 return false;
	 }
	
	
	 // Admin reply refund request
	public void addAmounttoWallet(User user , double amount) {
		user.wallet.addAmount(amount);
	}
	
	 public boolean remove_refund_request(TransactionInfo transinfo , boolean reply) {
		 int UserID;
    	  UserID = refundrequest.refundRequestList.get(0).userID;
    	  
          for(int i =0; i < signController.users.size() ;i++) {
        	   if(signController.users.get(i).id == UserID) {
        		   
        		   if(reply) {
        			   signController.users.get(i).refundRequestState = true;
        			   addAmounttoWallet(signController.users.get(i),transinfo.payamount);
        		   }
        		   else
        		       signController.users.get(i).refundRequestState = false;
        	   }
          }
          for(int i = 0; i < refundrequest.refundRequestList.size() ;i++) {
  		    if(refundrequest.refundRequestList.get(i).TID ==  transinfo.TID) {
		    	 refundrequest.refundRequestList.remove(i);
		    	 return true;
	         }
		 
	    }
        return false;
     }
}