import java.util.Scanner;
public class z1{
public static void main(String[] args){
	Scanner in=new Scanner(System.in);
	System.out.println("Vneset od 1-7 za izbor na den od nedelata");
	byte n=in.nextByte();
	Den p[]=Den.values();
	if(n<1||n>7) System.out.print("Gresno vnesen den");
	else if (n<6) System.out.printf("%s e raboten",p[n]);
	else  System.out.printf("%s e neraboten",p[n]);
}
}