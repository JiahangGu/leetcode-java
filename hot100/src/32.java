/**
 * 首先很自然的想法是统计左右括号的个数，如果相等则更新答案为对应长度，如果右括号大于左括号则到此处无效置为0.但是存在一个问题
 * 例如"()((())"返回2，但其实后面的两对括号是答案。
 * 改为动态规划做法，首先数据范围i是3*10^4，则复杂度最多是O(nlgn)，假设dp[i]表示以s[i]结尾的字符串合法的括号最大长度，则
 * 只有出现")"才会配对需要更新结果，并且取决于s[i-1]的字符情况。
 * 1. s[i-1] == '('，则s[i-1]和s[i]构成一对合法括号，并且和s[i-2]连续，则dp[i] = dp[i-2]+2
 * 2. s[i-1] == ')'，此时注意dp[i-1]表示以s[i-1]结尾的最大连续合法括号，也就是s[i-dp[i-1],i-1]对应的字符串是连续合法的，
 * 所以只要s[i-dp[i-1]-1]=='('就可以构造合法的括号字符串s[i-dp[i-1]-1,i]，并且还可以串联起s[i-dp[i-1]-2]结尾的最大合法
 * 字符串，所以dp[i] = dp[i-1] + 2 + dp[i - dp[i-1] - 2]。
 * 注意上述情况在求此前情况时可能存在下标为负数的情况，在取值之前需要判断。
 * 最终结果是dp中最大的数字。
 */
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length(), ans = 0;
        int []dp = new int[n];
        for (int i = 1;i < n;i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                else if (i > dp[i - 1] && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] >= 2? dp[i - dp[i - 1] - 2] : 0);
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }
}

/**
 * 用栈来判断括号是否匹配是一个经典的做法，但是在本题中，由于遇到右括号会将栈顶的左括号弹出，如果不是连续出现的左右括号因此无法统计
 * 最大的连续长度，需要想一个办法解决如何计算连续的问题。
 * 首先，如果当前字符串左右括号数相等并之前一直满足左括号数大于右括号数，则该字符串合法，如果左括号大于右括号，则匹配的长度由右括号
 * 决定，即当前索引减去配对的左括号索引，并且如果弹出后栈为空则表示从上一个不匹配的索引开始（最后一个右括号大于左括号个数的位置），
 * 字符串是合法的，所以如何记录上一个不匹配的索引成为了关键，既然栈空时表示最近字符串匹配，那么在栈底始终保存一个元素记录最后一个不
 * 匹配的索引位置，在遇到右括号弹出后，当前栈顶元素表示与当前右括号匹配的左括号的前一个位置处，即合法长度为i-stack.peek()，
 * 而右括号大于左括号的情况则比较简单，只需要将右括号索引放入即可，因为一旦不匹配，最后出现的右括号就是最后一个不匹配的。
 */
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length(), ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0;i < n;i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                }
                else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }
}

/**
 * 如解法1分析使用出现括号数量求长度的做法存在的问题，左括号个数大于右括号个数时无法更新最新解，这个问题是由于我们始终以右括号作为
 * 一个合法字符串的结束位。试想，如果我们逆序遍历字符串，此时合法序列右括号出现先于左括号，且左括号作为结束位，此前出现的左括号
 * 大于右括号导致无法更新解的问题在逆序遍历中得到解决（因为左括号等于右括号时已经更新），弥补了此前做法的缺陷，最终得到正确结果
 */
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length(), ans = 0;
        int left = 0, right = 0;
        for (int i = 0;i < n;i++) {
            if (s.charAt(i) == '(') {
                left++;
            }
            else {
                right++;
            }
            if (left == right) {
                ans = Math.max(ans, left * 2);
            }
            else if (right > left) {
                right = left = 0;
            }
        }
        left = right = 0;
        for (int i = n - 1;i >= 0;i--) {
            if (s.charAt(i) == ')') {
                right++;
            }
            else {
                left++;
            }
            if (left == right) {
                ans = Math.max(ans, left * 2);
            }
            else if (left > right) {
                left = right = 0;
            }
        }
        return ans;
    }
}