public class Knapsack implements GACandidate{
	
	protected boolean[] items = new boolean[100];
	protected double fitness = -1;
	protected double utility;
	protected double weight;
	protected Settings settings;
	protected GACandidate father = this;
	
	public Knapsack(Settings _settings) {
		settings = _settings;
		
		for(int i = 0; i < items.length; i++) {
			//items[i] = (Math.random() < 0.1);
			items[i] = false;
		}
		mutate();
	}
	
	public Knapsack(Knapsack _father) {
		settings = _father.settings;
		items = _father.items;
	}

	public GACandidate reproduce(GACandidate _father) {
		father = _father;
		GACandidate child = new Knapsack(this);
		return child;
	}

	public void mutate() {
		int numOfMutations = (int) (items.length * settings.MUTATION_RATE);
		for(int m = 0; m < numOfMutations; m++) {
			int mutationLocation = (int) (items.length * Math.random());
			items[mutationLocation] = !items[mutationLocation];
		}
	}

	public void crossover() {
		boolean[] fatherItems = father.getItems();
		boolean crossover = Math.random() < settings.CROSSOVER_RATE;
		int splitSize = (int) (settings.CROSSOVER_SPLIT * items.length);
		
		if(crossover) {
			int start = (int) (Math.random() * items.length);
			for(int i = start; i < splitSize; i++) {
				items[i] = fatherItems[i];
			}
		}
		
	}

	public double calculateFitness(boolean force) {
		if(fitness == -1 || force) {
			packKnapsack();
			
			if(weight > 500) {
				fitness = -2;
			}else {
				fitness = utility;
			}
			
			
		}
		return fitness;
	}
	
	public double calculateFitness() {
		return calculateFitness(false);
	}
	
	private void packKnapsack() {
		
		double[] vals = Assess.getTest2(items);
		weight = vals[0];
		utility = vals[1];
		
		//utility = 1000 * Math.random();
		//weight = 100 * Math.random();
		
	}

	public String toString() {
		return "weight|" + weight + " utility|" + utility;
	}


	public boolean[] getItems() {
		return items;
	}

	//UNUSED
	public double[] getDials() {
		return null;
	}
	
}
