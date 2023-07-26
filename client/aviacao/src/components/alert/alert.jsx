import PropTypes from "prop-types";
import {
  AlertContainer,
  Symbol,
  TextContainer,
  Title,
  Text,
  CloseSymbol,
} from "./styles";

const Alert = ({ variant, text, open, setOpen }) => {
  if (open) {
    return (
      <AlertContainer
        style={{
          background: variant.mainColor,
          border: variant.secondaryColor,
        }}
      >
        <Symbol style={{ background: variant.secondaryColor }}>
          {variant.symbol}
        </Symbol>
        <TextContainer>
          <Title>{variant.title}</Title>
          <Text>{text}</Text>
        </TextContainer>
        <CloseSymbol onClick={() => setOpen(false)}>X</CloseSymbol>
      </AlertContainer>
    );
  }
};

Alert.propTypes = {
  variant: PropTypes.object,
  text: PropTypes.string,
  open: PropTypes.bool,
  setOpen: PropTypes.func,
};

Alert.defaultProps = {};

export default Alert;
