import React, { Component } from "react";

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

  generateOntology(){
    let univ = 1
    let profile = "EL"
    let seed = null
    univ = document.getElementById("universities").value
    profile = document.getElementById("profile").value
    seed = document.getElementById("seed").value
    seed = seed ? seed : -1
    console.log(univ,profile,seed)

    const { ipcRenderer } = window.require('electron');

    let Data = {
        univ : univ,
        profile : profile,
        seed : seed
    };

    console.log(Data)

    // Send information to the main process
    // if a listener has been set, then the main process
    // will react to the request !
    ipcRenderer.send('generate-FixedTbox', Data);
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
                        <label>Number of Universities</label>
                        <input type="number" className="form-control"  id="universities" />
                    </div>
                    <div className="col">
                        <label>Owl 2 Profile</label>
                        <select className="form-control" id="profile" >
                            <option value="EL">OWL2 EL</option>
                            <option value="RL">OWL2 RL</option>
                            <option value="DL">OWL2 DL</option>
                        </select>
                    </div>
                    <div className="col">
                        <label >Seed Value <span className="text-muted">(Optional)</span></label>
                        <input type="number" className="form-control" id="seed" />
                    </div>
                    <div className="pt-3">
                        <button type="submit" className="btn btn-primary mt-3" onClick={this.generateOntology} > Submit </button>
                    </div>
                </div>
            </form>
        </div>
      </div>
    );
  }
}
