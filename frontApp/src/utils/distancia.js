// src/utils/distancia.js

/**
 * Calcula la duración estimada entre dos puntos usando la API de Google Maps Distance Matrix.
 * @param {Object} origen - Coordenadas del usuario { lat, lon }
 * @param {Object} destino - Coordenadas de la tienda { lat, lon }
 * @returns {Promise<string>} - Duración estimada en texto (por ejemplo, "12 mins") o mensaje de error
 */
 export async function calcularDistanciaGoogle(origen, destino) {
    const apiKey = ""; // 🔐 Reemplázala con tu clave
  
    const url = `https://maps.googleapis.com/maps/api/distancematrix/json?origins=${origen.lat},${origen.lon}&destinations=${destino.lat},${destino.lon}&mode=driving&key=${apiKey}`;
  
    try {
      const response = await fetch(url);
      const data = await response.json();
  
      if (data.status !== "OK") {
        throw new Error("Error desde Google Distance Matrix API: " + data.status);
      }
  
      const duration = data.rows[0].elements[0].duration?.text;
      return duration || "No disponible";
    } catch (error) {
      console.error("❌ Error al calcular distancia:", error);
      return "Error al calcular distancia";
    }
  }
  