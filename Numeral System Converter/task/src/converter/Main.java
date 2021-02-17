package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int sourceBase = Integer.parseInt(scanner.nextLine());
            String number = scanner.nextLine();
            int newBase = Integer.parseInt(scanner.nextLine());

            if (checkRadix(sourceBase) && checkRadix(newBase) && checkNumber(number)) {
                String decimal = Converter.convertToDecimalBase(number, sourceBase);
                String resultNumber = Converter.convertToNewNonDecimalBase(decimal, newBase);
                System.out.println(resultNumber);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error!");
        }
    }

    public static boolean checkRadix(int radix) {
        if (radix > 36 || radix < 1) {
            System.out.printf("Error! Wrong %s radix.", radix);
            return false;
        }
        return true;
    }

    public static boolean checkNumber(String number) {
        return number.matches("[0-9a-zA-Z]+(\\.[0-9a-zA-Z]+)?");
    }
}
