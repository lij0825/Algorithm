class Solution {
    public int missingInteger(int[] nums) {

        int N = nums.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                sum += nums[i];
            } else if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else if (nums[i] != nums[i - 1] + 1) {
                break;
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(nums[i]);
        }

        while (set.contains(sum)) {
            sum++;
        }

        return sum;
    }
}