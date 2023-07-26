import { useState } from "react";
import { createConfirmacao, fetchPassageiros } from "../service/api";

const useCreateConfirmacao = () => {
  const [data, setData] = useState();
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const postConfirmacao = async (confirmacao) => {
    if (!confirmacao) {
      return;
    }
    setIsLoading(true);
    setError(null);
    setData(null);
    try {
      const response = await createConfirmacao(confirmacao);
      fetchPassageiros();
      setData(response);
      setIsLoading(false);
    } catch (error) {
      setError(error.message);
    }
  };

  return {
    confirmacao: data,
    isLoading,
    error,
    postConfirmacao,
  };
};

export default useCreateConfirmacao;
