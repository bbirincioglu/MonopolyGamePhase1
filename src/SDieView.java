import domain.Die;


public class SDieView extends DieView {
	private static final String[] FILE_NAMES = new String[] {"one.png", "two.png", "three.png", "bus.png", "bus.png", "mrMonopoly.png"};
	
	public SDieView(Die die) {
		super(FILE_NAMES, die);
	}
}
