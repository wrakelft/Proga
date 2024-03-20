import managers.Console;

import java.io.File;

/**
 * Класс, который запускает программу
 *
 * @author wrakelft
 * @since 1.0
 */
public class Main {
    /**
     * Начало программы
     *
     * @param args аргумент командной строки
     */
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Something wrong with argument");
        }
        else {
            File file = new File(args[0]);
            if (file.canRead() && file.canWrite()) {
                Console console = new Console();
                console.start(System.in, args);
            } else {
                System.out.println("You do not have enough root or file don't exists");
            }
        }
    }
}