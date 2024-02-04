import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");

		int n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);

		int sum = 0;
		int[] sums = new int[n];

		int[] nums = new int[n];
		String[] str2 = br.readLine().split(" ");
		for (int i = 0; i < str2.length; i++) {
			nums[i] = Integer.parseInt(str2[i]);
			sum += nums[i];
			sums[i] = sum;
		}

//		System.out.println(Arrays.toString(sums).replaceAll("[,\\[\\]]", ""));

		for (int i = 0; i < m; i++) {
			String[] str3 = br.readLine().split(" ");
			int s = Integer.parseInt(str3[0]);
			int e = Integer.parseInt(str3[1]);
			if (s >= 2) {
				System.out.println(sums[e - 1] - sums[s - 2]);
			} else {
				System.out.println(sums[e - 1]);
			}
		}
	}
}
