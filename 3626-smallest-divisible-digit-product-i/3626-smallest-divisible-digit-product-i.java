class Solution {
    public int smallestNumber(int n, int t) {
        int answer = 0;
        while (n < 1000) {
            String[] num = String.valueOf(n).split("");

            int multiple = 1;
            for (int i = 0; i < num.length; i++) {
                multiple *= Integer.parseInt(num[i]);
            }

            if (multiple % t == 0) {
                answer = n;
                break;
            }
            n++;
        }

        return answer;
    }
}