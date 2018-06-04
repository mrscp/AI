import java.util.Arrays;
import java.util.Random;


public class ANN {
	
	public static double [][]X={
		{0.0,0.0},
		{0.0,1.0},
		{1.0,0.0},
		{1.0,1.0}
	};
	
	public static double[] y={
		0.0,
		1.0,
		1.0,
		0.0};
	
	public static double[][] hiddenWeights
	= new double[2][3];/*{
		{-5.0,10.0,-10.0},
		{-5.0,-10.0,10.0}
	};*/
	public static double [] outputWeights
	= new double[3];/*{-5.0,10.0,10.0};*/
	
	
	public static Random rnd = new Random();
	
	public static double activation(double z)
	{
		//return z>0?1:0;
		return 1.0/(1+Math.exp(-1.0*z));
		
	}
	public static double feedForward(double [] x)
	{
		double outputNH0 = 
				hiddenWeights[0][0]+
				hiddenWeights[0][1]*x[0]+
				hiddenWeights[0][2]*x[1];
		double outputNH1 = 
				hiddenWeights[1][0]+
				hiddenWeights[1][1]*x[0]+
				hiddenWeights[1][2]*x[1];
		double outputNHA0=activation(outputNH0);
		double outputNHA1=activation(outputNH1);
		
		double outputNHF0=
				outputWeights[0]+
				outputWeights[1]*outputNHA0+
				outputWeights[2]*outputNHA1;
		double output = activation(outputNHF0);
		return output;
		
		
	}
	
	
	public static int getQuality(){
		int count = 0;
		for (int i = 0; i < X.length; i++) {
			if((feedForward(X[i])<0.5 && y[i] == 1) || (feedForward(X[i])>0.5 && y[i] == 0)){
				count++;
			}
			//System.out.println(feedForward(x));
		}
		return count;
	}
	/*public static double[][] generateRandomly(){
		outputWeights
		= new double[]{-5.0,10.0,10.0};
		return new double[][]{
			{-5.0,10.0,-10.0},
			{-5.0,-10.0,10.0}
		};
	}*/
	public static void MonteCarlo(int maxIter,int n)
	{
		int iter=0;
		while(iter++<maxIter)
		{
			learn();
			//System.out.println(getQuality());
			if(getQuality()==0)
			{	
				System.out.println("I: "+iter);
				return ;
			}
		}
		return ;
	}
	
	public static void learn()
	{
		for(int i=0;i<hiddenWeights.length;i++)
			for(int j=0;j<hiddenWeights[0].length;j++)
				hiddenWeights[i][j]=-10+rnd.nextDouble()*20;
		for(int i =0;i<outputWeights.length;i++)
			outputWeights[i]=-10+rnd.nextDouble()*20;
	}
	
	public static void main(String... args)
	{
		MonteCarlo(1000000, 0);
		for (double[] x : X) {
			System.out.println(x[0] + " " + x[1] + ": " + feedForward(x));
		}
		
		for (double [] x : hiddenWeights) {
			System.out.println(Arrays.toString(x));
		}
		System.out.println(Arrays.toString(outputWeights));
	}
}
