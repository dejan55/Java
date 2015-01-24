import java.util.Scanner;
public class z1{
public static void main(String[] args){
	Scanner in=new Scanner(System.in);
	int n=in.nextInt();
	int a[]=new int[n];
	for(int i=0;i<n;i++)
		a[i]=in.nextInt();
	int max=0;
	for(int i=0;i<n;i++)
		for(int j=0;j<i;j++)
		if(Math.abs(a[j]-a[i])>max) max=Math.abs(a[j]-a[i]);
	System.out.print("Najgolemoto rastojanie e "+max);
}
}