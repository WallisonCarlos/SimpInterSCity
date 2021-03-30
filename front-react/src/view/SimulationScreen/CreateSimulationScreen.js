import React, { Component } from 'react';
import { Container, Breadcrumb, Alert, Collapse, Card, ListGroup } from 'react-bootstrap';
import FormSimulation from '../../component/FormSimulation/FormSimulation';

class CreateSimulationScreen extends Component {

    constructor(props) {
        super(props);
        this.state = {
            scenarioId: '',
            item: {},
            opened: false,
            message: {
                type: 'danger',
                text: 'You have an error',
                show: false
            },
            simulation: {
                title: "",
                description: "",
                scenario: ""
            },
            file: null,
            typeFile: ''
        };
        this.onSelectFile = this.onSelectFile.bind(this);
        this.onSelectTypeFile = this.onSelectScenario.bind(this);
    }

    handleChange = item => {
        this.setState({item: {...item}});
    };

    handleChangeTitle = title => {
        this.setState({simulation: {...this.state.simulation, ...{title: title}}});
    }

    handleChangeDescription = description => {
        this.setState({simulation: {...this.state.simulation, ...{description: description}}});
    }

    onSelectScenario = async scenario => {
        this.setState({simulation: {...this.state.simulation, ...{scenario: scenario}}});
        const item = await (await fetch("/scenarios/"+scenario)).json();
        this.setState({item: item});
        this.setState({opened: true});
    }

    onSelectFile(file) {
        this.setState({file: file});
    }

    onSelectScenario(type) {
        this.setState({typeFile: type});
    }

    getFileName(file) {
        return file.split("/")[2];
    }

    handleSubmit = async () => {
        let simulation = this.state.simulation;
        await fetch('/simulations', {
            method: 'POST',
            headers: {
            'Alocalhostccept': 'application/json',
            'Content-Type': 'application/json'
            },
            body: JSON.stringify(simulation),
        }).then(response => response.json()).then(data => {
            this.setState({message: {show: true, text: "Simulation created successful", type: "success"}})
        }).catch(error => {
            console.log(error);
        });
    }

    render() {
        return (
            <Container fluid>
                <h1>Create Simulation</h1>
                <Breadcrumb>
                    <Breadcrumb.Item href="">Home</Breadcrumb.Item>
                    <Breadcrumb.Item href="#">
                        Simulation
                    </Breadcrumb.Item>
                    <Breadcrumb.Item active>Create</Breadcrumb.Item>
                </Breadcrumb>
                <Collapse in={this.state.message.show}>
                    <div>
                        <Alert variant={this.state.message.type}>
                            {this.state.message.text}
                        </Alert>
                    </div>
                </Collapse>
                <FormSimulation handleChangeDescription={this.handleChangeDescription} handleSubmit={this.handleSubmit} handleChangeTitle={this.handleChangeTitle} onSelectScenario={this.onSelectScenario}/>
                <Collapse in={this.state.opened}>
                    <div>
                        <hr />
                        <Card>
                            <Card.Body>
                                <Card.Title>{this.state.item.name}</Card.Title>
                                <Card.Text>
                                    Description: {this.state.item.description}
                                </Card.Text>
                                <ListGroup variant="flush">
                                    <ListGroup.Item><b>Simulation Time: </b>{this.state.item.simulationTime}</ListGroup.Item>
                                    <ListGroup.Item><b>Map: </b>{(this.state.item.mapFile == null) ? "Not file uploaded" : this.state.item.mapFile}</ListGroup.Item>
                                    <ListGroup.Item><b>Trip: </b>{(this.state.item.tripsFile == null) ? "Not file uploaded" : this.state.item.tripsFile}</ListGroup.Item>
                                    <ListGroup.Item><b>Metro: </b>{(this.state.item.metroFile == null) ? "Not file uploaded" : this.state.item.metroFile}</ListGroup.Item>
                                    <ListGroup.Item><b>Bus: </b>{(this.state.item.busFile == null) ? "Not file uploaded" : this.state.item.busFile}</ListGroup.Item>
                                    <ListGroup.Item><b>Signals: </b>{(this.state.item.signals == null) ? "Not file uploaded" : this.state.item.signals}</ListGroup.Item>
                                    <ListGroup.Item><b>Digital Rails: </b>{(this.state.item.digitalRailsFile == null) ? "Not file uploaded" : this.state.item.digitalRailsFile}</ListGroup.Item>
                                    <ListGroup.Item><b>Empty Digital Rails: </b>{(this.state.item.emptyDigitalRailsFile == null) ? "Not file uploaded" : this.state.item.emptyDigitalRailsFile}</ListGroup.Item>
                                </ListGroup>
                            </Card.Body>
                        </Card>
                    </div>
                </Collapse>
            </Container>
        );
    };

};

export default CreateSimulationScreen;