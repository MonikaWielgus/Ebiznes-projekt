import './App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import MainPage from "./pages/MainPage";
import BookPage from "./pages/BookPage";
import CartPage from "./pages/CartPage";
import LoginPage from "./pages/LoginPage";
import LogoutPage from "./pages/LogoutPage";
import PaymentPage from "./pages/PaymentPage";

function App() {
  return (
      <Router>
        <Routes>
          <Route exact path="/" element={<MainPage/>}/>
            <Route exact path="/book/:id" element={<BookPage/>}/>
            <Route exact path="/cart" element={<CartPage/>}/>
            <Route exact path="/login" element={<LoginPage/>}/>
            <Route exact path="/logout" element={<LogoutPage/>}/>
            <Route exact path="/payment" element={<PaymentPage/>}/>
        </Routes>
      </Router>
  );
}
export default App;