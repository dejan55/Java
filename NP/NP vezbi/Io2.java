import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.EOFException;
class Some implements java.io.Serializable{
	private int one;
	private transient int Me; 	// Transient keyword doesn't safe in writeObject
	private String aga,aha;
	public Some(int one,int me,String aga,String aha){
		this.one=one;
		this.Me=me;
		this.aga=aga;
		this.aha=aha;
	}
	public String toString(){
		return String.format("%d %d %s %s",one,Me,aga,aha);
	}
}

public class Io2{
	public static void main(String[] args){
		Some so=new Some(20,200,"Ace","Gjorg");
		ObjectOutputStream outO=null;
		try{
			outO=new ObjectOutputStream(new FileOutputStream("txt/Ace.txt"));
			outO.writeInt(10);
			outO.writeInt(100);
			outO.writeInt(1000);
			outO.writeObject(so);
			outO.writeUTF("Ace is the best");
			outO.writeUTF("Every1 knows that");
			outO.writeUTF("And it just writes...");
			outO.writeUTF("And it just writes...");
			outO.writeUTF("And it just writes...");
			outO.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		catch(IOException e){
			System.out.println("IOException");
		}
		ObjectInputStream inI=null;
		try{
			inI=new ObjectInputStream(new FileInputStream("txt/Ace.txt"));
			
			System.out.print(inI.readInt()+" ");
			System.out.print(inI.readInt()+" ");
			System.out.print(inI.readInt()+" "); 
			//inI.readInt();
			System.out.println();
			System.out.println("And now the class");
			System.out.println(inI.readObject());
			try{
			while(true){
				System.out.println(inI.readUTF());
			}
			}
			catch(EOFException e){ }
			/*System.out.println(inI.readUTF());
			System.out.println(inI.readUTF()); */
			inI.close();
		}
		catch(EOFException e){
			System.out.println("End Of File Reached");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		catch(IOException e){
			System.out.println("IOException");
		}
		catch(Exception e){
			
		}
	}
}