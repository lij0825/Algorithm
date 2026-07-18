import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//진기의 최고급 붕어빵
public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			int N, M, K; // N사람, M시간, K개
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			int[] time = new int[N]; // 도착시간
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(time);

			boolean isPossible = true;
			for (int i = 0; i < N; i++) {
				int totalBread = (time[i] / M) * K;
				if (totalBread < i + 1) {
					isPossible = false;
					break;
				}
			}

			bw.write("#" + tc + (isPossible ? " Possible\n" : " Impossible\n"));
		}

		br.close();
		bw.flush();
		bw.close();
	}

}
