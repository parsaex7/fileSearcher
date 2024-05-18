import java.io.*;
import java.util.ArrayList;

public class fileAnalyzer extends Thread {

    private String filePath;
    private BufferedReader in;
    private int wordsCounter;
    private int letterCounter;
    private String minWord;
    private String maxWord;

    public fileAnalyzer(String path) throws FileNotFoundException {
        filePath = path;
        BufferedReader in = new BufferedReader(new FileReader(filePath));
    }

    public void run() {
        try {
            String word;
            boolean first = true;
            while ((word = in.readLine()) != null) {
                letterCounter += word.length();
                wordsCounter++;
                if (first) {
                    minWord = word;
                    maxWord = word;
                    first = false;
                }
                if (word.length() >= maxWord.length()) {
                    maxWord = word;
                }
                if (word.length() <= minWord.length()) {
                    minWord = word;
                }
            }

        } catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
