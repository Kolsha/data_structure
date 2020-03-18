class Solution(object):
    def isValid(s):
        """
        check if String s is a vilid parentheses string
        """
        count = 0
        for i in range(0, len(s)):
            if s[i] == '(':
                count += 1
            elif s[i] == ')':
                if count == 0:
                    return False
                count -= 1
        return count == 0

    def removeInvalidParentheses(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        def isValid(s):
            """
            check if String s is a vilid parentheses string
            """
            count = 0
            for i in range(0, len(s)):
                if s[i] == '(':
                    count += 1
                elif s[i] == ')':
                    if count == 0:
                        return False
                    count -= 1
            return count == 0

        res = []

        visited = set()  # set for visited
        queue = []      # list for bfs queue

        queue.append(s)
        visited.add(s)
        found = False
        while len(queue) != 0:
            str = queue.pop(0)
            if isValid(str):
                res.append(str)
                found = True

            if found:
                continue

            for i in range(0, len(str)):
                if str[i] != '(' and str[i] != ')':
                    continue

                nextStr = str[0:i] + str[i+1:]
                if nextStr not in visited:
                    queue.append(nextStr)
                    visited.add(nextStr)
        return res