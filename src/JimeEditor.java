import javax.swing.*;
import javax.swing.plaf.metal.*;

public class JimeEditor extends JFrame {

    JTextArea textArea;
    JimeMenuBar menuBar = new JimeMenuBar();

    public JimeEditor(int width, int height) {
        super();

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setText(assets.lipsum.Lipsum);

        try {
            // Set metal look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
        }

        menuBar = new JimeMenuBar();
        setMenuBar(menuBar);

        setSize(width, height);
        add(textArea);
    }
}
