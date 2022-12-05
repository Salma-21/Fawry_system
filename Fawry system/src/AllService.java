import java.util.*;

public class AllService {
	Vector<Service>services = new Vector();
	private static AllService obj;
	private AllService() {}
	public static AllService getInstance()
    {
        if (obj==null)
            obj = new AllService();
        return obj;
    }
	public void AddService(Service s){
		services.add(s);
	}
	
}
