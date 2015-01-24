import java.util.Arrays;
import java.util.Scanner;

class Bank{
	private String name;
	public Account[] accounts;
	private double sum1,sum2;
	private int k,j;
	public Bank(String name,Account accounts[]){
		this.name=name;
		sum1=sum2=0;
		this.accounts=new Account[accounts.length];
		for(int i=0;i<accounts.length;i++)
			this.accounts[i]=accounts[i];
	}
	public boolean makeTransaction(Transaction t){
		int br=0;
		for(int i=0;i<accounts.length;i++) {
		if(accounts[i].getID()==t.getFromAccountID()) {br++; j=i;} 
		if(accounts[i].getID()==t.getToAccountID()) {br++; k=i;}
		}
		if(br<2) return false;
		double p1,p2;
		p1=Double.parseDouble(t.getAmount().replaceAll("[\\D]", ""))/100;
		p2=Double.parseDouble(t.getP().replaceAll("[\\D]", ""))/100;
		double j1=Double.parseDouble(accounts[j].getBalance().replaceAll("[\\D]", ""))/100;
		if(j1-p2<=0) return false;
		double sum=p1+p2;
		double k1=(Double.parseDouble(accounts[k].getBalance().replaceAll("[\\D]", ""))/100)+p1;
		if(k==j) { j1=j1-p2; accounts[j].setBalance(String.format("%.2f$",j1)); }
		else {
		j1=j1-sum;
		accounts[k].setBalance(String.format("%.2f$",k1));
		accounts[j].setBalance(String.format("%.2f$",j1)); }
		sum1+=p1;
		sum2+=p2;
		return true;
	}
	public String totalTransfers(){
		return String.format("%.2f$",sum1);
	}
	public String totalProvision(){
		return String.format("%.2f$",sum2);
	}
	public String getN(){
		return name;
	}
	public boolean equals(Bank o){
		if(o==null) return false;
		if(name!=o.getN()) return false;
		if(accounts.length!=o.accounts.length) return false;
		for(int i=0;i<accounts.length;i++)
			if(!this.accounts[i].equals(o.accounts[i])) return false;
		return true;
	}
	public String toString(){
		String s=String.format("%s\n\n",name);
		for(int i=0;i<accounts.length;i++)
			s+=accounts[i]+"\n";
		return s;
	}
}


class Transaction{
	private final long fid,tid;
	private String descr,amount,prov;
	public Transaction(long from_id, long to_id,String descr,String amount){
		fid=from_id;
		tid=to_id;
		this.descr=descr;
		this.amount=amount;
	}
	public String getAmount(){
		return this.amount;
	}
	public long getFromAccountID(){
		return fid;
	}
	public long getToAccountID(){
		return tid;
	}
	public String getDescription(){
		return descr;
	}
	protected void setProv(String a){
		prov=a;
	}
	public String getP(){
		return prov;
	}
	public String toString(){
		return String.format("Amount:%s\nProvision:%s\nDescription:%s\nFrom:%d\nTo:%d\n",amount,prov,descr,fid,tid);
	}
}


class FlatAmountProvisionTransaction extends Transaction{
	private String flat;
	private static final String s="FlatAmount";
	public FlatAmountProvisionTransaction(long from_id, long to_id,String amount, String flat_amount_provision){
		super(from_id,to_id,s,amount);
		flat=flat_amount_provision;
		super.setProv(flat);
	}
	public String getFlatAmount(){
		return flat;
	}
	boolean equals(FlatAmountProvisionTransaction o){
		if(o==null) return false;
		if(super.getFromAccountID()!=o.getFromAccountID()) return false;
		if(super.getAmount()!=o.getAmount()) return false;
		if(super.getToAccountID()!=o.getToAccountID()) return false;
		if(flat!=getFlatAmount()) return false;
		return true;
	}
}


