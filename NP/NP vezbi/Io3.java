import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
public class Io3{
	public static void main(String[] args){
		try{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out=new PrintWriter(System.out);
		System.out.println("Enter text here");
		String s=in.readLine();
		out.println("this is what u wrote");
		out.println(s);
		in.close();
		out.close();
		}
		catch(Exception e){ }
	}
}