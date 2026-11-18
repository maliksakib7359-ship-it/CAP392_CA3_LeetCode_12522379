class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Place each valid positive number x (1 <= x <= n) at index x - 1
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int correctIdx = nums[i] - 1;
                int temp = nums[i];
                nums[i] = nums[correctIdx];
                nums[correctIdx] = temp;
            }
        }

        // The first index i where nums[i] != i + 1 gives the missing positive integer
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // If all integers 1..n are present, the answer is n + 1
        return n + 1;
    }
}