import React, { useState } from "react";

const Checkbox = ({ label, isSelected, onCheckboxChange, heading }) => {
  const [textInput, setText] = useState("");
  const [checked, setCheck] = useState(label);
  //console.log(isSelected+" "+label);
  return (
    <div className="row">
      <label className="col-1"></label>
      <label className="col-5" id={heading}>
        <input
          className="m-2"
          type="checkbox"
          id={label}
          name={label}
          checked={isSelected}
          onChange={onCheckboxChange}
        />
        {label}
      </label>
      <label className="col-3"></label>
      <label className="col-2 mb-0">
        <input
          class="form-group col-12 mr-5 rounded"
          // class="col-md-12"
          type="text"
          id={label + "Text"}
          width="20px"
          height="20px"
          onChange={(event) => {
            //console.log("Input "+label+" "+document.getElementById(label).checked);
            //console.log("Input "+isNaN(document.getElementById(label+"Text").value));
            if (document.getElementById(label).checked) {
              if (!isNaN(document.getElementById(label + "Text").value)) {
                setText(event.target.value);
              } else {
                setText("");
                alert("Please Enter A Number");
              }
            } else {
              alert("First Tick Checkbox");
            }
          }}
        />
      </label>
    </div>
  );
};

export default Checkbox;
