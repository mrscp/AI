package algorithm;

import java.util.concurrent.TimeUnit;

public class GeneticAlgorithm {
	public String getTime(long totalTime){
		if(totalTime > 100)
			return ("Time: "+String.format("%d min, %d sec", 
			    TimeUnit.MILLISECONDS.toMinutes(totalTime),
			    TimeUnit.MILLISECONDS.toSeconds(totalTime) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalTime))
			));
		else{
			return ("Time: " + totalTime + " ms");
		}
	}
	
	public GeneticAlgorithm(){
		int n = 100;
		int population = 400;
		double localMinima = (double)n;
		int countSameGeneration = 0;
		
		long startTime = System.currentTimeMillis();
		
		Population pop = new Population(population);
		pop.generateRandomPopulation(n);
		int generation = 0;
		
		while(true){
			NQueen sol = pop.calculateFitness();
			//pop.print();
			if(localMinima > pop.best.fitness){
				localMinima = pop.best.fitness;
			}
			if(pop.best.fitness >= localMinima && pop.best.fitness <= localMinima+2){
				countSameGeneration++;
			}else{
				countSameGeneration = 0;
			}
			System.out.println(countSameGeneration + " " + localMinima);
			if(Math.random() < 0.005){
				for(int c = 0; c < 100; c++)
				System.out.println("restarted");
				pop.generateRandomPopulation(n);
				sol = pop.calculateFitness();
				localMinima = (double)n;
				countSameGeneration = 0;
			}
			
			if(pop.best.fitness == 0 || sol != null){
				System.out.println("\nSolution: \n" + pop.best);
				break;
			}
			
			System.out.println(generation + " " + pop.n + ": " + pop.best);
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(getTime(totalTime));
			
			pop.crossOver();
			
			pop.mutation();
			
			generation++;
		}
		
		System.out.println(generation);
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(getTime(totalTime));
	}
	
	public static void main(String args[]){
		new GeneticAlgorithm();
	}
}
