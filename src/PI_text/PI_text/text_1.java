package PI_text;

import java.util.Scanner;

public class text_1 {
    public static Scanner scanf = new Scanner(System.in);
    public static void main(String[] args) {
        Pi_Text p=new Pi_Text();
        java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
        System.out.println("请在滴的一声后输入您的银行卡密码：");
        tk.beep();
        while (scanf.hasNextLine()) {
            String _pi_num=scanf.nextLine();
            p.findPiNumber(_pi_num);
            System.out.println("请在滴的一声后输入您的银行卡密码：");
            tk.beep();
        }
    }
}
