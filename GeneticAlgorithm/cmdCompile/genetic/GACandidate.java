public interface GACandidate extends Comparable<GACandidate>{
	
	public GACandidate reproduce(GACandidate father);
	public void mutate();
	public void crossover() ;
	
	public double calculateFitness();
	public double calculateFitness(boolean force);
	
	@Override
	public String toString();
	
	public double[] getDials();
	public boolean[] getItems();
	
	public default int compareTo(GACandidate other) {
		if(calculateFitness() > other.calculateFitness()) {
			return -1;
		}else if(calculateFitness() < other.calculateFitness()) {
			return 1;
		}else {
			return 0;
		}
	}
	
}
