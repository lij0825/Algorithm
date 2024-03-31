import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine()); // 전깃줄 개수

		int[][] line = new int[n][2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			line[i][0] = Integer.parseInt(st.nextToken()); // A
			line[i][1] = Integer.parseInt(st.nextToken()); // B
		}

		Arrays.sort(line, (a, b) -> {
			return a[0] - b[0];
		});

		int[] ans = new int[n + 1];
		ArrayList<Pair> p = new ArrayList<>();
		int max, mid, min;
		int len = 0;
		for (int i = 0; i < n; i++) {
			if (line[i][1] > ans[len]) {
				ans[++len] = line[i][1];
				p.add(new Pair(line[i][1], len));
			} else {
				min = 0;
				max = len;
				while (min < max) {
					mid = (min + max) / 2;
					if (line[i][1] > ans[mid]) {
						min = mid + 1;
					} else {
						max = mid;
					}
				}
				ans[max] = line[i][1];
				p.add(new Pair(line[i][1], max));
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
		ArrayList<Integer> cut = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (!res.contains(line[i][1])) {
				cut.add(line[i][0]);
			}
		}

		sb.append(cut.size());
		Collections.sort(cut);
		for (Integer integer : cut) {
			sb.append("\n").append(integer);
		}
		System.out.println(sb);
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