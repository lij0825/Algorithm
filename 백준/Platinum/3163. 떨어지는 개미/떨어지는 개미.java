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

		for (int t = 0; t < TC; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 개미 마리수
			int L = Integer.parseInt(st.nextToken()); // 막대 길이
			int k = Integer.parseInt(st.nextToken()); // k 번째로 떨어지는 개미 출력 해야됨

			Deque<Integer> dq = new ArrayDeque<>();

			Ant[] ant = new Ant[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				int ID = Integer.parseInt(st.nextToken());
				if (ID < 0) {
					ant[i] = new Ant(ID, idx);
				} else {
					ant[i] = new Ant(ID, L - idx);
				}
				dq.add(ID);
			}

			Arrays.sort(ant);

			Ant[] drop = new Ant[N];

			for (int i = 0; i < N; i++) {
				int id = 0;
				int len = 0;
				if (ant[i].ID < 0) {
					id = dq.pollFirst();
				} else {
					id = dq.pollLast();
				}
				len = ant[i].len;
				drop[i] = new Ant(id, len);
			}

			Arrays.sort(drop);

			sb.append(drop[k - 1].ID);
			if (t != TC - 1) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	static class Ant implements Comparable<Ant> {
		int ID, len;

		public Ant(int iD, int len) {
			super();
			this.ID = iD;
			this.len = len;
		}

		@Override
		public int compareTo(Ant o) {
			if (this.len == o.len) {
				return this.ID - o.ID;
			}
			return this.len - o.len;
		}
	}

}