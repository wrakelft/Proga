package managers;

import Collections.FuelType;
import Collections.Vehicle;
import Collections.VehicleType;
import Generators.IdGenerator;
import exceptions.NoElementException;
import exceptions.WrongArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Класс для проверки на валидность полей объекта Vehicle и для проверки входных данных из файла
 *
 * @author wrakelft
 * @since 1.0
 */
public class Validator {
    HashSet<Vehicle> vehicleList;

    public Validator(HashSet<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    /**
     * Проверяет, что значение строки не null
     *
     * @param arg  аргумент строки
     * @param data что из Organization мы проверяем
     * @throws WrongArgumentException если значение arg null
     */
    public static void inputNotEmpty(String arg, String data) throws WrongArgumentException{
        if(arg.isEmpty() || arg.trim().isEmpty()) {
            throw new WrongArgumentException(data);
        }
    }

    /**
     * Проверяет корректность координат по X
     *
     * @param arg аргумент строки
     * @throws WrongArgumentException если координата некорректна
     * @see Collections.Coordinates
     */
    public static void xGood(String arg) throws WrongArgumentException{
        long v = Long.parseLong(arg);
        if(v <= -952 || (v >= 9223372036854775807l)) {
            throw new WrongArgumentException("x");
        }
    }

    /**
     * Проверяет корректность координат по Y
     *
     * @param arg аргумент строки
     * @throws WrongArgumentException если координата некорректна
     * @see Collections.Coordinates
     */
    public static void yGood(String arg) throws WrongArgumentException{
        int v = Integer.parseInt(arg);
        if((v <= -109) || (v >= 2147483647)) {
            throw new WrongArgumentException("y");
        }
    }

    /**
     * Проверяет корректность значения engine power
     *
     * @param arg аргумент строки
     * @throws WrongArgumentException если некорректно
     * @see Collections.Vehicle
     */
    public static void enginePowerGood(String arg) throws WrongArgumentException{
        int v = Integer.parseInt(arg);
        if (v <= 0) {
            throw new WrongArgumentException("engine power");
        }
    }

    /**
     * Проверяет корректность значения Vehicle type
     *
     * @param arg аргумент строки
     * @throws WrongArgumentException если некорректно
     * @see Collections.VehicleType
     */
    public static void typeGood(String arg) throws WrongArgumentException{
        try {
            VehicleType.valueOf(arg.toUpperCase());
        } catch (Exception e) {
            throw new WrongArgumentException("vehicle type");
        }
    }

    /**
     * Проверяет корректность значения Fuel Type
     *
     * @param arg аргумент строки
     * @throws WrongArgumentException если некорректно
     * @see Collections.FuelType
     */
    public static void fuelTypeGood(String arg) throws WrongArgumentException{
        try {
            FuelType.valueOf(arg.toUpperCase());
        } catch (Exception e) {
            throw new WrongArgumentException("fuel type");
        }
    }

    /**
     * Проверяет корректность полей элементов из входного файла, удаляет элемент при несоответствии
     *
     * @see Collections.Vehicle
     */
    public HashSet<Vehicle> validateFile() {
        for(Iterator<Vehicle> iterator = vehicleList.iterator(); iterator.hasNext();) {
            Vehicle vehicle = iterator.next();
            if((vehicle.getId() > 0) && ((IdGenerator.idUnique(vehicle.getId())))) {
                IdGenerator.addId(vehicle.getId());
            }else {
                iterator.remove();
                System.out.println("id must be greater than 0, unique and as an integer");
                System.out.println("element from the input file was removed");
            }
            if(vehicle.getName() == null || vehicle.getName().isEmpty()) {
                iterator.remove();
                System.out.println("name cannot be empty or null");
                System.out.println("element from the input file was removed");
            }
            if(vehicle.getCoordinates() == null) {
                iterator.remove();
                System.out.println("coordinates cannot be null");
                System.out.println("element from the input file was removed");
            }

            if (vehicle.getEnginePower() == null || vehicle.getEnginePower() <= 0) {
                iterator.remove();
                System.out.println("engine power cannot be less then 0 and as an integer");
                System.out.println("element from the input file was removed");
            }

            if(vehicle.getCoordinates().getX() <= -952) {
                iterator.remove();
                System.out.println("X cannot be less then -952 and as an integer");
                System.out.println("element from the input file was removed");
            }
            if(vehicle.getCoordinates().getY() <= -109) {
                iterator.remove();
                System.out.println("Y cannot be less then -109 and as an integer");
                System.out.println("element from the input file was removed");
            }
        }
        return vehicleList;
    }
}
