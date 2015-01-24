public class Datum{
	private int mesec;
	private int den;
	private int godina;
	public Datum(int mesecot,int denot,int godinata){
		mesec= proveriMesec(mesecot);
		godina = godinata;
		den=proveriDen(denot);
		System.out.printf("Konstruktor na Datum objekt za datumot %s\n",this);
	}
	private int proveriMesec(int a){
		if(a>0&&a<=12) return a;
		else{
			System.out.printf("Nevalidniot mesec (%d) e postaven na 1.",a);
			return 1;
		}
	}
	private int proveriDen(int a){
		int denPoMesec[]={0,31,28,31,30,31,30,31,31,30,31,30,31};
		if(mesec==2&&a==29&&(godina%400==0||(godina%4==0&&godina%100!=0))) return a;
		else if(a>0&&a<=denPoMesec[mesec]) return a;
		else{
			System.out.printf("Nevalidniot den (%d) e postaven na 1.",a);
			return 1;
		}
	}
	public String toString(){
		return String.format("%d/%d/%d",mesec,den,godina);
	}
}