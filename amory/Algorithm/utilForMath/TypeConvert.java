package utilForMath;


/*0-9对应Ascii 48-57
 *A-Z 65-90
 *a-z 97-122
 *第33～126号(共94个)是字符，其中第48～57号为0～9十个阿拉伯数字
 */

import java.math.BigDecimal;


public class TypeConvert {

    public static boolean isPrime(int n){
        if (n <= 3) {
            return n > 1;
        }
        // 不在6的倍数两侧的一定不是质数
        if (n % 6 != 1 && n % 6 != 5) {
            return false;
        }

        int a = (int)Math.sqrt(n);
        for (int i = 5; i < a; i+=6){
            if (n % i == 0 || n % (i+2) == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 将字符型数字转换成相应的数字
     * '4'->4
     * @param a
     * @return
     */
    public static int charToInt(char a) {
        return a-'0';
    }

    /**
     * 将0-27转换成a-z
     * @param i
     * @return
     */
    public static char IntToCharUp(int i) {
        char char1=(char)(i+65);
        return char1;
    }/**
     * 将0-27转换成A-Z
     * @param i
     * @return
     */
    public static char IntToChar(int i) {
        char char1=(char)(i+97);
        return char1;
    }


    public static int IntBit(){
        String s = "here";
        String s1 = "中";
        byte[] bytes = s.getBytes();
        System.out.println(bytes.length);
        char c = '中';


        return 0;
    }

    public static byte[] charToBit(char c){
        byte[] b = new byte[2];
        b[0] = (byte)((c & 0xFF00) >> 8);
        b[1] = (byte)(c & 0xFF);
        System.out.println(b[0]+b[1]);
        return b;
    }

    public static BigDecimal isWuxianxunhuanxiaoshu(int a, int b) {
        BigDecimal bigDecimal = new BigDecimal(a);
        BigDecimal bigDecimal1 = new BigDecimal(b);
        System.out.println("a/b" + bigDecimal.divide(bigDecimal1));
        return bigDecimal.divide(bigDecimal1);

    }


//    public static String dashuxiangcheng(int[] n1, int [] n2){
//        String res = "";
//        int tempResLen;                    //每次相乘结果的最大长度
//        int resultLen;                     //结果的最大长度
//        int reseach;                           //每次一位相乘/相加的结果
//        int carry = 0;                     //进位
//        int offset = 0;                    //加法的偏移位
//        resultLen = n1.length + n2.length - 1; //结果长度最大为num1长度和num2长度之和，由于下标从0开始，所以要减一
//        tempResLen = n1.length;              //每次num1乘以num2每一位的结果最大长度是num1Len+1,由于下标从0开始，所以减一后约去1,只剩num1Len
//        //初始化result为0
//        for (int i =n1.length-1; i >=0 ; --i) {
//            for (int j = n2.length - 1; j >= 0; --j){
//                int tempres = n2[j]*n1[i];
//                res += tempres
//            }
//        }
//    }
    public static void main(String[] args) {
        System.out.println(IntToChar(0));
        System.out.println(IntToCharUp(0));
        char a =  'a';
        charToBit(a);
        isWuxianxunhuanxiaoshu(1, 2);
    }
}
