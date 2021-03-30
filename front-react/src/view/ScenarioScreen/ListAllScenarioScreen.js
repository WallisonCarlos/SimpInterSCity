import React, { Component } from 'react';
import { Container, Breadcrumb, Card, ListGroup } from 'react-bootstrap';

class ListAllScenarioScreen extends Component {

    constructor(props) {
        super(props);
        this.state = {
            scenarios: []
        };
    }

    async componentDidMount() {
        const scenarios = await (await fetch("/scenarios")).json();
        this.setState({scenarios: scenarios});
        console.log(scenarios)
    }

    render() {
        return (
            <Container fluid>
                <div>
                    <h1>All Scenarios</h1>
                </div>
                <Breadcrumb>
                    <Breadcrumb.Item href="">Home</Breadcrumb.Item>
                    <Breadcrumb.Item href="#">
                        Scenario
                    </Breadcrumb.Item>
                    <Breadcrumb.Item active>All</Breadcrumb.Item>
                </Breadcrumb>
                {
                    this.state.scenarios.map(
                        scenario => 
                        <Card>
                            <Card.Body>
                                <Card.Title>{scenario.name}</Card.Title>
                                <Card.Text>
                                    Description: {scenario.description}
                                </Card.Text>
                                <ListGroup variant="flush">
                                    <ListGroup.Item><b>Simulation Time: </b>{scenario.simulationTime}</ListGroup.Item>
                                    <ListGroup.Item><b>Map: </b>{(scenario.mapFile == null) ? "Not file uploaded" : scenario.mapFile}</ListGroup.Item>
                                    <ListGroup.Item><b>Trip: </b>{(scenario.tripsFile == null) ? "Not file uploaded" : scenario.tripsFile}</ListGroup.Item>
                                    <ListGroup.Item><b>Metro: </b>{(scenario.metroFile == null) ? "Not file uploaded" : scenario.metroFile}</ListGroup.Item>
                                    <ListGroup.Item><b>Bus: </b>{(scenario.busFile == null) ? "Not file uploaded" : scenario.busFile}</ListGroup.Item>
                                    <ListGroup.Item><b>Signals: </b>{(scenario.signals == null) ? "Not file uploaded" : scenario.signals}</ListGroup.Item>
                                    <ListGroup.Item><b>Digital Rails: </b>{(scenario.digitalRailsFile == null) ? "Not file uploaded" : scenario.digitalRailsFile}</ListGroup.Item>
                                    <ListGroup.Item><b>Empty Digital Rails: </b>{(scenario.emptyDigitalRailsFile == null) ? "Not file uploaded" : scenario.emptyDigitalRailsFile}</ListGroup.Item>
                                </ListGroup>
                            </Card.Body>
                        </Card>
                    )
                }
            </Container>
        );
    }

};

export default ListAllScenarioScreen;