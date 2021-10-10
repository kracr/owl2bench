import React, { Component } from 'react'
import { Navbar, NavDropdown, Form, FormControl, Button, Nav } from 'react-bootstrap'
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";

import Home from './Home';
import App from '../App';
import VersionPicker from './VersionPicker';
// import About from './About'

export default class BenchNav extends Component {
    render() {
        return (
            <Router>
                <div>
                    <Navbar bg="dark" variant={"dark"} expand="lg">
                        <div className="container">
                        <Navbar.Brand as={Link} to="/"><h2>Owl2Bench</h2></Navbar.Brand>
                        <Navbar.Toggle aria-controls="navbarScroll" />
                        <Navbar.Collapse id="navbarScroll">
                            <Nav
                                className="ml-auto my-2 my-lg-0 float-right"
                                style={{ maxHeight: '100px' }}
                                navbarScroll
                            >
                                <Nav.Link as={Link} to="/owl2bench"><h4>Try OWL2Bench</h4></Nav.Link>
                                {/* <Nav.Link as={Link} to="/about">About</Nav.Link> */}
                                {/* <Nav.Link as={Link} to="/contact">Contact</Nav.Link> */}

                            </Nav>
                        </Navbar.Collapse>
                        </div>

                    </Navbar>
                </div>
                <div>
                    <Switch>
                        <Route path="/owl2bench/">
                            <VersionPicker/>
                        </Route>
                        <Route path="/">
                            <Home />
                        </Route>
                        <Route path="/owl2bench/v2">
                            <App />
                        </Route>
                    </Switch>
                </div>
            </Router>
        )
    }
}