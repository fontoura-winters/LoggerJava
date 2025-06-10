import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public enum Mode { CONSOLE, FILE }

    public enum Level {
        DEBUG("\u001B[32m"),    // Verde
        WARNING("\u001B[33m"),  // Amarelo
        ERROR("\u001B[31m");    // Vermelho

        private final String colorCode;

        Level(String colorCode) {
            this.colorCode = colorCode;
        }

        public String getColorCode() {
            return colorCode;
        }
    }

    private Mode mode;
    private String filename;

    public Logger(Mode mode) {
        this(mode, "log.txt");
    }

    public Logger(Mode mode, String filename) {
        this.mode = mode;
        this.filename = filename;
    }

    public void log(Level level, String message) {
        String timestamp = LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String formattedMessage = String.format("[%s] [%s] %s", timestamp, level.name(), message);

        switch (mode) {
            case CONSOLE:
                System.out.println(level.getColorCode() + formattedMessage + "\u001B[0m");
                break;

            case FILE:
                try (FileWriter writer = new FileWriter(filename, true)) {
                    writer.write(formattedMessage + "\n");
                } catch (IOException e) {
                    System.err.println("Erro ao escrever no arquivo de log: " + e.getMessage());
                }
                break;

            default:
                throw new IllegalArgumentException("Modo de log inválido.");
        }
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }
}
