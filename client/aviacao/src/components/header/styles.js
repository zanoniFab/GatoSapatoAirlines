import styled from "styled-components";

export const HeaderDiv = styled.div`
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  background-color: var(--primary);
  color: white;
  padding: 16px;
  width: 100vw;
  gap: 16px;
  margin-bottom: 8px;
  @media (max-width: 700px) {
    display: block;
  }
`;

export const StyledDiv = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  @media (max-width: 700px) {
    display: block;
  }
`;

export const ButtonDiv = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
`;
