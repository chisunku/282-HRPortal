import React, { Component, useState, useEffect } from 'react';
import Footer from '../components/Footer.jsx';
import Jumbotron from '../components/Jumbotron.jsx';
import { service } from '../services.js';
// import Home from '../pages/Home.jsx';
import { useHistory } from 'react-router-dom';

function login(props) {

    const[error, setError] = useState('');
    const[empID, setEmpId] = useState('');
    const[password, setPassword] = useState('');
    const[isVerified, setIsVerified] = useState(false);
    const history = useHistory();

    const userLogin = async (e) => {
        localStorage.setItem('employee', empID);
        e.preventDefault();
        const res = await service.checkUser(empID, password).then(json => {return json});
        console.log(res);
        if(res === 'verified'){
            setIsVerified(true);
            history.push('/home', {data: empID});
        }
    };


    return(
        
        <div>
            {!isVerified?
            <div>
            <Jumbotron title={"LOGIN"} />
            <div>
                <h1>Login</h1>
                <form action="">
                  <input
                    name = "username"
                    type="number"
                    onChange={(e) => setEmpId(e.target.value)}
                    value={empID}
                    placeholder="Employee number"
                  />
                  <input
                    name = "password"
                    type="password"
                    onChange={(e) => setPassword(e.target.value)}
                    value={password}
                    placeholder="Password"
                  />
                  <p class='error'>{error}</p>
                  <button type="submit" onClick={userLogin}>
                    GO
                  </button>
                </form>
              </div>
            <Footer /></div>
            :<p>do nothing</p>}
        </div>
    )
}

export default login;