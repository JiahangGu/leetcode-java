/*
 本题通过删除不合法的左右括号生成合法的括号序列，初始想法是对于每一个左右括号，递归统计其删除或者不删除的情况，判断最后得到的
 序列是否合法。主要有两个难点：1是遍历得到所有可能的情况后如何快速判断是否合法；2是如何通过剪枝减少不必要的操作。
 对于1，合法的序列一定满足左右括号个数相等，所以可以在递归过程记录括号个数，最后判断是否相等即可。
 对于2，如果某一个序列中出现当前右括号个数大于左括号个数的情况，则一定非法，后面再加字符也非法，没有必要继续递归。并且题目要求删除
 最小个数的括号，所以所有结果字符长度相等，通过记录需要删除的最小的左右括号个数可以优化剪枝。记录的方式则是通过一次遍历，如果出现
 右括号个数大于左括号的情况，则多余的右括号都需要删除，并且最后剩余未匹配的左括号也要删除。所以记录需要删除的数目，如果当前删除个数
 达到最小数目则无法继续删除。
 此外还有个问题是，对于同一个结果可能通过删除不同的括号组合得到，最后需要通过集合筛选。
 */
class Solution {
    Set<String> ans = new HashSet<>();
    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;
        for (int i = 0;i < s.length();i++) {
            if (s.charAt(i) == '(') {
                left++;
            }
            else if (s.charAt(i) == ')'){
                if (left == 0) {
                    right++;
                }
                if (left > 0) {
                    left--;
                }
            }
        }
        dfs(s, 0, 0, 0, left, right, new StringBuffer());
        return new ArrayList<String>(ans);
    }

    private void dfs(String s, int idx, int leftCount, int rightCount, int leftRemain, int rightRemain, StringBuffer sb) {
        if (idx == s.length()) {
            if (leftRemain == 0 && rightRemain == 0) {
                ans.add(sb.toString());
            }
        }
        else {
            char c = s.charAt(idx);
            int len = sb.length();
            //不计算当前左或右括号
            if (c == '(' && leftRemain > 0) {
                dfs(s, idx + 1, leftCount, rightCount, leftRemain - 1, rightRemain, sb);
            }
            else if (c == ')' && rightRemain > 0) {
                dfs(s, idx + 1, leftCount, rightCount, leftRemain, rightRemain - 1, sb);
            }
            sb.append(c);
            //非括号部分加入
            if (c != '(' && c != ')') {
                dfs(s, idx + 1, leftCount, rightCount, leftRemain, rightRemain, sb);
            }
            else if (c == '(') {
                dfs(s, idx + 1, leftCount + 1, rightCount, leftRemain, rightRemain, sb);
            }
            //如果此前右括号完全匹配，当前右括号无法匹配剪枝
            else if (rightCount < leftCount) {
                dfs(s, idx + 1, leftCount, rightCount + 1, leftRemain, rightRemain, sb);
            }
            sb.deleteCharAt(len);
        }
    }
}