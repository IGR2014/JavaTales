// Клас що описує казку
public class Tale {


    // Ындекс казки у БД
    long id;
    // Автор казки
    String author;
    // Назва казки
    String name;
    // Текст казки
    String text;
    // Вік для якого підходить казка
    int age;


    // Конструктор
    public Tale(long id, String author, String name, String text, int age) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.text = text;
        this.age = age;
    }


}
