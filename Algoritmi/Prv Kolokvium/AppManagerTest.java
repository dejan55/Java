package naprednoL8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AppManagerTest {
	public static void main(String[] args) throws Exception {
		Scanner jin = new Scanner(System.in);
		int n = Integer.parseInt(jin.nextLine());
		App[] apps = new App[n];
		for (int i = 0; i < n; ++i) {
			apps[i] = new App(jin.nextLine(), jin.nextLine(), jin.nextInt(), jin.nextDouble());
			jin.nextLine();
		}
		int k = jin.nextInt();
		AppManager am = new AppManager(apps);
		if (k == 0) { // test everything but randomChoice
			while (true) {
				System.out.println();
				String cmd = jin.next();
				System.out.println(cmd);
				if (cmd.equals("stop"))
					break;
				if (cmd.equals("bestApp")) {
					print(am.bestApp());
				}
				if (cmd.equals("cheapestApp")) {
					print(am.cheapestApp());
				}
				if (cmd.equals("allApps")) {
					print(am.allApps(jin.next(), jin.nextBoolean()));
				}
				if (cmd.equals("ratingStats")) {
					System.out.println(Arrays.toString(am.ratingStats()));
				}
			}
		} else { // test randomChoice
			System.out.println("Testing random choice...");
			int w = jin.nextInt();
			for (int q = 0; q < w; ++q) {
				boolean[] flags = new boolean[n];
				for (int i = 0; i <= n / 3; ++i) {
					List<App> res = am.randomChoice();
					for (App a : res) {
						int idx = idxOf(apps, a);
						if (idx == -1)
							System.out
									.println("You returned an app that wasn't in the list at all? What are you doing???");
						if (flags[idx]) {
							System.out
									.println("You returned an app twice, before returning all the apps in the list. I want to see all the apps first then you can give me duplicates.");
							throw new Exception("App already returned");
						}
						flags[idx] = true;
					}
				}
			}
			System.out.println("Great work on implementing randomChoice. That is just what we needed.");
		}
	}

	private static void print(App app) {
		System.out.println("Name: " + app.getName());
		System.out.println("Rating: " + app.getRating());
		System.out.printf("Price: %.2f$\n", app.getPrice());
	}

	private static void print(List<App> apps) {
		for (App ap : apps) {
			print(ap);
			System.out.println();
		}
	}

	private static int idxOf(App apps[], App a) {
		for (int i = 0; i < apps.length; ++i)
			if (equal(apps[i], a))
				return i;
		return -1;
	}

	private static boolean equal(App a, App b) {
		return a.getName().equals(b.getName()) && a.getPrice() == b.getPrice() && a.getRating() == b.getRating();
	}

}

class App {
	private String name;
	private String description;
	private double price;
	private int rating;

	public App(String name, String description, int rating, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public int getRating() {
		return rating;
	}

}

class AppManager {
	protected List<App> lista;
	protected List<App> shuffle;

	public AppManager(App... apps) {
		lista = new ArrayList<App>();
		shuffle = new ArrayList<App>();
		for (int i = 0; i < apps.length; i++) {
			lista.add(apps[i]);
			shuffle.add(apps[i]);
		}
	}

	public App bestApp() {
		App res = lista.get(0);
		for (App app : lista) {
			if (app.getRating() > res.getRating()) {
				res = app;
			}
		}
		return res;
	}

	public App cheapestApp() {
		App res = lista.get(0);
		for (App app : lista) {
			if (app.getPrice() < res.getPrice()) {
				res = app;
			}
		}
		return res;
	}

	public List<App> randomChoice() {
		Collections.shuffle(shuffle);
		List<App> tmp = new ArrayList<App>();
		if (shuffle.size() > 3) {
			tmp = shuffle.subList(0, 3);
			shuffle = shuffle.subList(3, shuffle.size());
			return tmp;
		} else {
			tmp = shuffle.subList(0, shuffle.size());
			shuffle = shuffle.subList(0, 0);
			shuffle = new ArrayList<App>(lista);
			return tmp;
		}
	}

	public List<App> allApps(String attribute, boolean ascending) {
		List<App> tmp = new ArrayList<App>(lista);
		if (attribute.equals("name")) {
			if (ascending) {
				Collections.sort(tmp, new NameComparator());
			} else
				Collections.sort(tmp, Collections.reverseOrder(new NameComparator()));
		} else if (attribute.equals("price")) {
			if (ascending) {
				Collections.sort(tmp, new PriceComparator());
			} else
				Collections.sort(tmp, Collections.reverseOrder(new PriceComparator()));
		} else {
			if (ascending) {
				Collections.sort(tmp, new RatingComparator());
			} else
				Collections.sort(tmp, Collections.reverseOrder(new RatingComparator()));
		}
		return tmp;
	}

	public int[] ratingStats() {
		int[] tmp = new int[5];
		for (App app : lista) {
			tmp[app.getRating() - 1]++;
		}
		return tmp;
	}
}

class NameComparator implements Comparator<App> {

	@Override
	public int compare(App o1, App o2) {
		if (o1.getName().compareTo(o2.getName()) > 0) {
			return 1;
		} else if (o1.getName().compareTo(o2.getName()) < 0) {
			return -1;
		} else
			return 0;
	}

}

class PriceComparator implements Comparator<App> {

	@Override
	public int compare(App o1, App o2) {
		if (o1.getPrice() > o2.getPrice()) {
			return 1;
		} else if (o1.getPrice() < o2.getPrice()) {
			return -1;
		} else
			return 0;
	}
}

class RatingComparator implements Comparator<App> {

	@Override
	public int compare(App o1, App o2) {
		if (o1.getRating() > o2.getRating()) {
			return 1;
		} else if (o1.getRating() < o2.getRating()) {
			return -1;
		} else
			return 0;
	}
}
