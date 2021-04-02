import React, { Component } from 'react';
import { Container, Breadcrumb, Card, Button } from 'react-bootstrap';
import SyntaxHighlighter from 'react-syntax-highlighter';
import { docco } from 'react-syntax-highlighter/dist/esm/styles/hljs';


const SOCKET_ENDPOINT = "ws://127.0.0.1:8081";


class RunSimulationScreen extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            simulation: {},
            run: {
                key: this.props.match.params.id
            },
            output: "Output from InterSCSimulator execution"
        };
    }

    async componentDidMount() {
        const simulation = await (await fetch("/simulations/"+this.props.match.params.id)).json();
        this.setState({simulation: simulation});
    }

    async serverConnect() {
        let socket = new WebSocket(SOCKET_ENDPOINT);
        socket.onopen = function (event) {
            socket.send("Aqui vai algum texto que o servidor esteja aguardando urgentemente!");
        };
        socket.onmessage = function (event) {
            console.log(event.data);
        };
    }

    handleRead = async () => {
        const eventSource = new EventSource('http://localhost:8080/simulations/run'); 
        eventSource.onopen = (event) => console.log('open', event); 
        eventSource.onmessage = (event) => {
        const data = event.data; 
        this.setState({output: this.state.output+"\n"+data}); 
        };
        eventSource.onerror = (event) => console.log('error', event);
    }

    handleRun = async () => {
        let simulation = this.state.run;
        this.handleRead();
        await fetch('/simulations/run', {
            method: 'POST',
            headers: {
            'Alocalhostccept': 'application/json',
            'Content-Type': 'application/json'
            },
            body: JSON.stringify(simulation),
        }).then(response => response.json()).then(data => {
            console.log(data)
        }).catch(error => {
            console.log(error);
        });
    }

    render() {
        return (
            <Container fluid>
                <div>
                    <h1>Run a Simulation</h1>
                </div>
                <Breadcrumb>
                    <Breadcrumb.Item href="">Home</Breadcrumb.Item>
                    <Breadcrumb.Item href="#">
                        Simulation
                    </Breadcrumb.Item>
                    <Breadcrumb.Item active>Run</Breadcrumb.Item>
                </Breadcrumb>
                    <div>
                        <Card>
                            <Card.Header>Simulation ID: {this.state.simulation.id}</Card.Header>
                            <Card.Body>
                                <Card.Title>{this.state.simulation.title}</Card.Title>
                                <Card.Text>
                                    {this.state.simulation.description}
                                </Card.Text>
                                <Button variant="success" onClick={this.handleRun}>Run</Button>
                                <Button variant="success" onClick={this.handleRead}>Read</Button>
                            </Card.Body>
                        </Card>
                        <br />
                        <Card>
                            <Card.Header>InterSCSimulator Console</Card.Header>
                            <Card.Body>
                                <div>
                                    <SyntaxHighlighter language="bash" style={docco}>
                                        {this.state.output}
                                    </SyntaxHighlighter>    
                                </div>
                            </Card.Body>
                        </Card>
                    </div>
            </Container>
        );
    }
};

export default RunSimulationScreen;