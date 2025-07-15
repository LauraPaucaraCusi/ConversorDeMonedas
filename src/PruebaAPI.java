import java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PruebaAPI {
    public static void main(String[] args) {
        try {
            String url = "https://v6.exchangerate-api.com/v6/b5d62ef630da00408bb895e8/latest/USD";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            Gson gson = new Gson();
            ExchangeResponse exchange = gson.fromJson(json, ExchangeResponse.class);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("🔹 Ingresa el código de la moneda que deseas consultar (o escribe 'salir' para terminar): ");
                String codigoMoneda = scanner.nextLine().toUpperCase();

                if (codigoMoneda.equals("SALIR")) {
                    System.out.println("👋 ¡Gracias por usar el conversor! Hasta luego.");
                    break;
                }

                Double tasa = exchange.getConversion_rates().get(codigoMoneda);

                if (tasa != null) {
                    System.out.print("💲 Ingresa la cantidad de USD que deseas convertir a " + codigoMoneda + ": ");
                    double cantidadUSD = scanner.nextDouble();
                    scanner.nextLine(); // limpiar salto de línea

                    double resultado = cantidadUSD * tasa;
                    LocalDateTime ahora = LocalDateTime.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    System.out.println("==============================");
                    System.out.println("🕒 Fecha y hora: " + ahora.format(formato));
                    System.out.println("Base: " + exchange.getBase_code());
                    System.out.println("Moneda elegida: " + codigoMoneda);
                    System.out.println("Tasa de cambio: " + tasa);
                    System.out.println("Cantidad de USD: " + cantidadUSD);
                    System.out.println("Total convertido: " + resultado + " " + codigoMoneda);
                    System.out.println("----- Otras tasas importantes -----");
                    System.out.println("CLP: " + exchange.getConversion_rates().get("CLP"));
                    System.out.println("EUR: " + exchange.getConversion_rates().get("EUR"));
                    System.out.println("PEN: " + exchange.getConversion_rates().get("PEN"));
                    System.out.println("JPY: " + exchange.getConversion_rates().get("JPY"));
                    System.out.println("MXN: " + exchange.getConversion_rates().get("MXN"));
                    System.out.println("==============================");

                    // Guardar en tasas.txt
                    FileWriter writer = new FileWriter("tasas.txt", true);
                    writer.write("\n==============================\n");
                    writer.write("🕒 Fecha y hora: " + ahora.format(formato) + "\n");
                    writer.write("Base: " + exchange.getBase_code() + "\n");
                    writer.write("Moneda elegida: " + codigoMoneda + "\n");
                    writer.write("Tasa de cambio: " + tasa + "\n");
                    writer.write("Cantidad de USD: " + cantidadUSD + "\n");
                    writer.write("Total convertido: " + resultado + " " + codigoMoneda + "\n");
                    writer.write("----- Otras tasas importantes -----\n");
                    writer.write("CLP: " + exchange.getConversion_rates().get("CLP") + "\n");
                    writer.write("EUR: " + exchange.getConversion_rates().get("EUR") + "\n");
                    writer.write("PEN: " + exchange.getConversion_rates().get("PEN") + "\n");
                    writer.write("JPY: " + exchange.getConversion_rates().get("JPY") + "\n");
                    writer.write("MXN: " + exchange.getConversion_rates().get("MXN") + "\n");
                    writer.write("==============================\n");
                    writer.close();

                    System.out.println("✅ Conversión guardada en tasas.txt");
                } else {
                    System.out.println("⚠️ Moneda no encontrada. Intenta de nuevo.");
                }
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
