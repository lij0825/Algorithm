

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 이인준
 * 
 * 
 * 
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int hn = Math.abs(Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken()));

		long[][] way = new long[hn + 1][hn + 1];

		for (int i = 0; i < way.length; i++) {
			for (int j = i; j < way.length; j++) {
				if (i - 1 >= 0) {
					way[i][j] = way[i - 1][j] + way[i][j - 1];
				} else {
					way[i][j] = i + 1;
				}
			}
		}

//		for (long[] is : way) {
//			System.out.println(Arrays.toString(is));
//		}

		System.out.println(way[hn][hn]);
	}
}
