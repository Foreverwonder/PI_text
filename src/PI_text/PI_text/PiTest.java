package PI_text;

import java.util.Scanner;

public class PiTest {
    public static Scanner scanf = new Scanner(System.in);

    public static void main(String[] args) {
        PiDao pd = new PiDao();

        System.out.println("请输入你想寻找的六位数字：");
        while (scanf.hasNextLine()) {
            String _pi_num = scanf.nextLine();
            pd.findPiNumber(_pi_num);
            System.out.println("请在滴的一声后输入你的数字：");
        }

        System.out.println("请输入你想寻找的三位字母：");
        while (scanf.hasNextLine()) {
            String _pi_letter = scanf.nextLine();
            pd.findThreeLetter(_pi_letter);
            System.out.println("请输入你想寻找的三位字母：");
        }
    }
}


