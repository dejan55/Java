package naprednoL7;		
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

class ChatRoom{          // prvo se registrira use ama nema sobi????
	protected String ime;
	protected TreeSet<String> korisnici;
	
	public ChatRoom(String name){
		ime = name;
		korisnici = new TreeSet<String>();
	}
	
	public void addUser(String username){
		korisnici.add(username);
	}
	
	public void removeUser(String username){
		korisnici.remove(username);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(ime);
		sb.append("\n");
		if(korisnici.isEmpty()){
			sb.append("EMPTY\n");
		}
		else{
			for (String string : korisnici) {
				sb.append(string);
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	public boolean hasUser(String username){
		return korisnici.contains(username);
	}
	
	public int numUsers(){
		return korisnici.size();
	}
}

class ChatSystem{
	protected TreeMap<String, ChatRoom> sistem;
	protected HashMap<String, ArrayList<String>> koris; //ime na korisnik, sobi
	int s = 0;
	public ChatSystem(){
		sistem = new TreeMap<String, ChatRoom>();
		koris = new HashMap<String, ArrayList<String>>();
	}
	
	public void addRoom(String roomName){
		sistem.put(roomName, new ChatRoom(roomName));
	}
	
	public void removeRoom(String roomName){
		sistem.remove(roomName);
	}
	
	public ChatRoom getRoom(String roomName) throws NoSuchRoomException{
		ChatRoom ret = sistem.get(roomName);
		if(ret == null)
			throw new NoSuchRoomException(roomName);
		else return ret;
	}
	
	public void register(String userName){
		if (!sistem.isEmpty()) {
			String k = "";
			int min = Integer.MAX_VALUE;
			for (String key : sistem.keySet()) {
				if (sistem.get(key).numUsers() < min) {
					min = sistem.get(key).numUsers();
					k = key;
				}
			}
			koris.put(userName, new ArrayList<String>());
			koris.get(userName).add(sistem.get(k).ime);
			sistem.get(k).addUser(userName);
		}
		else{
			koris.put(userName, new ArrayList<String>());
		}
	}
	
	public void registerAndJoin(String userName, String roomName){
		koris.put(userName, new ArrayList<String>());
		koris.get(userName).add(sistem.get(roomName).ime);
		sistem.get(roomName).addUser(userName);
	}
	
	public void joinRoom(String userName, String roomName) throws NoSuchRoomException, NoSuchUserException{
	//	System.out.println(s++);
		if(!sistem.containsKey(roomName))
			throw new NoSuchRoomException(roomName);
		else{
			if(!koris.containsKey(userName))
				throw new NoSuchUserException(userName);
			else{
				koris.get(userName).add(sistem.get(roomName).ime);
				sistem.get(roomName).addUser(userName);
			}
		}
	}
	
	public void leaveRoom(String userName, String roomName) throws NoSuchRoomException, NoSuchUserException{
		if(!sistem.containsKey(roomName))
			throw new NoSuchRoomException(roomName);
		else{
			if(!koris.containsKey(userName))
				throw new NoSuchUserException(userName);
			else{
				koris.get(userName).remove(sistem.get(roomName).ime);
				sistem.get(roomName).removeUser(userName);
			}
		}
	}
	
	public void followFriend(String username, String friendUsername) throws NoSuchUserException, NoSuchRoomException{
		if(!koris.containsKey(friendUsername))
			throw new NoSuchUserException(friendUsername);
		else if(username.equals(friendUsername)){
			
		}
		else{
			for (int i = 0; i < koris.get(friendUsername).size(); i++) {
				if(sistem.containsKey(koris.get(friendUsername).get(i))){
					if (sistem.get(koris.get(friendUsername).get(i)).hasUser(friendUsername)) {
						joinRoom(username, koris.get(friendUsername).get(i));
					}
					else{
						String s = koris.get(friendUsername).get(i);
						koris.get(username).add(s);
					}
				}
				else{
				String s = koris.get(friendUsername).get(i);
				koris.get(username).add(s);
				}
			}
		}
	}
}

class NoSuchRoomException extends Exception{
	public NoSuchRoomException(String roomName){
		super(roomName);
	}
}

class NoSuchUserException extends Exception{
	public NoSuchUserException(String userName){
		super(userName);
	}
}

public class ChatSystemTest {
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchRoomException {
		Scanner jin = new Scanner(System.in);
		int k = jin.nextInt();
		if ( k == 0 ) {
			ChatRoom cr = new ChatRoom(jin.next());
			int n = jin.nextInt();
			for ( int i = 0 ; i < n ; ++i ) {
				k = jin.nextInt();
				if ( k == 0 ) cr.addUser(jin.next());
				if ( k == 1 ) cr.removeUser(jin.next()); 
				if ( k == 2 ) System.out.println(cr.hasUser(jin.next()));  
			}
			System.out.println("");
			System.out.println(cr.toString());
			n = jin.nextInt();
			if ( n == 0 ) return;
			ChatRoom cr2 = new ChatRoom(jin.next());
			for ( int i = 0 ; i < n ; ++i ) {
				k = jin.nextInt();
				if ( k == 0 ) cr2.addUser(jin.next());
				if ( k == 1 ) cr2.removeUser(jin.next()); 
				if ( k == 2 ) cr2.hasUser(jin.next());  
			}
            System.out.println(cr2.toString());
		}	
       if ( k == 1 ) {
			ChatSystem cs = new ChatSystem();
			Method mts[] = cs.getClass().getMethods();
			while ( true ) {
				String cmd = jin.next();
				if ( cmd.equals("stop") ) break;
				if ( cmd.equals("print") ) {
					System.out.println(cs.getRoom(jin.next())+"\n");continue;
				}
				for ( Method m : mts ) {
					if ( m.getName().equals(cmd) ) {
						String params[] = new String[m.getParameterTypes().length];
						for ( int i = 0 ; i < params.length ; ++i ) params[i] = jin.next();
						m.invoke(cs,params);
					}
				}				
			}
		}
	}

}
