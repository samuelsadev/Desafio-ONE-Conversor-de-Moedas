package servicos;

import modelos.ResultadoDaConversao;

import java.util.ArrayList;
import java.util.List;

public class HistoricoDeConversao {
    private List<ResultadoDaConversao> conversionHistory;

    public HistoricoDeConversao() {
        conversionHistory = new ArrayList<>();
    }

    public void addConversion(ResultadoDaConversao conversion) {
        conversionHistory.add(conversion);
    }

    public List<ResultadoDaConversao> getConversionHistory() {
        return conversionHistory;
    }

    public void displayConversionHistory() {
        if(conversionHistory.size() == 0) {
            System.out.println("------------------------------------");
            System.out.println("Não há conversões para consultar, por favor, faça uma conversão para consultar o histórico!");
            System.out.println("------------------------------------");
            return;
        }
        System.out.println("Histórico de Conversões:");
        System.out.println("-------------------------------------");
        for (int i = 0; i < conversionHistory.size(); i++) {
            ResultadoDaConversao conversion = conversionHistory.get(i);
            System.out.println("Conversão " + (i + 1) + ":");
            System.out.println("Valor a converter: " + conversion.getValorParaConverter());
            System.out.println("Moeda de origem: " + conversion.getCodigoMoedaPrimaria());
            System.out.println("Moeda de destino: " + conversion.getCodigoMoedaDestino());
            System.out.println("Resultado: " + conversion.getResultado());
            // implementar que ConversionResult tenha um método getConversionDate()
            // System.out.println("Data da conversão: " + conversion.getConversionDate());
            System.out.println("-------------------------------------");
        }
    }
}
