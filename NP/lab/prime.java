import java.util.Scanner;
public class prime{
public static void main(String[] args){
	Scanner in=new Scanner(System.in);
	int n=in.nextInt();
	for(int i=1;i<=n;i++){
		boolean pr=true;
		if(i%2==0) continue;
		for(int j=2;j<i;j++)
		if(i%j==0) pr=false;
		if(pr) System.out.print(i + "\t");
	}
}
}