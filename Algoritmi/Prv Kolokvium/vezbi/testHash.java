import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class testHash {
	public static void main (String[] args) throws IOException {
		Map<ChemicalElement,Integer> route=new HashMap<>(10);
		route.put(new ChemicalElement("F"),  new Integer(9));
        route.put(new ChemicalElement("Ne"), new Integer(10));
		System.out.println(route.get(new ChemicalElement("Ne")));
	}
}
class ChemicalElement implements Comparable<ChemicalElement> {

    // A ChemicalElement object represents a chemical element.
    
    // Each element contains the two characters of the chemical symbol.
    // The first character must be an uppercase letter. Where present,
    // the second character must be a lowercase letter. If absent, the
    // second character is a space.
    
    private char sym1, sym2; // The two letters of the chemical symbol.

    
    public ChemicalElement (String symbol) {
        if (symbol.length() >= 1)
            sym1 = Character.toUpperCase(symbol.charAt(0));
        else
            sym1 = ' '; // Should really fail.
        if (symbol.length() >= 2)
            sym2 = Character.toLowerCase(symbol.charAt(1));
        else
            sym2 = ' ';
    }
    
    
    public int hashCode () {
        return sym1 - 'A';
    }


    public int stepCode () {
    	return (sym2 == ' ') ? 1 : sym2 - 'a' + 2;
    }

    
    public String toString () {
        return "" + sym1 + sym2;
    }

    
    public int compareTo (ChemicalElement that) {
        return (this.sym1 < that.sym1) ? -1 :
               (this.sym1 > that.sym1) ? 1  :
               (this.sym2 < that.sym2) ? -1 :
               (this.sym2 > that.sym2) ? 1  :
               0;
    }

	@Override
	public boolean equals (Object that) {
		ChemicalElement other = (ChemicalElement)that;
    	if (other == null || ! (other instanceof ChemicalElement))
    	    return false;
    	else{
    		return this.compareTo(other) == 0;
    	}
    }
}


	
