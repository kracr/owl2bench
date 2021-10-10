import React, { Component } from "react";
import { Nav } from "react-bootstrap";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

export default class Home extends Component {
  render() {
    return (
      <div className="container">
        <h1 className="m-4 p-2 text-center">
          Owl2Bench : A Benchmark for OWL 2 Reasoners.
        </h1>
        <div>
          <span className="row">
            <h3 className="ml-3 pt-2 mb-0">Introduction</h3>
            <Nav className="ml-auto">
              <Nav.Link as={Link} to="/owl2bench">
                <button className="btn btn-success"> Try Owl2Bench </button>
              </Nav.Link>
            </Nav>
          </span>
          <hr className="border p-0 m-0 mt-2 mb-2" />

          <h5 className="lead">
            In the past decade, there has been remarkable progress towards the
            development of reasoners that involve expressive ontology languages
            such as OWL 2. However, they still do not scale well on expressive
            language profiles (OWL2 DL). To build better quality reasoners,
            developers need to find and improve the performance bottlenecks of
            their existing systems . A reasoner bench- mark aids the reasoner
            developers to evaluate their system’s performance and deal with the
            limitations. Furthermore, it paves the way for further research to
            improve performance and functionality. In particular, a reasoner
            needs to be evaluated from several aspects such as support for
            different language constructs and their combinations, their effect
            on reasoning performance, ability to handle large ontologies, and
            capability to handle queries that involve reasoning. Though there
            are some existing ontology benchmarks, they are limited in scope.
            LUBM and UOBM are based on the older version of OWL (OWL 1).
            OntoBench supports OWL 2 profiles but does not evaluate reasoner
            perfor- mance. ORE benchmark framework4 does not consider evaluation
            in the context of varying sizes of an ontology. In essence, no
            existing benchmark covers all the above-mentioned aspects for
            reasoner evaluation. Here, we present our ongoing efforts towards
            building a customizable ontology benchmark for OWL 2 reasoners named
            OWL2Bench.
          </h5>
        </div>
      </div>
    );
  }
}
