const Imagen = () => (
  <img src="https://www.educacionit.com/svg/medios/clarin.svg"></img>
);

function Funciones(props) {
  let contador = 1;

  return (
    <div>
      <h1>{props.titulo}  {contador}</h1>
      <h2>{props.subTitulo}</h2>
      <h3>{props.parrafo}</h3>
      <Imagen />
      <button
        onClick={() => {
          contador++;
          console.log("Pulsando boton: " + contador);
        }}
      >
        Aumentar
      </button>
    </div>
  );
}

export default Funciones;
