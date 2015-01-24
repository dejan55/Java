import java.util.*;
class EmptyStackException extends Exception{
    public EmptyStackException(){
    	super("EmptyStackException");
    }
}
class Node<T>{
	private Node<T> next;
    private T element;
    public Node(T element,Node<T> next){
    	this.element=element;
        this.next=next;
    }
    public void setNext(Node<T> next){
    	this.next=next;
    }
    public Node<T> getNext(){
    	return this.next;
    }
    public T getElement(){
    	return this.element;
    }


}
class Stack<T>{
	private Node<T> first;
    public Stack(){
    	first=null;
    }
    public void push(T element){
    	Node<T> tmp=first;
        first=new Node<T>(element,tmp);
    }
    public T peek() throws EmptyStackException{
    	if(isEmpty()) throw new EmptyStackException();
        return first.getElement();
    }
    public T pop() throws EmptyStackException{
        if(isEmpty()) throw new EmptyStackException();
        Node<T> tmp=first;
        first=first.getNext();
        return tmp.getElement();
    }
    public boolean isEmpty(){
    	return first==null;
    }
}
public class StackTest {

	
		
	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		int k = jin.nextInt();
		System.out.println("Test#"+k);
		if ( k == 0 ) {
			System.out.println("testing: Stack::push(T) , Stack::pop():T , T is Integer");
			int n = jin.nextInt();
			Stack<Integer> stack = new Stack<Integer>();
			System.out.println("Pushing elements:");
			for ( int i = 1 ; i <= n ; ++i ) {
				if ( i > 1 ) System.out.print(" ");System.out.print(i);
				stack.push(i);
			}
			System.out.println();
			System.out.println("Poping elements:");
			for ( int i = n ; i >= 1 ; --i ) {
				if ( i < n ) System.out.print(" ");
                try{System.out.print(stack.pop());}
                catch (Exception E){}
			}
			System.out.println();
		}
		if ( k == 1 ) {
			System.out.println("testing: Stack::push(T) , Stack::pop():T , T is String");
			
			int n = jin.nextInt();
			Stack<String> stack = new Stack<String>();
			System.out.println("Pushing elements:");
			for ( int i = 0 ; i < n ; ++i ) {
				if ( i > 0 ) System.out.print(" ");
				String next = jin.next();System.out.print(next);
				stack.push(next);
			}
			System.out.println();
			System.out.println("Poping elements:");
			for ( int i = 0 ; i < n ; ++i ) {
				if ( i > 0 ) System.out.print(" ");
                try{System.out.print(stack.pop());}
                catch (Exception E){}
			}
		}
		if ( k == 2 ) {
			System.out.println("testing: Stack::push(T) , Stack::pop():T , Stack::isEmpty():boolean, T is Double");
			
			Stack<Double> stack = new Stack<Double>();
			System.out.println("Pushing elements:");
			boolean flag = false;
			while ( jin.hasNextDouble() ) {
				double d = jin.nextDouble();
				stack.push(d);
				if ( flag ) System.out.print(" ");
				System.out.printf("%.2f",d);
				flag = true;
			}
			int i = 0;
			System.out.println();
			System.out.println("Poping elements:");
			while ( ! stack.isEmpty() ) {
				if ( i > 0 ) System.out.print(" ");++i;
                try{System.out.printf("%.2f",stack.pop());}
                catch (Exception e){}
			}
		}
		if ( k == 3 ) {
			System.out.println("testing: Stack::push(T) , Stack::pop():T , Stack::isEmpty():boolean , Stack::peek():T , T is Long");
			
			int n = jin.nextInt();
			Stack<Long> stack = new Stack<Long>();
			LinkedList<Long> control_stack = new LinkedList<Long>();
			boolean exact = true;
			for ( int i = 0 ; exact&&i < n ; ++i ) {
				if ( Math.random() < 0.5 ) {//add
					long to_add = (long)(Math.random()*456156168);
					stack.push(to_add);control_stack.addFirst(to_add);
				}
				else {
                    try{exact &= control_stack.isEmpty()==stack.isEmpty();
					if ( exact&&! stack.isEmpty() ) {
						if ( Math.random() > 0.7 ) exact &= control_stack.removeFirst().equals(stack.pop());
						else exact &= control_stack.peekFirst().equals(stack.peek());
					}
                       }
                    catch(Exception e){}
				}
			}
			System.out.println("Your stack outputs compared to the built in java stack were the same? "+exact);
		}
		if ( k == 4 ) {
			System.out.println("testing: Stack::pop():T , Stack::isEmpty():boolean , Stack::peek():T , T is Long");
			
			Stack<Date> test_stack = new Stack<Date>();
			
			System.out.println("Stack empty? "+test_stack.isEmpty());
			try {
			    test_stack.pop();
			    System.out.println("NO exeption was thrown when trying to pop from an empty stack!");
				
			} catch(Exception e) {
				System.out.print("Exeption thrown when trying to pop from an empty stack ");
				System.out.println(e.getClass().getSimpleName());
			}
			try {
			    test_stack.peek();
			    System.out.println("NO exeption was thrown when trying to peek in an empty stack!");
			} catch(Exception e) {
				System.out.print("Exeption thrown when trying to peek in an empty stack ");
				System.out.println(e.getClass().getSimpleName());
			}
		}
 	}

}
