package com.pruebas.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class GeoUtils {

    public double[] obtenerCoordenadas(String direccion) {
        try {
            // Añade ", Colombia" al final y codifica la dirección
            String encodedDireccion = URLEncoder.encode(direccion + ", Colombia", "UTF-8");
            String endpoint = "https://nominatim.openstreetmap.org/search?format=json&addressdetails=1&q=" + encodedDireccion;

            // 👉 Mostrar en consola la dirección y URL que se va a consultar
            System.out.println("🔍 Dirección a buscar: " + direccion);
            System.out.println("🌐 URL consultada: " + endpoint);

            HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Leer la respuesta
            StringBuilder json = new StringBuilder();
            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                while (scanner.hasNext()) {
                    json.append(scanner.nextLine());
                }
            }

            // 👉 Mostrar el JSON recibido (solo si quieres verificar resultados)
            System.out.println("📦 Respuesta JSON: " + json.toString());

            JSONArray datos = new JSONArray(json.toString());
            if (datos.length() > 0) {
                JSONObject ubicacion = datos.getJSONObject(0);
                double lat = Double.parseDouble(ubicacion.getString("lat"));
                double lon = Double.parseDouble(ubicacion.getString("lon"));
                System.out.println("✅ Coordenadas obtenidas: lat=" + lat + ", lon=" + lon);
                return new double[]{lat, lon};
            } else {
                System.out.println("⚠️ No se encontraron coordenadas para esa dirección.");
            }

        } catch (UnsupportedEncodingException e) {
            System.err.println("❌ Error al codificar la dirección: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("❌ Error al conectarse a la API de Nominatim: " + e.getMessage());
        }

        return null;
    }
}
