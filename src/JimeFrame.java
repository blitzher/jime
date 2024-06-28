import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class JimeFrame {
    private JimeMenuBar menuBar;
    private JFrame frame;
    private JimeEditor editor;

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

        frame = new JFrame();

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = gbc.weighty = 1.0;

        editor = new JimeEditor();
        frame.add(editor, gbc);

        menuBar = new JimeMenuBar();
        frame.setMenuBar(menuBar);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(WIDTH, HEIGHT);
    }
}
