import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


// Головне вікно додатку
public class TalesApp extends JFrame {


    // Об'єкт доступу до БД казок
    private final TalesDatabase database;


    // Список казок
    private final List talesList;


    // Графічний інтерфейс головного вікна додатку
    public TalesApp() {

        // Підключення до БД казок
        database = new TalesDatabase();

        // Розміщення елементів у вікні програми
        setLayout(new BorderLayout());

        // Шрифт для відображення елементів UI
        final Font uiFont = new Font("Times New Roman", Font.BOLD, 20);

        // Створення UI-панелі з кнопками
        final JPanel buttonPanel = getPanel(uiFont);
        // Відобразиит панель з кнопками у вікні програми (згори)
        add(buttonPanel, BorderLayout.NORTH);

        // Список казок
        talesList = new List();
        // Встановити шрифт
        talesList.setFont(uiFont);
        // Відобразити список казок у вікні програми (по центру)
        add(talesList, BorderLayout.CENTER);

        // Заголовок головного вікна програми
        setTitle("Казкар для дітей");
        // Розмір головного вікна - 800 на 600
        setSize(800, 600);
        // Відображати головне вікно
        setVisible(true);
        // Дія по закриттю вікна - вихід з програми
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Обробник подій зміни фокусу вікна
        addWindowFocusListener(new WindowFocusListener() {

            // Метод що буде викликано під час отримання вікном фокусу
            @Override
            public void windowGainedFocus(WindowEvent e) {
                // Оновлення списку казок
                updateTalesList();
            }

            // Метод що буде викликано під час втрати вікном фокусу
            @Override
            public void windowLostFocus(WindowEvent e) {
                // Реалізація не потрібна
            }

        });

        // Обробник події закриття вікна
        addWindowListener(new WindowAdapter() {

            // Метод що буде викликано під час закриття вікна
            @Override
            public void windowClosing(WindowEvent e) {
                // Закриття вікна
                dispose();
            }

        });

    }


    // Метод створення UI-панелі з кнопками
    @NotNull
    private JPanel getPanel(Font uiFont) {

        // Кнопки показу казки та додавання нової
        final JButton showButton = new JButton("Дивитись казку");
        final JButton addButton = new JButton("Додати нову казку");
        // Встановити шрифт
        showButton.setFont(uiFont);
        addButton.setFont(uiFont);

        // Панель для відображення кнопок
        final JPanel buttonPanel = new JPanel();
        // Доати кнопки на панель
        buttonPanel.add(showButton);
        buttonPanel.add(addButton);

        // Обробник натискання на кнопку відображення казки
        showButton.addActionListener(e -> showWindowTalesView());
        // Обробник натискання на кнопку додавання казки
        addButton.addActionListener(e -> showWindowTalesNew());

        // Повернення готової панелі
        return buttonPanel;

    }


    // Метод відображення форми відображення казки
    private void showWindowTalesView() {
        // Список усіх казок
        java.util.List<Tale> taleList = database.getAllTales();
        // Індекс обраної казки
        int selectedIndex = talesList.getSelectedIndex();
        // Якщо казка була дійсно обрана
        if (selectedIndex >= 0 && selectedIndex < taleList.size()) {
            // Форма відображення казки
            TalesView taleForm = new TalesView(taleList.get(selectedIndex));
            // Відображення форми
            taleForm.setVisible(true);
        }
    }

    // Метод відображення форми додавання нової казки
    private void showWindowTalesNew() {
        // Форма додавання нової казки
        TalesNew talesForm = new TalesNew();
        // Відображення форми
        talesForm.setVisible(true);
    }


    // Оновлення списку казок
    private void updateTalesList() {
        // Видалення попередніх даних зі списку
        talesList.removeAll();
        // Для кожної казки зі списку казок з БД
        for (Tale tale : database.getAllTales()) {
            // Формування заголовку казки
            String displayText = String.format(
                    "%s \"%s\" (Вік: %d+)",
                    tale.author,
                    tale.name,
                    tale.age
            );
            // Відображення чергової казки у списку
            talesList.add(displayText);
        }
    }


    // Метод що викликається під час закриття головного вікна програми
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


    // Головна функция додатку
    public static void main(String[] args) {
        // Запуск додатку
        new TalesApp();
    }


}
