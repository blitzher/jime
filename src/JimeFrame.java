import java.awt.BorderLayout;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class JimeFrame {
    private JimeMenuBar menuBar;
    private JFrame frame;
    private JimeEditor editor;
    private JimeFileExplorer fileExplorer;

    private final static int WIDTH = 800;
    private final static int HEIGHT = 600;

    private Path rootPath = Path.of("./");

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

        this.setRootPath(rootPath);

        editor = new JimeEditor();
        frame.add(editor.getComponent(), BorderLayout.CENTER);

        menuBar = new JimeMenuBar();
        frame.setMenuBar(menuBar);
        menuBar.bind(this);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        /* Set frame visibility */
        frame.setVisible(true);
    }

    public void setRootPath(Path path) {
        rootPath = path;
        if (fileExplorer != null) {
            frame.remove(fileExplorer.getComponent());
            // Garbage collect the old file explorer? Or is that necessary?
        }
        fileExplorer = new JimeFileExplorer(rootPath);
        frame.add(fileExplorer.getComponent(), BorderLayout.LINE_START);
        fileExplorer.setOnFileClickedConsumer((file) -> {
            editor.LoadFile(file.toPath());
        });
    }

    public Path getRootPath() {
        return rootPath;
    }

    public JimeEditor getEditor() {
        return editor;
    }

    public JFrame getFrame() {
        return frame;
    }
}
