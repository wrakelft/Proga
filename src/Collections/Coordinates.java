package Collections;

/**
 * Модель объекта "Координаты"
 * Содержит геттеры/сеттеры каждого поля класса
 * Некоторые поля имеют ограничения
 *
 * @author wrakelft
 * @since 1.0
 */
public class Coordinates {
    /**
     * Координата X
     * Значение поля должно быть больше -952
     *
     * @since 1.0
     */
    private long x;
    /**
     * Координата Y
     * Значение поля должно быть больше -952
     *
     * @since 1.0
     */
    private int y;

    /**
     * Базовый конструктор
     *
     * @since 1.0
     */
    public Coordinates(long x, int y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
