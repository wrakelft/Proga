package managers.Command;

import Collections.Vehicle;
import Generators.VehicleAsker;
import comparators.VehicleEnginePowerComparator;
import exceptions.WrongArgumentException;
import managers.CollectionManager;

import java.util.HashSet;

/**
 * Данная команда добавляет элемент в коллекцию, если его engine power больше всех в коллекции
 *
 * @see BaseCommand
 * @author wrakelft
 * @since 1.0
 */
public class AddIfMaxCommand implements BaseCommand{
    @Override
    public void execute(String[] args) throws Exception{
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 1) {
            throw new WrongArgumentException(args[1]);
        }
        HashSet<Vehicle> vehicleCollection = collectionManager.getVehicleCollection();
        VehicleEnginePowerComparator comparator = new VehicleEnginePowerComparator();
        Vehicle referenceVehicle = VehicleAsker.createVehicle();

        if(vehicleCollection.isEmpty() || vehicleCollection.stream().allMatch(vehicle -> comparator.compare(vehicle,referenceVehicle) < 0)) {
            collectionManager.addToCollection(referenceVehicle);
            System.out.println("successfully added");
        } else {
            System.out.println("new vehicle has lower engine power!");
        }
    }

    @Override
    public String getName() {
        return "addIfMax: ";
    }

    @Override
    public String getDescription() {
        return "adds an element to the collection if its Engine Power is greater than the existing ones";
    }
}
