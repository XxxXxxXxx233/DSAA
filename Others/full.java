package Others;

public class full {
	private static int count = 0;
	public static void permutation(char[]ss,int i){
		count++;
		if(ss==null||i<0 ||i>ss.length){
			return;
		} 
		if(i==ss.length-1){
			for(int j=0; j<ss.length; j++) {
				System.out.print(ss[j] + " ");
			}
			System.out.println();
		}else{
			for(int j=i;j<ss.length;j++){
				char temp=ss[j];
				ss[j]=ss[i];
				ss[i]=temp;
				permutation(ss,i+1);
				temp=ss[j];
				ss[j]=ss[i];
				ss[i]=temp;
			}
		}
	}
	public static void main(String args[]){
		permutation(new char[]{'1','2','3','4','5','6','7','8'},0);    
	}
}
