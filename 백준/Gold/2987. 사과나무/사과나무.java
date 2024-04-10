import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static double triarea;
	static int maxy = Integer.MIN_VALUE, maxx = Integer.MIN_VALUE, miny = Integer.MAX_VALUE, minx = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int y1 = Integer.parseInt(st.nextToken());
		int x1 = Integer.parseInt(st.nextToken());
		Point p1 = new Point(y1, x1);
		maxy = Math.max(y1, maxy);
		miny = Math.min(y1, miny);
		maxx = Math.max(x1, maxx);
		minx = Math.min(x1, minx);

		st = new StringTokenizer(br.readLine());
		int y2 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		Point p2 = new Point(y2, x2);
		maxy = Math.max(y2, maxy);
		miny = Math.min(y2, miny);
		maxx = Math.max(x2, maxx);
		minx = Math.min(x2, minx);

		st = new StringTokenizer(br.readLine());
		int y3 = Integer.parseInt(st.nextToken());
		int x3 = Integer.parseInt(st.nextToken());
		Point p3 = new Point(y3, x3);
		maxy = Math.max(y3, maxy);
		miny = Math.min(y3, miny);
		maxx = Math.max(x3, maxx);
		minx = Math.min(x3, minx);

		int N = Integer.parseInt(br.readLine());

		triarea = triarea(p1, p2, p3);
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int ty = Integer.parseInt(st.nextToken());
			int tx = Integer.parseInt(st.nextToken());
			Point tp = new Point(ty, tx);
			if (ty < miny || tx < minx || ty > maxy || tx > maxx) {
				continue;
			}
			if (inarea(tp, p1, p2, p3)) {
				cnt++;
			}
		}

		System.out.println(triarea);
		System.out.println(cnt);
	}

	static boolean inarea(Point tp, Point p1, Point p2, Point p3) {
		double a1 = triarea(tp, p2, p3);
		double a2 = triarea(p1, tp, p3);
		double a3 = triarea(p1, p2, tp);
		if (a1 + a2 + a3 == triarea) {
			return true;
		}
		return false;
	}

	static double triarea(Point p1, Point p2, Point p3) {
		double half = 2.0;
		return Math.abs(p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y)) / half;
	}

	static class Point {

		int y, x;

		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

}