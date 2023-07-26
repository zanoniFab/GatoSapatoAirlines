import { useState, useEffect } from "react";
import { fetchPassageiros } from "../service/api";

const useListaPassageiros = () => {
  const [data, setData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchData = async () => {
    setIsLoading(true);
    try {
      const response = await fetchPassageiros();
      setData(response);
      setIsLoading(false);
    } catch (error) {
      setError(error.message);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return {
    ListaPassageiros: data,
    isLoading,
    error,
    fetchData,
  };
};

export default useListaPassageiros;
