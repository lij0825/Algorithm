import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int islandIdx, n, m;
	static int[][] square, bridge;
	static int[] dy = { 0, 1, 0, -1 }, dx = { 1, 0, -1, 0 }, parents;
	static final int INF = Integer.MAX_VALUE;
	static ArrayList<Bridge> buildBridges = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 세로
		m = Integer.parseInt(st.nextToken()); // 가로

		square = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				square[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		islandIdx = 2; // 섬 체크

		islandChk();

		bridgeMake();

		bridgeCost();

		setParents();

		getBridge();

		System.out.println(solution());

//		for (int i = 2; i < islandIdx; i++) {
//			for (int j = 2; j < islandIdx; j++) {
//				if (bridge[i][j] == INF) {
//					System.out.print("0 ");
//				} else {
//					System.out.print(bridge[i][j] + " ");
//				}
//			}
//			System.out.println();
//		}
	}

	static int solution() {
		int ans = 0;
		for (Bridge bb : buildBridges) {
			int start = bb.s;
			int end = bb.e;
			int cost = bb.cost;
			if (!union(start, end)) {
				ans += cost;
			}
		}
		return allConnect() ? ans : -1;
	}

	static boolean allConnect() {
		int a = parents[2];
		for (int i = 3; i < islandIdx; i++) {
			if (a != find(i)) {
				return false;
			}
		}
		return true;
	}

	static void setParents() {
		parents = new int[islandIdx];
		for (int i = 0; i < islandIdx; i++) {
			parents[i] = i;
		}
	}

	static int find(int x) {
		if (parents[x] == x) {
			return x;
		}
		return parents[x] = find(parents[x]);
	}

	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return true;
		}
		if (a > b) {
			parents[a] = b;
		} else {
			parents[b] = a;
		}

		return false;
	}

	// 연결 된 다리 정보 저장
	static void getBridge() {
		for (int i = 1; i < islandIdx; i++) {
			for (int j = i + 1; j < islandIdx; j++) {
				if (bridge[i][j] != INF) {
					buildBridges.add(new Bridge(i, j, bridge[i][j]));
				}
			}
		}
		Collections.sort(buildBridges, new Comparator<Bridge>() {
			@Override
			public int compare(Bridge o1, Bridge o2) {
				return o1.cost - o2.cost;
			}
		});
	}

	static class Bridge {
		int s, e, cost;

		public Bridge(int s, int e, int cost) {
			super();
			this.s = s;
			this.e = e;
			this.cost = cost;
		}

	}

	// 간선 비용 저장
	static void bridgeCost() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (square[i][j] > 1) {
					int sland = square[i][j];
					for (int k = 0; k < 4; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						if (!isIn(ny, nx) || square[ny][nx] != 0) {
							continue;
						}
						int len = 0;
						while (isIn(ny, nx)) {
							int next = square[ny][nx];
							if (sland == next) {
								break;
							}
							if (next > 0 && sland != next) {
								if (len > 1) {
									bridge[sland][next] = Math.min(bridge[sland][next], len);
									bridge[next][sland] = Math.min(bridge[next][sland], len);
									break;
								} else {
									break;
								}
							}
							ny += dy[k];
							nx += dx[k];
							len++;
						}
					}
				}
			}
		}
	}

	// 간선 저장 배열 선언
	static void bridgeMake() {
		bridge = new int[islandIdx][islandIdx];
		for (int i = 0; i < islandIdx; i++) {
			Arrays.fill(bridge[i], INF);
		}
	}

	// 섬 넘버링
	static void islandChk() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (square[i][j] == 1) {
					Queue<Pair> q = new ArrayDeque<>();
					square[i][j] = islandIdx;
					q.add(new Pair(i, j));
					while (!q.isEmpty()) {
						Pair p = q.poll();
						for (int k = 0; k < 4; k++) {
							int ny = p.y + dy[k];
							int nx = p.x + dx[k];
							if (isIn(ny, nx) && square[ny][nx] == 1) {
								q.add(new Pair(ny, nx));
								square[ny][nx] = islandIdx;
							}
						}
					}
					islandIdx++;
				}
			}
		}
	}

	static class Pair {
		int y, x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

	static boolean isIn(int y, int x) {
		return y < 0 || x < 0 || y >= n || x >= m ? false : true;
	}
}