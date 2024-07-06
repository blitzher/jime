package util;

public abstract class FileUtilities {
    public static String ReadFile(java.nio.file.Path path) {
        try {
            byte[] encoded = java.nio.file.Files.readAllBytes(path);
            return new String(encoded);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void WriteFile(java.nio.file.Path path, String content) {
        try {
            java.nio.file.Files.write(path, content.getBytes());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}
