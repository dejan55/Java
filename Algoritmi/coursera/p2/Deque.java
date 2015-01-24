import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
   private Node first,last;
   private int count;
   private class Node{
	Item item;
	Node next;
	Node prev;
   }
   public Deque()  { this.count=0; }        // construct an empty deque
   public boolean isEmpty()  {   			// is the deque empty?
	return first==null;
	}
   public int size()  {                      // return the number of items on the deque
	return count;
	}
   public void addFirst(Item item)  {   // insert the item at the front
	if(item==null) throw new NullPointerException();
	Node new1=first;
	first=new Node();
	first.item=item;
	first.next=new1;
	first.prev=null;
	if(last==null) last=first;
	else new1.prev=first;
	count++;
	}
   public void addLast(Item item) {          // insert the item at the end
	if(item==null) throw new NullPointerException();
	Node new1=last;
	last=new Node();
	last.item=item;
	last.next=null;
	last.prev=new1;
	if(isEmpty()) first=last;
	else new1.next=last;  
	count++;
	}
   public Item removeFirst()  {              // delete and return the item at the front
	if(count<=0) throw new java.util.NoSuchElementException();
	Item item=first.item;
	first=first.next;
	count--;
	if(isEmpty()) last=null;
	return item;
	}
   public Item removeLast()  {               // delete and return the item at the end
	if(isEmpty()) throw new java.util.NoSuchElementException();
	Item item=last.item;
	if(last.prev!=null){
	last=last.prev;
	last.next=null;
	 }
	else last=null;
	if(last==null) first=null;
	count--;
	return item;
	}
   public Iterator<Item> iterator()  { return new ListIterator(); }      // return an iterator over items in order from front to end
   private class ListIterator	implements Iterator<Item>{
	private Node current=first;
	public boolean hasNext(){ return current!=null;}
	public void remove(){ throw new UnsupportedOperationException();}
	public Item next(){
		if(!hasNext()) throw new java.util.NoSuchElementException();
		Item item=current.item;
		current=current.next;
		return item;
	}
   }
   public static void main(String[] args) {
		Deque<String> q=new Deque<String>();
		q.addFirst("Ace");
		q.addFirst("Mare");
		q.addFirst("Letka");
		q.addFirst("Me");
		StdOut.println(q.removeLast());
		StdOut.println(q.removeLast());
		StdOut.println(q.removeLast());
		StdOut.println(q.removeLast());
		
		//q.removeLast(); 
		//q.removeFirst();
		Iterator<String> a = q.iterator();
		while(a.hasNext()) StdOut.println(a.next());
		StdOut.println(); 
   }
}