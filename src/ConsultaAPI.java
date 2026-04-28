import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ConsultaAPI {
    private static final String CONFIG_FILE = "config.properties";
    private static final String API_KEY_PROPERTY = "API_KEY";
    private static final String API_URL_TEMPLATE = "https://v6.exchangerate-api.com/v6/%s/latest/%s";

    private final HttpClient client;
    private final Gson gson;
    private final String apiKey;

    public ConsultaAPI() throws IOException {
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
        this.apiKey = cargarApiKey();
    }

    public ExchangeResponse consultarPorBase(String monedaBase) throws IOException, InterruptedException {
        String url = String.format(API_URL_TEMPLATE, apiKey, monedaBase.toUpperCase());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Error HTTP al consultar la API: " + response.statusCode());
        }

        ExchangeResponse exchangeResponse = gson.fromJson(response.body(), ExchangeResponse.class);
        if (exchangeResponse == null || exchangeResponse.getConversion_rates() == null) {
            throw new IOException("Respuesta inválida de la API.");
        }

        return exchangeResponse;
    }

    private String cargarApiKey() throws IOException {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        }

        String key = properties.getProperty(API_KEY_PROPERTY);
        if (key == null || key.isBlank() || "TU_API_KEY_AQUI".equalsIgnoreCase(key.trim())) {
            throw new IOException("API_KEY no configurada en config.properties");
        }

        return key.trim();
    }
}
