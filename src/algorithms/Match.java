package algorithms;

public class Match {
	String v1;
	String v2;
	int v1Index;
	int v2Index;
	String Sequence;
	public Match(int v1Index, int v2Index, String sequence) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.v1Index = v1Index;
		this.v2Index = v2Index;
		Sequence = sequence;
	}
	@Override
	public String toString() {
		return "Match [v1=" + v1 + ", v2=" + v2 + ", v1Index=" + v1Index + ", v2Index=" + v2Index + ", Sequence="
				+ Sequence + "]";
	}
	public String getV1() {
		return v1;
	}
	public void setV1(String v1) {
		this.v1 = v1;
	}
	public String getV2() {
		return v2;
	}
	public void setV2(String v2) {
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
