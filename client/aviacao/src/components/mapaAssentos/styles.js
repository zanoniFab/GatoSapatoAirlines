import styled from "styled-components";

export const Table = styled.table`
  border: 1px solid #ddd;
  border-radius: 16px;
  padding: 16px;
  @media (max-width: 700px) {
    display: block;
  }
`;

export const Linhas = styled.tr``;

export const Livre = styled.td`
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 8px;
  text-align: center;
`;

export const Ocupado = styled.td`
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 8px;
  text-align: center;
  background-color: gray;
`;

export const Selecionado = styled.td`
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 8px;
  text-align: center;
  background-color: green;
`;

export const Corpo = styled.tbody``;
