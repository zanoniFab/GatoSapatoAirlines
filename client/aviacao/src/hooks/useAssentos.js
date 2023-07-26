import { useState, useEffect } from "react";
import { fetchAssentos } from "../service/api";

const useAssentos = () => {
  const [data, setData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchAssentosData = async () => {
    setIsLoading(true);
    try {
      const response = await fetchAssentos();
      setData(response);
      setIsLoading(false);
    } catch (error) {
      setError(error.message);
    }
  };

  useEffect(() => {
    fetchAssentosData();
  }, []);

  return {
    assentos: data,
    isLoading,
    error,
    fetchAssentosData,
  };
};

export default useAssentos;
