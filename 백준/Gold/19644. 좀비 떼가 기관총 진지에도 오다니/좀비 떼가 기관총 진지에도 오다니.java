

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int l = Integer.parseInt(br.readLine()); 
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ml = Integer.parseInt(st.nextToken()); 
        int mk = Integer.parseInt(st.nextToken()); 
        int c = Integer.parseInt(br.readLine()); 
        int[] Z = new int[l];

        for (int i = 0; i < l; i++) {
            Z[i] = Integer.parseInt(br.readLine()); 
        }


        Deque<Integer> que = new ArrayDeque<>();

        int cnt = 0;
        boolean sur = true;


        for (int i = 0; i < Math.min(ml, l); i++) {
            if (cnt == 0) { 
                if (Z[i] - mk * (i + 1) <= 0) { 
                    que.add(0);
                } else {
                    que.add(Z[i] - mk * (i + 1)); 
                    cnt += 1;
                }
            } else { 
                if (Z[i] - mk * (i + 1 - cnt) <= 0) { 
                    que.add(0);
                } else {
                    que.add(Z[i] - mk * (i + 1 - cnt)); 
                    cnt += 1;
                }
            }
        }

        for (int i = ml; i < l; i++) {
            if (que.peekFirst() == 0) { 
                que.pollFirst(); 
                if (Z[i] - mk * (ml - cnt) <= 0) {
                    que.add(0);
                } else { 
                    que.add(Z[i] - mk * (ml - cnt));
                    cnt += 1;
                }
            } else { 
                que.pollFirst();
                if (c > 0) { 
                    c -= 1; 
                } else { 
                    sur = false; 
                    break;
                }
                if (Z[i] - mk * (ml - cnt) <= 0) { 
                    que.add(0);
                    cnt -= 1;
                } else {
                    que.add(Z[i] - mk * (ml - cnt));
                }
            }
        }

        if (sur) {
            while (!que.isEmpty()) {
                if (que.peekFirst() == 0) {
                    que.pollFirst();
                } else { 
                    que.pollFirst();
                    if (c > 0) {
                        c -= 1;
                    } else {
                        sur = false; 
                        break;
                    }
                }
            }
        }

        if (sur) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
