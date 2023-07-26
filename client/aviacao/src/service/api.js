import axios from "axios";

const BASE_URL = "http://localhost:8080/api";

export async function fetchPassageiros() {
  try {
    const resp = await axios.get(BASE_URL + "/passageiros");
    const dados = resp.data;
    return dados;
  } catch (error) {
    tratarErro(error);
  }
}

export async function fetchConfirmacao(eticket) {
  try {
    const resp = await axios.get(
      BASE_URL + `/passageiros/confirmacao/` + eticket,
    );
    const dados = resp.data;
    return dados;
  } catch (error) {
    tratarErro(error);
  }
}

export async function fetchPassageirosPendentes() {
  try {
    const resp = await axios.get(BASE_URL + `/passageiros/pendentes`);
    const dados = resp.data;
    return dados;
  } catch (error) {
    tratarErro(error);
  }
}

export async function fetchAssentos() {
  try {
    const resp = await axios.get(BASE_URL + `/assentos`);
    const dados = resp.data;
    return dados;
  } catch (error) {
    tratarErro(error);
  }
}

export async function createConfirmacao(confirmacao) {
  try {
    const resp = await axios.post(
      BASE_URL + `/passageiros/confirmacao`,
      confirmacao,
    );
    const dados = resp.data;
    return dados;
  } catch (error) {
    tratarErro(error);
  }
}

function tratarErro(error) {
  if (!error.response) throw new Error(error.message);
  if (error.response.status === 500) {
    throw new Error("Erro no servidor!");
  } else if (
    error.response.status >= 400 &&
    error.response.status <= 499 
  ) {
    throw new Error(error.response.data.mensagem);
  } else {
    throw new Error(error.response.data.mensagem);
  }
}
