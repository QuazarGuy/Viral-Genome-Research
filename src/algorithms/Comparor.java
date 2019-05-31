package algorithms;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Comparor {
public static void main(String args[]) throws FileNotFoundException {
	Scanner in=new Scanner(new File("outputSeperated.txt"));
	
	HashMap<String,Integer> counter=new HashMap<String,Integer>();
	while(in.hasNextLine()) {
		String line=in.nextLine();
		String[] items=line.substring(line.lastIndexOf(".gb,")+4, line.length()-1).split(",");
		
		for(String s:items) {
			if(counter.containsKey(s)) {
				counter.put(s, counter.get(s)+1);
			}else {
				counter.put(s, 1);
			}
		}
	}
	/*
	for(String s:counter.keySet()) {
		if(counter.get(s)>8)
		System.out.println(counter.get(s)+"\t"+s);
	}*/
	String[] keys=counter.keySet().toArray(new String[0]);
	int[] counts=new int[keys.length];
	for(int i=0;i<keys.length;i++) {
		counts[i]=counter.get(keys[i]);
		for(int j=0;j<keys.length;j++) {
			if(i==j)continue;
			if(keys[j].contains(keys[i])) {
				counts[i]++;
			}
		}
		counter.put(keys[i], counts[i]);
	}

	
	for(String s:counter.keySet()) {
		if(counter.get(s)>8)
		System.out.println(counter.get(s)+"\t"+s);
	}
	
}
}
