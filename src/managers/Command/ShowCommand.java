package managers.Command;

import Collections.Vehicle;
import exceptions.WrongArgumentException;
import managers.CollectionManager;

import java.util.HashSet;

/**
 * Данная команда выводит все элементы коллекции
 *
 * @see BaseCommand
 * @author wrakelft
 * @since 1.0
 */
public class ShowCommand implements BaseCommand{


    @Override
    public void execute(String[] args) throws Exception{
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 1) {
            throw new WrongArgumentException(args[1]);
        }
        collectionManager.showCollection();
        if (collectionManager.getVehicleCollection().isEmpty()) {
            System.out.println("Collection is Empty");
        }

    }

    @Override
    public String getName() {
        return "show: ";
    }

    @Override
    public String getDescription() {
        return "show data";
    }
}
