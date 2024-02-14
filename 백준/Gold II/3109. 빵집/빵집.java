
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r;
    static int c;
    static boolean[][] pipe;
    static String[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        // 파이프 체크
        pipe = new boolean[r][c];
        // 맵 입력
        map = new String[r][c];
        for (int i = 0; i < r; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < c; j++) {
                map[i][j] = str[j];
            }
        }
        int ans = 0;
        for (int i = 0; i < r; i++) {
            if (loop(i, 0)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static boolean loop(int y, int x) {
        if (map[y][x].equals("x") || pipe[y][x]) {
            return false;
        }
        if (x == c - 1) {
            return true;
        }

        int[] dy = { -1, 0, 1 };
        int[] dx = { 1, 1, 1 };
        pipe[y][x] = true;
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= c || ny >= r || map[ny][nx].equals("X") || pipe[ny][nx]) {
                continue;
            }
            if (loop(ny, nx)) {
                return true;
            }

        }
        return false;
    }
}