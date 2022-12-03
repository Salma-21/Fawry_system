import java.util.*;

public class Service {
	Vector<Provider>providers=new Vector();
	String name;
	public String getNAme() {
		return name;
	}
	public boolean add_provider(Provider p) {
		providers.add(p);
		return true;
	}

}
