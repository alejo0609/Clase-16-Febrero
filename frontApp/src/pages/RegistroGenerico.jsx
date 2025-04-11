import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

// Componente Input reutilizable
const Input = ({ label, name, type = "text", value, onChange }) => (
  <div className="mb-4 text-left">
    <label className="block text-gray-700 font-semibold mb-1">{label}</label>
    <input
      type={type}
      name={name}
      value={value || ""}
      onChange={onChange}
      className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-green-400"
      required
    />
  </div>
);

function RegistroGenerico() {
  const { tipo } = useParams();

  const camposIniciales = {
    usuario: {
      name: "",
      dni: "",
      direccion: "",
      ciudad: "",
      telefono: "",
      email: "",
      password: "",
      confirm_password: "",
    },
    cuidador: {
      name: "",
      dni: "",
      direccion: "",
      ciudad: "",
      telefono: "",
      email: "",
      password: "",
      confirm_password: "",
    },
    tienda: {
      nombre: "",
      direccion: "",
      barrio: "",
      ciudad: "",
      telefono: "",
      email: "",
      sitio_web: "",
      password: "",
    },
    mascota: {
      nombre_animal: "",
      raza: "",
      edad: "",
      imagen_animal: "",
      estado_animal: "",
      esterilizado: "",
    },
  };

  const [formData, setFormData] = useState({});
  const [mensaje, setMensaje] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    setFormData(camposIniciales[tipo] || {});
  }, [tipo]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validar coincidencia de contraseñas si aplica
    if ((tipo === "usuario" || tipo === "cuidador") && formData.password !== formData.confirm_password) {
      setError("Las contraseñas no coinciden.");
      return;
    }

    let endpoint = "";
    switch (tipo) {
      case "usuario":
        endpoint = "http://localhost:8080/datos_personales";
        break;
      case "cuidador":
        endpoint = "http://localhost:8080/datos_personales";
        break;
      case "tienda":
        endpoint = "http://localhost:8080/tiendas";
        break;
      case "mascota":
        endpoint = "http://localhost:8080/adopta";
        break;
      default:
        setError("Tipo de registro no reconocido");
        return;
    }

    try {
      const datosAEnviar = { ...formData };
      delete datosAEnviar.confirm_password; // quitar antes de enviar

      await axios.post(endpoint, datosAEnviar);
      setMensaje("Registro exitoso");
      setError("");
      setFormData(camposIniciales[tipo]);
    } catch (err) {
      console.error("Error:", err);
      setError("Ocurrió un error al registrar");
      setMensaje("");
    }
  };

  const renderCampos = () => {
    switch (tipo) {
      case "usuario":
      case "cuidador":
        return (
          <>
            <Input label="Nombre" name="name" value={formData.name} onChange={handleChange} />
            <Input label="DNI" name="dni" value={formData.dni} onChange={handleChange} />
            <Input label="Dirección" name="direccion" value={formData.direccion} onChange={handleChange} />
            <Input label="Ciudad" name="ciudad" value={formData.ciudad} onChange={handleChange} />
            <Input label="Teléfono" name="telefono" value={formData.telefono} onChange={handleChange} />
            <Input label="Correo Electrónico" name="email" type="email" value={formData.email} onChange={handleChange} />
            <Input label="Contraseña" name="password" type="password" value={formData.password} onChange={handleChange} />
            <Input label="Confirmar Contraseña" name="confirm_password" type="password" value={formData.confirm_password} onChange={handleChange} />
          </>
        );
      case "tienda":
        return (
          <>
            <Input label="Nombre de la tienda" name="nombre" value={formData.nombre} onChange={handleChange} />
            <Input label="Dirección" name="direccion" value={formData.direccion} onChange={handleChange} />
            <Input label="Barrio" name="barrio" value={formData.barrio} onChange={handleChange} />
            <Input label="Ciudad" name="ciudad" value={formData.ciudad} onChange={handleChange} />
            <Input label="Teléfono" name="telefono" value={formData.telefono} onChange={handleChange} />
            <Input label="Correo Electrónico" name="email" type="email" value={formData.email} onChange={handleChange} />
            <Input label="Sitio Web" name="sitio_web" value={formData.sitio_web} onChange={handleChange} />
            <Input label="Contraseña" name="password" type="password" value={formData.password} onChange={handleChange} />
          </>
        );
      case "mascota":
        return (
          <>
            <Input label="Nombre del Animal" name="nombre_animal" value={formData.nombre_animal} onChange={handleChange} />
            <Input label="Raza" name="raza" value={formData.raza} onChange={handleChange} />
            <Input label="Edad" name="edad" type="number" value={formData.edad} onChange={handleChange} />
            <Input label="Imagen del Animal" name="imagen_animal" value={formData.imagen_animal} onChange={handleChange} />
            <div className="mb-4 text-left">
              <label className="block text-gray-700 font-semibold mb-1">Estado del Animal</label>
              <select
                name="estado_animal"
                value={formData.estado_animal || ""}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md"
                required
              >
                <option value="">Selecciona estado</option>
                <option value="1">Disponible</option>
                <option value="0">Adoptado</option>
              </select>
            </div>
            <div className="mb-4 text-left">
              <label className="block text-gray-700 font-semibold mb-1">Esterilizado</label>
              <select
                name="esterilizado"
                value={formData.esterilizado || ""}
                onChange={handleChange}
                className="w-full px-4 py-2 border rounded-md"
                required
              >
                <option value="">Selecciona</option>
                <option value="1">Sí</option>
                <option value="0">No</option>
              </select>
            </div>
          </>
        );
      default:
        return <p className="text-red-500">Tipo de formulario no reconocido</p>;
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-green-50">
      <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-4 text-center text-green-700">
          Registrar {tipo}
        </h2>

        {mensaje && <p className="text-green-500 text-center mb-4">{mensaje}</p>}
        {error && <p className="text-red-500 text-center mb-4">{error}</p>}

        <form onSubmit={handleSubmit}>
          {renderCampos()}
          <button
            type="submit"
            className="w-full bg-green-600 text-white py-2 rounded-md font-bold hover:bg-green-700 transition mt-4"
          >
            Registrar
          </button>
        </form>
      </div>
    </div>
  );
}

export default RegistroGenerico;