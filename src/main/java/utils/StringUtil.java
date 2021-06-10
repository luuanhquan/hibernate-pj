package utils;

public class StringUtil {
    public static boolean isEmpty(String string) {
        if ("".equals(string)) {
            System.out.println("Không được để trống");
        }
        return "".equals(string);
    }
}
