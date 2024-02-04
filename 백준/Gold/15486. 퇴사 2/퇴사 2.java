import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 이인준
 * 
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 남은 날 입력

		int[] t = new int[n]; // 소요 일수 배열
		int[] p = new int[n]; // 금액 배열

		int[] dp = new int[n + 1]; // 최대값 저장

		// t,p 입력
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++) {
			if (i + t[i] <= n) {
				dp[i] += p[i]; // 가능한 날들의 금액
			}
		}

		for (int i = n - 1; i >= 0; i--) {
			if (i + t[i] <= n) {
				dp[i] = Math.max(p[i] + dp[i + t[i]], dp[i + 1]);
			} else {
				dp[i] = dp[i + 1];
			}
		}

		System.out.println(dp[0]);
	}
}
