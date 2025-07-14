import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PruebaAPI {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            HttpClient client = HttpClient.newHttpClient();

            System.out.println("🌍 ¿Qué API deseas usar?");
            System.out.println("1️⃣ ExchangeRate-API (monedas tradicionales)");
            System.out.println("2️⃣ CoinGecko (criptomonedas y algunas fiat)");
            System.out.print("Ingresa 1 o 2: ");
            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                // ---------------- API ExchangeRate ----------------
                String url = "https://v6.exchangerate-api.com/v6/b5d62ef630da00408bb895e8/latest/USD";
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();

                System.out.println("\n✅ Respuesta de ExchangeRate-API:");
                System.out.println(json);

                // Aquí puedes continuar con parseo y conversiones

            } else if (opcion.equals("2")) {
                // ---------------- API CoinGecko ----------------
                String urlGecko = "https://api.coingecko.com/api/v3/exchange_rates";
                HttpRequest requestGecko = HttpRequest.newBuilder()
                        .uri(URI.create(urlGecko))
                        .build();

                HttpResponse<String> responseGecko = client.send(requestGecko, HttpResponse.BodyHandlers.ofString());
                String jsonGecko = responseGecko.body();

                System.out.println("\n✅ Respuesta de CoinGecko:");
                System.out.println(jsonGecko);

                // Aquí puedes continuar con parseo y mostrar criptos

            } else {
                System.out.println("⚠️ Opción no válida. Ejecuta de nuevo y elige 1 o 2.");
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("❌ Ocurrió un error: " + e.getMessage());
        }
    }
}
