import java.util.Vector;

public class TransactionProcess {
	IPayment payment;
	AllServicesController all_service_control;
	public TransactionProcess() {
		all_service_control = new AllServicesController();
		
	}
	public Vector<Service> get_services(){
		return all_service_control.allService.services;
	}
	
	public Service choose_Service(String serviceName) {
		return all_service_control.search(serviceName);
	}
	
	public Vector<Provider> get_provider(Service service) {
		
		return service.getProviders();
	}
	
	public Provider choose_Provider(String providerName,Service service) {
		ServiceController service_control = new ServiceController(service);
		return service_control.SearchProvider(providerName);
	}
	
	public boolean choose_Payment(Provider provider) {
		return provider.checkCash();
	}
	
	public void create_payment_method(String type,Wallet wallet,double cost) {
		if(type=="cash")
			payment=new CashPayment();
		else if(type=="credit card")
			payment=new CreditPayment();
		else if(type=="wallet")
		{
			if(wallet !=null)
				payment=new WalletPayment(wallet);
			
		}
			
		else 
			payment=new CreditPayment();
		payment.pay(cost);
			
	}
	public void handel_transaction(Command c) {
		
		//provider_control.executeHandler(transinfo, payHistory);
		c.execute();
		/*public void executeHandler(TransactionInfo transinfo,PaymentHistory history) {
			p.command=new AddTransaction(transinfo,history);
			p.command.execute();
			
		}*/
			
	}
	
	public TransactionInfo set_transaction_info(int id,String servicename,String providername,String paymentmethod,double payamount,Vector<String>answer) {
		TransactionInfo transinfo=new TransactionInfo( id, servicename, providername, paymentmethod, payamount,answer);
		return transinfo;
	}
}


