import java.util.*;

// implementations for merge sort, bubble sort and a "silly" sort

public class Solution {

    private static int sillySortCount;
    private static int bubbleSortCount;
    private static int mergeSortCount;
    private static int scannerInputSize;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\\D");

        ArrayList<Integer> sillySortArrayList = new ArrayList<>();
        ArrayList<Integer> bubbleSortArrayList = new ArrayList<>();
        ArrayList<Integer> mergeSortArrayList = new ArrayList<>();

        while (scanner.hasNextInt()) {
            int integer = scanner.nextInt();
            scannerInputSize++;
            sillySortArrayList.add(integer);
            bubbleSortArrayList.add(integer);
            mergeSortArrayList.add(integer);
        }

        Integer[] sillySortArray = sillySortArrayList.toArray(new Integer[scannerInputSize]);
        Solution.sillySort(sillySortArray);
        Integer[] bubbleSortArray = bubbleSortArrayList.toArray(new Integer[scannerInputSize]);
        Solution.bubbleSort(bubbleSortArray);
        Integer[] mergeSortArray = mergeSortArrayList.toArray(new Integer[scannerInputSize]);


        // (1) sorted list from merge sort
        System.out.println(Solution.convertToString(Solution.mergeSort(mergeSortArray)));

        // (2) sillySortCount if input length < 10 else -1
        if (scannerInputSize > 10) {
            System.out.println("-1");
        }
        else {
            System.out.println(sillySortCount);
        }

        // (3) bubbleSortCount if input length < 10,000 else -1
        if (scannerInputSize > 10000) {
            System.out.println("-1");
        }
        else {
            System.out.println(bubbleSortCount);
        }

        // (4) mergeSortCount
        System.out.println(mergeSortCount);
    }

    private static Integer[] sillySort(Integer[] sillySortArray) {
        int length = sillySortArray.length;
        Integer[] maybeSorted = new Integer[length];

        if (length < 2) {
            return sillySortArray;
        }
        else {
            for (int i = 0; i < length; i++) {
                int temp = sillySortArray[0];
                sillySortArray[0] = sillySortArray[i];
                sillySortArray[i] = temp;

                maybeSorted[0] = sillySortArray[0];
                // recursive call to remaining items in array
                Integer[] newArray = Solution.sillySort(Arrays.copyOfRange(sillySortArray, 1, 
                        length));
                System.arraycopy(newArray, 0, maybeSorted, 1, newArray.length);

                boolean flag = false;
                for (int j = 0; j < maybeSorted.length - 1; j++) {
                    sillySortCount++;
                    if (maybeSorted[j] > maybeSorted[j + 1]) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    return maybeSorted;
                }
                else {
                    temp = sillySortArray[0];
                    sillySortArray[0] = sillySortArray[i];
                    sillySortArray[i] = temp;
                }
            }
        }
        return sillySortArray;
    }

    private static Integer[] bubbleSort(Integer[] bubbleSortArray) {
        while (true) {
            boolean swappedThisTurn = false;
            for (int i = 0; i < bubbleSortArray.length - 1; i++) {
                bubbleSortCount++;
                if (bubbleSortArray[i] > bubbleSortArray[i + 1]) {
                    int temp = bubbleSortArray[i];
                    bubbleSortArray[i] = bubbleSortArray[i + 1];
                    bubbleSortArray[i + 1] = temp;
                    swappedThisTurn = true;
                }
            }
            if (!swappedThisTurn) {
                return bubbleSortArray;
            }
        }
    }

    private static Integer[] mergeSort(Integer[] mergeSortArray) {
        int length = mergeSortArray.length;

        if (length < 2) {
            return mergeSortArray;
        }

        int mid = length / 2;
        Integer[] left = Arrays.copyOfRange(mergeSortArray, 0, mid);
        Integer[] right = Arrays.copyOfRange(mergeSortArray, mid, length);
        left = Solution.mergeSort(left);
        right = Solution.mergeSort(right);
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            mergeSortCount++;
            if (left[i] < right[j]) {
                mergeSortArray[k] = left[i];
                k++;
                i++;
            }
            else {
                mergeSortArray[k] = right[j];
                k++;
                j++;
            }
        }
        if (i < left.length) {
            for (; k < length; k++) {
                mergeSortArray[k] = left[i];
                i++;
            }
        }
        else {
            for (int a = k; a < length; a++) {
                mergeSortArray[a] = right[j];
                j++;
            }
        }
        return mergeSortArray;
    }


    private static String convertToString(Integer[] intArray) {
        String theString = "";
        for (int i = 0; i < intArray.length; i++) {
            if (i < intArray.length - 1) {
                theString = theString + intArray[i].toString() + ",";
            }
            else {
                theString = theString + intArray[i].toString();
            }

        }
        return theString;
    }

}
