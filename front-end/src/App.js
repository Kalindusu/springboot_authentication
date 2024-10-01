import { BrowserRouter,Routes,Route } from "react-router-dom";
import Register from "./Component/Register";
import Login from "./Component/Login";
 
import Home from "./Component/Home";


function App() {
  return (
    <div>

      <BrowserRouter>
            <Routes>
              <Route path="/home" element= { <Home/>} />
              <Route path="/register" element= { <Register/>} />
              <Route path="/" element= { <Login/>} />
            </Routes>
        </BrowserRouter>
      
    </div>
  );
}

export default App;