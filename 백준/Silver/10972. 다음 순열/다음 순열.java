

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] prep = new int[n];
		for (int i = 0; i < n; i++) {
			prep[i] = Integer.parseInt(st.nextToken());
		}

		if (np(prep)) {
			for (int i : prep) {
				sb.append(i).append(" ");
			}
		} else {
			sb.append("-1 ");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}

	static boolean np(int[] p) {
		int n = p.length;
		int i = n - 1;
		while (i > 0 && p[i - 1] >= p[i]) {
			--i;
		}
		if (i == 0) {
			return false;
		}
		int j = n - 1;
		while (p[i - 1] >= p[j]) {
			--j;
		}
		swap(p, i - 1, j);
		int k = n - 1;
		while (i < k) {
			swap(p, i++, k--);
		}
		return true;
	}

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
