import java.util.Vector;

public class Provider {
	Command command;
	String name;
	boolean accept_cash;
	Vector<String> form=new Vector();
	
	public Provider(String PN,Vector<String> v,boolean c) {
		name=PN;
		form=v;
		accept_cash=c;
		
	}
	public String getName() {
		return name;
	}
	public boolean checkCash() {
		return accept_cash;
	}
	public void executeHandler(TransactionInfo transinfo,PaymentHistory history) {
		command=new Handler(transinfo,history);
		command.execute();
		
	}
	

}
