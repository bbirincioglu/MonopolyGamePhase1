import domain.Die;


public class RDieView extends DieView {
	private static final String[] FILE_NAMES = new String[] {"one.png", "two.png", "three.png", "four.png", "five.png", "six.png"};
	
	public RDieView(Die die) {
		super(FILE_NAMES, die);
	}
}
