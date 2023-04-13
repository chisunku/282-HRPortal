import React, { Component, useState } from 'react';
import Footer from '../components/Footer.jsx';
import Navbar from '../components/Navbar.jsx';
import Jumbotron from '../components/Jumbotron.jsx';
import '../components/card.css';
import './Home.css';
import { useEffect } from 'react';
import { service } from '../services.js';
// import { Card, CardContent, CardMedia, CardActionArea, Typography } from '@material-ui/core';

function Home(props) {
  const [empId, setEmpId] = useState(props.location.state.data);
  const [title, setTitle] = useState('');
  const [lastName, setLastName] = useState('');
  const [desc, setDesc] = useState([]);
  console.log("employee id is:", empId);

  useEffect( () => {
    async function getEmpDetails(){
      await service.empInfo(empId).then(resp => resp.json()).then(data => {
        setTitle(data.firstName);
        setLastName(data.lastName);
      });
      // resp.json()).then(data => {console.log(data.empId);});
    }
    getEmpDetails();
  },[]);

  // render() {
    // console.log(props.location.state.data);
    return (
      <div>
        <Navbar />
        <Jumbotron title="Welcome"/>
        <div className='Home'>
        <div className='Card'> 
            <div className='upper-container'>
                <div className='image-container'>
                    <img src={'data:image/jpeg;base64,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'}
                     alt='no img'/>
                </div>
            </div>
            <div className='lower-container'>
                <h2>{empId}</h2>
                <h2>{title} {lastName}</h2>
                <h2>{desc}</h2>
            </div>
        </div>
        </div>
        <Footer />
      </div>
    );
  // }
}

export default Home
