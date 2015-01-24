import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class SLLJoinLists<Item extends Comparable<Item>> implements Iterable<Item> {
		class Node{
			Item value;
			Node next;
		} 
		private int size;
		private Node first;
		
		public SLLJoinLists(){
			this.size=0;
		}
		public void insertLast(Item el){
				Node new1=first;
				first=new Node();
				first.value=el;
				first.next=new1;
				size++;
		}
		public Node getLast(){
			return first;
		}
		public Node getFirst(){
			if(first==null) return first;
			Node tmp=first;
			while(tmp.next!=null)
			tmp=tmp.next;
			return tmp;
		}
		public SLLJoinLists joinLists(SLLJoinLists o2){
			SLLJoinLists rezult=new SLLJoinLists();
			Node jazol1=this.getLast(),jazol2=o2.getLast();
			Item i1,i2;
			i1=jazol2.value;
			i2=jazol1.value;
			while(jazol1!=null&&jazol2!=null){
				if(jazol1.value.compareTo(jazol2.value)>0){
					if(i1.compareTo(jazol1.value)==0){
					while(i1.compareTo(jazol1.value)==0) {jazol1=jazol1.next; if(jazol1==null) break;}
					continue; }
					i1=jazol1.value;
					rezult.insertLast(jazol1.value);
					jazol1=jazol1.next;
				}
				else if(jazol1.value.compareTo(jazol2.value)==0){
					Item c=jazol1.value;
					rezult.insertLast(jazol1.value);
					jazol2=jazol2.next;
					if(jazol2!=null)
					while(c.compareTo(jazol2.value)==0){
					jazol2=jazol2.next;
					if(jazol2==null) break; }
					jazol1=jazol1.next;
					if(jazol1!=null)
					while(c.compareTo(jazol1.value)==0){
					jazol1=jazol1.next;
					if(jazol1==null) break; }
					
				}	
				else{
					if(i2.compareTo(jazol2.value)==0){
					while(i2.compareTo(jazol2.value)==0) {jazol2=jazol2.next; if(jazol2==null) break;}
					continue; }
					i2=jazol2.value;
					rezult.insertLast(jazol2.value);
					jazol2=jazol2.next;
				}
			} 
			if(jazol1!=null)
				while(jazol1!=null){
					Item c=jazol1.value;
					rezult.insertLast(jazol1.value);
					jazol1=jazol1.next;
					if(jazol1!=null)
					while(c.compareTo(jazol1.value)==0){
					jazol1=jazol1.next;
					if(jazol1==null) break; }
				}
			if(jazol2!=null)
				while(jazol2!=null){
					Item c=jazol2.value;
					rezult.insertLast(jazol2.value);
					jazol2=jazol2.next;
					if(jazol2!=null)
					while(c.compareTo(jazol2.value)==0){
					jazol2=jazol2.next;
					if(jazol2==null) break; }
				}
			return rezult;
		}
		public Iterator<Item> iterator()  { return new ListIterator(); }      
		private class ListIterator	implements Iterator<Item>{
		private Node current=first;
		public boolean hasNext(){ return current!=null;}
		public void remove(){ throw new UnsupportedOperationException();}
		public Item next(){
		if(!hasNext()) throw new java.util.NoSuchElementException();
		Item item=current.value;
		current=current.next;
		return item;
	}
    } 
	
	
	
	public static void main(String[] args) throws IOException {
		SLLJoinLists<Integer> lista1=new SLLJoinLists<Integer>();
		SLLJoinLists<Integer> lista2=new SLLJoinLists<Integer>();
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
		s = stdin.readLine();
		String[] pomniza = s.split(" ");
		for (int i = 0; i < N; i++) {
			lista1.insertLast(Integer.parseInt(pomniza[i]));
		}

		s = stdin.readLine();
		N = Integer.parseInt(s);
		s = stdin.readLine();
		pomniza = s.split(" ");
		for (int i = 0; i < N; i++) {
			lista2.insertLast(Integer.parseInt(pomniza[i]));
		}
		SLLJoinLists spoeni;
		spoeni = lista1.joinLists(lista2);
		Iterator<Integer> it = spoeni.iterator();
		while (it.hasNext()) {
			System.out.print(it.next());
            if(it.hasNext())
                System.out.print(" ");
		}
        System.out.println(); 
	}
}
