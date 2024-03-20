package managers.Command;

import Generators.IdGenerator;
import exceptions.NoArgumentException;
import exceptions.NoElementException;
import exceptions.WrongArgumentException;
import exceptions.WrongArgumentException;
import managers.CollectionManager;

/**
 * Данная команда удаляет элемент из коллекции по его ID
 *
 * @see BaseCommand
 * @author wrakelft
 * @since 1.0
 */
public class RemoveById implements BaseCommand{
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
            collectionManager.removeById(Long.parseLong(args[1]));
            IdGenerator.removeId(Long.parseLong(args[1]));
            System.out.println("Removing complete! Сhanges applied");
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
        return "removeById id: ";
    }

    @Override
    public String getDescription() {
        return "Remove element by id";
    }
}
