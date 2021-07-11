/* eslint-disable */
import React, { useState } from "react";
import Predefined from "./types/Predefined.js";
import axios from "axios";
import "./bootstrap.min.css";

//const constructs1 = ['owl:Class','owl:complementOf','owl:intersectionOf','owl:Nothing','owl:oneOf','owl:Thing','owl:unionOf'];
const constructs1 = [
  "OwlClass",
  "ObjectComplement",
  "ObjectIntersection",
  "ObjectOneOf",
  "ObjectUnionOf",
];
let initally1 = [];
const indexes1 = new Map();
for (let i = 0; i < constructs1.length; i++) {
  initally1[i] = false;
  indexes1[constructs1[i]] = i;
  //console.log(constructs1[i]+" "+initally1[i]+" "+indexes1[constructs1[i]]);
}
const h1 = "Predefined Classes, Boolean Connectives and Enumerations";

//const constructs2 = ['owl:AllDisjointClasses','owl:disjointUnionOf','owl:disjointWith','owl:equivalentClass','rdf:subClassOf'];
//"AllDisjointClasses",
const constructs2 = [
  "DisjointUnion",
  "DisjointWith",
  "EquivalentClass",
  "RdfsSubClassOf",
];
let initally2 = [];
const indexes2 = new Map();
for (let i = 0; i < constructs2.length; i++) {
  initally2[i] = false;
  indexes2[constructs2[i]] = i;
}
const h2 = "Class Expression Axioms";

//  "AllDisjointObjectProperties",
// const constructs3 = ['owl:AllDisjointProperties (Object)','owl:AsmmetricProperty','owl:bottomObjectProperty','owl:equivalentProperty','owl:FunctionalProperty (Object)',
// 'owl:InverseFunctionalProperty','owl:inverseOf','owl:IrreflexiveProperty','owl:ObjectProperty','owl:PropertyChainAxiom',
// 'owl:propertyDisjointWith (Object)','owl:ReflexiveProperty','owl:SymmetricProperty','owl:topObjectProperty',
// 'owl:TransitiveProperty','rdfs:domain (Object)','rdfs:range (Object)','rdfs:subPropertyOf (Object)'];
//"AllDisjointObjectProperties","BottomObjectProperty","ObjectProperty", "PropertyChainAxiom","TopObjectProperty",
const constructs3 = [
  "AsymmetricProperty",
  "EquivalentObjectProperty",
  "FunctionalObjectProperty",
  "InverseFunctionalProperty",
  "InverseOfProperty",
  "IrreflexiveProperty",
  "ObjectPropertyDisjointWith",
  "ReflexiveProperty",
  "SymmetricProperty",
  "TransitiveProperty",
  "RdfsObjectDomain",
  "RdfsObjectRange",
  "PropertyChainAxiom",
  "RdfsObjectSubPropertyOf",
  "ObjectProperty",
];
let initally3 = [];
const indexes3 = new Map();
for (let i = 0; i < constructs3.length; i++) {
  initally3[i] = false;
  indexes3[constructs3[i]] = i;
}
const h3 = "Object Properties and Axioms";

// const constructs4 = ['owl:allValuesFrom (Object)','owl:cardinality (Object)','owl:hasSelf','owl:hasValue (Object)','owl:maxCardinality (Object)',
// 'owl:maxQualifiedCardinality (Object)','owl:minCardinality (Object)','owl:qualifiedCardinality (Object)','owl:someValuesFrom (Object)'];
//"ObjectAllValuesFrom", "ObjectCardinality", "HasSelf", "ObjectHasValue", "ObjectMaxCardinality","ObjectMaxQualifiedCardinality",  "ObjectMinCardinality",
const constructs4 = [
  "ObjectAllValuesFrom",
  "ObjectHasSelf",
  "ObjectHasValue",
  "ObjectSomeValuesFrom",
  "ObjectQualifiedCardinality",
  "ObjectMaxQualifiedCardinality",
  "ObjectMinQualifiedCardinality",
];
let initally4 = [];
const indexes4 = new Map();
for (let i = 0; i < constructs4.length; i++) {
  initally4[i] = false;
  indexes4[constructs4[i]] = i;
}
const h4 = "Object Properties Restrictions";

