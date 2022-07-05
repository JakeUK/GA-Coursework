package galgorithm;

public class Settings {
	
	//Default settings
	public int POPULATION_SIZE = 20;
	
	public double MUTATION_RATE = 0.1;
	public double MUTATION_SAMPLES = 5;
	public double MUTATION_MULTIPLIER = 1;
	
	public double CROSSOVER_RATE = 0.1;
	public double CROSSOVER_SPLIT = 0.5;
	
	public double ELITIST_PERCENT = 0.1;
	
	public boolean PROBLEM = false; //false = prob1, true = prob2
	
	
	
	public void setPopulation(int pop) {
		if(pop > 0) {
			POPULATION_SIZE = pop;
		}
		else {
			throw new RuntimeException("Population size not valid");
		}
	}
	
	public void setElitistPercent(double d) {
		if(d >= 0 && d <= 1) {
			ELITIST_PERCENT = d;
		}
		else {
			throw new RuntimeException("Elitist Percent not valid");
		}
	}
	
	public void setProblem(boolean prob) {
		PROBLEM = prob;
	}
	
	public void  setMutationRate(double f) {
		if(f >= 0 && f <= 1) {
			MUTATION_RATE = f;
		}
		else {
			throw new RuntimeException("Mutation rate not valid");
		}
	}
	
	public void  setMutationMultiplier(double f) {
		MUTATION_MULTIPLIER = f;
	}
	
	public void  setMutationSamples(int f) {
		if(f > 0) {
			MUTATION_SAMPLES = f;
		}
		else {
			throw new RuntimeException("Mutation sample number is not valid");
		}
	}
	
	public void  setCrossoverRate(double f) {
		if(f >= 0 && f <= 1) {
			CROSSOVER_RATE = f;
		}
		else {
			throw new RuntimeException("Crossover rate not valid");
		}
	}
	
	public void  setCrossoverSplit(double f) {
		if(f >= 0 && f <= 1) {
			CROSSOVER_SPLIT = f;
		}
		else {
			throw new RuntimeException("Crossover split not valid");
		}
	}
	
}
