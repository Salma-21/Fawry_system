import java.util.Vector;

public class RefundRequestController {
	PaymentHistory P;
	RefundRequests refundrequest = RefundRequests.getInstance() ; 
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
		 int UserID=transinfo.userID;
    	  //UserID = refundrequest.refundRequestList.firstElement().userID;
    	  
          for(int i =0; i < signController.sign.users.size() ;i++) {
        	   if(signController.sign.users.get(i).id == UserID) {
        		   
        		   if(reply) {
        			   signController.sign.users.get(i).refundRequestState = true;
        			   addAmounttoWallet(signController.sign.users.get(i),transinfo.payamount);
        		   }
        		   else
        		       signController.sign.users.get(i).refundRequestState = false;
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