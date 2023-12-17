import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// Клас роботи з БД
public class TalesDatabase {


    // Об'єкт підключення до БД
    private Connection connection;


    // Конструктор
    public TalesDatabase() {
        // Try
        try {
            // Необхідно для роботи з БД
            Class.forName("org.sqlite.JDBC");
            // Підключення до файлу БД "tales.db"
            connection = DriverManager.getConnection("jdbc:sqlite:tales.db");
            // Створення таблиць БД
            createTable();
        }
        // Catch
        catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }


    // Метод створення таблиць у БД
    private void createTable() {
        // Запит до БД на створення таблиці казок
        String sql = "CREATE TABLE IF NOT EXISTS tales (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "author TEXT NOT NULL," +
                "name TEXT NOT NULL," +
                "tale TEXT NOT NULL," +
                "age INTEGER NOT NULL" +
                ");";
        // Спроба підготувати запит до БД
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Виконання запиту
            statement.executeUpdate();
        }
        // Catch
        catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

    }


    // Метод отримання казок з БД
    public List<Tale> getAllTales() {
        // Масив казок для зберігання результатів, отриманих з БД
        List<Tale> tales = new ArrayList<>();
        // Запит до БД на отримання казок що там зберігаються
        String sql = "SELECT * FROM tales;";
        // Спроба підготувати запит до БД
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Виконання запиту
            ResultSet res = statement.executeQuery();
            // Розбір отриманих результатів з БД
            while (res.next()) {
                // Парсинг даних з БД
                long id = res.getLong("id");
                String author = res.getString("author");
                String name = res.getString("name");
                String text = res.getString("tale");
                int age = res.getInt("age");
                // Додавання казки до списку
                tales.add(new Tale(id, author, name, text, age));
            }
        }
        // Catch
        catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        // Повернення списку казок отриманих з БД
        return tales;
    }


    // Метод додання нової казки у БД
    public void addTale(@NotNull Tale tale) {
        // Запит до БД на додавання казки у таблицю казок
        String sql = "INSERT INTO tales (author, name, tale, age) VALUES (?, ?, ?, ?);";
        // Спроба підготувати запит до БД
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Параметри запросу
            statement.setString(1, tale.author);
            statement.setString(2, tale.name);
            statement.setString(3, tale.text);
            statement.setInt(4, tale.age);
            // Виконання запиту
            statement.executeUpdate();
        }
        // Catch
        catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }


    // Метод закриття підключення до БД
    public void close() {
        // Try
        try {
            // Підключення існує ?
            if (connection != null) {
                // Закрити підключення
                connection.close();
            }
        }
        // Catch
        catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }


}
