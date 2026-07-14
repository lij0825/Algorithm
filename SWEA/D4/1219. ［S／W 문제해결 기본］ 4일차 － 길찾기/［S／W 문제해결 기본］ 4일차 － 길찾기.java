import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static boolean[] visited;
	static ArrayList<Integer>[] roads = new ArrayList[100];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {

			visited = new boolean[100];

			st = new StringTokenizer(br.readLine());

			int T = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());

			for (int i = 0; i < 100; i++) {
				roads[i] = new ArrayList<Integer>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				roads[start].add(end);
			}

			int result = 0;
			if (dfs(0)) {
				result = 1;
			}

			bw.write("#" + T + " " + result + "\n");
		}

		bw.flush();
		bw.close();

	}

	static boolean dfs(int now) {
		if (now == 99) {
			return true;
		}
		if (visited[now]) {
			return false;
		}
		visited[now] = true;

		for (int road : roads[now]) {
			if (dfs(road)) {
				return true;
			}
		}

		return false;

	}

}
