import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 보석
		int K = Integer.parseInt(st.nextToken()); // 가방

		Jewel[] jewel = new Jewel[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			jewel[i] = new Jewel(weight, cost);
		}

		int[] bags = new int[K];
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(bags);

		Arrays.sort(jewel, (a, b) -> a.weight - b.weight);

		long ans = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		int j = 0;
		for (int i = 0; i < K; i++) {
			while (j < N && jewel[j].weight <= bags[i]) {
				pq.offer(jewel[j].cost);
				j++;
			}
			if (!pq.isEmpty()) {
				ans += pq.poll();
			}
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
		br.close();
	}

	static class Jewel {
		int weight, cost;

		public Jewel(int weight, int cost) {
			this.weight = weight;
			this.cost = cost;
		}
	}
}