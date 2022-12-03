
public class ProviderController {
	Provider p;
	
	public ProviderController(Provider p)
	{
		this.p=p;
	}
	
	public void executeHandler(TransactionInfo transinfo,PaymentHistory history) {
		p.command=new Handler(transinfo,history);
		p.command.execute();
		
	}

}