//  "AllDisjointDataProperties",
// const constructs5 = ['owl:AllDisjointProperties (Data)','owl:bottomDataProperty','owl:DatatypeProperty'
// ,'owl:equivalentProperty','owl:FunctionalProperty (Data)','owl:propertyDisjointWith (Data)',
// 'owl:topDataProperty','rdfs:domain (Data)','rdfs:range (Data)','rdfs:subPropertyOf (Data)'];
//"BottomDataProperty","DatatypeProperty","FunctionalDataProperty","DataPropertyDisjointWith","TopDataProperty",
const constructs5 = [
  "EquivalentDataProperty",
  "FunctionalDataProperty",
  "DataPropertyDisjointWith",
  "RdfsDataDomain",
  "RdfsDataRange",
  "RdfsDataSubPropertyOf",
];
let initally5 = [];
const indexes5 = new Map();
for (let i = 0; i < constructs5.length; i++) {
  initally5[i] = false;
  indexes5[constructs5[i]] = i;
}
const h5 = "Data Properties and Axioms";

// const constructs6 = ['owl:allValuesFrom (Data)','owl:cardinality (Data)','owl:hasValue (Data)','owl:maxCardinality (Data)',
// 'owl:maxQualifiedCardinality (Data)','owl:minCardinality (Data)','owl:minQualifiedCardinality (Data)'
// ,'owl:qualifiedCardinality (Data)','owl:someValuesFrom (Data)'];
//"DataAllValues","DataCardinality","DataHasValue","DataMaxCardinality", "DataMaxQualifiedCardinality", "DataMinCardinality", "DataMinQualifiedCardinality","DataQualifiedCardinality",  "DataSomeValuesFrom",
const constructs6 = [
  "DataAllValues",
  "DataHasValue",
  "DataMaxQualifiedCardinality",
  "DataMinQualifiedCardinality",
  "DataQualifiedCardinality",
  "DataSomeValuesFrom",
];
let initally6 = [];
const indexes6 = new Map();
for (let i = 0; i < constructs6.length; i++) {
  initally6[i] = false;
  indexes6[constructs6[i]] = i;
}
const h6 = "Data Properties Restrictions";

// const constructs7 = ['DatatypeRestriction','owl:complementOf','owl:intersectionOf'
// ,'owl:oneOf','owl:unionOf','rdfs:Datatype'];
//  "DataComplementOf","DataIntersectionOf","DataOneOf","DataUnionOf","DatatypeRestriction"
const constructs7 = [
  "DataComplementOf",
  "DataIntersectionOf",
  "DataOneOf",
  "DataUnionOf",
];
let initally7 = [];
const indexes7 = new Map();
for (let i = 0; i < constructs7.length; i++) {
  initally7[i] = false;
  indexes7[constructs7[i]] = i;
}
const h7 = "Data Ranges";

const constructs10 = ["HasKey", "AssertionAxioms"];
// const constructs10 = ["HasKey", "AssertionAxiomCount"];
let initally10 = [];
const indexes10 = new Map();
for (let i = 0; i < constructs10.length; i++) {
  initally10[i] = false;
  indexes10[constructs10[i]] = i;
}
const h10 = "Assertions";

const constructs8 = [
  "owl:rational",
  "owl:real",
  "rdf:langString",
  "rdf:PlainLiteral",
  "rdf:XMLLiteral",
  "rdfs:Literal",
  "xsd:anyURI",
  "xsd:base64Binary",
  "xsd:boolean",
  "xsd:byte",
  "xsd:dateTime",
  "xsd:dateTimeStamp",
  "xsd:decimal",
  "xsd:double",
  "xsd:float",
  "xsd:hexBinary",
  "xsd:int",
  "xsd:integer",
  "xsd:language",
  "xsd:long",
  "xsd:Name",
  "xsd:NCName",
  "xsd:negativeInteger",
  "xsd:NMTOKEN",
  "xsd:nonNegativeInteger",
  "xsd:nonPositiveInteger",
  "xsd:normalizeString",
  "xsd:positiveInteger",
  "xsd:short",
  "xsd:string",
  "xsd:token",
  "xsd:unsignedByte",
  "xsd:unsignedInt",
  "xsd:unsignedLong",
  "xsd:unsignedShort",
];
let initally8 = [];
const indexes8 = new Map();
for (let i = 0; i < constructs8.length; i++) {
  initally8[i] = false;
  indexes8[constructs8[i]] = i;
}
const h8 = "Datatype Maps";

