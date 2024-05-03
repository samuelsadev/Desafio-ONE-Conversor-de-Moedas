package modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static int nextId = 1;
    private int id;
    private LocalDateTime date;
    private double valueToConvert;
    private String primaryCurrencyCode;
    private String targetCurrencyCode;
    private double result;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Log(double valueToConvert, String primaryCurrencyCode, String targetCurrencyCode, double result) {
        this.id = nextId++;
        this.date = LocalDateTime.now();
        this.valueToConvert = valueToConvert;
        this.primaryCurrencyCode = primaryCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }

    public String getPrimaryCurrencyCode() {
        return primaryCurrencyCode;
    }

    public double getValueToConvert() {
        return valueToConvert;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void printLog() {
        System.out.println("------------------------------------");
        System.out.println("Log ID: " + this.getId());
        System.out.println("Data: " + this.getDate().format(formatter));
        System.out.println("Time: " + this.getDate().getHour() + ":" + this.getDate().getMinute() + ":" + this.getDate().getSecond());
        System.out.println("Informações da conversão:");
        System.out.println("  Input value: " + this.getValueToConvert() + " " + this.getPrimaryCurrencyCode());
        System.out.println("  Output value: " + this.getResult() + " " + this.getTargetCurrencyCode());
        System.out.println("------------------------------------");
    }
}
