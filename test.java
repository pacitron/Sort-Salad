import java.io.*;

public class test {
    public static void main(String[] args) {
        FileReader fileReader = null;
        String size="";
        int count=0;
        try {
            fileReader = new FileReader("D:\\Studies\\IntelliJ\\Sort\\src\\top-1m");

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String[] array=new String[100000];
        while ((size= bufferedReader.readLine())!=null){
            array[count]=size;
            count++;
            if(count==100000){
                break;
            }
        }

            BufferedWriter outputWriter = null;
            outputWriter = new BufferedWriter(new FileWriter("output"));
            for (int i = 0; i < array.length; i++) {
                // Maybe:
                outputWriter.write(array[i]);
                outputWriter.newLine();
            }
            outputWriter.flush();
            outputWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
