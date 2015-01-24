import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Array<E> {
	private E niza[];
	private int size;
	public Array(int a){
		niza=(E[]) new Object[a];
		size=a;
	}
	public void set(int pos,E o){
		if(pos<0||pos>size) throw new IndexOutOfBoundsException();
		niza[pos]=o;
	}
	public E get(int pos){
		if(pos<0||pos>size) throw new IndexOutOfBoundsException();
		return niza[pos];
	}	
	public int length(){
		return size;
	}
	public static int brojDoProsek(Array e){
		double avg=0;
		int j=0;
		for(int i=0;i<e.length();i++,j++)
		avg+=(int)e.get(i);
		avg=avg/j;
		double razlika=Math.abs(avg-(int)e.get(0));
		double min=razlika;
		j=0;
		for(int i=1;i<e.length();i++){
			double ab=Math.abs(avg-(int)e.get(i));
			if(min>=ab){
				if(razlika-ab==0) {if((int)e.get(i)<(int)e.get(j)) j=i;}
				else{
				min=ab;
				j=i;
				if(min==0) break;
				razlika=ab;
				}
			}
		}
		return (int)e.get(j);
	}
	public String toString(){
		String m="";
		for(int i=0;i<size;i++)
		m+=niza[i]+" ";
		return m;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in)); 
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
        Array<Integer> data=new Array<Integer>(N);
		for(int i=0;i<N;i++){
			s = stdin.readLine();
			int n=Integer.parseInt(s);
			data.set(i,n);
		}
        //System.out.println(data);
		System.out.println(brojDoProsek(data));		
	}
	
	

}
