import React from 'react';


class Hardness extends React.Component {
    render() { 
        return (
          <div className="container-fluid">
            <hr></hr>  
            <div className="row justify-content-center">
              <h1 className="mt-4">OWL2Bench : Ontology Hardness </h1>
            </div>
            <hr></hr>
            <div className="container bg-light border border-info border-3 rounded mb-5">
              <div className="row m-5">
                <div className="col">
                  <input className="form-check-input big-checkbox" type="checkbox" value="time"/>
                  <label className="form-check-label ml-4 mt-1 h4" >
                    Time
                  </label>
                </div>
                <div className="col">
                  <select className="form-control" >
                    <option value="Easy">Easy</option>
                    <option value="Medium">Medium</option>
                    <option value="Hard">Hard</option>
                  </select>
                </div>
              </div>
              <div className="row m-5">
                <div className="col">
                  <input className="form-check-input big-checkbox" type="checkbox" value="time"/>
                  <label className="form-check-label ml-4 mt-1 h4" >
                    Memory
                  </label>
                </div>
                <div className="col">
                  <select className="form-control" >
                    <option value="Easy">Easy</option>
                    <option value="Medium">Medium</option>
                    <option value="Hard">Hard</option>
                  </select>
                </div>
              </div>
              <div className="row m-5">
                <div className="col">
                  <label className="ml-4 h4">Ontology Size</label>
                </div>
                <div className="col">
                  <input type="number" className="form-control" />
                </div>
              </div>
              <div className="row mr-5 mb-5">
                <div className="col text-right">
                  <button type="submit" className="btn btn-outline-info">Generate</button>
                </div>
              </div>
            </div>
          </div>
        );
    }
}
 
export default Hardness;
