package prerna;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.regex.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

class Event{

	String name="";
	String contact_no="";
	String address="";
	String event_name="";
	String venue="";
	String date;
	int amount;
	Event next;
	Event rear,front;
	int priority;
	int a=0;
	int token;
	Event(String name,String contact_no,String address,String event_name,String venue,String date,int amount,int a,int token){
		this.name=name;
		this.contact_no=contact_no;
		this.address=address;
		this.event_name=event_name;
		this.venue=venue;
		this.date=date;
		this.amount=amount;
		next=null;
		this.priority=0;
		this.front=this.rear=null;
		this.token=token;

	}

}
class manage {
	Event rear,front;
	Event currnode=null;
	Scanner sc=new Scanner(System.in);
	int amount;
	Event stack;
	Event ptr=null;
	Event front1=null,rear1=null;
	Event que;
	int i=0;
	ArrayList<Integer> ar=new ArrayList<Integer>();
	List<Integer> r=new ArrayList<Integer>();
	boolean isValid(String s) {                 //checks whether the date is valid or not
		Pattern p=Pattern.compile("^\\d{10}$");
		Matcher m=p.matcher(s);
		return (m.matches());
	}
	boolean validate(String date) {
		if(date.trim().equals("")) {
			return true;
		}
		else {
			SimpleDateFormat frmt=new SimpleDateFormat("yyyy/MM/dd");  //formatting of date
			frmt.setLenient(false);
			try {
				Date javaDate=frmt.parse(date);
			}
			catch(ParseException e ) {
				System.out.println("       "+date +" is Invalid");
				return false;
			}
			return true;
		}
	}
	void customer_details() {
		int k=0;

		int flag=0;
		int a=0;
		String b=" ";

		b=sc.nextLine();
		System.out.print("	| Enter customer name:				");
		String name=sc.nextLine();
		String contact_no;
		do {                                                       //10-digit phone number is only valid
			System.out.print("	| Enter contact number:				");
			contact_no=sc.nextLine();
			if(isValid(contact_no)) {
				k=0;
			}else {
				System.out.println("		Invalid phone number");
				k=1;
			}
		}while(k==1);

		System.out.print("	| Enter address:				");
		String address=sc.nextLine();
		System.out.print("	| Enter event name:				");
		String event_name=sc.nextLine();
		System.out.print("	| Enter venue:					");
		String venue=sc.nextLine();
		String date;
		do {                                                   //validation of date
			System.out.println("  	  When you want to conduct the event ");
			System.out.print("	| Enter date(yyyy/MM/dd):			");
			date=sc.next();//1-31 
			if(validate(date)) {
				k=0;
			}
			else {
				System.out.println("  	| Enter valid date...");
				System.out.println();
				k=1;
			}
		}while(k==1);
        System.out.println();
		System.out.println(" 	| You need to pay 50% in advance");  //advance payment
		System.out.println();
		System.out.println("	 Which payment mode would you prefer? 		");
		System.out.print("        |(CASH/CREDITCARD/UPI):					 ");
		String pay_mode=sc.next();
		switch(pay_mode.toLowerCase()) {
		case "creditcard":
			System.out.print("	| Enter amount(in Rs): 				");
			amount=sc.nextInt();
			System.out.println();
			System.out.println("	| Card Swipping.....");
			System.out.println("	 ~Received Rs."+ amount +"successfully");
			break;
		case "upi":
			System.out.print("	| Enter amount(in Rs):					 ");
			amount=sc.nextInt();
			System.out.println();
			System.out.println("	| Scanning...");
			System.out.println("	 	~Received Rs."+ amount+" successfully");
			break;
		case "cash":
			System.out.print("	| Enter amount(in Rs):					");
			amount=sc.nextInt();
			System.out.println();
			System.out.println("	 ~Received Rs."+ amount+" successfully");
			break;
		}
		System.out.println();
		System.out.print("    | Provide token number : 				  ");
		int token=sc.nextInt();

		Event newnode=new Event(name,contact_no,address,event_name,venue,date,amount,a,token);

		if(flag==0) {                          //customers added in queue according to dates(ascending order)
			Event start = front;
			if (front == null) {
				front = rear = newnode;
				bill(newnode);
				return;
			}
			if (newnode.date.compareTo(front.date)<0) {
				newnode.next = front;
				(front) = newnode;
			}
			else {
				while (start.next != null &&
						start.next.date.compareTo(newnode.date)<0) {
					start = start.next;
				}
				newnode.next = start.next;
				start.next = newnode;
			}

			bill(newnode);
		}
		else if(flag==1) {
			System.out.println("	 CLASH");//if dates are same print clash
		}
	}





