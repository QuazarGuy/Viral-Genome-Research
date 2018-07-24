package algorithms;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.Virus;
import gui.Tracker;

public class Comparison {

	public static final int MAX_DISTANCE=0;

		public static void main(String[] args) throws InterruptedException, IOException {
		
		
		String[] genes=getFileInDir(new File("Viruses/"));
		
		ConcurrentLinkedQueue<List<String>> results=new ConcurrentLinkedQueue<List<String>>();
		
		ExecutorService pool=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		long time=System.currentTimeMillis();
		for(int i=0;i<genes.length;i+=2) {
			for(int j=i+2;j<genes.length;j+=2) {
				
				final int CurrentI=i;
				final int CurrentJ=j;
				pool.execute(()->{
					List<String> matches=getSequences(genes[CurrentI],genes[CurrentJ]);
					if(matches.size()>0) {
						matches.add(0, genes[CurrentI+1]);
						matches.add(0, genes[CurrentJ+1]);
						results.offer(matches);
						System.out.println((CurrentI>>1)+","+(CurrentJ>>1));
					}

				});
				
				
			}
		}
		pool.shutdown();
		pool.awaitTermination(Long.MAX_VALUE,TimeUnit.MINUTES);
		
		long time2=System.currentTimeMillis();
		System.out.println("finished in "+(time2-time));
		System.out.flush();
		
		
		//File output=new File("outputSeperated.txt");
		//PrintWriter pw=new PrintWriter(output);
		PrintStream pw= System.out;
		while(!results.isEmpty()) {
			System.out.println(results.poll());
		}
	}

	public static String[] getFileInDir(File dir) throws IOException{
		Pattern sequencep=Pattern.compile("translation=\\\"(.*?)\\\"",Pattern.DOTALL);
		ArrayList<String>strings=new ArrayList<String>(); 
		
		long start=System.currentTimeMillis();
		
		int num=0;
		for(File f:dir.listFiles()) {
			//if(num++>30)
				//break;
			
			
			byte[] chars = Files.readAllBytes(f.toPath());

			
			String buffer= new String(chars);
			Matcher seqs=sequencep.matcher(buffer);
			StringBuilder full=new StringBuilder();
			while(seqs.find()) {
				full.append(seqs.group(1).trim());
				//full+=seqs;
			}
			strings.add(full.toString().replaceAll("[\n 1-9]", "").toUpperCase());
			System.out.println(f.getName());
			strings.add(f.getName());

		}
		System.out.println(System.currentTimeMillis()-start);
		
		
		
		return strings.toArray(new String[0]);
		
	}
	
	
	
	public static List<String> getSequences(String a, String b){
		return getSequences(a,b,7);
	}
	
	public static List<String> getSequences(String g1, String g2,int max){
		ArrayList<String> found=new ArrayList<String>();
		
		int maxi=g1.length()-max;
		int maxj=g2.length()-max;
		
		for(int i=0;i<maxi;i++) {
			for(int j=i;j<maxj;j++) {
				
				if(g1.charAt(i)!=g2.charAt(j)) {
					continue;
				}
				int dist=MAX_DISTANCE;
				int count=relation(g1,g2,i,j,dist);
				if(count>=max) {
					found.add(g1.substring(i, i+count));
					
					
				}
			}
		}
		for(int i=found.size()-1;i>0;i--) {
			if(found.get(i-1).substring(1).equals(found.get(i))) {
				found.remove(i);
				
			}
		}
		
		return found;
		
		
		
	}
	
	
	public static List<Match> getSequencesTracked(String g1, String g2,int max,Tracker t){
		ArrayList<String> found=new ArrayList<String>();
		ArrayList<Match> fullData=new ArrayList<Match>();
		int maxi=g1.length()-max;
		int maxj=g2.length()-max;
		for(int i=0;i<maxi;i++) {
			
			if(i%10==0)
				t.update(100*i/maxi);
			for(int j=i;j<maxj;j++) {
				
				if(g1.charAt(i)!=g2.charAt(j)) {
					continue;
				}
				int dist=MAX_DISTANCE;
				int count=relation(g1,g2,i,j,dist);
				if(count>=max) {
					found.add(g1.substring(i, i+count));
					fullData.add(new Match(i, j, g1.substring(i, i+count)));
				}
			}
		}
		for(int i=found.size()-1;i>0;i--) {
			if(found.get(i-1).substring(1).equals(found.get(i))) {
				found.remove(i);
				fullData.remove(i);
			}
		}
		return fullData;
		
	}
	
	
	
	public static int relation(String g1, String g2, int i,int j,int maxDistance) {
		int count=0;
		
		while(i+count<g1.length()&&j+count<g2.length()&&g1.charAt(i+count)==g2.charAt(j+count)) {
			count++;
		}
		if(maxDistance<=0||i+count>=g1.length()||j+count>=g2.length()) {
			return count;
		}
		i+=count;
		j+=count;
		int l=relation(g1,g2,i+1,j,maxDistance-1);
		int r=relation(g1,g2,i,j+1,maxDistance-1);
		int c=relation(g1,g2,i+1,j+1,maxDistance-1);
		return count+Math.max(Math.max(l,r),c);
		
	}

	
	public static List<Match> getSequencesTracked(Virus virus, Virus virus2, int max, Tracker t) {
		ArrayList<String> found=new ArrayList<String>();
		ArrayList<Match> fullData=new ArrayList<Match>();
		
		//System.out.println(virus+","+virus2);
		
		for(int v1=0;v1<virus.getGenome().size();v1++) {
			for(int v2=0;v2<virus2.getGenome().size();v2++) {
				
				String g1=parse(virus.getGenome().get(v1));
				String g2=parse(virus2.getGenome().get(v2));
				int maxi=g1.length()-max;
				int maxj=g2.length()-max;
				for(int i=0;i<maxi;i++) {
					
					if(i%10==0)
						t.update(100*i/maxi);
					for(int j=i;j<maxj;j++) {
						
						if(g1.charAt(i)!=g2.charAt(j)) {
							continue;
						}
						int dist=MAX_DISTANCE;
						int count=relation(g1,g2,i,j,dist);
						if(count>=max) {
							found.add(g1.substring(i, i+count));
							fullData.add(new Match(virus,virus2,v1,v2,i, j, g1.substring(i, i+count)));
						}
					}
				}
			}
		}
		
		for(int i=found.size()-1;i>0;i--) {
			if(found.get(i-1).substring(1).equals(found.get(i))) {
				found.remove(i);
				fullData.remove(i);
			}
		}
		//System.out.println(fullData.size());
		return fullData;
		
	}

	private static String parse(String string) {
		Pattern sequencep=Pattern.compile("translation=\\\"(.*?)\\\"",Pattern.DOTALL);
		ArrayList<String>strings=new ArrayList<String>(); 
		
		long start=System.currentTimeMillis();
		
		int num=0;


			
			String buffer= string;
			Matcher seqs=sequencep.matcher(buffer);
			StringBuilder full=new StringBuilder();
			while(seqs.find()) {
				full.append(seqs.group(1).trim());
				
			}
			return (full.toString().replaceAll("[\n 1-9]", "").toUpperCase());
	}
	
/*
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Match>();
	}
*/
}