const constructs9 = ["Anonymous Individual", "owl:NamedIndividual"];
let initally9 = [];
const indexes9 = new Map();
for (let i = 0; i < constructs9.length; i++) {
  initally9[i] = false;
  indexes9[constructs9[i]] = i;
}
const h9 = "Individuals";
//only using hasKey
//const constructs10 = ['owl:AllDifferent','owl:hasKey','owl:NegativePropertyAssertion(on Data Property)',
//owl:NegativePropertyAssertion(on Object Property)','owl:sameAs'];

const constructs11 = [
  "owl:AnnotationProperty",
  "owl:backwardCompactibleWith",
  "owl:DeprecatedClass",
  "owl:DeprecatedProperty",
  "owl:imports",
  "owl:imcompatibleWith",
  "owl:priorVersion",
  "owl:versionInfo",
  "owl:versionIri",
  "rdfs:comment(on Class&Property multilingual)",
  "rdfs:comment(on Class&Property)",
  "rdfs:comment(on Ontology)",
  "rdfs:isDefinedBy",
  "rdfs:label(on Class&Property, multilingual)",
  "rdfs:label(on Class&Property)",
  "rdfs:label(on Ontology)",
  "rdfs:seeAlso",
];
let initally11 = [];
const indexes11 = new Map();
for (let i = 0; i < constructs11.length; i++) {
  initally11[i] = false;
  indexes11[constructs11[i]] = i;
}
const h11 = "OWL Annotations";

const headers = [h1, h2, h3, h4, h5, h6, h7, h10];
const headingInd = new Map();
for (let i = 0; i < headers.length; i++) {
  headingInd[headers[i]] = i;
}

const overallconstructs = [
  constructs1,
  constructs2,
  constructs3,
  constructs4,
  constructs5,
  constructs6,
  constructs7,
  constructs10,
];

let total = 0;
for (let i = 0; i < overallconstructs.length; i++) {
  total = total + overallconstructs[i].length;
}
const intValues = new Array(total).fill(0);

const but1 = [
  "owl:Class",
  "owl:Nothing",
  "owl:Thing",
  "owl:equivalentClass",
  "rdfs:subClassOf",
  "owl:equivalentProperty",
  "owl:FunctionalProperty",
  "owl:InverseFunctionalProperty",
  "owl:ObjectProperty",
  "owl:SymmetricProperty",
  "owl:TransitiveProperty",
  "rdfs:domain",
  "rdfs:range",
  "rdfs:subPropertyOf",
  "owl:allValuesFrom",
  "owl:maxCardinality",
  "owl:minCardinality",
  "owl:someValuesFrom",
  "owl:DatatypeProperty",
  "owl:AllDifferent",
  "owl:sameAs",
  "owl:AnnotationProperty",
  "owl:DeprecatedClass",
  "owl:versionInfo",
  "rdfs:comment(on Class&Property)",
  "rdfs:comment(on Ontology)",
  "rdfs:label(on Class&Property)",
  "rdfs:label(on Ontology)",
];
const lite = new Map();
for (let i = 0; i < but1.length; i++) {
  if (!lite.has(but1[i])) {
    lite.set(but1[i], i);
  }
}

