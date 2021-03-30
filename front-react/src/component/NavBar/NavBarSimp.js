import React, { Component } from "react";
import {Navbar, Nav, NavDropdown} from 'react-bootstrap';

class NavBarSimp extends Component {

    render = () => {
        return (
            <Navbar bg="dark" variant="dark">
            <Navbar.Brand href="#home">SimpInterSCity</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="mr-auto">
                    <Nav.Link href="/scenario/add">Home</Nav.Link>
                    <NavDropdown title="Simulation" id="basic-nav-dropdown">
                        <NavDropdown.Item href="/simulation/add">Create</NavDropdown.Item>
                        <NavDropdown.Item href="/simulation/all">Show</NavDropdown.Item>
                    </NavDropdown>
                    <NavDropdown title="Scenario" id="basic-nav-dropdown">
                        <NavDropdown.Item href="/scenario/add">Create</NavDropdown.Item>
                        <NavDropdown.Item href="/scenario/all">Show</NavDropdown.Item>
                    </NavDropdown>
                    <NavDropdown title="Simulation Points" id="basic-nav-dropdown">
                        <NavDropdown.Item href="#action/3.1">Create</NavDropdown.Item>
                        <NavDropdown.Item href="#action/3.2">Show</NavDropdown.Item>
                        <NavDropdown.Divider />
                        <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
                    </NavDropdown>
                </Nav>
            </Navbar.Collapse>
            </Navbar>
        );
    };
};

export default NavBarSimp;