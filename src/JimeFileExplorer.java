import java.awt.Dimension;
import java.nio.file.Path;

import javax.swing.JTree;
import util.FileTreeModel;

public class JimeFileExplorer extends JTree {

    private Path root;

    public JimeFileExplorer(Path _root) {
        super();

        root = _root;
        setModel(new FileTreeModel(root.toFile()));

        setPreferredSize(
                new Dimension(500, 600));

    }

}
