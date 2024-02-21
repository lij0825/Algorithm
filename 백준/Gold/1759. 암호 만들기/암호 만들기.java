

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        String[] str = br.readLine().split(" ");
        Arrays.sort(str); // 입력 문자열 정렬

        generatePasswords(str, l, c, 0, "");
        System.out.println(sb);
    }

    // 유효한 암호 생성
    static void generatePasswords(String[] str, int l, int c, int index, String password) {
        if (password.length() == l) {
            if (gatherCheck(password) && consonantCheck(password)) {
                sb.append(password).append("\n");
            }
            return;
        }

        // 현재 인덱스부터 재귀적으로 암호 생성
        for (int i = index; i < c; i++) {
            generatePasswords(str, l, c, i + 1, password + str[i]);
        }
    }

    // a, e, i, o, u 포함 여부 체크
    static boolean gatherCheck(String str) {
        return str.contains("a") || str.contains("e") || str.contains("i") || str.contains("o") || str.contains("u");
    }

    // a, e, i, o, u 아닌 문자 개수 체크
    static boolean consonantCheck(String str) {
        int cnt = 0;
        for (char ch : str.toCharArray()) {
            if (ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u') {
                cnt += 1;
            }
        }
        return cnt > 1;
    }
}
