import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;


class InsufficientElementsException extends Exception{
		public InsufficientElementsException(){
			super("Insufficient number of elements");
		}
	}
	class InvalidRowNumberException extends Exception{
		public InvalidRowNumberException(){
			super("Invalid row number");
		}
	}
	class InvalidColumnNumberException extends Exception{
		public InvalidColumnNumberException(){
			super("Invalid column number");
		}
	}
	final class FloatMatrix{
	private final int m;
	private final int n;
	private final float a[][];
	public FloatMatrix(float a[], int m, int n) throws Exception{
		int size=a.length;
		int proizvod=m*n;
		if(size<proizvod) throw new InsufficientElementsException();
		else{
		int br=size-proizvod;
		this.n=n;
		this.m=m;
		this.a=new float[m][n];
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
			this.a[i][j]=a[br++];
		}
	}
	public int numRows(){
		return m;
	}
	public int numColumns(){
		return n;
	}
	public String getMatrixDimensions(){
		return String.format("[%d x %d]",m,n);
	}
	public float maxElementAtRow(int row) throws Exception{
		row--;
        if(row>=m||row<0) throw new InvalidRowNumberException();
		float max=a[row][0];
		for(int i=1;i<n;i++)
			if(max<a[row][i]) max=a[row][i];
		return max;	
	}
	public float maxElementAtColumn(int column) throws Exception{
		column--;
        if(column>=n||column<0) throw new InvalidColumnNumberException();
		float max=a[0][column];
		for(int i=1;i<m;i++)
			if(max<a[i][column]) max=a[i][column];
		return max;	
	}
	public float sum(){
		float sum=0;
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
			sum+=a[i][j];
		return sum;
	}
	public float[] toSortedArray(){
		float b[]=new float[m*n];
		int br=0;
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
			b[br++]=a[i][j];
        int max;
        for(int i=0;i<br;i++){
        	max=i;
            for(int j=i+1;j<br;j++)
                if(b[max]<b[j]) max=j; 
                if(i!=max){
                float tmp=b[i];
                b[i]=b[max];
                b[max]=tmp; 
            }
        }
		return b;
	} 
	public String toString(){
		String s="";
		for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
				if(j+1!=n)
                s+=String.format("%.2f\t",a[i][j]);
               	else
                s+=String.format("%.2f",a[i][j]);
            }
            if(i+1==m) break;
            s+="\n";
		}
		return s;
	}
        public boolean equals(FloatMatrix fm){
        	if(fm==null) return false;
            if(this.m!=fm.m) return false;
            if(this.n!=fm.n) return false;
            for(int i=0;i<m;i++)
                for(int j=0;j<n;j++)
                if(this.a[i][j]!=fm.a[i][j]) return false;
            return true;
        }
        public int hashCode(){
        	return this.m*this.n;
        }
	}
	class MatrixReader{
        public static FloatMatrix readFloatMatrix(InputStream input) throws Exception{
        	Scanner in=new Scanner(input);
         	String s=in.nextLine();
            String[] parts=s.split(" ");
            int m=Integer.parseInt(parts[0]);
            int n=Integer.parseInt(parts[1]);
            int br=0;
            float b[]=new float[m*n];
            for(int i=0;i<m;i++){
                s=in.nextLine();
                parts=s.split(" ");
                for(int j=0;j<n;j++)
                    b[br++]=Float.parseFloat(parts[j]);
            }
            return new FloatMatrix(b,m,n);
        }
	}

public class FloatMatrixTester {
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		int tests = scanner.nextInt();
		FloatMatrix fm = null;

		float[] info = null;

		DecimalFormat format = new DecimalFormat("0.00");

