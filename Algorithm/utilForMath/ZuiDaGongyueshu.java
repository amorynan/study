package utilForMath;

public class ZuiDaGongyueshu {
    // x, y 的最大公约数和最小公倍数的恒等式 x*y = 最大公约数*最小公倍数

    public static int max(int x, int y){
       int z = y;
        while ( x % y != 0) {
            z = x % y;
            x = y;
            y = z;
        }
        return z;
    }

    public static void main(String[] args) {
        System.out.println(max(238, 306));
    }
}
