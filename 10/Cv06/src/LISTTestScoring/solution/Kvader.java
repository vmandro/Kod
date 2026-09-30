package LISTTestScoring.solution;
public class Kvader extends TriD {
	public Bod3D lavyDolny;
	public double dx, dy, dz;

	public Kvader(Bod3D lavyDolny, double dx, double dy, double dz) {
		super();
		this.lavyDolny = lavyDolny;
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
	}
	/**
	 * definujte objem kvadra 
	 */
	@Override
	public double objem() {
		return dx*dy*dz;
	}
	/**
	 * definujte povrch kvadra 
	 */
	@Override
	public double povrch() {
		return 2*(dx*dy + dy*dz + dx*dz);
	}
	/**
	 * definujte test, ci bod b je v kvadri 
	 */
	@Override
	public boolean jeV(Bod3D b) {
		return 
				lavyDolny.getX() <= b.getX() && b.getX() <= lavyDolny.getX() + dx && 
				lavyDolny.getY() <= b.getY() && b.getY() <= lavyDolny.getY() + dy && 
				lavyDolny.getZ() <= b.getZ() && b.getZ() <= lavyDolny.getZ() + dz; 
	}
	/**
	 * definujte posunutie kvadra o vektor b 
	 */
	@Override
	public void posun(Bod3D b) {
		lavyDolny.setX(lavyDolny.getX() + b.getX());
		lavyDolny.setY(lavyDolny.getY() + b.getY());
		lavyDolny.setZ(lavyDolny.getZ() + b.getZ());
	}
}
