import java.util.*;

public class Service {
	double cost;
	Vector<Provider>providers=new Vector();
	String name;
	double specieficDiscount;
	
    public Service() {
    	specieficDiscount=0;
    	cost=0;
	}
	public String getName() {
		return name;
	}
	public void setProvider(Provider pro){
		
		 providers.add(pro);
	}
	public Vector<Provider> getProviders(){
		
		return providers;
	}
	public void setSpceficDiscount(double s) {
		specieficDiscount +=s;
		
	}
	public double getSpceficDiscount() {
		return specieficDiscount;
		
	}
	public double getCost() {
		return cost;
		
	}
	public void updateCost() {
		double discountvalue=specieficDiscount*cost;
		cost-=discountvalue;
	}
		

}
