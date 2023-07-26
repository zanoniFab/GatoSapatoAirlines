import { Link } from "react-router-dom";
import Button from "../button/button";
import Logo from "../logomarca/logo";
import { HeaderDiv, StyledDiv, ButtonDiv } from "./styles";

function Header() {
  return (
    <HeaderDiv>
      <StyledDiv>
        <Logo/>
        <ButtonDiv>
          <Link to={"/passageiros"}>
            <Button>Passageiros</Button>
          </Link>
          <Link to={"/checkin"}>
            <Button>CheckIn</Button>
          </Link>
          <Link to={"/eticket"}>
            <Button>E-ticket</Button>
          </Link>
        </ButtonDiv>
      </StyledDiv>
    </HeaderDiv>
  );
}

export default Header;
