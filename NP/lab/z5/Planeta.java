public enum Planeta{
	//deklaracija na promenlivi od enum tip
	MERKUR(3.303e+23,2.4397e6),
	VENERA(4.869e+24,6.0518e6),
	ZEMJA(5.976e+24,6.37814e6),
	MARS(6.421e+23,3.3972e6),
	JUPITER(1.9e+27,7.1492e7),
	SATURN(5.688e+25,6.0268e7),
	URAN(8.686e+25,2.5559e7),
	NEPTUN(1.024e+26,2.4746e7);
	private final double masa;
	private final double radius;
	
	Planeta(double masa,double radius){
		this.masa=masa;
		this.radius=radius;
	}
	public double getMasa(){
		return masa;
	}
	public double getRadius(){
		return radius;
	}
	public static final double G=6.67300E-11;
	public double povrsinaGr(){
		return G*masa/(radius*radius);
	}
	public double povsinaTz(double dMasa){
		return dMasa*povrsinaGr();
	}
}