const but2 = [
  "owl:Class",
  "owl:complementOf",
  "owl:intersectionOf",
  "owl:Nothing",
  "owl:oneOf",
  "owl:Thing",
  "owl:unionOf",
  "owl:disjointWith",
  "owl:equivalentClass",
  "rdfs:subClassOf",
  "owl:equivalentProperty",
  "owl:FunctionalProperty",
  "owl:InverseFunctionalProperty",
  "owl:inverseOf",
  "owl:ObjectProperty",
  "owl:SymmetricProperty",
  "owl:TransitiveProperty",
  "rdfs:domain",
  "rdfs:range",
  "rdfs:subPropertyOf",
  "owl:allValuesFrom",
  "owl:cardinality",
  "owl:hasValue",
  "owl:maxCardinality",
  "owl:minCardinality",
  "owl:someValuesFrom",
  "owl:DataTypeProperty",
  "owl:oneOf",
  "owl:AllDifferent",
  "owl:sameAs",
  "owl:AnnotationProperty",
  "owl:DeprecatedProperty",
  "owl:versioanInfo",
  "rdfs:comment(on Class&Property)",
  "rdfs:comment(on Ontology)",
  "rdfs:label(on Class&Property)",
  "rdfs:label(on Ontology)",
];
const dl = new Map();
for (let i = 0; i < but2.length; i++) {
  if (!dl.has(but2[i])) {
    dl.set(but2[i], i);
  }
}

const but3 = [
  "owl:Class",
  "owl:intersectionOf",
  "owl:Nothing",
  "owl:Thing",
  "owl:AlldisjointClasses",
  "owl:disjointWith",
  "owl:equivalentClass",
  "rdfs:subClassOf",
  "owl:equivalentProperty",
  "owl:ObjectProperty",
  "owl:PropertyChainAxiom",
  "owl:ReflexiveProperty",
  "owl:TransitiveProperty",
  "rdfs:domain",
  "rdfs:range",
  "rdfs:subPropertyOf",
  "owl:hasSelf",
  "owl:hasValue",
  "owl:someValuesFrom",
  "owl:DatatypeProperty",
  "owl:equivalentProperty",
  "owl:FunctionalProperty",
  "owl:hasValue",
  "owl:someValuesFrom",
  "owl:intersectionaOf",
  "owl:oneOf",
  "rdfs:Datatype",
  "owl:rational",
  "owl:real",
  "rdf:PlainLiteral",
  "rdf:XMLLiteral",
  "xsd:anyURI",
  "xsd:base64Binary",
  "xsd:dateTime",
  "xsd:dateTimeStamp",
  "xsd:decimal",
  "xsd:hexBinary",
  "xsd:integer",
  "xsd:Name",
  "xsd:NMTOKEN",
  "xsd:nonNegativeInteger",
  "xsd:normalizedString",
  "xsd:string",
  "xsd:token",
  "owl:NamedIndividual",
  "owl:AllDifferent",
  "owl:hasKey",
  "owl:NegativePropertyAssertion(on Data Property)",
  "owl:NegativePropertyAssertion(on Object Property)",
  "owl:sameAs",
  "owl:AnnotationProperty",
  "owl:DeprecatedClass",
  "owl:DeprecatedProperty",
];
const el = new Map();
for (let i = 0; i < but3.length; i++) {
  if (!el.has(but3[i])) {
    el.set(but3[i], i);
  }
}

const but4 = [
  "owl:Class",
  "owl:complementOf",
  "owl:intersectionOf",
  "owl:Nothing",
  "owl:Thing",
  "owl:AllDisjointClasses",
  "owl:disjointWith",
  "owl:equivalentClass",
  "rdfs:subClassOf",
  "owl:AllDisjointProperties",
  "owl:AsymmetricProperty",
  "owl:equivalentProperty",
  "owl:inverseOf",
  "owl:IrreflexiveProperty",
  "owl:ObjectProperty",
  "owl:propertyDisjointWith",
  "owl:ReflexiveProperty",
  "rdfs:domain",
  "rdfs:range",
  "rdfs:subPropertyOf",
  "owl:someValuesFrom",
  "owl:AllDisjointProperties",
  "owl:DatatypeProperty",
  "owl:equivalentProperty",
  "owl:propertyDisjointWith",
  "owl:someValuesFrom",
  "owl:intersectionOf",
  "rdfs:Datatype",
  "owl:rational",
  "owl:real",
  "rdf:PlainLiteral",
  "rdf:XMLLiteral",
  "rdfs:Literal",
  "xsd:anyURI",
  "xsd:base64Binary",
  "xsd:dateTime",
  "xsd:dateTimeStamp",
  "xsd:decimal",
  "xsd:hexBinary",
  "xsd:integer",
  "xsd:Name",
  "xsd:NCName",
  "xsd:NMTOKEN",
  "xsd:nonNegativeInteger",
  "xsd:normalizedString",
  "xsd:string",
  "xsd:token",
  "owl:NamedIndividual",
  "owl:AllDifferent",
  "owl:AnnotationProperty",
  "owl:DeprecatedClass",
  "owl:DeprecatedProperty",
];
const ql = new Map();
for (let i = 0; i < but4.length; i++) {
  if (!ql.has(but4[i])) {
    ql.set(but4[i], i);
  }
}

