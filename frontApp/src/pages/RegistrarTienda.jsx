import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { GoogleMap, LoadScript, Marker, DirectionsService, DirectionsRenderer } from "@react-google-maps/api";

// 👉 API Key de Google Maps (Asegúrate de que Directions API esté habilitada)
const GOOGLE_MAPS_API_KEY = "AIzaSyBsecUTtE7TqdZXewlBoz5a0HEXOV5IQic";

// Estilos del mapa
const containerStyle = {
  width: "100%",
  height: "500px",
};

function Tiendas() {
  const [stores, setStores] = useState([]);
  const [selectedStore, setSelectedStore] = useState(null);
  const [userLocation, setUserLocation] = useState({ lat: 4.645, lng: -74.063 });
  const [route, setRoute] = useState(null);
  const [travelTime, setTravelTime] = useState({ driving: "", walking: "" });

  useEffect(() => {
    const tiendasEjemplo = [
      {
        name: "Tienda Animalista",
        address: "Calle 10 #45-23",
        city: "Medellin",
        lat: 4.645,
        lng: -74.063,
        phone: "+573001234567",
        whatsapp: "+573001234567",
        products: "Alimentos, juguetes, ropa para mascotas",
      },
      {
        name: "Pet Store",
        address: "Carrera 50 #23-89",
        city: "Medellín",
        lat: 6.209,
        lng: -75.566,
        phone: "+573112345678",
        whatsapp: "+573112345678",
        products: "Accesorios, comida premium, servicio veterinario",
      },
    ];
    setStores(tiendasEjemplo);
  }, []);

  // Obtener la ubicación del usuario
  useEffect(() => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          setUserLocation({
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          });
        },
        (error) => console.error("No se pudo obtener la ubicación", error)
      );
    }
  }, []);

  // Calcular la ruta y tiempo estimado
  const handleRequestRide = (store) => {
    if (!userLocation.lat || !userLocation.lng) {
      alert("No pudimos obtener tu ubicación.");
      return;
    }
    setSelectedStore(store);
  };

  return (
    <LoadScript googleMapsApiKey={GOOGLE_MAPS_API_KEY}>
      <div className="min-h-screen bg-gray-100 p-6">
        <h2 className="text-3xl font-bold text-center text-blue-700 mb-6">📍 Tiendas</h2>

        {/* Botón para Registrar Tienda */}
        <div className="text-center mb-6">
          <Link to="/registrar-tienda">
            <button className="bg-yellow-500 text-white px-6 py-2 rounded-md font-bold hover:bg-yellow-600 transition">
              🏪 Registrar Nueva Tienda
            </button>
          </Link>
        </div>

        {/* Lista de tiendas */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-6">
          {stores.map((store, index) => (
            <div key={index} className="bg-white p-4 rounded-lg shadow-md">
              <h3 className="text-xl font-bold text-gray-800">{store.name}</h3>
              <p className="text-gray-600">{store.address}, {store.city}</p>
              <p className="text-gray-600">📞 {store.phone}</p>
              <p className="text-blue-600">🛍️ {store.products}</p>

              {/* Botones */}
              <div className="flex flex-col space-y-2 mt-4">
                <a href={`https://wa.me/${store.whatsapp}`} target="_blank" rel="noopener noreferrer"
                  className="bg-green-500 text-white px-4 py-2 rounded-md font-bold text-center hover:bg-green-600 transition">
                  Comprar por WhatsApp
                </a>

                <button
                  onClick={() => handleRequestRide(store)}
                  className="bg-purple-500 text-white px-4 py-2 rounded-md font-bold text-center hover:bg-purple-600 transition"
                >
                  🚗 Ver Ruta
                </button>
              </div>
            </div>
          ))}
        </div>

        {/* Mapa con Google Maps */}
        <div className="max-w-4xl mx-auto bg-white p-4 rounded-lg shadow-md">
          <h3 className="text-xl font-semibold text-gray-700 mb-4 text-center">Mapa de Tiendas</h3>
          <GoogleMap mapContainerStyle={containerStyle} center={userLocation} zoom={12}>
            {/* Marcador del usuario */}
            <Marker position={userLocation} icon="http://maps.google.com/mapfiles/ms/icons/blue-dot.png" />

            {/* Marcadores de tiendas */}
            {stores.map((store, index) => (
              <Marker
                key={index}
                position={{ lat: store.lat, lng: store.lng }}
                onClick={() => setSelectedStore(store)}
              />
            ))}

            {/* Mostrar la ruta con Directions API */}
            {selectedStore && (
              <>
                {/* Solicitar la ruta */}
                <DirectionsService
                  options={{
                    destination: { lat: selectedStore.lat, lng: selectedStore.lng },
                    origin: userLocation,
                    travelMode: "DRIVING",
                  }}
                  callback={(result, status) => {
                    if (status === "OK") {
                      setRoute(result);
                      const durationDriving = result.routes[0].legs[0].duration.text;

                      // Solicitar ruta caminando
                      new window.google.maps.DirectionsService().route(
                        {
                          destination: { lat: selectedStore.lat, lng: selectedStore.lng },
                          origin: userLocation,
                          travelMode: "WALKING",
                        },
                        (walkingResult, walkingStatus) => {
                          if (walkingStatus === "OK") {
                            const durationWalking = walkingResult.routes[0].legs[0].duration.text;
                            setTravelTime({
                              driving: durationDriving,
                              walking: durationWalking,
                            });
                          }
                        }
                      );
                    }
                  }}
                />

                {/* Renderizar la ruta */}
                {route && <DirectionsRenderer directions={route} />}
              </>
            )}
          </GoogleMap>
        </div>

        {/* Mostrar tiempo de llegada */}
        {selectedStore && (
          <div className="text-center mt-4 bg-gray-200 p-4 rounded-md">
            <h3 className="text-lg font-bold text-gray-700">Tiempo Estimado de Llegada</h3>
            <p>🚗 En auto: <span className="text-blue-600 font-bold">{travelTime.driving}</span></p>
            <p>🚶 A pie: <span className="text-green-600 font-bold">{travelTime.walking}</span></p>
          </div>
        )}
      </div>
    </LoadScript>
  );
}

export default Tiendas;