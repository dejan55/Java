import java.util.Iterator;
public class Test{
public static void main(String[] args){
	Queue<String> q=new Queue<>(); // <>Vo inicalizacija Moze za ponovi verzii (java 7)
	Queue<Integer> m=new Queue<Integer>();
	//Iterator<Integer> b = m.iterator(); Ako go stavime tuka nema da vrati vrednosti
	q.enqueue("Ace");
	q.enqueue("Mare");
	q.enqueue("Letka");
	Iterator<String> a = q.iterator();
	if(a.hasNext()) System.out.println("True");
	while(a.hasNext()) System.out.println(a.next());
	System.out.println();
	System.out.println(q.dequeue());
	System.out.println(q.dequeue());
	System.out.println();
	q.enqueue("Ace"); 
	m.enqueue(1);
	m.enqueue(5);
	m.enqueue(11);
	Iterator<Integer> b = m.iterator();
	System.out.println();
	while(b.hasNext()) System.out.println(b.next());
	System.out.println();
	System.out.println(m.dequeue());
	System.out.println(m.dequeue());
}
}