package gui;

import java.awt.ScrollPane;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import database.Virus;
import database.VirusDB;
import algorithms.StringHasher;

public class ComparorFrame extends JFrame {

	private int compared = 0;

	private List<Virus> viruses;
	private VirusDB db;
	private JProgressBar progressBar;

	public static void main(String args[]) throws InterruptedException, IOException, SQLException {
		new ComparorFrame().run();
	}

	JPanel panel = new JPanel();

	public ComparorFrame() throws FileNotFoundException, InterruptedException, SQLException {
		super();
		this.setTitle("Virus Comparor");

		ScrollPane pane = new ScrollPane();
		this.add(pane);
		pane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		db = new VirusDB();
		db.clear();

		viruses = db.getViruses();

		progressBar = new JProgressBar(0, viruses.size() * (viruses.size() - 1) / 2);
		// progressBar=new JProgressBar(0,viruses.size());
		System.out.println(viruses.size());
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		panel.add(progressBar);

		this.pack();
		this.setSize(panel.getWidth() + 100, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void run() throws InterruptedException, IOException, SQLException {

		for (int min = 5; min <= 14; min++) {
			long time = System.currentTimeMillis();
			int threads = Runtime.getRuntime().availableProcessors();

			ExecutorService pool = Executors.newFixedThreadPool(threads);

			progressBar.setMaximum(viruses.size() * (viruses.size() - 1) / 2);

			StringHasher[] hashers = new StringHasher[threads];
			for (int i = 0; i < threads; i++) {
				hashers[i] = new StringHasher(min);
			}
			progressBar.setMaximum(viruses.size());
			for (int i = 0; i < viruses.size(); i++) {
				final int CurrentI = i;
				pool.execute(() -> {
					int thread = Integer.parseInt(Thread.currentThread().getName()
							.substring(Thread.currentThread().getName().lastIndexOf('-') + 1));
					VirusPanel vp = new VirusPanel(viruses.get(CurrentI).toString(), viruses.get(CurrentI).toString());
					SwingUtilities.invokeLater(() -> {
						panel.add(vp);
						ComparorFrame.this.revalidate();
						;
					});

					for (String s : viruses.get(CurrentI).getGenome()) {
						hashers[thread - 1].Ingest(s, viruses.get(CurrentI).toString());
					}

					SwingUtilities.invokeLater(() -> {
						panel.remove(vp);
						compared++;
						progressBar.setValue(compared);
					});
					// vp.update(100);
				});
			}

			pool.shutdown();
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
			for (int i = 1; i < threads; i++) {
				hashers[0].join(hashers[i]);
			}
			// hashers[0].print();
			long time2 = System.currentTimeMillis();
			System.out.println("finished seq in " + (time2 - time));

			hashers[0].add(db);
			long time3 = System.currentTimeMillis();
			System.out.println("finished db in " + (time3 - time2));
		}
	}

	private class VirusPanel extends JPanel implements Tracker {
		private JProgressBar progressBar;

		public VirusPanel(String v1, String v2) {
			setBorder(new TitledBorder(v1 + " | " + v2));
			progressBar = new JProgressBar(0, 100);
			progressBar.setValue(0);
			progressBar.setStringPainted(true);

			this.add(progressBar);
		}

		public void update(int prog) {
			progressBar.setValue(prog);
			// this.repaint();
		}
	}
}
