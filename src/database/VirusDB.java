package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sqlite.SQLiteConnection;

import algorithms.Find;
import algorithms.Match;

public class VirusDB {
	Connection connection;
	Statement statement;
	public VirusDB() throws SQLException {
		connection = DriverManager.getConnection("jdbc:sqlite:VirusDb.sqlite");
        statement = connection.createStatement();
        statement.setQueryTimeout(30);
	}
	public List<Virus> getViruses() {
		ArrayList<Virus> viruses=new ArrayList<Virus>();
		HashMap<Integer,Virus> map=new HashMap<Integer,Virus>();
		try {
			ResultSet res = statement.executeQuery("select * from virus join genome on virus.id=genome.virus");
			
			while(res.next()) {
				int id=res.getInt(1);
				Virus v=map.get(id);
				
				if(v==null) {
					v=new Virus(res.getString(2),res.getString(3),res.getString(4));
					map.put(id, v);
				}
				v.addGenome(parse(res.getString(7)));
			}
			viruses.addAll(map.values());
			System.out.println(viruses);
		} catch (SQLException e) {
			System.err.println("misc db error");
			e.printStackTrace();
		}
		
		return viruses;
	}
	
	private String parse(String string) {
		Pattern sequencep=Pattern.compile("translation=\\\"(.*?)\\\"",Pattern.DOTALL);
		String buffer= string;
		Matcher seqs=sequencep.matcher(buffer);
		StringBuilder full=new StringBuilder();
		while(seqs.find()) {
			full.append(seqs.group(1).trim());
			
		}
		return (full.toString().replaceAll("[\n 1-9]", "").toUpperCase());
	}
	
	public void clear() {
		try {
			statement.execute("delete from virus_sequence;");
			statement.execute("delete from sequence;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addSequence(ArrayList<String> sequences) {
		try {
		connection.setAutoCommit(false);
		for(String s:sequences) {
			statement.execute("insert into sequence (sequence) values(\""+s+"\")");
		}
		
		
		connection.commit();
		connection.setAutoCommit(true);
		}catch(Exception e) {
			System.err.println(e);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void startTransaction() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void endTransaction() {
		try {
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addVirusSequence(ArrayList<Find> locations) {
		SQLiteConnection db = (SQLiteConnection)connection;
		
		
		try {

			
			//db.setAutoCommit(false);
			ResultSet res;//=statement.executeQuery("select * from sequence where sequence=\""+locations.get(0).getSequence()+"\"");
			for(int i=0;i<locations.size();i++) {
				String st="insert into virus_sequence (virus,sequence,offset) values(("+"select id from virus where name=\""+locations.get(i).name+"\""+"),("+"select id from sequence where sequence=\""+locations.get(0).getSequence()+"\""+"),"+locations.get(i).location+")";
				//System.out.println(st);
				statement.execute(st);
			}
			//db.commit();
			//db.setAutoCommit(true);
		}catch(Exception e) {
			System.err.println(e);
			e.printStackTrace();
			System.err.println(locations);
			System.exit(1);
		}
	}
	
	
	public void insertMatch(Match m) {
		//System.out.println(m);
		String check="select * from sequence where sequence=\""+m.getSequence()+"\"";
		String v1 = "select id from virus where name=\""+m.getV1().getVirus()+"\"";
		String v2="select id from virus where name=\""+m.getV2().getVirus()+"\"";
		try {
			ResultSet res = statement.executeQuery(check);
			if(!res.next()) {
				statement.execute("insert into sequence (sequence) values(\""+m.getSequence()+"\")");
				res = statement.executeQuery(check);
				res.next();
			}
			int id=res.getInt(1);
			try {
			res = statement.executeQuery(v1);
			res.next();
			int v1id=res.getInt(1);
			statement.execute("insert into virus_sequence (virus,sequence) values("+v1id+","+id+")");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());;
			}
			
			
			try {
			res = statement.executeQuery(v2);
			res.next();
			int v2id=res.getInt(1);
			statement.execute("insert into virus_sequence (virus,sequence) values("+v2id+","+id+")");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
