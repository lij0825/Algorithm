import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		int N, max;
		long profit;

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			profit = 0;
			max = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());

			int[] prices = new int[N];
			for (int i = 0; i < N; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = N - 1; i >= 1; i--) {
				if (max < prices[i]) {
					max = prices[i];
				}
				if (max > prices[i - 1]) {
					profit += max - prices[i - 1];
				}
			}

			bw.write("#" + t + " ");
			bw.write(String.valueOf(profit));
			bw.write("\n");
		}

		bw.flush();
		bw.close();
	}

}
