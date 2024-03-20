package managers.Command;

import Generators.VehicleAsker;
import exceptions.WrongArgumentException;
import managers.CollectionManager;

/**
 * Данная команда добавляет новый элемент в коллекцию
 *
 * @see BaseCommand
 * @author wrakelft
 * @since 1.0
 */
public class AddCommand implements BaseCommand{
    @Override
    public void execute(String[] args) throws Exception{
        VehicleAsker vehicleAsker = new VehicleAsker();
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 1) {
            throw new WrongArgumentException(args[1]);
        }
        collectionManager.addToCollection(vehicleAsker.createVehicle());
        System.out.println("successfully added");
    }

    @Override
    public String getName() {
        return "add {element}: ";
    }

    @Override
    public String getDescription() {
        return "add new vehicle in collection";
    }
}
