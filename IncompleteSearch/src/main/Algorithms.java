package main;

import java.util.ArrayList;
import java.util.Random;


public class Algorithms {
	static Random myRand = new Random();
	public static void main(String...args)
	{
		/*NQueen init = generateRandomly(5);
		
		System.out.println(init);
		System.out.println(init.getQuality());
		*/
		int[] ns = {4,5,8,10,15,20}; 
		long timeGreedy = 0, timeRandom = 0;
		int successGreedy = 0, successRandom = 0;
		for(int i = 0; i < ns.length; i++){
			long startGreedy = System.currentTimeMillis();
			NQueen greedy = GreedyHillClimbing(20000, ns[i]);
			long endGreedy = System.currentTimeMillis();
			
			
			long totalGreedy = endGreedy - startGreedy;
			timeGreedy+= totalGreedy;
			
			long startRandom = System.currentTimeMillis();
			NQueen randomAlgo = RandomHillClimbing(20000, ns[i]);
			long endRandom = System.currentTimeMillis();
			
			
			long totalRandom = endRandom - startRandom;
			timeRandom+= totalRandom;
			
			if(!greedy.equals(null)){
				successGreedy++;
			}
			if(!randomAlgo.equals(null)){
				successRandom++;
			}
			
			System.out.println(ns[i] + "\nGreedy: " + totalGreedy + " " + greedy + "\nRandom: " + totalRandom + " " + randomAlgo);
		}
		System.out.println("\nPerformance: \nAlgo\tTime\tSuccess");
		System.out.println("Greedy:\t" + timeGreedy + "\t" + successGreedy + "\nRandom:\t"
				+ timeRandom + "\t" + successRandom);
	}
	
	public static NQueen Gibs(int maxIter,int n)
	{
		NQueen s=generateRandomly(n);
		if(s.getQuality()==0) return s;
		int iter=0;
		while(iter++<maxIter)
		{
			NQueen sprime = generateRandomNeighbor(s);
			if(sprime.getQuality()==0)
			{	
				System.out.println(iter);
				return sprime;
			}
			s=sprime;
		}
		return null;
	}
	
	public static NQueen RandomHillClimbing(int maxIter,int n)
	{
		NQueen s=generateRandomly(n);
		if(s.getQuality()==0) return s;
		int iter=0;
		while(iter++<maxIter)
		{
			NQueen sprime = generateRandomNeighbor(s);
			if(sprime.getQuality()==0)
			{	
				System.out.println(iter);
				return sprime;
			}
			if(s.getQuality() >= sprime.getQuality()){
				s=sprime;
			}
		}
		return null;
	}
	
	public static NQueen GreedyHillClimbing(int maxIter,int n)
	{
		NQueen s=generateRandomly(n);
		if(s.getQuality()==0) return s;
		int iter=0;
		while(iter++<maxIter)
		{
			NQueen sprime = generateBestNeighbor(s);
			if(sprime.getQuality()==0)
			{	
				System.out.println(iter);
				return sprime;
			}
			if(s.getQuality() >= sprime.getQuality()){
				s=sprime;
			}
		}
		return null;
	}
	
	public static NQueen MonteCarlo(int maxIter,int n)
	{
		int iter=0;
		while(iter++<maxIter)
		{
			NQueen s = generateRandomly(n);
			if(s.getQuality()==0)
			{	
				System.out.println(iter);
				return s;
			}
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
