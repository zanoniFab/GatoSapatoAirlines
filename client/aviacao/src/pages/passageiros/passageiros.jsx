import PageWrapper from "../../components/pageWrapper/pageWrapper";
import useListaPassageiros from "../../hooks/useListaPassageiros";
import { Coluna, ColunaTitulo, Table, Cabecalho, Corpo, Linhas } from "./styles";

function Passageiros() {
  const { ListaPassageiros } = useListaPassageiros();
  return (
    <PageWrapper>
      <h1>Passageiros</h1>
      <Table>
        <Cabecalho>
          <tr>
            <ColunaTitulo>CPF</ColunaTitulo>
            <ColunaTitulo>Nome</ColunaTitulo>
            <ColunaTitulo>Data de Nascimento</ColunaTitulo>
            <ColunaTitulo>Programa de Fidelidade</ColunaTitulo>
            <ColunaTitulo>Milhas</ColunaTitulo>
            <ColunaTitulo>Assento</ColunaTitulo>
            <ColunaTitulo>e-ticket</ColunaTitulo>
          </tr>
        </Cabecalho>
        <Corpo>
          {ListaPassageiros.map((passageiro) => (
            <Linhas key={passageiro.cpf}>
              <Coluna datatype={"CPF"}>{passageiro.cpf}</Coluna>
              <Coluna datatype={"Nome"}>{passageiro.nome}</Coluna>
              <Coluna datatype={"Nascimento"}>
                {passageiro.dataNascimento}
              </Coluna>
              <Coluna datatype={"Fidelidade"}>
                {passageiro.classificacao}
              </Coluna>
              <Coluna datatype={"Milhas"}>{passageiro.milhas}</Coluna>
              {passageiro.assento && (
                <Coluna datatype={"Assento"}>{passageiro.assento}</Coluna>
              )}
              {!passageiro.assento && <Coluna datatype={"Assento"}>-</Coluna>}
              {passageiro.eticket && (
                <Coluna datatype={"e-ticket"}>{passageiro.eticket}</Coluna>
              )}
              {!passageiro.eticket && <Coluna datatype={"e-ticket"}>-</Coluna>}
            </Linhas>
          ))}
        </Corpo>
      </Table>
    </PageWrapper>
  );
}

export default Passageiros;
