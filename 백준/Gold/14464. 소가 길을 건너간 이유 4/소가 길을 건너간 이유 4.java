import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		int C = Integer.parseInt(input[0]);
		int N = Integer.parseInt(input[1]);

		int[] chicken = new int[C];

		for (int i = 0; i < C; i++) {
			chicken[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(chicken);

		PriorityQueue<Cow> pq = new PriorityQueue<>((a, b) -> {
			if (a.end == b.end) {
				return a.start - b.start;
			}
			return a.end - b.end;
		});

		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			pq.add(new Cow(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
		}

		int answer = 0;

		while (!pq.isEmpty()) {
			Cow cow = pq.poll();

			for (int i = 0; i < C; i++) {
				if (chicken[i] >= cow.start && chicken[i] <= cow.end) {
					answer++;
					chicken[i] = -1;
					break;
				}
			}
		}

		System.out.println(answer);
	}

	static class Cow {
		int start, end;

		public Cow(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}