
public class Handler implements Command {
	TransactionInfo transinfo;
	PaymentHistory payHist;
	
	public Handler(TransactionInfo trans,PaymentHistory history){
		this.transinfo=trans;
		this.payHist=history;
		
	}

	@Override
	public void execute() {
		// 
		payHist.addTrancation(transinfo);
		
	}

}
