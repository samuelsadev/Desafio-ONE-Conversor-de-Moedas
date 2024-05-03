package conversorDeMoedas;

import modelos.ResultadoDaConversao;
import modelos.Conversor;
import modelos.Log;
import servicos.HistoricoDeConversao;
import servicos.ApiServico;
import servicos.AnalisadorDeRespostas;
import servicos.ServicoDeLog;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConversorDeMoedas {
    private static final HistoricoDeConversao historyManager = new HistoricoDeConversao();
    private static final ServicoDeLog logService = new ServicoDeLog();

    public static void run(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {

            getMenu();

            int option;
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("------------------------------------");
                System.out.println("Entrada inválida. Por favor, insira um número dentre as opções.");
                System.out.println("------------------------------------");
                scanner.nextLine();
                continue;
            }

            switch (option) {
                case 1:
                    convert(scanner, "USD", "BRL");
                    break;
                case 2:
                    convert(scanner, "EUR", "BRL");
                    break;
                case 3:
                    convert(scanner, "BRL", "USD");
                    break;
                case 4:
                    convert(scanner, "BRL", "EUR");
                    break;
                case 5:
                    convert(scanner, "EUR", "USD");
                    break;
                case 6:
                    convert(scanner, "JPY", "BRL");
                    break;
                case 7:
                    historyManager.displayConversionHistory();
                    break;
                case 8:
                    logService.displayAllLogs();
                    break;
                case 9:
                    System.out.println("Tarefa finalizada!");
                    continueLoop = false;
                    break;
                default:
                    System.out.println("------------------------------------");
                    System.out.println("Opção inválida. Tente novamente.");
                    System.out.println("------------------------------------");
                    break;
            }
        }
    }

    private static Conversor getCurrencyData(String currencyCode) {
        ApiServico apiService = new ApiServico();
        AnalisadorDeRespostas parser = new AnalisadorDeRespostas();
        String jsonResponse = apiService.fetchCurrencyData(currencyCode);
        Conversor currency = parser.parseJSONResponse(jsonResponse);
        return currency;
    }

    private static void convert(Scanner scanner, String currencyCode, String currencyConvert) {
        try {
            Conversor currencyToConvert = getCurrencyData(currencyCode);
            Double exchangeRateConvert = currencyToConvert.getExchangeRate().get(currencyConvert);

            if (exchangeRateConvert == null) {
                System.out.println("Taxa de câmbio para " + currencyConvert + " indisponível.");
                return;
            }

            double valueToConvert = handleConversionInput(scanner);

            double result = valueToConvert * exchangeRateConvert;

            displayConversionResult(valueToConvert, currencyToConvert.getCode(), currencyConvert, result);

            saveInHistoryManager(valueToConvert, currencyToConvert.getCode(), currencyConvert, result);
            saveInLogHistoryManager(valueToConvert, currencyToConvert.getCode(), currencyConvert, result);
        } catch (NumberFormatException e) {
            System.out.println("------------------------------------");
            System.out.println("Entrada inválida. Por favor, insira um número válido.");
            System.out.println("------------------------------------");
        } catch (Exception e) {
            System.out.println("------------------------------------");
            System.out.println("Ocorreu um erro ao realizar a conversão. Por favor, tente novamente.");
            System.out.println("------------------------------------");
        }
    }


    private static void saveInHistoryManager(double valueToConvert,
                                             String currencyToConvert,
                                             String currencyConvert,
                                             double result) {
        ResultadoDaConversao conversion1 = new ResultadoDaConversao(valueToConvert, currencyToConvert, currencyConvert, result);
        historyManager.addConversion(conversion1);
    }

    private static void saveInLogHistoryManager( double valueToConvert,
                                                 String primaryCurrencyCode,
                                                 String targetCurrencyCode,
                                                 double result) {
        Log log = new Log(valueToConvert, primaryCurrencyCode, targetCurrencyCode, result);
        logService.addLog(log);
    }

    private static void getMenu() {
        System.out.println("*************************************");
        System.out.println("Conversor de Moedas");
        System.out.println("1. Converter de Dólar para Real");
        System.out.println("2. Converter de Euro para Real");
        System.out.println("3. Converter de Real para Dólar");
        System.out.println("4. Converter de Real para Euro");
        System.out.println("5. Converter de Euro para Dólar");
        System.out.println("6. Converter de Lene para Real");
        System.out.println("7. Converter de Libra Esterlina para Real");
        System.out.println("8. Converter de Franco Suíço para Real");
        System.out.println("9. Consultar histórico de conversões");
        System.out.println("10. Consultar logs de conversões");
        System.out.println("11. Sair");
        System.out.println("Escolha uma opção: ");
        System.out.println("*************************************");
        System.out.println(" ");
    }

    private static double handleConversionInput(Scanner scanner) {
        System.out.println("Digite o valor que deseja converter: ");
        double valueToConvert;

        try {
            valueToConvert = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("------------------------------------");
            System.out.println("Entrada inválida. Por favor, insira um número válido.");
            System.out.println("------------------------------------");
            scanner.nextLine();
            return handleConversionInput(scanner);
        }

        return valueToConvert;
    }

    private static void displayConversionResult(double valueToConvert, String currencyToConvert, String currencyConvert, double result) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String resultFormated = decimalFormat.format(result);

        System.out.println("-------------------------------------");
        System.out.println("Valor convertido:");
        System.out.println("Valor de $ " + valueToConvert + " (" + currencyToConvert + ")"
                + " corresponde ao valor final de: $ " + resultFormated + " (" + currencyConvert + ")");
        System.out.println("-------------------------------------");
    }
}
