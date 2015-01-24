import java.util.Iterator;
public class Subset {
   public static void main(String[] args){
	int k = Integer.parseInt(args[0]);
	RandomizedQueue<String> q=new RandomizedQueue<String>();
	while (!StdIn.isEmpty()){
		String st=StdIn.readString();
		q.enqueue(st);
	}
	Iterator<String> a = q.iterator();
	for(int i=0;i<k;i++)
		StdOut.println(a.next());
   }
}