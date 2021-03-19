//dp，时间复杂度O(n^3/2)
class Solution {
    public int numSquares(int n) {
        List<Integer> squareNums = new ArrayList<>();
        int i = 1;
        while (i * i <= n) {
            squareNums.add(i * i);
            i++;
        }
        int []dp = new int[n + 1];
        for (i = 0;i < n + 1;i++) {
            dp[i] = 10;
        }
        dp[0] = 0;
        for (i = 1;i < n + 1;i++) {
            for (int j : squareNums) {
                if (j > i) break;
                dp[i] = Math.min(dp[i], dp[i - j] + 1);
            }
        }
        return dp[n];
    }
}

//DFS+贪心，复杂度O(h/2)
class Solution {
    Set<Integer> squareNum;
    public int numSquares(int n) {
        squareNum = new HashSet<>();
        int i = 1;
        while (i * i <= n) {
            squareNum.add(i * i);
            i += 1;
        }
        for (i = 1;i <= n;i++) {
            if (is_divided(n, i)) {
                return i;
            }
        }
        return 4;
    }

    private boolean is_divided(int n, int count) {
        if (count == 1) {
            return squareNum.contains(n);
        }
        for (int k : squareNum) {
            if (is_divided(n - k, count - 1)) {
                return true;
            }
        }
        return false;
    }
}

//BFS
class Solution {
    public int numSquares(int n) {
        List<Integer> squareNum = new ArrayList<>();
        int i = 1;
        while (i * i <= n) {
            squareNum.add(i * i);
            i += 1;
        }
        Set<Integer> queue = new HashSet<>();
        queue.add(n);
        int level = 0;
        while (!queue.isEmpty()) {
            level += 1;
            Set<Integer> next_queue = new HashSet<>();
            for (int remain : queue) {
                for (int square : squareNum) {
                    if (square == remain) {
                        return level;
                    }
                    else if (square > remain) {
                        break;
                    }
                    else {
                        next_queue.add(remain - square);
                    }
                }
            }
            queue = next_queue;
        }
        return level;
    }
}