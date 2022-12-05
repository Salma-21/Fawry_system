import java.util.Scanner;
import java.util.Vector;

public class Boundry {
	static AllServicesController allServicesController=new AllServicesController();
	static Sign sign;
	static TransactionProcess trans=new TransactionProcess();
	static PaymentHistory paymentHistory=new PaymentHistory();
	static Provider provider;
	static ProviderController providerController;
	static RefundRequestController refundRequestController = new RefundRequestController(paymentHistory);
	static Admin admin = new Admin(refundRequestController);
	static SignController signContoller=new SignController();;
	static User user=new User();//will be equal to user who sign in 
	
	public static void displayService(Vector<Service>s)
	{
		for(int i=0;i<s.size();i++)
		{
			System.out.println(s.get(i).getName());
		}
	}
	public static void displayProvider(Vector<Provider>p)
	{
		for(int i=0;i<p.size();i++)
		{
			System.out.println(p.get(i).getName());
		}
	}
	public static Vector<String> fillForm(Vector<String> form)
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
	public  static void pay()
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
	
	public static void displayrefundRequest(TransactionInfo refundRequests) {
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
	public static void requestrefund()
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
	public static void replyrefund()
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
	public static Service searchForService() {
		Service service;
		System.out.println("Enter service name:");
		Scanner obj1 = new Scanner(System.in);
	    String str = obj1.nextLine();
	    service=allServicesController.search(str);
	    System.out.println("service name:"+service.getName()); 
	    System.out.println("service cost:"+service.getCost());
	    System.out.println("service specific discount:"+service.getSpceficDiscount());
	    for (int i=0; i<service.getProviders().size() ;i++)
		{
	    	System.out.println("service provider:"+service.getProviders().get(i));
		}
	    return service;
	}
	
	//add provider
	public static void addServiceProvider() {
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
	public static void addDiscount() {
		
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
	public static  User  signIn(String username,String pass)
	{
		return signContoller.signIn(username,pass);
	}

	
	public static void checkDiscount(String serviceName)
	{
		Service service;
		service=allServicesController.search(serviceName);
		System.out.println(service.getSpceficDiscount());
	}
	
	
	public static void main(String[] args) {
		System.out.println("**** Welcome to our system ****");
		Scanner obj2 = new Scanner(System.in);
		boolean countinue=true;
		while(countinue)
		{
			User user=new User();
			System.out.println("Who are you?\nEnteryour reply 1 or 2 \n1-user\n2-admin");
			Scanner obj1 = new Scanner(System.in);
			int reply=obj1.nextInt();
			boolean signed=false;
			System.out.println("Choose one of the following options -->\n");
			if(reply==1)
			{
				System.out.println("1-sign in \n2-sign up\n3-search for service by name\n"
						+ "4-pay for service\n5-add funds to wallet\n6-ask for refund\n"
						+ "7-check discount for services ");
				 reply=obj1.nextInt();
				switch(reply) {
					case 1:
						System.out.println("Enter your user name:");
						String username=obj1.nextLine();
						System.out.println("Enter your Password:");
						String pass=obj1.nextLine();
						user=signIn(username,pass);
						if(user!=null)
						{
							System.out.println("Welcome "+user.userName);
							signed=true;
						}
							
						else
							System.out.println(" oops! we are not found you can to sign up");
						break;
					case 2:
						System.out.println("Enter your user name:");
						String name=obj1.nextLine();
						System.out.println("Enter yourPassword:");
						String passw=obj1.nextLine();
						System.out.println("Enter your email:");
						String email=obj1.nextLine();
						user=signContoller.signUp(name, passw, email);
						System.out.println("Welcome "+user.userName);
						signed=true;
						break;
					case 3:
						System.out.println("Enter the service name you want to search for:");
						String Sname=obj1.nextLine();
						searchForService();
						break;
					case 4:
						pay();
						break;
					case 5:
						if(signed) {
							System.out.println("Enter your Credit Card number:");
							String num = obj2.nextLine();
							System.out.println("Enter The fund amount you want to add:");
							double fund = obj2.nextDouble();
							IPayment payment=new CreditPayment();
							payment.pay(fund);
							user.addToWallet(fund);
							System.out.println("You have added "+fund+" to wallet successfully and tthe total fund ypu have noe in wallet = "+user.getWalletAmount());
						}
						else
							System.out.println("You Shold Sign in first !!");
						break;
					case 6:
						if(signed) {
						requestrefund();
						}
						else
							System.out.println("You Shold Sign in first !!");
						break;
					case 7:
						System.out.println("Enter the service name you want to check discount for:");
						name = obj2.nextLine();
						checkDiscount(name);
						break;
					default:
						System.out.println("Wrong input try again!");
						break;	
						
				}
			}
			else if (reply==2)
			{
				System.out.println("1-add service provider\n2-add discount\n3-reply to refund requests list");
				int rep=obj1.nextInt();
				switch(rep) {
				case 1:
					addServiceProvider();
					break;
				case 2:
					 addDiscount();
					break;
				case 3:
					replyrefund();
					break;
				default:
					System.out.println("Wrong input try again!");
					break;
				}
			}
			else
			{
				System.out.println("Wrong input try again!");
				
			}

		}
		
	}
	
}
