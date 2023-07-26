import styled from "styled-components";

export const InputContainer = styled.div`
  margin: 8px;
  @media (max-width: 700px) {
    width: 100%;
  }
`;

export const StyledInput = styled.input`
  border: 1px solid #d3d3d3;
  border-radius: 5px;
  padding: 4px;
  width: 420px;
  @media (max-width: 700px) {
    width: 100%;
  }
`;

export const InputLabel = styled.label`
  font-style: bold;
  padding-right: 8px;
`;
