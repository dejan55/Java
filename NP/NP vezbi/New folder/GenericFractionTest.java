package naprednoVezbi;

import java.util.Scanner;

public class GenericFractionTest {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        double n1 = scanner.nextDouble();
        double d1 = scanner.nextDouble();
        float n2 = scanner.nextFloat();
        float d2 = scanner.nextFloat();
        int n3 = scanner.nextInt();
        int d3 = scanner.nextInt();
        try {
        	GenericFraction<Double, Double> gfDouble = new GenericFraction<Double, Double>(n1, d1);
        	GenericFraction<Float, Float> gfFloat = new GenericFraction<Float, Float>(n2, d2);
        	GenericFraction<Integer, Integer> gfInt = new GenericFraction<Integer, Integer>(n3, d3);
            System.out.printf("%.2f\n", gfDouble.toDouble());
            System.out.println(gfDouble.add(gfFloat));
            System.out.println(gfInt.add(gfFloat));
            System.out.println(gfDouble.add(gfInt));
            gfInt = new GenericFraction<Integer, Integer>(n3, 0);
        } catch(ZeroDenominatorException e) {
            System.out.println(e.getMessage());
        }
        
        scanner.close();
    }

}

// вашиот код овде
class GenericFraction<T extends Number, U extends Number>{
	protected T num;
	protected U denom;
	
	public GenericFraction(T numerator, U denominator) throws ZeroDenominatorException{
		if(denominator.doubleValue()==0)
			throw new ZeroDenominatorException();
		else{
			num = numerator;
			denom = denominator;
		}
	}
	
	public GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) throws ZeroDenominatorException{
		int a, b, c, d, e, f;
		a=num.intValue();
		b=denom.intValue();
		c=gf.num.intValue();
		d=gf.denom.intValue();
		e = d*a + b*c;
		f=b*d;
		GenericFraction<Integer, Integer> nov = new GenericFraction<Integer, Integer>(e, f);
		int k;
		k=nzd(nov.num.intValue(), nov.denom.intValue());
		GenericFraction<Double, Double> nov1 = new GenericFraction<Double, Double>((nov.num.doubleValue()/k), (nov.denom.doubleValue()/k));
		return nov1;
	}
	
	public double toDouble(){
		return num.doubleValue()/denom.doubleValue();
	}
	
	@Override
	public String toString() {
		return String.format("%.2f / %.2f", num.doubleValue(), denom.doubleValue());
	}
	
	public int nzd(int a, int b){
		if(b==0)
			return a;
		else if(a==0)
			return b;
		else{
			if(a>b)
			return nzd(a%b, b);
			else return nzd(b%a, a);
		}
	}
}

class ZeroDenominatorException extends Exception{
	public ZeroDenominatorException(){
		super("Denominator cannot be zero");
	}
}