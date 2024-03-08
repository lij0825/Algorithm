import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		for (int t = 0; t < tc; t++) {
			int n = Integer.parseInt(br.readLine());
			Pair[] locaton = new Pair[n + 2];
			boolean[] visited = new boolean[n + 2];
			Queue<Pair> q = new LinkedList<Pair>();
			boolean res = false;
			String[] str;
			for (int i = 0; i < n + 2; i++) {
				str = br.readLine().split(" ");
				locaton[i] = new Pair(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
			}

			Pair start = locaton[0];
			Pair end = locaton[n + 1];
			q.add(start);

			while (!q.isEmpty()) {
				Pair p = q.poll();
				if (p.equals(end)) {
					res = true;
					break;
				}
				for (int i = 1; i < n + 2; i++) {
					if (!visited[i]
							&& Math.abs(p.x - locaton[i].x) + Math.abs(p.y - locaton[i].y) <= 1000) {
						q.add(locaton[i]);
						visited[i] = true;
					}
				}
			}
			if (res) {
				sb.append("happy");
			} else {
				sb.append("sad");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

class Pair {
	int x;
	int y;

	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}