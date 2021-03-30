import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import {Switch, Route} from 'react-router-dom';
import NavBarSimp from './component/NavBar';
import CreateScenarioScreen from './view/ScenarioScreen/CreateScenarioScreen';
import ListAllScenarioScreen from './view/ScenarioScreen/ListAllScenarioScreen';
import CreateSimulationScreen from './view/SimulationScreen/CreateSimulationScreen';
import ListAllSimulationScreen from './view/SimulationScreen/ListAllSimulationScreen';

class App extends Component {
  render = () => {  
    return (
      <div>
        <NavBarSimp />
        <div className="container mt-3">
          <Switch>
            <Route exact path="/scenario/add" component={CreateScenarioScreen} />
            <Route exact path="/scenario/all" component={ListAllScenarioScreen} />
            <Route exact path="/simulation/add" component={CreateSimulationScreen} />
            <Route exact path="/simulation/all" component={ListAllSimulationScreen} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
