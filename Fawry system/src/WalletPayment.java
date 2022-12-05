
public class WalletPayment implements IPayment {
	Wallet wallet;
	
	public WalletPayment(Wallet wallet) {
		this.wallet = wallet;
	}
	
	@Override
	public void pay(double amount) {
		wallet.consumeAmount(amount);
		
	}

	@Override
	public void refund(double amount) {
		wallet.addAmount(amount);
	}
	

}
