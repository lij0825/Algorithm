import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 개미 마리수
			int L = Integer.parseInt(st.nextToken()); // 막대 길이

			Deque<Integer> dq = new ArrayDeque<>();

			Ant[] ant = new Ant[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				if (dir == 0) {
					ant[i] = new Ant(i + 1, dir, idx, idx);
				} else {
					ant[i] = new Ant(i + 1, dir, L - idx, idx);
				}
			}
			Arrays.sort(ant, (a, b) -> a.idx - b.idx);
			for (int i = 0; i < N; i++) {
				dq.add(ant[i].ID);
			}
			Arrays.sort(ant);
			Ant[] drop = new Ant[N];
			for (int i = 0; i < N; i++) {
				int id = 0;
				int len = ant[i].len;
				if (ant[i].dir == 0) {
					id = dq.pollFirst();
				} else {
					id = dq.pollLast();
				}
				drop[i] = new Ant(id, ant[i].dir, len, len);
			}
			Arrays.sort(drop);

			sb.append("Case #" + t + ": ");
			for (int i = 0; i < N; i++) {
				sb.append(drop[i].ID);
				if (i != N - 1) {
					sb.append(" ");
				}
			}
			if (t != TC) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	static class Ant implements Comparable<Ant> {
		int ID, dir, len, idx;

		public Ant(int iD, int dir, int len, int idx) {
			super();
			this.ID = iD;
			this.dir = dir;
			this.len = len;
			this.idx = idx;
		}

		@Override
		public int compareTo(Ant o) {
			if (this.len == o.len) {
				return this.ID - o.ID;
			}
			return this.len - o.len;
		}

		@Override
		public String toString() {
			return "Ant [ID=" + ID + ", dir=" + dir + ", len=" + len + "]";
		}

	}

}