import java.util.Scanner;
public class z3{
public static void main(String[] args){
	Scanner in=new Scanner(System.in);
	smetka s=new smetka(50.00);
	smetka s2=new smetka(-7.53);
	System.out.printf( "saldo na smetkata 1: $%.2f\n", s.getSaldo() );
	System.out.printf( "saldo na smetkata 2: $%.2f\n", s2.getSaldo() );
	System.out.print( "Vnesete iznos na depozit za smetkata 1: " );
	double deposit=in.nextDouble();
	System.out.printf( "\nse dodavaat %.2f kon saldoto na smetkata 1\n\n", deposit );
	s.kredit(deposit);
	System.out.printf( "saldo na smetkata 1: $%.2f\n", s.getSaldo() );
	System.out.print( "Vnesete iznos na depozit za smetkata 2: " );
	deposit=in.nextDouble();
	System.out.printf( "\nse dodavaat %.2f kon saldoto na smetkata 2\n\n", deposit );
	s2.kredit(deposit);
	System.out.printf( "saldo na smetkata 2: $%.2f\n", s2.getSaldo() );
}
}