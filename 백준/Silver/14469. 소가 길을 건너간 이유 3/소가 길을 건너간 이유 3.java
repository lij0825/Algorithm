import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Cow[] cows = new Cow[N];

		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			cows[i] = new Cow(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}

		Arrays.sort(cows, (c1, c2) -> {
			if (c1.arriveTime == c2.arriveTime) {
				return c1.checkTime - c2.checkTime;
			} else {
				return c1.arriveTime - c2.arriveTime;
			}
		});

		int time = 0;

		for (int i = 0; i < N; i++) {
			if (time < cows[i].arriveTime) {
				time = cows[i].arriveTime;
			}
			time += cows[i].checkTime;
		}

		System.out.println(time);
	}

	static class Cow {
		int arriveTime;
		int checkTime;

		public Cow(int arriveTime, int checkTime) {
			this.arriveTime = arriveTime;
			this.checkTime = checkTime;
		}
	}
}