package algorithms;


public class Find{
	public int location;
	public String original;
	public String name;
	int length;
	public Find(int l, String o, String name, int length) {
		this.location=l;
		this.original=o;
		this.name=name;
		this.length=length;
	}
	public String toString() {
		return name+":"+location+":"+getSequence();
	}
	public String getSequence() {
		return original.substring(location,location+length);
	}
}