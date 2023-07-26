import PropTypes from "prop-types";
import { forwardRef } from "react";
import { SelectContainer, SelectLabel, StyledSelect } from "./styles";

const SelectAssento = forwardRef(
  ({ labelText, helperText, inputName, data, selecionado, setSelecionado, ...props }, ref) => {
    return (
      <SelectContainer>
        <SelectLabel htmlFor={inputName}>{labelText}</SelectLabel>
        <StyledSelect id={labelText} name={inputName} {...props} ref={ref} value={selecionado} onChange = {(event) => setSelecionado(event.target.value)}>
            <option value="">selecione</option>
          {data.map((item) => (
            <option key={item.assento} value={item?.assento}>
              {item?.assento}
            </option>
          ))}
        </StyledSelect>
        {!!helperText && <span>Inválido</span>}
      </SelectContainer>
    );
  },
);

SelectAssento.displayName = "SelectAssento";

SelectAssento.propTypes = {
  labelText: PropTypes.string,
  helperText: PropTypes.string,
  inputName: PropTypes.string.isRequired,
  data: PropTypes.array.isRequired,
  selecionado: PropTypes.string,
  setSelecionado: PropTypes.func,
};

SelectAssento.defaultProps = {};

export default SelectAssento;
