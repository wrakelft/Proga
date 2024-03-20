package managers.Command;

import Collections.Coordinates;
import Collections.FuelType;
import Collections.Vehicle;
import Collections.VehicleType;
import Generators.IdGenerator;
import Generators.VehicleAsker;
import comparators.VehicleEnginePowerComparator;
import exceptions.*;
import managers.CollectionManager;
import managers.CommandManager;
import managers.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Данная команда выполняет скрипт файл
 *
 * @see BaseCommand
 * @author wrakelft
 * @since 1.0
 */
public class ExecuteScriptCommand implements BaseCommand{
    private static Stack<File> stack = new Stack<>();
    @Override
    public void execute(String[] args) throws Exception {
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 2) {
            throw new WrongArgumentException(args[2]);
        }
        if (args.length < 2) {
            throw new NoArgumentException("script name");
        }
        File file = new File(args[1]);
        try {
            if (!file.canRead()) {
                throw new RootException();
            }
            if (stack.isEmpty()) {
                stack.add(file);
            } else if (stack.contains(file)) {
                throw new RecursionException();
            }
            stack.add(file);
        } catch (RecursionException ex) {
            stack.pop();
            System.out.println(ex.getMessage());
            System.out.println("Script Executing finished with error");
            if (!stack.isEmpty()) {
                stack.pop();
            }
            return;
        }

