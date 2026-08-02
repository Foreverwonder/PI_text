package PI_text;

import java.util.Scanner;

public class PiTest {

    public static void main(String[] args) {
        PiDao dao;
        try {
            dao = new PiDao();
        } catch (Exception e) {
            System.out.println("初始化失败: " + e.getMessage());
            System.out.println("请确认 pi6.txt 位于当前目录或 src/PI_text/PI_text/ 下。");
            return;
        }

        try (Scanner scanf = new Scanner(System.in)) {
            System.out.println("========================================");
            System.out.println("  π 查找器 · 在 π 小数点后 1412 万位中查找");
            System.out.println("  输入数字(如 141592)或字母(如 abc,按 a=1..z=26 编码)");
            System.out.println("  输入 exit 退出");
            System.out.println("========================================");

            while (scanf.hasNextLine()) {
                System.out.print("请输入: ");
                String input = scanf.nextLine().trim();

                if (input.isEmpty()) {
                    continue;
                }
                if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                    System.out.println("再见!");
                    break;
                }

                long position;
                if (input.matches("\\d+")) {
                    position = dao.findNumber(input);
                } else if (input.matches("[a-zA-Z]+")) {
                    position = dao.findLetters(input);
                } else {
                    System.out.println("输入格式不正确,请输入纯数字或纯字母。");
                    continue;
                }

                if (position > 0) {
                    System.out.println("找到啦! 在 π 小数点后第 " + position + " 位");
                    System.out.println("3.1415926......");
                } else {
                    System.out.println("呜呜呜在 1412 万位里没找到...(数字越长或字母越多,概率越低哦)");
                }
            }
        }
    }
}
