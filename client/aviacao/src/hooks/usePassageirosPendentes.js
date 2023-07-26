import { useState, useEffect } from "react";
import { fetchPassageirosPendentes } from "../service/api";

const usePassageirosPendentes = () => {
  const [data, setData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchData = async () => {
    setIsLoading(true);
    try {
      const response = await fetchPassageirosPendentes();
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
    passageirosPendentes: data,
    isLoading,
    error,
    fetchData,
  };
};

export default usePassageirosPendentes;
