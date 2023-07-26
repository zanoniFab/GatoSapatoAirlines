import styled from "styled-components";

export const StyledForm = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex-grow: 1;
  margin-bottom: 32px;
  gap: 8px;
  @media (max-width: 700px) {
    justify-content: space-between;
  }
`;
