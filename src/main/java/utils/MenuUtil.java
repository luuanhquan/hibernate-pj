package utils;

import java.util.Scanner;

public class MenuUtil {
    public static Boolean Continue(){
        System.out.print("Nhập tiếp? (Y/N): ");
        String tiep = new Scanner(System.in).nextLine();
        return ("y").equalsIgnoreCase(tiep);
    }
}
