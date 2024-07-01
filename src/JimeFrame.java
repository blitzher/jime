import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = gbc.weighty = 1.0;

        fileExplorer = new JimeFileExplorer();
        frame.add(fileExplorer, new GridBagConstraints() {
            {
                gridx = 0;
                gridy = 0;
                fill = GridBagConstraints.BOTH;
                weightx = 0.3;

            }
        });

        editor = new JimeEditor();
        frame.add(editor, new GridBagConstraints() {
            {
                gridx = 1;
                gridy = 0;
                fill = GridBagConstraints.BOTH;
                weightx = weighty = 0.7;
            }
        });

        menuBar = new JimeMenuBar();
        frame.setMenuBar(menuBar);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        /* Set frame visibility */
        frame.setVisible(true);
    }
}
