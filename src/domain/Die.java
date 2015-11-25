package domain;
import java.util.ArrayList;
import java.util.Random;


public class Die {
	private ArrayList<DieObserver> dieObservers;
	private int[] randomValues;
	private int faceValue;
	
	public Die() {
		this.randomValues = new int[5];
		this.faceValue = 1;
		this.dieObservers = new ArrayList<DieObserver>();
	}
	
	public void roll() {
		Random random = new Random();
		
		for (int i = 0; i < getRandomValues().length; i++) {
			getRandomValues()[i] = 1 + random.nextInt(6);
		}
	}
	
	public void notifyDieObservers() {
		ArrayList<DieObserver> dieObservers = getDieObservers();
		
		for (int i = 0; i < dieObservers.size(); i++) {
			dieObservers.get(i).update(getFaceValue());
		}
	}
	
	public static void animate(Die die1, Die die2, Die speedDie) {
		int[] randomValues1 = die1.getRandomValues();
		int[] randomValues2 = die2.getRandomValues();
		int[] randomValues3 = speedDie.getRandomValues();
		
		for (int i = 0; i < die1.getRandomValues().length; i++) {
			int randomValue1 = randomValues1[i];
			int randomValue2 = randomValues2[i];
			int randomValue3 = randomValues3[i];
			die1.setFaceValue(randomValue1);
			die2.setFaceValue(randomValue2);
			speedDie.setFaceValue(randomValue3);
			
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void animate(Die die1, Die die2) {
		int[] randomValues1 = die1.getRandomValues();
		int[] randomValues2 = die2.getRandomValues();
		
		for (int i = 0; i < die1.getRandomValues().length; i++) {
			int randomValue1 = randomValues1[i];
			int randomValue2 = randomValues2[i];
			die1.setFaceValue(randomValue1);
			die2.setFaceValue(randomValue2);
			
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addDieObserver(DieObserver dieObserver) {
		getDieObservers().add(dieObserver);
	}
	
	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
		notifyDieObservers();
	}
	
	public int getFaceValue() {
		return faceValue;
	}
	
	public ArrayList<DieObserver> getDieObservers() {
		return dieObservers;
	}

	public void setDieObservers(ArrayList<DieObserver> dieObservers) {
		this.dieObservers = dieObservers;
	}

	public void setRandomValues(int[] randomValues) {
		this.randomValues = randomValues;
	}
	
	public int[] getRandomValues() {
		return randomValues;
	}
}
