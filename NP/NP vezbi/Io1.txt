import java.io.*;
import java.util.Scanner;
public class Io1{
	public static void main(String[] args){
	PrintWriter out=null;
	try{
		out=new PrintWriter(new FileOutputStream("ace.txt"));
		out.println("1 2 3 4");
		out.println("This is Spartaaa");
		out.println("This is 3");
		out.println("This is Changed");
		out.println("This is Freedom");
	}
	catch(Exception e){
	
	}
	out.close();
	
	Scanner in=null;
	try{
	in=new Scanner(new FileInputStream("ace.txt"));
	while(in.hasNextLine()){
		System.out.println(in.nextLine());
	} 
	}
	catch(Exception e){
	}
	BufferedReader buff=null;
	try{
		buff=new BufferedReader(new FileReader("C:\\Users\\Home\\Desktop\\NP vezbi\\txt/mare.txt"));
		String s=buff.readLine();
		String si[]=s.split(" ");
		int sum=0;
		for(int i=0;i<si.length;i++)
			sum+=Integer.parseInt(si[i]);
		System.out.println(sum);
		buff.skip(8);
		System.out.println((char)buff.read());
		buff.readLine();
		while(s!=null){
			System.out.println(s);
			s=buff.readLine();
		}
	}
	catch(Exception e){
		System.out.println("Mare not found :(");
	}
	}
}