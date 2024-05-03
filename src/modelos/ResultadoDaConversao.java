package modelos;

public class ResultadoDaConversao {
    private double valorParaConverter;
    private String codigoMoedaPrimaria;
    private String codigoMoedaDestino;
    private double resultado;

    public ResultadoDaConversao(double valueToConvert, String primaryCurrencyCode, String targetCurrencyCode, double result) {
        this.valorParaConverter = valueToConvert;
        this.codigoMoedaPrimaria = primaryCurrencyCode;
        this.codigoMoedaDestino = targetCurrencyCode;
        this.resultado = result;
    }

    public double getValorParaConverter() {
        return valorParaConverter;
    }

    public String getCodigoMoedaPrimaria() {
        return codigoMoedaPrimaria;
    }

    public String getCodigoMoedaDestino() {
        return codigoMoedaDestino;
    }

    public double getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return "Valor a converter: " + valorParaConverter +
                ", Moeda primária: " + codigoMoedaPrimaria +
                ", Moeda a ser convertida: " + codigoMoedaDestino +
                ", Resultado: " + resultado;
    }
}
