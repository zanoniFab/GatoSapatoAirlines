import PropTypes from "prop-types";
import { forwardRef } from "react";
import { SelectContainer, SelectLabel, StyledSelect } from "./styles";

const SelectDespacho = forwardRef(
  ({ labelText, helperText, inputName, data, ...props }, ref) => {
    return (
      <SelectContainer>
        <SelectLabel htmlFor={inputName}>{labelText}</SelectLabel>
        <StyledSelect id={labelText} name={inputName} {...props} ref={ref}>
          <option value="">selecione</option>
          {data.map((item) => (
            <option key={item.valor} value={item?.valor}>
              {item.label}
            </option>
          ))}
        </StyledSelect>
        {!!helperText && <span>Inválido</span>}
      </SelectContainer>
    );
  },
);

SelectDespacho.displayName = "SelectDespacho";

SelectDespacho.propTypes = {
  labelText: PropTypes.string,
  helperText: PropTypes.string,
  inputName: PropTypes.string.isRequired,
  data: PropTypes.array.isRequired,
};

SelectDespacho.defaultProps = {};

export default SelectDespacho;
