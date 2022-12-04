import java.util.*;

public class Service {
	double cost;
	Vector<Provider>providers=new Vector();
	String name;
	double specieficDiscount;
	
    public Service() {
    	specieficDiscount=0;
	}
	public String getName() {
		return name;
	}
	public Vector<Provider> getProviders(){
		
		return providers;
	}
	public void setSpceficDiscount(double s) {
		specieficDiscount +=s;
		
	}
	public void updateCost() {
		double discountvalue=specieficDiscount*cost;
		cost-=discountvalue;
	}
		

}
