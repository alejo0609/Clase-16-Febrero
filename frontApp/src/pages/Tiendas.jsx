// src/pages/Tiendas.jsx
import React, { useEffect, useState } from "react";
import FiltroBarrios from "../components/FiltroBarrios";
import TiendaCard from "../components/TiendaCard";
import MapaTiendasGoogle from "../components/MapaTiendasGoogle";
import { calcularDistanciaGoogle } from "../utils/distancia";
import useUserLocation from "../hooks/useUserLocation";

function Tiendas() {
  const [tiendas, setTiendas] = useState([]);
  const [filtroBarrio, setFiltroBarrio] = useState("");
  const [distancias, setDistancias] = useState({});
  const [lineas, setLineas] = useState([]);
  const userCoords = useUserLocation();

  useEffect(() => {
    const fetchTiendas = async () => {
      try {
        const response = await fetch("http://localhost:8080/tiendas");
        const data = await response.json();
        setTiendas(data);
      } catch (error) {
        console.error("Error al cargar tiendas:", error);
      }
    };

    fetchTiendas();
  }, []);

  const tiendasFiltradas = filtroBarrio
    ? tiendas.filter((tienda) =>
        tienda.barrio.toLowerCase().includes(filtroBarrio.toLowerCase())
      )
    : tiendas;

  useEffect(() => {
    const obtenerDistanciasYLineas = async () => {
      const nuevasDistancias = {};
      const nuevasLineas = [];

      await Promise.all(
        tiendasFiltradas.map(async (tienda) => {
          if (userCoords && tienda.latitud && tienda.longitud) {
            const duracion = await calcularDistanciaGoogle(
              userCoords,
              { lat: tienda.latitud, lng: tienda.longitud }
            );
            nuevasDistancias[tienda.nombre] = duracion;

            nuevasLineas.push({
              from: userCoords,
              to: { lat: tienda.latitud, lng: tienda.longitud },
            });
          }
        })
      );

      setDistancias(nuevasDistancias);
      setLineas(nuevasLineas);

      // Cargar líneas desde el backend
      try {
        const res = await fetch(`http://localhost:8080/mapa/lineas?userLat=${userCoords.lat}&userLon=${userCoords.lng}`);
        const data = await res.json();
        setLineas(
          data.map((linea) => ({
            from: { lat: linea.fromLat, lng: linea.fromLon },
            to: { lat: linea.toLat, lng: linea.toLon },
          }))
        );
      } catch (error) {
        console.error("Error al cargar líneas desde el backend:", error);
      }
    };

    if (userCoords && tiendasFiltradas.length > 0) {
      obtenerDistanciasYLineas();
    }
  }, [userCoords, tiendasFiltradas]);

  return (
    <div className="p-4">
      <h2 className="text-2xl font-bold mb-4 text-green-700">Tiendas Registradas</h2>

      <FiltroBarrios tiendas={tiendas} setFiltroBarrio={setFiltroBarrio} />

      <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-4 mb-8">
        {tiendasFiltradas.map((tienda, i) => (
          <TiendaCard
            key={i}
            tienda={tienda}
            distancia={distancias[tienda.nombre] || "Calculando distancia..."}
          />
        ))}
      </div>

      <MapaTiendasGoogle tiendas={tiendasFiltradas} userCoords={userCoords} lineas={lineas} />
    </div>
  );
}

export default Tiendas;
