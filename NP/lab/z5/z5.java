import java.util.Scanner;
public class z5{
public static void main(String[] args){
	Scanner in=new Scanner(System.in);
	System.out.println("Vneseste ja vashata tezina vo kg: ");
	double kg=in.nextDouble();
	Planeta planeta=Planeta.ZEMJA;
	double masa=kg/planeta.povrsinaGr();
	/*for( Planeta p: Planeta.values() )
		System.out.printf("Vashata tezhina na %s e %f \n",p,p.povsinaTz(masa)); */
	Planeta p[]=Planeta.values();
	for(int i=0;i<8;i++){
		
		System.out.printf("Vashata tezhina na %s e %f \n",p[i],p[i].povsinaTz(masa)); }
}
}