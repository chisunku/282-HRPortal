import React, { Component } from 'react';
import './Jumbotron.css';
// import Particles from "react-tsparticles";

class Jumbotron extends Component {
  render() {
    return (
      <div className="jumbotron jumbotron-fluid">
        <div className="container">
          <h1 className="display-1">{this.props.title}</h1>
        </div>
      </div>

    );
  }
}

export default Jumbotron;
