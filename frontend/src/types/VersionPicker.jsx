import React, { Component } from "react";
import { Button , Card } from "react-bootstrap";
import App from '../App'
import FixedTBox from "./FixedTBox"
import Hardness from "./Hardness"

export default class VersionPicker extends Component {
    state = {
        showv1: false,
        showv2: false,
        showv3: false,
    }
    
    toggleView(val){
        if (val===1){
            this.setState({
                showv1: !this.state.showv1,
                showv2: false,
                showv3: false,
            })
        }
        else if (val===2){
            this.setState({
                showv1: false,
                showv2: !this.state.showv2,
                showv3: false,
            })
        }
        else if (val===3){
            this.setState({
                showv1: false,
                showv2: false,
                showv3: !this.state.showv3,
            })
        }
    }

    render() {
        return ( 
            <div>
                <div className="container" >
                    <div className="row mt-5">
                        <div className="col-4">
                            <Card>
                                <Card.Header className="bg-cyan"><h5>Owl2Bench : Fixed TBox </h5></Card.Header>
                                <Card.Body>
                                    <Card.Title>About</Card.Title>
                                    <Card.Text>
                                    With supporting text below as a natural lead-in to additional content.
                                    </Card.Text>
                                    <Button onClick={() => this.toggleView(1)} variant="info" >
                                        OWL2Bench : Fixed TBox
                                    </Button>
                                </Card.Body>
                            </Card>
                        </div>
                        <div className="col-4">
                            <Card>
                                <Card.Header className="bg-cyan"> <h5>Owl2Bench : Variable TBox </h5></Card.Header>
                                <Card.Body>
                                    <Card.Title>About</Card.Title>
                                    <Card.Text>
                                    With supporting text below as a natural lead-in to additional content.
                                    </Card.Text>
                                    <Button onClick={() => this.toggleView(2)} variant="info">
                                        OWL2Bench : Variable TBox
                                    </Button>
                                </Card.Body>
                            </Card>
                        </div>
                        <div className="col-4">
                            <Card>
                                <Card.Header className="bg-cyan"> <h5>Owl2Bench : Hardness </h5></Card.Header>
                                <Card.Body>
                                    <Card.Title>About</Card.Title>
                                    <Card.Text>
                                    With supporting text below as a natural lead-in to additional content.
                                    </Card.Text>
                                    <Button onClick={() => this.toggleView(3)} variant="info">
                                        OWL2Bench : Hardness
                                    </Button>
                                </Card.Body>
                            </Card>
                        </div>
                    </div>
                </div>
                {(this.state.showv1) ? <FixedTBox /> : ' ' }
                {(this.state.showv2) ? <App /> : ' ' }
                {(this.state.showv3) ? <Hardness /> : ' ' }
            </div>
        );        
    }
}