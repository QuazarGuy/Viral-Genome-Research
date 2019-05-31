package algorithms;

import database.Virus;

public class Match {
	Virus v1;
	Virus v2;
	int v1Index;
	int v2Index;
	int g1,g2;
	String Sequence;
	public Match(Virus v1, Virus v2,int g1, int g2,int v1Index, int v2Index, String sequence) {
		super();
		this.g1=g1;
		this.g2=g2;
		this.v1 = v1;
		this.v2 = v2;
		this.v1Index = v1Index;
		this.v2Index = v2Index;
		Sequence = sequence;
	}
	public Match(int v1Index, int v2Index, String sequence) {
		super();
		this.v1 = null;
		this.v2 = null;
		this.v1Index = v1Index;
		this.v2Index = v2Index;
		Sequence = sequence;
	}
	@Override
	public String toString() {
		return "Match [v1=" + v1 + ", v2=" + v2 + ", v1Index=" + v1Index + ", v2Index=" + v2Index + ", Sequence="
				+ Sequence + "]";
	}
	public Virus getV1() {
		return v1;
	}
	public void setV1(Virus v1) {
		this.v1 = v1;
	}
	public Virus getV2() {
		return v2;
	}
	public void setV2(Virus v2) {
		this.v2 = v2;
	}
	public int getV1Index() {
		return v1Index;
	}
	public void setV1Index(int v1Index) {
		this.v1Index = v1Index;
	}
	public int getV2Index() {
		return v2Index;
	}
	public void setV2Index(int v2Index) {
		this.v2Index = v2Index;
	}
	public String getSequence() {
		return Sequence;
	}
	public void setSequence(String sequence) {
		Sequence = sequence;
	}
	

}
