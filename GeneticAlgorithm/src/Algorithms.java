

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;


public class Algorithms {
	static Random myRand = new Random();
	static int iteration = 0;
	
	public static void main(String...args)
	{
		//NQueen init = generateRandomly(5);
		
		//System.out.println(init);
		//System.out.println(init.getQuality());
		System.out.println(geneticAlgorithm(20, 1000));
	}
	
	public static NQueen selectParent(ArrayList<NQueen> parents){
		int size = parents.size();
		int index = myRand.nextInt(size)%size;
		int probability = myRand.nextInt(parents.get(index).n);
		if(probability < parents.get(index).getQuality()){
			return parents.get(index);
		}
		return selectParent(parents);
	}
	
	public static ArrayList<NQueen> uniformCrossover(ArrayList<NQueen> parents){
		ArrayList<NQueen> childs = new ArrayList<>();
		
		int size = parents.size();
		int n = parents.get(0).n;
		int k = 0;
		
		for(int i = 0; i < size/2; i++){
			NQueen parent1 = selectParent(parents);
			NQueen parent2 = selectParent(parents);
			
			NQueen child1 = new NQueen(n);
			NQueen child2 = new NQueen(n);
			
			for(int j = 0; j < n; j++){
				if(Math.random() <= 0.5){
					child1.queens[j] = parent1.queens[j];
					child2.queens[j] = parent2.queens[j];
				}else{
					child2.queens[j] = parent1.queens[j];
					child1.queens[j] = parent2.queens[j];
				}
			}
			childs.add(k, child1);
			k++;
			childs.add(k, child2);
			k++;
		}
		
		return childs;
	}
	
	public static ArrayList<NQueen> mutation(ArrayList<NQueen> childs){
		ArrayList<NQueen> naturalChilds = new ArrayList<>();
		double mutationRate = 0.1;
		for(NQueen child: childs){
			NQueen temp = new NQueen(child);
			for(int i = 0; i<child.n; i++){
				double mutationProbability = Math.random();
				if(mutationProbability < mutationRate){
					int rq=myRand.nextInt(temp.n);
					int rv=myRand.nextInt(temp.n);
					temp.queens[rq]=rv;
				}
			}
			naturalChilds.add(temp);
		}
		return naturalChilds;
	}
	
	public static NQueen geneticAlgorithm(Integer n, int maxGeneration){
		int x = Math.floorDiv((n/2), 2)*2;
		int y = (int) (x * 9);
		int noOfparents = y + x;
		System.out.println(x + " " + y + " " + noOfparents);
		int generation = 0;
		
		ArrayList<NQueen> parents = new ArrayList<>();
		for(int i = 0; i < noOfparents; i++){
			parents.add(generateRandomly(n));
		}
		
		while(generation < maxGeneration){
			for(NQueen parent: parents){
				if(parent.getQuality() == 0){
					System.out.println(generation);
					return parent;
				}
			}
			
			ArrayList<NQueen> childs = uniformCrossover(parents);
			PriorityQueue<NQueen> tournament = new PriorityQueue<>();
			tournament.addAll(childs);
			ArrayList<NQueen> naturalChild = mutation(childs);
			tournament.addAll(naturalChild);
			
			for(NQueen print: parents){
				//System.out.println(print);
				if(print.getQuality() == 0){
					System.out.println(generation);
					return print;
				}
			}
			
			//System.out.println("childs");
			for(NQueen print: childs){
				//System.out.println(print);
				if(print.getQuality() == 0){
					System.out.println(generation);
					return print;
				}
			}
			//System.out.println("natural");
			for(NQueen print: naturalChild){
				//System.out.println(print);
				if(print.getQuality() == 0){
					System.out.println(generation);
					return print;
				}
			}
			
			
			for(int i = 0; i < noOfparents; i++){
				
				parents.add(tournament.poll());
			}
			generation++;
		}
		
		return null;
	}
	
	public static NQueen generateRandomNeighbor(NQueen other)
	{
			NQueen temp = new NQueen(other);
			int rq=myRand.nextInt(temp.n);
			int rv=myRand.nextInt(temp.n);
			temp.queens[rq]=rv;
			
			return temp;
	}
	
	public static NQueen generateBestNeighbor(NQueen other)
	{
		NQueen temp = new NQueen(other);
		ArrayList<NQueen> neighbours = new ArrayList<NQueen>();
		
		NQueen best = new NQueen(other);
		
		for(int i = 0; i < temp.n; i++){
			for(int j = 0; j < temp.n; j++){
				temp = new NQueen(other);
				temp.queens[i]=j;
				neighbours.add(temp);
				if(temp.getQuality() < best.getQuality()){
					best = temp;
				}
			}
		}
		
		ArrayList<NQueen> bests = new ArrayList<NQueen>();
		bests.add(best);
		for(NQueen otherBest: neighbours){
			if(best.getQuality() == otherBest.getQuality()){
				bests.add(otherBest);
			}
		}
		//System.out.println(neighbours);
		return bests.get(myRand.nextInt(bests.size()));
		//return best;
	}
	
	public static NQueen generateRandomly(int n)
	{
		NQueen obj = new NQueen(n);
		for(int i=0;i<n;i++)
		{
			obj.queens[i]=myRand.nextInt(n);
		}
		return obj;
	}
	
}
