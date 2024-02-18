import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int lower = 0; // 낮은값
        int upper = 0; // 높은값
        int[] arr = new int[n]; // 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            upper = Math.max(upper, arr[i]);
        }
        int max = Integer.parseInt(br.readLine()); // 최대
        while (lower <= upper) {
            int mid = (lower + upper) / 2; // 중간값, 상한액
            long best = 0; // 중간값(상한액)을 전부 더한 값
            for (int i = 0; i < n; i++) {
                if (arr[i] > mid) { // 중간보다 크면
                    best += mid; // 중간값을 더한다
                } else { // 중간보다 작거나 같으면
                    best += arr[i]; // 해당값을 더한다
                }
            }
            if (best <= max) { // 배정액이 최대값보다 작거나 같으면
                lower = mid + 1;
            } else { // 배정액이 최대값보다 크면
                upper = mid - 1;
            }
        }
        System.out.println(upper);
    }
}