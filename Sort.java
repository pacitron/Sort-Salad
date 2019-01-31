import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

class QuickSort {
    String names[];
    int length;
    void sort(String array[]) {
        if (array == null || array.length == 0) {
            return;
        }
        this.names = array;
        this.length = array.length;
        quickSort(0, length - 1);
    }
    void quickSort(int low, int high) {
        int i = low;
        int j = high;
        String pivot = this.names[low + (high - low) / 2];
        while (i <= j) {
            while (this.names[i].compareToIgnoreCase(pivot) < 0) {
                i++;
            }
            while (this.names[j].compareToIgnoreCase(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                exchangeNames(i, j);
                i++;
                j--;
            }
        }
        if (low < j) {
            quickSort(low, j);
        }
        if (i < high) {
            quickSort(i, high);
        }
    }
    void exchangeNames(int i, int j) {
        String temp = this.names[i];
        this.names[i] = this.names[j];
        this.names[j] = temp;
    }
}

class HeapSort {
    static void heapify(String a[], int n, int i) {
        int max, child;
        child = 2 * i + 1;
        max = i;
        if (child < n)
            if (a[child].compareToIgnoreCase(a[max])>0)
                max = child;
        if (child + 1 < n)
            if (a[child + 1].compareToIgnoreCase(a[max])>0)
                max = child + 1;
        if (max != i) {
            String temp = a[i];
            a[i] = a[max];
            a[max] = temp;
            heapify(a, n, max);
        }
    }
    static void buildheap(String a[]) {
        for (int i = a.length / 2 - 1; i >= 0; i--)
            heapify(a, a.length, i);
    }
    static void heap(String names[]) {
        buildheap(names);
        for (int i = names.length - 1; i >= 1; i--) {
            String temp = names[0];
            names[0] = names[i];
            names[i] = temp;
            heapify(names, i, 0);
        }
    }
}

class InsertionSort
{
    @SuppressWarnings("unchecked")
    static void insertionSort(Comparable[] names)
    {
        Comparable temp;
        for(int i = 1; i < names.length; i++)
        {
            temp = names[i];
            int j = 0;
            for(j = i; j > 0; j--)
                if(temp.compareTo(names[j - 1]) < 0) {
                    names[j] = names[j - 1];
                }
                else
                    break;
            names[j] = temp;
        }
    }
}

public class Sort {

