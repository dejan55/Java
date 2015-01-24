import java.util.Scanner;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;

public class F1Test {

	public static void main(String[] args) {
		F1Race f1Race = new F1Race();
		f1Race.readResults(System.in);
		f1Race.printSorted(System.out);

	}

}

class F1Race {
    private String[] ime;
    private int[] mm,ss,nnn;
    private int brS;
    public F1Race(){
    	ime=new String[100];
        mm=new int[100];
        ss=new int[100];
        nnn=new int[100];
        brS=0;
    }
    void readResults(InputStream inputStream){
    	Scanner in=new Scanner(inputStream);
        while(in.hasNextLine()){
        	ime[brS]=in.nextLine();
            /*mm[brS]=in.nextInt();
            ss[brS]=in.nextInt();
            nnn[brS]=in.nextInt(); */
            brS++;
        }
    }
    void printSorted(OutputStream outputStream){
    	PrintWriter out=new PrintWriter(outputStream);
        String s="";
        for(int i=0;i<brS;i++){
        	s+=String.format("%d. %s",i,ime[i]);
            s+='\n';
        }
        //\t%d:%d%d",i,ime[i],mm[i],ss[i],nnn[i]
        out.println(s);
    }
    
}