import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

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
class Student{
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
		c=new Contact[100];
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
		return this.city;
	}
	public String getFullName(){
		return String.format("%s %s",firstName,lastName);
	}
	public long getIndex(){
		return index;
	}
	public Contact getLatestContact(){
		int j=0;
		Contact max=c[0];
		for(int i=1;i<br;i++)
			if(!max.isNewerThan(c[i])) max=c[i];
		return max;
	}
	public int getNumb(){
		return br;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"ime\"" + ":");
		sb.append("\"" + firstName + "\"");
		sb.append(", \"prezime\"" + ":");
		sb.append("\"" + lastName + "\"");
		sb.append(", \"vozrast\"" + ":");
		sb.append(age);
		sb.append(", \"grad\"" + ":");
		sb.append("\"" + city + "\"");
		sb.append(", \"indeks\"" + ":");
		sb.append(index);
		sb.append(", \"telefonskiKontakti\"" + ":[");
		Contact[] a=getEmailContacts();
		Contact[] b=getPhoneContacts();
		for (int i = 0; i < brP; i++) {
		if(i+1==brP) sb.append("\"" + b[i].getPhone() + "\"");
		else{
		sb.append("\"" + b[i].getPhone() + "\"");
		sb.append(", ");
		}
		}
		sb.append("], ");
		sb.append("\"emailKontakti\"" + ":[");
		for (int i = 0; i < brE; i++) {
		if(i+1==brE) sb.append("\"" + a[i].getEmail() + "\"");
		else{
		sb.append("\"" + a[i].getEmail() + "\"");
		sb.append(", ");}
		}
		sb.append("]}");
		return sb.toString();
		}
	} 

class Faculty{
	private String name;
	private int k;
	private Student[] students;
	public Faculty(String name, Student [] students){
		this.name=name;
		k=students.length;
		this.students=new Student[k];
		for(int i=0;i<k;i++)
			this.students[i]=students[i];
	}
	public int countStudentsFromCity(String cityName){
		int br=0;
		for(int i=0;i<students.length;i++)
			if(students[i].getCity().equals(cityName)) br++;
		return br;
	}
	public Student getStudent(long index){
		for(int i=0;i<k;i++)
			if(this.students[i].getIndex()==index) return students[i];
		return null;
		}
	public double getAverageNumberOfContacts(){
		double sum=0;
		for(int i=0;i<k;i++)
			sum+=students[i].getNumb();
		return sum/k;
	}
	public Student getStudentWithMostContacts(){
		int max=students[0].getNumb();
		int j=0;
		for(int i=1;i<k;i++)
			if(max<=students[i].getNumb()) 
			if(max==students[i].getNumb()) {
			if(students[i].getIndex()>students[j].getIndex())
			j=i;
			}
			else
			{max=students[i].getNumb(); j=i;}
		return students[j];
	}
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("{\"fakultet\"" + ":");
		sb.append("\"" + name + "\"");
		sb.append(", \"studenti\"" + ":[");
		for(int i=0;i<k;i++)
		if(i+1==k) sb.append("" + students[i] + "");
		else{
		sb.append("" + students[i] + "");
		sb.append(", ");
		}
		sb.append("]}");
		return sb.toString();
	}

}
public class ContactsTester {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int tests = scanner.nextInt();
		Faculty faculty = null;

		int rvalue = 0;
		long rindex = -1;

		DecimalFormat df = new DecimalFormat("0.00");

		for (int t = 0; t < tests; t++) {

			rvalue++;
			String operation = scanner.next();

			switch (operation) {
			case "CREATE_FACULTY": {
				String name = scanner.nextLine().trim();
				int N = scanner.nextInt();

				Student[] students = new Student[N];

				for (int i = 0; i < N; i++) {
					rvalue++;

					String firstName = scanner.next();
					String lastName = scanner.next();
					String city = scanner.next();
					int age = scanner.nextInt();
					long index = scanner.nextLong();

					if ((rindex == -1) || (rvalue % 13 == 0))
						rindex = index;

					Student student = new Student(firstName, lastName, city,
							age, index);
					students[i] = student;
				}

				faculty = new Faculty(name, students);
				break;
			}

			case "ADD_EMAIL_CONTACT": {
				long index = scanner.nextInt();
				String date = scanner.next();
				String email = scanner.next();

				rvalue++;

				if ((rindex == -1) || (rvalue % 3 == 0))
					rindex = index;

				faculty.getStudent(index).addEmailContact(date, email);
				break;
			}

			case "ADD_PHONE_CONTACT": {
				long index = scanner.nextInt();
				String date = scanner.next();
				String phone = scanner.next();

				rvalue++;

				if ((rindex == -1) || (rvalue % 3 == 0))
					rindex = index;

				faculty.getStudent(index).addPhoneContact(date, phone);
				break;
			}

			case "CHECK_SIMPLE": {
				System.out.println("Average number of contacts: "
						+ df.format(faculty.getAverageNumberOfContacts()));

				rvalue++;

				String city = faculty.getStudent(rindex).getCity();
				System.out.println("Number of students from " + city + ": "
						+ faculty.countStudentsFromCity(city));

				break;
			}

			case "CHECK_DATES": {

				rvalue++;

				System.out.print("Latest contact: ");
				Contact latestContact = faculty.getStudent(rindex)
						.getLatestContact();
				if (latestContact.getType().equals("Email"))
					System.out.println(((EmailContact) latestContact)
							.getEmail());
				if (latestContact.getType().equals("Phone"))
					System.out.println(((PhoneContact) latestContact)
							.getPhone()
							+ " ("
							+ ((PhoneContact) latestContact).getOperator()
									.toString() + ")");

				if (faculty.getStudent(rindex).getEmailContacts().length > 0&&faculty.getStudent(rindex).getPhoneContacts().length > 0) {
					System.out.print("Number of email and phone contacts: ");
					System.out
							.println(faculty.getStudent(rindex)
									.getEmailContacts().length
									+ " "
									+ faculty.getStudent(rindex)
											.getPhoneContacts().length);

					System.out.print("Comparing dates: ");
					int posEmail = rvalue
							% faculty.getStudent(rindex).getEmailContacts().length;
					int posPhone = rvalue
							% faculty.getStudent(rindex).getPhoneContacts().length;

					System.out.println(faculty.getStudent(rindex)
							.getEmailContacts()[posEmail].isNewerThan(faculty
							.getStudent(rindex).getPhoneContacts()[posPhone]));
				}

				break;
			}

			case "PRINT_FACULTY_METHODS": {
				System.out.println("Faculty: " + faculty.toString());
				System.out.println("Student with most contacts: "
						+ faculty.getStudentWithMostContacts().toString());
				break;
			}

			}

		}

		scanner.close();
	}
}
