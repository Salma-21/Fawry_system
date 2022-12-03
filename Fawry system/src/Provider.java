import java.util.Vector;

public class Provider {
	String name;
	boolean accept_cash;
	Vector<String> form=new Vector();
	
	public Provider(String PN,Vector<String> v,boolean c) {
		name=PN;
		form=v;
		accept_cash=c;
	}
	

}
