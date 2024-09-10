/***
 * Question: String Manipulation
 * Owner Name: Dikshant Goswami
 * Date: 10-9-2024
 */

import java.util.Scanner;

class StringManipulatesSimple {

    // this function takes the char[] array--> as a array by a user
    // start --> its an index of initial position of an array
    // length --> it is the length of our character array

    public void generateCombinations(char[] array, int start, int lengthofCharArray) {
        // Generate combinations for each possible length (1 to full length)
        for (int size = 1; size <= lengthofCharArray; size++) {
            // Temporary array to hold the current combination
            char[] temporary = new char[size];
            generateCombinationRecursive(array, temporary, 0, 0, size);
        }
    }

    // Recursive function to generate combinations of a specific length
    // this function takes the char[] array--> as a array by a user
    // Temporary array to hold the current combination
    // start is the initial index
    // size is define the size of array
    public void generateCombinationRecursive(char[] array, char[] temporary, int start, int index, int size) {
        if (index == size) {
            // Once we have a valid combination, generate all its permutations
            generatePermutations(temporary, 0, size);
            return;
        }

        // Generate combinations by choosing characters one by one
        for (int i = start; i < array.length; i++) {
            temporary[index] = array[i];
            generateCombinationRecursive(array, temporary, i + 1,index + 1, size);
        }
    }

    // Function to generate all permutations of a given combination
    // this function takes the char[] array--> as a array by a user
    // start is the initial index
    // length defines the length of our array
    // here the charcter defines the charcater of array
    public void generatePermutations(char[] array, int start, int length) {
        if (start == length) {
            // Print the current permutation
            for (char character : array) {
                System.out.print(character);
            }
            System.out.println();
        } else {
            for (int i = start; i < length; i++) {
                swap(array, start, i);
                generatePermutations(array, start + 1, length);
                swap(array, start, i);  // Swap back to maintain original order
            }
        }
    }

    // Swap function to swap two characters in an array
    // char[] array:--> defines the character of an array
    // here start aand end defines the position of an array element which is going to be swap
    public void swap(char[] array, int start, int end) {
        char temporary = array[start];
        array[start] = array[end];
        array[end] = temporary;
    }

    // reduceToSingleDigit:--> we will get to know that our digit is already in single digit or it is not we will make it in single digit
    // then we add the single digits until our number become the single digit
    // number:-> defines the digit entered by user
    public void reduceToSingleDigit(long inputbyuser) {
        System.out.println("Initial number: " + inputbyuser);
        while (inputbyuser >= 10) {
            inputbyuser = sumOfDigits(inputbyuser);
            System.out.println("Intermediate result: " + inputbyuser);
        }
        System.out.println("Single digit number: " + inputbyuser);
    }

    // Sum the digits of a number manually
    // here we will add the single digits one by one adn return the sum of them so that it can ne used again and again until we get single digit alone
    public long sumOfDigits(long inputbyuser) {
        long sum = 0;
        while (inputbyuser > 0) {
            sum += inputbyuser % 10;  // Extract last digit
            inputbyuser /= 10;  // Remove last digit
        }
        return sum;
    }

    // Find consecutive sums to match the given number
    // we will check if our user entered value is consecutive or not
    // number defines the value entered by the user
    public void findConsecutiveSums(int inputbyuser) {
        boolean found = false;
        for (int start = 1; start <= inputbyuser / 2; start++) {
            int sum = 0;
            for (int i = start; i <= inputbyuser; i++) {
                sum += i;
                if (sum == inputbyuser) {
                    printConsecutiveSums(start, i);
                    found = true;
                    break;
                } else if (sum > inputbyuser) {
                    break;
                }
            }
        }
        if (!found) {
            System.out.println(inputbyuser + " does not have any consecutive sum.");
        }
    }

