import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M;
	static int[] arr1, arr2;
	static List<Integer> ans = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr1 = new int[N];

		int max = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr1[i]);
		}

		M = Integer.parseInt(br.readLine());
		arr2 = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}

		find(0, 0);

		sb.append(ans.size()).append("\n");
		for (int i = 0; i < ans.size(); i++) {
			sb.append(ans.get(i)).append(" ");
		}
		System.out.println(sb);
	}

	static void find(int idx1, int idx2) {
		if (idx1 >= N || idx2 >= M) {
			return;
		}

		int maxNum = findMax(idx1, idx2);

		if (maxNum == 0) {
			return;
		}

		int maxIdx1 = 0;
		int maxIdx2 = 0;

		for (int i = idx1; i < N; i++) {
			if (arr1[i] == maxNum) {
				maxIdx1 = i;
				break;
			}
		}

		for (int i = idx2; i < M; i++) {
			if (arr2[i] == maxNum) {
				maxIdx2 = i;
				break;
			}
		}

		ans.add(maxNum);

		find(maxIdx1 + 1, maxIdx2 + 1);
	}

	static int findMax(int idx1, int idx2) {
		int[] array1 = Arrays.copyOfRange(arr1, idx1, arr1.length);
		int[] array2 = Arrays.copyOfRange(arr2, idx2, arr2.length);
		Arrays.sort(array1);
		Arrays.sort(array2);

		int max = 0;
		for (int i = array1.length - 1; i >= 0; i--) {
			for (int j = array2.length - 1; j >= 0; j--) {
				if (array1[i] == array2[j]) {
					max = array1[i];
					return max;
				}
			}
		}
		return max;
	}
}