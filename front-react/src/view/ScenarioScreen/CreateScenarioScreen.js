import React, { Component } from 'react';
import FormScenario from '../../component/FormScenario';
import AddFileScenario from '../../component/AddFileScenario';
import { Container, Breadcrumb, Alert, Collapse, Card, ListGroup } from 'react-bootstrap';

class CreateScenarioScreen extends Component {

    constructor(props) {
        super(props);
        this.state = {
            scenarioId: '',
            item: {
                name: "",
                description: "",
                simulationTime: 0
            },
            opened: false,
            message: {
                type: 'danger',
                text: 'You have an error',
                show: false
            },
            file: null,
            typeFile: ''
        };
        this.onSelectFile = this.onSelectFile.bind(this);
        this.onSelectTypeFile = this.onSelectTypeFile.bind(this);
    }

    handleChange = item => {
        this.setState({item: {...item}});
    };

    handleChangeName = name => {
        this.setState({item: {...this.state.item, ...{name: name}}});
    }

    handleChangeDescription = description => {
        this.setState({item: {...this.state.item, ...{description: description}}});
    }

    handleChangeSimulationTime = simulationTime => {
        this.setState({item: {...this.state.item, ...{simulationTime: simulationTime}}});
    }

    onSelectFile(file) {
        this.setState({file: file});
    }

    onSelectTypeFile(type) {
        this.setState({typeFile: type});
    }

    getFileName(file) {
        return file.split("/")[2];
    }

    handleSubmit = async () => {
        let item = this.state.item;
        await fetch('/scenarios', {
            method: 'POST',
            headers: {
            'Alocalhostccept': 'application/json',
            'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        }).then(response => response.json()).then(data => {
            this.setState({opened: true});
            this.setState({scenarioId: data.id});
            this.setState({item: data});
            this.setState({message: {show: true, text: "Scenario created successful", type: "success"}})
        }).catch(error => {
            console.log(error);
        });
    }

    onFileUploadHandler = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        console.log(this.state.file)
        formData.append('file', this.state.file);
        console.log(formData)
        await fetch('/scenarios/add-file/'+this.state.typeFile+'/'+this.state.scenarioId, {
            method: 'post',
            headers: {
                "Access-Control-Allow-Origin": "*",
                "mode": "no-cors"
            },
            body: formData
        }).then(response => response.json())
        .then( data => {
            this.setState({item: data});
            this.setState({message: {show: true, text: "File add successful", type: "success"}})
            console.log(data);
        });
    };

    render() {
        return (
            <Container fluid>
                <h1>Create Scenario</h1>
                <Breadcrumb>
                    <Breadcrumb.Item href="">Home</Breadcrumb.Item>
                    <Breadcrumb.Item href="#">
                        Scenario
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
                <FormScenario handleChangeDescription={this.handleChangeDescription} handleSubmit={this.handleSubmit} handleChangeName={this.handleChangeName} handleChangeSimulationTime={this.handleChangeSimulationTime}/>
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
                        <br />
                        <Collapse in={this.state.message.show}>
                            <div>
                                <Alert variant={this.state.message.type}>
                                    {this.state.message.text}
                                </Alert>
                            </div>
                        </Collapse>
                        <AddFileScenario onSelectTypeFile={this.onSelectTypeFile} onSelectFile={this.onSelectFile} onFileUploadHandler={this.onFileUploadHandler} />
                    </div>
                </Collapse>
            </Container>
        );
    };

};

export default CreateScenarioScreen;