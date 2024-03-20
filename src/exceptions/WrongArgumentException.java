package exceptions;

/**
 * Обеспечивает исключение, если возникла ошибка в аргументе
 *
 * @author wrakelft
 * @since 1.0
 */

public class WrongArgumentException extends Exception{

    /**
     * @param argument название аргумента, который был введен с ошибкой
     *
     * @author wrakelft
     * @since 1.0
     */
    public WrongArgumentException(String argument) {
        super("Wrong argument in command: " + argument);
    }
}
