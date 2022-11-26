package cipher;

import java.io.*;

/**
 * @author : Cong-Phuc, Nguyen
 * @since : 11/24/2022, Thu
 **/
public class ReadAndWriteFile {
    public static void main(String[] args) {

    }

    public static boolean writeKeyToFile(String key, String name) {
        try {
            File file = new File("src/main/java/cipher/"+name);
            FileOutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(key);

            System.out.println(file.getAbsolutePath());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String readKeyFromFile(String path) {
        File file = new File(path);
        String result = "";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
