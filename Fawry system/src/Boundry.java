import java.util.Scanner;
import java.util.Vector;

public class Boundry {
	Sign sign;
	TransactionProcess trans=new TransactionProcess();
	PaymentHistory paymentHistory=new PaymentHistory();
	Provider provider;
	ProviderController providerController;
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
		 trans.create_payment_method(paymentName);
		 
		 //fill provider form
		 
		 Vector<String>answer=fillForm(provider.form);
		 
		 //save transaction
		 
		 double amount=0; //check total discount
		 TransactionInfo transinfo=trans.set_transaction_info(user.id,serviceName,providerName,paymentName,amount,answer);
		 
		trans.handel_transaction(providerController,transinfo,paymentHistory);
		user.increaseTimesOfPay();
		System.out.println(" ****Congratulation you have finished the payment process**** ");
	    
	}
	
	
	//search
	public Service searchForService() {
		Service service;
		System.out.println("Enter service name:");
		Scanner obj1 = new Scanner(System.in);
	    String str = obj1.nextLine();
	    ServiceController ser=new ServiceController();
	    service=ser.search(str);
	    System.out.println(service); 
	    return service;
	}
	
	//add provider
	public void addServiceProvider() {
		Service s=new Service();
		s=searchForService();
		System.out.println("Enter provider name:");
		Scanner obj1 = new Scanner(System.in);
	    String provider_name = obj1.nextLine();
	    System.out.println("Enter num of form fields:");
	    int size = obj1.nextInt();
	    Vector<String> vec=new Vector();
	    for (int i=0; i<size ;i++)
		{
	    	System.out.println("Enter field"+i+1+":");
	    	String string = obj1.nextLine();
	    	vec.add(string);
		}
	    //Provider p=new Provider(provider_name,vec,);
	   s.add_provider(p);
		
	}
	
	
	

}
