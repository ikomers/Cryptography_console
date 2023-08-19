public class Decrypter {

    /*критерий точности, который показывает
    количество вхождений наиболее
    распространенных подстрок в русском языке*/
    private int accuracy = 15;

    /*наиболее распространенные подстроки в русском языке*/
    static String[] keys = new String[]{", ", ". ", ", а", "? ", " и ", " с ", " в ", " я ", " мы ", "оло", "ото", "оро",
            "чик", "щик", "ник", "ниц", "к", "иц", "як", "ушк", "лив", "ист", "ов", "ев", "ый", "пере", "во", "по",
            "ко", "ро", "ин", "ая", "иц", "пре", "при", "за", ": ", "; ", "ст", "но", "ся", "тся", "ться", "со", "мой",
            "твой", "он", "она", "оно", "они", "ты", "сь", "по", "не", "ни", "вот", "кто", "жи", "ши"};

    public void decrypt() {
        AlphabetIndices alphabet = new AlphabetIndices();
        CryptoFileManager fileManager = new CryptoFileManager();
        StringBuilder cryptoText = fileManager.getFileText();
        StringBuilder decryptedText = new StringBuilder();
        char[] textToDecrypt = cryptoText.toString().toCharArray();
        int marker = 1;

        if (validText(cryptoText)) {
            System.out.println("Текст не нуждается в расшифровке");
        } else {
            int bias = 33;               //magic number. Is need a special class?

            for (char c : textToDecrypt) {
                int indexOfSymbol = alphabet.findCharacterIndex(c);
                int displasment = indexOfSymbol - bias;
                if (displasment < 0) {
                    decryptedText.append(AlphabetIndices.russianAlphabet[
                            AlphabetIndices.russianAlphabet.length + displasment]);
                } else {
                    decryptedText.append(AlphabetIndices.russianAlphabet[displasment]);
                }
            }
            if (validText(decryptedText)) {
                fileManager.writeInCommonFile(decryptedText, marker);
            } else {
                System.out.println("Не удалось расшифровать файл с критерием точности " + accuracy + "\n" +
                        "Пробую расшифровать с критерием точности " + (accuracy - 1));
                if (accuracy > 10) {
                    accuracy--;
                    decrypt();
                } else {
                    System.out.println("""
                            ____________________________________________________________
                            Похоже, что шпионы перепутали ключи.\s
                            Но для этого у нас есть 3 режим работы программы.\s
                            Запустите программу еще раз и выберите метод грубой силы""");
                }
            }
        }
    }

    public void bruteForce() {
        AlphabetIndices alphabet = new AlphabetIndices();
        CryptoFileManager fileManager = new CryptoFileManager();
        StringBuilder cryptoText = fileManager.getFileText();
        StringBuilder decryptedText = new StringBuilder();
        char[] textToDecrypt = cryptoText.toString().toCharArray();
        int marker = 1;
        if (validText(cryptoText)) {
            System.out.println("Текст не нуждается в расшифровке");
        } else {
            int bias = 1;
            while (!validText(decryptedText)) {
                if (bias < 75) {
                    decryptedText = new StringBuilder();
                    for (char c : textToDecrypt) {
                        int indexOfSymbol = alphabet.findCharacterIndex(c);
                        int displasment = indexOfSymbol - bias;
                        if (displasment < 0) {
                            decryptedText.append(AlphabetIndices.russianAlphabet[
                                    AlphabetIndices.russianAlphabet.length + displasment]);
                        } else {
                            decryptedText.append(AlphabetIndices.russianAlphabet[displasment]);
                        }
                    }
                    bias++;
                } else {
                    marker = -1;
                    break;
                }
            }
            fileManager.writeInCommonFile(decryptedText, marker);
        }
    }

    public boolean validText(StringBuilder encryptedText) {
        String substring = encryptedText.toString().toLowerCase();
        int validCounter = 0;
        for (String key : keys) {
            if (substring.contains(key)) {
                validCounter++;
            }
        }
        return validCounter >= accuracy;
    }
}