import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			R = Math.min(N - R, R);
			long NN = 1;
			long RR = 1;
			for (int i = 0; i < R; i++) {
				NN = (NN * (N - i)) % MOD;
				RR = (RR * (R - i)) % MOD;
			}
			long RRR = pow(RR, MOD - 2);
			long ans = (NN * RRR) % MOD;
			sb.append(ans).append("\n");
		
		System.out.println(sb.toString());
	}

	static long pow(long num, long exponentiation) {
		if (exponentiation == 1) {
			return num;
		}
		long halfPow = pow(num, exponentiation / 2);
		long res = halfPow * halfPow % MOD;
		if (exponentiation % 2 == 1) {
			res = res * num % MOD;
		}
		return res;
	}
}