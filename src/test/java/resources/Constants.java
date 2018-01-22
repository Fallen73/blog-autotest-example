package resources;

/**
 * Константы: таймаут, сообщения об ошибках
 */
public final class Constants {

    public static final int ELEMENT_TIMEOUT = 5;
    public static final int PAGE_LOAD_TIMEOUT = 500;
    public static final int WIDTH = 1920;
    public static final int LENGTH = 1080;
    public static final String ELEMENT_ERROR_MESSAGE = "Элемент недоступен";
    public static final String CAN_NOT_GET_TO_PAGE_ERROR_MESSAGE = "Невозможно попасть на страницу";
    public static final String CAN_NOT_FIND_IMAGE_ERROR_MESSAGE = "Изображение статьи не найдено";
    public static final String CAN_NOT_FIND_TYPE_ERROR_MESSAGE = "Тип статьи не найден";
    public static final String CAN_NOT_FIND_DATE_ERROR_MESSAGE = "Дата создания статьи не найден";
    public static final String CAN_NOT_FIND_VIEW_COUNTER_ERROR_MESSAGE = "Счетчик просмотров статьи не найден";
    public static final String CAN_NOT_FIND_COMMENT_COUNTER_ERROR_MESSAGE = "Счетчик комментариев статьи не найден";
    public static final String CAN_NOT_FIND_TAGS_ERROR_MESSAGE = "Теги статьи не найдены";
    public static final String CAN_NOT_FIND_TITLE_ERROR_MESSAGE = "Заголовок статьи не найден";
    public static final String CAN_NOT_FIND_ANNOUNCE_ERROR_MESSAGE = "Описание статьи не найдено";
    public static final String TITLE_ERROR_MESSAGE = "В блоке \"%s\" заголовок на главной странице не совпадает с заголовком \"%s\"";
    public static final String VIEW_COUNTER_ERROR_MESSAGE = "В блоке \"%s\" число посещений: %s >= %s";
    public static final String NOT_SUPPORTED_BROWSER_ERROR_MESSAGE = "Выбранный браузер не поддерживается";
}
