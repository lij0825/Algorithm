import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] arr, copy;
	static int a1 = 1, b1 = 1, a2 = 1, b2 = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		copyarr();
		findfront();
		change(a1, b1);
		if (chkcan()) {
			System.out.println(a1 + " " + b1 + "\n" + b2 + " " + a2);

		} else {
			findback();
			change(a2, b2);
			if (chkcan()) {
				System.out.println(a1 + " " + b1 + "\n" + b2 + " " + a2);
			} else {
				reset();
				copyarr();
				findback();
				change(a2, b2);
				if (chkcan()) {
					System.out.println(b2 + " " + a2 + "\n" + a1 + " " + b1);
				} else {
					findfront();
					System.out.println(b2 + " " + a2 + "\n" + a1 + " " + b1);
				}
			}
		}

	}

	static void reset() {
		a1 = 1;
		b1 = 1;
		a2 = 1;
		b2 = 1;
	}

	static void findfront() {

		for (int i = 1; i <= N; i++) {
			if (copy[i] != i) {
				a1 = i;
				break;
			}
		}

		for (int i = 1; i <= N; i++) {
			if (copy[i] == a1) {
				b1 = i;
				break;
			}
		}

	}

	static void findback() {

		for (int i = N; i > 0; i--) {
			if (copy[i] != i) {
				a2 = i;
				break;
			}
		}

		for (int i = N; i > 0; i--) {
			if (copy[i] == a2) {
				b2 = i;
				break;
			}
		}

	}

	static void change(int a, int b) {
		if (a > b) {
			int temp = a;
			a = b;
			b = temp;
		}
		while (a < b) {
			int temp = copy[a];
			copy[a] = copy[b];
			copy[b] = temp;
			a++;
			b--;
		}
	}

	static boolean chkcan() {
		for (int i = 1; i <= N; i++) {
			if (copy[i] != i) {
				return false;
			}
		}
		return true;
	}

	static void copyarr() {
		copy = new int[N + 1];
		copy = arr.clone();
	}

}