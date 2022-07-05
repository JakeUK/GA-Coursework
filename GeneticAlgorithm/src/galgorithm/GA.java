package galgorithm;

import java.util.ArrayList;
import java.util.Collections;

public class GA {
	
	public ArrayList<GACandidate> candidates;
	private Settings settings;
	private long generation;
	private double fitnessSum;
	
	private long timeTaken;
	
	public GA(Settings _settings) {
		settings = _settings;
	}
	
	public void populate() {
		candidates = new ArrayList<>();
		generation = 0;
		for(int i = 0; i < settings.POPULATION_SIZE; i++) {
			if(!settings.PROBLEM) {
				candidates.add(new BlackBox(settings));
			}else {
				candidates.add(new Knapsack(settings));
			}
		}
		sortCandidates();
	}
	
	
	
	
	private GACandidate[] chooseParents() {

		GACandidate[] parents = new GACandidate[2];
		
		double rand = fitnessSum * Math.random();
		double runningSum = 0;
		
		for(int p = 0; p < 2; p++) {
			for(int i = 0; i < candidates.size(); i++) {
				double fit = candidates.get(i).calculateFitness();
				if(fit > 0) {
					runningSum += candidates.get(i).calculateFitness();
					if(runningSum > rand) {
						parents[p] = candidates.get(i);
					}
				}
				
			}
		}
		
		return parents;
	}
	
	private void calculateFitnessSum() {
		double sum = 0;
		for(int i = 0; i < candidates.size(); i++) {
			double fit = candidates.get(i).calculateFitness();
			if(fit > 0) {
				sum += fit;
			}
		}
		fitnessSum = sum;
	}
	
	public void iterateGeneration() {
		
		long prevTime = System.currentTimeMillis();
		
		//PICK CHILDREN
		ArrayList<GACandidate> newCandidates = new ArrayList<>();
		int topRanks = (int) (settings.POPULATION_SIZE * settings.ELITIST_PERCENT);
		
		//Elitist selection (top X%)
		for(int i = 0; i < topRanks; i++) {
			newCandidates.add(candidates.get(i));
		}
		
		calculateFitnessSum();
		for(int i = topRanks; i < settings.POPULATION_SIZE; i++) {
			GACandidate[] parents = chooseParents();
			if(parents != null) {
				newCandidates.add(parents[0].reproduce(parents[1]));
			}else {
				//Incase somehow no parents are found (shouldnt happen)
				newCandidates.add(candidates.get((int)(Math.random() * (candidates.size() - 1))));
			}
		}
		
		//MUTATE
		for(int i = 0; i < newCandidates.size(); i++) {
			if(i != 0) {
				newCandidates.get(i).mutate(); // First place does not get mutated
			}
			newCandidates.get(i).calculateFitness();
		}
		
		candidates = newCandidates;
		
		sortCandidates();
		timeTaken = System.currentTimeMillis() - prevTime;
		
		generation++;
	}
	
	private void sortCandidates() {
		Collections.sort(candidates);
	}
	
	public String generationStatistics() {
		String stats = "Generation " + generation + "  |   Time taken : " + timeTaken + "ms\n" + 
		               "   Best Fitness : " + getBestFitness() + "\n" + 
		               "   Median Fitness : " + getMedianFitness() + "\n" + 
		               "   Worst Fitness : " + getWorstFitness() + "\n";
		return stats;
	}
	
	public String getBestFitness() {
		return candidates.get(0).toString();
	}
	
	public String getMedianFitness() {
		return candidates.get((candidates.size() - 1) / 2).toString();
	}
	
	public String getWorstFitness() {
		return candidates.get(candidates.size() - 1).toString();
	}
	
	
}
