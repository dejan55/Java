import java.util.Scanner;

class Matrix<T>{
    private int n,m;
    private T[][] matrica;
    public Matrix(int numRows,int numCols){
    	n=numRows;
        m=numCols;
        matrica=(T[][])new Object[numRows][numCols];
        for(int i=0;i<numRows;i++)
            for(int j=0;j<numCols;j++)
            matrica[i][j]=null;
    }
    public int getNumRows(){
    	return n;
    }
    public int getNumColumns(){
    	return m;
    }
    public T getElementAt(int row,int col){
    	return matrica[row][col];
    }
    public void setElementAt(int row,int col,T value){
    	matrica[row][col]=value;
    }
    public void fill(T element){
    	for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
            matrica[i][j]=element;
    }
    public void insertRow(int row){
    	if(row<0&&row>getNumRows()) throw new ArrayIndexOutOfBoundsException();
        T[][] tmp=(T[][]) new Object[n+1][m];
        
		for(int i=0;i<=n;i++){
            if(i<row)
            for(int j=0;j<m;j++)
            tmp[i][j]=matrica[i][j];
            else if(i==row)
                for(int j=0;j<m;j++)
                tmp[i][j]=null;
           else 
                for(int j=0;j<m;j++)
                tmp[i][j]=matrica[i-1][j];
        }
        matrica=tmp;
        n++;
    }
    public void insertColumn(int col){
    	if(col<=0&&col>=getNumColumns()) throw new ArrayIndexOutOfBoundsException();
        T[][] tmp=(T[][]) new Object[n][m+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<=m;j++){
                if(j<col)
					tmp[i][j]=matrica[i][j];
				else if(j==col)
					tmp[i][m]=null;
				else
					tmp[i][j]=matrica[i][j-1];
			
			}
        }
        matrica=tmp;
        m++;
    }
    public void deleteRow(int row){
    	if(row<=0&&row>=getNumRows()) throw new ArrayIndexOutOfBoundsException();
        T[][] tmp=(T[][]) new Object[n-1][m];
        for(int i=0;i<n;i++){
        	if(i<row)
                for(int j=0;j<m;j++)
                tmp[i][j]=matrica[i][j];
            else if(i==row) continue;
            else 
                for(int j=0;j<m;j++)
                tmp[i-1][j]=matrica[i][j];
        }
          matrica=tmp;  
          n--;
    }
    public void deleteColumn(int col){
    	if(col<=0&&col>=getNumColumns()) throw new ArrayIndexOutOfBoundsException();
        T[][] tmp=(T[][]) new Object[n][m-1];
    	for(int i=0;i<n;i++)
            for(int j=0;j<m;j++){
            if(j<col)
			tmp[i][j]=matrica[i][j];
			else if(j==col) continue;
			else
				tmp[i][j-1]=matrica[i][j];
			}
        matrica=tmp;
        m--;
    }
    public void resize(int rows,int num){
        if(rows<n){
        	T[][] tmp=(T[][]) new Object[rows][m];
            for(int i=0;i<rows;i++)
				for(int j=0;j<m;j++)
				tmp[i][j]=matrica[i][j];
			matrica=tmp;
			n=rows;
        }
        else if(rows>n){
			T[][] tmp=(T[][]) new Object[rows][m];
            for(int i=0;i<rows;i++)
                if(i<n)
				for(int j=0;j<m;j++)
				tmp[i][j]=matrica[i][j];
				else
				for(int j=0;j<m;j++)
				tmp[i][j]=null;
			matrica=tmp;
			n=rows;
        }
        if(num<m){
        	T[][] tmp=(T[][]) new Object[n][num];
            for(int i=0;i<n;i++)
				for(int j=0;j<num;j++)
				tmp[i][j]=matrica[i][j];
			matrica=tmp;
			m=num;
        }
        else if(num>m){
			T[][] tmp=(T[][]) new Object[n][num];
            for(int i=0;i<n;i++){
				for(int j=0;j<m;j++)
				tmp[i][j]=matrica[i][j];
				for(int j=m;j<num;j++)
				tmp[i][j]=null;
				}
			matrica=tmp;
			m=num;
        }
    }

}
public class MatrixTest {
	
	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		int t = jin.nextInt();
		if ( t == 0 ) {
			int r = jin.nextInt();
			int c = jin.nextInt();
			Matrix<Integer> matrix = new Matrix<Integer>(r,c);
			print(matrix);
		}
		if ( t == 1 ) {
			int r = jin.nextInt();
			int c = jin.nextInt();
			Matrix<Integer> matrix = new Matrix<Integer>(r,c);
			for ( int i = 0 ; i < r ; ++i ) {
				for ( int k = 0 ; k < c ; ++k ) {
					matrix.setElementAt(i, k, jin.nextInt());
				}
			}
			print(matrix);
		}
		if ( t == 2 ) {
			int r = jin.nextInt();
			int c = jin.nextInt();
			Matrix<String> matrix = new Matrix<String>(r,c);
			for ( int i = 0 ; i < r ; ++i ) {
				for ( int k = 0 ; k < c ; ++k ) {
					matrix.setElementAt(i, k, jin.next());
				}
			}
			print(matrix);
		}
		if ( t == 3 ) {
			int r = jin.nextInt();
			int c = jin.nextInt();
			Matrix<String> matrix = new Matrix<String>(r,c);
			for ( int i = 0 ; i < r ; ++i ) {
				for ( int k = 0 ; k < c ; ++k ) {
					matrix.setElementAt(i, k, jin.next());
				}
			}
			print(matrix);
			matrix.deleteRow(jin.nextInt());
			matrix.deleteRow(jin.nextInt());
			print(matrix);
			int ir = jin.nextInt();
			matrix.insertRow(ir);
			for ( int k = 0 ; k < c ; ++k ) {
				matrix.setElementAt(ir, k, jin.next());
			}
			ir = jin.nextInt();
			matrix.insertRow(ir);
			for ( int k = 0 ; k < c ; ++k ) {
				matrix.setElementAt(ir, k, jin.next());
			}
			print(matrix);
			matrix.deleteColumn(jin.nextInt());
			matrix.deleteColumn(jin.nextInt());
			print(matrix);
			int ic = jin.nextInt();
			matrix.insertColumn(ir);
			for ( int i = 0 ; i < r ; ++i ) {
				matrix.setElementAt(i, ic, jin.next());
			}
			ic = jin.nextInt();
			matrix.insertColumn(ic);
			for ( int i = 0 ; i < r ; ++i ) {
				matrix.setElementAt(i, ic, jin.next());
			}
			print(matrix);
		}
		if ( t == 4 ) {
			int r = jin.nextInt();
			int c = jin.nextInt();
			Matrix<Integer> matrix = new Matrix<Integer>(r,c);
			for ( int i = 0 ; i < r ; ++i ) {
				for ( int k = 0 ; k < c ; ++k ) {
					matrix.setElementAt(i, k, jin.nextInt());
				}
			}
			print(matrix);
			int nr = jin.nextInt();
			int nc = jin.nextInt();
			matrix.resize(nr, nc);
			print(matrix);
			matrix.fill(jin.nextInt());
			print(matrix);
		}
	}
	
	public static void print ( Matrix<?> m ) {
		int r = m.getNumRows();int c = m.getNumColumns();
		System.out.println("  "+r+" x "+c);
		System.out.print("    ");
		for ( int k = 0 ; k < c ; ++k ) {
			System.out.print(k+"    ");
		}
		System.out.println();
		System.out.print("  ");
		for ( int k = 0 ; k < c ; ++k ) {
			System.out.print("-----");
		}
		System.out.println();
		for ( int i = 0 ; i < r ; ++i ) {
			System.out.print(i+"|");
			for ( int k = 0 ; k < c ; ++k ) {
				if ( k > 0 ) System.out.print(" ");
				System.out.print(m.getElementAt(i, k));
			}
			System.out.println();
		}
		System.out.println();
	}

}
