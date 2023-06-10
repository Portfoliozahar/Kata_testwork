import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, Integer> RomNum = new HashMap<>();
    private static final Map<Integer, String> ArabNum = new HashMap<>();

    // Создал и инициализировал массив для римских цифр
    // можно добавлять числа до M 1000
    // но в данном ТЗ можно получить максимум 100 ( 10 * 10 или X * X)

    static {
        String[] romanNumerals = {
                "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        for (int i = 1; i <= 100; i++) {
            if (i <= 20 || i % 10 == 0) {
                RomNum.put(romanNumerals[i - 1], i);
            }

            ArabNum.put(i, romanNumerals[i - 1]);
        }
    }




    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите выражение: ");
            String input = scanner.nextLine();
            String res = calc(input);
            System.out.println("Ответ: "+ res);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) {
        String[] part = input.split("\\s+");
        if (part.length != 3) {
            if (part.length == 1) {
                throw new IllegalArgumentException("Строка не является математической операцией.");
            } else {
                throw new IllegalArgumentException(
                        "Формат математической операции неверный (или нужен пробел).");
            }
        }

        String a = part[0];
        String operator = part[1];
        String b = part[2];

        boolean aCheckRom = RomNum.containsKey(a);
        boolean bCheckRom = RomNum.containsKey(b);

        // проверяем введенны арабские или римские для вывода нужного ответа или
        // эксепшона

        if (aCheckRom && bCheckRom) {
            int aNum = ConvertRom(a);
            int bNum = ConvertRom(b);
            int res = Operation(aNum, operator, bNum);
            if (res <= 0) {
                throw new IllegalArgumentException(
                        "Римские цифры не могут представлять ноль или отрицательные числа.");
            }
            return ArabNum.get(res);

        } else if (!aCheckRom && !bCheckRom) {
            int aNum = ConvertArab(a);
            int bNum = ConvertArab(b);
            int res = Operation(aNum, operator, bNum);

            return Integer.toString(res);
        } else {
            throw new IllegalArgumentException(
                    "Используйте римские и арабские цифры отдельно друг от друга.");
        }
    }

    private static int Operation(int a, String operator, int b) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new IllegalArgumentException("Делить на ноль нельзя.");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Доступны только операторы +, -, *, /..");
        }
    }

    // конвертируем для проверки арабские
    private static int ConvertArab(String number) {

        int num = Integer.parseInt(number);
        if (num < 1 || num > 10) {
            throw new IllegalArgumentException(
                    "Калькулятор может принимать на вход только числа от 1 до 10 включительно, не более.");
        }
        return num;

    }

    // конвертируем для проверки римские
    private static int ConvertRom(String number) {
        int num2 = RomNum.get(number);

        if (num2 < 1 || num2 > 10) {
            throw new IllegalArgumentException(
                    "Калькулятор может принимать на вход только числа от I до X включительно, неболее.");
        }

        return num2;
    }

}
