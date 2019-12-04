package database;

import java.util.ArrayList;
import java.util.List;

public class Virus {

	String virus;
	String genus;
	String host;
	String transmission;
	String diseases;
	List<String> genome;
	String family;
	String proteome;

	public Virus(String virus, String genus, String family, String host, String transmission, String diseases,
			List<String> genome, String proteome) {
		super();
		this.virus = virus;
		this.genus = genus;
		this.host = host;
		this.transmission = transmission;
		this.diseases = diseases;
		this.genome = genome;
		this.family = family;
		this.proteome = proteome;
	}

	public Virus(String virus, String genus, String family) {
		this(virus, genus, family, "", "", "", new ArrayList<String>(), "");
	}

	public String getVirus() {
		return virus;
	}

	public void setVirus(String virus) {
		this.virus = virus;
	}

	public String getGenus() {
		return genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getDiseases() {
		return diseases;
	}

	public void setDiseases(String diseases) {
		this.diseases = diseases;
	}

	public List<String> getGenome() {
		return genome;
	}

	public void setGenome(List<String> genome) {
		this.genome = genome;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getProteome() {
		return proteome;
	}

	public void setProteome(String proteome) {
		this.proteome = proteome;
	}

	public String toString() {
		return virus;
	}

	public void addGenome(String string) {
		genome.add(string);

	}

}
