import java.util.Scanner;
public class z2{ // Prosek za sredno
public static void main(String[] args){
	Scanner in=new Scanner(System.in);
	int a[][]=new int[4][];
	double brV=0;
	for(int i=0;i<4;i++){
		System.out.println("Kolku predmeti imas vo "+(i+1)+" godina?");
		int n=in.nextInt();
		a[i]=new int[n];
		double brG=0;
		for(int j=0;j<n;j++){
			a[i][j]=in.nextInt();
			brG+=a[i][j];}
		double prosek=brG/n;
		brV+=prosek;
		System.out.printf("Prosekot za %d godina e: %.2f\n", i+1, brG/n);
		}
		System.out.print("Vkupniot uspex e "+ brV/4);
}
}