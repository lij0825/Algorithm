
import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


public class Main {

    private static int n, m, k;
    private static int sx, sy, ex, ey;
    private static int[][] origin;
    private static int[][] rolled;
    private static int[][] rolling;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        origin = new int[n + 1][m + 1]; // 원본배열

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rolling = new int[k][3]; // 돌리기 명령 초기화

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            rolling[i][0] = Integer.parseInt(st.nextToken());
            rolling[i][1] = Integer.parseInt(st.nextToken());
            rolling[i][2] = Integer.parseInt(st.nextToken());
        }

        loop(0, new int[k], new boolean[k]);

        StringBuilder sb = new StringBuilder();
        sb.append(min);

        System.out.println(sb.toString());
    }

    // 재귀로 회전하는 순서 경우의 수
    private static void loop(int depth, int[] ratation, boolean[] visited) {
        if (depth == k) {
            rollingArr(ratation); // 회전 순서정해진대로 돌림
            return;
        }
        // 방문 체크
        for (int i = 0; i < k; i++) {
            if (!visited[i]) {
                visited[i] = true;
                ratation[depth] = i;
                loop(depth + 1, ratation, visited);
                visited[i] = false;
            }
        }
    }

    // 배열 돌리기
    private static void rollingArr(int[] rotation) {
        rolled = new int[n + 1][m + 1];
        // 초기 배열을 복사하여 rolled에 저장
        for (int i = 1; i <= n; i++) {
            System.arraycopy(origin[i], 0, rolled[i], 0, m + 1);
        }

        for (int index : rotation) {
            rotation(rolling[index]);
        }

        for (int i = 1; i <= n; i++) {
            int rowSum = 0;
            for (int j = 1; j <= m; j++) {
                rowSum += rolled[i][j];
            }
            min = Math.min(min, rowSum);
        }
    }

    // 들어온 배열 연산을 실행함
    private static void rotation(int[] rotation) {
        int r = rotation[0];
        int c = rotation[1];
        int s = rotation[2];

        ey = r + s;
        ex = c + s;
        sy = r - s;
        sx = c - s;

        int a = (ex - sx + 1) / 2;
        int b = (ey - sy + 1) / 2;

        for (int j = 0; j < Math.min(a, b); j++) {
            if (sx >= ex && sy >= ey) {
                break;
            }

            ArrayDeque<Integer> list = new ArrayDeque<>();
            rollLoop(sx, sy, sx, sy, list);

            list.addFirst(list.pollLast());

            rolledArr(sx, sy, sx, sy, list);
            // 안쪽으로 파고 들기 위해 범위 조정
            sx++;
            sy++;
            ex--;
            ey--;
        }
    }

    // 돌리면서 큐에 담음
    private static void rollLoop(int x1, int y1, int x2, int y2, ArrayDeque<Integer> q) {
        if (!q.isEmpty() && x1 == x2 && y1 == y2) {
            return;
        }
        if (y1 == sy && x1 < ex) {
            q.add(rolled[y1][x1]);
            rollLoop(x1 + 1, y1, x2, y2, q);
        } else if (x1 == ex && y1 < ey) {
            q.add(rolled[y1][x1]);
            rollLoop(x1, y1 + 1, x2, y2, q);
        } else if (y1 == ey && x1 > sx) {
            q.add(rolled[y1][x1]);
            rollLoop(x1 - 1, y1, x2, y2, q);
        } else if (x1 == sx && y1 > sy) {
            q.add(rolled[y1][x1]);
            rollLoop(x1, y1 - 1, x2, y2, q);
        }
    }

    // 큐에 있던걸로 배열에 배치
    private static void rolledArr(int x1, int y1, int x2, int y2, ArrayDeque<Integer> q) {
        if (q.isEmpty() && x1 == x2 && y1 == y2) {
            return;
        }
        if (y1 == sy && x1 < ex) {
            rolled[y1][x1] = q.poll();
            rolledArr(x1 + 1, y1, x2, y2, q);
        } else if (x1 == ex && y1 < ey) {
            rolled[y1][x1] = q.poll();
            rolledArr(x1, y1 + 1, x2, y2, q);
        } else if (y1 == ey && x1 > sx) {
            rolled[y1][x1] = q.poll();
            rolledArr(x1 - 1, y1, x2, y2, q);
        } else if (x1 == sx && y1 > sy) {
            rolled[y1][x1] = q.poll();
            rolledArr(x1, y1 - 1, x2, y2, q);
        }
    }
}