class FlatPercentProvisionTransaction extends Transaction{
	private int cents;
	private static final String s="FlatPercent";
	public FlatPercentProvisionTransaction(long from_id, long to_id,String amount, int cents_per_dolar){
		super(from_id,to_id,s,amount);
		cents=cents_per_dolar;
		super.setProv(cal());
	}
	public int getPercent(){
		return cents;
	}
	private String cal(){
		int a=Integer.parseInt(super.getAmount().replaceAll("[\\D]", ""));
		a/=100;
		double c=(a*cents)/100.00;
		return String.format("%.2f$",c);
	}
	boolean equals(FlatPercentProvisionTransaction o){
		if(o==null) return false;
		if(super.getFromAccountID()!=o.getFromAccountID()) return false;
		if(super.getAmount()!=o.getAmount()) return false;
		if(super.getToAccountID()!=o.getToAccountID()) return false;
		if(cents!=getPercent()) return false;
		return true;
	}
}


class Account{
	private String name;
	private String balans;
	private long iD;
	private static int i=0;
	public Account(String user_name,String initial_balance){
		name=user_name;
		balans=initial_balance;
		this.iD=i++;
	}
	public String getBalance(){
		return balans;
	}
	public String getUserName(){
		return name;
	}
	public long getID(){
		return iD;
	}
	public void setBalance(String balance){
		balans=balance;
	}
	boolean equals(Account o){
		if(o==null) return false;
		if(name!=o.getUserName()) return false;
		if(balans!=o.getBalance()) return false;
		if(iD!=o.getID()) return false;
		return true;
	}
	public String toString(){
		return String.format("Name:%s\nBalance:%s\n",name,balans);
	}
}


