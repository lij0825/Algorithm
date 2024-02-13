
import java.util.Scanner;

//설탕 배달
public class Main {

	//	static int N;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int ans = 0;
		int three = 0;
		
		

		System.out.println(aaa(N, three));
	}

	public static int aaa(int N, int three){	
		int five = ((N - (three * 3)) / 5);
		if(((N - (three * 3)) % 5) == 0) {
			return three + five;
		}
		else if((N - (three * 3)) < 3){
			return -1;
		}
		
		return aaa(N, (three + 1));
	}
}

