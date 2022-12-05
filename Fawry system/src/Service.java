import java.util.*;

public class Service {
	double cost;
	Vector<Provider>providers=new Vector();
	String name;
	double specieficDiscount;
	
    public Service(String name,int cost) {
    	this.name = name;
    	specieficDiscount = 0;
    	this.cost=cost;
	}
    public void setName(String name) {
		 this.name = name;
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
		updateCost();
	}
	public double getSpceficDiscount() {
		return specieficDiscount;
		
	}
	public void SetCost(double cost) {
		this.cost = cost;
		
	}
	public double getCost() {
		return cost;
		
	}
	public void updateCost() {
		double discountvalue = specieficDiscount * cost;
		cost -= discountvalue;
		SetCost(discountvalue);
		
	}
		

}
