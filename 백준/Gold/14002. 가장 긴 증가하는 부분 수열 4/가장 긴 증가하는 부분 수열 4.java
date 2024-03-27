import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] ans = new int[n + 1];
		ArrayList<Pair> p = new ArrayList<>();
		int min, max, mid;
		int len = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] > ans[len]) {
				ans[++len] = arr[i];
				p.add(new Pair(arr[i], len));
			} else {
				min = 0;
				max = len;
				while (min < max) {
					mid = (min + max) / 2;
					if (arr[i] > ans[mid]) {
						min = mid + 1;
					} else {
						max = mid;
					}
				}
				ans[max] = arr[i];
				p.add(new Pair(arr[i], max));
			}
		}

		Collections.reverse(p);
		ArrayList<Integer> res = new ArrayList<>();
		for (Pair pair : p) {
			if (pair.idx == len) {
				len--;
				res.add(pair.num);
			}
		}
		System.out.println(res.size());
		Collections.reverse(res);
		System.out.println(res.toString().replaceAll("[\\[\\],]", ""));
	}

	static class Pair {
		int num, idx;

		public Pair(int num, int idx) {
			super();
			this.num = num;
			this.idx = idx;
		}

		@Override
		public String toString() {
			return "Pair [num=" + num + ", idx=" + idx + "]";
		}

	}
}