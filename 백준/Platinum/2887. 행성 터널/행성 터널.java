import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//  min(|xA-xB|, |yA-yB|, |zA-zB|) 거리비용
public class Main {

	static int[] par;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 행성 개수

		Planet[] planet = new Planet[N];

		par = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			par[i] = i;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			planet[i] = new Planet(x, w, z, i);
		}

		PriorityQueue<Vector> vpq = new PriorityQueue<Vector>((a, b) -> a.cost - b.cost);
		// ------------------------------------------------------------------------------------------------
		Arrays.sort(planet, (a, b) -> a.x - b.x);
		for (int i = 0; i < N - 1; i++) {
			int s = i;
			int e = i + 1;
			int cost = Math.min(Math.abs(planet[s].x - planet[e].x),
					Math.min(Math.abs(planet[s].y - planet[e].y), Math.abs(planet[s].z - planet[e].z)));
			vpq.add(new Vector(planet[s].id, planet[e].id, cost));

		}
		// ------------------------------------------------------------------------------------------------
		Arrays.sort(planet, (a, b) -> a.y - b.y);
		for (int i = 0; i < N - 1; i++) {
			int s = i;
			int e = i + 1;
			int cost = Math.min(Math.abs(planet[s].x - planet[e].x),
					Math.min(Math.abs(planet[s].y - planet[e].y), Math.abs(planet[s].z - planet[e].z)));
			vpq.add(new Vector(planet[s].id, planet[e].id, cost));

		}
		// ------------------------------------------------------------------------------------------------
		Arrays.sort(planet, (a, b) -> a.z - b.z);
		for (int i = 0; i < N - 1; i++) {
			int s = i;
			int e = i + 1;
			int cost = Math.min(Math.abs(planet[s].x - planet[e].x),
					Math.min(Math.abs(planet[s].y - planet[e].y), Math.abs(planet[s].z - planet[e].z)));
			vpq.add(new Vector(planet[s].id, planet[e].id, cost));

		}
		// ------------------------------------------------------------------------------------------------
		int cnt = 0;
		int sum = 0;
		while (cnt < N && !vpq.isEmpty()) {
			Vector v = vpq.poll();
			int s = v.start;
			int e = v.end;
			int c = v.cost;
			if (union(s, e)) {
				continue;
			}

			par[find(e)] = find(s);

			sum += c;
			cnt++;
		}

		System.out.println(sum);

	}

	// 연결 되어있는지 확인
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) {
			return true;
		}
		return false;
	}

	// 부모 찾기
	static int find(int x) {
		if (par[x] == x) {
			return x;
		}
		return par[x] = find(par[x]);
	}

	static class Planet {
		int x, y, z, id;

		public Planet(int x, int y, int z, int id) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.id = id;
		}

		@Override
		public String toString() {
			return "Planet [x=" + x + ", y=" + y + ", z=" + z + ", id=" + id + "]\n";
		}

	}

	static class Vector {

		int start, end, cost;

		public Vector(int start, int end, int cost) {
			super();
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Vector [start=" + start + ", end=" + end + ", cost=" + cost + "]\n";
		}

	}
}