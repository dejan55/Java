package naprednoL8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
public class MacStringTest {
    
public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		MacString mc = new MacString(jin.next());
		int k = jin.nextInt();
		if ( k == 0 ) {//test eve but changeAplhabet
			while ( true ) {
				String cmd = jin.next();
				if ( cmd.equals("stop") ) break;
				System.out.print(cmd+" ");
				if ( cmd.equals("isPalindrome") ) {
					System.out.println();
					System.out.println(mc.isPalindrome());
					System.out.println(mc);
				}
				if ( cmd.equals("isRecuring") ) {
					String base = jin.next();
					System.out.println(base);
					System.out.println(mc.isRecuring(new MacString(base)));
					System.out.println(mc);
				}
				if ( cmd.equals("insert") ) {
					char c = jin.next().charAt(0);
					System.out.println(c);
					mc.insert(c);
				}
				if ( cmd.equals("append") ) {
					char c = jin.next().charAt(0);
					System.out.println(c);
					mc.append(c);
				}
				if ( cmd.equals("format") ) {
					int n = jin.nextInt();
					boolean asc = jin.nextBoolean();
					System.out.println(n+" "+asc);
					System.out.println(mc.format(n,asc));
					System.out.println(mc);
				}
				if ( cmd.equals("print") ) {
					System.out.println();
					System.out.println(mc);
				}
			}	
		}
		if ( k == 1 ) {//test changeAlphabet
			int num_rules = jin.nextInt();
			TreeMap<Character,Character> rules = new TreeMap<Character,Character>();
			for ( int i = 0 ; i < num_rules ; ++i ) {
				String rule = jin.next();
				rules.put(rule.charAt(0), rule.charAt(2));
			}
			System.out.println(mc.changeAplhabet(rules));
			System.out.println(mc);
		}
	}
}

class MacString{
	protected List<Character> lista;
	
	public MacString(){
		lista = new ArrayList<Character>();
	}
	
	public MacString(String s){
		lista = new ArrayList<Character>();
		for (int i = 0; i < s.length(); i++) {
			lista.add(s.charAt(i));
		}
	}
	
	public void insert(Character c){
		lista.add(0, c);
	}
	
	public void append(Character c){
		lista.add(c);
	}
	
	public int length(){
		return lista.size();
	}
	
	public boolean isPalindrome(){
		boolean pal = true;
		for (int i = 0; i < lista.size() / 2 + 1; i++) {
			if(!lista.get(i).equals(lista.get(lista.size()-i-1))){
				pal = false;
			}
		}
		return pal;
	}
	
	public boolean isRecuring(MacString base){
		int br = 0;
		String s = new String(toString());
		String p = new String(base.toString());
		for (int i = 0; i < s.length(); i++) {
			if (p.length()+i <= s.length()) {
				if (p.equals(s.substring(i, p.length()+i))) {
					br++;
					i = p.length()+i-1;
				}
			}
		}
		if(br >= 2){
			return true;
		}
		else return false;
		/*for (int i = 0; i < lista.size(); i++) {    //i ova raboti isto kako gornoto 8/9 vadi
			int k = i;
			for (int j = 0; j < base.length(); j++) {
				if(base.lista.get(j).equals(lista.get(k))){
					if(j==base.length()-1){
						br++;
						i=k;
					}
					k++;
				}
				else{
					break;
				}
				if(k==lista.size()){
					break;
				}
			}
		}
		if(br>=2){
			return true;
		}
		else{
			return false;
		}*/
	}
	
	public MacString changeAplhabet(Map<Character,Character> new_aplhabet){
		MacString nov = new MacString();
		for (Character character : lista) {
			if(new_aplhabet.containsKey(character)){
				nov.append(new_aplhabet.get(character));
			}
			else{
				nov.append(character);
			}
		}
		return nov;
	}
	
	public MacString format(int length, boolean right_align){
		MacString nov = new MacString(toString());
		if(nov.length() < length){
			if(right_align){
				while (nov.length() != length) {
					nov.insert(' ');
				}
			}
			else{
				while(nov.length() != length){
					nov.append(' ');
				}
			}
		}
		return nov;
	}
	
	@Override
	public String toString() {
		String s = new String();
		for (Character character : lista) {
			s+=character;
		}
		return s;
	}
}