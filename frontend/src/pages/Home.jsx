import React, { Component, useState } from 'react';
import Footer from '../components/Footer.jsx';
import Navbar from '../components/Navbar.jsx';
import Jumbotron from '../components/Jumbotron.jsx';
import '../components/card.css';
import './Home.css';
import { useEffect } from 'react';
import { service } from '../services.js';
import { Card, CardContent, CardMedia, CardActionArea, Typography, Button } from '@material-ui/core';

function Home(props) {
  const [empId, setEmpId] = useState(localStorage.getItem('employee'));
  // console.log("employee: ",empId);
  const [desc, setDesc] = useState({});
  // console.log("employee id is:", empId);

  useEffect( () => {
    async function getEmpDetails(){
      await service.empInfo(empId).then(resp => resp.json()).then(data => {
        setDesc(data);
      });
    }
    getEmpDetails();
  },[]);

  const outlook = () => {
    console.log('outlook');
    window.location.href = "mailto:"+desc.firstName+"."+desc.lastName+"@outlook.com";
  }

  const linkedin = () => {
    console.log('linkedin');
    window.open('https://www.linkedin.com/search/results/all/?keywords='+desc.firstName+"%20"+desc.lastName+"&origin=GLOBAL_SEARCH_HEADER&sid=cVv", "_blank");
  }

    return (
      <div>
        <Navbar />
        <Jumbotron title="Welcome"/>
        <div className='Home'>
        <div className='Card'> 
            <div className='upper-container'>
              <h2 className='centerElement'>{desc.firstName+" "+desc.lastName}</h2>
                <div className='image-container'>
                     {desc.gender=='F'?<img src={require('/Users/sunku/Desktop/SJSU/Sem-2/282/282-HRPortal/frontend/src/images/man.png')} />:
                  <img className='image' src={require('/Users/sunku/Desktop/SJSU/Sem-2/282/282-HRPortal/frontend/src/images/man.png')} /> }
                </div>
            </div>
            <div className='lower-container'>
                <h2 className='centerAlign'>Title: {desc.title}</h2>
                <h3 className='centerAlign'>Manager: {desc.manager}</h3>
            </div>
            <div style={{padding:'0% 0% 0% 3%'}}>
              <h10>Contact methods</h10>
              <l1 className='contacts'>
                <ul><img width={20} height={20} onClick={() => outlook()} 
                  src={require('/Users/sunku/Desktop/SJSU/Sem-2/282/282-HRPortal/frontend/src/images/email.jpeg')} /></ul>
                <ul><img width={20} height={20} onClick={() => linkedin()} 
                  src={require('/Users/sunku/Desktop/SJSU/Sem-2/282/282-HRPortal/frontend/src/images/linkedin.png')} /></ul>
              </l1>
            </div>
        </div>
        </div>
        <Footer />
      </div>
    );
}

export default Home
