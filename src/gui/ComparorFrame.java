package gui;

import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

import algorithms.Comparison;
import algorithms.Comparor;
import algorithms.Match;

public class ComparorFrame extends JFrame {
	
	private int compared=0;
	
	private JProgressBar progressBar;
	public static void main(String args[]) throws InterruptedException, IOException, SQLException {
		new ComparorFrame().run();;
	}
	JPanel panel=new JPanel();
	public ComparorFrame() throws FileNotFoundException, InterruptedException {
		super();
		this.setTitle("Virus Comparor");
		
		ScrollPane pane=new ScrollPane();
		this.add(pane);
		pane.add(panel);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		
		File dir=new File("Viruses/");
		File[] list = dir.listFiles();
		
		progressBar=new JProgressBar(0,list.length*(list.length-1)/2);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		panel.add(progressBar);
		
		this.pack();
		this.setSize(panel.getWidth()+100,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	
	
	
	public void run() throws InterruptedException, IOException, SQLException {
		
		String[] genes=Comparison.getFileInDir(new File("Viruses/"));
		
		ConcurrentLinkedQueue<List<Match>> results=new ConcurrentLinkedQueue<List<Match>>();
		
		ExecutorService pool=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		progressBar.setMaximum(genes.length*(genes.length-2)/4/2);
		
		
		long time=System.currentTimeMillis();
		for(int i=0;i<genes.length;i+=2) {
			for(int j=i+2;j<genes.length;j+=2) {
				
				final int CurrentI=i;
				final int CurrentJ=j;
				pool.execute(()->{
					VirusPanel vp=new VirusPanel(genes[CurrentI+1],genes[CurrentJ+1]);
					panel.add(vp);
					ComparorFrame.this.revalidate();;
					//ComparorFrame.this.repaint();
					
					List<Match> matches=Comparison.getSequencesTracked(genes[CurrentI],genes[CurrentJ],7,(Tracker)vp);
					if(matches.size()>0) {
						for(Match m:matches) {
							m.setV1(genes[CurrentI+1]);
							m.setV2(genes[CurrentJ+1]);
						}
						results.offer(matches);
					}
					vp.update(100);
					panel.remove(vp);
					synchronized(ComparorFrame.this) {
						compared++;
						progressBar.setValue(compared);
					}
					
					
				});
				
				
			}
		}
		pool.shutdown();
		pool.awaitTermination(Long.MAX_VALUE,TimeUnit.MINUTES);
		
		Connection connection = DriverManager.getConnection("jdbc:sqlite:VirusDb.sqlite");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.
      
        
        
        
		
		
		
		for(List<Match>list:results) {
			for(Match m :list) {
				//String virusName=
				
				
				/*statement.executeUpdate(String.format("insert into virus_disease(virus,disease) values((select id from virus where name='%s'),(select id from disease where name ='%s'));",
            			v.virus.trim(),s.trim()));
				*/
				
				
				
				
				System.out.println(m);
			}
			
		}
		
		long time2=System.currentTimeMillis();
		System.out.println("finished in "+(time2-time));
		
		
	}
	
	
	
	
	
	private class VirusPanel extends JPanel implements Tracker{
		private JProgressBar progressBar;

		public VirusPanel(String v1,String v2){
			setBorder(new TitledBorder(v1+" | "+v2));
			progressBar = new JProgressBar(0, 100);
			progressBar.setValue(0);
			progressBar.setStringPainted(true);
			
			
			
			
			this.add(progressBar);
			
		}
		public void update(int prog) {
			progressBar.setValue(prog);
			//this.repaint();
		}
	}
	
	
	
	
	
	
}
