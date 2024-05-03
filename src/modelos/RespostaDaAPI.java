package modelos;

import java.util.Map;

public record RespostaDaAPI(String base_code, Map<String, Double> conversion_rates) {}
