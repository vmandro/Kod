package LISTTestScoring.solution;
public class Kruh extends DvaD implements DvaDInterface {
	public Bod2D stred;
	public double polomer;
	
	public Kruh(Bod2D stred, double polomer) {
		super();
		this.stred = stred;
		this.polomer = polomer;
	}
	/**
	 * defunjte metodu, co pocita obsah kruhu
	 */
	@Override
	public double obsah() {
		return Math.PI*polomer*polomer;
	}
	/**
	 * defunjte metodu, co pocita obvod kruhu
	 */
	@Override
	public double obvod() {
		return 2*Math.PI*polomer;
	}
	/**
	 * defunjte metodu, co zisti, ci bod b je v kruhu/na kruznici
	 */	
	@Override
	public boolean jeV(Bod2D b) {
		return Math.sqrt(
				(stred.getX()-b.getX())*(stred.getX()-b.getX()) +
				(stred.getY()-b.getY())*(stred.getY()-b.getY())) <= polomer;
	}
	/**
	 * definujte metodu, co posunie kruh o vektor b
	 */
	@Override
	public void posun(Bod2D b) {
		stred.setX(stred.getX() + b.getX());
		stred.setY(stred.getY() + b.getY());
	}
}
