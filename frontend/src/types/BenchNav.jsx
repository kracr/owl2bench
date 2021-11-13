import React, { Component } from 'react'
import { Navbar, Nav } from 'react-bootstrap'
import {
    BrowserRouter,
    Switch,
    Route,
    Link,
    HashRouter,
} from "react-router-dom";
import { createBrowserHistory, createHashHistory } from 'history';
import { isElectron } from './utils';
import Home from './Home';
import App from '../App';
import VersionPicker from './VersionPicker';

const { ipcRenderer } = window.require('electron')


// import About from './About'
export const history = isElectron()
  ? createHashHistory()
  : createBrowserHistory();
const Router = isElectron() ? HashRouter : BrowserRouter;
export default class BenchNav extends Component {
    render() {
        return (
            <Router history={history}>
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