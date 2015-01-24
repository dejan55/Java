import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
class ArrayReader {
    public static IntegerArray readIntegerArray(InputStream input) {
        int b[]=null;
        try{
        int n=input.read()-48;
        while(n<0||n>9) n=input.read()-48;
		int m=input.read()-48;
		if(m>=0&&m<10) n=n*10+m;
        if(n==0) return new IntegerArray();
		b=new int[n];
        int i=0;
		int data=input.read();
		boolean mi=false;
		while(data!=-1) {
			int br=data-48;
			if(data==45) mi=true;
			if(br<0||br>9) {data=input.read(); continue; }
			int c=input.read()-48;
			while(c>=0&&c<10) {br=br*10+c; c=input.read()-48; }
			if(mi==true) {b[i++]=-br; mi=false; }
			else b[i++]=br;
			data=input.read(); }
        }
        
        catch(IOException e){} 
        return new IntegerArray(b);
    }

}
final class IntegerArray{
    private final int niza[];
	public IntegerArray(){
		niza=null;
	}
    public IntegerArray(int a[]){
    	niza=new int[a.length];
        for(int i=0;i<a.length;i++)
            this.niza[i]=a[i];
    }
    public int length(){
    	if(niza==null) return 0;
		return niza.length;
    }
    public int getElementAt(int i){
    	return niza[i];
    }
    public int sum(){
    	if(niza==null) return 0;
		int sum=0;
        for(int i:niza) sum+=i;
        return sum;
    }
    public double average(){
		return sum()/(double)length();
    }
    public IntegerArray getSorted(){
    	if(niza==null) return new IntegerArray();
		int n[]=Arrays.copyOf(niza,length());
        Arrays.sort(n);
        return new IntegerArray(n);
    }
    public IntegerArray concat(IntegerArray ia){
    	int l=length()+ia.length();
        int n[]=new int[l];
        for(int i=0;i<length();i++)
            n[i]=niza[i];
		int j=0;
        for(int i=length();i<l;i++,j++)
            n[i]=ia.getElementAt(j);
        return new IntegerArray(n);
    }
    public String toString(){
        if(niza==null) return "[]";
		String s="[";
        for(int i=0;i<length()-1;i++)
            s+=niza[i]+", ";
        s+=niza[length()-1]+"]";
        return s;
    }
}
public class IntegerArrayTester {
	
	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		String s = jin.nextLine();
		IntegerArray ia = null;
		switch (s) {
			case "testSimpleMethods" : 
				ia = new IntegerArray(generateRandomArray(jin.nextInt()));
				testSimpleMethods(ia);
				break;
			case "testConcatAndEquals" :
				ia = new IntegerArray(new int[]{5,2,3,7});
				IntegerArray same_as_ia1 = new IntegerArray(new int[]{5,2,3,7});
				IntegerArray same_as_ia2 = new IntegerArray(new int[]{5,2,3,7,8});
				IntegerArray same_as_ia3 = new IntegerArray(new int[]{5,2});
				IntegerArray same_as_ia4 = new IntegerArray(new int[]{3,7});
				IntegerArray same_as_ia5 = new IntegerArray(new int[]{2,3,5,7});
				IntegerArray same_as_ia6 = new IntegerArray(new int[]{7,5,3,2});
				IntegerArray same_as_ia7 = same_as_ia3.concat(same_as_ia4);
				IntegerArray same_as_ia8 = same_as_ia4.concat(same_as_ia3);
				if ( ! (ia.equals(ia))&&! (ia.equals(same_as_ia1)) && 
					 ! (same_as_ia1.equals(ia)) && 
					  (ia.equals(same_as_ia2))	&& 
					  (ia.equals(same_as_ia3))	&& 
					  (ia.equals(same_as_ia4))	&& 
					  (ia.equals(same_as_ia5))	&& 
					  (ia.equals(same_as_ia6))	&& 
					  (ia.equals(same_as_ia7))	&& 
					  (ia.equals(null))	&& 
					  (ia.equals(new int[]{5,2,3,7}))	&& 
					  (ia.equals(same_as_ia8))	)
					System.out.println("Your equals or concat method doesn't work properly.");
				else
					System.out.println("Your equals and concat method work great.");
				break;
			case "testReadingAndSorted" :
				String input_s = jin.nextLine()+"\n"+jin.nextLine();
				ia = ArrayReader.readIntegerArray(new ByteArrayInputStream(input_s.getBytes()));
				testSimpleMethods(ia);
				System.out.println(ia.getSorted());
				break;
			case "testImmutability" :
				int a[] = generateRandomArray(jin.nextInt());
				ia = new IntegerArray(a);
				testSimpleMethods(ia);
				testSimpleMethods(ia);
				IntegerArray sorted_ia = ia.getSorted();
				testSimpleMethods(ia);
				testSimpleMethods(sorted_ia);
				sorted_ia.getSorted();
				testSimpleMethods(sorted_ia);
				testSimpleMethods(ia);
				a[0] += 2;
				testSimpleMethods(ia);
				ia = ArrayReader.readIntegerArray(new ByteArrayInputStream(integerArraytoString(ia).getBytes()));
				testSimpleMethods(ia);
				break;
		}
		jin.close();
	}
	
	static void testSimpleMethods(IntegerArray ia) {
		System.out.print(integerArraytoString(ia));
		System.out.println(ia);
		System.out.println(ia.sum());
		System.out.printf("%.2f\n",ia.average());
	}
	
	
	static String integerArraytoString(IntegerArray ia) {
		StringBuilder sb = new StringBuilder();
		sb.append(ia.length()).append('\n');
		for ( int i = 0 ; i < ia.length() ; ++i ) 
			sb.append(ia.getElementAt(i)).append(' ');
		sb.append('\n');
		return sb.toString();
	}
	
	
	static int[] generateRandomArray(int k) {
		Random rnd = new Random(k);
		int n = rnd.nextInt(8)+2;
		int a[] = new int[n];
		for ( int i = 0 ; i < n ; ++i ) {
			a[i] = rnd.nextInt(20)-5;
		}
		return a;
	}

}