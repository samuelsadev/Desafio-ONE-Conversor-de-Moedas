package modelos;

import java.util.Map;

public class Conversor {
    private final String code;
    private final Map<String, Double> exchangeRate;


    public Conversor(RespostaDaAPI apiResponse) {
        this.code = apiResponse.base_code();
        this.exchangeRate = apiResponse.conversion_rates();
    }

    public String getCode() {
        return code;
    }

    public Map<String, Double> getExchangeRate() {
        return exchangeRate;
    }

    @Override
    public String toString() {
        return this.code + ": " + this.exchangeRate ;
    }
}
