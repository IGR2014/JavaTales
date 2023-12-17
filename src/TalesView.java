import org.jetbrains.annotations.NotNull;
import javax.swing.*;
import java.awt.*;


// Форма відображення вікна з казкою
public class TalesView extends JFrame {


    // Конструктор
    public TalesView(@NotNull Tale tale) {

        // Set the layout manager
        setLayout(new BorderLayout());

        // Формування текстового поля
        final JTextArea taleField = getTextArea(tale);
        // Панель для відображення текстового поля
        final JScrollPane textPanel = new JScrollPane(taleField);
        // Додання текстового поля на форму
        add(textPanel, BorderLayout.CENTER);

        // Заголовок головного вікна програми
        setTitle("Казкар для дітей: " + '\"' + tale.name + '\"');
        // Розмір вікна - 800 на 600
        setSize(800, 600);
        // Відображати вікно
        setVisible(true);
        // Дія по закриттю вікна
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    @NotNull
    private static JTextArea getTextArea(@NotNull Tale tale) {
        // Шрифт для відображення елементів UI
        final Font uiFont = new Font("Times New Roman", Font.BOLD, 24);
        // Текст казки із заголовком та автором
        final String taleText = tale.name + " (" + tale.author + ")\n\n" + tale.text
                .replaceAll("\r", "")
                .replaceAll("\n+", "\n");
        // Текстове поле для відображення казки
        final JTextArea taleField = new JTextArea(taleText);
        // Заборона редагувати
        taleField.setEditable(false);
        // Перенос по словах
        taleField.setLineWrap(true);
        // Встановлення шрифта
        taleField.setFont(uiFont);
        // Повернення сформованого текстового поля
        return taleField;
    }


}
