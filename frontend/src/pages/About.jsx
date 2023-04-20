import React, { useState, useEffect } from 'react';
import Footer from '../components/Footer.jsx';
import Navbar from '../components/Navbar.jsx';
import Jumbotron from '../components/Jumbotron.jsx';
import { service } from '../services.js';
import Table from '../components/table.jsx';

function About(props) {

  const[emps, setEmps] = useState([]);
  const[empID, setEmpId] = useState(localStorage.getItem('employee'));
  const[manager, setManager] = useState({});

  console.log("emp id in about ",empID);

  useEffect( () => {
    async function getEmpDetails(){
      await service.teamInfo(empID).then(resp => resp.json()).then(data => {
        // console.log("in about: ",data.employees);
        setEmps(data.employees);
        console.log("in about: ",typeof(data)," ",data," keys:",Object.keys(data));
      });
    }
    async function getManagerDetails(){
      await service.manager(empID).then(resp => resp.json()).then(data => {
        console.log("in about: ",data);
        setManager(data);
      });
    }
    getManagerDetails();
    getEmpDetails();
  },[]);

  const outlook = () => {
    console.log('outlook');
    window.location.href = "mailto:"+manager.managerFirstName+"."+manager.managerLastName+"@outlook.com";
  }

  const linkedin = () => {
    console.log('linkedin');
    window.open('https://www.linkedin.com/search/results/all/?keywords='+manager.managerFirstName+"%20"+manager.managerLastName+"&origin=GLOBAL_SEARCH_HEADER&sid=cVv", "_blank");
  }

  // render() {
    return (
      <div>
        <Navbar />
        <Jumbotron title="Team" subtitle="This page is all about me and my work!"/>
        <div className="container">
          {/* manager card */}
          <div className='Card'> 
            <div className='upper-container'>
              <h2 className='centerElement'>{manager.managerFirstName+" "+manager.managerLastName}</h2>
                <div className='image-container'>
                     {manager.managerGender=='F'?<img src={require('/Users/sunku/Desktop/SJSU/Sem-2/282/282-HRPortal/frontend/src/images/man.png')} />:
                  <img className='image' src={require('/Users/sunku/Desktop/SJSU/Sem-2/282/282-HRPortal/frontend/src/images/man.png')} /> }
                </div>
            </div>
            <div className='lower-container'>
                <h2 className='centerAlign'>Title: Manager</h2>
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
        <Table data={emps} />
        <Footer />
      </div>
    );
  // }
}

export default About
