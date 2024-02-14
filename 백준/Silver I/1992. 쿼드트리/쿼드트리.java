

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str[j]);
            }
        }

        loop(0, 0, n);
    }

    static void loop(int y, int x, int depth) {
        if (depth == 1) {
            System.out.print(map[y][x]);
            return;
        }
        boolean one = true;
        boolean zero = true;
        for (int i = y; i < y + depth; i++) {
            for (int j = x; j < x + depth; j++) {
                if (map[i][j] == 1) {
                    zero = false;
                }
                if (map[i][j] == 0) {
                    one = false;
                }
                if (!one && !zero) {
                    break;
                }
            }
        }
        if (one) {
            System.out.print(1);
        } else if (zero) {
            System.out.print(0);
        } else {
            System.out.print("(");
            loop(y, x, depth / 2); // 1
            loop(y, x + depth / 2, depth / 2); // 2
            loop(y + depth / 2, x, depth / 2); // 3
            loop(y + depth / 2, x + depth / 2, depth / 2); // 4
            System.out.print(")");
        }
    }
}