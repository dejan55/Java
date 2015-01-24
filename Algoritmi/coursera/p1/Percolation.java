public class Percolation {
   private WeightedQuickUnionUF uf;
   private boolean b[][];
   private int mn,c;
   public Percolation(int N)   {             // create N-by-N grid, with all sites blocked
	if(N<=0) throw new IllegalArgumentException();
	c=N;
	b=new boolean[N+2][N+2]; 
	for(int i=1;i<=N;i++)
		for(int j=1;j<=N;j++){
			b[i][j]=false;
			}
	uf=new WeightedQuickUnionUF(N*(N+3)+2);
	for(int i=0;i<N;i++){		// virtual top
		uf.union(i,i+1);
		}
	mn=N*(N+3)+1;
	for(int j=mn;j>(N*(N+2)+1);j--)
		uf.union(j,j-1);	    // virtual bottom
   }
   public void open(int i, int j)  {    // open site (row i, column j) if it is not already
	if (i <= 0 || i > c || j <= 0 || j > c) throw new IndexOutOfBoundsException("row index i out of bounds");
	if(isOpen(i,j)) return;
	else { b[i][j]=true;			
	if((i+1==c+1))	uf.union((i+1)*c+(i+j+1),i*c+(i+j)); 
	else if(isOpen(i+1,j)) uf.union((i+1)*c+(i+j+1),i*c+(i+j)); 
	if((i-1==0))	uf.union((i-1)*c+(i+j-1),i*c+(i+j)); 
	else if(isOpen(i-1,j)) uf.union((i-1)*c+(i+j-1),i*c+(i+j));  
	if(j+1==c+1) return;	
	else if(isOpen(i,j+1)) uf.union(i*c+(i+j)+1,i*c+(i+j));  
	if(j-1==0) return;
	else if(isOpen(i,j-1)) uf.union(i*c+(i+j)-1,i*c+(i+j));  }
	}
   public boolean isOpen(int i, int j) {   // is site (row i, column j) open?
	if (i <= 0 || i > c||j <= 0 || j > c) throw new IndexOutOfBoundsException("row index i out of bounds");
	return b[i][j];
	}
   public boolean isFull(int i, int j) {   // is site (row i, column j) full?
	if (i <= 0 || i > c||j <= 0 || j > c) throw new IndexOutOfBoundsException("row index i out of bounds");
	return uf.connected(i*c+(i+j),0);
	}
   public boolean percolates()  {            // does the system percolate?
	return uf.connected(0,mn);
	}
	///*
   public static void main(String[] args){
	StdOut.println("Vnesi go N");
	int na=StdIn.readInt();
	Percolation p=new Percolation(na);
	for(int i=0;i<5;i++){
		StdOut.println("Vnesi a[i][j]");
		int k=StdIn.readInt();
		int l=StdIn.readInt();
		p.open(k,l);
	}
	System.out.println("Proveri dali a[i][j] broja e Full");
		int k=StdIn.readInt();
		int l=StdIn.readInt();
		if(p.isFull(k,l)) StdOut.println("Full");
	if(p.percolates()) StdOut.println("Peroclates");	
	}    //*/
}