    // Print the sequence of consecutive sums
    // start defines our initial stage and end defines our last stage
    public void printConsecutiveSums(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (i != start) System.out.print(" + ");
            System.out.print(i);
        }
        System.out.println();
    }
    // Caesar Cipher with Shift Variability
    // In this programme we will shift the word os string from the value mention by user
    // for example  Input: "HELLO WORLD", Shift Pattern: [1, 2, 3]
    //              Output:  IGOMQ ZPTOE (Shifts: 'H' +1, 'E' +2, 'L' +3, 'L' +1, 'O' +2, etc.)
    public String encryptString(String input, int[] pattern) {
        String encrypted = "";
        int patternIndex = 0;
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar >= 'A' && currentChar <= 'Z') {
                int shift = pattern[patternIndex % pattern.length];
                currentChar = (char) (((currentChar - 'A' + shift) % 26) + 'A');
                // As the alphabetic have 26 letters we use 26
                patternIndex++;
            } else if (currentChar >= 'a' && currentChar <= 'z') {
                int shift = pattern[patternIndex % pattern.length];
                currentChar = (char) (((currentChar - 'a' + shift) % 26) + 'a');
                patternIndex++;
            }
            encrypted += currentChar;
        }
        return encrypted;
    }

    // findmaximum function help to find the maximum element given by user
    public  int[] findMaximum(int[] array, int series) {
        String seriesString = Integer.toString(series);
        int[] maxElements = new int[seriesString.length()];

        for (int s = 0; s < seriesString.length(); s++) {
            int nthMax = Character.getNumericValue(seriesString.charAt(s));
            int[] tempArray = array.clone();

            for (int n = 1; n <= nthMax; n++) {
                int max = Integer.MIN_VALUE;
                int maxIndex = -1;

                // Find the nth maximum element
                for (int i = 0; i < tempArray.length; i++) {
                    if (tempArray[i] > max) {
                        max = tempArray[i];
                        maxIndex = i;
                    }
                }

                if (maxIndex != -1) {
                    tempArray[maxIndex] = Integer.MIN_VALUE;
                }

                if (n == nthMax) {
                    maxElements[s] = max;
                }
            }
        }

        return maxElements;
    }

    // Function to encode numbers and find ASCII values of the max elements
    // this will  help to find the maximum element for ASCII value according to the series entered by user
    public  void encodingNumberToAsciiValue() {
        Scanner inputfromuser = new Scanner(System.in);
        int number;
        int series;

        System.out.println(Constants.ENTER_NUMBER);
        if (!inputfromuser.hasNextInt()) {
            System.out.println(Constants.INTEGER_WARNING);
        }
        else {
            number = inputfromuser.nextInt();
            System.out.println(Constants.ENTER_SERIES);
            series = inputfromuser.nextInt();
            String seriesString = Integer.toString(series);
            if (series > number) {
                System.out.println( Constants.CORECT_STRING);
            } else {
                String numberToString = Integer.toString(number);
                int StringToNumber;
                int[] outputArray = new int[numberToString.length()];
                int index = 0;

                for (int i = 0; i < numberToString.length(); i++) {
                    char character = numberToString.charAt(i);
                    StringToNumber = Integer.parseInt(String.valueOf(character));
                    outputArray[index] = StringToNumber;
                    index++;
                }

                int[] max = findMaximum(outputArray, series);

                String asciiValues = "";

                for (int i = 0; i < max.length; i++) {
                    char character = (char) (max[i] + '0');
                    int asciiValue = (int) character;
                    asciiValues += asciiValue;
                }

                System.out.println(asciiValues);
            }
               }
        }

    // Show menu options
    // they can choose task which they want to perform
    public void showMenu() {
        System.out.println(Constants.GENERATE_PERMUTATION);
        System.out.println(Constants.REDUCE_NUMBER);
        System.out.println(Constants.FIND_CONSECUTIVE);
        System.out.println(Constants.ENCRYPT_STRING);
        System.out.println(Constants.ENCODE_NUMBER);
        System.out.println(Constants.TERMINATED);
    }

    // Ask user if they want to continue
    // this will help them take decision if not in use
    public boolean askToContinue(String action) {
        Scanner inputfromuser = new Scanner(System.in);
        System.out.println("Do you want to " + action + " again? (yes/no)");
        String response = inputfromuser.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }
}


public class StringManipulationSimple {
    public static void main(String[] args) {
        StringManipulatesSimple manipulator = new StringManipulatesSimple();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            manipulator.showMenu();
            System.out.println(Constants.ENTER);
            if(!scanner.hasNextInt()) {
                System.out.println(Constants.ENTER_INTEGER);
            }
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    do {
                        System.out.println(Constants.STRING_PERMUTATION);
                        String input = scanner.nextLine();
                        manipulator.generateCombinations(input.toCharArray(), 0, input.length());
                    } while (manipulator.askToContinue(Constants.GENERATE));
                    break;

                case 2:
                    do {
                        System.out.println(Constants.TOREDUCE);
                        if(!scanner.hasNextInt()) {
                            System.out.println(Constants.PL_INTEGER);
                        }

                        long inputbyuser = scanner.nextLong();
                        if(inputbyuser < 0) {
                            System.out.println(Constants.SHOULD_POSITIVE);
                        }
                        else {
                            manipulator.reduceToSingleDigit(inputbyuser);
                        }}
                     while (manipulator.askToContinue(Constants.REDUCE_NUMBER2));
                    break;

                case 3:
                    do {
                        System.out.println(Constants.FIND_CONSECUTIVE2);
                        if(!scanner.hasNextInt()) {
                            System.out.println(Constants.PL_INTEGER);
                        }
                        int number = scanner.nextInt();
                        manipulator.findConsecutiveSums(number);
                    } while (manipulator.askToContinue(Constants.CONSECUTIVE_SUM2));
                    break;

                case 4:
                    do {
                        System.out.println(Constants.STRING_TOENCRYPT);
                        String inputString = scanner.nextLine();
                        System.out.println(Constants.SHIFT_PATTERN);
                        String[] patternStr = scanner.nextLine().split(" ");

                        int[] pattern = new int[patternStr.length];
                        for (int i = 0; i < patternStr.length; i++) {
                            pattern[i] = Integer.parseInt(patternStr[i]);
                        }
                        String encryptedString = manipulator.encryptString(inputString, pattern);
                        System.out.println(Constants.ENCRYPTED + encryptedString);
                    } while (manipulator.askToContinue(Constants.ENCY));
                    break;

                case 5:
                    do {
                        manipulator.encodingNumberToAsciiValue();
                    } while (manipulator.askToContinue(Constants.ENCODED));
                    break;

                case 6:
                    run = false;
                    break;

                default:
                    System.out.println(Constants.INVALID);
            }
        }

        System.out.println(Constants.TERMINATED);
            }
}
