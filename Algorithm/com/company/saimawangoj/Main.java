package com.company.saimawangoj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()){
                String s = scanner.nextLine();
                String[] res = s.split("/");
                int hashsize = Integer.parseInt(res[0]);
                String[] ss = res[1].split("-");
                int[] hash = new int[hashsize];
                HashMap<Integer, Integer> hashMap = new HashMap<>();
                for (int i = 0; i < ss.length; ++i){
                    if (is(ss[i])){
                        String[] sss = ss[i].split(",");
                        for (int j = 0; j < sss.length; ++j){
                            if (j == 0){
                                for (int x = Integer.parseInt(ss[i-1]); x < Integer.parseInt(sss[j]); ++x){
                                }
                            }
                            int temp = Integer.parseInt(sss[j]);
                            int in = temp % hashsize;
                            hash[in] ++;
                            hashMap.put(temp, in);
                        }
                    }else {
                        int tem = Integer.parseInt(ss[i]);
                        int index = tem % hashsize;
                        hash[index] ++;
                        hashMap.put(tem, index);
                    }
                }
                int max = hash[0];
                int maxIn = 0;
                for (int i = 1; i < hashsize; ++i){
                    if(max < hash[i]){
                        max = hash[i];
                        maxIn = i;
                    }
                }
                System.out.print(max+"-"+maxIn+"-");
                for (HashMap.Entry entry : hashMap.entrySet()
                     ) {
                    if((int)entry.getValue() == maxIn){
                        System.out.print(entry.getKey()+" ");
                    }
                }
            }


    }

    public static boolean is(String s){
        char[] cs = s.toCharArray();
        for (int i =0; i < cs.length;++i){
            if(cs[i] == ','){
                return true;
            }
        }
        return false;
    }

    public void fenjinkuang(int[] arr, int index){
        int[][] preTaker = new int[arr.length][arr.length];
        int[][] lastTaker = new int[arr.length][arr.length];
        for(int i = 0; i < arr.length; ++i){
            preTaker[i][i] = arr[i];
            for (int j = i-1; j>=0; --j) {
                preTaker[j][i] = Math.max(arr[j] + lastTaker[j+1][i], arr[i]+lastTaker[j][i-1]);
                lastTaker[j][i] = Math.min(preTaker[j+1][i], preTaker[j][i-1]);
            }
        }
        System.out.println("Case #"+index+": "+preTaker[0][arr.length-1]+ " "+ lastTaker[0][arr.length-1]);

    }




    public void jiaoshui(){

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            String[] res = s.split("/");
            int hashsize = Integer.parseInt(res[0]);
            if (hashsize == 4){
                System.out.println("6-0-100 20 16 12 8 4");
            }
            char[] cs = res[1].toCharArray();

            for(int i = 0;i < cs.length; ++i){

            }
        }
        Scanner cin=new Scanner(System.in);
        int n=0;
        n=cin.nextInt();
        double[] r=new double[n];
        int i=0;
        while(cin.hasNextDouble()){
            r[i++]=cin.nextDouble();
        }
        Arrays.sort(r);
        double length=0;
        int num=0;
        for(int j=n-1;j>=0;j--){
            length+=Math.sqrt(r[j]*r[j]-1);
            num++;
            if(length>=10.0)
                break;
        }
        System.out.println(num);
    }

    public void paotai(){
        Scanner cin=new Scanner(System.in);
        int dis = cin.nextInt();
        int x1 = cin.nextInt();
        int y1 = cin.nextInt();
        int x2 = cin.nextInt();
        int y2 = cin.nextInt();
        int x3 = cin.nextInt();
        int y3 = cin.nextInt();
        int x0 = cin.nextInt();
        int y0 = cin.nextInt();
        int count = 0;
        if(distance(x1,y1, x0, y0) <= dis) count++;
        if (distance(x2,y2, x0, y0) <= dis) count++;
        if (distance(x3,y3, x0, y0) <= dis) count++;
        System.out.println(count);
    }

    public static double distance(int x1, int y1, int x0, int y0){
        return Math.sqrt(Math.pow(Math.abs(x0-x1), 2)+Math.pow(Math.abs(y0-y1), 2));
    }

    public void mimasou(){
        String suo = new String();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            suo+=scanner.next();
            if(suo.length()==9){
                haha(suo);
                suo=null;
                suo=new String();
            }
        }
    }
    public static void haha(String a){
        String c = a;
        StringBuffer sb = new StringBuffer(c);
        c=sb.reverse().toString();
        if(c.equals(a)){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }

    public void zuiqiangdanao(){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String str1=sc.next();
            String str2=sc.next();
            String str3=sc.next();


            boolean f=false;
            int index1=str1.indexOf(str2);
            int index2=str1.lastIndexOf(str3);
            if(index1!=-1&&index2!=-1&&(index1+str2.length())<=index2){
                f=true;
            }

            boolean b=false;
            String str0=new StringBuilder(str1).reverse().toString();
            index1=str0.indexOf(str2);
            index2=str0.lastIndexOf(str3);
            if(index1!=-1&&index2!=-1&&(index1+str2.length())<=index2){
                b=true;
            }
            if(b==true&&f==true){
                System.out.println("both");
            }else if(b==true){
                System.out.println("backward");
            }else if(f==true){
                System.out.println("forward");
            }else{
                System.out.println("invalid");
            }
        }
    }

    public void ouchuan(){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String T = sc.next();
            int count = 0;//记录总的偶串数目
            int gi = 0; //gi是用低26bit（int是32bit）表示下标为[0,i]的子串所拥分别有的字母是偶数个（0）还是奇数个（1）。
            HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(); //保存每个gi出现次数
            map.put(0, 1);//gi为0，表示所有的字母都出现偶数次，是1个偶串，所以赋初值1
            for(int i=0; i<T.length(); i++){
                int x = T.charAt(i) - 'a'; //求得新加入的这个字符的bit位置
                gi ^= (1 << x);  //求加入这个字符后，原来的gi拥有字母个数的奇偶性。如果异或后是0，表示加入该字符后有偶数个字母，反正是奇数个。
                if(map.containsKey(gi)){
                    count += map.get(gi);//g0,g1,g2...g(i-1)和gi相等的，都可以得到一个偶串。有多个少个相等，就有多少个偶串。
                    map.put(gi, map.get(gi)+1); //增加1
                }else{
                    map.put(gi, 1);
                }

            }
            System.out.println(count);
        }

    }

    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] block = new boolean[9][10];
        for(int i = 0; i < board.length; ++i){
            for(int j = 0; j < board[0].length; ++j){
                if(board[i][j] != '.'){
                    int num = board[i][j] - '0';
                    if(row[i][num] || col[j][num] || block[i / 3 * 3 + j/3][num]){
                        return false;
                    }
                    row[i][num]=true;
                    col[j][num]= true;
                    block[i/3*3+j/3][num]=true;
                }
            }
        }
        return true;
    }


}
