class Solution {
    public String smallestNumber(String num, long t) {
        long temp = t;
        for (int p : new int[] { 2, 3, 5, 7 }) {
            while (temp % p == 0)
                temp /= p;
        }
        if (temp > 1)
            return "-1";

        int n = num.length();

        long[] prefixRem = new long[n + 1];
        prefixRem[0] = t;
        int firstZero = n;

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (c == '0') {
                firstZero = i;
                break;
            }
            prefixRem[i + 1] = prefixRem[i] / gcd(prefixRem[i], c - '0');
        }

        if (firstZero == n && prefixRem[n] == 1) {
            return num;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i > firstZero)
                continue;

            int startDigit = num.charAt(i) - '0' + 1;
            for (int d = startDigit; d <= 9; d++) {
                long nextRem = prefixRem[i] / gcd(prefixRem[i], d);
                int remLen = n - 1 - i;

                if (minDigits(nextRem) <= remLen) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i).append(d);
                    fillGreedy(sb, remLen, nextRem);
                    return sb.toString();
                }
            }
        }

        int targetLen = Math.max(n + 1, minDigits(t));
        StringBuilder sb = new StringBuilder();
        fillGreedy(sb, targetLen, t);
        return sb.toString();
    }

    private int minDigits(long t) {
        if (t <= 1)
            return 0;

        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        while (t % 2 == 0) {
            c2++;
            t /= 2;
        }
        while (t % 3 == 0) {
            c3++;
            t /= 3;
        }
        while (t % 5 == 0) {
            c5++;
            t /= 5;
        }
        while (t % 7 == 0) {
            c7++;
            t /= 7;
        }

        int min23 = 1000;
        for (int x = 0; x <= Math.min(c2, c3); x++) {
            int d2 = (c2 - x + 2) / 3;
            int d3 = (c3 - x + 1) / 2; 
            min23 = Math.min(min23, x + d2 + d3);
        }

        return c7 + c5 + min23;
    }

    private void fillGreedy(StringBuilder sb, int len, long remT) {
        long currT = remT;
        for (int pos = 0; pos < len; pos++) {
            int remPos = len - 1 - pos;
            for (int d = 1; d <= 9; d++) {
                long nextT = currT / gcd(currT, d);
                if (minDigits(nextT) <= remPos) {
                    sb.append(d);
                    currT = nextT;
                    break;
                }
            }
        }
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}