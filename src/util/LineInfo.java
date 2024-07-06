package util;

public class LineInfo {
    public final String Text;
    public final int Start;
    public final int End;

    public LineInfo(String text, int start, int end) {
        if (!text.endsWith("\n")) {
            text += "\n";
        }
        Text = text;
        Start = start;
        End = end;
    }
}