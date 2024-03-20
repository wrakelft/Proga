package Generators;

import Collections.Coordinates;
import Collections.FuelType;
import Collections.Vehicle;
import Collections.VehicleType;
import exceptions.NoElementException;
import exceptions.WrongArgumentException;
import managers.Validator;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Класс генерирует объект Vehicle
 *
 * @author wrakelft
 * @see Vehicle
 * @since 1.0
 */
public class VehicleAsker {
    /**
     * Метод поэтапно запрашивает данные и проверяет их для создания объекта
     *
     * @see Vehicle
     * @since 1.0
     */
    public static Vehicle createVehicle() throws Exception{
        long id = IdGenerator.generateId();
        String input, x, y;
        Coordinates coordinates;
        Scanner scanner = new Scanner(System.in);
        Vehicle vehicle;
        vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setCreationDate(new Date());

        while (true) {
            try {
                System.out.print("Input name: ");
                input = scanner.nextLine();
                Validator.inputNotEmpty(input, "Name");
                vehicle.setName(input);
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage() + " cannot be empty or null");
            }
        }

        while (true) {
            try {
                System.out.print("Input x: ");
                input = scanner.nextLine();
                Validator.inputNotEmpty(input, "x");
                Validator.xGood(input);
                x = input;
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage() + " cannot be less then -952, empty and must be an integer");
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter integer digit more then -952");
            }
        }

        while (true) {
            try {
                System.out.print("Input y: ");
                input = scanner.nextLine();
                Validator.inputNotEmpty(input, "y");
                Validator.yGood(input);
                y = input;
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage() + " cannot be less then -109, empty and must be an integer");
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter integer digit more then -109");
            }
        }
        coordinates = new Coordinates(Long.parseLong(x), Integer.parseInt(y));
        vehicle.setCoordinates(coordinates);


        while (true) {
            try {
                System.out.print("Input engine power: ");
                input = scanner.nextLine();
                Validator.inputNotEmpty(input, "engine power");
                Validator.enginePowerGood(input);
                vehicle.setEnginePower(Integer.valueOf(input));
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage() + " cannot be less then 0, empty and must be an integer");
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter integer digit more then 0");
            }
        }

        while (true) {
            try {
                System.out.print("Input vehicle type ( PLANE, SHIP, BICYCLE, MOTORCYCLE ): ");
                input = scanner.nextLine().trim().toUpperCase();
                Validator.inputNotEmpty(input, "vehicle type");
                Validator.typeGood(input);
                vehicle.setType(VehicleType.valueOf(input));
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage() + " cannot have a value other than ( PLANE, SHIP, BICYCLE, MOTORCYCLE )");
            }
        }

        while (true) {
            try {
                System.out.print("Input fuel type ( KEROSENE, MANPOWER, NUCLEAR ): ");
                input = scanner.nextLine().trim().toUpperCase();
                Validator.inputNotEmpty(input, "fuel type");
                Validator.fuelTypeGood(input);
                vehicle.setFuelType(FuelType.valueOf(input));
                System.out.println("Generating...Ready");
                return vehicle;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage() + " cannot have a value other than (  KEROSENE, MANPOWER, NUCLEAR )");
            }
        }
    }
}