		for (int t = 0; t < tests; t++) {

			String operation = scanner.next();

			switch (operation) {
			case "READ": {
				int N = scanner.nextInt();
				int R = scanner.nextInt();
				int C = scanner.nextInt();

				float[] f = new float[N];

				for (int i = 0; i < f.length; i++)
					f[i] = scanner.nextFloat();

				try {
					fm = new FloatMatrix(f, R, C);
					info = Arrays.copyOf(f, f.length);

				} catch (InsufficientElementsException e) {
					System.out.println("Exception caught: " + e.getMessage());
				}

				break;
			}

			case "INPUT_TEST": {
				int R = scanner.nextInt();
				int C = scanner.nextInt();

				StringBuilder sb = new StringBuilder();

				sb.append(R + " " + C + "\n");

				scanner.nextLine();

				for (int i = 0; i < R; i++)
					sb.append(scanner.nextLine() + "\n");

				fm = MatrixReader.readFloatMatrix(new ByteArrayInputStream(sb
						.toString().getBytes()));

				info = new float[R * C];
				Scanner tempScanner = new Scanner(new ByteArrayInputStream(sb
						.toString().getBytes()));
				tempScanner.nextFloat();
				tempScanner.nextFloat();
				for (int z = 0; z < R * C; z++) {
					info[z] = tempScanner.nextFloat();
				}

				tempScanner.close();

				break;
			}

			case "PRINT": {
				System.out.println(fm.toString());
				break;
			}

			case "DIMENSION": {
				System.out.println("Dimensions: " + fm.getMatrixDimensions());
				break;
			}

			case "COUNT_ROWS": {
				System.out.println("Rows: " + fm.numRows());
				break;
			}

			case "COUNT_COLUMNS": {
				System.out.println("Columns: " + fm.numColumns());
				break;
			}

			case "MAX_IN_ROW": {
				int row = scanner.nextInt();
				try {
					System.out.println("Max in row: "
							+ format.format(fm.maxElementAtRow(row)));
				} catch (InvalidRowNumberException e) {
					System.out.println("Exception caught: " + e.getMessage());
				}
				break;
			}

			case "MAX_IN_COLUMN": {
				int col = scanner.nextInt();
				try {
					System.out.println("Max in column: "
							+ format.format(fm.maxElementAtColumn(col)));
				} catch (InvalidColumnNumberException e) {
					System.out.println("Exception caught: " + e.getMessage());
				}
				break;
			}

			case "SUM": {
				System.out.println("Sum: " + format.format(fm.sum()));
				break;
			}

			case "CHECK_EQUALS": {
				int val = scanner.nextInt();

				int maxOps = val % 7;

				for (int z = 0; z < maxOps; z++) {
					float work[] = Arrays.copyOf(info, info.length);

					int e1 = (31 * z + 7 * val + 3 * maxOps) % info.length;
					int e2 = (17 * z + 3 * val + 7 * maxOps) % info.length;

					if (e1 > e2) {
						float temp = work[e1];
						work[e1] = work[e2];
						work[e2] = temp;
					}

					FloatMatrix f1 = fm;
					FloatMatrix f2 = new FloatMatrix(work, fm.numRows(),
							fm.numColumns());
					System.out
							.println("Equals check 1: "
									+ f1.equals(f2)
									+ " "
									+ f2.equals(f1)
									+ " "
									+ (f1.hashCode() == f2.hashCode()&&f1
											.equals(f2)));
				}

				if (maxOps % 2 == 0) {
					FloatMatrix f1 = fm;
					FloatMatrix f2 = new FloatMatrix(new float[] { 3.0f, 5.0f,
							7.5f }, 1, 1);

					System.out
							.println("Equals check 2: "
									+ f1.equals(f2)
									+ " "
									+ f2.equals(f1)
									+ " "
									+ (f1.hashCode() == f2.hashCode() && f1
											.equals(f2)));
				}

				break;
			}

			case "SORTED_ARRAY": {
				float[] arr = fm.toSortedArray();

				String arrayString = "[";

				if (arr.length > 0)
					arrayString += format.format(arr[0]) + "";

				for (int i = 1; i < arr.length; i++)
					arrayString += ", " + format.format(arr[i]);

				arrayString += "]";

				System.out.println("Sorted array: " + arrayString);
				break;
			}

			}

		}

		scanner.close();
	}
}