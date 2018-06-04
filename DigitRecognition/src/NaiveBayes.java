import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;

/**
 * Created by swakkhar on 4/10/17.
 */

public class NaiveBayes {
    public static int X[][]=new int [5000][400];
    public static int y[] = new int[5000];
    
    public static int pixel_value_count[][]=new int [400][400];
    
	private static BufferedReader readerX;
	private static BufferedReader readerY;
    public static void readData() throws IOException {
        readerX = new BufferedReader(new FileReader("X.csv"));
        readerY = new BufferedReader(new FileReader("Y.csv"));
        String xStr=null;
        String yStr=null;
        int i=0;
        while (true)
        {
            xStr=readerX.readLine();
            yStr=readerY.readLine();
            if (xStr==null)break;

            int j=0;
            for (String x:xStr.split(",")) {
                X[i][j]=Integer.parseInt(x);
                j++;
            }
            y[i]=Integer.parseInt(yStr) % 10;
            i++;

        }
    }
    private static Random rand = new Random();
    
    public static void main(String[] args) throws IOException {
        readData();
        count_pixel_value();
        
        int number = rand.nextInt(5000);    	
        new DigitDisplay(X[number]);
        System.out.println("Real Label:" + y[number]);
        System.out.println("Predicted Label:"+predictData(X[number]));
        
    }
    
    public static void count_pixel_value(){
    	for(int i = 0; i < 10; i++){
		
	for(int k = 0; k < 400; k++){
		for(int j = i*500; j < (i*500)+500; j++){
		
			if(X[j][k] == 1){
			pixel_value_count[i][k]++;
    			}
    		}
		}
    }
    }
    
    public static int predictData(int[] x)
    {
    	double probability[] = new double [10];
    	int i;
    	int maximum_probabilty = 0;
    	for(i = 0; i < 10; i++){
    		double q = 1.00;
		
		for(int j = 0; j < 400; j++){
			if(x[j] == 1){
				q = ((double)pixel_value_count[i][j]/500)* q;
			}else{
				q = ((500-(double)pixel_value_count[i][j])/500)*q;
			}	
		}
    		if(probability[maximum_probabilty] < q){
    			maximum_probabilty = i;
    		}
    		probability[i] = q;
    	}
    	return maximum_probabilty;
    }
}


class DigitDisplay extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int [][] data= new int[20][20];
    public DigitDisplay(int [] d)
    {
        super("Digit Display");
        for(int i=0;i<20;i++)
            for(int j=0;j<20;j++)
                data[i][j]=d[i*20+j];
        setSize(400,400);
        setVisible(true);
    }
    public void paint(Graphics g)
    {
        for(int i=0;i<20;i++)
            for(int j=0;j<20;j++)
            {
                if(data[i][j]==1)
                    g.fillRect(i*20,j*20,20,20);
            }
    }
}
