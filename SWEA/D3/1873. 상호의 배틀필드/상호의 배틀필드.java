import java.io.*;
import java.util.*;

/**
 * D3_1873_상호의배틀필드
 */
class Solution {

    static boolean[][] iron;
    static boolean[][] brick;
    static boolean[][] water;
    static String[][] ground;
    static String[] order;
    static int N, H, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine()); // 테스트케이스 개수
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken()); // 세로크기
            W = Integer.parseInt(st.nextToken()); // 가로크기
            iron = new boolean[H][W]; // 강철벽 위치 확인용
            brick = new boolean[H][W]; // 벽돌위치 확인
            water = new boolean[H][W]; // 물 위치 확인
            // 초기 땅 입력 --------------------------------------------------------
            ground = new String[H][W];
            for (int i = 0; i < H; i++) {
                String[] str = br.readLine().split("");
                for (int j = 0; j < W; j++) {
                    ground[i][j] = str[j];
                }
            }
            groundChk();
            // --------------------------------------------------------------------
            N = Integer.parseInt(br.readLine()); // 명령 개수
            order = br.readLine().split(""); // 명령

            for (int i = 0; i < N; i++) {
                act(order[i]);
            }
            sb.append("#" + t + " ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(ground[i][j]);
                }
                sb.append("\n");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    // 전차 좌표
    static int[] find() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (ground[i][j].equals("^") || ground[i][j].equals("v")
                        || ground[i][j].equals("<") || ground[i][j].equals(">")) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    // 명령 수행
    static void act(String order) {
        int y = find()[0];
        int x = find()[1];
        if (order.equals("S")) { // 발사
            if (ground[y][x].equals("^")) { // 위 (-1,0)
                shot(y, x, -1, 0);
            }
            if (ground[y][x].equals("v")) { // 아래 (1,0)
                shot(y, x, 1, 0);
            }
            if (ground[y][x].equals("<")) { // 왼쪽 (0,-1)
                shot(y, x, 0, -1);
            }
            if (ground[y][x].equals(">")) { // 오른쪽 (0,1)
                shot(y, x, 0, 1);
            }
        }
        if (order.equals("U")) { // 위 (-1,0)
            move(y, x, -1, 0, "^");
        }
        if (order.equals("D")) { // 아래 (1,0)
            move(y, x, 1, 0, "v");
        }
        if (order.equals("L")) { // 왼쪽 (0,-1)
            move(y, x, 0, -1, "<");
        }
        if (order.equals("R")) { // 오른쪽 (0,1)
            move(y, x, 0, 1, ">");
        }
    }

    // 갈수있는지 없는 강철벽, 벽돌벽, 물웅덩이 확인
    static void groundChk() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (ground[i][j].equals("#")) {
                    iron[i][j] = true;
                } else if (ground[i][j].equals("*")) {
                    brick[i][j] = true;
                } else if (ground[i][j].equals("-")) {
                    water[i][j] = true;
                }
            }
        }
    }

    // 발사
    static void shot(int y, int x, int dy, int dx) {
        int ny = y + dy;
        int nx = x + dx;
        if (ny < 0 || nx < 0 || ny >= H || nx >= W) {
            return;
        }
        if (iron[ny][nx]) {
            return;
        }
        if (brick[ny][nx]) {
            ground[ny][nx] = ".";
            brick[ny][nx] = false;
            return;
        }
        shot(ny, nx, dy, dx);
    }

    // 움직일수있으면 움직임
    static void move(int y, int x, int dy, int dx, String arrow) {
        int ny = y + dy;
        int nx = x + dx;
        if (ny < 0 || nx < 0 || ny >= H || nx >= W) {
            ground[y][x] = arrow;
            return;
        }
        if (iron[ny][nx]) {
            ground[y][x] = arrow;
            return;
        }
        if (brick[ny][nx]) {
            ground[y][x] = arrow;
            return;
        }
        if (water[ny][nx]) {
            ground[y][x] = arrow;
            return;
        }
        ground[y][x] = ".";
        ground[ny][nx] = arrow;
    }
}