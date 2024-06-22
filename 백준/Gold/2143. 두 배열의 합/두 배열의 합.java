import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine()); // 합

		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(input[i]);
		}

		int m = Integer.parseInt(br.readLine());
		int[] B = new int[m];
		input = br.readLine().split(" ");
		for (int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(input[i]);
		}

		Map<Integer, Long> cntA = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++) {
				sum += A[j];
				Long count = cntA.get(sum);
				if (count == null) {
					cntA.put(sum, 1L);
				} else {
					cntA.put(sum, count + 1);
				}
			}
		}

		Map<Integer, Long> cntB = new HashMap<>();
		for (int i = 0; i < m; i++) {
			int sum = 0;
			for (int j = i; j < m; j++) {
				sum += B[j];
				Long count = cntB.get(sum);
				if (count == null) {
					cntB.put(sum, 1L);
				} else {
					cntB.put(sum, count + 1);
				}
			}
		}

		long ans = 0;
		for (Integer i : cntA.keySet()) {
			if (cntB.containsKey(T - i)) {
				ans += cntA.get(i) * cntB.get(T - i);
			}
		}

		System.out.println(ans);

	}
}