import React, { Component } from 'react';
import { Form, Button } from "react-bootstrap";

class FormSimulation extends Component {

    constructor(props) {
        super(props);
        this.state = {
            scenarios: []
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChangeTitle = this.handleChangeTitle.bind(this);
        this.handleChangeDescription = this.handleChangeDescription.bind(this);
        this.onSelectScenario = this.onSelectScenario.bind(this);
    }

    async componentDidMount() {
        const scenarios = await (await fetch("/scenarios")).json();
        this.setState({scenarios: scenarios});
        console.log(scenarios)
    }

    handleSubmit(event) {
        event.preventDefault();
        this.props.handleSubmit();
    }

    handleChangeTitle(event) {
        this.props.handleChangeTitle(event.target.value);
    }

    handleChangeDescription(event) {
        this.props.handleChangeDescription(event.target.value);
    }

    onSelectScenario(event) {
        console.log(event.target.value);
        this.props.onSelectScenario(event.target.value);
    }
    
    render() {
        return (
            <Form onSubmit={this.handleSubmit}>
                <Form.Group controlId="formBasicEmail">
                    <Form.Label>Title</Form.Label>
                    <Form.Control type="text" name="name" placeholder="Enter scenario name" onChange={event => this.handleChangeTitle(event)} />
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label>Description</Form.Label>
                    <Form.Control as="textarea" name="description" rows={3} placeholder="Enter scenario description..." onChange={event => this.handleChangeDescription(event)} />
                </Form.Group>
                <Form.Group controlId="exampleForm.ControlSelect2">
                        <Form.Label>Select scenario</Form.Label>
                        <Form.Control as="select" onSelect={this.onSelectScenario} onChange={this.onSelectScenario}>
                            <option value="" >Select a scenario</option>
                            {
                                this.state.scenarios.map( scenario =>
                                    <option value={scenario.id} >{scenario.name}</option>
                                )
                            }
                        </Form.Control>
                    </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        );
    }

};

export default FormSimulation;