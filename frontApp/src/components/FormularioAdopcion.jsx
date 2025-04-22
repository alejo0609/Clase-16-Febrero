import React, { useState } from "react";
import { useParams, useNavigate, useLocation } from "react-router-dom";
import axios from "axios";

const FormularioAdopcion = () => {
  const { idAnimal } = useParams();
  const { id } = useParams();
  const navigate = useNavigate();
  const location = useLocation();
  const animal = location.state?.animal;

  // Asegúrate de agregar idTienda aquí en el estado
  const [formData, setFormData] = useState({
    nombre: "",
    dni: "",
    direccion: "",
    ciudad: "",
    telefono: "",
    correo: "",
    ocupacion: "",
    tipoVivienda: "",
    motivoAdopcion: ""
  });

 
    
 

  const [formularioEnviado, setFormularioEnviado] = useState(false);
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    
  
    const dataToSend = {
      ...formData,
      animal: {
        idAnimal: animal?.idAnimal
      }
      
    };
    console.log("Enviando:", dataToSend);
  
    try {
      const response = await axios.post("http://localhost:8080/adopcion", dataToSend);
      if (response.status === 200) {
        setFormularioEnviado(true);
        setError(null);
        setTimeout(() => {
          navigate("/");
        }, 3000);
      }
    } catch (err) {
      setError("Error al enviar el formulario. Intenta nuevamente.");
      console.error(err);
      console.error("Error:", err.response?.data || err.message);
    }
  };
  

  if (!animal) {
    return (
      <p className="p-4 text-red-600 font-semibold text-center">
        No se pudo obtener la información del animal.
      </p>
    );
  }

  return (
    <div className="max-w-2xl mx-auto p-6 bg-white rounded-2xl shadow-md mt-6">
      <h2 className="text-2xl font-bold mb-4 text-center text-green-700">
        Formulario de Adopción
      </h2>

      <div className="border p-4 rounded-lg shadow mb-6 bg-white">
        <h3 className="text-lg font-semibold mb-2">{animal.nombre_animal}</h3>
        <p><strong>Raza:</strong> {animal.raza}</p>
        <p><strong>Edad:</strong> {animal.edad} años</p>
        <div className="w-full h-64 overflow-hidden rounded mt-3">
          <img
            src={`/aset/${animal.imagen_animal}`}
            alt={animal.nombre_animal}
            className="w-full h-full object-cover object-center"
          />
        </div>
      </div>

      {formularioEnviado ? (
        <div className="mt-6 p-4 bg-green-100 text-green-800 rounded-xl text-center font-semibold">
          ¡Gracias por enviar tu solicitud de adopción! Pronto serás
          contactado por la tienda encargada.
        </div>
      ) : (
        <form onSubmit={handleSubmit}>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <input
              type="text"
              name="nombre"
              placeholder="Nombre completo"
              className="p-2 border rounded"
              value={formData.nombre}
              onChange={handleChange}
              required
            />
            <input
              type="text"
              name="dni"
              placeholder="DNI"
              className="p-2 border rounded"
              value={formData.dni}
              onChange={handleChange}
              required
            />
            <input
              type="text"
              name="direccion"
              placeholder="Dirección"
              className="p-2 border rounded"
              value={formData.direccion}
              onChange={handleChange}
              required
            />
            <input
              type="text"
              name="ciudad"
              placeholder="Ciudad"
              className="p-2 border rounded"
              value={formData.ciudad}
              onChange={handleChange}
              required
            />
            <input
              type="tel"
              name="telefono"
              placeholder="Teléfono"
              className="p-2 border rounded"
              value={formData.telefono}
              onChange={handleChange}
              required
            />
            <input
              type="email"
              name="correo"
              placeholder="Correo electrónico"
              className="p-2 border rounded"
              value={formData.correo}
              onChange={handleChange}
              required
            />
            <input
              type="text"
              name="ocupacion"
              placeholder="Ocupación"
              className="p-2 border rounded"
              value={formData.ocupacion}
              onChange={handleChange}
              required
            />
            <input
              type="text"
              name="tipoVivienda"
              placeholder="Tipo de vivienda"
              className="p-2 border rounded"
              value={formData.tipoVivienda}
              onChange={handleChange}
              required
            />
          </div>
          <div className="mt-4">
            <textarea
              name="motivoAdopcion"
              placeholder="Motivo de adopción"
              rows="4"
              className="w-full p-2 border rounded"
              value={formData.motivoAdopcion}
              onChange={handleChange}
              required
            ></textarea>
          </div>
          <button
            type="submit"
            className="mt-6 w-full bg-green-600 text-white py-2 rounded-md font-bold hover:bg-green-700 transition"
          >
            Enviar Solicitud
          </button>
        </form>
      )}

      {error && (
        <div className="mt-6 p-4 bg-red-100 text-red-800 rounded-xl text-center font-semibold">
          {error}
        </div>
      )}
    </div>
  );
};

export default FormularioAdopcion;
