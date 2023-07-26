import { Table, Linhas, Corpo, Livre, Ocupado, Selecionado } from "./styles";
import PropTypes from "prop-types";

function MapaAssentos({assentos, selecionado, setSelecionado} ) {
  const assentosPorFila = [];
  
  for (let i = 0; i < assentos.length; i = i + 6) {
    assentosPorFila.push(assentos.slice(i, i + 6));
  }


  return (
      <>
        <Table>
          <Corpo>
            {assentosPorFila.map((fila, index) => (
                <Linhas key={index}>
                  {fila.map((assento) => {
                    if (assento.assento === selecionado) {
                      return (
                          <Selecionado
                              key={assento.assento}
                              onClick={() => setSelecionado("")}
                          >
                            {assento.assento}
                          </Selecionado>
                      );
                    } else if (!assento.ocupado) {
                      return (
                          <Livre
                              key={assento.assento}
                              onClick={() => setSelecionado(assento.assento)}
                          >
                            {assento.assento}
                          </Livre>
                      );
                    } else {
                      return (
                          <Ocupado key={assento.assento}>
                            {assento.assento}
                          </Ocupado>
                      );
                    }
                  })}
                </Linhas>
            ))}
          </Corpo>
        </Table>
      </>
  );
}

MapaAssentos.propTypes = {
  assentos: PropTypes.array,
  selecionado: PropTypes.string,
  setSelecionado: PropTypes.func,
};

MapaAssentos.defaultProps = {};

export default MapaAssentos;
