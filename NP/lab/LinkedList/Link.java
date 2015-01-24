public class Link {

 

    // Set to public so getters & setters aren't needed



    public String bookName;

    public int millionsSold;

     

    // Reference to next link made in the LinkList

    // Holds the reference to the Link that was created before it

    // Set to null until it is connected to other links

     

    public Link next;

     

    public Link(String bookName, int millionsSold){

         

        this.bookName = bookName;

        this.millionsSold = millionsSold;

         

    }

     

    public void display(){

         

        System.out.println(bookName + ": " + millionsSold + ",000,000 Sold");

         

    }

     

    public String toString(){

         

        return bookName;

         

    }

     

    public static void main(String[] args) {

         

        LinkList theLinkedList = new LinkList();

         

        // Insert Link and add a reference to the book Link added just prior
38
        // to the field next
39
         
40
        theLinkedList.insertFirstLink("Don Quixote", 500);
41
        theLinkedList.insertFirstLink("A Tale of Two Cities", 200);
42
        theLinkedList.insertFirstLink("The Lord of the Rings", 150);
43
        theLinkedList.insertFirstLink("Harry Potter and the Sorcerer's Stone", 107);
44
         
45
        theLinkedList.display();
46
         
47
        System.out.println("Value of first in LinkedList " + theLinkedList.firstLink + "\n");
48
         
49
        // Removes the last Link entered
50
         
51
        theLinkedList.removeFirst();
52
         
53
        theLinkedList.display();
54
         
55
        System.out.println(theLinkedList.find("The Lord of the Rings").bookName + " Was Found");
56
         
57
        theLinkedList.removeLink("A Tale of Two Cities");
58
         
59
        System.out.println("\nA Tale of Two Cities Removed\n");
60
         
61
        theLinkedList.display();
62
         
63
    }
64
     
65
}
