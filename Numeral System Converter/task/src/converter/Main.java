package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int sourceBase = Integer.parseInt(scanner.nextLine());
            String number = scanner.nextLine();
            int newBase = Integer.parseInt(scanner.nextLine());
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                    'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            if (checkRadix(sourceBase) && checkRadix(newBase)) {
                String decimal = convertToDecimalBase(number, sourceBase, chars);
                String resultNumber = convertToNewNonDecimalBase(decimal, newBase, chars);
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

    public static String convertToDecimalBase(String number, int radix, char[] chars) {
        String decimal;
        if (radix == 1) {
            int temp = 0;
            for (int i = 0; i < number.length(); i++) {
                temp++;
            }
            decimal = String.valueOf(temp);
        } else if (radix == 10) {
            decimal = number;
        } else {
            decimal = covertToDecimal(number, radix, chars);
        }
        return decimal;
    }

    private static String covertToDecimal(String number, int radix, char[] chars) {
        if (number.contains(".")) {
            String[] parts = number.split("\\.");
            double decimal = Integer.parseInt(parts[0], radix);
            String[] fractions = parseNumber(parts[1], chars);
            for (int i = 0; i < fractions.length; i++) {
                decimal += Double.parseDouble(String.valueOf(fractions[i])) / Math.pow(radix, i + 1);
            }
            return Double.toString(decimal);
        } else {
            return String.valueOf(Integer.parseInt(number, radix));
        }
    }

    public static String convertToNewNonDecimalBase(String decimal, int radix, char[] chars) {
        String number;
        if (radix == 1) {
            number = "1".repeat(Math.max(0, Integer.parseInt(decimal)));
        } else if (radix == 10) {
            number = decimal;
        } else {
            number = convertToNewBase(decimal, radix, chars);
        }
        return number;
    }

    private static String convertToNewBase(String decimal, int radix, char[] chars) {
        if (decimal.contains(".")) {
            String[] parts = decimal.split("\\.");
            String integer = Integer.toString(Integer.parseInt(parts[0]), radix);
            StringBuilder fractional = new StringBuilder();
            double reminder = Double.parseDouble("0." + parts[1]);
            int temp;
            do {
                temp = (int) (reminder * radix);
                fractional.append(chars[temp]);
                reminder = reminder * radix - temp;
            } while (fractional.length() != 5);

            return integer + "." + fractional;

        } else {
            return Integer.toString(Integer.parseInt(decimal), radix);
        }
    }

    private static String[] parseNumber(String number, char[] chars) {
        String[] result = new String[number.length()];
        for (int i = 0; i < result.length; i++) {
            int index = findIndex(chars, number.charAt(i));
            if (index == -1) {
                continue;
            }
            result[i] = String.valueOf(index);
        }
        return result;
    }

    private static int findIndex(char[] chars, char element) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == Character.toLowerCase(element)) {
                return i;
            }
        }
        return -1;
    }
}
