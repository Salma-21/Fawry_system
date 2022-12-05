import java.util.Scanner;
import java.util.Vector;

public class Boundry {
	static AllServicesController allServicesController=new AllServicesController();
	static Sign sign;
	static TransactionProcess trans=new TransactionProcess();
	static PaymentHistory paymentHistory=PaymentHistory.getInstance();
	static Provider provider;
	static ProviderController providerController;
	static RefundRequestController refundRequestController = new RefundRequestController(paymentHistory);
	static Admin admin = new Admin(refundRequestController);
	static SignController signContoller=new SignController();;
	static User user;
	static TotalDiscount totalDIscount=new TotalDiscount() ; 
	static AllMainServicesControlles allMainServicesControlles = new AllMainServicesControlles() ; 
	
	public static void setUser(User userr)
	{
		user=userr;
	}
	public static void displayMainService()
	{
		for(int i=0; i < allMainServicesControlles.allMainservices.getmainservices().size();i++)
		{
			System.out.println(allMainServicesControlles.allMainservices.getmainservices().get(i).getName() + "\n" );
		}
	}
	public static void displayService(Vector<Service>s)
	{
		if(s != null) {
		  for(int i=0;i<s.size();i++){
			System.out.println(s.get(i).getName());
		  }
		}
		else {
			System.out.println("There is no services");
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
	    provider = trans.choose_Provider(providerName,service);
		providerController = new ProviderController(provider);
		
		//choose payment method
		
		boolean b=trans.choose_Payment(provider);
		 System.out.println("this provider allow the following payment method:");
		 System.out.println("1-Credit Card\n2-Wallet");
		 if(b)
			 System.out.println("3-Cash\n");
		 
		 System.out.println("Enter the payment method name you want :");
		 String paymentName = obj1.nextLine();
		 double cost =  getTotalCost(user,service);
		 boolean bool =true;
		 if(user.wallet.getAmount()<cost && paymentName.equals("Wallet"))
		 {
			 System.out.println("oops! your funds in wallet less than you want to pay you can choose diffrent method or add fund to wallet");
			 bool=false;
		}
			 trans.create_payment_method(paymentName , user.wallet,cost);
		 
		 //fill provider form
		 if(bool) 
		 {
			 Vector<String>answer=fillForm(provider.form);
			 
			 //save transaction
			  //check total discount
			 TransactionInfo transinfo=trans.set_transaction_info(user.id,serviceName,providerName,paymentName,cost,answer);
			 System.out.println("this is your Transaction id you will need it if you want to refund : "+transinfo.TID);
			 
			 Command command = new AddTransaction(transinfo,paymentHistory);
			trans.handel_transaction(command);
			user.increaseTimesOfPay();
			System.out.println(" ****Congratulation you have finished the payment process**** ");
		    
		 }
		 
	}
	public static double getTotalCost(User user,Service service)
	{
		  return totalDIscount.getTotalCost(user, service);
	}
	
	///Maram  
	
	public static void displayrefundRequest(TransactionInfo refundRequests) {
		 System.out.println("userID : " + refundRequests.userID);
		 System.out.println("servicename : " + refundRequests.servicename);
		 System.out.println("providername : " + refundRequests.providername);
		 System.out.println("paymentmethod : " + refundRequests.paymentmethod);
		 System.out.println("payamount : " +refundRequests.payamount);
		 Service service = allServicesController.search(refundRequests.servicename);
		 provider = trans.choose_Provider(refundRequests.providername,service);
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
	    service = allServicesController.search(str);
		//System.out.println(service.getName());
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
		service = searchForService();
		//System.out.println(service.getName());
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
	    	System.out.println("Enter field"+(i+1)+":");
	    	Scanner obj5 = new Scanner(System.in);
	    	String string = obj5.nextLine();
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
			service.setSpceficDiscount(discount/100.0);
			System.out.println("You have set this discount successfuly ");
		}
		else if(x==2)
		{
			System.out.println("Enter The number of payment times for user to apply this discount : ");
			int num = obj1.nextInt();
			System.out.println("Enter The amount of the discount: ");
			double discount = obj1.nextDouble();
			totalDIscount.setOverallDiscount(num, discount);
			System.out.println("You have set this discount successfuly ");
		}
		else
			System.out.println("Wrong input try again!");
			
	}
	public static  User  signIn(String username,String pass)
	{
		return signContoller.signIn(username,pass);
	}

	
	public static void checkDiscount(String serviceName)
	{
		Service service;
		service=allServicesController.search(serviceName);
		System.out.println(service.getSpceficDiscount()*100+"%");
	}
	
	
	public static void main(String[] args) {
		
		// User 1
		
		signContoller.signUp("yomna","12345","yomna@");
		signContoller.signUp("maram" ,"12345","maram@"); 
		signContoller.signUp("salma" ,"12345","salmaa@"); 
		signContoller.signUp("maryam" ,"12345","maryam@"); 
		
		// Vector<String> v
		Vector<String> v = new Vector();
		v.add("Your mobile: ");	
		v.add("amount: ");
		
		//providers
		Provider p = new Provider("vodafoneCash", v,false); 
		
	    //  Service1
		MainServices mainServices1 = new MainServices();
		
		mainServices1.setservicename("Mobile recharge services");
		allMainServicesControlles.AddService(mainServices1);
		Service s1 = new Service("Vodafone",50);
		allServicesController.AddService(s1);
		s1.setProvider(p);
		mainServices1.addService(s1);
		Service s2 = new Service("Etisalat",60);
		allServicesController.AddService(s2);
		s2.setProvider(p);
		mainServices1.addService(s2);
		Service s3 = new Service("Orange",70);
		allServicesController.AddService(s3);
		s3.setProvider(p);
		mainServices1.addService(s3);
		Service s4 = new Service("We",40);
		allServicesController.AddService(s4);
		mainServices1.addService(s4);
		
	  //  Service2
		MainServices mainServices2 = new MainServices();
		mainServices2.setservicename("Internet Payment services");
		allMainServicesControlles.AddService(mainServices2);
		Service s5 = new Service("Vodafone",50);
		allServicesController.AddService(s5);
		mainServices2.addService(s5);
		Service s6 = new Service("Etisalat",60);
		allServicesController.AddService(s6);
		mainServices2.addService(s6);
		Service s7 = new Service("Orange",70);
		allServicesController.AddService(s7);
		mainServices2.addService(s7);
		Service s8 = new Service("We",40);
		allServicesController.AddService(s8);
		mainServices2.addService(s8);
		
	//  Service3	
		MainServices mainServices3 = new MainServices();
		mainServices3.setservicename("Landline services");
		allMainServicesControlles.AddService(mainServices3);
		Service s9 = new Service("Monthly receipt",200);
		allServicesController.AddService(s9);
		mainServices3.addService(s9);
		Service s10 = new Service("Quarter receipt",400);
		allServicesController.AddService(s10);
		mainServices3.addService(s10);
		
	//  Service4	
		MainServices mainServices4 = new MainServices();
		mainServices4.setservicename("Donations");
		allMainServicesControlles.AddService(mainServices4);
		Service s11 = new Service("Cancer Hospital",30);
		allServicesController.AddService(s11);
		s11.setProvider(p);
		mainServices4.addService(s11);
		Service s12 = new Service("Schools",20);
		allServicesController.AddService(s12);
		mainServices4.addService(s12);
		Service s13 = new Service("NGOs",15);
		allServicesController.AddService(s13);
		mainServices4.addService(s13);
		
		
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
				System.out.println("1-sign in \n2-sign up");
			    reply=obj1.nextInt();
			    boolean flag = true;
			    do {
					switch(reply) {
						case 1:
							System.out.println("Enter your user name:");
							Scanner username1 = new Scanner(System.in);
							String username= username1.nextLine();
							System.out.println("Enter your Password:");
							Scanner pass1 = new Scanner(System.in);
							String pass = pass1.nextLine();
							user=signIn(username,pass);
							if(user!=null)
							{
								System.out.println("Welcome "+user.userName);
								setUser(user);
								//signed=true;
								flag = false;
							}
							else
								System.out.println(" oops! we are not found you can to sign up");
							
							break;
						case 2:
							System.out.println("Enter your user name:");
							Scanner name1 = new Scanner(System.in);
							String name =name1.nextLine();
							System.out.println("Enter yourPassword:");
							Scanner passw1 = new Scanner(System.in);
							String passw = passw1.nextLine();
							System.out.println("Enter your email:");
							Scanner email1 = new Scanner(System.in);
							String email=email1.nextLine();
							user=signContoller.signUp(name, passw, email);
							System.out.println("Welcome "+user.userName);
							signed=true;
							setUser(user);
							flag = false;
						break;
					}
			    }while(flag == true);
				System.out.println("1-search for service by name \n2-pay for service \n3-add funds to wallet \n4-ask for refund \n5-check discount for services ");
			    reply=obj1.nextInt();
				switch(reply) {
					 case 1:
							System.out.println("Enter the Main service name you want to search for:");
							Scanner obj6 = new Scanner(System.in);
							String Sname = obj6.nextLine();
							displayService(allMainServicesControlles.search(Sname));
					  break;
					  case 2:
						   pay();
					  break;
					  case 3:
							System.out.println("Enter your Credit Card number:");
							String num = obj2.nextLine();
							System.out.println("Enter The fund amount you want to add:");
							double fund = obj2.nextDouble();
							IPayment payment=new CreditPayment();
							payment.pay(fund);
							user.addToWallet(fund);
							System.out.println("You have added "+fund+" to wallet successfully and the total fund ypu have noe in wallet = "+user.getWalletAmount());
					break;
					case 4:
						requestrefund();
					break;
					case 5:
						System.out.println("Enter the service name you want to check discount for:");
						Scanner obj5 = new Scanner(System.in);
						String name = obj5.nextLine();
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

