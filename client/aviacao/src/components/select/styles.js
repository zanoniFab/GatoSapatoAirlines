import styled from "styled-components";

export const SelectContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 4px;
  margin: 8px;
  @media (max-width: 700px) {
    flex-direction: row;
    justify-content: space-between;
    width: 100%;
  }
`;

export const StyledSelect = styled.select`
  border: 1px solid #d3d3d3;
  border-radius: 5px;
  padding: 4px;
  width: 50vw;
`;

export const SelectLabel = styled.label`
  font-style: bold;
`;
