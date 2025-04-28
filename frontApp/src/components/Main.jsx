import React, { useEffect, useState } from "react";
import axios from "axios";
import { FaPaw, FaNewspaper } from "react-icons/fa";
import { Link } from "react-router-dom";

// Importar imágenes de noticias desde assets
import news1 from "../assets/news1.jpg";
import news2 from "../assets/news2.jpg";
import news3 from "../assets/news3.jpg";

function Main() {
  const [animals, setAnimals] = useState([]);
  const [postulaciones, setPostulaciones] = useState({});

  useEffect(() => {
    axios
      .get("http://localhost:8080/animal/ultimos4") // Obtiene los últimos 4 animales para adopción
      .then((response) => {
        console.log("Datos recibidos:", response.data); // Depuración
        setAnimals(response.data);
      })
      .catch((error) => console.error("Error al obtener datos:", error));
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

  // Datos de Noticias
  const news = [
    {
      title: "Historias de adopción: Cómo Max encontró un hogar",
      description:
        "Descubre la emotiva historia de Max, un perro rescatado que encontró una nueva familia.",
      image: news1,
      link: "#",
    },
    {
      title: "Consejos para cuidar un gato en casa",
      description:
        "Todo lo que necesitas saber para que tu gato tenga una vida feliz y saludable.",
      image: news2,
      link: "#",
    },
    {
      title: "Importancia de la esterilización en mascotas",
      description:
        "Por qué esterilizar a tu mascota es una de las mejores decisiones para su salud.",
      image: news3,
      link: "#",
    },
  ];

  return (
    <main className="py-12 bg-gray-100 flex flex-col items-center">
      <div className="max-w-6xl mx-auto px-6 text-center">
        {/* 🐾 Sección de Animales en Adopción */}
        <h2 className="text-3xl font-bold text-gray-800 mb-8">
          🐾 Últimos 4 Animales en Adopción 🐾
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

        {/* 📝 Sección de Noticias */}
        <div className="mt-12">
          <h2 className="text-3xl font-bold text-gray-800 mb-8 flex justify-center items-center gap-2">
            <FaNewspaper className="text-blue-600" /> Noticias y Artículos 📰
          </h2>
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
            {news.map((article, index) => (
              <div
                key={index}
                className="bg-white p-4 rounded-lg shadow-md hover:shadow-lg transition"
              >
                <img
                  src={article.image}
                  alt={article.title}
                  className="w-full h-40 object-cover rounded-md mb-4"
                />
                <h3 className="text-xl font-bold text-gray-800">
                  {article.title}
                </h3>
                <p className="text-gray-600">{article.description}</p>
                <a
                  href={article.link}
                  className="mt-3 inline-block text-blue-600 font-bold hover:underline"
                >
                  Leer más →
                </a>
              </div>
            ))}
          </div>
        </div>
      </div>
    </main>
  );
}

export default Main;
