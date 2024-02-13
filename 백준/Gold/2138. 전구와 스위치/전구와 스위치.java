

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] switches = new int[n];
        String[] state = br.readLine().split("");
        for (int i = 0; i < n; i++) {
            switches[i] = Integer.parseInt(state[i]); // 스위치 초기값
        }

        int[] chgSwitches = new int[n];
        state = br.readLine().split("");
        for (int i = 0; i < n; i++) {
            chgSwitches[i] = Integer.parseInt(state[i]); // 바꿔야하는 스위치 초기값
        }

        if (Arrays.toString(chgSwitches).equals(Arrays.toString(switches))) { // 바꾸기 전에 두 스위치가 같으면 0번
            System.out.println(0);
            System.exit(0);
        }

        int stOn = firstOn(switches.clone(), chgSwitches.clone());
        int stOff = firstOff(switches.clone(), chgSwitches.clone());
        if (stOn != -1 && stOff != -1) {
            System.out.println(stOn < stOff ? stOn : stOff);
        } else if (stOn != -1) {
            System.out.println(stOn);
            System.exit(0);
        } else if (stOff != -1) {
            System.out.println(stOff);
            System.exit(0);
        } else {
            System.out.println(-1);
            System.exit(0);
        }

    }

    static int firstOn(int[] switches, int[] chgSwitches) { // 맨 처음 스위치를 눌렀을때
        switches[0] = (switches[0] == 1 ? 0 : 1);
        switches[1] = (switches[1] == 1 ? 0 : 1);

        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if (switches[i - 1] == chgSwitches[i - 1]) {
                continue;
            }
            cnt++;
            switches[i - 1] = (switches[i - 1] == 1 ? 0 : 1);
            switches[i] = (switches[i] == 1 ? 0 : 1);
            if (i + 1 < n) {
                switches[i + 1] = (switches[i + 1] == 1 ? 0 : 1);
            }
        }
        return Arrays.toString(chgSwitches).equals(Arrays.toString(switches)) ? cnt : -1;

    }

    static int firstOff(int[] switches, int[] chgSwitches) {
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            if (switches[i - 1] == chgSwitches[i - 1]) {
                continue;
            }
            cnt++;
            switches[i - 1] = (switches[i - 1] == 1 ? 0 : 1);
            switches[i] = (switches[i] == 1 ? 0 : 1);
            if (i + 1 < n) {
                switches[i + 1] = (switches[i + 1] == 1 ? 0 : 1);
            }
        }
        return Arrays.toString(chgSwitches).equals(Arrays.toString(switches)) ? cnt : -1;
    }
}