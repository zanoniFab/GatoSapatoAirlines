import { useState } from "react";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { StyledForm } from "./styles";
import Button from "../../components/button";
import Input from "../../components/input";
import Bilhete from "../../components/bilhete";
import useConfirmacao from "../../hooks/useConfirmacao";
import PageWrapper from "../../components/pageWrapper/pageWrapper";
import Alert from "../../components/alert/alert";
import { AlertError } from "../../components/alert/alertVariants";

const schema = yup.object().shape({
  eticket: yup.string(),
});

function Eticket() {
  const { confirmacao, getConfirmacao, error } = useConfirmacao();
  const [openErro, setOpenErro] = useState(true);
  
  const onSubmit = (data) => {
    getConfirmacao(data.eticket)
      .then(reset)
      .catch(console.log(error));
  };

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm({
    resolver: yupResolver(schema),
  });

  return (
    <PageWrapper>
      <h1>Bilhete de Confirmação de Voo</h1>
      <StyledForm onSubmit={handleSubmit(onSubmit)}>
        <Input
          type="string"
          labelText="E-ticket"
          inputName="eticket"
          helperText={errors?.eticket?.message}
          {...register("eticket")}
          placeholder="Digite seu e-ticket"
        ></Input>
        <Button type="submit">
          Consultar
        </Button>
      </StyledForm>
      {error && <Alert variant={AlertError} text={error} open={openErro} setOpen={setOpenErro} />}
      {confirmacao && <Bilhete dados={confirmacao} />}
    </PageWrapper>
  );
}

export default Eticket;
