import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.nio.file.Path;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import util.*;

public class JimeEditor {

    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JPanel panel;
    private Path currentFilePath;
    private TextLineNumber tln;

    public JimeEditor() {
        super();

        panel = new JPanel();

        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setText(assets.lipsum.Lipsum);
        // Set monospace font

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = gbc.weighty = 1.0;

        // Create the scroll pane view of the text area
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();

        // Use the utility class to render line numbers
        tln = new TextLineNumber(textArea);
        scrollPane.setRowHeaderView(tln);

        panel.add(scrollPane, gbc);
        panel.add(verticalScrollBar, new GridBagConstraints() {
            {
                gridx = 1;
                gridy = 0;
                fill = GridBagConstraints.VERTICAL;
                weighty = 1.0;
            }
        });
    }

    public void LoadFile(Path path) {
        textArea.setText(util.FileUtils.ReadFile(path));
        currentFilePath = path;
        // TODO: Cache caret positions of previously opened files
        textArea.setCaretPosition(0);
    }

    public java.awt.Component getComponent() {
        return panel;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * Get the line number of the caret in the text area
     * 
     * @return The line number of the caret, or 0 if an exception occurs
     */
    public int getCaretLineNumber() {
        try {
            return textArea.getLineOfOffset(textArea.getCaretPosition());
        } catch (BadLocationException e) {
            // TODO: Log exceptions to a log file
            e.printStackTrace();
            return 0;
        }
    }

    public LineInfo getTextAtLine(int line) {
        try {
            int lineStart = textArea.getLineStartOffset(line);
            int lineEnd = textArea.getLineEndOffset(line);
            String text = textArea.getText(lineStart, lineEnd - lineStart);
            return new LineInfo(text, lineStart, lineEnd);
        } catch (BadLocationException e) {
            return null;
        }

    }

    public void moveLine(int line, int delta) {
        // Out of bounds check
        if (line + delta < 0 || line + delta >= textArea.getLineCount())
            return;

        LineInfo curLine = getTextAtLine(line);
        LineInfo targetLine = getTextAtLine(line + delta);

        if (delta < 0) {
            textArea.replaceRange(targetLine.Text, curLine.Start, curLine.End);
            textArea.replaceRange(curLine.Text, targetLine.Start, targetLine.End);
        } else {
            textArea.replaceRange(curLine.Text, targetLine.Start, targetLine.End);
            textArea.replaceRange(targetLine.Text, curLine.Start, curLine.End);
        }

        textArea.setCaretPosition(getTextAtLine(line + delta).Start);

    }

    public Path getCurrentFilePath() {
        return currentFilePath;
    }

    public void setCurrentFilePath(Path path) {
        currentFilePath = path;
    }

    public String getContent() {
        return textArea.getText();
    }

    public void setContent(String content) {
        textArea.setText(content);
        scrollPane.getVerticalScrollBar().setValue(0);
        tln.documentChanged();

    }
}
