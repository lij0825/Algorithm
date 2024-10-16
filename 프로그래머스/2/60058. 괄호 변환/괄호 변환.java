import java.util.*;

class Solution {

    public String solution(String p) {
        return solve(p);
    }

    static String solve(String p) {
        
        char[] w = p.toCharArray();
        
        if (w.length == 0) {
            return "";
        }

        int cnt1 = 0;
        int cnt2 = 0;
        int idx = 0;

        for (int i = 0; i < w.length; i++) {
            if (w[i] == '(') {
                cnt1++;
            } else {
                cnt2++;
            }
            if (cnt1 == cnt2) {
                idx = i + 1;
                break;
            }
        }

        char[] u = Arrays.copyOfRange(w, 0, idx);
        char[] v = Arrays.copyOfRange(w, idx, w.length);

        boolean flag = check(u);

        StringBuilder sb = new StringBuilder();
        
        if (flag) {
            sb.append(u);
            sb.append(solve(new String(v)));
            return sb.toString();
        }

        sb.append("(");
        sb.append(solve(new String(v)));
        sb.append(")");

        char[] u2 = Arrays.copyOfRange(u, 1, u.length - 1);

        for (int i = 0; i < u2.length; i++) {
            if (u2[i] == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }

        return sb.toString();
    }

    static boolean check(char[] u) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < u.length; i++) {
            char cur = u[i];
            if (cur == '(') {
                stack.push(cur);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else if (stack.peek() == '(') {
                    stack.pop();
                }
            }
        }
        return true;
    }
}
