import java.util.Scanner;
import java.util.Vector;

public class Boundry {
	AllServicesController allServicesController=new AllServicesController();
	Sign sign;
	TransactionProcess trans=new TransactionProcess();
	PaymentHistory paymentHistory=new PaymentHistory();
	Provider provider;
	ProviderController providerController;
	RefundRequestController refundRequestController = new RefundRequestController(paymentHistory);
	Admin admin = new Admin(refundRequestController);
	User user=new User();//will be equal to user who sign in 
	
	public void displayService(Vector<Service>s)
	{
		for(int i=0;i<s.size();i++)
		{
			System.out.println(s.get(i).getName());
		}
	}
	public void displayProvider(Vector<Provider>p)
	{
		for(int i=0;i<p.size();i++)
		{
			System.out.println(p.get(i).getName());
		}
	}
	public Vector<String> fillForm(Vector<String> form)
	{
		Vector<String>answer=new Vector();
		Scanner obj2 = new Scanner(System.in);
		System.out.println("Fill The following fields");
		for(int i=0;i<form.size();i++)
		{
			System.out.println(form.get(i));
			String value = obj2.nextLine();
			answer.addElement(value);
		}
		return answer;
	}
	public void pay()
	{
		Service service;
		Scanner obj1 = new Scanner(System.in);
		
		//choose service
		
		System.out.println("This is our Services: ");
		Vector<Service>s=trans.get_services();
		displayService(s);
		System.out.println("Enter the service name you want :");
	    String serviceName = obj1.nextLine();
	    service=trans.choose_Service(serviceName);
	    
	  //choose provider
	    
	    Vector<Provider>p=trans.get_provider(service);
	    System.out.println("This is our Providers :");
	    displayProvider(p);
	    System.out.println("Enter the provider name you want :");
	    String providerName = obj1.nextLine();
	    provider=trans.choose_Provider(providerName);
		providerController=new ProviderController(provider);
		
		//choose payment method
		
		boolean b=trans.choose_Payment(provider);
		 System.out.println("this provider allow the following payment method:");
		 System.out.println("1-Credit Card\n2-Wallet");
		 if(b)
			 System.out.println("3-Cash\n");
		 
		 System.out.println("Enter the payment method name you want :");
		 String paymentName = obj1.nextLine();
		 trans.create_payment_method(paymentName , user.wallet);
		 
		 //fill provider form
		 
		 Vector<String>answer=fillForm(provider.form);
		 
		 //save transaction
		 
		 double amount=0; //check total discount
		 TransactionInfo transinfo=trans.set_transaction_info(user.id,serviceName,providerName,paymentName,amount,answer);
		 
		 Command command = new AddTransaction(transinfo,paymentHistory);
		trans.handel_transaction(command);
		user.increaseTimesOfPay();
		System.out.println(" ****Congratulation you have finished the payment process**** ");
	    
	}
	
	///Maram  
	
	public void displayrefundRequest(TransactionInfo refundRequests) {
		 System.out.println("userID : " + refundRequests.userID);
		 System.out.println("servicename : " + refundRequests.servicename);
		 System.out.println("providername : " + refundRequests.providername);
		 System.out.println("paymentmethod : " + refundRequests.paymentmethod);
		 System.out.println("payamount : " +refundRequests.payamount);
		 provider = trans.choose_Provider(refundRequests.providername);
		 for(int i=0;i<provider.form.size();i++) {
		    System.out.println(i + 1+ " - " + provider.form.get(i) + " : " + refundRequests.answer.get(i) );
		 }
	}
	
	// User Add refund to list
	public void requestrefund()
	{
		//Enter ID Transaction
		 System.out.println("Enter Your ID Transaction : ");
         Scanner obj1 = new Scanner(System.in);
         int TransID = obj1.nextInt();
         // call fun Add to refund list 
         boolean reply = refundRequestController.add_refund_request(TransID);
		 if(reply)
			 System.out.println("The request has been sent Successfully"); 
		 else
			 System.out.println("There is not Transaction with this ID!!!"); 
	}
	// Admin  reply refund 
	public void replyrefund()
	{
		 Command command ;
		 int Continue = 0;
		 boolean answer = false;
	     // Enter yes if you want to reply the next reply
		do {
			 System.out.println("Enter 1 or 2 if you want to reply the next reply : ");
	         System.out.println("1- Yes  \n 2- No");
	         Scanner obj1 = new Scanner(System.in);
	         Continue = obj1.nextInt();
	         displayrefundRequest(refundRequestController.refundRequestList.get(0));
	        // Enter reply 
	         System.out.println("Enter 1 or 2 reply : ");
	         System.out.println("1- Yes  \n 2- No");
	         Scanner obj2 = new Scanner(System.in);
	         int reply = obj2.nextInt();
	         if(reply == 1) 
	        	  answer = true;
	         else if(reply == 2)
	        	  answer = false;
			 command = new DeleteTransaction(refundRequestController.refundRequestList.get(0),paymentHistory,refundRequestController,answer);
	         trans.handel_transaction(command);
		}while(Continue == 1 && (!refundRequestController.refundRequestList.isEmpty()));
		
		 
	}
	

	
	//search
	public Service searchForService() {
		Service service;
		System.out.println("Enter service name:");
		Scanner obj1 = new Scanner(System.in);
	    String str = obj1.nextLine();
	    service=allServicesController.search(str);
	    System.out.println(service.getName()); 
	    System.out.println(service.getCost());
	    System.out.println(service.getSpceficDiscount());
	    for (int i=0; i<service.getProviders().size() ;i++)
		{
	    	System.out.println(service.getProviders().get(i));
		}
	    return service;
	}
	
	//add provider
	public void addServiceProvider() {
		Service service;
		service=searchForService();
		System.out.println("Enter provider name you want to add:");
		Scanner obj1 = new Scanner(System.in);
	    String provider_name = obj1.nextLine();
	    System.out.println("does it accept cash?(Enter 1 or 2)");
	    System.out.println("1-YES");
	    System.out.println("2-NO");
	    int cashState = obj1.nextInt();
	    boolean acceptCash=false;
	    if(cashState==1)
	    	acceptCash=true;
	    
	    System.out.println("Enter num of form fields:");
	    int size = obj1.nextInt();
	    Vector<String> vec=new Vector();
	    for (int i=0; i<size ;i++)
		{
	    	System.out.println("Enter field"+i+1+":");
	    	String string = obj1.nextLine();
	    	vec.add(string);
		}
	    Admin admin=new Admin();
	    admin.addServiceProvider(service,provider_name,vec,acceptCash);

		
	}
	public void addDiscount() {
		
		System.out.println("Choose Discount you want to Add: ");
		System.out.println("1- Spcific Discount ");
		
		System.out.println("2- Ovarall Discount ");
		 
		Scanner obj1 = new Scanner(System.in);
		int x = obj1.nextInt();
		if(x==1)
		{
			System.out.println("Enter The Service Name: ");
			Scanner obj2 = new Scanner(System.in);
			String name = obj2.nextLine();
			Service service =allServicesController.search(name);
			System.out.println("Enter The amount of The Discount % : ");
			 
			Scanner obj3 = new Scanner(System.in);
			int discount = obj3.nextInt();
			service.setSpceficDiscount(discount/100);
			
			
		}
	}
	
}
