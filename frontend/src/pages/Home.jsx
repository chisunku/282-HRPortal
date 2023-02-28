import React, { Component } from 'react';
import Footer from '../components/Footer.jsx';
import Navbar from '../components/Navbar.jsx';
import Jumbotron from '../components/Jumbotron.jsx';
import Card from '../components/card.jsx';
import './Home.css';
// import { Card, CardContent, CardMedia, CardActionArea, Typography } from '@material-ui/core';

class Home extends Component {
  render() {
    return (
      <div>
        <Navbar />
        <Jumbotron title="Welcome"/>
        <div className='Home'>
          <Card />
        </div>
        <Footer />
      </div>
    );
  }
}

export default Home
