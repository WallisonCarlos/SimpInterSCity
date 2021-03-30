import React, { Component } from 'react';
import { Form, Button } from "react-bootstrap";

class FormScenario extends Component {

    emptyItem = {
        name: '',
        description: '',
        simulationTime: 0
    }
    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChangeName = this.handleChangeName.bind(this);
        this.handleChangeDescription = this.handleChangeDescription.bind(this);
        this.handleChangeSimulationTime = this.handleChangeSimulationTime.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item}
        item[name] = value
        this.props.handleChange({item});
    }

    handleSubmit(event) {
        event.preventDefault();
        this.props.handleSubmit();
    }

    handleChangeName(event) {
        this.props.handleChangeName(event.target.value);
    }

    handleChangeDescription(event) {
        this.props.handleChangeDescription(event.target.value);
    }

    handleChangeSimulationTime(event) {
        this.props.handleChangeSimulationTime(event.target.value);
    }

    render() {
        return (
            <Form onSubmit={this.handleSubmit}>
                <Form.Group controlId="formBasicEmail">
                    <Form.Label>Name</Form.Label>
                    <Form.Control type="text" name="name" placeholder="Enter scenario name" onChange={event => this.handleChangeName(event)} />
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label>Description</Form.Label>
                    <Form.Control as="textarea" name="description" rows={3} placeholder="Enter scenario description..." onChange={event => this.handleChangeDescription(event)} />
                </Form.Group>
                <Form.Group controlId="formBasicEmail">
                    <Form.Label>Simulation Time</Form.Label>
                    <Form.Control type="number" name="simulationTime" placeholder="Enter simulation time" onChange={event => this.handleChangeSimulationTime(event)} />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        );
    };

};

export default FormScenario;