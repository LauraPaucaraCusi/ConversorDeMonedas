# 💱 Conversor de Monedas en Java

Este proyecto en Java permite convertir montos desde USD a diferentes monedas, consumiendo datos en tiempo real desde la **ExchangeRate-API**.  
Además, guarda un historial detallado de las conversiones realizadas, incluyendo la fecha y hora exactas.

---

## ✨ Funcionalidades

✅ Consulta tasas de cambio actualizadas desde ExchangeRate-API.  
✅ Conversión de montos ingresados en USD a otras monedas elegidas.  
✅ Guarda un historial en `tasas.txt` con:
- Fecha y hora de la conversión.
- Moneda seleccionada y tasa usada.
- Total convertido.
- Otras tasas de interés (EUR, PEN, JPY, MXN, etc.).  
  ✅ Presentación clara, ordenada y fácil de leer.

---

## 📦 Requisitos

- Java 11 o superior.
- Biblioteca **Gson** para procesar JSON (`gson-2.10.1.jar` incluida en `/lib`).
- Conexión a Internet para consultar la API.

---

## ▶️ Cómo ejecutar

1️⃣ Clonar o descargar este repositorio.  
2️⃣ Abrir el proyecto en **IntelliJ IDEA** u otro IDE compatible.  
3️⃣ Ejecutar la clase `PruebaAPI.java`.  
4️⃣ Seguir las instrucciones por consola:
- Ingresar el código de la moneda (ejemplo: CLP, EUR, MXN, etc.).
- Ingresar la cantidad en USD para convertir.
- Ver en consola el resultado y el historial actualizado en `tasas.txt`.

---

## 📌 Detalles técnicos

- Se usa la API gratuita de ExchangeRate-API:  
  `https://v6.exchangerate-api.com/v6/tu_api_key_aqui/latest/USD`
- Historial guardado con marcas de tiempo usando `java.time.LocalDateTime`.
- Manejo de datos JSON gracias a **Gson**.

---

## 👩‍💻 Autor

Laura Paucara  
🔗 [Mi GitHub](https://github.com/LauraPaucaraCusi)

Desarrollado como parte del **Desafío Alura Latam**.

---

⭐ ¡Gracias por revisar este proyecto!