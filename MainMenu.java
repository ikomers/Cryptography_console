import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenu {

    public void getFile() {
        new CryptoFileManager().getFile();
    }

    public void getMenu() {
        System.out.println("""
                Выберите действие:
                зашифровать файл (введите '1')
                расшифровать файл (введите '2')
                взломать шифровку методом грубой силы (введите '3')""");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            boolean isActionDone = false;
            int foolCounter = 0;
            int i = 0;
            while (!isActionDone) {
                try {
                    i = Integer.parseInt(br.readLine());
                    if ((i == 1) || (i == 2) || (i == 3)) {
                        isActionDone = true;
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    switch (foolCounter) {
                        case 0 -> {
                            System.out.println("Нужно вводить только числа 1, 2 или 3");
                            foolCounter++;
                            continue;
                        }
                        case 1 -> {
                            System.out.println("ТОЛЬКО ЦИФРЫ 1, 2 или 3");
                            foolCounter++;
                            continue;
                        }
                        default -> {
                            System.out.println("Продолжайте пробовать, однажды у вас получится");
                            continue;
                        }
                    }
                }
            }
            switch (i) {
                case 1 -> new Encrypter().encrypt();
                case 2 -> new Decrypter().decrypt();
                case 3 -> new Decrypter().bruteForce();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}