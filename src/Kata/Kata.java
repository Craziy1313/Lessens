package Kata;

import Calculate.Romain;

import java.util.Scanner;
import java.util.TreeMap;

public class Kata {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите 2 числа (Римских или Арабских): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));

    }

    public static String calc(String input) throws Exception {
        String[] data;
        String action;
        Romain romain = new Romain();
        int number1 = 0;
        int number2 = 0;
        int number3 = 0;
        String calculate = input;


        calculate = calculate.replace(" ", "");
        if (calculate.contains("+")) {
            data = calculate.split("\\+");
            action = "+";
        } else if (calculate.contains("-")) {
            data = calculate.split("-");
            action = "-";
        } else if (calculate.contains("*")) {
            data = calculate.split("\\*");
            action = "*";
        } else if (calculate.contains("/")) {
            data = calculate.split("/");
            action = "/";
        } else
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        if (data.length !=2) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        if (romain.isRomain(data[0]) == romain.isRomain(data[1])) {
            boolean isRomain = romain.isRomain(data[0]);

            if (isRomain) {
                number1 = romain.romeToArabian(data[0]);
                number2 = romain.romeToArabian(data[1]);
                if (number1 > 10 || number1 <= 0 || number2 > 10 || number2 <= 0)
                    throw new Exception("Ввели неверное число");
            } else {
                number1 = Integer.parseInt(data[0]);
                number2 = Integer.parseInt(data[1]);
                if (number1 > 10 || number1 <= 0 || number2 > 10 || number2 <= 0)
                    throw new Exception("Ввели неверное число");
            }
            switch (action) {
                case "+":
                    number3 = number1 + number2;
                    break;
                case "-":
                    number3 = number1 - number2;
                    break;
                case "*":
                    number3 = number1 * number2;
                    break;
                case "/":
                    number3 = number1 / number2;
                    break;
            }
            if (isRomain) {
                if (number3 <= 0) throw new Exception("т.к. в римской системе нет отрицательных чисел");
                String number4= romain.arabianToRome(number3);
                return number4;
            } else {
                String number4 = Integer.toString(number3);
                return number4;
            }
        } else throw new Exception("Используются одновременно разные системы счисления");
    }

    static class Romain {
        TreeMap<Character, Integer> rom = new TreeMap<>();
        TreeMap<Integer, Character> arab = new TreeMap<>();

        Romain() {

            rom.put('C', 100);
            rom.put('L', 50);
            rom.put('X', 10);
            rom.put('V', 5);
            rom.put('I', 1);
            arab.put(100, 'C');
            arab.put(50, 'L');
            arab.put(10, 'X');
            arab.put(5, 'V');
            arab.put(1, 'I');
        }

        boolean isRomain(String number) {
            return rom.containsKey(number.charAt(0));
        }

        int romeToArabian(String s) {
            int arabian = 0;
            int out = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                int current = rom.get(s.charAt(i));
                if (current < out) {
                    arabian -= current;
                } else {
                    arabian += current;
                }
                out = current;
            }
            return arabian;
        }
        String arabianToRome(int number) {
            String rom = "";
            int arabian;
            do {
                arabian = arab.floorKey(number);
                rom += arab.get(arabian);
                number -= arabian;
            } while (number != 0);
            return rom;
        }
    }
}

