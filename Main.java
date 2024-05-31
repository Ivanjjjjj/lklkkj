import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.


        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
    }
}

class Calc {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа ");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int nomer1;
        int nomer2;
        String plusminus;
        String resultat;
        boolean biba;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть два операнда");
        plusminus = detectOperation(expression);
        if (plusminus == null) throw new Exception("Неподдерживаемая математическая операция");
        if (Cifr.isRim(operands[0]) && Cifr.isRim(operands[1])) {
            nomer1 = Cifr.convertToArabic(operands[0]);
            nomer2 = Cifr.convertToArabic(operands[1]);
            biba = true;
        }
        //если оба числа арабские
        else if (!Cifr.isRim(operands[0]) && !Cifr.isRim(operands[1])) {
            nomer1 = Integer.parseInt(operands[0]);
            nomer2 = Integer.parseInt(operands[1]);
            biba = false;
        }
        //если одни число римское, а другое - арабское
        else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (nomer1 > 10 || nomer2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(nomer1, nomer2, plusminus);
        if (biba) {

            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }

            resultat = Cifr.convertToRim(arabian);
        } else {

            resultat = String.valueOf(arabian);
        }

        return resultat;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {

        return switch (oper) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }

}

class Cifr {
    static String[] cifrArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean isRim(String val) {
        for (String s : cifrArray) {
            if (val.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabic(String dude) {
        for (int i = 0; i < cifrArray.length; i++) {
            if (dude.equals(cifrArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRim(int arabic) {
        return cifrArray[arabic];
    }

}
