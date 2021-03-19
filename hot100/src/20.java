class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i < s.length();i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                continue;
            }
            if (stack.empty()) {
                return false;
            }
            char cc = stack.pop();
            if (c == ')') {
                if (cc != '(') {
                    return false;
                }
            }
            else if (c == ']') {
                if (cc != '[') {
                    return false;
                }
            }
            else if (c == '}') {
                if (cc != '{') {
                    return false;
                }
            }
        }
        return stack.empty();
    }
}