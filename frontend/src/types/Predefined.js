import React, { useState, useEffect } from "react";
import Checkbox from "./Checkbox.js";
import "../bootstrap.min.css";

function Predefined(props) {
  const [checked, setCheck] = useState(props.initally);
  // console.log(
  //   " pred " +
  //     props.initally.length +
  //     " " +
  //     checked[0] +
  //     " " +
  //     props.initally[0]
  // );
  useEffect(() => {
    let flag = false;
    for (let i = 0; i < checked.length; i++) {
      if (checked[i] != props.initally[i]) {
        flag = true;
        break;
      }
    }
    if (flag == true) {
      setCheck(props.initally);
    }
  });
  let toggle = (e, boolVal) => {
    props.toggleParent(e.target.id, boolVal);
  };
  let selectAll = (e) => toggle(e, true);
  let selectNone = (e) => toggle(e, false);
  return (
    <div>
      <div className="row">
        <h5 className="col-3"></h5>
        <h5>{props.heading}</h5>
      </div>
      <div className="row">
        <div className="col-1"></div>
        <div className="col-5">
          <button
            id={props.heading}
            class="btn btn-secondary"
            onClick={(event) => {
              selectAll(event);
            }}
          >
            {" "}
            Select All{" "}
          </button>
          <input
            type="text"
            class="col-md-3 mb-3"
            id={"selectAllInput" + props.heading}
            placeholder="Input"
          ></input>
        </div>
        <div className="col-5">
          <button
            id={props.heading}
            class="btn btn-secondary"
            onClick={(event) => {
              selectNone(event);
            }}
          >
            Select None
          </button>
        </div>
        {/* <div className="col-3">
                    <button id={props.heading} class="btn btn-secondary" onClick={ (event) => { props.invertToggle(event); } }>Invert Selections</button>
                </div> */}
      </div>
      <br></br>
      {props.constructs.map((construct) => {
        return (
          <Checkbox
            heading={props.heading}
            label={construct}
            isSelected={checked[props.indexes[construct]]}
            onCheckboxChange={(event) => props.handleCheckboxChange(event)}
            key={construct}
          />
        );
      })}
    </div>
  );
}

export default Predefined;
