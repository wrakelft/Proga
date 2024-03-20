package managers.Command;

import Collections.Vehicle;
import exceptions.WrongArgumentException;
import managers.CollectionManager;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Данная команда группирует элементы коллекции по дате их создания
 *
 * @see BaseCommand
 * @author wrakelft
 * @since 1.0
 */
public class GroupCountingByCreationDateCommand implements BaseCommand{
    @Override
    public void execute(String[] args) throws Exception{
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 1) {
            throw new WrongArgumentException(args[1]);
        }
        HashSet<Vehicle> vehicleCollection = collectionManager.getVehicleCollection();
        if (vehicleCollection.isEmpty()) {
            System.out.println("Collection is empty");
        } else {
            Map<Date, Long> groupedByCreatioonDate = vehicleCollection.stream()
                    .collect(Collectors.groupingBy(Vehicle::getCreationDate,Collectors.counting()));
            for (Map.Entry<Date, Long> entry : groupedByCreatioonDate.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    @Override
    public String getName() {
        return "groupCountingByCreationDate: ";
    }

    @Override
    public String getDescription() {
        return "grouping collection elements by the value of the creation Date field, " +
                "displaying the number of elements in each group";
    }
}
