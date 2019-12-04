package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import database.VirusDB;

public class StringHasher implements Iterable<ArrayList<Find>> {

	public static final int CHARS;
	private static int[] charMap = new int[127];

	static {
		char[] chars = { 'A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y',
				'V' };
		for (int i = 0; i < chars.length; i++) {
			charMap[chars[i]] = i;
		}
		CHARS = chars.length;
	}

	private int length;
	private HashMap<Long, ArrayList<Find>> map = new HashMap<Long, ArrayList<Find>>();

	public StringHasher(int i) {
		this.length = i;
	}

	public void Ingest(String s, String name) {
		// if it's too short don't proccess it
		if (s.length() < length) {
			// System.err.println(name + "is too short");
			return;
		}

		// key made by rolling hash
		long key = 0;

		// calculates the multiplier for the first item in hash for rolling hash
		long size = 1;

		// generate initial key
		for (int i = 0; i < length; i++) {
			size *= CHARS;
			key = CHARS * key + (charMap[s.charAt(i)]);
		}

		// first element effects value by s[i]*CHARS^(length-1)
		size /= CHARS;

		// add sequence to map, if not found, make list for it
		ArrayList<Find> item = map.get(key);
		if (item == null) {
			item = new ArrayList<Find>();
			map.put(key, item);
		}
		item.add(new Find(0, s, name, length));

		for (int i = 1; i <= s.length() - length; i++) {

			/*
			 * how we actually calculate the hash given a hash for a:b we subtract
			 * a*(numChars^(length-1)) we multiply by numChars and we add b+1 this
			 * effectively gives the hash of (a+1):(b+1) in constant time
			 *
			 * think of it as shifting a digit off a number in base numChars
			 */
			key = (key - (charMap[s.charAt(i - 1)]) * size) * CHARS + (charMap[s.charAt(i + length - 1)]);

			// add sequence to map, if not found, make list for it
			item = map.get(key);
			if (item == null) {
				item = new ArrayList<Find>();
				map.put(key, item);
			}
			item.add(new Find(i, s, name, length));
		}
	}

	/**
	 * adds all sequences from another map to this one
	 * 
	 * @param other map
	 */
	public void join(StringHasher o) {
		for (Long key : o.map.keySet().toArray(new Long[0])) {
			ArrayList<Find> list = map.get(key);
			if (list == null) {
				this.map.put(key, o.map.get(key));
			} else {
				list.addAll(o.map.get(key));
			}
		}
	}

	public int max = 0;

	/**
	 * dumps all known sequences into the database
	 * 
	 * @param db
	 */
	public void add(VirusDB db) {
		int max = map.keySet().size();
		System.out.println("max" + max);
		ArrayList<String> sequences = new ArrayList<String>();
		for (Long key : map.keySet()) {
			ArrayList<Find> list = map.get(key);

			if (list.size() > 1) {
				this.max++;
				sequences.add(list.get(0).getSequence());
			}
		}
		// this.max=sequences.size();
		System.out.println(sequences.size());
		db.addSequence(sequences);
		db.startTransaction();
		int cur = 0;
		for (Long key : map.keySet()) {
			ArrayList<Find> list = map.get(key);
			cur++;
			if (cur % 100000 == 0) {
				// db.endTransaction();
				System.out.println(cur + "/" + max);
				// db.startTransaction();
			}
			if (list.size() > 1) {

				db.addVirusSequence(list);
			}
		}
		db.endTransaction();
	}

	@Override
	public Iterator<ArrayList<algorithms.Find>> iterator() {
		return new itr();
	}

	private class itr implements Iterator<ArrayList<Find>> {

		private Iterator<Long> internal;

		public itr() {
			this.internal = StringHasher.this.map.keySet().iterator();
		}

		@Override
		public boolean hasNext() {
			return internal.hasNext();
		}

		@Override
		public ArrayList<Find> next() {
			return StringHasher.this.map.get(internal.next());
		}
	}
}
