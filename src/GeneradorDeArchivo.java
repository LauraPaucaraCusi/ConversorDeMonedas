import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneradorDeArchivo {
    private static final String ARCHIVO_SALIDA = "tasas.txt";
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void guardarConversion(String monedaOrigen, String monedaDestino, double montoOrigen, double tasa, double montoConvertido) throws IOException {
        LocalDateTime ahora = LocalDateTime.now();

        try (FileWriter writer = new FileWriter(ARCHIVO_SALIDA, true)) {
            writer.write("\n==============================\n");
            writer.write("🕒 Fecha y hora: " + ahora.format(FORMATO) + "\n");
            writer.write("Moneda origen: " + monedaOrigen + "\n");
            writer.write("Moneda destino: " + monedaDestino + "\n");
            writer.write("Monto origen: " + montoOrigen + "\n");
            writer.write("Tasa usada: " + tasa + "\n");
            writer.write("Total convertido: " + montoConvertido + " " + monedaDestino + "\n");
            writer.write("==============================\n");
        }
    }
}
