import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class SpecialSLLJoin<Item extends Comparable<Item>> implements Iterable<Item> {
	class Node{
			Item value;
			Node next;
		} 
		private int size;
		private Node first,last;
		
		public SpecialSLLJoin(){
			this.size=0;
		}
		public void insertLast(Item el){
				Node oldlast=last;
				last=new Node();
				last.value=el;
				last.next=null;
				if(isEmpty()) first=last;
				else oldlast.next=last;
				//size++;
		}
		public boolean isEmpty(){
			return first==null;
		}
		public Node getLast(){
			//Item item=first.value;
			//first=first.next;
			if(isEmpty())last=null;
			return first;
		}
		public SpecialSLLJoin specialJoin(SpecialSLLJoin o1,SpecialSLLJoin o2){
			SpecialSLLJoin rezult=new SpecialSLLJoin();
			Node jazol1=o1.getLast(),jazol2=o2.getLast();
			int br1,br2;
			br1=2;
			br2=0;
			while(jazol1!=null&&jazol2!=null){
				if(br1==2){
					rezult.insertLast(jazol1.value);
					jazol1=jazol1.next;
					br2++;
					if(br2==2) {br1=3; br2=0;}
				}
				else{
					rezult.insertLast(jazol2.value);
					jazol2=jazol2.next;
					br2++;
					if(br2==2) {br1=2; br2=0; }
				}
			}
			if(jazol1!=null)
				while(jazol1!=null){
					rezult.insertLast(jazol1.value);
					jazol1=jazol1.next;
				}
			if(jazol2!=null)
				while(jazol2!=null){
					rezult.insertLast(jazol2.value);
					jazol2=jazol2.next;
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
	
	
	
	
	public static void main(String[] args) throws IOException{
		SpecialSLLJoin lista1=new SpecialSLLJoin();
		SpecialSLLJoin lista2=new SpecialSLLJoin();
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
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
		SpecialSLLJoin spoeni;
		spoeni =lista1.specialJoin(lista1,lista2);
		Iterator<Integer> it = spoeni.iterator();
		while (it.hasNext()) {
			System.out.print(it.next());
            if(it.hasNext())
                System.out.print(" ");
		}
        System.out.println(); 
		
	}
}
