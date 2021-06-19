package Main;

import java.util.HashMap;
import java.util.Random;

import static Main.Program.numOfAssigning;
import static Main.Program.numOfCompares;

public class Helper {

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            numOfAssigning++;
            int j = i - 1;
            numOfAssigning++;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                numOfAssigning++;
                j = j - 1;
                numOfAssigning++;
                numOfCompares++;
            }
            numOfCompares++;
            arr[j + 1] = key;
            numOfAssigning++;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        numOfAssigning+=3;
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        numOfAssigning++;
        int i = (start - 1);
        numOfAssigning++;
        for (int j = start; j <= end - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                numOfAssigning++;
                swap(arr, i, j);
            }
            numOfCompares++;
        }
        swap(arr, i + 1, end);
        return (i + 1);
    }

    public static void myPreferredSortingMethod(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int start, int end) {
        numOfCompares++;
        if (start < end) {
            int part = partition(arr, start, end);
            numOfAssigning++;
            quickSort(arr, start, part - 1);
            quickSort(arr, part + 1, end);
        }
    }

    public static void countSort(int[] arr)
    {
        numOfAssigning++;
        int[] B = new int[100];
        numOfAssigning++;
        int[] C = new int[arr.length];
        numOfAssigning++;
        for (int j : arr) {
            B[j - 1]++;
            numOfAssigning++;
        }

        for (int i = 1; i < B.length; i++) {
            B[i] += B[i - 1];
            numOfAssigning++;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            C[B[arr[i] - 1] - 1] = arr[i];
            B[arr[i] - 1]--;
            numOfAssigning+=2;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = C[i];
            numOfAssigning++;
        }
    }


    private static String getMinValueKeyInHashMap(HashMap<String, Long> map) {
        long min = Integer.MAX_VALUE;
        String minKey = "";
        for (String key : map.keySet()) {
            long num = map.get(key);
            if (num < min) {
                min = num;
                minKey = key;
            }
        }
        return minKey;
    }

    public static void printMinValues() {
        System.out.println("\n\n\n");
        System.out.println("----------------------------");
        String minKey = getMinValueKeyInHashMap(Program.overallNumsOfCompares);
        System.out.println("The Method with the least comparisons is: " + minKey + " (" + Program.overallNumsOfCompares.get(minKey) + " comparisons & " + Program.overallNumsOfAssigning.get(minKey) + " Assigning)");
        minKey = getMinValueKeyInHashMap(Program.overallNumsOfAssigning);
        System.out.println("The Method with the least Assigning is: " + minKey + " (" + Program.overallNumsOfCompares.get(minKey) + " comparisons & " + Program.overallNumsOfAssigning.get(minKey) + " Assigning)");
        System.out.println("----------------------------");
    }

    public static void reset() {
        System.out.println("\t (With " + numOfCompares + " Comparisons & With " + numOfAssigning + " Assigning)");
        Program.clonedArr = Program.orgArr.clone();
        numOfCompares = 0;
        numOfAssigning = 0;
    }

    public static int[] getFilledArray(int length) {
        int[] retArr = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            retArr[i] = random.nextInt(100) + 1;
        }
        return retArr;
    }

    public static void printIntArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if(i>=50){
                System.out.println();
                return;
            }
            if (i + 1 != arr.length) {
                System.out.print(arr[i] + ", ");
            } else {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
    public static void saveResults(String key) {
        Program.overallNumsOfCompares.put(key, numOfCompares);
        Program.overallNumsOfAssigning.put(key, numOfAssigning);
    }
}
