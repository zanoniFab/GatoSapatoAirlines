import PropTypes from "prop-types";
import { forwardRef } from "react";
import { SelectContainer, SelectLabel, StyledSelect } from "./styles";

const SelectPassageiro = forwardRef(
  ({ labelText, helperText, inputName, data, ...props }, ref) => {
    return (
      <SelectContainer>
        <SelectLabel htmlFor={inputName}>{labelText}</SelectLabel>
        <StyledSelect id={labelText} name={inputName} {...props} ref={ref}>
          <option value="">selecione</option>
          {data.map((item) => (
            <option key={item.cpf} value={item?.cpf}>
              {item?.nome}
            </option>
          ))}
        </StyledSelect>
        {!!helperText && <span>Inválido</span>}
      </SelectContainer>
    );
  },
);

SelectPassageiro.displayName = "Select";

SelectPassageiro.propTypes = {
  labelText: PropTypes.string,
  helperText: PropTypes.string,
  inputName: PropTypes.string.isRequired,
  data: PropTypes.array.isRequired,
};

SelectPassageiro.defaultProps = {};

export default SelectPassageiro;
