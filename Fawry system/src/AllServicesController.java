
public class AllServicesController {
	
    AllService allService = AllService.getInstance();
	
	public Service search(String str){
		Service s = null;
		for (int i=0; i<allService.services.size() ;i++)
		{
			if (allService.services.get(i).getName().equals(str))
			{
				s = allService.services.get(i);
			}
		}
		return s;
	}
	public void AddService(Service s){
		allService.services.add(s);
	}
	
}
