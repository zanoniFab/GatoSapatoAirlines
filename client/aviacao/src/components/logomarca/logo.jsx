import { LogodDiv, LogoImg } from "./styles";

function Logo() {
  return (
    <LogodDiv>
      <LogoImg
        src="../../src/assets/gatoSapato.png"
        aria-label="Logomarca da gato e sapato airlines"
      />
      <h1>Gato & Sapato Airlines</h1>
    </LogodDiv>
  );
}

export default Logo;
