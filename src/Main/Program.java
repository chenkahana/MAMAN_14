package Main;

import BST.BST;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

import static Main.Helper.*;

public class Program {

    //Values That will change for each method used
    public static long numOfCompares = 0;
    public static long numOfAssigning = 0;
    public static long numOfDifferentNumbers = 0;

    //The array on which manipulations will be made. creating a cloned to keep the original values.
    public static int[] orgArr;
    public static int[] clonedArr;

    //HashMaps to store parameters for each method to later compare.
    public static final HashMap<String, Long> overallNumsOfCompares = new HashMap<>();
    public static final HashMap<String, Long> overallNumsOfAssigning = new HashMap<>();


    public static double time;

    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        System.out.print("please enter the length of the required array: ");
        int reqLength = scan.nextInt();
        orgArr = Helper.getFilledArray(reqLength);
        clonedArr = orgArr.clone();
        System.out.println("The Array is:");
        printIntArr(clonedArr);
        System.out.println("Number of different values in array:");

        //(1)
        startTimer();
        System.out.println("according to the original method: " + originalMethod(clonedArr) + "\t(was done in " + stopTimer() + ")");
        saveResults("Original Method");
        reset();

        //(2)
        startTimer();
        System.out.println("according to the insertion method: " + insertion(clonedArr) + "\t(was done in " + stopTimer() + ")");
        saveResults("InsertionSort Method");
        reset();

        //(3)
        startTimer();
        System.out.println("according to my preferred method method: " + preferredSolution(clonedArr) + "\t(was done in " + stopTimer() + ")");
        saveResults("Preferred Method");
        reset();

        //(4)
        startTimer();
        System.out.println("according to the counting sort method: " + countingSortSolution(clonedArr) + "\t(was done in " + stopTimer() + ")");
        saveResults("CountingSort Method");
        reset();

        //(5)
        startTimer();
        System.out.println("according to the hash table method: " + hashTableSolution(clonedArr) + "\t(was done in " + stopTimer() + ")");
        saveResults("Hash Table Method");
        reset();

        //(6)
        startTimer();
        System.out.println("according to the Binary Search Tree method: " + BSTSolution(clonedArr) + "\t(was done in " + stopTimer() + ")");
        saveResults("Binary Search Tree Method");
        reset();


        printMinValues();

    }

    private static void startTimer() {
        time = System.nanoTime();
    }

    private static String stopTimer() {
        DecimalFormat df = new DecimalFormat("###.###");

        double res = (System.nanoTime() - time) / 1000000;
        if (res > 1000) { // >=seconds
            if (res > 60000) { //>=minutes
                return df.format(res / 60000) + " min";
            }
            return df.format(res / 1000) + " sec";
        }
        return df.format(res) + " mil sec";
    }

    private static int hashTableSolution(int[] arr) {
        int[] table = new int[100];
        for (int i = 0; i < 100; i++) {
            table[i] = 0;
            numOfAssigning++;
        }
        for (int i = 0; i < arr.length; i++) {
            table[arr[i] - 1]++;
            numOfAssigning++;
        }
        int res = 0;
        for (int i = 0; i < 100; i++) {
            numOfCompares++;
            if (table[i] > 0) {
                res++;
                numOfAssigning++;
            }
        }
        return res;
    }


    private static int BSTSolution(int[] arr) {
        BST tree = new BST();
        tree.insert(arr);
        return arr.length - tree.numOfSameValues;
    }

    private static int countingSortSolution(int[] arr) {
        int numOfDifferentNumbers = 1;
        Helper.countSort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (i + 1 < arr.length && arr[i] != arr[i + 1]) {
                numOfDifferentNumbers++;
                numOfAssigning++;
            }
            numOfCompares++;
        }
        return numOfDifferentNumbers;
    }


    private static int insertion(int[] arr) {
        int numOfDifferentNumbers = 1;
        Helper.insertionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (i + 1 < arr.length && arr[i] != arr[i + 1]) {
                numOfDifferentNumbers++;
                numOfAssigning++;
            }
            numOfCompares++;
        }
        return numOfDifferentNumbers;
    }

    private static int preferredSolution(int[] arr) {
        int numOfDifferentNumbers = 1;
        Helper.myPreferredSortingMethod(arr);
        for (int i = 0; i < arr.length; i++) {
            if (i + 1 < arr.length && arr[i] != arr[i + 1]) {
                numOfDifferentNumbers++;
                numOfAssigning++;
            }
            numOfCompares++;
        }
        return numOfDifferentNumbers;
    }

    private static int originalMethod(int[] arr) {
        int U_Size = 1;
        boolean U;
        for (int i = 1; i < arr.length; i++) {
            U = true;
            numOfAssigning++;
            for (int j = 0; j < U_Size; j++) {
                if (arr[i] == arr[j]) {
                    U = false;
                    numOfAssigning++;
                    j = U_Size;
                    numOfAssigning++;
                }
                numOfCompares++;
            }
            if (U) {
                arr[U_Size] = arr[i];
                numOfAssigning++;
                U_Size = U_Size + 1;
                numOfAssigning++;
            }
        }
        return U_Size;
    }

}
