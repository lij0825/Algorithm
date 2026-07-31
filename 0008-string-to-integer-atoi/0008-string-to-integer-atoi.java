class Solution {
    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) {
            return 0;
        }
        int i = 0;
        int n = chars.length;
        int sign = 1;
        long result = 0;

        while (i < n && chars[i] == ' ') {
            i++;
        }

        if (i < n && (chars[i] == '+' || chars[i] == '-')) {
            if (chars[i] == '-') {
                sign = -1; 
            }
            i++;
        }

        while (i < n && chars[i] >= '0' && chars[i] <= '9') {
            int digit = chars[i] - '0';
            result = result * 10 + digit;

            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;  
            }

            i++;
        }

        return (int) (result * sign);
    }

}