import styled from "styled-components";

export const AlertContainer = styled.div`
  border: 1px solid #ddd;
  border-radius: 16px;
  display: flex;
  height: 50px;
  align-items: center;
`;

export const Symbol = styled.div`
  width: 30px;
  height: inherit;
  border-radius: 16px 0px 0px 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 700;
`;

export const TextContainer = styled.div`
  padding: 8px;
`;

export const Title = styled.span`
  font-weight: 500;
`;

export const Text = styled.span``;

export const CloseSymbol = styled.div`
  padding: 8px;
  cursor: pointer;
  font-weight: 700;
`;
