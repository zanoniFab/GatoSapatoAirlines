import styled from "styled-components";

export const EticketDiv = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  width: 420px;
  border: 1px solid #ddd;
  padding: 8px;
  border-radius: 16px;
  margin-top: 32px;
  @media (max-width: 700px) {
    width: 100%;
  }
`;

export const StyledQRCode = styled.div`
  padding: 16px;
`;
