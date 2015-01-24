public class Vraboten{
	private String ime;
	private String prezime;
	private Datum datumNaRaganje;
	private Datum datumNaVrabotuvanje;
	
	public Vraboten(String imeto,String prezimeto,Datum rodenden,Datum denKogaEVraboten){
		ime=imeto;
		prezime=prezimeto;
		datumNaRaganje=rodenden;
		datumNaVrabotuvanje=denKogaEVraboten;
	}
	public String toString(){
		return String.format("%s, %s Vraboten na: %s Rodenden: %s",prezime,ime,datumNaVrabotuvanje,datumNaRaganje);
	}
}