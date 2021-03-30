import React, { Component } from 'react';
import { Card, ListGroup } from 'react-bootstrap';

class ItemScenario extends Component {

    constructor(props) {
        super(props);
        this.state = {
            item: this.props.item
        };
    }

    componentDidMount() {
        this.setState({item: this.props.item});
    }

    render() {
        return (
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
        );
    }
};

export default ItemScenario;