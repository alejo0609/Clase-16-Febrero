// src/hooks/useUserLocation.js
import { useEffect, useState } from "react";

const useUserLocation = () => {
  const [coords, setCoords] = useState(null);

  useEffect(() => {
    navigator.geolocation.getCurrentPosition(
      (pos) => {
        setCoords({
          lat: pos.coords.latitude,
          lng: pos.coords.longitude, // 👈 MUY IMPORTANTE: lng, no lon
        });
      },
      () => {
        setCoords({
          lat: 6.2442,
          lng: -75.5812,
        });
      }
    );
  }, []);

  return coords;
};

export default useUserLocation;
