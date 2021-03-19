## Java刷leetcode常用的API整理
Arrays.sort(nums)默认对nums升序排序
HashSet实现集合数据类型，不包含重复的元素
TreeSet会将集合中的元素排序
Queue<Integer> queue = new LinkedList<>();因为Queue只是一个接口，无法实例化

将char转为int的方法为：int num = c - '0';
构建需要增删的String时最好使用StringBuffer，可以通过append和delete，而不需要每次使用一个临时字符串，效率更好