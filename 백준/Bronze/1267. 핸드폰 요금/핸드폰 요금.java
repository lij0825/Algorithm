import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		int[] arr = new int[n];
		for(int i = 0; i < n;i++){
			arr[i] = Integer.parseInt(str[i])+1;
		}
		int Y = 0;
		int M = 0;
		for(int i = 0; i < n; i++){
			Y += young(arr[i]);
			M += minsick(arr[i]);
		}
		if(Y==M){
			System.out.print("Y "+"M "+Y);
		}else if(Y>M){
			System.out.print("M "+M);
		}else{
			System.out.print("Y "+Y);
		}
		
	}

	static int young(int time){
		int cost = 0;
		while(time>0){
			time  =  time - 30;
			cost += 10;
		}

		return cost;
	}
	static int minsick(int time){
		int cost = 0;
		while(time>0){
			time  =  time - 60;
			cost += 15;
		}

		return cost;	
	}
}