package moduletwo5.page.enums;

public enum MainMenu {

    LATEST(0, "Последние файлы"),
    FILES(1, "Файлы"),
    PHOTO(2, "Все фотографии"),
    ALBUMS(3,"Альбомы"),
    GENERAL_ACCESS(4, "Публичные ссылки"),
    HISTORY(5, "История"),
    ARCHIVE(6, "Архив"),
    BASKET(7, "Корзина");

    private int index;
    private String title;

    MainMenu(int index, String title) {
        this.index = index;
        this.title = title;
    }

    public int getIndex() {
        return index;
    }

    public String getTitle() {
        return title;
    }
}
