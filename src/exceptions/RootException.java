package exceptions;

/**
 * Обеспечивает исключение, если программа не имеет прав доступа к какому-либо файлу
 * @see managers.Command.ExecuteScriptCommand
 * @author wrakelft
 * @since 1.0
 */
public class RootException extends Exception{

    public RootException() {
        super("You do not have enough rights to read the file");
    }
}
