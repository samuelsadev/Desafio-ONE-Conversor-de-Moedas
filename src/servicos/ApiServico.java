package servicos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiServico {
    private static final String API_KEY = System.getenv("API_KEY_EXCHANGE_RATE");

    public String fetchCurrencyData(String currency) {
        URI URI_API = URI.create("https://v6.exchangerate-api.com/v6/" + "495010f85a31a3305ff4db96"+ "/latest/" + currency);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI_API)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (IOException | InterruptedException e ) {
            throw new RuntimeException("Não foi possível localizar a moeda, tente novamente!");
        }

    }

}
