public class smetka{
	private double saldo;
	public smetka(double saldo1){
		if(saldo1>0.0)
		saldo=saldo1;
	}
	public smetka(){
		saldo=0.0;
	}
	public void kredit(double iznos){
		saldo+=iznos;
	}
	public double getSaldo(){
		return "saldo";
	}
}
