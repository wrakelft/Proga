package managers;
import Collections.Vehicle;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import exceptions.NoElementException;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Класс отвечает за взаимодействие с коллекцией на базовом уровне
 *
 * @see Vehicle
 * @author wrakelft
 * @since 1.0
 */

public class CollectionManager {
    @XStreamImplicit
    private static HashSet<Vehicle> vehicleCollection;
    private final java.util.Date creationDate;
    private static CollectionManager instance;

    /**
     * Базовый конструктор
     *
     * @since 1.0
     */
    private CollectionManager() {
        vehicleCollection = new HashSet<>();
        creationDate = new Date();
    }

    public static CollectionManager getInstance() {
        if (instance == null) {
            instance = new CollectionManager();
        }
        return instance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Получить коллекцию
     *
     * @return коллекция со всеми элементами
     */
    public static HashSet<Vehicle> getVehicleCollection() {
        return vehicleCollection;
    }

    /**
     * Установить коллекцию
     *
     */
    public void setVehicleCollection(HashSet<Vehicle> vehicleCollection) {
        this.vehicleCollection = vehicleCollection;
    }

    /**
     * Добавить элемент в коллекцию
     *
     */
    public void addToCollection(Vehicle vehicle) {
        vehicleCollection.add(vehicle);
    }

    /**
     * Очистить коллекцию
     *
     */
    public void clearCollection() {
        vehicleCollection.clear();
    }

    /**
     * Показать все элементы коллекции
     *
     */
    public void showCollection() {
        for (Vehicle vehicle : vehicleCollection) {
            System.out.println(vehicle);
        }
    }

    /**
     * Показать информацию о коллекции
     *
     */
    public void infoCollecton() {
        System.out.println("Type: " + vehicleCollection.getClass() + "\n" +
                "Creation Date: " + getCreationDate() + "\n" + "Size: " + vehicleCollection.size());
    }

    /**
     * Удалить элемент из коллекции по его ID
     *
     * @param id
     */
    public Vehicle removeById(long id) throws NoElementException {
        Iterator<Vehicle> iterator = vehicleCollection.iterator();
        while (iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            if(vehicle.getId() == id) {
                iterator.remove();
                return vehicle;
            }
        }
        throw new NoElementException(id);
    }
}