const but5 = [
  "owl:Class",
  "owl:complementOf",
  "owl:intersectionOf",
  "owl:Nothing",
  "owl:oneOf",
  "owl:Thing",
  "owl:unionOf",
  "owl:AllDisjointclasses",
  "owl:disjointWith",
  "owl:equivalentClass",
  "rdfs:subClassOf",
  "owl:AlldisjointProperties",
  "owl:AsymmetricProperty",
  "owl:equivalentProperty",
  "owl:FunctionalProperty",
  "owl:InverseFunctionalProperty",
  "owl:inverseOf",
  "owl:IrreflexiveProperty",
  "owl:ObjectProperty",
  "owl:PropertyChainAxiom",
  "owl:propertyDisjointWith",
  "owl:SymmetricProperty",
  "owl:TransitiveProperty",
  "rdfs:domain",
  "rdfs:range",
  "rdfs:subPropertyOf",
  "owl:allValuesFrom",
  "owl:hasValue",
  "owl:maxCardinality",
  "owl:maxQualifiedCardinality",
  "owl:someValuesFrom",
  "owl:AllDisjointProperties",
  "owl:DatatypeProperty",
  "owl:equivalentProperty",
  "owl:FunctionalProperty",
  "owl:propertyDisjointWith",
  "owl:allValuesFrom",
  "owl:hasValue",
  "owl:maxCardinality",
  "owl:intersectionOf",
  "rdfs:Datatype",
  "rdf:PlainLiteral",
  "rdf:XMLLiteral",
  "rdfs:Literal",
  "xsd:anyURI",
  "xsd:base64Binary",
  "xsd:boolean",
  "xsd:byte",
  "xsd:dateTime",
  "xsd:dateTimeStamp",
  "xsd:decimal",
  "xsd:double",
  "xsd:float",
  "xsd:hexBinary",
  "xsd:int",
  "xsd:integer",
  "xsd:language",
  "xsd:long",
  "xsd:Name",
  "xsd:NCName",
  "xsd:negativeInteger",
  "xsd:NMTOKEN",
  "xsd:nonNegativeInteger",
  "xsd:nonPositiveInteger",
  "xsd:nomralizedString",
  "xsd:positiveInteger",
  "xsd:short",
  "xsd:string",
  "xsd:token",
  "xsd:unsignedByte",
  "xsd:unsignedLong",
  "Anonymous Individual",
  "owl:NamedIndividual",
  "owl:AllDifferent",
  "owl:hasKey",
  "owl:NegativePropertyAssertion(on Data Property)",
  "owl:NegativePropertyAssertion(on Object Property)",
  "owl:sameAs",
  "owl:AnnotationProperty",
  "owl:DeprecatedClass",
  "owl:DeprecatedProperty",
];
const rl = new Map();
for (let i = 0; i < but5.length; i++) {
  if (!rl.has(but5[i])) {
    rl.set(but5[i], i);
  }
}

