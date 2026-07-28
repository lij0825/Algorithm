class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = nums[i];
            right[i] = nums[n + i];
        }

        int[] answer = new int[2 * n];

        for (int i = 0; i < 2 * n; i++) {
            if (i % 2 == 0) {
                answer[i] = left[i / 2];
            } else {
                answer[i] = right[i / 2];
            }
        }
        return answer;
    }
}