import { requirePropFactory } from '@material-ui/core';
import React, {useState} from 'react'
import './card.css'

function Card() {

    const[name, setName] = useState('Your Name'); 
    const[title, setTitle] = useState('Your Title'); 
    const[desc, setDesc] = useState('Your desc'); 

    return (
        <div className='Card'> 
            <div className='upper-container'>
                <div className='image-container'>
                    <img src={require('/Users/sunku/Desktop/SJSU/Sem-2/282/test_git/simple-react-router-demo/src/images/city-streets.jpeg')}
                     alt='no img'/>
                </div>
            </div>
            <div className='lower-container'>
                <h2>{name}</h2>
                <h2>{title}</h2>
                <h2>{desc}</h2>
            </div>
        </div>
    )
}

export default Card
