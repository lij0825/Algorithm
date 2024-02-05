

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String srgs[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		ArrayList<Integer> ans = new ArrayList<>();

		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i < n + 1; i++) {
			q.add(i);
		}

		int idx = 1;
		int person = 0;

		while (!q.isEmpty()) {
			if (idx % k == 0) {
				person = q.poll();
				ans.add(person);
			} else {
				person = q.poll();
				q.add(person);
			}
			idx++;
		}
		sb.append("<");
		for (int i = 0; i < ans.size() - 1; i++) {
			sb.append(ans.get(i)).append(", ");
		}
		sb.append(ans.get(ans.size()-1)).append(">");
		System.out.println(sb);
	}
}
