import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.nio.file.Path;
import java.util.function.Consumer;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import util.FileTreeModel;

public class JimeFileExplorer {

    private Path root;
    private JTree tree;

    private Consumer<File> onClickConsumer;

    public void setOnFileClickedConsumer(Consumer<File> delegate) {
        onClickConsumer = delegate;
    }

    public JimeFileExplorer(Path _root) {
        super();

        tree = new JTree();

        root = _root;
        tree.setModel(new FileTreeModel(root.toFile()));
        tree.setCellRenderer(new TreeCellRenderer() {
            @Override
            public java.awt.Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                    boolean expanded, boolean leaf, int row, boolean hasFocus) {

                File f = (File) value;
                JLabel label = new JLabel(f.getName());
                label.setPreferredSize(new Dimension() {
                    {
                        width = 500;
                        height = 20;
                    }
                });
                label.setIcon(f.isDirectory() ? null : null);

                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        System.out.println("Clicked on " + f.getName());
                        // if (!f.isDirectory())
                        // return;
                    }
                });

                return label;
            }

        });

        tree.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                File component = getFileAt(e.getPoint());
                if (component == null) {
                    tree.setCursor(Cursor.getDefaultCursor());
                } else {
                    tree.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }
        });
        tree.addMouseListener(new MouseListener() {
            private void click(MouseEvent e) {
                // Don't propogate the event to the tree after it has been handled
                e.consume();

                File file = getFileAt(e.getPoint());
                if (file == null)
                    return;
                if (onClickConsumer != null && file.isFile())
                    onClickConsumer.accept(file);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                click(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                click(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

        tree.setPreferredSize(
                new Dimension(250, 600));

    }

    private File getFileAt(int x, int y) {
        TreePath path = tree.getPathForLocation(x, y);
        if (path == null)
            return null;
        return (File) path.getLastPathComponent();
    }

    private File getFileAt(Point p) {
        return getFileAt(p.x, p.y);
    }

    public java.awt.Component getComponent() {
        return tree;
    }

}
