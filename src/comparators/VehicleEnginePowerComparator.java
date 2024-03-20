package comparators;

import Collections.Vehicle;

import java.util.Comparator;

/**
 * Сравнение транспортных средств по мощности двигателя
 *
 * @author wrakelft
 * @since 1.0
 */
public class VehicleEnginePowerComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle vehicleOne, Vehicle vehicleTwo) {
        return vehicleOne.getEnginePower().compareTo(vehicleTwo.getEnginePower());
    }
}
