import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function Adopta() {
  const [animals, setAnimals] = useState([]);
  const [postulaciones, setPostulaciones] = useState({});

  useEffect(() => {
    axios
      .get("http://localhost:8080/animal/disponibles") // Endpoint para obtener todos los animales
      .then((response) => {
        setAnimals(response.data);
      })
      .catch((error) => console.error("Error al obtener animales:", error));
  }, []);

  useEffect(() => {
    if (animals.length > 0) {
      const fetchPostulaciones = async () => {
        const nuevasPostulaciones = {};
        for (const animal of animals) {
          try {
            const response = await axios.get(
              `http://localhost:8080/adopcion/totalPostulaciones/${animal.idAnimal}`
            );
            nuevasPostulaciones[animal.idAnimal] = response.data;
          } catch (error) {
            console.error(
              "Error al obtener postulaciones para animal ID:",
              animal.idAnimal,
              error
            );
            nuevasPostulaciones[animal.idAnimal] = 0;
          }
        }
        setPostulaciones(nuevasPostulaciones);
      };

      fetchPostulaciones();
    }
  }, [animals]);

  return (
    <main className="py-12 bg-gray-100 flex flex-col items-center">
      <div className="max-w-6xl mx-auto px-6 text-center">
        <h2 className="text-3xl font-bold text-gray-800 mb-8">
          🐾 Animales Disponibles para Adopción 🐾
        </h2>
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {animals.length > 0 ? (
            animals.map((animal, index) => {
              const totalPostulaciones = postulaciones[animal.idAnimal] || 0;
              const estaCompleto = totalPostulaciones >= 3;

              return (
                <div
                  key={index}
                  className={`bg-white shadow-md rounded-lg overflow-hidden ${
                    estaCompleto ? "opacity-50" : ""
                  }`}
                >
                  <img
                    src={
                      animal.imagen_animal
                        ? `/aset/${animal.imagen_animal}`
                        : "https://via.placeholder.com/150"
                    }
                    alt={animal.nombre_animal}
                    className="w-full h-40 object-cover"
                  />
                  <div className="p-4">
                    <h3 className="text-xl font-semibold text-gray-800">
                      {animal.nombre_animal}
                    </h3>
                    <p className="text-gray-600">Edad: {animal.edad} años</p>
                    <p className="text-gray-600">Raza: {animal.raza}</p>
                    <p className="text-gray-600">
                      Postulaciones: {totalPostulaciones} de 3
                    </p>
                    <p
                      className={`text-sm font-bold mt-2 ${
                        animal.estado_animal ? "text-red-500" : "text-blue-500"
                      }`}
                    >
                      {animal.estado_animal ? "No disponible" : "Disponible"}
                    </p>
                    <Link
                      to={`/formulario-adopcion/${animal.idAnimal}`}
                      state={{ animal }}
                      className={`mt-4 inline-block text-white px-4 py-2 rounded transition duration-300 ${
                        estaCompleto
                          ? "bg-gray-400 cursor-not-allowed"
                          : "bg-green-600 hover:bg-green-700"
                      }`}
                      onClick={(e) => {
                        if (estaCompleto) e.preventDefault();
                      }}
                    >
                      {estaCompleto ? "Cupo lleno" : "Adoptar"}
                    </Link>
                  </div>
                </div>
              );
            })
          ) : (
            <p className="text-gray-600">No hay animales disponibles.</p>
          )}
        </div>
      </div>
    </main>
  );
}

export default Adopta;
