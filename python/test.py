class Solution(object):
    def findLengthOfLCIS(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if nums == None or len(nums) == 0:
            return 0
        
        dp, res = 1, 1
        prev = nums[0]
        for num in nums[1:]:
            if num > prev:
                dp += 1
                res = max(res, dp)
            else:
                dp = 1
            prev = num
        return res