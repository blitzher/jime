import java.awt.Font;

import javax.swing.*;

public class Editor extends JPanel {
    public Editor(int width, int height) {
        super();

        JEditorPane textPane = new JEditorPane();
        JScrollPane scrollPane = new JScrollPane(textPane);

        textPane.setText("Lorem ipsum dolor sit amet");
        add(scrollPane);

        add(textPane);

    }

}
