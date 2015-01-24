import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RoutingHashJava {
	public static void main (String[] args) throws IOException {
		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		in.nextLine();
		Map<String,String> route=new HashMap<>(N);
		for(int i=0;i<N;i++){
			String pom1=in.nextLine();
			String pom2=in.nextLine();
			route.put(pom1,pom2);
		}
		int m=in.nextInt();
		in.nextLine();
		System.out.println(route);
		for (int i=0; i<m; i++) {
			String pom1=in.nextLine();
			String pom2=in.nextLine();
			String comp=route.get(pom1);
			boolean cT=false;
			if(comp!=null){
				String tmp[]=comp.split("\\.");
				String tmp2[]=pom2.split("\\.");
				for (int j=0;j<3 ;j++ ) {
					if(tmp[j].equals(tmp2[j])) cT=true;
					else {cT=false;break;}
				}
			}
			if(cT) System.out.println("postoi");
			else System.out.println("ne postoi");
		}
		
	}
}

	
