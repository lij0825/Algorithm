import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, X, ans = 0, days = 0;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		input();

		solve();

		output();
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}

	static void solve() {

		int sum = 0, left = 0, right = 0;

		while (right < N) {
			sum += arr[right];
			if (right < X - 1) {
				right++;
				continue;
			}
			if (sum > ans) {
				ans = sum;
				days = 1;
			} else if (ans != 0 && sum == ans) {
				days++;
			}
			sum -= arr[left++];
			right++;
		}
	}

	static void output() {
		System.out.print(ans == 0 ? "SAD" : ans + "\n" + days);
	}

}