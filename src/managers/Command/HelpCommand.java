package managers.Command;

import exceptions.WrongArgumentException;
import managers.CommandManager;

import java.util.LinkedHashMap;

/**
 * Данная команда выводит все возможные команды и их описание
 *
 * @see BaseCommand
 * @author wrakelft
 * @since 1.0
 */
public class HelpCommand implements BaseCommand{

    @Override
    public void execute(String[] args) throws Exception{
        CommandManager commandManager = new CommandManager();
        if (args.length > 1) {
            throw new WrongArgumentException(args[1]);
        }
        LinkedHashMap<String, BaseCommand> commandList = commandManager.getCommandList();
        for (String name : commandList.keySet()) {
            BaseCommand command = commandList.get(name);
            System.out.println(command.getName() + " " + command.getDescription());
        }
    }

    @Override
    public String getName() {
        return "help: ";
    }

    @Override
    public String getDescription() {
        return "Command to get information";
    }
}
