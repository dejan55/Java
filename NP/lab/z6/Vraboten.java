public class Vraboten{
	private String ime;
	private int plata;
	private Pozicija poz;
	public Vraboten(){
		this(null,0,null); //Default constructor
	}
	public Vraboten(String ime1,int plata1,Pozicija po){
		ime=ime1;
		plata=plata1;
		poz=po;
	}
	public void setIme(String i){
		this.ime=i;
	}
	public void setPlata(int a){
		this.plata=a;
	}
	public void setPoz(Pozicija poz){
		this.poz=poz;
	}
	public String getIme(){
		return ime;
	}
	public int getPlata(){
		return plata;
	}
	public Pozicija getPoz(){
		return poz;
	}
}