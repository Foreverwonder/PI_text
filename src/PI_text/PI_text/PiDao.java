package PI_text;
/*
 * 读取一个text文件并输出
 * */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class PiDao {
    public void findPiNumber(String _pi_num) {
//        String filePath = "d:\\pi6.txt";//14million
//        String filePath = "pi6.txt";//14million
        String filePath = "pi6.txt";//14million
        int pi_line = 0;
        try {
            FileInputStream inputStream = null;
            Scanner sc = null;
            try {
                inputStream = new FileInputStream(filePath);
                sc = new Scanner(inputStream, "utf-8");
                while (sc.hasNextLine()) {
                    pi_line++;
                    String line = sc.nextLine();
                    if ((line.indexOf(_pi_num)) != -1) {
                        System.out.println("你输入的数字" + _pi_num + "在π3.1415926.....后第" + (50 * (pi_line - 2) + line.indexOf(_pi_num) + 1) + "位找到啦");
                        System.out.println("该数字及周围数字可见为：\n3.14......" + line + "...");
                        break;
                    }
                }
                if (sc.hasNextLine()==false){
                    System.out.println("呜呜呜本机算力有限没找到...(tips:若是超过六位数，找到的概率会大幅下降哦)");
                }
//                System.out.println("测试数值"+sc.hasNextLine());==false
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (sc != null) {
                    sc.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findThreeLetter(String _pi_letter) {
        String filePath = "pi6.txt";//14million
        int pi_line = 0;
        String strIn = _pi_letter;
        char[] cIn= new char[20];
        cIn = strIn.toCharArray();
        String str="";
//        for (int i = 0; i < strIn.length(); i++) {
//            System.out.print(cIn[i]);
//        }
        for (int i = 0; i < strIn.length(); i++) {
            if (cIn[i] >= 'a' && cIn[i] <= 'z') {
//                cOut[i] = (char) ((int) cIn[i] - 96);
                str+= ((int) cIn[i] - 96);
//                System.out.println(cOut[i]);
//                System.out.println((char)((int) cIn[i]-96));

            } else if (cIn[i] >= 'A' && cIn[i] <= 'Z') {
//                cOut[i] = (char) ((int) cIn[i] - 64);
                str+= ((int) cIn[i] - 64);
//                System.out.println(cOut[i]);
//                System.out.println((char)((int) cIn[i]-64));
            }
        }


        try {
            String _pi_num=str;
            FileInputStream inputStream = null;
            Scanner sc = null;
            try {
                inputStream = new FileInputStream(filePath);
                sc = new Scanner(inputStream, "utf-8");
                while (sc.hasNextLine()) {
                    pi_line++;
                    String line = sc.nextLine();
                    if ((line.indexOf(_pi_num)) != -1) {
                        System.out.println("你输入的英文字母--" +_pi_letter+"--按a=1,b=2,c=3..编码成的数字--"+ _pi_num + "--在π里3.1415926.....后第" + (50 * (pi_line - 2) + line.indexOf(_pi_num) + 1) + "位找到啦");
                        System.out.println("该数字及周围数字可见为：\n3.14......" + line + "...");
                        break;
                    }
                }if (sc.hasNextLine()==false){
                    System.out.println("呜呜呜本机算力有限没找到...(tips:若是字母编码后超过六位数，找到的概率会大幅下降哦)");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (sc != null) {
                    sc.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}