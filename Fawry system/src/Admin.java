import java.util.Vector;

public class Admin {
	RefundRequestController refundRequestController;
	
	
	public Admin(RefundRequestController refundRequestControlle) {
		
		this.refundRequestController = refundRequestControlle;
	}
	public Admin() {}
	
    
    public void addServiceProvider(Service service,String provider_name,Vector<String> vec,boolean acceptCash) {
    	Provider provider=new Provider( provider_name, vec, acceptCash);
    	service.setProvider(provider);
		
	}

}
