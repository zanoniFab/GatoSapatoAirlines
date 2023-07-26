import { useState } from "react";
import { fetchConfirmacao } from "../service/api";

const useConfirmacao = () => {
  const [data, setData] = useState();
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const getConfirmacao = async (eticket) => {
    if (!eticket) {
      return;
    }
    setError(null);
    setData(null);
    setIsLoading(true);
    try {
      const response = await fetchConfirmacao(eticket);
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
    getConfirmacao,
  };
};

export default useConfirmacao;
