package naprednoVezbi;

import java.util.Scanner;

public class TripleTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		Triple<Integer> tInt = new Triple<Integer>(a, b, c);
		System.out.printf("%.2f\n", tInt.max());
		System.out.printf("%.2f\n", tInt.average());
		tInt.sort();
		System.out.println(tInt);
		float fa = scanner.nextFloat();
		float fb = scanner.nextFloat();
		float fc = scanner.nextFloat();
		Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
		System.out.printf("%.2f\n", tFloat.max());
		System.out.printf("%.2f\n", tFloat.average());
		tFloat.sort();
		System.out.println(tFloat);
		double da = scanner.nextDouble();
		double db = scanner.nextDouble();
		double dc = scanner.nextDouble();
		Triple<Double> tDouble = new Triple<Double>(da, db, dc);
		System.out.printf("%.2f\n", tDouble.max());
		System.out.printf("%.2f\n", tDouble.average());
		tDouble.sort();
		System.out.println(tDouble);
	}
}

// vasiot kod ovde
// class Triple
class Triple<T extends Number> {
	protected T prv;
	protected T vtor;
	protected T tret;

	public Triple(T p, T v, T t) {
		prv = p;
		vtor = v;
		tret = t;
	}

	public double max() {
		double a;
		if (prv.doubleValue() > vtor.doubleValue()) {
			if (prv.doubleValue() > tret.doubleValue())
				a = prv.doubleValue();
			else
				a = tret.doubleValue();
		} else if (vtor.doubleValue() > tret.doubleValue())
			a = vtor.doubleValue();
		else
			a = tret.doubleValue();
		return a;
	}

	public double average() {
		return (prv.doubleValue() + vtor.doubleValue() + tret.doubleValue()) / 3;
	}

	public void sort() {
		if (prv.doubleValue() > vtor.doubleValue()){
			T tmp = prv;
			prv = vtor;
			vtor = tmp;
		}
			if (vtor.doubleValue() > tret.doubleValue()){
				T tmp = vtor;
				vtor = tret;
				tret = tmp;
			}
			if (prv.doubleValue() > vtor.doubleValue()){
				T tmp = prv;
				prv = vtor;
				vtor = tmp;
			}
	}
	
	@Override
	public String toString() {
		return String.format("%.2f %.2f %.2f", prv.doubleValue(), vtor.doubleValue(), tret.doubleValue());
	}
}
