public class Encrypter {

    public void encrypt() {
        AlphabetIndices alphabet = new AlphabetIndices();
        CryptoFileManager file = new CryptoFileManager();
        StringBuilder text = file.getFileText();   //исходный текст

        StringBuilder cryptoText = new StringBuilder(); //зашифрованный текст
        char[] textToCrypt = text.toString().toCharArray();
        //задаем смещение для шифра Цезаря рандомом
//        Random random = new Random();
//        int bias = random.nextInt(20);
        int bias = 33;                                        //magic number. Is need a special class?

        //Производим смещение каждого символа. Если смещение больше, чем массив,
        // тогда делаем разность и начинаем сначала
        for (int i = 0; i < textToCrypt.length; i++) {
            if (!AlphabetIndices.isCharacterValid(textToCrypt[i])) {
                continue;
            }
            int indexOfSymbol = alphabet.findCharacterIndex(textToCrypt[i]);
            int displasment = indexOfSymbol + bias;
            if (displasment >= AlphabetIndices.russianAlphabet.length) {
                cryptoText.append(AlphabetIndices.russianAlphabet[displasment
                        - AlphabetIndices.russianAlphabet.length]);
            } else {
                cryptoText.append(AlphabetIndices.russianAlphabet[displasment]);
            }
        }
        file.writeInCryptoFile(cryptoText);
    }
}