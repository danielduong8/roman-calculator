package com.example.daniel.RomanCalculator;

/*
© 2018 Daniel Duong
@author danielduong8@gmail.com
 _____     ______     __   __     __     ______     __            _____     __  __     ______     __   __     ______
/\  __-.  /\  __ \   /\ "-.\ \   /\ \   /\  ___\   /\ \          /\  __-.  /\ \/\ \   /\  __ \   /\ "-.\ \   /\  ___\
\ \ \/\ \ \ \  __ \  \ \ \-.  \  \ \ \  \ \  __\   \ \ \____     \ \ \/\ \ \ \ \_\ \  \ \ \/\ \  \ \ \-.  \  \ \ \__ \
 \ \____-  \ \_\ \_\  \ \_\\"\_\  \ \_\  \ \_____\  \ \_____\     \ \____-  \ \_____\  \ \_____\  \ \_\\"\_\  \ \_____\
  \/____/   \/_/\/_/   \/_/ \/_/   \/_/   \/_____/   \/_____/      \/____/   \/_____/   \/_____/   \/_/ \/_/   \/_____/

*/


public class Numeral {
    public static final int MAX_VALUE = 3999;
    public static final int MIN_VALUE = 1;
    private static final String VALID_ROMAN = "IVXLCDM";
    private static final int[] ROMAN_VALUES = {1, 5, 10, 50, 100, 500, 1000};
    private String roman;
    private int value;
    private boolean isValid;

    /**
     * Constructs a Numeral object given a string in the form of a roman numeral.
     * Checks if the string given string is valid.
     *
     * @param s the string to be converted into a Numeral object
     */
    public Numeral(String s) {
        set(s);
    }

    /**
     * Constructs a Numeral object given an integer. Checks if the given integer is
     * valid and raises an error if otherwise.
     *
     * @param i any integer between and including MIN_VALUE-MAX_VALUE
     */
    public Numeral(int i) {
        set(i);
    }

    /**
     * Returns the integer value of the Numeral object.
     *
     * @return the integer value
     */
    public int getValue() {
        return value;
    }

    public String getRoman() {
        return roman;
    }

    public boolean checkValid() {
        return isValid;
    }

    /**
     * Returns the roman of the Numeral object as a string.
     *
     * @return the string of roman roman
     */
    public String toString() {
        return String.valueOf(value) + "\t" + roman;
    }

    /**
     * Sets instance attributes if string produces a valid roman numeral,
     * or sets isValid attribute to false otherwise.
     *
     * @param s the string of roman roman
     */
    public void set(String s) {
        String characters = "IVXLCDM";
        for (int j = 0; j < s.length(); j++) {
            if (!characters.contains("" + s.charAt(j))) {
                isValid = false;
                return;
            }
        }
        int i = romanToInt(s);
        if (i > MAX_VALUE || !intToRoman(i).equals(s)) {
            isValid = false;
            return;
        }
        value = i;
        roman = s;
        isValid = true;
    }

    /**
     * Sets instance attributes if integer is valid,
     * or sets isValid attribute to false otherwise.
     *
     * @param i an integer value
     */
    public void set(int i) {
        if (i < MIN_VALUE || i > MAX_VALUE) {
            isValid = false;
            return;
        }
        value = i;
        roman = intToRoman(i);
        isValid = true;
    }

    /**
     * Returns the roman equivalent of the given integer as a String.
     *
     * @param num an integer
     * @return the roman equivalent of the given integer as a String.
     */
    public static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (num != 0) {
            int remainder = num % 10;
            if (remainder == 4) {
                result.insert(0, VALID_ROMAN.charAt(i + 1));
                result.insert(0, VALID_ROMAN.charAt(i));
            } else if (remainder == 9) {
                result.insert(0, VALID_ROMAN.charAt(i + 2));
                result.insert(0, VALID_ROMAN.charAt(i));
            } else {
                for (int j = 0; j < remainder % 5; j++) {
                    result.insert(0, VALID_ROMAN.charAt(i));
                }
                if (remainder >= 5) {
                    result.insert(0, VALID_ROMAN.charAt(i + 1));
                }
            }
            i += 2;
            num /= 10;
        }
        return result.toString();
    }

    /**
     * Returns the integer equivalent of the given roman numeral represented by s.
     *
     * @param s a roman numeral
     * @return the integer equivalent of the given roman numeral represented by s.
     */
    public static int romanToInt(String s) {
        int total = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            int letter = ROMAN_VALUES[VALID_ROMAN.indexOf(s.charAt(i))];
            int adjLetter = ROMAN_VALUES[VALID_ROMAN.indexOf(s.charAt(i + 1))];
            if (letter >= adjLetter) {
                total += letter;
            } else {
                total -= letter;
            }
        }
        total += ROMAN_VALUES[VALID_ROMAN.indexOf(s.charAt(s.length() - 1))];
        return total;
    }
}
