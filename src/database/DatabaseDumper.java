package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseDumper {

	public static String[] getFileInDir(File dir) throws IOException {
		Pattern sequencep = Pattern.compile("translation=\\\"(.*?)\\\"", Pattern.DOTALL);
		ArrayList<String> strings = new ArrayList<String>();

		long start = System.currentTimeMillis();

		int num = 0;
		for (File f : dir.listFiles()) {
			if (num++ > 30)
				break;

			byte[] chars = Files.readAllBytes(f.toPath());

			String buffer = new String(chars);
			Matcher seqs = sequencep.matcher(buffer);
			StringBuilder full = new StringBuilder();
			while (seqs.find()) {
				full.append(seqs.group(1).trim());
				// full+=seqs;
			}
			strings.add(full.toString().replaceAll("[\n 1-9]", "").toUpperCase());
			System.out.println(f.getName());
			strings.add(f.getName());
		}
		System.out.println(System.currentTimeMillis() - start);

		return strings.toArray(new String[0]);
	}

	public static ArrayList<Virus> scrape(Connection connection) throws IOException, SQLException {
		ArrayList<Virus> viruses = new ArrayList<Virus>();

		// connect to site
		System.out.print("Connecting...");
		URL url = new URL("https://viralzone.expasy.org/678");
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(url.openStream())));
		System.out.println("Connected");

		// buffer data from site locally
		String buffer = "";
		while (s.hasNextLine()) {
			buffer += s.nextLine();
		}
		s.close();
		buffer = buffer.toLowerCase();

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
						virus = cells.group(1).replaceAll("<.*?>", "");
					} else if (headings.get(column).toLowerCase().contains("genus")) {
						genus = cells.group(1).replaceAll("<.*?>", "");
						;
					} else if (headings.get(column).toLowerCase().contains("host")) {
						host = cells.group(1).replaceAll("<.*?>", "");
						;
					} else if (headings.get(column).toLowerCase().contains("transmission")) {
						transmission = cells.group(1).replaceAll("<.*?>", "");
						;
					} else if (headings.get(column).toLowerCase().contains("disease")) {
						disease = cells.group(1).replaceAll("<.*?>", "");
						;
					} else if (headings.get(column).toLowerCase().contains("genome")) {

					} else if (headings.get(column).toLowerCase().contains("proteome")) {
						proteome = cells.group(1).replaceAll("<.*?>", "");
						;
					}
					column++;
				}
				// (String virus, String genus, String host, String transmission, String
				// diseases, List<String> genome,
				// String proteome) {

				String family = genus.split(",")[1];
				genus = genus.split(",")[1];
				virus.trim();
				genus.trim();
				family.trim();
				host.trim();
				transmission.trim();
				disease.trim();
				Virus v = new Virus(virus, genus, family, host, transmission, disease, null, null);
				viruses.add(v);
			}
		} else {
			System.err.println("table contains no rows, quitting");
			System.exit(-1);
		}
		System.out.println(viruses);
		return viruses;
	}

	public static void main(String[] args) throws IOException, SQLException {
		updateFromWeb();
		Connection connection = DriverManager.getConnection("jdbc:sqlite:VirusDb.sqlite");
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(30); // set timeout to 30 sec.
		File dir = new File("Viruses/");
		for (File f : dir.listFiles()) {
			String file = f.getName();
			int dash = file.lastIndexOf('-');
			String name = file.substring(0, dash).replace('~', '/').toLowerCase();
			int number = Integer.parseInt(file.substring(dash + 1, file.length() - 3));

			byte[] chars = Files.readAllBytes(f.toPath());

			String buffer = new String(chars);

			if (!statement.executeQuery("Select * from virus where name ='" + name + "'").next()) {
				System.out.println("MISSING " + name);
			} else {
				System.out.println("inserting " + name);
				PreparedStatement prep = connection.prepareStatement(String.format(
						"insert into genome(text,virus,subgene) values(?,(select id from virus where name ='%s'),'%d');",
						name, number));
				prep.setString(1, buffer);
				prep.execute();
			}
		}
	}

	public static void updateFromWeb() throws IOException {
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:VirusDb.sqlite");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			System.out.println("deleteing");
			statement.executeUpdate("delete from virus_host");
			System.out.println("deleteing");
			statement.executeUpdate("delete from virus_disease;");
			System.out.println("deleteing");
			statement.executeUpdate("delete from virus_transmission;");
			System.out.println("deleteing");
			statement.executeUpdate("delete from virus;");
			System.out.println("deleteing");
			statement.executeUpdate("delete from host;");
			System.out.println("deleteing");
			statement.executeUpdate("delete from transmission;");
			System.out.println("deleteing");
			statement.executeUpdate("delete from sqlite_sequence;");
			System.out.println("Finished");

			ArrayList<Virus> viruses = scrape(connection);
			HashSet<String> disease = new HashSet<String>();
			HashSet<String> host = new HashSet<String>();
			HashSet<String> transmission = new HashSet<String>();
			for (Virus v : viruses) {
				for (String s : v.getDiseases().split(",")) {
					disease.add(s.trim());
				}
				for (String s : v.getHost().split(",")) {
					host.add(s.trim());
				}
				for (String s : v.getTransmission().split(",")) {
					transmission.add(s.trim());
				}
				statement.executeUpdate(String.format("insert into virus(name,genus,family) values('%s','%s','%s');",
						v.getVirus(), v.getVirus(), v.family));
			}
			System.out.println("inserting disease");
			for (String s : disease) {
				statement.executeUpdate("insert into disease(name) values('" + s + "')");
			}
			System.out.println("inserting transmission");
			for (String s : transmission) {
				statement.executeUpdate("insert into transmission(name) values('" + s + "')");
			}
			System.out.println("inserting host");
			for (String s : host) {
				statement.executeUpdate("insert into host(name) values('" + s + "')");
			}

			for (Virus v : viruses) {
				System.out.println("inserting" + v.virus);
				for (String s : v.getDiseases().split(",")) {
					statement.executeUpdate(String.format(
							"insert into virus_disease(virus,disease) values((select id from virus where name='%s'),(select id from disease where name ='%s'));",
							v.virus.trim(), s.trim()));
				}
				for (String s : v.getHost().split(",")) {
					statement.executeUpdate(String.format(
							"insert into virus_host(virus,host) values((select id from virus where name='%s'),(select id from host where name ='%s'));",
							v.virus.trim(), s.trim()));
				}
				for (String s : v.getTransmission().split(",")) {
					statement.executeUpdate(String.format(
							"insert into virus_transmission(virus,transmission) values((select id from virus where name='%s'),(select id from transmission where name ='%s'));",
							v.virus.trim(), s.trim()));
				}
				/*
				 * statement.executeUpdate(String.
				 * format("insert into virus_host(virus,host) values(select id from virus where name=''%s,'select id from host where name ='%s');"
				 * , v.virus,v.host));
				 */
			}
			/*
			 * statement.executeUpdate("delete from virus_host");
			 * System.out.println("deleteing");
			 * statement.executeUpdate("delete from virus_disease;");
			 * System.out.println("deleteing");
			 * statement.executeUpdate("delete from virus_transmission;");
			 */

			/*
			 * statement.executeUpdate("create table person (id integer, name string)");
			 * statement.executeUpdate("insert into person values(1, 'leo')");
			 * statement.executeUpdate("insert into person values(2, 'yui')"); ResultSet rs
			 * = statement.executeQuery("select * from person"); while(rs.next()) { // read
			 * the result set System.out.println("name = " + rs.getString("name"));
			 * System.out.println("id = " + rs.getInt("id")); }
			 * 
			 * /
			 **/
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}
	}
}
