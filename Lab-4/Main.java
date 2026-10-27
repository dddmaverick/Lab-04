import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Unsorted Array ---------------------------------------------------");

        ArrayList<Integer> integerList = Lab4.getList();
        Lab4.outputList(integerList);

        System.out.println("\n\nBubble sort results ----------------------------------------------");

        ArrayList<Integer> bubbleList = new ArrayList<>(integerList);

        long startTime = System.nanoTime();
        ArrayList<Integer> bubbleSortedList = Lab4.bubbleSort(bubbleList);
        long endTime = System.nanoTime();

        Lab4.outputList(bubbleSortedList);

        System.out.println("\nBubble sort took "
                + (endTime - startTime) / 1_000_000.0
                + " milliseconds.");

        System.out.println("\n\nInsertion sort results -------------------------------------------");

        ArrayList<Integer> insertionList = new ArrayList<>(integerList);

        startTime = System.nanoTime();
        ArrayList<Integer> insertionSortedList = Lab4.insertionSort(insertionList);
        endTime = System.nanoTime();

        Lab4.outputList(insertionSortedList);

        System.out.println("\nInsertion sort took "
                + (endTime - startTime) / 1_000_000.0
                + " milliseconds.");
    }
}

class Lab4 {
    public static ArrayList<Integer> insertionSort(
            ArrayList<Integer> integerList) {

        for (int i = 1; i < integerList.size(); i++) {
            int value = integerList.get(i);
            int j = i - 1;

            while (j >= 0 && integerList.get(j) > value) {
                integerList.set(j + 1, integerList.get(j));
                j--;
            }

            integerList.set(j + 1, value);
        }

        return integerList;
    }

    public static ArrayList<Integer> bubbleSort(
            ArrayList<Integer> integerList) {

        for (int i = 0; i < integerList.size() - 1; i++) {
            for (int j = 0; j < integerList.size() - 1 - i; j++) {
                if (integerList.get(j) > integerList.get(j + 1)) {
                    int temp = integerList.get(j);

                    integerList.set(j, integerList.get(j + 1));
                    integerList.set(j + 1, temp);
                }
            }
        }

        return integerList;
    }

    public static ArrayList<Integer> getList() {
        ArrayList<Integer> integerList = new ArrayList<>();
        String line;

        try (BufferedReader br =
                     new BufferedReader(new FileReader("integers.txt"))) {

            while ((line = br.readLine()) != null) {
                integerList.add(Integer.parseInt(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return integerList;
    }

    public static void outputList(ArrayList<Integer> integerList) {
        for (int i = 0; i < integerList.size(); i++) {
            System.out.print(integerList.get(i) + " ");
        }
    }
}


//  I would use insertion sort because it is simple to write and understand it also works well for smaller lists.
//  yes, insertion sort was faster in my test this makes sense because bubble sort usually makes more swaps. Both have an O(n^2) time complexity, but they do not always take exactly the same amount of time.
//  bubble sort is easier for me to understand because it compares two numbers next to each other and swaps them when they are in the wrong order