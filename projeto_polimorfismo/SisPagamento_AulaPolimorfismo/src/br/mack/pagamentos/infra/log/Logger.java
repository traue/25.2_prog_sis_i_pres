package br.mack.pagamentos.infra.log;

public class Logger {
    public void log(String msg) {
        System.out.println("[LOG] " + msg);
    }
    public void log(String fmt, Object... args) {
        System.out.println("[LOG] " + String.format(fmt, args));
    }
    public void log(Exception e) {
        System.out.println("[ERR] " + e.getClass().getSimpleName() + ": " + e.getMessage());
    }
}