public class AlphabetIndices {

    int[] russianAlphabetIntValues = getIntsOfAlphabetCharacters();

    //Всего 75 элементов
    static char[] russianAlphabet = {
            'А', 'Б', 'В', '.', 'Г', 'Д', 'Е', 'Ё', ',', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М',
            'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', ' ', 'Ч', 'Ш', 'Щ', 'Ъ',
            'Ы', 'Ь', '"', 'Э', 'Ю', ':', 'Я', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', '-', 'р', 'с',  '!', 'т', 'у', 'ф', 'х',
            'ц', 'ч', 'ш', '?', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'
    };


    private int[] getIntsOfAlphabetCharacters() {
        int[] alphabet = new int[russianAlphabet.length];
        for (int i = 0; i < russianAlphabet.length; i++) {
            alphabet[i] = (int) russianAlphabet[i];
        }
        return alphabet;
    }


    public int findCharacterIndex(char ch) {
        for (int i = 0; i < russianAlphabet.length; i++) {
            if (ch == russianAlphabet[i]) {
                return i;
            }
        }

        return -1; // Символ не найден
    }

    public static boolean isCharacterValid(char ch) {
        for (char validCh : russianAlphabet) {
            if (ch == validCh) {
                return true;
            }
        }
        return false;
    }

}