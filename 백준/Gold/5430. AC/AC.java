import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			String[] order = br.readLine().split("");

			int N = Integer.parseInt(br.readLine());

			String[] str = br.readLine().split(",");

			Deque<Integer> dq = new ArrayDeque<>();
			Deque<Integer> temp = new ArrayDeque<>();

			for (int i = 0; i < str.length; i++) {
				String strchg = str[i].replaceAll("[^0-9]", "");
				if (strchg.equals("")) {
					continue;
				}
				dq.add(Integer.parseInt(strchg));
			}
			boolean errorflag = false;
			boolean flag = false;
			for (int i = 0; i < order.length; i++) {
				if (order[i].equals("R")) {
					if (flag) {
						flag = false;
					} else {
						flag = true;
					}
				}
				if (order[i].equals("D")) {
					if (dq.isEmpty()) {
						sb.append("error");
						errorflag = true;
						break;
					}
					if (!flag) {
						dq.pollFirst();
					} else {
						dq.pollLast();
					}

				}
			}
			if (!errorflag) {
				boolean delchar = false;
				sb.append("[");
				if (!flag) {
					while (!dq.isEmpty()) {
						delchar = true;
						sb.append(dq.pollFirst()).append(",");
					}
				} else {
					while (!dq.isEmpty()) {
						delchar = true;
						sb.append(dq.pollLast()).append(",");
					}
				}
				if (delchar) {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append("]");
			}
			sb.append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}