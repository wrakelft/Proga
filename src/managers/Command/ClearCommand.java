package managers.Command;

import exceptions.WrongArgumentException;
import managers.CollectionManager;

/**
 * Данная команда очищает коллекцию
 *
 * @see BaseCommand
 * @author wrakelft
 * @since 1.0
 */
public class ClearCommand implements BaseCommand{
    @Override
    public void execute(String[] args) throws Exception{
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 1) {
            throw new WrongArgumentException(args[1]);
        }
        if (collectionManager.getVehicleCollection().isEmpty()) {
            System.out.println("Collection already cleared");
        } else {
            collectionManager.clearCollection();
            System.out.println("Collection cleared");
        }
    }

    @Override
    public String getName() {
        return "clear: ";
    }

    @Override
    public String getDescription() {
        return "Clears the collection";
    }
}
