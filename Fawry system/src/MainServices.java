import java.util.Vector;

public class MainServices {
	
	 String servicename;
	 Vector<Service>services=new Vector();
	 
	 public void addService(Service services) {
		 this.services.add(services);
	 }
	 
	 public Vector<Service> getSubService() {
		   return services;
	 }
	 
	 public void setservicename(String servicename) {
	    	this.servicename = servicename;
	 }
	 
     public String getName() {
    	return servicename;
     }
	
}
