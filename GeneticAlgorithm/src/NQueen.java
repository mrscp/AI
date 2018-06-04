

import java.util.Arrays;


public class NQueen implements Comparable<NQueen>{
	int n;
	int [] queens;
	public NQueen() {
		n=-1;
		queens=null;
	}
	
	public NQueen(int n) {
		this.n = n;
		queens = new int[n];
	}
	
	public NQueen(NQueen other) {
		this.n = other.n;
		this.queens=new int[n];
		for(int i=0;i<n;i++)
			this.queens[i]=other.queens[i];
	}
	
	public int getQuality()
	{
		int q = 0;
		for(int i=0;i<n;i++)
		{
			for(int j=i+1;j<n;j++)
			{
				if(queens[i]==queens[j])
					q++;
				if (Math.abs(i-j) == Math.abs(queens[i]-queens[j]))
					q++;
			}
		}
		return q;
	}
	
	@Override
	public String toString() {
		return "NQueen [n=" + n + ", queens=" + Arrays.toString(queens) + "]";
	}
	
	@Override
	public int compareTo(NQueen o) {
		return (this.getQuality()-o.getQuality());
	}
}
