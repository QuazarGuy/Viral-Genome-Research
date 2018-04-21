import java.util.List;

public class Virus {
	
	
	String virus; String genus; String host; String transmission; String diseases; List<String> genome;
	String proteome;
	public Virus(String virus, String genus, String host, String transmission, String diseases, List<String> genome,
			String proteome) {
		super();
		this.virus = virus;
		this.genus = genus;
		this.host = host;
		this.transmission = transmission;
		this.diseases = diseases;
		this.genome = genome;
		this.proteome = proteome;
	}

	public String toString() {
		return virus;
	}

}
