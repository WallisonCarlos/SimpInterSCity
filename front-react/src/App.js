import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import {Switch, Route} from 'react-router-dom';
import NavBar from './component/NavBar';

class App extends Component {
  render = () => {  
    return (
      <div>
        <NavBar />
        <div className="container mt-3">
          <Switch>
            
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
