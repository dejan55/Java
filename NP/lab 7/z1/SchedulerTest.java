import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Collections;

import javax.swing.text.html.HTMLDocument.Iterator;

public class SchedulerTest {

	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		int k = jin.nextInt();
		if (k == 0) {
			Scheduler<String> scheduler = new Scheduler<String>();
			Date now = new Date();
			scheduler.add(new Date(now.getTime() - 7200000), jin.next());
			scheduler.add(new Date(now.getTime() - 3600000), jin.next());
			scheduler.add(new Date(now.getTime() - 14400000), jin.next());
			scheduler.add(new Date(now.getTime() + 7200000), jin.next());
			scheduler.add(new Date(now.getTime() + 14400000), jin.next());
			scheduler.add(new Date(now.getTime() + 3600000), jin.next());
			scheduler.add(new Date(now.getTime() + 18000000), jin.next());
			System.out.println(scheduler.getFirst());
			System.out.println(scheduler.getLast());
		}
		if (k == 3) { // test Scheduler with String
			Scheduler<String> scheduler = new Scheduler<String>();
			Date now = new Date();
			scheduler.add(new Date(now.getTime() - 7200000), jin.next());
			scheduler.add(new Date(now.getTime() - 3600000), jin.next());
			scheduler.add(new Date(now.getTime() - 14400000), jin.next());
			scheduler.add(new Date(now.getTime() + 7200000), jin.next());
			scheduler.add(new Date(now.getTime() + 14400000), jin.next());
			scheduler.add(new Date(now.getTime() + 3600000), jin.next());
			scheduler.add(new Date(now.getTime() + 18000000), jin.next());
			System.out.println(scheduler.next());
			System.out.println(scheduler.last());
			ArrayList<String> res = scheduler.getAll(new Date(
					now.getTime() - 10000000), new Date(
					now.getTime() + 17000000));
			Collections.sort(res);
			for (String t : res) {
				System.out.print(t + " , ");
			}
		}
		if (k == 4) {// test Scheduler with ints complex
			Scheduler<Integer> scheduler = new Scheduler<Integer>();
			int counter = 0;
			ArrayList<Date> to_remove = new ArrayList<Date>();

			while (jin.hasNextLong()) {
				Date d = new Date(jin.nextLong());
				int i = jin.nextInt();
				if ((counter & 7) == 0) {
					to_remove.add(d);
				}
				scheduler.add(d, i);
				++counter;
			}
			jin.next();

			while (jin.hasNextLong()) {
				Date l = new Date(jin.nextLong());
				Date h = new Date(jin.nextLong());
				ArrayList<Integer> res = scheduler.getAll(l, h);
				Collections.sort(res);
				System.out.println(l + " <: " + print(res) + " >: " + h);
			}
			System.out.println("test");
			ArrayList<Integer> res = scheduler.getAll(new Date(0), new Date(
					Long.MAX_VALUE));
			Collections.sort(res);
			System.out.println(print(res));
			for (Date d : to_remove) {
				scheduler.remove(d);
			}
			res = scheduler.getAll(new Date(0), new Date(Long.MAX_VALUE));
			Collections.sort(res);
			System.out.println(print(res));
		}
	}

	private static <T> String print(ArrayList<T> res) {
		if (res == null || res.size() == 0)
			return "NONE";
		StringBuffer sb = new StringBuffer();
		for (T t : res) {
			sb.append(t + " , ");
		}
		return sb.substring(0, sb.length() - 3);
	}

}

class Scheduler<T> {
	protected HashMap<Date, T> mapa;

	public Scheduler() {
		mapa = new HashMap<Date, T>();
	}

	public void add(Date d, T t) {
		mapa.put(d, t);
	}

	public boolean remove(Date d) {
		T tmp = mapa.remove(d);
		if (tmp == null)
			return false;
		else
			return true;
	}

	public T next() {
		long max = Long.MAX_VALUE;
		Date sega = new Date();
		Date tmp = new Date();
		for (Date key : mapa.keySet()) {
			if (key.after(sega)) {
				if (key.getTime() - sega.getTime() < max) {
					max = key.getTime() - sega.getTime();
					tmp = key;
				}
			}
		}
		return mapa.get(tmp);
	}

	public T last() {
		long max = Long.MAX_VALUE;
		Date sega = new Date();
		Date tmp = new Date();
		for (Date key : mapa.keySet()) {
			if (key.before(sega)) {
				if (sega.getTime() - key.getTime() < max) {
					max = sega.getTime() - key.getTime();
					tmp = key;
				}
			}
		}
		return mapa.get(tmp);
	}

	public ArrayList<T> getAll(Date begin, Date end) {
		ArrayList<T> nova = new ArrayList<T>();
		for (Date key : mapa.keySet()) {
			if (key.before(end)&&key.after(begin)) {
				nova.add(mapa.get(key));
			}
		}
		return nova;
	}

	public T getFirst() {
		long max = Long.MAX_VALUE;
		Date tmp = new Date();
		for (Date key : mapa.keySet()) {
			if (key.getTime() < max) {
				max = key.getTime();
				tmp = key;
			}
		}
		return mapa.get(tmp);
	}

	public T getLast() {
		long max = 0;
		Date tmp = new Date();
		for (Date key : mapa.keySet()) {
			if (key.getTime() > max) {
				max = key.getTime();
				tmp = key;
			}
		}
		return mapa.get(tmp);
	}
}