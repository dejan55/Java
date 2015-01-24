import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
   private int count;
   private Item[] a;
   private void resize(int cap){
	Item[] copycat=(Item[]) new Object[cap];
	for(int i=0;i<count;i++)
		copycat[i]=a[i];
	a=copycat;
   }
   public RandomizedQueue()  {               // construct an empty randomized queue
		this.count=0;
		a=(Item[]) new Object[1];
	}
   public boolean isEmpty(){                 // is the queue empty?
		return count==0;
	}
   public int size() {                       // return the number of items on the queue
		return count;
	}
   public void enqueue(Item item){           // add the item
		if(item==null) throw new NullPointerException();
		if(count==a.length) resize(2*a.length);
		a[count++]=item;
	}
   public Item dequeue()  {                  // delete and return a random item
		if(count<=0) throw new java.util.NoSuchElementException();
		if(count>0&& count==a.length/4) resize(a.length/2);
		int r=StdRandom.uniform(count);
		Item new1=a[r];
		a[r]=a[--count];
		a[count]=null;
		return new1;
	}
   public Item sample() {                    // return (but do not delete) a random item
		if(count<=0) throw new java.util.NoSuchElementException();
		int r=StdRandom.uniform(count);
		return a[r];
	}
   public Iterator<Item> iterator(){ return new ReverseArrayIterator(); } // return an independent iterator over items in random order
   private class ReverseArrayIterator implements Iterator<Item>{
		private int N=count;
		private Item[] b=a;
		//public ReverseArrayIterator(){ b=(Item[]) new Object[count]; b=a; }
		public boolean hasNext() {return N>0; }
		public void remove() { throw new UnsupportedOperationException();}
		public Item next() {
		if(!hasNext()) throw new java.util.NoSuchElementException();
		int r=StdRandom.uniform(N);
		Item next1=b[r];
		b[r]=b[--N];
		b[N]=null;
		return next1; 
		}
   }
   public static void main(String[] args){   // unit testing
		RandomizedQueue<String> q=new RandomizedQueue<String>();
		q.enqueue("Ace");
		q.enqueue("Mare");
		q.enqueue("Letka");
		q.enqueue("Me");
		//StdOut.println(q.sample());
		//StdOut.println(q.sample());
		//StdOut.println(q.dequeue());
		StdOut.println();
		Iterator<String> a = q.iterator();
		Iterator<String> b = q.iterator();
		while(a.hasNext()) StdOut.println(a.next());
		StdOut.println("B:");
		while(b.hasNext()) StdOut.println(b.next());
   }
}