/*
通过加法表示减法，a-b->a+(-b)，需要实现取负函数。实现方法可以是将k个-1相加，但复杂度为O(k)。可以通过优化更快地使a接近0，例如逐渐加大
每一步的步数，先加-1，然后-2，-4...，并且希望精确地将a减为0，为此当a减去下一个delta后会改变a地符号时，将a重置为1并重复该过程。时间复杂度
为O(lg^2k)。
乘法操作则可以通过将a与其自身相加b次。
除法操作可以不断地将b与其自身相加直到可以得到a地值，需要重复相加的次数即为x。
 */
class Operations {

    public Operations() {

    }

    private int negate(int a) {
        int neg = 0;
        int newSign = a < 0 ? 1 : -1;
        int delta = newSign;
        while (a != 0) {
            boolean differentSigns = (a + delta > 0) != (a > 0);
            if (a + delta != 0 && differentSigns) {
                delta = newSign;
            }
            neg += delta;
            a += delta;
            delta += delta;
        }
        return neg;
    }

    private int abs(int a) {
        if (a < 0) {
            return negate(a);
        }
        else {
            return a;
        }
    }

    public int minus(int a, int b) {
        return a + negate(b);
    }

    public int multiply(int a, int b) {
        if (a < b) {
            return multiply(b, a);
        }
        int sum = 0;
        for (int i = abs(b); i > 0; i = minus(i, 1)) {
            sum += a;
        }
        if (b < 0) {
            sum = negate(sum);
        }
        return sum;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            return Integer.MAX_VALUE;
        }
        int absa = abs(a);
        int absb = abs(b);
        int product = 0;
        int x = 0;
        while (product + absb <= absa) {
            x++;
            product += absb;
        }
        if ((a < 0 && b < 0) || (a > 0 && b > 0)) {
            return x;
        }
        else {
            return negate(x);
        }
    }
}

/**
 * Your Operations object will be instantiated and called as such:
 * Operations obj = new Operations();
 * int param_1 = obj.minus(a,b);
 * int param_2 = obj.multiply(a,b);
 * int param_3 = obj.divide(a,b);
 */