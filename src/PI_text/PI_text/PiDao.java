package PI_text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * π 数字查找器。
 *
 * 数据文件 pi6.txt 格式:第一行 "3.",之后每行 50 位数字。
 * 本类一次性将全部行读入内存(1412 万字符约 14MB),采用"逐行 + 行间窗口"方式查找:
 * 每一行除常规 indexOf 外,还会把「当前行尾 + 下一行开头」拼接成一个窗口再查一次,
 * 从而解决目标数字恰好跨行时的漏查问题。
 */
public class PiDao {

    /** 数据行列表:index 0 为 "3.",index 1 起为每行 50 位数字 */
    private final List<String> rows = new ArrayList<>();

    /** 每行固定位数 */
    private static final int LINE_WIDTH = 50;

    public PiDao() throws IOException {
        String filePath = locateDataFile();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    rows.add(trimmed);
                }
            }
        }
        if (rows.isEmpty()) {
            throw new IOException("pi6.txt 为空或无法读取");
        }
    }

    /** 查找目标数字串在 π 小数点后的位数(从 1 开始计数),找不到返回 -1 */
    public long findNumber(String target) {
        if (target == null || target.isEmpty()) {
            return -1;
        }
        int len = target.length();

        for (int i = 1; i < rows.size(); i++) {
            String row = rows.get(i);

            // 1) 常规查找:目标完整落在本行内
            int idx = row.indexOf(target);
            if (idx != -1) {
                return (long) (i - 1) * LINE_WIDTH + idx + 1;
            }

            // 2) 跨行查找:目标可能跨在本行末尾 + 下一行开头
            if (i + 1 < rows.size() && row.length() >= len - 1) {
                String next = rows.get(i + 1);
                String window = row.substring(row.length() - (len - 1)) + next;
                int widx = window.indexOf(target);
                if (widx != -1) {
                    // 窗口起点 = 当前行末尾的 (len-1) 位处
                    long posInRow = (row.length() - (len - 1)) + widx;
                    return (long) (i - 1) * LINE_WIDTH + posInRow + 1;
                }
            }
        }
        return -1;
    }

    /** 字母转数字(a=1,b=2,...,z=26;大小写均可),再复用 findNumber */
    public long findLetters(String letters) {
        StringBuilder sb = new StringBuilder();
        for (char c : letters.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sb.append(c - 'a' + 1);
            } else if (c >= 'A' && c <= 'Z') {
                sb.append(c - 'A' + 1);
            }
        }
        return findNumber(sb.toString());
    }

    /** 查找数据文件:优先当前目录,其次 src 子目录(兼容两种运行方式) */
    private String locateDataFile() {
        String[] candidates = {
                "pi6.txt",
                "src/PI_text/PI_text/pi6.txt"
        };
        for (String path : candidates) {
            if (java.nio.file.Files.exists(java.nio.file.Path.of(path))) {
                return path;
            }
        }
        return candidates[0];
    }
}
