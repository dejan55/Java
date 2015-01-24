public class CBHTTest {
    
    public static void main (String[] args) {
        CBHT<String,String> table1 = new CBHT<>(2);
        table1.insert("new ChemicalElement()",  "new Integer(9)");
         table1.insert("new ChemicalElement()",  "new Integer(9)");
          table1.insert("new ChemicalElement()",  "new Integer(9)");
           table1.insert("new ChemicalElement()",  "new Integer(9)");
            table1.insert("new ChemicalElement()",  "new Integer(9)");
             table1.insert("new ChemicalElement()",  "new Integer(9)");
        

        System.out.println ("Tabelata od slajd 5");
        System.out.println(table1);

    }
}        