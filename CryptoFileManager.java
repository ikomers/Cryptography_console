import java.io.*;
import java.util.Scanner;

public class CryptoFileManager {
    //TODO разобраться с Валерой почему поля fileText и file не определялись (кидали NPE) пока я их не сделал static
    static StringBuilder fileText;

    public StringBuilder getFileText() {
        return fileText;
    }

    static File file;

    public void setFile(File file) {
        CryptoFileManager.file = file;
    }

    public void getFile() {
        System.out.println("Введите путь к файлу");
        while (file == null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String filePath = br.readLine();

                File file = new File(filePath);

                if (file.exists()) {
                    try {
                        readFileText(filePath);
                        setFile(file);
                    } catch (EmptyFileException e) {
                        System.out.println("Файл пустой");
                    }
                } else {
                    System.out.println("Файл не существует по указанному пути. \n" +
                            "Попробуйте ввести путь к файлу еще раз.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //НАМ БЫ ТУТ ЗАКРЫТЬ BR, НО ТОГДА НЕ РАБОТАЕТ ОСТАЛЬНАЯ ЧАСТЬ ПРОГРАММЫ ИБО System.in ПАДАЕТ.
        // Но мы его закрываем сразу в следующем методе
    }

    public void readFileText(String filePath) throws EmptyFileException {
        StringBuilder text = new StringBuilder();
        AlphabetIndices alphabet = new AlphabetIndices();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                //alphabet.checkSymbols(line);
                text.append(line);
            }

        } catch (IOException ex) {
            System.out.println("Ошибка при чтении файла: " + ex.getMessage());
            throw new RuntimeException(ex);
        }

        if (text.length() > 0) {
            fileText = text;
        } else {
            throw new EmptyFileException();
        }
    }


    public void writeInCommonFile(StringBuilder text, int marker) {
        String outputFile = file.getParent() + "NO MORE SECRETS.txt";
        if (marker > 0) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                fileOutputStream.write(text.toString().getBytes());
                System.out.println("Файл расшифрован и находится тут: " + outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println(text);
            System.out.println("Не удалось расшифровать файл");
        }
    }
    public void writeInCryptoFile(StringBuilder text) {

        String outputFile = file.getParent() + "TOP SECRET.txt";
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            fileOutputStream.write(text.toString().getBytes());
            System.out.println("Файл зашифрован и находится тут: " + outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}