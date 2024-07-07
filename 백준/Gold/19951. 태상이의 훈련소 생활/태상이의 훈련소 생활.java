import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 연병장의 크기
		int M = Integer.parseInt(st.nextToken()); // 조교의 수

		st = new StringTokenizer(br.readLine());
		int[] H = new int[N + 1]; // 연병장의 높이
		for (int i = 1; i <= N; i++) {
			H[i] = Integer.parseInt(st.nextToken());
		}

		int[] diff = new int[N + 1]; // 높이 변화량
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			diff[a] += k;
			if (b + 1 <= N) {
				diff[b + 1] -= k;
			}
		}

		int sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += diff[i];
			H[i] += sum;
		}

		for (int i = 1; i <= N; i++) {
			sb.append(H[i]).append(" ");
		}

		System.out.println(sb);
	}
}