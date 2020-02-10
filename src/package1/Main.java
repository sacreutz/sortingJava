package package1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
       // File folder = new File("C:\\Users\\sophie.cruetz\\IdeaProjects\\Exercise2_255\\src\\package1\\Inputfiles");
        File folder = new File(s + "\\src\\package1\\Inputfiles");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                List<String> pair = readFile(listOfFiles[i]);
                writeToFileCountingSort(pair, i);
                writeToFile(pair, i);
            }

        }

    }


    public static List<String> readFile(File file) throws IOException {

        int[] results = new int[2];
        Scanner sc = new Scanner(file);
        int sum= sc.nextInt();
        int counter = 0;
        System.out.println(sc.nextLine());

        String arrayList = sc.nextLine();
        String str[] = arrayList.split(" ");
        List<String> al = new ArrayList<String>();
        al = Arrays.asList(str);
        System.out.println("al1");
        System.out.println(al);

        //check for sum here
        //String[] pair = findTwoNumberSum(al, sum);
        List<String> list = new ArrayList<String>();
        // Collections.addAll(list, sum);
        // Collections.addAll(list, arrayList);
        String sumString = Integer.toString(sum);
        list.add(sumString);
        list.add(al.toString());
        System.out.println("sumstring");
        System.out.println(sumString);
        results[0] = sum;

        return list;
    }


    private static int[] countingSort(int[] array, int maxValue) {
            int max = maxValue += 1;
            int[] count = new int[max];
            Arrays.fill(count, 0);

            for (int item : array) {
                count[item] += 1;
            }

            for (int i = 1; i < count.length; ++i)
                count[i] += count[i - 1];

            int output[] = new int[array.length];

            for (int i = 0; i < array.length; ++i) {
                output[count[array[i]] - 1] = array[i];
                --count[array[i]];
            }

            System.out.println("output");
            System.out.println(Arrays.toString(output));
            return output;
        }

        private static int[] insertionSort(int[] array){
            if (array.length == 0) {
                return new int[] {};
            }
            for (int i = 1; i < array.length; i++) {
                int j = i;
                while (j > 0 && array[j] < array[j - 1]) {
                    swap(j, j -1, array);
                    j -= 1;
                }
            }

            return array;
        }

        public static void swap (int i, int j, int[] array) {
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }

    public static void writeToFileCountingSort(List<String> list, int i){
        System.out.println("list");
        System.out.println(list);
        List<Integer> intList = new ArrayList<Integer>();
        String inputArray = list.get(1);
        String[] str = inputArray.substring(1, inputArray.length() -1).split(", ");
        System.out.print("str");
        System.out.print(str);
        List<String> al = new ArrayList<String>();
        al = Arrays.asList(str);
        System.out.println("al");
        System.out.println(al);
        for(String s : str) intList.add(Integer.valueOf(s));
        int[] array = intList.stream().mapToInt(j->j).toArray();
        int max = Integer.parseInt(list.get(0));

        int[] resultArray = countingSort(array, max);


        //String fileName = "C:\\Users\\sophie.cruetz\\eclipse-workspace\\Exercise1_255\\Outputfiles\\test" + i + ".txt";
        String fileName = "out" + (i+1) + ".txt"; //Kamilla edited
        File outputFile = new File(fileName);
        try {

            BufferedWriter output = new BufferedWriter(new FileWriter(outputFile));
            output.write(Arrays.toString(resultArray));
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeToFile(List<String> list, int i) {
        List<Integer> intList = new ArrayList<Integer>();
        String inputArray = list.get(1);
        String[] str = inputArray.substring(1, inputArray.length() -1).split(", ");
        List<String> al = new ArrayList<String>();
        al = Arrays.asList(str);
        for(String s : str) intList.add(Integer.valueOf(s));
        int[] array = intList.stream().mapToInt(j->j).toArray();

        int[] resultArray = insertionSort(array);

        String fileName = "otherOut" + (i+1) + ".txt";
        File outputFile = new File(fileName);
        try {

            BufferedWriter output = new BufferedWriter(new FileWriter(outputFile));
            output.write(Arrays.toString(resultArray));
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}



