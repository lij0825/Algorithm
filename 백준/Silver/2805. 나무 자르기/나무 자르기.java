import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // 나무
		int m = Integer.parseInt(st.nextToken()); // 최소 미터

		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());

		long maxH = Integer.MIN_VALUE;
		long minH = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (maxH < arr[i]) {
				maxH = arr[i];
			}
		}
		long mid = (maxH + minH) / 2;
		int cnt = 0;
		while (true) {
			// 자른 나무 더하기
			long sum = 0;

			for (int i = 0; i < n; i++) {
				if (arr[i] > mid) {
					sum += (arr[i] - mid);
				}
			}
			// 필요 미터보다 작으면
			if (sum < m) {
				maxH = mid;
				if (mid == (maxH + minH) / 2) {
					break;
				}
				mid = (maxH + minH) / 2;

			} else if (sum > m) {
				minH = mid;
				if (mid == (maxH + minH) / 2) {
					break;
				}
				mid = (minH + maxH) / 2;

			} else if (sum == m) {
				break;
			}
		}
		System.out.println(mid);
	}
}