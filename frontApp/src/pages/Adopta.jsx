import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

const Animales = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/animal/disponibles") 
      .then(response => {
        console.log("Datos recibidos:", response.data); 
        setData(response.data);
      })
      .catch(error => {
        console.error("Error al obtener datos:", error);
      });
  }, []);

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Lista de Animales</h1>
      {data.length > 0 ? (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6"> 
          {data.map(animal => (
            <div key={animal.idAnimal} className="border rounded-lg shadow-md p-4 text-center flex flex-col justify-between h-full">
              <img 
                src={`/aset/${animal.imagen_animal}`} 
                alt={animal.nombre_animal} 
                className="w-full h-40 object-cover object-center rounded-md"
              />
              <div className="mt-2">
                <h2 className="text-lg font-semibold">{animal.nombre_animal}</h2>
                <p><strong>Raza:</strong> {animal.raza}</p>
                <p><strong>Edad:</strong> {animal.edad} años</p>
                <p><strong>Estado:</strong> {animal.estadoAnimal ? "Disponible" : "No disponible"}</p>
              </div>
              <Link 
                to={`/formulario-adopcion/${animal.idAnimal}`} 
                state={{ animal }}
                className="mt-4 inline-block bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700 transition duration-300"
              >
                Adoptar
              </Link>
            </div>
          ))}
        </div>
      ) : (
        <p>No hay animales disponibles.</p>
      )}
    </div>
  );
};

export default Animales;
