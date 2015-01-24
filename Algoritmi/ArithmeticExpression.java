import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArithmeticExpression {
    
    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
        char opr[]=new char[r];
        int br,br2;
        int num[]=new int[r];
        br=br2=0;
       	String s=new String("()+-");
        char ci[]=s.toCharArray();
        for(int i=l;i<=r;i++){
        	if(c[i]==ci[0]) ;
            else if(c[i]==ci[2]) opr[br2++]=c[i]; 
            else if(c[i]==ci[3]) opr[br2++]=c[i];
            else if(c[i]==ci[1]){
                if(br2==0) break;
				if(opr[br2-1]==ci[2]){
                	num[br-2]=num[br-2]+num[br-1];
                    br--;
                    br2--;
                }
                else {
                	num[br-2]=num[br-2]-num[br-1];
                    br--;
                    br2--;
                
                }
            }
            else num[br++]=c[i]-48;
        
        
        }
       return num[0]; 
    }
    
    public static void main(String[] args) throws Exception {
        int i,j,k;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String expression = br.readLine();
        char exp[] = expression.toCharArray();
        
        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);
        
        br.close();
        
    }
    
}
