import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 개미 수
		int L = Integer.parseInt(st.nextToken()); // 막대 길이
		int T = Integer.parseInt(st.nextToken()); // 시간

		Ant[] ants = new Ant[N];
		Ant[] ids = new Ant[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			ids[i] = new Ant(i + 1, start);
			String dir = st.nextToken();
			int end = 0;
			if (dir.equals("R")) {
				end = start + T;
			} else {
				end = start - T;
			}
			if (end > L || end < 0) {
				end = Math.abs(end);
				int w = end / L;
				if (w % 2 == 0) {
					ants[i] = new Ant(i + 1, end % L);
				} else {
					ants[i] = new Ant(i + 1, L - (end % L));
				}
			} else {
				ants[i] = new Ant(i + 1, end);
			}
		}
		int coin = Integer.parseInt(br.readLine());
		Arrays.sort(ants, (a, b) -> a.now - b.now);
		Arrays.sort(ids, (a, b) -> a.now - b.now);
		for (int i = 0; i < N; i++) {
			if (ants[i].id == coin) {
				System.out.println(ids[i].id);
				break;
			}
		}
	}

	static class Ant {
		int now, id;

		public Ant(int id, int now) {
			this.now = now;
			this.id = id;
		}
	}
}