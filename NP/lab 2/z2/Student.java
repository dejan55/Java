import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONValue;
abstract class Contact{
	private String date;
	private boolean check;
	public Contact(String date){
		this.date=date;
	}
	public boolean isNewerThan(Contact c){
		String p1[]=this.date.split("-");
		String p2[]=c.date.split("-");
		if(Integer.parseInt(p1[0])<Integer.parseInt(p2[0])) return false;
		else if(Integer.parseInt(p1[0])==Integer.parseInt(p2[0])){
		if(Integer.parseInt(p1[1])<Integer.parseInt(p2[1])) return false;
		else if(Integer.parseInt(p1[1])==Integer.parseInt(p2[1]))
		if(Integer.parseInt(p1[2])<Integer.parseInt(p2[2])) return false;}
		return true;
	}
	public void setCheck(boolean a){
		this.check=a;
	}
	public boolean getCheck(){
		return check;
	}
	public String getPhone(){
		return "";
	}
	public String getEmail(){
		return "";
	}
	public abstract String getType();
}
class EmailContact extends Contact{
	private String email;
	public EmailContact(String date, String email){
		super(date);
		super.setCheck(false);
		this.email=email;
	}
	@Override
	public String getEmail(){
		return email;
	}
	public String getType(){
		return String.format("%s","Email");
	}
}
class PhoneContact extends Contact{
	private String phone;
	enum Operator { VIP, ONE, TMOBILE }
	public PhoneContact(String date, String phone){
		super(date);
		super.setCheck(true);
		this.phone=phone;
	}
	@Override
	public String getPhone(){
		return phone;
	}
	public Operator getOperator(){
		String s=""+phone.charAt(2);
		int check=Integer.parseInt(s);
		if(check<=3) return Operator.TMOBILE;
		if(check==5||check==6) return Operator.ONE;
		return Operator.VIP;
	} 
	public String getType(){
		return String.format("%s","Phone");
	}
}
public class Student{
	private String firstName,lastName,city;
	private int age,br,brE,brP;
	private long index;
	private Contact[] c;
	public Student(String firstName, String lastName, String city, int age, long index){
		this.firstName=firstName;
		this.lastName=lastName;
		this.city=city;
		this.age=age;
		this.index=index;
		c=new Contact[10];
		br=brE=brP=0;
	}
	public void addEmailContact(String date, String email){
		c[br++]=new EmailContact(date,email);
		brE++;
	}
	public void addPhoneContact(String date, String phone){
		c[br++]=new PhoneContact(date,phone);
		brP++;
	}
	public Contact[] getEmailContacts(){
		Contact[] a=new Contact[brE];
		int br1=0;
		for(int i=0;i<br;i++)
			if(!c[i].getCheck())
			a[br1++]=c[i];
		return a;
	}
	public Contact[] getPhoneContacts(){
		Contact[] a=new Contact[brP];
		int br1=0;
		for(int i=0;i<br;i++)
			if(c[i].getCheck())
			a[br1++]=c[i];
		return a;
	}
	public String getCity(){
		return city;
	}
	public String getFullName(){
		return firstName;
	}
	public long getIndex(){
		return index;
	}
	public Contact getLatestContact(){
		return c[br-1];
	}
	public int getNumb(){
		return br;
	}
	/*public String toString(){
		Map obj=new LinkedHashMap();
		obj.put("ime",firstName);
		obj.put("prezime",lastName);
		obj.put("vozrast",age);
		obj.put("grad",city);
		obj.put("indeks",index);
		String jsonText = JSONValue.toJSONString(obj);
		return jsonText;
		/*Contact[] a=getEmailContacts();
		Contact[] b=getPhoneContacts();
		for(int i=0;i<brP;i++)
			if(i+1==brP) s+=String.format('"%s"], ',b[i].getPhone());
			else s+=String.format('"%s", ',b[i].getPhone());
		s+=String.format('"emailKontakti":[');
		for(int i=0;i<brE;i++)
			if(i+1==brE) s+=String.format('"%s"]}',a[i].getEmail());
			else s+=String.format('"%s", ',a[i].getEmail());
		return s; 
	} */
}
	public static void main(String[] args){

		/*Student s1=new Student("Sanja","Mihova","Skopje",21,82907017);
		Student s2=new Student("Emil","Stankov","Bitola",29,43009931);
		s1.addEmailContact("2012-04-10","sanja@ef.ukim.mk");
		s1.addEmailContact("2012-04-10","sanja@exm");
		s2.addPhoneContact("2010-03-05","070/744-235");
		//System.out.println();
		System.out.println(s1);
		/*Contact[] a=s1.getEmailContacts();
		Contact[] b=s2.getPhoneContacts();
		Contact c=s1.getLatestContact();
		System.out.println(c.getEmail());
		for(int i=0;i<b.length;i++)
		System.out.println(b[i].getPhone()); */
	}

}