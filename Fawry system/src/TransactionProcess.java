import java.util.Vector;

public class TransactionProcess {
	IPayment payment;
	ServiceController service_control;
	AllServicesController all_service_control;
	
	public Vector<Service> get_services(){
		return all_service_control.allService.services;
	}
	
	public Service choose_Service(String serviceName) {
		return all_service_control.search(serviceName);
	}
	
	public Vector<Provider> get_provider(Service service) {
		
		return service.getProviders();
	}
	
	public Provider choose_Provider(String providerName) {
		return service_control.SearchProvider(providerName);
	}
	
	public boolean choose_Payment(Provider provider) {
		return provider.checkCash();
	}
	
	public void create_payment_method(String type) {
		if(type=="cash")
			payment=new CashPayment();
		else if(type=="credit card")
			payment=new CreditPayment();
		else if(type=="wallet")
			payment=new WalletPayment();
		payment.pay(0);//need for check discount
			
	}
	public void handel_transaction(ProviderController provider_control,TransactionInfo transinfo,PaymentHistory payHistory) {
		
		provider_control.executeHandler(transinfo, payHistory);
			
	}
	
	public TransactionInfo set_transaction_info(int id,String servicename,String providername,String paymentmethod,double payamount,Vector<String>answer) {
		TransactionInfo transinfo=new TransactionInfo( id, servicename, providername, paymentmethod, payamount,answer);
		return transinfo;
	}
}


