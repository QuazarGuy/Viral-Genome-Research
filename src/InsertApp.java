import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class InsertApp {

	/**
	 * Connect to the test.db database
	 *
	 * @return the Connection object
	 */
	private Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite:sqlite/db/alignment.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	/**
	 * Insert a new row into the viruses table
	 *
	 * @param name
	 * @param capacity
	 */
	public void insertVirus(String name) {
		String sql = "INSERT INTO virus(name) VALUES(?)";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Insert a new sequence virus pair into the matchedSequences table
	 *
	 * @param name
	 * @param capacity
	 */
	public void insertSequence(String sequence, Integer ID) {
		String sql = "INSERT INTO matchedSequences(Sequence,VirusID) VALUES(?,?)";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, sequence);
			pstmt.setInt(2, ID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @param args the command line arguments
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		InsertApp app = new InsertApp();
		Map<String, Integer> virusToID = new HashMap<String, Integer>();
		Set<String> sequenceToID = new HashSet<String>();
		
		// Insert virus names
		File folder = new File("Viruses");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				int nameLength = (int) listOfFiles[i].getName().length() - 3;
				String virus = listOfFiles[i].getName().substring(0, nameLength);
//				app.insertVirus(virus);
				virusToID.put(virus, i+1);
//				System.out.println(virus);
			}
		}
		
		// Load Separated Output
		File seperatedOutput = new File("outputSeperated.txt");
		Scanner s = new Scanner(seperatedOutput);
		int j = 0;
		while (s.hasNextLine()) {
			String line = s.nextLine();
			line = line.substring(1, line.length()-1);
//			System.out.println(line);
			String[] tokens = line.split(", ");
//			System.out.println(tokens[0]);
//			System.out.println(tokens[1]);
			int virus1 = virusToID.get(tokens[0].substring(0, tokens[0].length()-3));
			int virus2 = virusToID.get(tokens[1].substring(0, tokens[1].length()-3));
			for (int i = 2; i < tokens.length; i++) {
				j++;
				if (j % 50 == 0) {
					System.out.println(j);
				}
				sequenceToID.add(virus1 + ", " + tokens[i]);
				sequenceToID.add(virus2 + ", " + tokens[i]);
			}
		}
		s.close();
		
		// Insert sequence matches
		j = 0;
		for (String entry : sequenceToID) {
			if (j % 50 == 0) {
				System.out.println(j);
			}
			j++;
			String[] fields = entry.split(", ");
			String sequence = fields[1];
			Integer ID = Integer.parseInt(fields[0]);
//			System.out.println(ID + ": " + sequence);
			app.insertSequence(sequence, ID);
		}
	}
}

















