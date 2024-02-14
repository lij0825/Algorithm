import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int r;
	static int c;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] Nrc = br.readLine().split(" ");
		N = Integer.parseInt(Nrc[0]);
		r = Integer.parseInt(Nrc[1]);
		c = Integer.parseInt(Nrc[2]);

		int temp = (int) Math.pow(2, N);
		Zloop(r, c, temp);
		System.out.println(cnt);
	}

	public static void Zloop(int r, int c, int temp) {
		int halfN = temp / 2;
		if (temp >= 1) {
			if (r >= halfN && c >= halfN) {
				cnt += halfN * halfN * 3;
			} else if (r >= halfN && c < halfN) {
				cnt += halfN * halfN * 2;
			} else if (r < halfN && c >= halfN) {
				cnt += halfN * halfN;
			}
			if (r >= halfN && c >= halfN) {
				Zloop(r - halfN, c - halfN, halfN);
			}else if (r < halfN && c >= halfN) {
				Zloop(r, c - halfN, halfN);
			}else if (r >= halfN && c < halfN) {
				Zloop(r - halfN, c, halfN);
			}else if (r < halfN && c < halfN) {
				Zloop(r, c, halfN);
			}
		}
		else {
			return;
		}
	}
}