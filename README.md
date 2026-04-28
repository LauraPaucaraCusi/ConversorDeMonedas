# 💱 Conversor de Monedas en Java

Aplicación de consola en Java para convertir montos entre **cualquier moneda de origen y cualquier moneda de destino** usando tasas actualizadas de **ExchangeRate-API**.

---

## ✨ Funcionalidades

✅ Conversión universal (ejemplo: USD → CLP, EUR → MXN, PEN → JPY, etc.).  
✅ Consulta en tiempo real desde ExchangeRate-API con URL dinámica por moneda base.  
✅ Registro de cada conversión en `tasas.txt` con fecha, monto, tasa y resultado.  
✅ Código organizado en clases separadas para un diseño más limpio y mantenible.

---

## 🧱 Estructura del proyecto

- `Main.java`: punto de entrada e interacción por consola.
- `ConsultaAPI.java`: conexión HTTP, lectura de `API_KEY` y consumo de la API.
- `GeneradorDeArchivo.java`: persistencia del historial de conversiones.
- `ExchangeResponse.java`: mapeo del JSON recibido.

---

## 📦 Requisitos

- Java 11 o superior.
- Biblioteca **Gson** (`lib/gson-2.10.1.jar`).
- Conexión a Internet.
- API key válida de ExchangeRate-API.

---

## ⚙️ Configuración

1. Crea/edita el archivo `config.properties` en la raíz del proyecto.
2. Agrega tu clave:

```properties
API_KEY=tu_api_key_real
```

> La aplicación leerá automáticamente esta clave al iniciar.

---

## ▶️ Ejecución

1. Compila:

```bash
javac -cp "lib/gson-2.10.1.jar" src/*.java
```

2. Ejecuta:

```bash
java -cp "src:lib/gson-2.10.1.jar" Main
```

3. En consola, ingresa:
   - Moneda de origen (por ejemplo `USD`).
   - Moneda de destino (por ejemplo `EUR`).
   - Monto a convertir.

Escribe `SALIR` para terminar.

---

## 🔐 Seguridad

Este proyecto **no hardcodea** la clave de API dentro del código fuente.

- Se usa `config.properties` para separar secretos de la lógica del programa.
- `.gitignore` incluye `config.properties`, evitando subir credenciales por error a GitHub.

Este enfoque reduce riesgos de exposición de claves y facilita cambiar credenciales entre entornos.

---

## 📌 Nota sobre la API

La URL se construye dinámicamente con este formato:

`https://v6.exchangerate-api.com/v6/{API_KEY}/latest/{MONEDA_BASE}`

De esa forma, la moneda base cambia según la selección del usuario.
