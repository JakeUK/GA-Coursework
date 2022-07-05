package galgorithm;

public class BlackBox implements GACandidate{
	
	protected double[] dials = new double[20];
	protected double fitness = -1;
	protected double pureFitness = -1;
	protected Settings settings;
	public GACandidate father = this;
	
	/*
	 * Sets the dials as the same as its parents
	 */
	public BlackBox(BlackBox parent) {
		settings = parent.settings;
		dials = parent.dials.clone();
	}
	
	/*
	 * Sets the dials to random values
	 */
	public BlackBox(Settings _settings) {
		settings = _settings;
		for(int i = 0; i < dials.length; i++) {
			dials[i] = 10 * Math.random() - 5;
		}
		calculateFitness();
	}

	/*
	 * Reproduce the candidate with a chance to mutate or crossover based on the percentages in Settings
	 */
	public GACandidate reproduce(GACandidate _father) {
		
		father = _father;
		GACandidate child = new BlackBox(this);
		return child;
		
	}
	
	/*
	 * Mutates the current candidate
	 */
	public void mutate() {
		
		//Randomises MUTATION_RATE percent of dials
		
		int numOfMutations = (int) (dials.length * settings.MUTATION_RATE * Math.random());
		for(int i = 0; i < numOfMutations; i++) {
			int mutated = (int) ((dials.length - 1) * Math.random());
			double value = 0;
			
			for(int a = 0; a < settings.MUTATION_SAMPLES; a++) {
				value += Math.random() * 5;
			}
			value = (value / settings.MUTATION_SAMPLES) * settings.MUTATION_MULTIPLIER;
			
			double oldFitness = fitness;
			setDial(mutated, value);
			calculateFitness(true);
			if(fitness < oldFitness) {
				setDial(mutated, -(value*2));
				calculateFitness(true);
				if(fitness < oldFitness) {
					setDial(mutated, value);
				}
			}
			
		}
		crossover();
	}
	
	public void crossover() {
		double[] fatherDials = father.getDials();
		boolean crossover = Math.random() < settings.CROSSOVER_RATE;
		//double split = settings.CROSSOVER_SPLIT;
		int splitSize = (int) (settings.CROSSOVER_SPLIT * dials.length);
		
		if(crossover) {
			int start = (int) (Math.random() * dials.length);
			for(int i = start; i < splitSize; i++) {
				dials[i] = fatherDials[i];
			}
		}
		calculateFitness(true);
	}
	
	/*
	 * Calculates fitness
	 */
	public double calculateFitness(boolean force) {
		if(fitness == -1 || force) {
			
			//fitness = Assess.getTest1(dials);
			pureFitness = 0;
			
			for(double dial : dials) {
				pureFitness += Math.abs(dial);
			}
			
			fitness = 1 / (pureFitness * pureFitness);
			
			
		}
		return fitness;
		
	}
	
	public double calculateFitness() {
		return calculateFitness(false);
	}
	
	public String toString() {
		return "" + pureFitness;
	}
	
	/*
	 * Does bounds checking on the dial setting
	 */
	private void setDial(int sel, double val) {
		double newVal = dials[sel] + val;
		
		if(newVal >= -5 && newVal <= 5) {
			dials[sel] = newVal;
		}
	}
	
	/*
	 * Returns dials
	 */
	public double[] getDials() {
		return dials.clone();
	}
	
	//UNUSED METHOD
	public boolean[] getItems() {
		boolean[] unused = new boolean[0];
		return unused;
	}
	
}
