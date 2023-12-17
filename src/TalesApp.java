import javax.swing.*;
import java.awt.*;


// Головне вікно додатку
public class TalesApp extends JFrame {


    // Графічний інтерфейс головного вікна додатку
    public TalesApp() {

        //
        setLayout(new BorderLayout());

        //
        final Button showButton = new Button("Дивитись казку");
        final Button addButton = new Button("Додати нову казку");
        final Panel buttonPanel = new Panel();
        buttonPanel.add(showButton);
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.NORTH);

        //
        final List talesList = new List();
        add(talesList, BorderLayout.CENTER);

        //
        setTitle("Tales App");
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    // Головна функция додатку
    public static void main(String[] args) {
        // Запуск додатку
        new TalesApp();
    }


}
