import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

			int[] time = new int[11112]; // 도착시간
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				time[Integer.parseInt(st.nextToken())] += 1;
			}

			boolean flag = true;
			int bread = 0; // 붕어빵 갯수
			for (int i = 0; i < 11112; i++) {
				if (i != 0 && i % M == 0) {
					bread += K;
				}
				if (time[i] > 0) {
					bread -= time[i];
				}
				if (bread < 0) {
					flag = false;
					break;
				}
			}

			if (flag) {
				bw.write("#" + tc + " Possible\n");
			} else {
				bw.write("#" + tc + " Impossible\n");
			}

		}

		br.close();
		bw.flush();
		bw.close();
	}

}
