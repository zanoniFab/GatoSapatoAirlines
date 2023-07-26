import { PropTypes } from "prop-types";
import { StyledPage } from "./styles";

function PageWrapper({ children }) {
  return <StyledPage>{children}</StyledPage>;
}

PageWrapper.propTypes = {
  children: PropTypes.node.isRequired,
};

export default PageWrapper;
