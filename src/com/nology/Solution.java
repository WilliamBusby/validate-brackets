package com.nology;

/*
 * Click `Run` to execute the snippet below!
 */

import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }
        System.out.println(validateBrackets("{ [] ( ) }")); // 1
        System.out.println(validateBrackets("{ [(] ) }")); // 0
        System.out.println(validateBrackets("{ [ }")); // 0
        System.out.println(validateBrackets("test")); // 1
        System.out.println(validateBrackets("test()")); // 1
        System.out.println(validateBrackets("test(){")); // 0
    }

    public static int validateBrackets(String inputCode) {
        String onlyBrackets = inputCode.replaceAll("([^\\[\\](){}])",""); // Only brackets
        List<Character> openers = Arrays.asList('(','{','['); // Opening brackets
        List<Character> closers = Arrays.asList(')','}',']'); // Closing brackets
        if(onlyBrackets.length() == 0) return 1; // If empty, no brackets so valid
        if(!(checkBracketPairs(onlyBrackets, "\\[\\]")
                && checkBracketPairs(onlyBrackets,"()")
                && checkBracketPairs(onlyBrackets, "{}")) || // Validate number of each bracket is even
                !openers.contains(onlyBrackets.charAt(0)) || // Check first bracket is an opening one
                !closers.contains(onlyBrackets.charAt(onlyBrackets.length()-1))) // Check final bracket is closing
            return 0;
        for(int i = 0; i<onlyBrackets.length()-1; i++) { // Checking every bracket except last
            char currentChar = onlyBrackets.charAt(i); // Current character
            char nextChar = onlyBrackets.charAt(i+1); // Next character
            if(invalidConsecutiveBrackets(currentChar, nextChar, openers, closers)) return 0; // Checks invalid brackets
        }
        return 1; // If everything else passes, return 1
    }

    public static boolean checkBracketPairs(String onlyBrackets, String regex) {
        return onlyBrackets.replaceAll("([^" + regex + "])", "").length() % 2 == 0;
    }

    public static boolean invalidConsecutiveBrackets(char currentChar, char nextChar,
                                                     List<Character> openers, List<Character> closers) {
        return openers.contains(currentChar) && // Check if it is an opening bracket
                closers.contains(nextChar) && // Check if the next character is a closing bracket
                openers.indexOf(currentChar) != closers.indexOf(nextChar); // Check that the opening character and
        // next closing character are NOT the same type
    }
}

