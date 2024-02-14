import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 이인준
 */
public class Main {
    static long maxHp = 0;
    static long n, atk;
    static long saveHp = Long.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken()); // 방 개수
        atk = Long.parseLong(st.nextToken()); // 공격력

        long type = 0; // 1과 2
        long a = 0;
        long h = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            type = Long.parseLong(st.nextToken());
            a = Long.parseLong(st.nextToken());
            h = Long.parseLong(st.nextToken());
            if (type == 1) {
                battle(a, h);
            }
            if (type == 2) {
                potion(a, h);
            }
        }
        long fisrt = 1;
        long minHp = Math.max(saveHp, maxHp) + fisrt;
        System.out.println(minHp);
    }

    // 전투 type = 1 몬스터 공격력,몬스터 체력 ,용사 공격력
    static void battle(long a, long h) {
        long cnt = 0;
        if (h > atk) {
            if (h % atk == 0) {
                cnt = (h / atk) - 1;
            } else {
                cnt = h / atk;
            }
        }
        maxHp += cnt * a; // 전투 진행하면서 필요한 체력 저장해둠
    }

    // 포션방 type = 2 공격력 증가량, 회복량
    static void potion(long a, long h) {
        atk += a;
        saveHp = Math.max(saveHp, maxHp); // 회복하기 전까지 필요한 체력 저장

        maxHp = Math.max(0, maxHp - h); // 회복량만큼 필요체력에서 뺌
    }
}