public class BankTester {
	
	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		String test_type = jin.nextLine();
		switch ( test_type ) {
			case "typical_usage" : 
				testTypicalUsage(jin); 
				break;
			case "equals":
				testEquals(); 
				break;
		}
		jin.close();
	}
	
	private static void testEquals() {
		Account a1 = new Account("Andrej","20.00$");
		Account a2 = new Account("Andrej","20.00$");
		Account a3 = new Account("Andrej","30.00$");
		Account a4 = new Account("Gajduk","20.00$");
		if ( ! ( a1.equals(a1) 
			&& ! a1.equals(a2)
			&& ! a2.equals(a1)
			&& ! a3.equals(a1)
			&& ! a4.equals(a1)
			&& ! a1.equals(null) ) ) {
			System.out.println("Your account equals method does not work properly."); 
			return;
		}
		if ( a1.getID() == a2.getID() || a1.getID() == a3.getID() || a1.getID() == a4.getID()
			|| a2.getID() == a3.getID() || a2.getID() == a4.getID() || a3.getID() == a4.getID()	) {
			System.out.println("Different accounts have the same IDS. This is not allowed");
			return;
		}
		FlatAmountProvisionTransaction fa1 = new FlatAmountProvisionTransaction(10, 20, "20.00$", "10.00$");
		FlatAmountProvisionTransaction fa2 = new FlatAmountProvisionTransaction(10, 20, "20.00$", "10.00$");
		FlatAmountProvisionTransaction fa3 = new FlatAmountProvisionTransaction(20, 10, "20.00$", "10.00$");
		FlatAmountProvisionTransaction fa4 = new FlatAmountProvisionTransaction(10, 20, "50.00$", "50.00$");
		FlatAmountProvisionTransaction fa5 = new FlatAmountProvisionTransaction(30, 40, "20.00$", "10.00$");
		FlatPercentProvisionTransaction fp1 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
		FlatPercentProvisionTransaction fp2 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
		FlatPercentProvisionTransaction fp3 = new FlatPercentProvisionTransaction(20, 10, "20.00$", 10);
		FlatPercentProvisionTransaction fp4 = new FlatPercentProvisionTransaction(10, 20, "50.00$", 10);
		FlatPercentProvisionTransaction fp5 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 30);
		FlatPercentProvisionTransaction fp6 = new FlatPercentProvisionTransaction(30, 40, "20.00$", 10);
		if ( fa1.equals(fa1) &&
		   ! fa2.equals(null) &&
			 fa2.equals(fa1) &&
			 fa1.equals(fa2) &&
			 fa1.equals(fa3) &&
		   ! fa1.equals(fa4) &&
		   ! fa1.equals(fa5) &&
		   ! fa1.equals(fp1) &&
		     fp1.equals(fp1) &&
		   ! fp2.equals(null) &&
			 fp2.equals(fp1) &&
			 fp1.equals(fp2) &&
			 fp1.equals(fp3) &&
		   ! fp1.equals(fp4) &&
		   ! fp1.equals(fp5) &&
		   ! fp1.equals(fp6) ) {
			System.out.println("Your transactions equals methods do not work properly."); 
			return;
		}
		Account accounts[] = new Account[] { a1,a2,a3,a4 };
		Account accounts1[] = new Account[] { a2,a1,a3,a4 };
		Account accounts2[] = new Account[] { a1,a2,a3 };
		Account accounts3[] = new Account[] { a1,a2,a3,a4 };
		
		Bank b1 = new Bank("Test",accounts);
		Bank b2 = new Bank("Test",accounts1);
		Bank b3 = new Bank("Test",accounts2);
		Bank b4 = new Bank("Sample",accounts);
		Bank b5 = new Bank("Test",accounts3);
		if (! ( b1.equals(b1) &&
		    ! b1.equals(null) &&
			! b1.equals(b2) &&
			! b2.equals(b1) &&
			! b1.equals(b3) &&
			! b3.equals(b1) &&
			! b1.equals(b4) &&
			  b1.equals(b5)  )) {
			System.out.println("Your bank equals method do not work properly."); 
			return;
		}	
		accounts[2] = a1;
		if ( ! b1.equals(b5) )  {
			System.out.println("Your bank equals method do not work properly."); 
			return;
		}
		long from_id = a2.getID();
		long to_id = a3.getID();
		Transaction t = new FlatAmountProvisionTransaction(from_id,to_id,"3.00$","3.00$");
		b1.makeTransaction(t);
		if ( !b1.equals(b5) )  {
			System.out.println("Your bank equals method do not work properly."); 
			return;
		}
		b5.makeTransaction(t);
		if ( ! b1.equals(b5) )  {
			System.out.println("Your bank equals method do not work properly."); 
			return;
		}
        System.out.println("All your equals methods work properly.");
	}

	private static void testTypicalUsage(Scanner jin) {
		String bank_name = jin.nextLine();
		int num_accounts = jin.nextInt();jin.nextLine();
		Account accounts[] = new Account[num_accounts];
		for ( int i = 0 ; i < num_accounts ; ++i ) 
			accounts[i] = new Account(jin.nextLine(),jin.nextLine());
		Bank bank = new Bank(bank_name,accounts);
		while ( true ) {
			String line = jin.nextLine();
			switch ( line ) {
				case "stop":
					return;
				case "transaction":
					String descrption = jin.nextLine();
					String amount = jin.nextLine();
					String parameter = jin.nextLine();
					int from_idx = jin.nextInt();
					int to_idx = jin.nextInt();
					jin.nextLine();
					Transaction t = getTransaction(descrption,from_idx,to_idx,amount,parameter,bank);
					System.out.println("Transaction amount:"+t.getAmount());
					System.out.println("transaction description:"+t.getDescription());
					System.out.println("Transaction succesfull? "+bank.makeTransaction(t));
					break;
				case "print":
					System.out.println(bank.toString());
					System.out.println("Total provisions:"+bank.totalProvision());
					System.out.println("Total transfers:"+bank.totalTransfers());
					System.out.println();
					break;
			}
		}
	}
	
	private static Transaction getTransaction(String description,int from_idx,int to_idx,String amount,String o,Bank bank) {
		switch ( description ) {
			case "FlatAmount":
				return new FlatAmountProvisionTransaction(bank.accounts[from_idx].getID(), bank.accounts[to_idx].getID(), amount,o);
			case "FlatPercent":
				return new FlatPercentProvisionTransaction(bank.accounts[from_idx].getID(), bank.accounts[to_idx].getID(), amount,Integer.parseInt(o));
		}
		return null;
	}
	
	

}
