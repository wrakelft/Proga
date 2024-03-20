package managers.Command;

import Collections.Vehicle;
import Generators.IdGenerator;
import Generators.VehicleAsker;
import comparators.VehicleEnginePowerComparator;
import exceptions.NoArgumentException;
import exceptions.WrongArgumentException;
import managers.CollectionManager;

import java.util.HashSet;

/**
 * Данная команда удаляет все элементы, значение engine power которых меньше чем у заданного
 *
 * @see BaseCommand
 * @author wrakelft
 * @since 1.0
 */

public class RemoveLowerCommand implements BaseCommand{
    @Override
    public void execute(String[] args) throws Exception{
        try {
            CollectionManager collectionManager = CollectionManager.getInstance();
            if (args.length > 2) {
                throw new WrongArgumentException(args[2]);
            }
            if (args.length < 2) {
                throw new NoArgumentException("engine power");
            }
            VehicleEnginePowerComparator comparator = new VehicleEnginePowerComparator();
            HashSet<Vehicle> vehicleCollection = collectionManager.getVehicleCollection();
            HashSet<Vehicle> toRemove = new HashSet<>();
            Vehicle referenceVehicle = null;
            long inputEl = Long.parseLong(args[1]);
            for (Vehicle vehicle : vehicleCollection) {
                if (vehicle.getId() == inputEl) {
                    referenceVehicle = vehicle;
                }
            }
            for (Vehicle vehicle : vehicleCollection) {
                if (comparator.compare(vehicle, referenceVehicle) < 0) {
                    toRemove.add(vehicle);
                    IdGenerator.removeId(vehicle.getId());
                }
            }
            if (toRemove.isEmpty()) {
                System.out.println("no elements to remove");
            } else {
                vehicleCollection.removeAll(toRemove);
                System.out.println("remove successfully");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter digit");
        } catch (NullPointerException e) {
            System.out.println("No element with this id");
        }


    }

    @Override
    public String getName() {
        return "removeLower {element}: ";
    }

    @Override
    public String getDescription() {
        return "remove elements with lower then the element";
    }
}
