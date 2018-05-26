import java.util.HashMap;

public class NGramLibrary {
	// Name - 3 letter - 1 letter key: http://www.cryst.bbk.ac.uk/education/AminoAcid/the_twenty.html
	public enum AminoAcid {
		A, R, N, D, C, Q, E, G, H, I, L, K, M, F, P, S, T, W, Y, V, B, Z, stop
	}
	
	static {
		HashMap<String, AminoAcid> BPtoAA = new HashMap<String, AminoAcid>();
		BPtoAA.put("gcu", AminoAcid.A); BPtoAA.put("gca", AminoAcid.A); BPtoAA.put("gca", AminoAcid.A); BPtoAA.put("gcg", AminoAcid.A);
		BPtoAA.put("cgu", AminoAcid.R); BPtoAA.put("cgc", AminoAcid.R); BPtoAA.put("cga", AminoAcid.R); BPtoAA.put("cgg", AminoAcid.R); BPtoAA.put("aga", AminoAcid.R); BPtoAA.put("agg", AminoAcid.R);
		BPtoAA.put("aau", AminoAcid.N); BPtoAA.put("aac", AminoAcid.N);
		BPtoAA.put("gau", AminoAcid.D); BPtoAA.put("gaa", AminoAcid.D);
		BPtoAA.put("ugu", AminoAcid.C); BPtoAA.put("ugc", AminoAcid.C);
		BPtoAA.put("caa", AminoAcid.Q); BPtoAA.put("cag", AminoAcid.Q);
		BPtoAA.put("gaa", AminoAcid.E); BPtoAA.put("gag", AminoAcid.E);
		BPtoAA.put("ggu", AminoAcid.G); BPtoAA.put("gga", AminoAcid.G); BPtoAA.put("gga", AminoAcid.G); BPtoAA.put("ggg", AminoAcid.G);
		BPtoAA.put("cau", AminoAcid.H); BPtoAA.put("cac", AminoAcid.H);
		BPtoAA.put("auu", AminoAcid.I); BPtoAA.put("auc", AminoAcid.I); BPtoAA.put("aua", AminoAcid.I);
		BPtoAA.put("uua", AminoAcid.L); BPtoAA.put("uug", AminoAcid.L); BPtoAA.put("cuu", AminoAcid.L); BPtoAA.put("cuc", AminoAcid.L); BPtoAA.put("cua", AminoAcid.L); BPtoAA.put("cug", AminoAcid.L);
		BPtoAA.put("aaa", AminoAcid.K); BPtoAA.put("aag", AminoAcid.K);
		BPtoAA.put("aug", AminoAcid.M);
		BPtoAA.put("uuu", AminoAcid.F); BPtoAA.put("uuc", AminoAcid.F);
		BPtoAA.put("ccu", AminoAcid.P); BPtoAA.put("ccc", AminoAcid.P); BPtoAA.put("cca", AminoAcid.P); BPtoAA.put("ccg", AminoAcid.P);
		BPtoAA.put("ucu", AminoAcid.S); BPtoAA.put("ucc", AminoAcid.S); BPtoAA.put("uca", AminoAcid.S); BPtoAA.put("ucg", AminoAcid.S); BPtoAA.put("agu", AminoAcid.S); BPtoAA.put("agc", AminoAcid.S);
		BPtoAA.put("acu", AminoAcid.T); BPtoAA.put("acc", AminoAcid.T); BPtoAA.put("aca", AminoAcid.T); BPtoAA.put("acg", AminoAcid.T);
		BPtoAA.put("ugg", AminoAcid.W);
		BPtoAA.put("uau", AminoAcid.Y); BPtoAA.put("uac", AminoAcid.Y);
		BPtoAA.put("guu", AminoAcid.V); BPtoAA.put("gua", AminoAcid.V); BPtoAA.put("gua", AminoAcid.V); BPtoAA.put("gug", AminoAcid.V);
		BPtoAA.put("uaa", AminoAcid.stop); BPtoAA.put("uag", AminoAcid.stop); BPtoAA.put("uga", AminoAcid.stop);
	}
	
	public enum AcidGroup {
		Na, Ar, Pu, Pc, Nc
	}
	
	static {
		HashMap<AminoAcid, AcidGroup> AAtoAG = new HashMap<AminoAcid, AcidGroup>();
		AAtoAG.put(AminoAcid.A, AcidGroup.Na);
		AAtoAG.put(AminoAcid.R, AcidGroup.Pc);
		AAtoAG.put(AminoAcid.N, AcidGroup.Pu);
		AAtoAG.put(AminoAcid.D, AcidGroup.Nc);
		AAtoAG.put(AminoAcid.C, AcidGroup.Pu);
		AAtoAG.put(AminoAcid.Q, AcidGroup.Pu);
		AAtoAG.put(AminoAcid.E, AcidGroup.Nc);
		AAtoAG.put(AminoAcid.G, AcidGroup.Na);
		AAtoAG.put(AminoAcid.H, AcidGroup.Pc);
		AAtoAG.put(AminoAcid.I, AcidGroup.Na);
		AAtoAG.put(AminoAcid.L, AcidGroup.Na);
		AAtoAG.put(AminoAcid.K, AcidGroup.Pc);
		AAtoAG.put(AminoAcid.M, AcidGroup.Na);
		AAtoAG.put(AminoAcid.F, AcidGroup.Ar);
		AAtoAG.put(AminoAcid.P, AcidGroup.Na);
		AAtoAG.put(AminoAcid.S, AcidGroup.Pu);
		AAtoAG.put(AminoAcid.T, AcidGroup.Pu);
		AAtoAG.put(AminoAcid.W, AcidGroup.Ar);
		AAtoAG.put(AminoAcid.Y, AcidGroup.Ar);
		AAtoAG.put(AminoAcid.V, AcidGroup.Na);
	}
	
	static {
		HashMap<AminoAcid, Integer> HydropathyIndex = new HashMap<AminoAcid, Integer>();
		HydropathyIndex.put(AminoAcid.A, 18);
		HydropathyIndex.put(AminoAcid.R, -45);
		HydropathyIndex.put(AminoAcid.N, -35);
		HydropathyIndex.put(AminoAcid.D, -35);
		HydropathyIndex.put(AminoAcid.C, 25);
		HydropathyIndex.put(AminoAcid.Q, -35);
		HydropathyIndex.put(AminoAcid.E, -35);
		HydropathyIndex.put(AminoAcid.G, -4);
		HydropathyIndex.put(AminoAcid.H, -32);
		HydropathyIndex.put(AminoAcid.I, 45);
		HydropathyIndex.put(AminoAcid.L, 38);
		HydropathyIndex.put(AminoAcid.K, -39);
		HydropathyIndex.put(AminoAcid.M, 19);
		HydropathyIndex.put(AminoAcid.F, 28);
		HydropathyIndex.put(AminoAcid.P, -16);
		HydropathyIndex.put(AminoAcid.S, -8);
		HydropathyIndex.put(AminoAcid.T, -7);
		HydropathyIndex.put(AminoAcid.W, -9);
		HydropathyIndex.put(AminoAcid.Y, -13);
		HydropathyIndex.put(AminoAcid.V, 42);
	}
}
