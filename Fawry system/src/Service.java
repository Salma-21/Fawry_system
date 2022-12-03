import java.util.*;

public class Service {
	double cost;
	Vector<Provider>providers=new Vector();
	String name;
	public String getName() {
		return name;
	}
	public boolean add_provider(Provider p) {
		providers.add(p);
		return true;
	}
	public boolean remove_provider(Provider p) {
		for(int i =0 ;i<providers.size();i++) {
			if(providers.get(i).getName() == p.getName()) {
				providers.remove(p);
				return true;
			}
		}
		return false;
		
	}
	public Vector<Provider> getProviders(){
		
		return providers;
	}
	public Provider SearchProvider(String providername) {
		for(int i =0 ;i<providers.size();i++) {
			if(providers.get(i).getName() == providername) {
				return providers.get(i);
			}
		
	    }
		return null;
	}
	

}
