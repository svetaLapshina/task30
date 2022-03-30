import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;

class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Target directory not specified!");
            return;
        }
        String targetDir = args[0];

        String sep = FileSystems.getDefault().getSeparator();
        String [] paths = {
                new StringBuilder(targetDir).append(sep).append("src").append(sep).append("main").toString(),
                new StringBuilder(targetDir).append(sep).append("src").append(sep).append("test").toString(),
                new StringBuilder(targetDir).append(sep).append("res").append(sep).append("drawables").toString(),
                new StringBuilder(targetDir).append(sep).append("res").append(sep).append("vectors").toString(),
                new StringBuilder(targetDir).append(sep).append("res").append(sep).append("icons").toString(),
                new StringBuilder(targetDir).append(sep).append("savegames").toString(),
                new StringBuilder(targetDir).append(sep).append("temp").toString()
        };

        String [] files = {
                new StringBuilder(targetDir).append(sep).append("src").append(sep).append("main").append(sep).append("Main.java").toString(),
                new StringBuilder(targetDir).append(sep).append("src").append(sep).append("main").append(sep).append("Utils.java").toString()
        };

        StringBuilder log = new StringBuilder();
        for (String p: paths) {
            if (new File(p).mkdirs()) {
                log.append("Folder ").append(p).append(" created\n");
            } else {
                if (new File(p).exists())
                    log.append("Folder ").append(p).append(" already exists\n");
                else
                    log.append("Failed to create folder ").append(p).append('\n');
            }
        }

        for (String f: files) {
            try {
                if (new File(f).createNewFile()) {
                    log.append("File ").append(f).append(" created\n");
                } else {
                    if (new File(f).exists())
                        log.append("File ").append(f).append(" already exists\n");
                    else
                        log.append("Failed to create file ").append(f).append('\n');
                }
            } catch (IOException e) {
                log.append("Failed to create file ").append(f).append(": ").append(e.getMessage());
            }
        }

        String logPath = new StringBuilder(paths[6]).append(sep).append("temp.txt").toString();
        try {
            FileWriter fw = new FileWriter(logPath);
            fw.write(log.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to save log to " + logPath);
        }
    }
}