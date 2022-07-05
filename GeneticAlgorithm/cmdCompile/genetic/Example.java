public class Example {
	
	public Example() {
		
		long startT = System.currentTimeMillis();
		String name = "Jake Cooper";
		String login = "jc2088";

		double[] sol1 = doProblem1(3000);
		boolean[] sol2 = doProblem2(3000);

		Assess.checkIn(name, login, sol1, sol2);

		long endT = System.currentTimeMillis();
		System.out.println("Total execution time was: " +  ((endT - startT)/1000.0) + " seconds");
		
	}
	
	private boolean[] doProblem2(int iterations) {
		
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
		}

		return problem2.candidates.get(0).getItems();
		
	}
	
	private double[] doProblem1(int iterations) {
		
		Settings settings = new Settings();
		settings.setPopulation(200);
		settings.setCrossoverRate(0.9);
		settings.setCrossoverSplit(0.2);
		settings.setMutationRate(0.15);
		settings.setMutationSamples(5);
		settings.setMutationMultiplier(0.1);
		settings.setElitistPercent(0.05);
		settings.setProblem(false);
		
		GA problem1 = new GA(settings);
		problem1.populate();
		
		for(int i = 0; i <= iterations; i++) {
			
			problem1.iterateGeneration();
		}

		
		//return best solution
		return problem1.candidates.get(0).getDials();
		
	}
	
	private void log(Object o) {
		System.out.println(o);
	}

	public static void main(String[] args) {
		Example e = new Example();
	}
	
}
