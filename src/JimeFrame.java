import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class JimeFrame {
    private JimeMenuBar menuBar;
    private JFrame frame;
    private JimeEditor editor;
    private JimeFileExplorer fileExplorer;

    private final static int WIDTH = 800;
    private final static int HEIGHT = 600;

    public JimeFrame() {
        try {
            // Use the native top bar for Mac OS
            if (System.getProperty("os.name").startsWith("Mac"))
                System.setProperty("apple.laf.useScreenMenuBar", "true");

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Jime");
        BorderLayout layout = new BorderLayout();
        frame.setLayout(layout);

        fileExplorer = new JimeFileExplorer();
        frame.add(fileExplorer, BorderLayout.LINE_START);

        editor = new JimeEditor();
        frame.add(editor, BorderLayout.CENTER);

        menuBar = new JimeMenuBar();
        frame.setMenuBar(menuBar);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        /* Set frame visibility */
        frame.setVisible(true);
    }
}