    public static void mergeSort(String[] names) {
        if (names.length >= 2) {
            String[] left = new String[names.length / 2];
            String[] right = new String[names.length - names.length / 2];

            for (int i = 0; i < left.length; i++) {
                left[i] = names[i];
            }

            for (int i = 0; i < right.length; i++) {
                right[i] = names[i + names.length / 2];
            }

            mergeSort(left);
            mergeSort(right);
            merge(names, left, right);
        }
    }
    public static void merge(String[] names, String[] left, String[] right) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < names.length; i++) {
            if (b >= right.length || (a < left.length && left[a].compareToIgnoreCase(right[b]) < 0)) {
                names[i] = left[a];
                a++;
            } else {
                names[i] = right[b];
                b++;
            }
        }
    }

    //-----------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        System.out.println("Enter the input file path:");
        Scanner input=new Scanner(System.in);
        String fileName="";
        fileName=input.nextLine();
        String size="";
        int count=0;
        double algorithmTime=0;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            size= bufferedReader.readLine();
            String[] array=new String[Integer.parseInt(size)];
            String[] Name=new String[Integer.parseInt(size)];
            String[] resultArray=new String[Integer.parseInt(size)];
            while ((size= bufferedReader.readLine())!=null){
                array[count]=size;
                count++;
            }
            System.out.println("Sorting...");
            QuickSort sorter = new QuickSort();
            HeapSort hsort= new HeapSort();
            InsertionSort isort=new InsertionSort();

            //Last Name
            for(int i=0;i<array.length;i++){
                Name[i]=array[i].split(" ")[0];
            }
            long startTime = System.nanoTime();
            sorter.sort(Name);
            long endTime   = System.nanoTime();
            for(int i=0;i<array.length;i++){
                for(int j=0;j<array.length;j++){
                    if(Name[i].equals(array[j].split(" ")[0])){
                        resultArray[i]=array[j];
                        array[j]="|";
                        break;
                    }
                }
            }
            /*
            for (String value:resultArray){
                System.out.println(value);
            }*/
            double totalTime = endTime - startTime;
            algorithmTime+=totalTime;
            System.out.println("\n"+"\tLAST NAME (QUICK)\n"+totalTime+" nano seconds");
            totalTime=totalTime/1000000000;
            DecimalFormat format = new DecimalFormat("0.0000000000");
            System.out.println(format.format(totalTime)+" seconds");
            System.out.println("------------------------------------------------------------------------------");
            //First Name
            for(int i=0;i<array.length;i++){
                Name[i]=resultArray[i].split("\\|")[0];
            }
            startTime = System.nanoTime();
            mergeSort(Name);
            endTime   = System.nanoTime();
            for(int i=0;i<resultArray.length;i++){
                for(int j=0;j<resultArray.length;j++){
                    if(Name[i].equals(resultArray[j].split("\\|")[0])){
                        array[i]=resultArray[j];
                        resultArray[j]="| |";
                        break;
                    }
                }
            }
            /*
            for (String value:array){
                System.out.println(value);
            }*/
            totalTime = endTime - startTime;
            algorithmTime+=totalTime;
            System.out.println("\n"+"\tFIRST NAME (MERGE)\n"+totalTime+" nano seconds");
            totalTime=totalTime/1000000000;
            format = new DecimalFormat("0.0000000000");
            System.out.println(format.format(totalTime)+" seconds");
            System.out.println("------------------------------------------------------------------------------");
            //State
            for(int i=0;i<array.length;i++){
                Name[i]=array[i].split("\\|")[0]+"|"+array[i].split("\\|")[3];
            }
            startTime = System.nanoTime();
            hsort.heap(Name);
            endTime = System.nanoTime();
            for(int i=0;i<array.length;i++){
                for(int j=0;j<array.length;j++){
                    if(Name[i].split("\\|")[0].equals(array[j].split("\\|")[0]) && Name[i].split("\\|")[1].equals(array[j].split("\\|")[3])){
                        resultArray[i]=array[j];
                        array[j]="| | | ";
                        break;
                    }
                }
            }
            /*
            for (String value:resultArray){
                System.out.println(value);
            }*/
            totalTime = endTime - startTime;
            algorithmTime+=totalTime;
            System.out.println("\n"+"\tSTATE (HEAP)\n"+totalTime+" nano seconds");
            totalTime=totalTime/1000000000;
            format = new DecimalFormat("0.0000000000");
            System.out.println(format.format(totalTime)+" seconds");
            System.out.println("------------------------------------------------------------------------------");
            //City
            for(int i=0;i<array.length;i++){
                Name[i]=resultArray[i].split("\\|")[0]+"|"+resultArray[i].split("\\|")[3]+"|"+resultArray[i].split("\\|")[2];
            }
            startTime = System.nanoTime();
            sorter.sort(Name);
            endTime   = System.nanoTime();
            for(int i=0;i<resultArray.length;i++){
                for(int j=0;j<resultArray.length;j++){
                    if(Name[i].split("\\|")[0].equals(resultArray[j].split("\\|")[0]) && Name[i].split("\\|")[1].equals(resultArray[j].split("\\|")[3]) && Name[i].split("\\|")[2].equals(resultArray[j].split("\\|")[2])){
                        array[i]=resultArray[j];
                        resultArray[j]="| | ";
                        break;
                    }
                }
            }
            /*
            for (String value:array){
                System.out.println(value);
            }*/
            totalTime = endTime - startTime;
            algorithmTime+=totalTime;
            System.out.println("\n"+"\tCITY (QUICK)\n"+totalTime+" nano seconds");
            totalTime=totalTime/1000000000;
            format = new DecimalFormat("0.0000000000");
            System.out.println(format.format(totalTime)+" seconds");
            System.out.println("------------------------------------------------------------------------------");
            //Zip code
            for(int i=0;i<array.length;i++){
                Name[i]=array[i].split("\\|")[0]+"|"+array[i].split("\\|")[3]+"|"+array[i].split("\\|")[2]+"|"+array[i].split("\\|")[4];
            }

            startTime = System.nanoTime();
            mergeSort(Name);
            endTime = System.nanoTime();

            for(int i=0;i<array.length;i++){
                for(int j=0;j<array.length;j++){
                    if(Name[i].split("\\|")[0].equals(array[j].split("\\|")[0]) && Name[i].split("\\|")[1].equals(array[j].split("\\|")[3]) && Name[i].split("\\|")[2].equals(array[j].split("\\|")[2]) && Name[i].split("\\|")[3].equals(array[j].split("\\|")[4])){
                        resultArray[i]=array[j];
                        array[j]="| | | | | ";
                        break;
                    }
                }
            }
            /*
            for (String value:resultArray){
                System.out.println(value);
            }*/
            totalTime = endTime - startTime;
            algorithmTime+=totalTime;
            System.out.println("\n"+"\tZIP CODE (MERGE)\n"+totalTime+" nano seconds");
            totalTime=totalTime/1000000000;
            format = new DecimalFormat("0.0000000000");
            System.out.println(format.format(totalTime)+" seconds");
            System.out.println("------------------------------------------------------------------------------");
            //Street
            for(int i=0;i<array.length;i++){
                Name[i]=resultArray[i].split("\\|")[0]+"|"+resultArray[i].split("\\|")[3]+"|"+resultArray[i].split("\\|")[2]+"|"+resultArray[i].split("\\|")[4]+"|"+resultArray[i].split("\\|")[1];
            }
            startTime = System.nanoTime();
            isort.insertionSort(Name);
            endTime   = System.nanoTime();
            for(int i=0;i<resultArray.length;i++){
                for(int j=0;j<resultArray.length;j++){
                    if(Name[i].split("\\|")[0].equals(resultArray[j].split("\\|")[0]) && Name[i].split("\\|")[1].equals(resultArray[j].split("\\|")[3]) && Name[i].split("\\|")[2].equals(resultArray[j].split("\\|")[2]) && Name[i].split("\\|")[3].equals(resultArray[j].split("\\|")[4]) && Name[i].split("\\|")[4].equals(resultArray[j].split("\\|")[1])){
                        array[i]=resultArray[j];
                        resultArray[j]="| | ";
                        break;
                    }
                }
            }
            /*
            for (String value:array){
                System.out.println(value);
            }*/
            totalTime = endTime - startTime;
            algorithmTime+=totalTime;
            System.out.println("\n"+"\tSTREET (INSERTION)\n"+totalTime+" nano seconds");
            totalTime=totalTime/1000000000;
            format = new DecimalFormat("0.0000000000");
            System.out.println(format.format(totalTime)+" seconds");
            System.out.println("------------------------------------------------------------------------------");

            System.out.println("creating output file...");
            BufferedWriter outputWriter = null;
            outputWriter = new BufferedWriter(new FileWriter("output"));
            for (int i = 0; i < array.length; i++) {
                // Maybe:
                outputWriter.write(array[i]);
                outputWriter.newLine();
            }
            outputWriter.flush();
            outputWriter.close();

            System.out.println("\tTOTAL ALGORITHM");
            System.out.println(algorithmTime+" nano seconds");
            algorithmTime=algorithmTime/1000000000;
            format = new DecimalFormat("0.0000000000");
            System.out.println(format.format(algorithmTime)+" seconds");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
