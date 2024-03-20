package managers;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Класс отвечает за чтение командной строки
 * Обеспечивает связь между пользователем и командами
 *
 * @see CommandManager
 * @author wrakelft
 * @since 1.0
 */
public class Console {

    public static String filepath;

    /**
     * Выполнение команды из InputStream
     *
     * @param input откуда происходит чтение
     * @param args путь до файла
     */
    public void start(InputStream input, String[] args) {
        Scanner scanner = new Scanner(input);
        CommandManager manager = new CommandManager();
        CollectionManager collectionManager = CollectionManager.getInstance();
        try {
            String filepath = args[0];
            FileManager fileManager = FileManager.getInstance(filepath);
            collectionManager.setVehicleCollection(fileManager.readCollection());
            System.out.println("Everything is fine");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error while reading file: " + args[0]);
            System.exit(0);
        }


        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            if (!command.isEmpty()) {
                try {
                    manager.startExecute(command);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
