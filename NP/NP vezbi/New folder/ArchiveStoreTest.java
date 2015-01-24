import java.util.Date;
import java.util.Scanner;

public class ArchiveStoreTest {
	public static void main(String[] args) {
		ArchiveStore store = new ArchiveStore();
        Date date = new Date(113, 10, 7);
		Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
		int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
		int i;
		for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
			long days = scanner.nextLong();
			Date dateToOpen = new Date(date.getTime() + (days * 24 * 60
					* 60 * 1000));
			LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
			store.archiveItem(lockedArchive, date);
		}
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
		for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
			int maxOpen = scanner.nextInt();
			SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
		}
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
			int open = scanner.nextInt();
            try {
            	store.openItem(open, date);
            } catch(NonExistingItemException e) {
            	System.out.println(e.getMessage());
            }
        }
		System.out.println(store.getLog());
	}
}
abstract class Archive{
	private int id;
	private Date dateArchived;
	private boolean da;
	public Archive(int id,Date date){
		this.id=id;
		this.dateArchived=date;
	}
	public Archive(int id){
		this.id=id;
	}
	public int getID(){
		return id;
	}
	public void setDate(Date d){
		this.dateArchived=d;
	}
	protected void setDa(boolean da){
		this.da=da;
	}
	protected boolean getDa(){
		return da;
	}
}
class LockedArchive extends Archive{
	public Date dateToOpen;
	public LockedArchive(int id,Date open){
		super(id);
		super.setDa(true);
		this.dateToOpen=open;
	}
}
class SpecialArchive  extends Archive{
	public int maxOpen;
	public SpecialArchive(int id, int maxOpen){
		super(id);
		super.setDa(false);
		this.maxOpen=maxOpen;
	}
}
class ArchiveStore{
    private Archive[] ar;
	private int br;
	private String log;
	private int[] list,opened;
	public ArchiveStore(){
		ar=new Archive[100];
		list=new int[100];
		opened=new int[100];
		br=0;
		log="";
	}
	public void archiveItem(Archive item, Date date){
		list[br++]=item.getID();
		ar[list[br-1]]=item;
		ar[list[br-1]].setDate(date);
		log+="Item "+list[br-1]+" archived at "+date;
		log+="\n";
	}
	public void openItem(int id, Date date) throws NonExistingItemException{
		for(int i=0;i<br;i++)
			if(id==ar[list[i]].getID()) { 
			if(ar[list[i]].getDa()){
			LockedArchive my=(LockedArchive)ar[list[i]];
			if(my.dateToOpen.before(date)){
			log+="Item "+list[i]+" opened at "+date;
			log+="\n";}
			else{
			log+="Item "+list[i]+" cannot be opened before "+my.dateToOpen;
			log+="\n";
			}
			}
			else{
			SpecialArchive my=(SpecialArchive)ar[list[i]];
			if(my.maxOpen>opened[i]){
			opened[i]++;
			log+="Item "+list[i]+" opened at "+date;
			log+="\n";
			}
			else{
			log+="Item "+list[i]+" cannot be opened more than "+my.maxOpen+" times";
			log+="\n";
			}
			}
			return;}
		throw new NonExistingItemException("Item with id "+id+" doesn't exist"); 
	}
	public String getLog(){
		return log; 
	}

}
class NonExistingItemException extends Exception{
	public NonExistingItemException(){
		super("NonExistingItemException");
	}
	public NonExistingItemException(String s){
		super(s);
	}
}


