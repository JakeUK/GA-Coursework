package galgorithm;

public class Example {
	
	public Example() {
		
		doProblem1(3000);
		doProblem2(3000);
		
	}
	
	private void doProblem2(int iterations) {
		
		Settings settings = new Settings();
		settings.setPopulation(200);
		settings.setCrossoverRate(0.9);
		settings.setCrossoverSplit(0.2);
		settings.setMutationRate(0.01); //Single bit
		settings.setElitistPercent(0.05);
		settings.setProblem(true);
		
		GA problem2 = new GA(settings);
		problem2.populate();
		
		for(int i = 0; i <= iterations; i++) {
			
			problem2.iterateGeneration();
			if(i % 500 == 0) {
				log(problem2.generationStatistics());
			}
		}
		
	}
	
	private void doProblem1(int iterations) {
		
		Settings settings = new Settings();
		settings.setPopulation(200);
		settings.setCrossoverRate(0.9);
		settings.setCrossoverSplit(0.2);
		settings.setMutationRate(0.15);
		settings.setMutationSamples(5);
		settings.setMutationMultiplier(0.01);
		settings.setElitistPercent(0.05);
		settings.setProblem(false);
		
		GA problem1 = new GA(settings);
		problem1.populate();
		
		for(int i = 0; i <= iterations; i++) {
			
			problem1.iterateGeneration();
			if(i % 500 == 0) {
				log(problem1.generationStatistics());
			}
		}

		
		//Print best solution
		double[] bestDials = problem1.candidates.get(0).getDials();
		
	}
	
	private void log(Object o) {
		System.out.println(o);
	}

	public static void main(String[] args) {
		Example e = new Example();
	}
	
}