function App() {
  const [checkbox1, setCheck1] = useState(initally1);
  const [checkbox2, setCheck2] = useState(initally2);
  const [checkbox3, setCheck3] = useState(initally3);
  const [checkbox4, setCheck4] = useState(initally4);
  const [checkbox5, setCheck5] = useState(initally5);
  const [checkbox6, setCheck6] = useState(initally6);
  const [checkbox7, setCheck7] = useState(initally7);
  const [checkbox8, setCheck8] = useState(initally8);
  const [checkbox9, setCheck9] = useState(initally9);
  const [checkbox10, setCheck10] = useState(initally10);
  const [checkbox11, setCheck11] = useState(initally11);
  const fxns = [
    setCheck1,
    setCheck2,
    setCheck3,
    setCheck4,
    setCheck5,
    setCheck6,
    setCheck7,
    setCheck10,
  ];
  const boxes = [
    checkbox1,
    checkbox2,
    checkbox3,
    checkbox4,
    checkbox5,
    checkbox6,
    checkbox7,
    checkbox10,
  ];
  const index = [
    indexes1,
    indexes2,
    indexes3,
    indexes4,
    indexes5,
    indexes6,
    indexes7,
    indexes10,
  ];

  let generate = () => {
    let userInput = [];
    let flag = false;
    let ifany = false;
    for (let i = 0; i < boxes.length; i++) {
      for (let j = 0; j < boxes[i].length; j++) {
        if (boxes[i][j] == true) {
          let construct = overallconstructs[i][j];
          let userVal = document.getElementById(construct + "Text").value;
          ifany = true;
          if (userVal >= 1) {
            userInput.push(userVal);
          } else {
            flag = true;
            userVal = 1;
            userInput.push(1);
          }
          // console.log(construct + " | " + userVal);
        } else {
          userInput.push(0);
        }
      }
    }
    if (ifany == true) {
      axios
        .post("http://localhost:8080/api/send", {
          userValues: userInput,
          // format: "xmlFormat",
          format: "manchesterFormat",
        })
        .then((response) => {
          alert(
            response.status +
              " All Non - User Input Checkboxes have been set 1 valaue by default " +
              total
          );
        });
    } else {
      alert(" Nothing Checked , Cant Generate ");
    }
  };

  let particular = (ver) => {
    /*
    for ( let i = 0 ; i < overallconstructs.length ; i++ ){
      let allCheck = [];
      for ( let j = 0 ; j < overallconstructs[i].length ; j++ ){
        if ( ver.has(overallconstructs[i][j]) ){
          allCheck[j] = true;
        }
        else{
          allCheck[j] = false;
        }
        //console.log(allCheck[j]);
      }
      //console.log(overallconstructs[i]);
      fxns[i](allCheck);
    }
    */
  };

  let invertToggle = () => {
    for (let j = 0; j < boxes.length; j++) {
      let allCheck = [];
      for (let i = 0; i < boxes[j].length; i++) {
        allCheck[i] = !boxes[j][i];
      }
      fxns[j](allCheck);
    }
    // console.log(headingInd[ind]+" toggle "+ind);
  };
  let toggle = (boolVal) => {
    for (let j = 0; j < boxes.length; j++) {
      let allCheck = [];
      let contructNames = overallconstructs[j];
      for (let i = 0; i < boxes[j].length; i++) {
        allCheck[i] = boolVal;
        if (boolVal == false) {
          document.getElementById(contructNames[i] + "Text").value = "";
          document.getElementById("selectAllInput").value = "";
          // console.log(
          //   contructNames[i] +
          //     " = " +
          //     document.getElementById(contructNames[i] + "Text").value
          // );
        }
        if (boolVal == true) {
          document.getElementById(contructNames[i] + "Text").value =
            document.getElementById("selectAllInput").value.length != 0
              ? document.getElementById("selectAllInput").value
              : 1;
          // console.log(
          //   document.getElementById("selectAllInput").value +
          //     " =  dekho left main "
          // );
        }
      }
      fxns[j](allCheck);
    }
  };
  let selectAll = () => toggle(true);
  let selectNone = () => toggle(false);

  let toggleChildren = (ind, boolVal) => {
    let allCheck = [];
    let contructNames = overallconstructs[headingInd[ind]];
    for (let i = 0; i < boxes[headingInd[ind]].length; i++) {
      // console.log(
      //   contructNames[i] +
      //     " = " +
      //     document.getElementById(contructNames[i] + "Text").value +
      //     " = " +
      //     ind +
      //     " || " +
      //     document.getElementById("selectAllInput" + ind).value
      // );
      if (boolVal == false) {
        document.getElementById("selectAllInput" + ind).value = "";
        document.getElementById(contructNames[i] + "Text").value = "";
      } else if (boolVal == true) {
        document.getElementById(contructNames[i] + "Text").value =
          document.getElementById("selectAllInput" + ind).value.length != 0
            ? document.getElementById("selectAllInput" + ind).value
            : 1;
      }
      allCheck[i] = boolVal;
    }
    fxns[headingInd[ind]](allCheck);
  };
  let invertToggleChildren = (e) => {
    let ind = e.target.id;
    //console.log(headingInd[ind]+" toggle "+ind);
    let allCheck = [];
    for (let i = 0; i < boxes[headingInd[ind]].length; i++) {
      allCheck[i] = !boxes[headingInd[ind]][i];
    }
    fxns[headingInd[ind]](allCheck);
    //     allCheck[i] = !checked[i];
  };
  //index - array of hashmaps
  //boxes - array of checkboxes
  //headingInd - hashmap of heading's index
  let handleCheckboxOne = (e) => {
    const nameclicked = e.target.name;
    let ind = e.target.parentElement.id;
    if (e.target.checked == false) {
      document.getElementById(nameclicked + "Text").value = "";
    }
    let allCheck = [...boxes[headingInd[ind]]];
    allCheck[index[headingInd[ind]][nameclicked]] =
      !boxes[headingInd[ind]][index[headingInd[ind]][nameclicked]];
    fxns[headingInd[ind]](allCheck);
    //console.log(ind+ " | "+nameclicked+"  "+e.target.checked+" "+allCheck[index[headingInd[ind]][nameclicked]]+" "+!boxes[headingInd[ind]][index[headingInd[ind]][nameclicked]]);
  };

  return (
    <div className="App">
      <div className="row justify-content-center">
        <h1>OWL2Bench</h1>
      </div>

      <div class="dropdown show">
        <a
          class="btn btn-secondary dropdown-toggle"
          href="#"
          role="button"
          id="dropdownMenuLink"
          data-toggle="dropdown"
          aria-haspopup="true"
          aria-expanded="false"
        >
          Dropdown link
        </a>

        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
          <a class="dropdown-item" href="#">
            Action
          </a>
          <a class="dropdown-item" href="#">
            Another action
          </a>
          <a class="dropdown-item" href="#">
            Something else here
          </a>
        </div>
      </div>

      <hr></hr>
      {/* <div className="row justify-content-center">
        <div className="col-3">
          <button class="btn btn-primary"> Easy </button>
        </div>
        <div className="col-3">
          <button class="btn btn-primary"> Medium </button>
        </div>
        <div className="col-3">
          <button class="btn btn-primary"> Hard </button>
        </div>
      </div> */}
      <hr></hr>
      {/* <div className="row justify-content-center">
        <div className=" col-1"></div>
        <div className=" col-2">
          {" "}
          <button class="btn btn-primary" onClick={(event) => particular(lite)}>
            {" "}
            OWL Lite{" "}
          </button>{" "}
        </div>
        <div className=" col-2">
          {" "}
          <button class="btn btn-primary" onClick={(event) => particular(dl)}>
            {" "}
            OWL DL{" "}
          </button>{" "}
        </div>
        <div className=" col-2">
          {" "}
          <button class="btn btn-primary" onClick={(event) => particular(el)}>
            {" "}
            OWL 2 EL{" "}
          </button>{" "}
        </div>
        <div className=" col-2">
          {" "}
          <button class="btn btn-primary" onClick={(event) => particular(ql)}>
            {" "}
            OWL 2 QL{" "}
          </button>{" "}
        </div>
        <div className=" col-2">
          {" "}
          <button class="btn btn-primary" onClick={(event) => particular(rl)}>
            {" "}
            OWL 2 RL{" "}
          </button>{" "}
        </div>
        <div className=" col-1"></div>
      </div>
      <hr></hr> */}
      <div className="row">
        <div className=" col-md-3"></div>
        <div className=" col-md-4">
          {" "}
          <button class="btn btn-success" onClick={selectAll}>
            {" "}
            Select All{" "}
          </button>{" "}
          <input
            type="text"
            class="col-md-3 mb-3"
            id="selectAllInput"
            placeholder="All Input"
          ></input>
        </div>
        <div className=" col-md-4">
          {" "}
          <button class="btn btn-success" onClick={selectNone}>
            Select None
          </button>{" "}
        </div>
        {/* <div className=" col-md-3">
          {" "}
          <button class="btn btn-success" onClick={invertToggle}>
            Invert Selections
          </button>{" "}
        </div> */}
      </div>
      <hr></hr>
      <hr></hr>
      <div className="row">
        <div className="container col-6">
          <Predefined
            invertToggle={invertToggleChildren}
            handleCheckboxChange={handleCheckboxOne}
            toggleParent={toggleChildren}
            constructs={constructs1}
            initally={checkbox1}
            indexes={indexes1}
            heading={h1}
          />
        </div>
        <div className="container col-6">
          <Predefined
            invertToggle={invertToggleChildren}
            handleCheckboxChange={handleCheckboxOne}
            toggleParent={toggleChildren}
            constructs={constructs2}
            initally={checkbox2}
            indexes={indexes2}
            heading={h2}
          />
        </div>
      </div>
      <br></br>
      <div className="row">
        <div className="container col-6">
          <Predefined
            invertToggle={invertToggleChildren}
            handleCheckboxChange={handleCheckboxOne}
            toggleParent={toggleChildren}
            constructs={constructs3}
            initally={checkbox3}
            indexes={indexes3}
            heading={h3}
          />
        </div>
        <div className="container col-6">
          <Predefined
            invertToggle={invertToggleChildren}
            handleCheckboxChange={handleCheckboxOne}
            toggleParent={toggleChildren}
            constructs={constructs4}
            initally={checkbox4}
            indexes={indexes4}
            heading={h4}
          />
        </div>
      </div>
      <br></br>
      <div className="row">
        <div className="container col-6">
          <Predefined
            invertToggle={invertToggleChildren}
            handleCheckboxChange={handleCheckboxOne}
            toggleParent={toggleChildren}
            constructs={constructs5}
            initally={checkbox5}
            indexes={indexes5}
            heading={h5}
          />
        </div>
        <div className="container col-6">
          <Predefined
            invertToggle={invertToggleChildren}
            handleCheckboxChange={handleCheckboxOne}
            toggleParent={toggleChildren}
            constructs={constructs6}
            initally={checkbox6}
            indexes={indexes6}
            heading={h6}
          />
        </div>
      </div>
      <br></br>
      <div className="row">
        <div className="container col-6">
          <Predefined
            invertToggle={invertToggleChildren}
            handleCheckboxChange={handleCheckboxOne}
            toggleParent={toggleChildren}
            constructs={constructs7}
            initally={checkbox7}
            indexes={indexes7}
            heading={h7}
          />
        </div>
        <div className="container col-6">
          <Predefined
            invertToggle={invertToggleChildren}
            handleCheckboxChange={handleCheckboxOne}
            toggleParent={toggleChildren}
            constructs={constructs10}
            initally={checkbox10}
            indexes={indexes10}
            heading={h10}
          />
        </div>
      </div>
      <br></br>
      {/* <div className="row">
        <div className="container col-6">
          <Predefined
            invertToggle={invertToggleChildren}
            handleCheckboxChange={handleCheckboxOne}
            toggleParent={toggleChildren}
            constructs={constructs9}
            initally={checkbox9}
            indexes={indexes9}
            heading={h9}
          />
        </div>
        <div className="container col-6">
          <Predefined
            invertToggle={invertToggleChildren}
            handleCheckboxChange={handleCheckboxOne}
            toggleParent={toggleChildren}
            constructs={constructs10}
            initally={checkbox10}
            indexes={indexes10}
            heading={h10}
          />
        </div>
      </div>
      <br></br> */}
      {/* <div className="row">
        <div className="container col-6"><Predefined invertToggle={invertToggleChildren} handleCheckboxChange={handleCheckboxOne} toggleParent={toggleChildren} constructs={constructs11} initally={checkbox11} indexes={indexes11} heading={h11}/></div>
        <div className="container col-6"><Predefined invertToggle={invertToggleChildren} handleCheckboxChange={handleCheckboxOne} toggleParent={toggleChildren} constructs={constructs12} initally={checkbox12} indexes={indexes12} heading={h12} /></div>
      </div>
      <br></br> */}
      <hr></hr>
      <hr></hr>
      <div className="row justify-content-center">
        <button class="btn btn-primary" onClick={(event) => generate()}>
          {" "}
          Generate{" "}
        </button>
      </div>
    </div>
  );
}

export default App;
