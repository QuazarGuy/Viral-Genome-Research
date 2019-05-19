
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraper {

	public static void main(String[] args) throws IOException {
		ArrayList<Virus> viruses = new ArrayList<Virus>();
		// connect to site
		URL url = new URL("https://viralzone.expasy.org/678");
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(url.openStream())));
		System.out.println("Connected");

		// buffer data from site locally
		String buffer = "";
		while (s.hasNextLine()) {
			buffer += s.nextLine();
		}
		s.close();
		
		// look for the subject list with items starting with that initial()
		Pattern list = Pattern.compile("<table id=\\\"table\\\"([\\s\\S]*?)</table>");
		Matcher listm = list.matcher(buffer);
		if (listm.find()) {
			buffer = listm.group(0);

		} else {
			System.err.println("could not find table, quitting");
			System.exit(-1);
		}
		Pattern rowp = Pattern.compile("<tr>([\\s\\S]*?)</tr>");
		Matcher row = rowp.matcher(buffer);
		if (row.find()) {

			String rawheadings = row.group(1);
			Pattern headp = Pattern.compile("<th.*?>([^>]*?)</th>");
			Matcher head = headp.matcher(rawheadings);
			ArrayList<String> headings = new ArrayList<String>();
			while (head.find()) {
				headings.add(head.group(1));
			}

			while (row.find()) {
				Pattern cellp = Pattern.compile("<td[^>]*?>(.*?)</td>");
				Matcher cells = cellp.matcher(row.group(0));

				String virus, genus, host, transmission, disease, proteome;
				virus = genus = host = transmission = disease = proteome = "";
				String genome = "";

				int column = 0;
				while (cells.find()) {
					if (column > headings.size()) {
						break;
					} else if (headings.get(column).toLowerCase().contains("virus")) {
						virus = cells.group(1);
					} else if (headings.get(column).toLowerCase().contains("genus")) {
						genus = cells.group(1);
					} else if (headings.get(column).toLowerCase().contains("host")) {
						host = cells.group(1);
					} else if (headings.get(column).toLowerCase().contains("transmission")) {
						transmission = cells.group(1);
					} else if (headings.get(column).toLowerCase().contains("disease")) {
						disease = cells.group(1);
					} else if (headings.get(column).toLowerCase().contains("genome")) {
						DownloadGenome(cells.group(1), genome, virus);

					} else if (headings.get(column).toLowerCase().contains("proteome")) {
						proteome = cells.group(1);
					}
					column++;
				}

			}

		} else {
			System.err.println("table contains no rows, quitting");
			System.exit(-1);
		}

		System.out.println(viruses);
	}

	private static void DownloadGenome(String capture, String genome, String name) {
		// https://www.ncbi.nlm.nih.gov/sviewer/viewer.cgi?tool=portal&save=file&log$=seqview&db=nuccore&report=genbank&id=253761958&extrafeat=976&conwithfeat=on&withparts=on

		Pattern linkp = Pattern.compile("href=\\\".*?(NC_.*?)\\\"");
		Matcher links = linkp.matcher(capture);
		int sequence = 1;
		while (links.find()) {
			try {
				String link = "https://www.ncbi.nlm.nih.gov/nucleotide/" + links.group(1);
				URL url = new URL(link);
				Scanner s = new Scanner(new BufferedReader(new InputStreamReader(url.openStream())));
				String buffer = "";
				while (s.hasNextLine()) {
					buffer += s.nextLine();
				}
				s.close();

				Pattern cdsp = Pattern.compile("name=\\\"ncbi_uidlist\\\" content=\\\"([0-9]+)\\\"");
				Matcher cdss = cdsp.matcher(buffer);
				if (cdss.find()) {
					String file = getFile(
							"https://www.ncbi.nlm.nih.gov/sviewer/viewer.cgi?tool=portal&save=file&log$=seqview&db=nuccore&report=genbank&id="
									+ cdss.group(1) + "&extrafeat=976&conwithfeat=on&withparts=on");

					File dir = new File("Viruses");
					if (!dir.exists())
						dir.mkdirs();

					File f = new File("Viruses/" + name.replaceAll("<.*?>", "").replaceAll("[\\\\/]", "|") + "-"
							+ sequence + ".gb");
					sequence++;
					FileWriter w = new FileWriter(f);

					w.write(file);
					w.flush();
					w.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private static String getFile(String link) throws IOException {
		System.out.println("scraping:" + link);
		URL url = new URL(link);
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(url.openStream())));
		String buffer = "";
		while (s.hasNextLine()) {
			buffer += s.nextLine() + "\n";
		}
		s.close();
		return buffer;
	}

}
