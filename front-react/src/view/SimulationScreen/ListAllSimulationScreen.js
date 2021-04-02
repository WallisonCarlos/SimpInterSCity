import React, { Component } from 'react';
import { Container, Breadcrumb, Card, ListGroup, Button } from 'react-bootstrap';

class ListAllSimulationScreen extends Component {

    constructor(props) {
        super(props);
        this.state = {
            simulations: []
        };
    }

    async componentDidMount() {
        const simulations = await (await fetch("/simulations")).json();
        this.setState({simulations: simulations});
    }

    goToRun(simulationId) {
        alert(simulationId);
    }

    render() {
        return (
            <Container fluid>
                <div>
                    <h1>All Simulations</h1>
                </div>
                <Breadcrumb>
                    <Breadcrumb.Item href="">Home</Breadcrumb.Item>
                    <Breadcrumb.Item href="#">
                        Simulation
                    </Breadcrumb.Item>
                    <Breadcrumb.Item active>All</Breadcrumb.Item>
                </Breadcrumb>
                {
                    this.state.simulations.map(
                        simulation => 
                        <Card>
                            <Card.Header>Simulation ID: {simulation.id}</Card.Header>
                            <Card.Body>
                                <Card.Title>{simulation.title}</Card.Title>
                                <Card.Text>
                                    {simulation.description}
                                </Card.Text>
                                <ListGroup variant="flush">
                                    <ListGroup.Item><b>Scenario ID: </b>{simulation.scenario.id}</ListGroup.Item>
                                    <ListGroup.Item><b>Scenario name: </b>{simulation.scenario.name}</ListGroup.Item>
                                    <ListGroup.Item><b>Scenario description: </b>{simulation.scenario.description}</ListGroup.Item>
                                </ListGroup>
                                <Button variant="success" href={"/simulation/run/"+simulation.id}>Edit</Button>
                                <Button variant="danger" href={"/simulation/run/"+simulation.id}>Delete</Button>
                                <Button variant="primary" href={"/simulation/run/"+simulation.id}>Go to Run</Button>
                            </Card.Body>
                        </Card>
                    )
                }
            </Container>
        );
    }

};

export default ListAllSimulationScreen;