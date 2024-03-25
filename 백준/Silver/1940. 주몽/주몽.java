import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 재료의 수
		int m = Integer.parseInt(br.readLine()); // 번호를 합쳐서 갑옷이 되는 수
		String[] input = new String[n];
		input = br.readLine().split(" ");
		int[] arr = new int[n]; // 갑옷의 고유 번호
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		int cnt = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] + arr[j] == m) {
					cnt += 1;
				}
			}
		}
		System.out.println(cnt);
	}
}