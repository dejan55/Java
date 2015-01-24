import java.util.Scanner;
public class z6{
	public static final int MAX=100;
	public static void sort(Vraboten[] niza,int n){
		int i,j;
		Vraboten v;
		for(i=0;i<n-1;i++)
			for(j=i;j<n;j++)
				if(niza[i].getPlata()<niza[j].getPlata()){
					v=niza[i];
					niza[i]=niza[j];
					niza[j]=v;
				}
	}
public static void main(String[] args){
	Scanner in=new Scanner(System.in);
	Vraboten[] niza=new Vraboten[MAX];
	int n,plata,i;
	String ime;
	short poz;
	System.out.println("Vneseto go brojot na vraboteni: ");
	n=in.nextInt();
	/*for(i=0;i<n;i++){
		niza[i]=new Vraboten();
		System.out.print("Vnesi ime:");
		ime=in.next();
		niza[i].setIme(ime);
		System.out.print("Vnesi plata:");
		plata=in.nextInt();
		niza[i].setPlata(plata);
		System.out.print("Vnesi pozicija (0-vraboten, 1-direktor, 2- shef :");
		poz=in.nextShort();
		if(poz==0) niza[i].setPoz(Pozicija.VRABOTEN);
		else if(poz==1) niza[i].setPoz(Pozicija.DIREKTOR);
		else niza[i].setPoz(Pozicija.SHEF);
		} */
		for(i=0;i<n;i++){
		System.out.print("Vnesi ime:");
		ime=in.next();
		System.out.print("Vnesi plata:");
		plata=in.nextInt();
		System.out.print("Vnesi pozicija (0-vraboten, 1-direktor, 2- shef :");
		poz=in.nextShort();
		Pozicija p[]=Pozicija.values();
		niza[i]=new Vraboten(ime,plata,p[poz]);
		}
		z6.sort(niza,n); // povik do staticki metod
		for(i=0;i<n;i++)
			System.out.printf("%d %s %d %s\n",i+1,niza[i].getIme(),niza[i].getPlata(),niza[i].getPoz());
		
		
	}
}
