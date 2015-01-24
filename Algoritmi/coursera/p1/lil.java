import java.util.Scanner;
public class lil{
public static void main(String[] args){
	Scanner in=new Scanner(System.in);
	System.out.println("Vnesi N");
	int n=in.nextInt();
	int t=StdRandom.uniform(1,n);
	System.out.println(t);
}
}