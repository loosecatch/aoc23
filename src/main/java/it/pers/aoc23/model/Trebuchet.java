package it.pers.aoc23.model;

import it.pers.aoc23.model.days.Day;
import it.pers.aoc23.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static it.pers.aoc23.utils.Utils.loadFile;

public class Trebuchet implements Day {
    public String filename;

    public static Utils.DayLoader<Integer> firstLoader = new Utils.DayLoader<Integer>() {
        @Override
        public Integer load(BufferedReader bufferedReader) throws IOException {
            String line = bufferedReader.readLine();
            int sum = 0;
            while (line != null){
                System.out.println(line);
                var digits = line.replaceAll("[A-Za-z]","");
                System.out.println("digits: "+digits);
                var number = "";
                if (digits.length()==1) number=digits+digits;
                else {
                    number = String.valueOf(digits.charAt(0)) + String.valueOf(digits.charAt(digits.length()-1));
                }
                System.out.println(number);
                sum += Integer.parseInt(number);
                line = bufferedReader.readLine();
            }
            return sum;
        }
    };

    public static Utils.DayLoader<Integer> secondLoader = new Utils.DayLoader<Integer>() {
        @Override
        public Integer load(BufferedReader bufferedReader) throws IOException {
            String line = bufferedReader.readLine();
            int sum = 0;
            while (line != null){
                System.out.println("line "+line);
                var digits = extractNumbers(line);
                System.out.println("digits: "+digits);
                var number = digits.get(0) + digits.get(digits.size()-1);
                System.out.println("number: "+number);
                sum += Integer.parseInt(number);
                line = bufferedReader.readLine();
            }
            return sum;
        }
    };

    public Trebuchet(String filename){
        this.filename=filename;
    }
    @Override
    public void partOne() {
        System.out.println(this.getName(filename)+"part one: "+loadFile(filename,firstLoader));
    }

    @Override
    public void partTwo() {
        System.out.println(this.getName(filename)+"part two: "+loadFile(filename,secondLoader));
    }

    private static List<String> extractNumbers(String input) {
        List<String> numbers = new ArrayList<>();
        String[] numberWords = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" ,"0", "1", "2", "3", "4", "5", "6", "7", "8" ,"9"};
        int i = 0;
        while (i < input.length()) {
            boolean found = false;
            for (String numberWord : numberWords) {
                if (input.startsWith(numberWord, i)) {
                    numbers.add(String.valueOf(findIndex(numberWords, numberWord)%10));
                    int sub = numberWord.length();
                    i += sub>1 ? sub-1 : sub;
                    found = true;
                    break;
                }
            }
            if (!found) {
                i++;
            }
        }

        return numbers;
    }

    private static int findIndex(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1; // Not found
    }
}
