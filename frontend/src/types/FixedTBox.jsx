import React, { Component } from "react";
import { Button, Card } from "react-bootstrap";

export default class FixedTBox extends Component {
  state = {
    showv1: false,
    showv2: false,
  };

  toggleView(val) {
    if (val === 2) {
      this.setState({
        showv2: !this.state.showv2,
        showv1: false,
      });
    } else if (val === 1) {
      this.setState({
        showv1: !this.state.showv1,
        showv2: false,
      });
    }
  }

  render() {
    return (
      <div className="container-fluid">
        <hr></hr>  
        <div className="row justify-content-center">
          <h1 className="mt-4">OWL2Bench : Fixed TBox </h1>
        </div>
        <hr></hr>
        <hr></hr>
        <div className="container">
            <form className="form-horizontal">
                <div className="form-row">
                    <div className="col">
                        <label for="universities">Number of Universities</label>
                        <input type="number" className="form-control"  id="universities" />
                    </div>
                    <div className="col">
                        <label>Owl 2 Profile</label>
                        <select className="form-control" >
                            <option value="EL">OWL2 EL</option>
                            <option value="RL">OWL2 RL</option>
                            <option value="DL">OWL2 DL</option>
                        </select>
                    </div>
                    <div className="col">
                        <label for="seed">Seed Value <span className="text-muted">(Optional)</span></label>
                        <input type="number" className="form-control" />
                    </div>
                    <div className="pt-3">
                        <button type="submit" className="btn btn-primary mt-3"> Submit </button>
                    </div>
                </div>
            </form>
        </div>
      </div>
    );
  }
}
