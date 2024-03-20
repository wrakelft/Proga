package managers;

import exceptions.UnknowCommandException;
import managers.Command.*;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;

/**
 * Класс обеспечивает связь между командами и CollectionManager
 *
 * @see CollectionManager
 * @author wrakelft
 * @since 1.0
 */
public class CommandManager {
    private static LinkedHashMap<String, BaseCommand> commandList;
    public static ArrayDeque<BaseCommand> lastTwelveCommand = new ArrayDeque<>();

    public CommandManager() {
        commandList = new LinkedHashMap<>();
        commandList.put("help", new HelpCommand());
        commandList.put("info", new InfoCommand());
        commandList.put("show", new ShowCommand());
        commandList.put("removeById", new RemoveById());
        commandList.put("clear", new ClearCommand());
        commandList.put("save", new SaveCommand());
        commandList.put("exit", new ExitCommand());
        commandList.put("add", new AddCommand());
        commandList.put("removeLower", new RemoveLowerCommand());
        commandList.put("updateId", new UpdateIdCommand());
        commandList.put("addIfMax", new AddIfMaxCommand());
        commandList.put("history", new HistoryCommand());
        commandList.put("countByFuelType", new CountByFuelTypeCommand());
        commandList.put("countLessThenFuelType", new CountLessThenFuelTypeCommand());
        commandList.put("executeScript", new ExecuteScriptCommand());
        commandList.put("groupCountingByCreationDate", new GroupCountingByCreationDateCommand());
    }

    /**
     * Выполняет команду, сохраняя ее имя
     *
     * @since 1.0
     */
    public static void startExecute(String line) throws Exception{
        String commandName = line.split(" ")[0];
        if (!commandList.containsKey(commandName)) {
            throw new UnknowCommandException(commandName);
        }
        BaseCommand command = commandList.get(commandName);
        command.execute(line.split(" "));
        if (lastTwelveCommand.size() >= 12) {
            lastTwelveCommand.removeLast();
        } else {
            lastTwelveCommand.addFirst(command);
        }
    }

    public LinkedHashMap<String, BaseCommand> getCommandList() {
        return commandList;
    }

    }