	int isEmpty(Event front)
	{
		return ((front) == null)?1:0;
	}


	void bill(Event newnode) {    //bill
		int p=0;
		do {
			System.out.print("\n	| Enter 1 to print the bill :			");
			p=sc.nextInt();
			if(p==1) {
				System.out.println();
				System.out.println("                 --------------------------------------------------------------");
				System.out.println("           			-*-*-*-*-*-* PICTURE PERFECT *-*-*-*-*-*-          ");
				System.out.println("                   			 ------- Events -------                     ");
				System.out.println("                  			We got you ,Lock it up ! \n          ");
				System.out.println("           				*       INVOICE       *                        ");
				System.out.println("           			| Customer name : 		"+newnode.name+"           ");
				System.out.println("           			| Contact number :		"+newnode.contact_no+"     ");
				System.out.println("           			| Event name : 			"+newnode.event_name+"     ");
				System.out.println("           			| Address : 			"+newnode.address+"        ");
				System.out.println("           			| Venue : 			"+newnode.venue+"              ");
				System.out.println("           			| Date :			"+newnode.date+"               ");
				System.out.println("           			| Amount :			"+newnode.amount+"           ");
				System.out.println(" 					| Token no :			"+newnode.token);
				System.out.println("\n                     ~ Advanced payment of Rs. "+newnode.amount+" done on "+newnode.date+" successfully.");
				System.out.println("                 --------------------------------------------------------------");
			}else {
				System.out.println(" 	 Try Again ....");
			}
		}while(p!=1);

	}

	void display() {       //upcoming events

		currnode=front;
		if(currnode==null) {
			System.out.println("	| No upcoming events ");
		}
		else {
			while(isEmpty(currnode)==0) {
				System.out.println("-------------------");
				System.out.println("		| Customer name : 		"+currnode.name);
				System.out.println("		| Contact number :		"+currnode.contact_no);
				System.out.println("		| Event name : 			"+currnode.event_name);
				System.out.println("		| Address : 			"+currnode.address);
				System.out.println("		| Venue : 			"+currnode.venue);
				System.out.println("		| Date :			"+currnode.date);
				System.out.println("		| Amount :			"+currnode.amount);
				System.out.println(" 		| Token no :			"+currnode.token);
				System.out.println("-------------------");
				currnode=currnode.next;
			}
		}

	}

	Event dequeue() {

		if (this.front == null)
			return null;
		Event temp = this.front;
		this.front = this.front.next;

		if (this.front == null)
			this.rear = null;
		return temp;
	}
	void done() {                //events done are linked using linked list
		Event node=dequeue();

		if(front1==null) {
			front1=node;
			node.next=null;
		}else {
			Event curr=front1;
			while(curr.next!=null) {
				curr=curr.next;
			}
			curr.next=node;
			node.next=null;
		}
	}
	void dis() {          //displays the list of events done
		Event ptr=front1;
		if(ptr==null) {
			System.out.println("List is empty");
		}
		else {
			while(ptr!=null) {
				System.out.println("--------");
				System.out.println("	| Event name : 			"+ptr.event_name);
				System.out.println("	| Venue : 			"+ptr.venue);
				System.out.println("	| Date :			"+ptr.date);
				System.out.println("	| Amount :			"+ptr.amount);
				System.out.println("--------");
				ptr=ptr.next;
			}
		}
	}

	void search() {              //finds customer by token number for full payment
		currnode=front;
		System.out.print("	 | Enter the token number :			");
		int tok=sc.nextInt();
		if(currnode==null) {
			System.out.println("		--No events for now");

		}
		else {
			while(currnode.next!=null) {
				if(currnode.token==tok) {
					System.out.println("	| Customer name : 		"+currnode.name);
					System.out.println("	| Contact number :		"+currnode.contact_no);
					System.out.println("	| Event name : 			"+currnode.event_name);
					System.out.println("	| Address : 			"+currnode.address);
					System.out.println("	| Venue : 			"+currnode.venue);
					System.out.println("	| Date :			"+currnode.date);
					System.out.println("	| Amount :			"+currnode.amount);
					System.out.print("		--Paid full payment now which is :");
					currnode.amount=sc.nextInt();
					ar.add(currnode.amount);
					System.out.print("	| Ratings(* * * * *):                ");
					currnode.a=sc.nextInt();
					r.add(currnode.a);
				}
				currnode=currnode.next;

			}
			if(currnode.next==null) {
				if(currnode.token==tok) {
					System.out.println("	| Customer name : 		"+currnode.name);
					System.out.println("	| Contact number :		"+currnode.contact_no);
					System.out.println("	| Event name : 			"+currnode.event_name);
					System.out.println("	| Address : 			"+currnode.address);
					System.out.println("	| Venue : 			"+currnode.venue);
					System.out.println("	| Date :			"+currnode.date);
					System.out.println("	| Amount :			"+currnode.amount);
					System.out.print("		--Full payment received :			");
					currnode.amount=sc.nextInt();
					ar.add(currnode.amount);
					System.out.print("	| Ratings :                ");
					currnode.a=sc.nextInt();
					r.add(currnode.a);
				}

			}
		}

	}
	void pay() {           // full payments are stored in array using arraylist
		int sum=0;
		int j=0;
		if(ar.isEmpty()) {
			System.out.println("	| No payment done");
			return;
		}
		else{
			for(j=0;j<ar.size();j++) {

				sum=sum+ar.get(j);

			}
			System.out.print(" 	| Total  payment till date:		"+sum);	//prints the total payment received till date
			System.out.println();
		}

	}

