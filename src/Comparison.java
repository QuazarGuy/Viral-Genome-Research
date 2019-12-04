import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Comparison {

	public static final int MAX_DISTANCE = 0;
	public static final int MIN_AMINO_ACIDS = 7;

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {

		// Reads proteins from file to an arraylist of strings
		Pattern sequencep = Pattern.compile("translation=\\\"(.*?)\\\"");
		ArrayList<String> strings = new ArrayList<String>();
		File dir = new File("Viruses/");
//		int num=0;
		for (File f : dir.listFiles()) {
//			if(num++>30)
//				break;
			Scanner s = new Scanner(f);
			String buffer = "";
			while (s.hasNextLine()) {
				buffer += s.nextLine();
			}
			s.close();
			Matcher seqs = sequencep.matcher(buffer);
			String full = "";
			while (seqs.find()) {
				full += seqs.group(1);
			}
			strings.add(full.toUpperCase().replaceAll("\n", "").replaceAll("[ 1-9]", ""));
			System.out.println(f.getName());
			strings.add(f.getName());
		}

		String[] genes = strings.toArray(new String[0]); // Even indices are proteins, the next odd is the virus name

		/*
		 * for(int i=0;i<genes.length;i++) {
		 * genes[i]=genes[i].toUpperCase().replaceAll("\n", "").replaceAll("[ 1-9]","");
		 * }
		 */

		// ThreadPoolExecutor pool=new ThreadPoolExecutor();
		ConcurrentLinkedQueue<List<String>> results = new ConcurrentLinkedQueue<List<String>>();
		ExecutorService pool = Executors.newCachedThreadPool();

		for (int i = 0; i < genes.length; i += 2) {
			for (int j = i + 2; j < genes.length; j += 2) {
				final int CurrentI = i;
				final int CurrentJ = j;

				pool.execute(() -> {
					List<String> matches = getSequences(genes[CurrentI], genes[CurrentJ]);
					if (matches.size() > 0) {
//						System.out.printf("%s,%s,%s\n",genes[CurrentI+1],genes[CurrentJ+1],matches.toString());
						matches.add(0, genes[CurrentI + 1]);
						matches.add(0, genes[CurrentJ + 1]);
						results.offer(matches);
					}
					System.out.println((CurrentI >> 1) + "," + (CurrentJ >> 1));
				});

			}
		}
		pool.shutdown();
		pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
		File output = new File("outputSeperated.txt");
		PrintWriter pw = new PrintWriter(output);
		while (!results.isEmpty()) {
			pw.println(results.poll());
		}
		pw.close();
	}

	static List<String> getSequences(String a, String b) {
		return getSequences(a, b, MIN_AMINO_ACIDS);
	}

	static List<String> getSequences(String g1, String g2, int max) {
		ArrayList<String> found = new ArrayList<String>();
		for (int i = 0; i < g1.length() - 1; i++) {
			for (int j = i; j < g2.length() - 1; j++) {

				if (g1.charAt(i) != g2.charAt(j)) {
					continue;
				}
				int dist = MAX_DISTANCE;
				int count = relation(g1, g2, i, j, dist);
				if (i + count < g1.length() && j + count < g2.length() && count >= max) {
					int g1m = g1.length() < i + count + dist ? g1.length() - 1 : i + count + dist;
					found.add(g1.substring(i, g1m));

					/*
					 * if(count>max)max=count; System.out.println("###MATCH### "+count); int
					 * g1m=g1.length()<i+count+dist?g1.length()-1:i+count+dist; int
					 * g2m=g2.length()<j+count+dist?g2.length()-1:j+count+dist;
					 * System.out.println(g1.substring(i, g1m)); System.out.println(g2.substring(j,
					 * g2m));
					 */
				}
			}
		}
		for (int i = found.size() - 1; i > 0; i--) {
			if (found.get(i - 1).substring(1).equals(found.get(i))) {
				found.remove(i);
			}
		}
		return found;

	}

	public static int relation(String g1, String g2, int i, int j, int maxDistance) {
		int count = 0;

		while (i + count < g1.length() && j + count < g2.length() && g1.charAt(i + count) == g2.charAt(j + count)) {
			count++;
		}
		if (i + count >= g1.length() || j + count >= g2.length() || maxDistance <= 0) {
			return count;
		}
		i += count;
		j += count;
		int l = relation(g1, g2, i + 1, j, maxDistance - 1);
		int r = relation(g1, g2, i, j + 1, maxDistance - 1);
		int c = relation(g1, g2, i + 1, j + 1, maxDistance - 1);

		// System.out.printf("%d\t%d\t%d\t\n",l,r,c);
		return count + Math.max(Math.max(l, r), c);

	}

}
