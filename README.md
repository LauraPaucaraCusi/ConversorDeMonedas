# Conversor de Monedas

Este proyecto en Java consume una API pública de tasas de cambio para convertir montos desde USD a diferentes monedas.  
Permite al usuario ingresar el código de la moneda y la cantidad en USD para obtener el equivalente convertido.

## Funcionalidades

- Consulta tasas de cambio en tiempo real desde [ExchangeRate-API](https://www.exchangerate-api.com/).
- Conversión de montos USD a monedas seleccionadas.
- Guarda un historial de conversiones en un archivo `tasas.txt`.

## Requisitos

- Java 11 o superior.
- Biblioteca Gson para manejo de JSON (`gson-2.10.1.jar` incluida en `/lib`).
- Conexión a Internet para consultar la API.

## Cómo ejecutar

1. Clonar o descargar el repositorio.
2. Abrir el proyecto en IntelliJ IDEA u otro IDE.
3. Ejecutar la clase `PruebaAPI.java`.
4. Seguir las instrucciones en consola para realizar conversiones.

## Autor

Laura Paucara  
[https://github.com/LauraPaucaraCusi](https://github.com/LauraPaucaraCusi)

---

*Proyecto desarrollado como parte del desafío de programación en Alura Latam.*
~~~~