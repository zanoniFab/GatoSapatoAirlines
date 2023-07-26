import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import useAssentos from "../../hooks/useAssentos";
import useCreateConfirmacao from "../../hooks/useCreateConfirmacao";
import usePassageirosPendentes from "../../hooks/usePassageirosPendentes";
import Button from "../../components/button";
import MapaAssentos from "../../components/mapaAssentos";
import SelectAssento from "../../components/select/selectAssento";
import SelectDespacho from "../../components/select/selectDespacho";
import SelectPassageiro from "../../components/select/selectPassageiro";
import PageWrapper from "../../components/pageWrapper/pageWrapper";
import { StyledForm } from "./styles";
import { useState } from "react";
import Alert from "../../components/alert/alert";
import { AlertError, AlertSuccess } from "../../components/alert/alertVariants";

const schema = yup.object().shape({
  cpf: yup.number().required("Campo obrigatório"),
  assento: yup.string().required("Campo obrigatório"),
  malasDespachadas: yup.boolean().required("Campo obrigatório"),
});

function Checkin() {
  const { postConfirmacao, error, confirmacao } = useCreateConfirmacao();
  const { passageirosPendentes, fetchData } = usePassageirosPendentes();
  const { assentos, fetchAssentosData } = useAssentos();
  const [selecionado, setSelecionado] = useState();
  const [openErro, setOpenErro] = useState(true);
  const [openSucesso, setOpenSucesso] = useState(true);
  const assentosDisponiveis = assentos.filter((assento) => !assento.ocupado);
  const opcoesDespacho = [
    { label: "sim", valor: true },
    { label: "não", valor: false },
  ];

  const onSubmit = (data) => {
    data.assento = selecionado;
    postConfirmacao(data)
      .then(fetchData)
      .then(fetchAssentosData)
      .then(() => setOpenSucesso(true))
      .then(() => reset)
      .catch(console.log("erro", error))
      .then(() => setOpenErro(true));
  };

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm({
    defaultValues: {
      assento: "selecione",
      cpf: "selecione",
      malasDespachadas: null,
    },
    resolver: yupResolver(schema),
  });

  return (
    <PageWrapper>
      <h1>Realizar Checkin</h1>
      {error ? (
        <Alert
          variant={AlertError}
          text={error}
          open={openErro}
          setOpen={setOpenErro}
        />
      ) : (
        confirmacao && (
          <Alert
            variant={AlertSuccess}
            text={"E-ticket " + confirmacao.eticket}
            open={openSucesso}
            setOpen={setOpenSucesso}
          />
        )
      )}
      <StyledForm onSubmit={handleSubmit(onSubmit)}>
        <SelectPassageiro
          id="cpf"
          labelText="Passageiro"
          placeholder="Selecione"
          inputName="cpf"
          data={passageirosPendentes}
          helperText={errors?.message}
          {...register("cpf")}
        ></SelectPassageiro>
        <SelectDespacho
          id="malasDespachadas"
          labelText="Despachar malas?"
          placeholder="Selecione"
          inputName="malasDespachadas"
          data={opcoesDespacho}
          helperText={errors?.message}
          {...register("malasDespachadas")}
        ></SelectDespacho>
        <SelectAssento
          id="assento"
          labelText="Assento"
          placeholder="Selecione"
          inputName="assento"
          data={assentosDisponiveis}
          selecionado={selecionado}
          setSelecionado={setSelecionado}
          helperText={errors?.message}
          onChange={(assento) => !assento.ocupado}
          {...register("assento")}
        ></SelectAssento>
        <Button type="submit">Confirmar</Button>
      </StyledForm>

      <MapaAssentos
        assentos={assentos}
        selecionado={selecionado}
        setSelecionado={setSelecionado}
      />
    </PageWrapper>
  );
}

export default Checkin;
