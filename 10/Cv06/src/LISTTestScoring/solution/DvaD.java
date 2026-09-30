package LISTTestScoring.solution;
abstract class DvaD implements DvaDInterface {
	// definujte konstantu obe, ktora obsahuje lubovolny obdlznik a kruh v lubovolnom poradi
	public static DvaD[] obe = new DvaD[]{
				new Obdlznik(new Bod2D(0,0),4,5),	
				new Kruh(new Bod2D(0,0),10)
	};
}
