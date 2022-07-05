import './App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import MainPage from "./pages/MainPage";
import BookPage from "./pages/BookPage";
import CartPage from "./pages/CartPage";

function App() {
  return (
      <Router>
        <Routes>
          <Route exact path="/" element={<MainPage/>}/>
            <Route exact path="/book/:id" element={<BookPage/>}/>
            <Route exact path="/cart" element={<CartPage/>}/>
        </Routes>
      </Router>
  );
}

export default App;
