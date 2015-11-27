package domain;

public class Cup {
	private Die die1;
	private Die die2;
	private Die speedDie;
	
	public Cup() {
		setDie1(new Die());
		setDie2(new Die());
		setSpeedDie(new Die());
	}
	
	public void roll2Dice() {
		Die die1 = getDie1();
		Die die2 = getDie2();
		
		die1.roll();
		die2.roll();
		Die.animate(die1, die2);
	}
	
	public void roll3Dice() {
		Die die1 = getDie1();
		Die die2 = getDie2();
		Die speedDie = getSpeedDie();
		
		die1.roll();
		die2.roll();
		speedDie.roll();
		
		Die.animate(die1, die2, speedDie);
	}
	
	public int getDiceValuesTotal() {
		int diceValuesTotal;
		
		if (isMrMonopolyRolled() || isBusRolled()) {
			diceValuesTotal = getDie1().getFaceValue() + getDie2().getFaceValue();
		} else {
			diceValuesTotal = getDie1().getFaceValue() + getDie2().getFaceValue() + getSpeedDie().getFaceValue();
		}
		
		return diceValuesTotal;
	}
	
	public boolean isTripleRolled() {
		boolean isTripleRolled = false;
		
		if (!isMrMonopolyRolled() && !isBusRolled()) {
			if (getDie1().getFaceValue() == getDie2().getFaceValue() && getDie2().getFaceValue() == getSpeedDie().getFaceValue()) {
				isTripleRolled = true;
			}
		}
		
		return isTripleRolled;
	}
	
	public boolean isDoubleRolled() {
		boolean isDoubleRolled = false;
		
		int die1Value = getDie1().getFaceValue();
		int die2Value = getDie2().getFaceValue();
		int speedDieValue = getSpeedDie().getFaceValue();
		
		if (isMrMonopolyRolled() || isBusRolled()) {
			if (die1Value == die2Value) {
				isDoubleRolled = true;
			}
		} else {
			if (!isTripleRolled()) {
				if (die1Value == die2Value || die2Value == speedDieValue || die1Value == speedDieValue) {
					isDoubleRolled = true;
				}
			}
		}
		
		return isDoubleRolled;
	}
	
	public boolean isMrMonopolyRolled() {
		boolean isMrMonopolyRolled = false;
		
		if (getSpeedDie().getFaceValue() == 6) {
			isMrMonopolyRolled = true;
		}
		
		return isMrMonopolyRolled;
	}
	
	public boolean isBusRolled() {
		boolean isBusRolled = false;
		
		if (getSpeedDie().getFaceValue() == 4 || getSpeedDie().getFaceValue() == 5) {
			isBusRolled = true;
		}
		
		return isBusRolled;
	}
	
	public Die getDie1() {
		return die1;
	}

	public void setDie1(Die die1) {
		this.die1 = die1;
	}

	public Die getDie2() {
		return die2;
	}

	public void setDie2(Die die2) {
		this.die2 = die2;
	}

	public Die getSpeedDie() {
		return speedDie;
	}

	public void setSpeedDie(Die speedDie) {
		this.speedDie = speedDie;
	}
}
