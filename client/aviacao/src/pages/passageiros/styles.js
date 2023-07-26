import styled from "styled-components";

export const Table = styled.table`
  border-collapse: collapse;
  @media (max-width: 700px) {
    width: 100%;
  }
`;

export const Cabecalho = styled.thead`
  @media (max-width: 700px) {
    display: none;
  }
`;

export const Corpo = styled.tbody`
  @media (max-width: 700px) {
    width: 100%;
  }
`;

export const Linhas = styled.tr`
  @media (max-width: 700px) {
    width: 100%;
    margin-bottom: 16px;
    display: block;
  }
`;

export const ColunaTitulo = styled.th`
  padding: 8px;
  border: 1px solid #ddd;
  text-align: center;
`;

export const Coluna = styled.td`
  padding: 8px;
  border: 1px solid #ddd;
  text-align: center;
  @media (max-width: 700px) {
    display: block;
    width: 100%;
    text-align: right;
    padding-left: 50%;
    text-align: right;
    position: relative;
    ::before {
      content: attr(datatype);
      position: absolute;
      left: 0;
      width: 50%;
      padding-left: 15px;
      font-size: 15px;
      font-weight: bold;
      text-align: left;
    }
  }
`;
