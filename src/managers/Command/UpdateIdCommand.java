package managers.Command;

import Collections.Vehicle;
import Generators.VehicleAsker;
import exceptions.NoArgumentException;
import exceptions.NoElementException;
import exceptions.WrongArgumentException;
import managers.CollectionManager;

import java.util.Date;
import java.util.HashSet;

/**
 * Данная команда обновляет значения полей элемента по его ID
 *
 * @see BaseCommand
 * @author wrakelft
 * @since 1.0
 */
public class UpdateIdCommand implements BaseCommand{
    @Override
    public void execute(String[] args) throws Exception{
        try {
            CollectionManager collectionManager = CollectionManager.getInstance();
            if (args.length > 2) {
                throw new WrongArgumentException(args[2]);
            }
            if (args.length < 2) {
                throw new NoArgumentException("id");
            }
            HashSet<Vehicle> vehicleCollection = collectionManager.getVehicleCollection();
            Vehicle referenceVehicle = null;
            long inputEl = Long.parseLong(args[1]);
            boolean found = false;
            Date dateForUpdate = null;
            for (Vehicle vehicle : vehicleCollection) {
                if (vehicle.getId() == inputEl) {
                    dateForUpdate = vehicle.getCreationDate();
                    collectionManager.removeById(inputEl);
                    referenceVehicle = VehicleAsker.createVehicle();
                    referenceVehicle.setIdForUpdate(inputEl);
                    referenceVehicle.setCreationDate(dateForUpdate);
                    collectionManager.addToCollection(referenceVehicle);
                    found = true;
                    System.out.println("Success");
                    break;
                }
            }
              if (!found){
                    throw new NoElementException(inputEl);
                }
        } catch (NoElementException e) {
            System.out.println(e.getMessage());
            System.out.println("The program continues safely");
        }
          catch (NumberFormatException e) {
            System.out.println("Please enter digit");
        }
    }

    @Override
    public String getName() {
        return "updateId {element}: ";
    }

    @Override
    public String getDescription() {
        return "update element";
    }
}
