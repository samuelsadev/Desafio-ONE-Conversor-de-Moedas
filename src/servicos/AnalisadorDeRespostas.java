package servicos;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import modelos.RespostaDaAPI;
import modelos.Conversor;

public class AnalisadorDeRespostas {
    public Conversor parseJSONResponse(String jsonResponse) {
        try {
            Gson gson = new Gson();
            RespostaDaAPI apiResponse = gson.fromJson(jsonResponse, RespostaDaAPI.class);

            if (apiResponse.base_code() == null || apiResponse.conversion_rates() == null) {
                throw new IllegalArgumentException("Dados da API inválidos");
            }

            Conversor currency = new Conversor(apiResponse);

            if (currency.getExchangeRate() == null) {
                throw new IllegalArgumentException("Falha ao carregar o cambio");
            }

            return currency;
        } catch (JsonSyntaxException | JsonIOException e) {
            throw new RuntimeException("Erro ao receber dados JSON", e);
        }

    }
}