        String path = args[1];
        Scanner scanner = new Scanner(new File(path), StandardCharsets.UTF_8.name());
        String[] vehicleD = new String[6];
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if ((line.split(" ")[0].equals("add"))) {
                    try {
                        String[] parts = line.split(" ");
                        if (parts.length > 1) {
                            throw new WrongArgumentException(parts[2]);
                        }
                        for (int n = 0; n < 6 && scanner.hasNextLine(); n++) {
                            vehicleD[n] = scanner.nextLine();
                        }
                        Vehicle vehicle1 = new Vehicle();
                        Validator.inputNotEmpty(vehicleD[0], "Name");
                        Validator.inputNotEmpty(vehicleD[1], "x");
                        Validator.xGood(vehicleD[1]);
                        Validator.inputNotEmpty(vehicleD[2], "y");
                        Validator.yGood(vehicleD[2]);
                        Validator.inputNotEmpty(vehicleD[3], "engine power");
                        Validator.enginePowerGood(vehicleD[3]);
                        Validator.inputNotEmpty(vehicleD[4], "Vehicle type");
                        Validator.typeGood(vehicleD[4]);
                        Validator.inputNotEmpty(vehicleD[5], "fuel type");
                        Validator.fuelTypeGood(vehicleD[5]);
                        Coordinates coordinates = new Coordinates(Long.parseLong(vehicleD[1]), Integer.parseInt(vehicleD[2]));
                        vehicle1.setId(IdGenerator.generateId());
                        vehicle1.setName(vehicleD[0]);
                        vehicle1.setCoordinates(coordinates);
                        vehicle1.setCreationDate(new Date());
                        vehicle1.setEnginePower(Integer.parseInt(vehicleD[3]));
                        vehicle1.setType(VehicleType.valueOf(vehicleD[4].toUpperCase()));
                        vehicle1.setFuelType(FuelType.valueOf(vehicleD[5].toUpperCase()));
                        collectionManager.addToCollection(vehicle1);
                        System.out.println("New element successfully added");
                    } catch (WrongArgumentException exx) {
                        stack.pop();
                        System.out.println(exx.getMessage());
                        System.out.println("Script Executing finished with error");
                        return;
                    }
                } else if (line.split(" ")[0].equals("updateId")) {
                    try {
                        String[] parts = line.split(" ");
                        if (parts.length > 2) {
                            throw new WrongArgumentException(parts[2]);
                        }
                        if (parts.length < 2) {
                            throw new NoArgumentException("id");
                        }
                        String rightPart = parts[1];
                        for (int n = 0; n < 6 && scanner.hasNextLine(); n++) {
                            vehicleD[n] = scanner.nextLine();
                        }
                        Vehicle referenceVehicle = new Vehicle();
                        Validator.inputNotEmpty(vehicleD[0], "Name");
                        Validator.inputNotEmpty(vehicleD[1], "x");
                        Validator.xGood(vehicleD[1]);
                        Validator.inputNotEmpty(vehicleD[2], "y");
                        Validator.yGood(vehicleD[2]);
                        Validator.inputNotEmpty(vehicleD[3], "engine power");
                        Validator.enginePowerGood(vehicleD[3]);
                        Validator.inputNotEmpty(vehicleD[4], "Vehicle type");
                        Validator.typeGood(vehicleD[4]);
                        Validator.inputNotEmpty(vehicleD[5], "fuel type");
                        Validator.fuelTypeGood(vehicleD[5]);
                        Coordinates coordinates = new Coordinates(Long.parseLong(vehicleD[1]), Integer.parseInt(vehicleD[2]));
                        HashSet<Vehicle> vehicleCollection = collectionManager.getVehicleCollection();
                        long inputEl = Long.parseLong(rightPart);
                        Date dateForUpdate = null;
                        boolean found = false;
                        for (Vehicle vehicleColl : vehicleCollection) {
                            if (vehicleColl.getId() == inputEl) {
                                dateForUpdate = vehicleColl.getCreationDate();
                                collectionManager.removeById(inputEl);
                                referenceVehicle.setIdForUpdate(inputEl);
                                referenceVehicle.setName(vehicleD[0]);
                                referenceVehicle.setCoordinates(coordinates);
                                referenceVehicle.setCreationDate(dateForUpdate);
                                referenceVehicle.setEnginePower(Integer.parseInt(vehicleD[3]));
                                referenceVehicle.setType(VehicleType.valueOf(vehicleD[4].toUpperCase()));
                                referenceVehicle.setFuelType(FuelType.valueOf(vehicleD[5].toUpperCase()));
                                collectionManager.addToCollection(referenceVehicle);
                                System.out.println("Element successfully updated");
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            throw new NoElementException(inputEl);
                        }
                        for (int v = 0; v < parts.length; v++) {
                            parts[v] = null;
                        }
                    } catch (WrongArgumentException exx) {
                        stack.pop();
                        System.out.println(exx.getMessage());
                        System.out.println("Script Executing finished with error");
                        return;
                    } catch (NoArgumentException exx) {
                        stack.pop();
                        System.out.println(exx.getMessage());
                        System.out.println("Script Executing finished with error");
                        return;
                    } catch (NoElementException ex) {
                        stack.pop();
                        System.out.println(ex.getMessage());
                        System.out.println("Script Executing finished with error");
                        return;
                    }
                } else if (line.split(" ")[0].equals("addIfMax")) {
                    try {
                        String[] parts = line.split(" ");
                        if (parts.length > 1) {
                            throw new WrongArgumentException(parts[1]);
                        }
                        for (int n = 0; n < 6 && scanner.hasNextLine(); n++) {
                            vehicleD[n] = scanner.nextLine().toUpperCase();
                        }
                        Vehicle vehicle2 = new Vehicle();
                        Validator.inputNotEmpty(vehicleD[0], "Name");
                        Validator.inputNotEmpty(vehicleD[1], "x");
                        Validator.xGood(vehicleD[1]);
                        Validator.inputNotEmpty(vehicleD[2], "y");
                        Validator.yGood(vehicleD[2]);
                        Validator.inputNotEmpty(vehicleD[3], "engine power");
                        Validator.enginePowerGood(vehicleD[3]);
                        Validator.inputNotEmpty(vehicleD[4], "Vehicle type");
                        Validator.typeGood(vehicleD[4]);
                        Validator.inputNotEmpty(vehicleD[5], "fuel type");
                        Validator.fuelTypeGood(vehicleD[5]);
                        Coordinates coordinates = new Coordinates(Long.parseLong(vehicleD[1]), Integer.parseInt(vehicleD[2]));
                        vehicle2.setId(IdGenerator.generateId());
                        vehicle2.setName(vehicleD[0]);
                        vehicle2.setCoordinates(coordinates);
                        vehicle2.setCreationDate(new Date());
                        vehicle2.setEnginePower(Integer.parseInt(vehicleD[3]));
                        vehicle2.setType(VehicleType.valueOf(vehicleD[4].toUpperCase()));
                        vehicle2.setFuelType(FuelType.valueOf(vehicleD[5].toUpperCase()));
                        VehicleEnginePowerComparator comparator = new VehicleEnginePowerComparator();
                        HashSet<Vehicle> vehicleCollection = collectionManager.getVehicleCollection();
                        if (vehicleCollection.isEmpty() || vehicleCollection.stream().allMatch(vehicle -> comparator.compare(vehicle, vehicle2) < 0)) {
                            collectionManager.addToCollection(vehicle2);
                            System.out.println("New element successfully added");
                        } else {
                            System.out.println("New vehicle has lower engine power! Command don't execute");
                        }
                        for (int v = 0; v < parts.length; v++) {
                            parts[v] = null;
                        }
                    } catch (WrongArgumentException exx) {
                        stack.pop();
                        System.out.println(exx.getMessage());
                        System.out.println("Script Executing finished with error");
                        return;
                    }
                } else {
                    CommandManager.startExecute(line);
                }
            }
            stack.pop();
            scanner.close();
            System.out.println("Script command executing complete!");
        } catch (EmptyStackException e) {
            System.out.print("");
        } finally {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }
    }

    @Override
    public String getName() {
        return "executeScript {filename}: ";
    }

    @Override
    public String getDescription() {
        return "execute script from file";
    }
}
