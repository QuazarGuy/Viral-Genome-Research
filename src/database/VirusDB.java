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
				v.addGenome(res.getString(7));
			}
			viruses.addAll(map.values());
			System.out.println(viruses);
		} catch (SQLException e) {
			System.err.println("misc db error");
			e.printStackTrace();
		}
		
		return viruses;
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
