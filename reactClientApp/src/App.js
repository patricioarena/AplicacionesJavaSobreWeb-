import './App.css';

import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { AuthProvider } from '../src/Contexts/AuthContext';

import Header from './Components/Header';
import Nav from './Components/Nav'
import Footer from './Components/Footer';
import Signup from './Components/SignUp';
import Login from './Components/Login'
import Forgot from './Components/Forgot'
import Ribbon from "./Components/Ribbon/Ribbon";
import NewcomersHome from './Components/NewcomersHome';
import ClientHome from './Components/ClientHome';
import SellerHome from './Components/SellerHome';
import About from './Components/About'
import Product from './Components/Product'


function App() {

  return (
    <div>
      <AuthProvider>
          <Router>
            <Nav />
            <Ribbon />
            <div className="content-wrapper">
              <Header />
              <div className="content">
                <div className="container">

                  <Switch>
                    <Route exact path="/" component={NewcomersHome} />
                    <Route path="/newcomershome" component={NewcomersHome} />
                    <Route path="/clienthome" component={ClientHome} />
                    <Route path="/sellerhome" component={SellerHome} />
                    <Route exact path="/product" component={Product} />
                    <Route path="/product/:id" component={Product} />
                    <Route path="/about" component={About} />
                    <Route path="/signup" component={Signup} />
                    <Route path="/login" component={Login} />
                    <Route path="/forgot" component={Forgot} />
                  </Switch>

                </div>
              </div>
            </div>
            <Footer />
          </Router>
      </AuthProvider>
    </div>
  );

}

export default App;
