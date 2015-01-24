public class PercolationStats {
   private Percolation per;
   private int t,open,n;
   private double[] x;
   private double sum;
   public PercolationStats(int N , int T) {   // perform T independent computational experiments on an N-by-N grid
		if(N <= 0||T <= 0) throw new IllegalArgumentException();
		sum=0; t=T; n=N;
		x=new double[T];
		for(int i=0 ; i<T ; i++){
		per=new Percolation(N);
		open=0;
			while(!(per.percolates())){
			int r1=StdRandom.uniform(1,N+1);
			int r2=StdRandom.uniform(1,N+1);
			if(!per.isOpen(r1,r2)){
				per.open(r1,r2);
				open++; }
			}
		x[i]=(double)open/(n*n);
		sum+=x[i];
		}
	}
   public double mean() {   	                // sample mean of percolation threshold
		return sum/t;
	}
   public double stddev()  {
	double sum1=0;
	for(int i=0;i<t;i++)
		sum1+=(x[i]-mean())*(x[i]-mean());
	return Math.sqrt((double)sum1/(t-1));
	}
   public double confidenceLo()  {           // returns lower bound of the 95% confidence interval
	return mean()-(1.96*stddev()/Math.sqrt(t));
	}
   public double confidenceHi()   {
	return mean()+(1.96*stddev()/Math.sqrt(t));
	}										// returns upper bound of the 95% confidence interval
   public static void main(String[] args){
	int n=StdIn.readInt();
	int t=StdIn.readInt();
	PercolationStats ps=new PercolationStats(n,t);
	StdOut.println("mean\t= "+ps.mean());
	StdOut.println("stddev\t= " +ps.stddev());
	StdOut.println("95% confidence interval = "+ ps.confidenceLo() +", "+ ps.confidenceHi());
   }
}