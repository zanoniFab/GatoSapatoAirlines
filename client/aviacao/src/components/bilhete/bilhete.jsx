import QRCode from "react-qr-code";
import PropTypes from "prop-types";
import dateFormat from "dateformat";
import { EticketDiv, StyledQRCode } from "./styles";

function Bilhete({ dados }) {
  return (
    <EticketDiv>
      <StyledQRCode>
        <QRCode
          value={
            "http://localhost:8080/api/passageiros/confirmacao/" + dados.eticket
          }
        />
      </StyledQRCode>
      <h2>
        {dados.nome} - {dados.classificacao}
      </h2>
      <strong>Eticket {dados.eticket} </strong>
      <p>Assento: {dados.assento}</p>
      <p>Mala Despachada: {dados.malasDespachadas ? "sim" : "não"}</p>
      <p>
        Data da confirmação:{" "}
        {dateFormat(dados.dataHoraConfirmacao, "dd mm yyyy HH:MM:ss")}
      </p>
    </EticketDiv>
  );
}

Bilhete.propTypes = {
  dados: PropTypes.shape({
    eticket: PropTypes.string,
    cpf: PropTypes.number,
    nome: PropTypes.string,
    assento: PropTypes.string,
    malasDespachadas: PropTypes.bool,
    dataHoraConfirmacao: PropTypes.string,
    classificacao: PropTypes.string,
  }),
};

export default Bilhete;
