import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());

		ArrayList<Long> weights;
		long max, min;

		for (int tc = 1; tc <= TC; tc++) {

			min = 0;
			max = 0;

			weights = new ArrayList<Long>();

			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N * (N - 1) / 2; i++) {
				weights.add(Long.parseLong(st.nextToken()));
			}

			weights.sort((a, b) -> a.compareTo(b));

			for (int i = 0; i < N - 1; i++) {
				min += weights.get(i);
			}

			int index = 0;
			int gap = 1;

			for (int i = 0; i < N - 1; i++) {
				max += weights.get(index);
				index += gap;
				gap++;
			}

			bw.write(min + " " + max + "\n");
		}

		bw.flush();
		bw.close();
	}

}
