import javax.print.attribute.standard.MediaSize;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

class StringQuickSort {

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

    void quickSort(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        String pivot = this.names[lowerIndex + (higherIndex - lowerIndex) / 2];

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
        //call quickSort recursively
        if (lowerIndex < j) {
            quickSort(lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSort(i, higherIndex);
        }
    }

    void exchangeNames(int i, int j) {
        String temp = this.names[i];
        this.names[i] = this.names[j];
        this.names[j] = temp;
    }
}

class radix
{
    @SuppressWarnings("unchecked")
    public static void radixSort( String [ ] arr, int maxLen )
    {
        final int BUCKETS = 256;
        ArrayList<String> [ ] wordsByLength = new ArrayList[ maxLen + 1 ];
        ArrayList<String> [ ] buckets = new ArrayList[ BUCKETS ];
        for( int i = 0; i < wordsByLength.length; i++ )
            wordsByLength[ i ] = new ArrayList<>( );
        for( int i = 0; i < BUCKETS; i++ )
            buckets[ i ] = new ArrayList<>( );
        for( String s : arr )
            wordsByLength[ s.length( ) ].add( s );
        int idx = 0;
        for( ArrayList<String> wordList : wordsByLength )
            for( String s : wordList )
                arr[ idx++ ] = s;
        int startingIndex = arr.length;
        for( int pos = maxLen - 1; pos >= 0; pos-- )
        {
            startingIndex -= wordsByLength[ pos + 1 ].size( );
            for( int i = startingIndex; i < arr.length; i++ )
                buckets[ arr[ i ].charAt( pos ) ].add( arr[ i ] );
            idx = startingIndex;
            for( ArrayList<String> thisBucket : buckets )
            {
                for( String s : thisBucket )
                    arr[ idx++ ] = s;
                thisBucket.clear( );
            }
        }
    }
}

public class NameSort {

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
        String fileName="D:\\Studies\\IntelliJ\\Sort\\src\\sample";
        String size="";
        int count=0;

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            size= bufferedReader.readLine();
            int temp=Integer.parseInt(size);
            String[] array=new String[Integer.parseInt(size)];
            String[] Name=new String[Integer.parseInt(size)];
            String[] resultArray=new String[Integer.parseInt(size)];
            while ((size= bufferedReader.readLine())!=null){
                array[count]=size;
                count++;
            }
            StringQuickSort sorter = new StringQuickSort();
            radix sort=new radix();

            //Last Name
            for(int i=0;i<array.length;i++){
                Name[i]=array[i].split(" ")[0];
            }
            long startTime = System.nanoTime();
            mergeSort(Name);
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
            System.out.println("\n"+"\tLAST NAME\n"+totalTime+" nano seconds");
            totalTime=totalTime/1000000000;
            DecimalFormat format = new DecimalFormat("0.0000000000");
            System.out.println("\n"+format.format(totalTime)+" seconds");
            System.out.println("------------------------------------------------------------------------------");
            //First Name
            for(int i=0;i<array.length;i++){
                Name[i]=resultArray[i].split("\\|")[0];
            }
            startTime = System.nanoTime();
            sorter.sort(Name);
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
            System.out.println("\n"+"\tFIRST NAME\n"+totalTime+" nano seconds");
            totalTime=totalTime/1000000000;
            format = new DecimalFormat("0.0000000000");
            System.out.println("\n"+format.format(totalTime)+" seconds");
            System.out.println("------------------------------------------------------------------------------");
            //State
            Name=null;
            for(int i=0;i<array.length;i++){
                if (array[i].split("\\|")[0].equals(array[i+1].split("\\|")[0])) {
                    System.out.println(array[i]+"\n"+array[i+1]);
                    Name[i]=array[i].split("\\|")[3];
                    i++;
                    Name[i]=array[i].split("\\|")[3];
                } else {
                    if(Name!=null){
                        startTime = System.nanoTime();
                        mergeSort(Name);
                        endTime = System.nanoTime();
                        totalTime+= endTime - startTime;
                        for(int k=0;k<array.length;k++){
                            for(int j=0;j<array.length;j++){
                                if(Name[k].equals(array[j].split("\\|")[3])){
                                    resultArray[k]=array[j];
                                    array[j]="| | | ";
                                    break;
                                }
                            }
                        }
                        Name=null;
                    }
                }
            }
            //startTime = System.nanoTime();
            //mergeSort(Name);
            //endTime = System.nanoTime();

            for(int j=0;j<array.length;j++){
                if(array[j]!="| | | "){
                    resultArray[j]=array[j];
                    array[j]="| | | ";
                    break;
                }
            }


            for (String value:resultArray){
                System.out.println(value);
            }
            //totalTime = endTime - startTime;
            System.out.println("\n"+"\tSTATE\n"+totalTime+" nano seconds");
            totalTime=totalTime/1000000000;
            format = new DecimalFormat("0.0000000000");
            System.out.println("\n"+format.format(totalTime)+" seconds");
            System.out.println("------------------------------------------------------------------------------");
            //City
            for(int i=0;i<array.length;i++){
                Name[i]=resultArray[i].split("\\|")[2];
            }
            startTime = System.nanoTime();
            sorter.sort(Name);
            endTime   = System.nanoTime();
            for(int i=0;i<resultArray.length;i++){
                for(int j=0;j<resultArray.length;j++){
                    if(Name[i].equals(resultArray[j].split("\\|")[2])){
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
            System.out.println("\n"+"\tCITY\n"+totalTime+" nano seconds");
            totalTime=totalTime/1000000000;
            format = new DecimalFormat("0.0000000000");
            System.out.println("\n"+format.format(totalTime)+" seconds");
            System.out.println("------------------------------------------------------------------------------");
            //Zip code
            for(int i=0;i<array.length;i++){
                Name[i]=array[i].split("\\|")[4];
            }

            startTime = System.nanoTime();
            sort.radixSort(Name,Name.length);
            endTime = System.nanoTime();

            for(int i=0;i<array.length;i++){
                for(int j=0;j<array.length;j++){
                    if(Name[i].equals(array[j].split("\\|")[4])){
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
            System.out.println("\n"+"\tZIP CODE\n"+totalTime+" nano seconds");
            totalTime=totalTime/1000000000;
            format = new DecimalFormat("0.0000000000");
            System.out.println("\n"+format.format(totalTime)+" seconds");
            System.out.println("------------------------------------------------------------------------------");
            //Street
            for(int i=0;i<array.length;i++){
                Name[i]=resultArray[i].split("\\|")[1];
            }
            startTime = System.nanoTime();
            sorter.sort(Name);
            endTime   = System.nanoTime();
            for(int i=0;i<resultArray.length;i++){
                for(int j=0;j<resultArray.length;j++){
                    if(Name[i].equals(resultArray[j].split("\\|")[1])){
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
            System.out.println("\n"+"\tSTREET\n"+totalTime+" nano seconds");
            totalTime=totalTime/1000000000;
            format = new DecimalFormat("0.0000000000");
            System.out.println("\n"+format.format(totalTime)+" seconds");
            System.out.println("------------------------------------------------------------------------------");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

