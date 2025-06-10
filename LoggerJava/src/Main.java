public class Main {
    public static void main(String[] args) {
        Logger consoleLogger = new Logger(Logger.Mode.CONSOLE);
        consoleLogger.debug("Mensagem de depuração no console.");
        consoleLogger.warning("Mensagem de aviso no console.");
        consoleLogger.error("Mensagem de erro no console.");

        Logger fileLogger = new Logger(Logger.Mode.FILE, "log_sistema.txt");
        fileLogger.debug("Log em arquivo: debug");
        fileLogger.warning("Log em arquivo: warning");
        fileLogger.error("Log em arquivo: erro");
    }
}
