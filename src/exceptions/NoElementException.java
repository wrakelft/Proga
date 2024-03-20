package exceptions;

/**
 * Исключение если не существует элемента коллекции с указанным ID
 * @see managers.Command.RemoveById
 * @author wrakelft
 * @since 1.0
 */

public class NoElementException extends Exception{

    /**
     * @param id ID элемента
     *
     * @author wrakelft
     * @since 1.0
     */
    public NoElementException(long id) {
        super("No element in collection with id: " + id);
    }


}
