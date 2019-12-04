
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import algorithms.Find;
import algorithms.StringHasher;
import database.Virus;
import database.VirusDB;

public class Main {

	public static HashMap<String, HashSet<String>> blacklist = new HashMap<String, HashSet<String>>();

	private static List<Virus> viruses;
	private static VirusDB db;

	public static void main(String args[]) throws InterruptedException, IOException, SQLException {
		System.out.println("opening link to db");
		db = new VirusDB();
		System.out.println("database");
		db.clear();
		System.out.println("getting viruses");
		viruses = db.getViruses();

		System.out.println("starting scanner");
		long time = System.currentTimeMillis();
		run();
		System.out.println(System.currentTimeMillis() - time);
	}

	public static void makeBlacklist() {
		String[] pox = new String[] { "monkeypox virus", "variola virus", "cowpox virus", "vaccinia virus" };

		HashSet<String> poxset = new HashSet<String>();
		for (String s : pox) {
			poxset.add(s);
			blacklist.put(s, poxset);
		}
	}

	public static void run() throws InterruptedException, IOException, SQLException {
		// get number of threads to use
		final int threads = Runtime.getRuntime().availableProcessors();

		int tot = 0;
		// allocate a map for each thread
		StringHasher[] hashers = new StringHasher[threads];

		// find all matching sequences length 5 to 14
		for (int min = 6; min <= 14; min++) {
			long time = System.currentTimeMillis();

			// re allocate map for each thread
			for (int i = 0; i < threads; i++) {
				hashers[i] = new StringHasher(min);
			}

			// make a thread pool to give tasks too
			ExecutorService pool = Executors.newFixedThreadPool(threads);

			for (int i = 0; i < viruses.size(); i++) {
				final int CurrentI = i;
				pool.execute(() -> {

					// System.err.println(Thread.currentThread().getName());
					int thread = Integer.parseInt(Thread.currentThread().getName()
							.substring(Thread.currentThread().getName().lastIndexOf("-") + 1)) - 1;
					// this relies on the threadpool haveing sequential thread numbers, it's not a
					// guarentee
					// int thread= (int)(Thread.currentThread().getId()%threads);

					// ingest each of the sequences in this virus
					for (String s : viruses.get(CurrentI).getGenome()) {
						hashers[thread].Ingest(s, viruses.get(CurrentI).toString());
					}
				});
			}

			pool.shutdown();
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
			long time2 = System.currentTimeMillis();

			System.out.println("finished seq in " + (time2 - time));
			for (int i = 1; i < threads; i++) {
				hashers[0].join(hashers[i]);

			}

			hashers[0].add(db);
			tot += hashers[0].max;
			long time3 = System.currentTimeMillis();
			System.out.println("finished db in " + (time3 - time2));
		}
		System.out.println(tot);
		makeBlacklist();
		long time = System.currentTimeMillis();
		int count = 0;
		/*
		 * for(ArrayList<Find> list:hashers[0]) { if(list.size()>1) { for(int
		 * i=0;i<list.size();i++) { for(int j=i+1;j<list.size();j++) {
		 * if(list.get(i).original!=list.get(j).original) {
		 * compare(list.get(i),list.get(j)); count++; }
		 * 
		 * } } }
		 * 
		 * }
		 */
		long end = System.currentTimeMillis();
		System.out.println(end - time);
		System.out.println(count);
		System.out.println(Main.count);
	}

	static int count = 0;

	private static void compare(Find find, Find find2) {
		if (blacklist.containsKey(find.name)) {
			if (blacklist.get(find.name).contains(find2.name)) {
				return;
			}
		}

		int i = find.location;
		int j = find2.location;
		count -= 14;
		while (find.original.length() > i && find2.original.length() > j
				&& find.original.charAt(i) == find2.original.charAt(j)) {
			i++;
			j++;
			count++;
		}
	}
}
