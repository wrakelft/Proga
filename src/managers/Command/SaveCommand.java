package managers.Command;

import exceptions.WrongArgumentException;
import managers.CollectionManager;
import managers.FileManager;

import static managers.Console.filepath;

/**
 * Данная команда сохраняет коллекцию в XML файл
 *
 * @see BaseCommand
 * @author wrakelft
 * @since 1.0
 */
public class SaveCommand implements BaseCommand{
    @Override
    public void execute(String[] args) throws Exception{
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 1) {
            throw new WrongArgumentException(args[1]);
        }
        FileManager fileManager = FileManager.getInstance(filepath);
        fileManager.writeCollection(collectionManager.getVehicleCollection());
        System.out.println("saved successfully");
    }

    @Override
    public String getName() {
        return "save: ";
    }

    @Override
    public String getDescription() {
        return "Save collection to xml file";
    }
}
