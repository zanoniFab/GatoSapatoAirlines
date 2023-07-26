import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import App from "./App.jsx";
import Passageiros from "./pages/passageiros/passageiros";
import Eticket from "./pages/eticket/eticket";
import Checkin from "./pages/checkin/checkin";
import "./index.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<App />}>
        <Route path="/" element={<Navigate to="/passageiros" />} />
        <Route path="/passageiros" element={<Passageiros />} />
        <Route path="/eticket" element={<Eticket />} />
        <Route path="/checkin" element={<Checkin />} />
      </Route>
    </Routes>
  </BrowserRouter>,
);
