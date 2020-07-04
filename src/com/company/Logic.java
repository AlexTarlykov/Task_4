package com.company;

public class Logic {

    public static void sort(String[] input) {
        int gap = input.length;
        boolean swapped = true;
        while (gap > 1 || swapped) {
            if (gap > 1)
                gap = (int) (gap / 1.247330950103979);

            int i = 0;
            swapped = false;
            while (i + gap < input.length) {
                if (countOfVowels(input[i]) < countOfVowels(input[i + gap])) {
                    String t = input[i];
                    input[i] = input[i + gap];
                    input[i + gap] = t;
                    swapped = true;
                } else if (countOfVowels(input[i]) == countOfVowels(input[i + gap])) {
                    if (input[i].toCharArray().length > input[i + gap].toCharArray().length) {
                        String t = input[i];
                        input[i] = input[i + gap];
                        input[i + gap] = t;
                        swapped = true;
                    }
                }
                i++;
            }
        }
    }

    private static int countOfVowels(String word) {
        int count = 0;
        for (int i = 0; i < word.toCharArray().length; i++) {
            if (word.toCharArray()[i] == 'a' || word.toCharArray()[i] == 'e' ||
                    word.toCharArray()[i] == 'i' || word.toCharArray()[i] == 'o' ||
                    word.toCharArray()[i] == 'u' || word.toCharArray()[i] == 'y' ||
                    word.toCharArray()[i] == 'A' || word.toCharArray()[i] == 'E' ||
                    word.toCharArray()[i] == 'I' || word.toCharArray()[i] == 'O' ||
                    word.toCharArray()[i] == 'U' || word.toCharArray()[i] == 'Y') {
                count++;
            }
        }
        return count;
    }
}
