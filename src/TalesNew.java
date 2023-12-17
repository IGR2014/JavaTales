import javax.swing.*;
import java.awt.*;


// Форма додавання казки у БД
public class TalesNew extends JFrame {


    // Об'єкт роботи з БД
    private final TalesDatabase database;
    // Поле для зберігання ім'я автора
    private final TextField authorField;
    // Поле для зберігання назви казки
    private final TextField nameField;
    // Поле для зберігання тексту казки
    private final TextArea taleField;
    // Поле для зберігання віку
    private final TextField ageField;


    // Конструктор
    public TalesNew() {

        // Підключення до БД казок
        database = new TalesDatabase();

        // Розміщення елементів у вікні програми
        setLayout(new BorderLayout());

        // Шрифт для відображення елементів UI
        final Font uiFont = new Font("Times New Roman", Font.PLAIN, 14);

        // Кнопка додання казки у БД
        final JButton addButton = new JButton("Додати казку");
        // Встановити шрифт
        addButton.setFont(uiFont);
        // Обробка натискання на кнопку додання казки
        addButton.addActionListener(e -> addTale());

        // Компоненти для вводу даних казок
        final JList<String> talesList = new JList<>();
        authorField = new TextField("Автор казки");
        nameField = new TextField("Назва казки");
        taleField = new TextArea("Текст казки");
        ageField = new TextField("Вік");
        // Встановити шрифт
        talesList.setFont(uiFont);
        authorField.setFont(uiFont);
        nameField.setFont(uiFont);
        taleField.setFont(uiFont);
        ageField.setFont(uiFont);
        // Додавання комппонентів на форму
        add(talesList, BorderLayout.CENTER);

        // Панель роміщення елементів
        final JPanel formPanel = new JPanel(new GridLayout(5, 1));
        formPanel.add(authorField);
        formPanel.add(nameField);
        formPanel.add(taleField);
        formPanel.add(ageField);
        formPanel.add(addButton);
        add(formPanel, BorderLayout.CENTER);

        // Заголовок головного вікна програми
        setTitle("Казкар для дітей: Додання нової казки");
        // Розмір головного вікна - 800 на 600
        setSize(800, 600);
        // Дія по закриттю вікна - вихід з програми
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }


    // Метод перевірки що дані заповнені
    private boolean areFieldsEmpty() {
        return  authorField.getText().trim().isEmpty()  ||
                nameField.getText().trim().isEmpty()    ||
                taleField.getText().trim().isEmpty()    ||
                ageField.getText().trim().isEmpty();
    }


    // Метод додання казки у БД
    private void addTale() {
        // Перевірка що дані введено
        if (areFieldsEmpty()) {
            // Відображення діалогу помилок якщо ні
            showErrorDialog();
            // Вихід
            return;
        }
        // Отримання значень з полів
        final String author = authorField.getText();
        final String name = nameField.getText();
        final String text = taleField.getText();
        final int age = Integer.parseInt(ageField.getText());
        // Додання казки у БД
        database.addTale(new Tale(0L, author, name, text, age));
        // Закриття форми
        dispose();
    }


    // Метод відображення вікна з помилками
    private void showErrorDialog() {

        // Формування елементів діалогу
        final JDialog errorDialog = new JDialog(this, "Помилка", true);
        final JLabel errorLabel = new JLabel("Будь ласка, дозаповніть усі поля!");
        final JButton closeButton = new JButton("Закрити");

        // Обробник дії натискання на кнопку закриття
        closeButton.addActionListener(e -> errorDialog.dispose());

        // Розміщення елементів у діалогу
        errorDialog.setLayout(new BorderLayout());
        errorDialog.add(errorLabel, BorderLayout.CENTER);
        errorDialog.add(closeButton, BorderLayout.SOUTH);

        // Розмір та параметри відображення діалогу
        errorDialog.setSize(300, 100);
        errorDialog.setLocationRelativeTo(this);
        errorDialog.setVisible(true);

    }


    // Метод що викликається під час закриття вікна
    @Override
    public void dispose() {
        // Підключення до БД існує ?
        if (database != null) {
            // Закрити БД
            database.close();
        }
        // Виклик батьківського методу
        super.dispose();
    }


}
