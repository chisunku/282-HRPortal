import React, { useState } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import './App.css';
import Home from './pages/Home.jsx';
import About from './pages/About.jsx';
import Contact from './pages/Contact.jsx';
import Login from './pages/login.jsx';
//import Particles from "react-tsparticles";

function App() {
  // const[state, setState] = useState(null)
  // render() {
    return (
      <Router>
        <div>
          <Route exact path='/' component={Login} />
          <Route path="/home" component={Home}/>
          <Route path="/about" component={About}/>
          <Route path="/contact" component={Contact}/>
        </div>
      </Router>
    );
  // }
}

export default App;
