public class UnionFind{
	private int a[];
	private int sz[];
	public UnionFind(int N){
		a=new int[N];
		sz=new int[N];
		for(int i=0;i<N;i++){
			a[i]=i;
			sz[i]=1; }
		
	}
	private int root(int i){
		while(i!=a[i]){
			a[i]=a[a[i]];
			i=a[i];
		}
		return i;
	}
	public boolean connected(int p,int q){
		return root(p)==root(q);
	}
	public void union(int p,int q){
		int i=root(p);
		int j=root(q);
		if(i==j) return;
		if(sz[i]<sz[j]) { a[i]=j; sz[j]+=sz[i]; }
		else { a[j]=i; sz[i]+=sz[j]; }
	}
	
}