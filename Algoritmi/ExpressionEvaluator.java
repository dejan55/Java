import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

	public static int evaluateExpression(String expression){
		String[] c=expression.split("\\+");
        String[] a;
        int sum=0;
        for(int i=0;i<c.length;i++){
            a=c[i].split("\\*");
            int pro=Integer.parseInt(a[0]);
         	for(int j=1;j<a.length;j++)
                pro*=Integer.parseInt(a[j]);
            sum+=pro;
        }
        
		return sum;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		System.out.println(evaluateExpression(input.readLine()));
    }

}