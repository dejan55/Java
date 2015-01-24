import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DLLNode<E>{
	protected E element;
	protected DLLNode<E> succ, pred;
	
	public DLLNode(E elem, DLLNode<E> p, DLLNode<E> s){
		element = elem;
		succ = s;
		pred = p;
	}
}

class DLL<E extends Comparable<E>>{
	protected DLLNode<E> first, last;
	
	public DLL(){
		first = null;
		last = null;
	}
    public boolean isEmpty(){
    	return first==null;
    }
	
	public void insertFirst(E nov){
		DLLNode<E> ins = new DLLNode(nov, null, first);
		if(first == null) {
			last = ins;
		}
		else
			first.pred = ins;
			first = ins;
	}
	
	public void insertLast(E o){
		if (first == null)
			insertFirst(o);
		else {
			DLLNode<E> ins = new DLLNode<E>(o, last, null);
			last.succ= ins;
			last = ins;
			}
		}
	
}

public class DivideOddEven {
	
    public static void print(DLL<Integer> lista){
    	DLL<Integer> tmp = new DLL();
        tmp=lista;
        while(tmp.first!=null){
            if(tmp.first.succ==null) { System.out.print(tmp.first.element); break;}
            else System.out.print(tmp.first.element+" ");
            tmp.first=tmp.first.succ;
  
        }
        
        //System.out.print(tmp.first.element);
    
    }
    public static DLL<Integer>[] divide(DLL<Integer> lista){
    	DLL<Integer> tmp = new DLL();
		tmp=lista;
        DLL<Integer>[] listaTmp=new DLL[2];
        listaTmp[0]=new DLL<>();
        listaTmp[1]=new DLL<>();
        while(tmp.first!=null){
        	if(tmp.first.element%2==0)
                listaTmp[0].insertLast(tmp.first.element);
            else listaTmp[1].insertLast(tmp.first.element);
            tmp.first=tmp.first.succ;
        }
        return listaTmp;
    }
	public static void main(String[] args) throws IOException {
		DLL<Integer> lista = new DLL();
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
		s = stdin.readLine();
		String[] pomniza = s.split(" ");
		for (int i = 0; i < N; i++) {
			lista.insertLast(Integer.parseInt(pomniza[i]));
		}
        DLL<Integer>[] list=divide(lista);
        if(!list[1].isEmpty()){
        print(list[1]);
        System.out.println();   
        }
        print(list[0]); 
}
}