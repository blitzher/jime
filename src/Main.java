import javax.swing.*;

public class Main {

    public final static int WIDTH = 400;
    public final static int HEIGHT = 400;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jime Editor");
        JMenuBar menuBar = new JMenuBar();
        JMenuItem file = new JMenuItem("File");
        JMenuItem edit = new JMenuItem("Edit");
        JMenuItem view = new JMenuItem("View");
        JMenuItem help = new JMenuItem("Help");
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(view);
        menuBar.add(help);

        frame.setSize(400, 400);
        Editor editor = new Editor(WIDTH - 100, HEIGHT);

        frame.add(menuBar);
        frame.add(editor);
        // frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Exit when escape key is pressed

    }
}