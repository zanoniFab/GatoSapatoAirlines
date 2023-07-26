import { forwardRef } from "react";
import PropTypes from "prop-types";
import { StyledInput, InputContainer, InputLabel } from "./styles";

const Input = forwardRef(({ labelText, helperText, inputName, ...props }, ref) => {
  return (
    <InputContainer className="container">
      {labelText && <InputLabel className="label" htmlFor={inputName}>{labelText}</InputLabel>}

      <StyledInput id={inputName} ref={ref} {...props} />

      {!!helperText && <span className="error">{helperText}</span>}
    </InputContainer>
  );
});

Input.propTypes = {
  labelText: PropTypes.string,
  helperText: PropTypes.string,
  inputName: PropTypes.string,
};

Input.displayName = "Input";

export default Input;
