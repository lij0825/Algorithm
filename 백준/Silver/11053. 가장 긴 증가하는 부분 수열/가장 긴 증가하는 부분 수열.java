

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] length = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int k = 0; k < n; k++) {
            length[k] = 1;
            for (int i = 0; i < k; i++) {
                if (arr[i] < arr[k]) {
                    length[k] = Math.max(length[k], length[i] + 1);
                }
            }
        }
        // System.out.println("length: " + Arrays.toString(length));
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, length[i]);
        }
        System.out.println(max);
    }
}