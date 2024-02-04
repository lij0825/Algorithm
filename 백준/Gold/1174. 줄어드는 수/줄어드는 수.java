
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Long> decre = new ArrayList<>();
		int n = Integer.parseInt(br.readLine());
		int[] nums = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

		for (int i = 0; i < 1 << 10; i++) {
			long num = 0;
			for (int j = 0; j < 10; j++) {
				if ((i & (1 << j)) > 0) {
					num = num * 10 + nums[j];
				}
			}
			decre.add(num);
		}
		Collections.sort(decre);
		if (decre.size() <= n) {
			System.out.println(-1);
		} else {
			System.out.println(decre.get(n));
		}
	}
}
