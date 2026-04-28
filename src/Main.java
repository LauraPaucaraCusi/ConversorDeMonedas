import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            ConsultaAPI consultaAPI = new ConsultaAPI();
            GeneradorDeArchivo generadorDeArchivo = new GeneradorDeArchivo();

            System.out.println("💱 Conversor de Monedas Profesional");

            while (true) {
                System.out.print("\n🔹 Moneda origen (o 'SALIR'): ");
                String monedaOrigen = scanner.nextLine().trim().toUpperCase();

                if ("SALIR".equals(monedaOrigen)) {
                    System.out.println("👋 ¡Gracias por usar el conversor!");
                    break;
                }

                System.out.print("🔹 Moneda destino: ");
                String monedaDestino = scanner.nextLine().trim().toUpperCase();

                ExchangeResponse exchangeResponse = consultaAPI.consultarPorBase(monedaOrigen);
                Map<String, Double> tasas = exchangeResponse.getConversion_rates();

                Double tasa = tasas.get(monedaDestino);
                if (tasa == null) {
                    System.out.println("⚠️ La moneda destino no existe o no está disponible: " + monedaDestino);
                    continue;
                }

                System.out.print("💲 Monto a convertir en " + monedaOrigen + ": ");
                String entradaMonto = scanner.nextLine().trim();
                double montoOrigen;

                try {
                    montoOrigen = Double.parseDouble(entradaMonto);
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Monto inválido. Ingresa un número válido.");
                    continue;
                }

                double montoConvertido = montoOrigen * tasa;

                System.out.println("==============================");
                System.out.println("Base consultada: " + exchangeResponse.getBase_code());
                System.out.println("Tasa " + monedaOrigen + " -> " + monedaDestino + ": " + tasa);
                System.out.println("Resultado: " + montoConvertido + " " + monedaDestino);
                System.out.println("==============================");

                generadorDeArchivo.guardarConversion(monedaOrigen, monedaDestino, montoOrigen, tasa, montoConvertido);
                System.out.println("✅ Conversión guardada en tasas.txt");
            }

        } catch (IOException e) {
            System.out.println("❌ Error de configuración/entrada: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("❌ La consulta a la API fue interrumpida.");
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
