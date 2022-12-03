import java.util.Scanner;
import java.util.Vector;

public class Boundry {
	Sign sign;
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
