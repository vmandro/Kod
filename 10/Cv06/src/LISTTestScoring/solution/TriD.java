package LISTTestScoring.solution;
abstract class TriD {
	public abstract double objem();
	public abstract double povrch();
	public abstract boolean jeV(Bod3D b);
	public abstract void posun(Bod3D b);
	
	
	public static TriD[] obe = new TriD[] {
			new Kvader(new Bod3D(0,0,0),10,20,30),	
			new Gula(new Bod3D(0,0,0),30)	
	};
}
