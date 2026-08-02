package PI_text;

public class PiCheck {
    public static void main(String[] args) {
        try {
            PiDao dao = new PiDao();

            // 测试用例: [输入, 期望位置(从 pi6.txt 全量拼接后验证的准确值)]
            String[][] cases = {
                {"141592", "1"},       // π 开头,普通行内
                {"535897", "8"},       // 行内
                {"314159", "176451"},  // 行内
                {"000000", "1699927"}, // 行内
                {"999999", "762"},     // 行内
                {"375105", "46"},      // 跨行案例(原程序找不到!)
                {"510582", "48"},      // 跨行案例(原程序找不到!)
                {"105820", "49"},      // 跨行案例
                {"abc", "1924"},       // a=1,b=2,c=3 -> 123, 整体拼接法验证=1924
            };

            boolean allPass = true;
            for (String[] c : cases) {
                String input = c[0];
                String expected = c[1];
                long actual = input.matches("\\d+")
                        ? dao.findNumber(input)
                        : dao.findLetters(input);
                boolean pass = String.valueOf(actual).equals(expected);
                if (!pass) allPass = false;
                System.out.println((pass ? "✅" : "❌") + " " + input
                        + " -> 实际:" + actual + " 期望:" + expected);
            }

            // 额外:字母测试(a=1,b=2,c=3 => "123" 出现在 π 第 4 位: 3.14159... 中的 "141"? 不是,用 findLetters 独立验证)
            System.out.println("\n--- 字母编码验证 ---");
            System.out.println("abc(a=1,b=2,c=3=123) -> " + dao.findLetters("abc"));
            System.out.println("AB(c=?) -> " + dao.findLetters("AB"));

            System.out.println("\n" + (allPass ? "🎉 全部通过!" : "⚠️ 存在失败用例"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
