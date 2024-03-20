package managers.Command;

import exceptions.WrongArgumentException;
import managers.CollectionManager;

/**
 * Данная команда выводит информацию о коллекции
 *
 * @see BaseCommand
 * @author wrakelft
 * @since 1.0
 */
public class InfoCommand implements BaseCommand{
    @Override
    public void execute(String[] args) throws Exception{
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (args.length > 1) {
            throw new WrongArgumentException(args[1]);
        }
        collectionManager.infoCollecton();
        System.out.println();
    }

    @Override
    public String getName() {
        return "info: ";
    }

    @Override
    public String getDescription() {
        return "information about collection";
    }
}
