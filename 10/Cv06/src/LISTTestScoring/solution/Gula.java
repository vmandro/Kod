package LISTTestScoring.solution;
public class Gula extends TriD {
	public Bod3D stred;
	public double polomer;
	
	public Gula(Bod3D stred, double polomer) {
		super();
		this.stred = stred;
		this.polomer = polomer;
	}
	/**
	 * definujte metodu, co pocita objem gule
	 */
	@Override
	public double objem() {	
		return 4*Math.PI*polomer*polomer*polomer/3;
	}
	/**
	 * definujte metodu, co pocita povrch gule
	 */
	@Override
	public double povrch() {
		return 4*Math.PI*polomer*polomer;
	}
	/**
	 * definujte metodu, co zisti, ci bo b je v/na guli
	 */
	@Override
	public boolean jeV(Bod3D  b) {
		return Math.sqrt(
				(stred.getX()-b.getX())*(stred.getX()-b.getX()) +
				(stred.getY()-b.getY())*(stred.getY()-b.getY())+
				(stred.getZ()-b.getZ())*(stred.getZ()-b.getZ())
				) <= polomer;
	}
	/**
	 * definujte metodu, co posunie gulu o vektor b
	 */
	@Override
	public void posun(Bod3D b) {
		stred.setX(stred.getX() + b.getX());
		stred.setY(stred.getY() + b.getY());
		stred.setZ(stred.getZ() + b.getZ());
	}
}
