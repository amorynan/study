package com.company.string;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class Main {

    public String replaceSpace(StringBuffer str) {
        String s =  str.toString();
        String s1 = s.replaceAll(" ", "%20");
        return s1;
    }

    /**
     * 找到string a 中完全匹配match 字符串的子字符串，并且返回string1中第一个, kmp
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()){
            return -1;
        }
        if(needle.equals("")  ||  haystack.equals("")){
            return 0;
        }

        char[] cs = haystack.toCharArray();
        char[] match = needle.toCharArray();
        int[] next = getNext(match);
        int i = 0;
        int j = 0;
        while (i < cs.length && j < match.length ){
            if(cs[i] == match[j]){
                i++;
                j++;
            }else if(j == 0){
                i++;
            }else {
                j = next[j];
            }
        }
        return j == match.length ? i - j : -1;
    }

    public static int[] getNext(char[] match){
        int[] next = new int[match.length];
        next[0] = -1;
        if(match.length == 1){
            return next;
        }
        next[1] = 0;
        int pos = 2;
        int curlength = 0;
        while (pos < match.length){
            if (match[curlength] == match[pos-1]){
                next[pos++] = ++curlength;
            }else if (curlength > 0){
                curlength = next[curlength];
            }else {
                next[pos++] = 0;
            }
        }
        return next;
    }


    public static int beibao(int[] w, int[] v, int c){
        int max = 0;
        int[][] dp = new int[v.length][c+1];
        for (int i = 0; i <= c; ++i) {
            if (i >= w[0]){
                dp[0][i] = w[0];
            }
        }
        for (int i = 1; i < w.length; ++i) {
            for (int j = 1; j <= c; ++j) {
                if (j < w[i]) {
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i - 1][c - w[j]] + w[i], dp[i - 1][j]);
                }
            }
        }
        return dp[v.length-1][c];
    }

    /**
     * 计算字符串中所有的数字之和
     * @param s
     * @return
     */
    public static int sumInstring(String s) {
        if (s == null ) {
            return 0;
        }

        char[] cs = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < cs.length; ++i) {
            boolean flag = true;
            if (cs[i] == '-') {
                // 判断当前符号修饰的真正值
                while (i < cs.length && cs[i] == '-') {
                    i++;
                    flag = !flag;
                }
            }
            if (Character.isDigit(cs[i])){
                int j = -1;
                while(i < cs.length && Character.isDigit(cs[i])) {
                    i++;
                    j++;
                }
                int num = 0;
                while(j >= 0){
                    num += Math.pow(10, j) * (cs[i-j-1]-'0');
                    j--;
                }
                sum += flag ? num : -num;
            }
        }
        return sum;
    }


    /**
     * 删除字符串中满足长度==k的'0'的子串
     * @param s
     * @param k
     * @return
     */
    public static String deleteKString(StringBuilder s, int k) {
        if (s == null) {
            return null;
        }
        char[] cs = s.toString().toCharArray();
        for (int i = 0; i < cs.length; ++i) {
            if (cs[i] == '0') {
                int j = k;
                while (j > 0 && i < cs.length && cs[i] == '0') {
                    i++;
                    j--;
                }
                if (j == 0 && ((i < cs.length && cs[i] != '0') || i == cs.length)){
                    s = s.replace(i-k,i,"");
                }
            }
        }
        return s.toString().trim();
    }

    /**
     * 判断两个字符串是否互相旋转，时间复杂度o（n*m）
     * 主要用map的思想
     * @param a
     * @param b
     * @return
     */
    public static boolean isReverse(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        List<Integer> map = new ArrayList<>();
        for (int i = 0; i < bs.length; ++i) {
            if (bs[i] == as[0]) {
                map.add(i);
            }
        }
        boolean res = true;
        for (int j = 0; j < map.size(); ++j) {
            for (int i = 0; i < as.length; ++i) {
                if (bs[(map.get(j) + i) % as.length] != as[i]){
                    res = false;
                    break;
                }
                if (i == as.length -1) {
                    res = true;
                }
            }
        }
        return res;

    }

    /**
     * 上面的加强版,小技巧：将两个b顺序n合成一个大2b ，然后判断2b里面是否包含a即可
     * @param a
     * @param b
     * @return
     */
    public static boolean isRotation(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        String bb = b+b;
        return bb.contains(a);
    }

    /**
     * 将输入的字符串转换为数字，注意有检验日常+溢出的效果
     * @param s
     * @return
     */
    public static int convertAndisValid(String s) {
        if (s == null || s.charAt(0) == '0') {
            return 0;
        }

        char[] cs = s.toCharArray();
        int len = cs.length-1;
        int sum = 0;
        boolean flag = true;
        for (int i = 0; i < cs.length; ++i) {
            if (!Character.isDigit(cs[i])) {
                if (i == 0 && cs[i] == '-'){
                    flag = false;
                    continue;
                }else {
                    return 0;
                }
            }
            sum += (int)Math.pow(10, len - i) * (cs[i]-'0');
        }
        // 返回结果根据flag的值需要判断是否有溢出
        return flag ? (sum > 0 ? sum : 0) : -sum;
    }

    public static String replaceSpecial(String s, String from, String to) {
        if (s == null || !s.contains(from)) {
            return s;
        }
        return s.replaceFirst(from, to);
    }


    public static int returnIndexForString (String s) {
        char[] cs = s.toCharArray();
        int mid = 0;
        int left = 0;
        int right = cs.length - 1;
        return 0;
    }

    public char getCharAtString(String s, int index) {
        String[] s1 = s.split("_");
        int[] range = new int[s1.length];
        char[] in = new char[s1.length];
        int j = 0;
        for (int i = 0; i < s1.length; ++i) {
            if (i % 2 == 0) {
                in[j] = s1[i].charAt(0);
            }
            range[j] = j > 0 ? range[j-1]+Integer.parseInt(s1[i]) : Integer.parseInt(s1[i]);
            j++;
        }
        // 找到第一个大于index 的arr index
        int mid = range.length/2;
        int left = 0;
        int right = range.length - 1;
        while (left < right) {
            mid = (left + right) / 2;
            if (range[mid] == index){
                left = mid;
                break;
            }else if(index < range[mid]){
                right = mid - 1;
            }else {
                left = mid +1;
            }
        }
        return range[left] == index ? in[left] : in[left - 1];

    }

    /**
     * 在时间复杂度是o（n） 且空间复杂度是o（1） 的情况下，不敢变数字的位置去替换其中的字符
     * 主要的小技巧来源于倒着复制这样一个思想，顺序复制下来前面的数字，保证数字的顺序性，然后又能得到最后一个替换的位置，直接前置前面的数值就好
     * @param s
     * @return
     */
    public static String replaceStringInWord(String s) {
        if (s == null) {
            return null;
        }
        char[] cs = s.toCharArray();
        int j = cs.length - 1;
        for (int i = cs.length - 1; i>=0; --i){
            if (cs[i] != '*') {
                cs[j--] = cs[i];
            }
        }

        while (j >= 0) {
            cs[j--] = '*';
        }
        return new String(cs);
    }

    /**
     * 重点 ： 判断一个字符串中是否每一个字符都只是出现了一次，除了简单的map，要求空间复杂度为o（1），时间复杂度尽可能小
     * 那么首先可以想到排序判断相邻的情况，但是经典的排序算法中需要了解哪些是时空复杂度
     * 从而需要得出一个满足上述要求，时间复杂度在o(nlogn)的情况，而不是每一个遍历为o（n^2）的情况
     * @param s
     * @return
     */
    public static boolean judgeAllIsOneInString(String s){
        char[] cs = s.toCharArray();

        //Arrays.sort(cs);

        heapSort(cs);
        for (int i = 1; i < cs.length; ++i) {
            if (cs[i] == cs[i-1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 空间复杂度是o（1）的堆排序了解一下
     * 分为初始化堆和调整堆的过程，初始化堆的时间复杂度是o(n)，调整是o（logn）
     * 这里采用的是大根堆，排出的效果是依次从小到大的顺序
     * @param cs
     */
    public static void heapSort(char[] cs) {
        for (int i = 0; i < cs.length; ++i) {
            heapInsert(cs, i);
        }

        for (int i = cs.length - 1; i > 0; --i) {
            swap(cs, 0, i);
            heapfy(cs, 0, i);
        }
    }

    /**
     * 初始化堆的过程也是按照每一个遍历到的每一个值的过程，进行初始化，能满足的效果是当前的根节点一定大于下面子孩子的节点
     * @param cs
     * @param index
     */
    public static void heapInsert(char[] cs, int index) {
        int parent;
        while (index > 0){
            parent = (index - 1) / 2;
            if (cs[parent] < cs[index]) {
                swap(cs, parent, index);
                index = parent;
            }else {
                break;
            }
        }
    }

    /**
     * 调整堆的过程，是在已经将每次选举出来的最大值放在了最后一个位置之后，从头开始向剩下的需要调整的堆进行一个选举下一个最大值的过程，
     * 所以此时相当于将一个当前最大值退居后，先让一个小zhi临时替代，然后根据一个比武选出下一个老大，注意必须是要每次都要将整个数组都调整成大根堆的情况才能算结束
     * 因为这样才能满足下一次的比武的能公平公正，而不能让这个随机的小值占了便宜
     * @param cs
     * @param index
     * @param size
     */
    public static void heapfy(char[] cs, int index, int size){
        // 1。 左右孩子和当前的下标
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;

        while (left < size) {
            // 判断左右和当前root的最大情况值
            if (cs[left] > cs[index] ) {
                largest = left;
            }
            if(right < size && cs[right] > cs[largest]) {
                largest = right;
            }
            // 判断是否进行交换
            if (largest != index){
                swap(cs, index, largest);
            }else {
                // 如果当前已经比左右孩子大了，就代表当前index 的值比剩下的都要大，也即是带排序的整个数组已经又是一个大根堆的形式了
                break;
            }
            // 如果当前index 还没有满足largest 就是自己的index 的情况，那就说明此值还需要继续调整直到整个数组变成大根堆的形式
            index = largest;
            left = 2 * index + 1;
            right = 2 * index + 2;
        }
    }

    /**
     * 只需要逆序字符串中的单词，不是字符
     * @param s
     * @return
     */
    public static void reverseString(String s) {
        if (s == null ){
            return;
        }

        String[] pre = s.split(" ");
        int start = 0;
        int end = pre.length - 1;
        while (start < end){
            swap(pre, start,end);
            start ++;
            end --;
        }

        for (int i = 0; i < pre.length; ++i) {
            System.out.print(pre[i]+" ");
        }
    }

    /**
     * 还是上面的需要逆序字符串中的单词，不是具体的字符,但是需要做到空间复杂度为o（1）
     * 小技巧就是，首先逆序整个字符串，然后找到每一个单词，进行单词内部的逆转即可
     * @param s
     */
    public static void rotateWordInString(char[] s) {
        if (s == null) {
            return;
        }
        int i = 0;
        int j = s.length - 1;
        reverseString(s, i, j);
        int k = 0;
        while( k < s.length) {
            int x = k;
            while (x < s.length && s[x] != ' '){
                x ++;
            }
            reverseString(s, k, x-1);
            k = x + 1;
        }
        System.out.println(s);
    }

    public static void reverseString(char[] s, int i, int j){
        while (i < j) {
            swap(s, i, j);
            i++;
            j--;
        }
    }

    public static void reverseSpcialLenInString(char[] s, int size){
        reverseString(s, 0, s.length - 1);
        reverseString(s, 0, s.length - size  -1 );
        reverseString(s, s.length - size, s.length - 1);
        System.out.println(new String(s));
    }

    public static int minDisForTwoStringInArray(String[] s, String a, String b) {
        List<Integer> map = new ArrayList<>();
        List<Integer> map1 = new ArrayList<>();
        for (int i = 0; i < s.length; ++i) {
            if (s[i].equals(a)) {
                map.add(i);
            }
            if (s[i].equals(b)) {
                map1.add(i);
            }
        }
        if(map.isEmpty() || map1.isEmpty()) {
            return  -1;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < map.size(); ++i) {
            for (int j = 0; j < map1.size(); ++j) {
                min = Math.max( min, Math.abs(map.get(i) - map1.get(j)));
            }
        }
        return min;
    }

    /**
     * 寻找数组中指定两个字符串的最小距离，除了用map 的方法去记录
     * 主要的思想就是当遍历到当前的节点满足是a或者b其中一个，去看当前节点左边一个离他最近的b或者a 的位置，所以我们需要有
     * 两个变量去记录最近一个出现a或者b的位置的情况，而且"去看当前节点左边一个离他最近的b或者a 的位置"的重要条件是从左到右遍历的
     * 最后全局更新min的值就可以了
     * @param s 1
     * @param a
     * @param b
     * @return
     */
    public static int minDisforTwoStringInArray(String[] s, String a, String b) {
        if (a.equals(b)){
            return 0;
        }
        int lastIndexForA = -1;
        int lastIndexForB = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < s.length; ++i) {
            int dis = 0;
            if (s[i].equals(a)) {
                dis = lastIndexForB == -1 ? Integer.MAX_VALUE : i - lastIndexForB;
                lastIndexForA = i;
            }
            if (s[i].equals(b)){
                dis = lastIndexForA == -1 ? Integer.MAX_VALUE : i - lastIndexForA;
                lastIndexForB = i;
            }
            min = Math.min(dis, min);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }



    /**
     * 重点：以最小的补充字符代价完成当前字符串变成回文的结构
     * 首先最值问题，先向着dp的方向去思考看看，因为dp解决的是完成每一次最值的某一个最小的结果，所以需要转换问题为
     * 从i-j 最少需要多少字符可以让i-j上的变成回文
     * dp[i][j 已经定义好了之后，需要完成dp[i][j] 的更新策略
     * 这里有个坑就是不像一般dp ，按顺序更新，而是通过具体的场景
     * 场景一：dp[i][j] 取决于s[i] 和 s[j] 的值的比较，相等，则dp[i][j] = dp[i+1][j-1] , 注意推导出了这个公式之后需要注意的地方有是从里面向外面扩展的思想
     * 所以需要先遍历得出里面的值，比如dp[j][j-1] ，总之是需要对里面的值进行计算，这个计算的来源一般来说就是题目的限制条件
     * 场景二 ：不相等：则看s[i+1 - j] 和 s[i - j-1]上谁的代价最小，然后让其变成回文之后再加上最后一个值就可以让整体变成回文也即是 dp[i][j] = Math.min(dp[i+1][j],dp[i][j-1])+1
     * 所以在这里需要有敏感度可以看出dp的更新策略依附于中间的某些值，再加上具体的场景，可以得到从s[j] 开始往前算，当为一个字符的时候，dp为0
     * 当往前扩展为两个的时候，需要比较，判断为0或者1， 当往前扩展大于两个的时候，就要具体比较并且凭借前面打下的依据数值，算最小的情况
     *
     *
     * 还没有完，上面只是算出来了dp的数组，然后需要根据这个dp的数组进行填充整个字符串
     * 填充的策略就是，char【】 res 的长度肯定是dp[0][n] + s.length-1
     * 所以就是从最左最右开始往里面进行填充
     * 如果s[i] == s[j] ,res[index] == s[i] or s[j],同时另外一头一样的值 ,注意这里i++，j--因为ij的值直接影响我们下一次需要判断的最小情况的依据
     * 上述情况不满足，res[index] = dp[i+1][j] > dp[i][j-1] ? s[i]+xxx+s[i] ; s[j]+xxx+s[j]
     * 注意这里假设dp[i+1][j] > dp[i][j-1]，那么应该只有i++; s[i - j-1]上需要的最小代价已经用过了，但是s[i - j-2] 的还没有用呢，所以不需要对j进行操作
     * @param s
     * @return
     */
    public static String minCharactorsForParlindrome(String s) {
        if (s == null) {
            return null;
        }
        int[][] dp = minNumberForParlindrome(s);
        // 根据算出来的dp 数组为最小依据遍历填充最后的字符串的最左边和最右边
        char[] res = new char[s.length()+dp[0][dp.length-1]];
        int i = 0;
        int j = s.length() - 1;
        int index = 0;
        // 需要注意什么时候i更新，什么时候j更新，什么时候i j 一起更新
        while (i < s.length() && j >=0 && i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                res[index] = s.charAt(i);
                res[res.length - 1 - index] = s.charAt(i);
                i++;
                j--;
            }else {
                if (dp[i][j-1] < dp[i+1][j]) {
                    res[index] = s.charAt(j);
                    res[res.length - 1 - index] = s.charAt(j);
                    j--;

                }else {
                    res[index] = s.charAt(i);
                    res[res.length - 1 - index] = s.charAt(i);
                    i++;
                }
            }
            index ++;

        }
        return new String(res);

    }

    /**
     * 公式字符串求值 -- 主要考察一个代码的写能力，主要还是需要调试
     * @param s
     * @return
     */
    public static int calValForString(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() == 0) {
            return Integer.parseInt(s);
        }
        char[] cs = s.toCharArray();
        Stack<Integer> value =  new Stack<>();
        Stack<Character> character = new Stack<>();
        int i = 0;
        while(i < s.length()) {
            if (Character.isDigit(cs[i])) {
                int num = 0;
                while (i < cs.length && Character.isDigit(cs[i])){
                    num = num * 10 + cs[i] - '0';
                    ++i;
                }
                value.push(num);
                continue;
            }else {
                char c = cs[i];
                if (character.isEmpty()){
                    if (c == '-'){
                        value.push(0);
                    }
                    character.push(c);
                    i++;
                    continue;
                }
                char peek = character.peek();
                switch (c){
                    case '+' :
                    case '-':
                        if (c == '-' && cs[i-1] == '('){
                            value.push(0);
                            character.push(c);
                            break;
                        }
                        if (peek == '(') {
                            character.push(c);
                            break;
                        }
                        int num = getSum(value.pop(), value.pop(), peek);
                        value.push(num);
                        character.pop();
                        character.push(c);
                        break;
                    case '*':
                    case '/':
                        if (peek == '*' || peek == '/') {
                            // 计算值
                            int num_ = getSum(value.pop(), value.pop(), peek);
                            value.push(num_);
                            character.pop();
                        }else{
                            character.push(c);
                        }
                        break;
                    case '(':
                        character.push(c);
                        break;
                    case ')':
                        int nu = getSum(value.pop(), value.pop(), peek);
                        value.push(nu);
                        character.pop();
                        character.pop();
                        break;
                }
            }
            i++;
        }
        if (character.isEmpty()){
            return value.pop();
        }else {
            while (!character.isEmpty()){
                int num = getSum(value.pop(), value.pop(), character.pop());
                value.push(num);
            }
            return value.pop();
        }

    }


    /**
     * 0 左边 必有 1 的二进制字符串数量
     * 又是一个相邻的情况的判断问题
     * 注意一下是否能够调整为p[i]，也即是说假设[0 - i-1] 已经确定，并且i-1下一定是1，从i开始p[i] 的值就是代表了可能出现的结果总数，那这个值的
     * 更新策略根据题意，当字符串中i的值为0的时候，就是代表i+1一定是1，所以p[i] = p[i+2], 如果i的值是1的时候p[i] = p[i+1]
     * 所以综上p[i] = p[i+1 + p[i+2]
     * 只要得出上面的式子，基本上就确定要从后面往前面遍历，并且初始化的时候一定要想清楚，尽可能吧所有提前可以知道结果初始化
     * @param n
     * @return
     */
    public static int oneBeforeZero(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] p = new int[n];
        if (n > 2) {
            p[n-1] = 1;
            p[n-2] = 2;
        }
        for (int i = n-3; i >= 0; --i) {
            p[i] = p[i+1] + p[i+2];
        }
        return p[0];
    }

    /**
     * 在一个字符串数组中拼接所有字符串，使最后的字符串的字典顺序最小
     * 此题需要证明，大体是贪心算法，但是不纠结这个，当作工具使用
     * 证明的结果就是对字符串进行排序即可，也即是说string[] = {a,b,c}
     * a.b > b.a ->a.b
     * 那么如何对字符串进行字典序的排序呢？就直接写一个comparetor针对数组进行排序，二者comparetor 主要使用java
     * 自带的string的compareTo的方法，其实现原理就是按照，this.charAt(k) - another.charAt(k) then
     * this.length - another.length
     * String s
     * @return
     */

    static class StringComparetor implements Comparator<String> {

        @Override
        public int compare(String a, String b) {
            return (a+b).compareTo(b+a);
        }
    }

    public static String minDicOrder(String[] s){
        if (s == null || s.length == 0) {
            return "";
        }
        Arrays.sort(s,new StringComparetor());
        String res = "";
        for (int i = 0; i < s.length; ++i) {
            res += s[i];
        }
        return res;
    }

    public static int getNumber(int i, char[] cs){
        int num = 0;
        while (i < cs.length && Character.isDigit(cs[i])){
            num = num * 10 + cs[i] - '0';
            ++i;
        }
        return num;
    }

    public static int getSum(int i, int j, char c) {
        int res = 0;
        switch (c){
            case '+':res =  i+j; break;
            case '-':res = j-i; break;
            case '*':res = i*j; break;
            case '/':res = j/i;break;
        }
        return res;

    }

    public static int[][] minNumberForParlindrome(String s) {
        // 定义一个dp， dp[i][j] 表示从i-j 上使s【i-j】 变成回文的最少需要补充的字符数
        int[][] dp = new int[s.length()][s.length()];
        // dp[i][j] 的数值来源分为三种场景： 当s[i] == s[j]， dp[i][j] == dp[i+1][j-1]
        // 当s[i] != s[j] , dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1])+1
        // 所以可以看到dp的值是随着中间的值决定的，那么dp的更新策略也需要从已知向外扩展，不要顺着更新，这是个坑
        // 从理论的角度上来讲，就是每一次得到的最小的值都应该是从当前的位置，字符个数为1的时候往外扩展的得到的最小值
        for (int j = 1; j < s.length(); ++j) {
            // 一个字符的时候就是回文，默认为0，两个字符的时候判断是否相等，决定0或者1
            dp[j-1][j] = s.charAt(j) == s.charAt(j-1) ? 0:1;
            // 大于两个字符的时候
            for (int i = j - 2; i >= 0; --i) {
                if (s.charAt(i) == s.charAt(j)){
                    //上面的更新策略保证了这里不会溢出
                    dp[i][j] = dp[i+1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1])+1;
                }
            }

        }

        return dp;
    }

    /**
     * 找到字符串中无重复字符的最大长度
     * 这里时间复杂度不确定，类似于循环遍历的方式，不推荐，看下面一个最优的解法
     * @param s
     * @return
     */
    public static int maxLengthNoRepeatInString(String s) {
        if (s == null) {
            return 0;
        }
        char[] cs = s.toCharArray();
        int[] dp = new int[cs.length];
        dp[0] = 0;

        for (int i = 1; i < cs.length; ++i) {
            int j = i-1;
            while(j >= dp[i-1] && cs[i] != cs[j]){
                j--;
            }
            dp[i] = j+1;
        }
        int max = 0;
        for (int i = 0; i < cs.length; ++i) {
            max = Math.max(max,i-dp[i]+1);
        }
        return max;
    }

    /**
     * 找到最长无重复子串
     * 核心思想就是区间求交，遍历一次，找到和当前下标i相同的前面一个最近的下标值，
     * 那么在这段中就一定不是重复的，其次需要判断i-1 的最长的字符的前一个下标，然后这两个区间求交就是当前i的最长的无重复子串区间
     * @param s
     * @return
     */
    public static int maxLengthNoRepeatInStringV2(String s) {
        if (s == null) {
            return 0;
        }
        int pre = -1;
        int[] map = new int[256];
        // 初始化 ，map 代表了下标所代表的值的最近出现的位置的记录，作为依据给下一个查看
        for (int i = 0; i < map.length; ++i) {
            map[i] = -1;
        }
        char[] cs = s.toCharArray();
        int max = 0;
        for(int i = 0; i < cs.length; ++i) {
            int lastIndex = map[cs[i]];
            if (pre < lastIndex) {
                pre = lastIndex;
            }
            map[cs[i]] = i;
            max = Math.max(max, i-pre);
        }
        return max;
    }


    /**
     * 求a串包含b串的最小长度情况
     * 实现上不难-主要的思路就是定义一个right和left的pointer，然后还需要几个信号量，match a串中还欠b串的字符串的数目，初始状态就是b串的长度
     * 然后需要一个map来计算出每次right遍历是归还给b串的值，key对应了归还的值，value就对应了归还的数量，初始状态下，map就是b的串相应的字符和
     * 各自对应的数量
     * 然后更新的策略就是right每次向后移动，就代表要归还当前的字符给b，也即是要去更新map的值，同时需要判断是够要更新match的值，而match的更新
     * 策略则是，当map中某一个字符的值对应的value变成了0，也就是a串不欠b相应的字符了，match 就--
     * 当match -- 到0之后还要进行一个内缩的操作，就是在a串一直归还的操作过程中，会有很多多还的部分，可以排除掉，也即是让left右移，然后拿回来
     * 多还的部分，然后value相应自加，同时value的操作在任何时候都可能引起match的临界值的情况，所以要持续关注match是否将要变成1，那就可以计算
     * 当前的min值，同时此时的left-right上就是满足遍历到left和right区间的最小，但是还需要继续从left+1，开始往后面看重复操作
     * @param a
     * @param b
     * @return
     */
    public static int minContainSubStringLength(String a, String b) {
        if (b == null) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < b.length(); ++i) {
            if (map.containsKey(b.charAt(i))){
                map.replace(b.charAt(i), map.get(b.charAt(i))+1);
            }else {
                map.put(b.charAt(i), 1);
            }
        }
        int min = Integer.MAX_VALUE;
        char[] cs = a.toCharArray();
        int match = b.length();
        int right = 0;
        int left = 0;
        while (right < cs.length) {
            if (match == 0) {
                // left 开始往右进行内缩
                while (left < right) {
                    int number = map.get(cs[left]);
                    if (number == 0) {
                        min = Math.min(min, right - left + 1);
                        map.replace(cs[left], 1);
                        left ++;
                        break;
                    }else {
                        // 进行还原操作
                        map.replace(cs[left], ++number);
                    }
                    left++;
                }
            }
            if (map.containsKey(cs[right])){
                int number = map.get(cs[right]);
                map.replace(cs[right], --number);
                if (number == 0 && --match == 0){
                    continue;
                }
            }else {
                map.put(cs[right], -1);
            }
            right ++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * 回文的最小分割数
     * 又是最值的问题，考虑dp 的解法，同时一定要结合题目的含义决定
     * dp[i] 可以代表从i-length 长度下最小的回文分割数，那么他的更新策略就是假设
     * 在i-length中间有j的，如果i-j上是回文的，那么dp[i] = dp[j+1]+1,通过遍历从i-length
     * 决定j 的位置，更新此时的dp[i]的最小值
     * 同时在从i遍历到length过程中需要尽快的得出i-j是否是回文的，用一个boolean p[i][j] 来表示i-j是否是
     * 回文的，p[i][j] == true 的情况只有三种，i==j, j-i<2&&cs[i]==cs[j], j-i>=2&&p[i+1][j-1]&&cs[i]==cs[j]
     * 当为true的时候便可以计算dp的值
     * 由于i的遍历是从后往前的，所以一定能通过后面的值求出dp[i]的值，由于j是从i往后的，所以p的值也能始终
     * 同时为了方便可以让dp往后多加一个，并设置为-1
     * @param s
     * @return
     */
    public static int minForDivPariliondrome(String s) {
        if (s == null || s.length() == 1) {
            return 0;
        }
        char[] cs = s.toCharArray();
        // dp[i] 的含义为从 i 到 length - 1上最小的回文切割数，从而dp[0] 为解
        int[] dp = new int[s.length()+1];
        // 初始化
        dp[s.length()] = -1;
        boolean[][] p = new boolean[cs.length][cs.length];
        for (int i = cs.length - 1; i >= 0; --i) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j <= cs.length - 1; ++j){
                // 这里的判断条件后面的括号里面的顺序是不能改变的,总体就是解决了从i到leng下为true的可能性
                if (cs[i] == cs[j] && (j-i<2 || p[i+1][j-1])){
                    p[i][j] = true;
                    dp[i] = Math.min(dp[i], dp[j+1]+1);
                }
            }
        }
        return dp[0];
    }

    public static boolean stringMatch(String s, String b){
        if (b == null || s == null) {
            return false;
        }
        char[] cs = s.toCharArray();
        char[] cb = b.toCharArray();

        return match(cs,cb, cs.length-1, cb.length-1);
    }

    public static boolean match(char[] cs, char[] bs, int indexs, int indexb) {
        boolean res = true;
        if (indexs == 0 && (cs[indexs] == bs[indexb] || bs[indexb] == '*')){
            return res;
        }
        if (cs[indexs] == bs[indexb] || bs[indexb] == '.') {
            res = match(cs, bs, indexs-1, indexb-1);
        }else if(bs[indexb] == '*' && bs[indexb-1] == '.') {
                res = match(cs, bs, indexs - 1, indexb);
        }else{
            res = false;
        }
        return res;
    }

    public static void swap(String[] s, int i, int j) {
        String temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static String solution(String s) {
        //map按照字符编码
        int[] map = new int[27];
        for (int i = 0; i < s.length(); ++i) {
            map[s.charAt(i)-97] ++;
        }
        String res = "";
        for (int i = 0; i < map.length; ++i) {
            if (map[i]!=0) {
                // string value of 可以将char类型的在string中体现
                res += String.valueOf((char)(i+97))+map[i];
            }
        }
        return res;
    }

    // 12001
    public static String solution1(int target){
        char[] sign = new char[] {'L', 'S', 'B', 'Q', 'W'};
        String s = "";
        int i = 0;
        boolean flag = false; // 表示当前位置上为0的情况下，下一个数字是否有值的情况,因为需要判断如果检测到当前已经为0了，再遍历前面一个的时候就应该
        // 把这个值关闭，就不会🈶重复的L输出了
        while (target != 0){
            int temp = target % 10;
            if(temp != 0) {
                flag = true;

                if (i != 0) {
                    s = sign[i] + s;
                }
                s = temp + s;
            }else if (flag && i != 0) {
                s = sign[0] + s;
                flag = false;
            }
            ++i;
            target /= 10;
        }
        return s;
    }


    public static void swap(char[] cs, int i, int j) {
        if (i ==j) {
            return;
        }
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }
    public static void main(String[] args) {
        //Scanner s = new Scanner(System.in);
        //String next = s.nextLine();
         //int k = s.nextInt();
        //String b = s.nextLine();
        //String to = s.nextLine();
        // int sum = sumInstring(next);
        //String returns = deleteKString(new StringBuilder(next), k);
       //System.out.println(replaceStringInWord(next));
        ////System.out.println(judgeAllIsOneInString(next));
        //reverseString(next);
        //rotateWordInString(next.toCharArray());
        //reverseSpcialLenInString(next.toCharArray(), 3);
        //System.out.println(minDisForTwoStringInArray(next.split(" "), b, to));
       // System.out.println(maxLengthNoRepeatInString(next));
       // System.out.println(maxLengthNoRepeatInStringV2(next));
        //System.out.println(stringMatch(next, b));
        //System.out.println(solution1(11));
        //System.out.println(solution(to));

//        int[] c = new int[] {1, 3 ,5, 6};
//        int[] v = new int[] {2, 4, 5, 1};
//        int w = 6;
//        //beibao(c, v, w);
//        //System.out.println(strStr("aabaaabaaac",
//          //      "aabaaac"));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
//        reverse(Integer.parseInt("1534236469"));

        Scanner in = new Scanner(System.in);
        String[] s = in.nextLine().split(",");
        String a = s[0];
        String b = s[1];

        System.out.println(min(a, b));

    }
    public static int reverse(int x) {
        boolean flag = x >= 0;
        int res = 0;
        while (x != 0){
            int yu = x % 10;
            x = x / 10;
            res = res * 10 + yu;
            if(flag ? res >= Integer.MAX_VALUE : -res <= Integer.MIN_VALUE){
                return 0;
            }
        }
        return flag ? res : -res;

    }

    public static int min(String a, String b){
        int[] dp = new int[a.length()];
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
//        int i = 0;
//        int j = 0;
        int min = Integer.MAX_VALUE;
        dp[0] = Math.abs(as[0]-bs[0]);
//        while (i < as.length && j <bs.length){
//            min = Math.min(min, Math.abs(as[i]-bs[i]));
//            for(int k = 0; k < i;++i){
//                if(as[k] != as[i]){
//                    min = Math.min(Math.abs(as[k]-bs[i])+Math.abs(as[i]-bs[k]), min);
//                }
//            }
//            i++;
//            j++;
//        }
        for (int i = 1; i < as.length; ++i){
            dp[i] = dp[i-1]+Math.abs(as[i]-bs[i]);
            for (int j = 0; j < i; ++j){
                if(as[j] == bs[i]){
                    int t = Math.abs(as[i]-bs[j])+1;
                    if(t < dp[i]){
                        swap(as, i, j);
                        dp[i] = t;
                    }

                }
            }
        }
        return dp[dp.length-1];

    }



}
