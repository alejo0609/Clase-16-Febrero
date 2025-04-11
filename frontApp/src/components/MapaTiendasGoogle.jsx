// src/components/MapaTiendasGoogle.jsx
import React, { useEffect, useState } from "react";
import {
  GoogleMap,
  LoadScript,
  Marker,
  InfoWindow,
  Polyline,
} from "@react-google-maps/api";

const MapaTiendasGoogle = ({ tiendas, userCoords, lineas = [] }) => {
  const [infoTienda, setInfoTienda] = useState(null);

  const containerStyle = {
    width: "100%",
    height: "500px",
  };

  const tiendasConCoordenadas = tiendas.filter(
    (tienda) => tienda.latitud && tienda.longitud
  );

  console.log("📍 Coordenadas del usuario:", userCoords);

  return (
    <LoadScript googleMapsApiKey="">
      {userCoords && (
        <GoogleMap
          mapContainerStyle={containerStyle}
          center={userCoords}
          zoom={13}
        >
          {/* Usuario */}
          <Marker position={userCoords} label="Tú" />

          {/* Tiendas */}
          {tiendasConCoordenadas.map((tienda, index) => (
            <Marker
              key={index}
              position={{ lat: tienda.latitud, lng: tienda.longitud }}
              onClick={() => setInfoTienda(tienda)}
            />
          ))}

          {/* Líneas desde el usuario a cada tienda */}
          {lineas.map((linea, index) => (
            <Polyline
              key={index}
              path={[
                { lat: linea.from.lat, lng: linea.from.lng }, // ✅ Corregido aquí
                { lat: linea.to.lat, lng: linea.to.lng },     // ✅ Y aquí
              ]}
              options={{
                strokeColor: "#4285F4",
                strokeOpacity: 0.7,
                strokeWeight: 3,
              }}
            />
          ))}

          {/* Info de tienda */}
          {infoTienda && (
            <InfoWindow
              position={{ lat: infoTienda.latitud, lng: infoTienda.longitud }}
              onCloseClick={() => setInfoTienda(null)}
            >
              <div>
                <strong>{infoTienda.nombre}</strong>
                <br />
                {infoTienda.direccion}
              </div>
            </InfoWindow>
          )}
        </GoogleMap>
      )}
    </LoadScript>
  );
};

export default MapaTiendasGoogle;