	void ratings() {      //ratings stored in array using arraylist
		int rate=0;
		int j=0;

		if(r.isEmpty()) {
			System.out.println("	| No ratings ");
			return;
		}
		else {
			for(j=0;j<r.size();j++) {
				rate=rate+r.get(j);

			}
		}
		if(j!=0) {
			System.out.print("	| Total ratings:		 "+rate/(j));//prints average rating
		}


	}




}

public class Asiignment7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		manage obj=new manage();

		int n=0;
		int x=0;
		String ch=" ";
		do {
			System.out.println("\n\n");
			System.out.println("				-*-*-*-*-*-* PICTURE PERFECT *-*-*-*-*-*-");
			System.out.println("        				 ------- Events -------");
			System.out.println("       					 We got you ,Lock it up ! \n\n");
			System.out.println("		~Build for Impact	~One of a kind venues	 ~Best rates Guaranteed  \n\n		~Expertly curated  "
					+ "	~No-Fuss planning	 ~Teams of all sizes \n");
			System.out.print("	| Enter 1 to continue ...	");
			int l=sc.nextInt();
			if(l==1) {
				String username, password;
				int bool=1; 
				int y=1;
				do {
					System.out.println("\n 	| Login to your account ...");//login option for admin
					System.out.print("	| Enter username :		");//user name
					username = sc.next();
					System.out.print("	| Enter password :		");//password
					password = sc.next();
					if(username.equals("admin") && password.equals("admin123"))//successful log in when user name and password are same as given here
					{
						System.out.println("	| Authentication Successful");
					
						bool=1;
						break;
					}
					else
					{
						bool=0;
						System.out.println("	  ~ Authentication Failed");
						System.out.println();
						System.out.print("	| Press 1 to login again 	");
						y=sc.nextInt();
					}

				}while(y==1);

				if(bool==1) {
					do {
						System.out.println("\n 					=======EVENTS======\n");
						System.out.println(" 	 ~Parties 	~Weddings 		~Conferences		~Seminars	\n	 ~Trade shows	~Networking Events	~Ceremonies		~Product	\n	 ~Launches "
								+ "	~Job fairs		~Board meetings		~VIP Events ");
						System.out.println("\n			********** SYSTEM MENU **********");
						System.out.println("	  1.Enter customer details ");
						System.out.println(" 	  2.Payments Update");
						System.out.println(" 	  3.Upcoming events ");
						System.out.println("  	  4.Payments Till date");
						System.out.println("  	  5.Events done successfully");  
						System.out.println(" 	  6.Rating  of events ");
						System.out.print("\n		 --Enter choice :		");
						ch=sc.next();
						switch(ch) {
						case "1":

							obj.customer_details();
							break;
						case "2":
							obj.search();		
							break;
						case "3":
							System.out.println("  				| UPCOMING EVENTS ");
							obj.display();
							break;
						case "4":
							obj.pay();
							break;
						case "5":
							System.out.println(" 		*********Events Successfully completed***********");
							obj.done();
							obj.dis();
							break;
						case "6":
							obj.ratings();
							break;
						default:
							System.out.println("		~~Invalid");
						}
						System.out.println("\n		--Press 1 to continue /Press any key to log out 	");
						x=sc.nextInt();
					}while(x==1);

					System.out.println("	--Logged out successfull ");
				}

			}
			else {
				System.out.println("	 Enter valid input");
			}
			System.out.println();
			System.out.println("		__Press 1 to go back to home page.");
			n=sc.nextInt();
		}while(n==1);

	}

}


