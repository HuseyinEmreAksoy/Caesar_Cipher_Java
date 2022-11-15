import java.io.*;
import java.util.Scanner;

    public class TextFileUtil implements EncryptDecrypt {

        public boolean IsLetter(char c) {
            if (c > 96 && c < 123)
                return true;
            return c > 64 && c < 91;
        }

        @Override
        public void encrypt(String inputFileName, String key) {

            Scanner inputStream = null;
            PrintWriter outputStream = null;
            try {
                inputStream = new Scanner(new File(inputFileName));
                outputStream = new PrintWriter(inputFileName.substring(0, inputFileName.indexOf(".")) + ".encr");

            } catch (FileNotFoundException e) {
                System.out.println("hata");
                System.exit(0);
            }
            int keyCount = 0;
            while (inputStream.hasNext()) {
                String newString = "";
                String word = inputStream.nextLine();
                for (int i = 0; i < word.length(); i++) {
                    if (IsLetter(word.charAt(i))) {
                        int formula;

                        if (Character.isUpperCase(word.charAt(i))) {
                            formula = ((word.charAt(i) - 65) + (Character.toUpperCase(key.charAt(keyCount % key.length())) - 65)) % 26;
                            newString += (char) (formula + 65);
                            keyCount++;
                        } else {
                            formula = ((word.charAt(i) - 97) + (Character.toLowerCase(key.charAt(keyCount % key.length())) - 97)) % 26;
                            newString += (char) (formula + 97);
                            keyCount++;
                        }
                    } else
                        newString += word.charAt(i);
                }
                outputStream.println(newString);
            }
            inputStream.close();
            outputStream.close();
        }

        @Override
        public void decrypt(String inputFileName, String key, String outputFileName) {

            Scanner inputStream = null;
            PrintWriter outputStream = null;
            try {
                inputStream = new Scanner(new File(inputFileName));
                outputStream = new PrintWriter(outputFileName);
            } catch (FileNotFoundException e) {
                System.out.println("hata");
                System.exit(0);
            }
            int keyCount = 0;
            while (inputStream.hasNextLine()) {
                String word = inputStream.nextLine();
                String newString = "";
                for (int i = 0; i < word.length(); i++) {
                    if (IsLetter(word.charAt(i))) {
                        int formula;
                        if (Character.isUpperCase((word.charAt(i)))) {
                            formula = ((word.charAt(i) - 65) - (Character.toUpperCase(key.charAt(keyCount % key.length())) - 65)) % 26;
                            if (formula < 0) {
                                formula += 26;
                            }

                            newString += (char) (formula + 65);
                            keyCount++;
                        } else {
                            formula = ((word.charAt(i) - 97) - (Character.toLowerCase(key.charAt(keyCount % key.length())) - 97)) % 26;
                            if (formula < 0) {
                                formula += 26;
                            }
                            newString += (char) (formula + 97);
                            keyCount++;
                        }
                    } else
                        newString += word.charAt(i);
                }
                outputStream.println(newString);
            }
            inputStream.close();
            outputStream.close();
        }

        public static void main(String[] args) {
            TextFileUtil firstTry = new TextFileUtil();

            firstTry.encrypt("Crypt.txt","ALi");
            firstTry.decrypt("Crypt.txt", "e", "Crypt2.txt");

        }
    }

