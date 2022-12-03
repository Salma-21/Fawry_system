
public class AllServicesController {
	
AllService allService=new AllService();
	
	public Service search(String str){
		
		Service s=new Service();
		for (int i=0; i<allService.services.size() ;i++)
		{
			if (allService.services.get(i).getName()== str)
			{
				s=allService.services.get(i);
			}
		}
		return s;
	}

}
