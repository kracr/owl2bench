(this.webpackJsonpfrontend=this.webpackJsonpfrontend||[]).push([[0],{18:function(e,t,a){e.exports=a(41)},23:function(e,t,a){},41:function(e,t,a){"use strict";a.r(t);var n=a(0),l=a.n(n),o=a(15),r=a.n(o),s=(a(23),a(17)),i=a(1),c=function(e){var t=e.label,a=e.isSelected,o=e.onCheckboxChange,r=e.heading,s=Object(n.useState)(""),c=Object(i.a)(s,2),d=(c[0],c[1]),m=Object(n.useState)(t),u=Object(i.a)(m,2);u[0],u[1];return l.a.createElement("div",{className:"row"},l.a.createElement("label",{className:"col-1"}),l.a.createElement("label",{className:"col-5",id:r},l.a.createElement("input",{type:"checkbox",id:t,name:t,checked:a,onChange:o}),t),l.a.createElement("label",{className:"col-2"},l.a.createElement("input",{class:"form-group col-md-12",type:"text",id:t+"Text",width:"20px",height:"20px",onChange:function(e){document.getElementById(t).checked?isNaN(document.getElementById(t+"Text").value)?(d(""),alert("Please Enter A Number")):d(e.target.value):alert("First Tick Checkbox")}})))};a(6);for(var d=function(e){var t=Object(n.useState)(e.initally),a=Object(i.a)(t,2),o=a[0],r=a[1];Object(n.useEffect)((function(){for(var t=!1,a=0;a<o.length;a++)if(o[a]!=e.initally[a]){t=!0;break}1==t&&r(e.initally)}));var s=function(t,a){e.toggleParent(t.target.id,a)};return l.a.createElement("div",null,l.a.createElement("div",{className:"row"},l.a.createElement("h5",{className:"col-3"}),l.a.createElement("h5",null,e.heading)),l.a.createElement("div",{className:"row"},l.a.createElement("div",{className:"col-1"}),l.a.createElement("div",{className:"col-5"},l.a.createElement("button",{id:e.heading,class:"btn btn-secondary",onClick:function(e){s(e,!0)}}," ","Select All"," "),l.a.createElement("input",{type:"text",class:"col-md-3 mb-3",id:"selectAllInput"+e.heading,placeholder:"Input"})),l.a.createElement("div",{className:"col-5"},l.a.createElement("button",{id:e.heading,class:"btn btn-secondary",onClick:function(e){s(e,!1)}},"Select None"))),l.a.createElement("br",null),e.constructs.map((function(t){return l.a.createElement(c,{heading:e.heading,label:t,isSelected:o[e.indexes[t]],onCheckboxChange:function(t){return e.handleCheckboxChange(t)},key:t})})))},m=a(16),u=a.n(m),p=["OwlClass","ObjectComplement","ObjectIntersection","ObjectOneOf","ObjectUnionOf"],w=[],f=new Map,g=0;g<p.length;g++)w[g]=!1,f[p[g]]=g;for(var y="Predefined Classes, Boolean Connectives and Enumerations",h=["DisjointUnion","DisjointWith","EquivalentClass","RdfsSubClassOf"],v=[],x=new Map,b=0;b<h.length;b++)v[b]=!1,x[h[b]]=b;for(var P="Class Expression Axioms",O=["AsymmetricProperty","EquivalentObjectProperty","FunctionalObjectProperty","InverseFunctionalProperty","InverseOfProperty","IrreflexiveProperty","ObjectPropertyDisjointWith","ReflexiveProperty","SymmetricProperty","TransitiveProperty","RdfsObjectDomain","RdfsObjectRange","PropertyChainAxiom","RdfsObjectSubPropertyOf","ObjectProperty"],E=[],C=new Map,j=0;j<O.length;j++)E[j]=!1,C[O[j]]=j;for(var N="Object Properties and Axioms",D=["ObjectAllValuesFrom","ObjectHasSelf","ObjectHasValue","ObjectSomeValuesFrom","ObjectQualifiedCardinality","ObjectMaxQualifiedCardinality","ObjectMinQualifiedCardinality"],A=[],I=new Map,k=0;k<D.length;k++)A[k]=!1,I[D[k]]=k;for(var S="Object Properties Restrictions",T=["EquivalentDataProperty","FunctionalDataProperty","DataPropertyDisjointWith","RdfsDataDomain","RdfsDataRange","RdfsDataSubPropertyOf"],M=[],F=new Map,B=0;B<T.length;B++)M[B]=!1,F[T[B]]=B;for(var V="Data Properties and Axioms",R=["DataAllValues","DataHasValue","DataMaxQualifiedCardinality","DataMinQualifiedCardinality","DataQualifiedCardinality","DataSomeValuesFrom"],L=[],q=new Map,W=0;W<R.length;W++)L[W]=!1,q[R[W]]=W;for(var U="Data Properties Restrictions",K=["DataComplementOf","DataIntersectionOf","DataOneOf","DataUnionOf"],Q=[],X=new Map,H=0;H<K.length;H++)Q[H]=!1,X[K[H]]=H;for(var z="Data Ranges",G=["HasKey","ClassAssertionAxioms","ObjectPropertyAssertionAxioms","DataPropertyAssertionAxioms"],J=[],$=new Map,Y=0;Y<G.length;Y++)J[Y]=!1,$[G[Y]]=Y;for(var Z="Assertions",_=["owl:rational","owl:real","rdf:langString","rdf:PlainLiteral","rdf:XMLLiteral","rdfs:Literal","xsd:anyURI","xsd:base64Binary","xsd:boolean","xsd:byte","xsd:dateTime","xsd:dateTimeStamp","xsd:decimal","xsd:double","xsd:float","xsd:hexBinary","xsd:int","xsd:integer","xsd:language","xsd:long","xsd:Name","xsd:NCName","xsd:negativeInteger","xsd:NMTOKEN","xsd:nonNegativeInteger","xsd:nonPositiveInteger","xsd:normalizeString","xsd:positiveInteger","xsd:short","xsd:string","xsd:token","xsd:unsignedByte","xsd:unsignedInt","xsd:unsignedLong","xsd:unsignedShort"],ee=[],te=new Map,ae=0;ae<_.length;ae++)ee[ae]=!1,te[_[ae]]=ae;for(var ne=["Anonymous Individual","owl:NamedIndividual"],le=[],oe=new Map,re=0;re<ne.length;re++)le[re]=!1,oe[ne[re]]=re;for(var se=["owl:AnnotationProperty","owl:backwardCompactibleWith","owl:DeprecatedClass","owl:DeprecatedProperty","owl:imports","owl:imcompatibleWith","owl:priorVersion","owl:versionInfo","owl:versionIri","rdfs:comment(on Class&Property multilingual)","rdfs:comment(on Class&Property)","rdfs:comment(on Ontology)","rdfs:isDefinedBy","rdfs:label(on Class&Property, multilingual)","rdfs:label(on Class&Property)","rdfs:label(on Ontology)","rdfs:seeAlso"],ie=[],ce=new Map,de=0;de<se.length;de++)ie[de]=!1,ce[se[de]]=de;for(var me=[y,P,N,S,V,U,z,Z],ue=new Map,pe=0;pe<me.length;pe++)ue[me[pe]]=pe;for(var we=[p,h,O,D,T,R,K,G],fe=0,ge=0;ge<we.length;ge++)fe+=we[ge].length;new Array(fe).fill(0);for(var ye=["owl:Class","owl:Nothing","owl:Thing","owl:equivalentClass","rdfs:subClassOf","owl:equivalentProperty","owl:FunctionalProperty","owl:InverseFunctionalProperty","owl:ObjectProperty","owl:SymmetricProperty","owl:TransitiveProperty","rdfs:domain","rdfs:range","rdfs:subPropertyOf","owl:allValuesFrom","owl:maxCardinality","owl:minCardinality","owl:someValuesFrom","owl:DatatypeProperty","owl:AllDifferent","owl:sameAs","owl:AnnotationProperty","owl:DeprecatedClass","owl:versionInfo","rdfs:comment(on Class&Property)","rdfs:comment(on Ontology)","rdfs:label(on Class&Property)","rdfs:label(on Ontology)"],he=new Map,ve=0;ve<ye.length;ve++)he.has(ye[ve])||he.set(ye[ve],ve);for(var xe=["owl:Class","owl:complementOf","owl:intersectionOf","owl:Nothing","owl:oneOf","owl:Thing","owl:unionOf","owl:disjointWith","owl:equivalentClass","rdfs:subClassOf","owl:equivalentProperty","owl:FunctionalProperty","owl:InverseFunctionalProperty","owl:inverseOf","owl:ObjectProperty","owl:SymmetricProperty","owl:TransitiveProperty","rdfs:domain","rdfs:range","rdfs:subPropertyOf","owl:allValuesFrom","owl:cardinality","owl:hasValue","owl:maxCardinality","owl:minCardinality","owl:someValuesFrom","owl:DataTypeProperty","owl:oneOf","owl:AllDifferent","owl:sameAs","owl:AnnotationProperty","owl:DeprecatedProperty","owl:versioanInfo","rdfs:comment(on Class&Property)","rdfs:comment(on Ontology)","rdfs:label(on Class&Property)","rdfs:label(on Ontology)"],be=new Map,Pe=0;Pe<xe.length;Pe++)be.has(xe[Pe])||be.set(xe[Pe],Pe);for(var Oe=["owl:Class","owl:intersectionOf","owl:Nothing","owl:Thing","owl:AlldisjointClasses","owl:disjointWith","owl:equivalentClass","rdfs:subClassOf","owl:equivalentProperty","owl:ObjectProperty","owl:PropertyChainAxiom","owl:ReflexiveProperty","owl:TransitiveProperty","rdfs:domain","rdfs:range","rdfs:subPropertyOf","owl:hasSelf","owl:hasValue","owl:someValuesFrom","owl:DatatypeProperty","owl:equivalentProperty","owl:FunctionalProperty","owl:hasValue","owl:someValuesFrom","owl:intersectionaOf","owl:oneOf","rdfs:Datatype","owl:rational","owl:real","rdf:PlainLiteral","rdf:XMLLiteral","xsd:anyURI","xsd:base64Binary","xsd:dateTime","xsd:dateTimeStamp","xsd:decimal","xsd:hexBinary","xsd:integer","xsd:Name","xsd:NMTOKEN","xsd:nonNegativeInteger","xsd:normalizedString","xsd:string","xsd:token","owl:NamedIndividual","owl:AllDifferent","owl:hasKey","owl:NegativePropertyAssertion(on Data Property)","owl:NegativePropertyAssertion(on Object Property)","owl:sameAs","owl:AnnotationProperty","owl:DeprecatedClass","owl:DeprecatedProperty"],Ee=new Map,Ce=0;Ce<Oe.length;Ce++)Ee.has(Oe[Ce])||Ee.set(Oe[Ce],Ce);for(var je=["owl:Class","owl:complementOf","owl:intersectionOf","owl:Nothing","owl:Thing","owl:AllDisjointClasses","owl:disjointWith","owl:equivalentClass","rdfs:subClassOf","owl:AllDisjointProperties","owl:AsymmetricProperty","owl:equivalentProperty","owl:inverseOf","owl:IrreflexiveProperty","owl:ObjectProperty","owl:propertyDisjointWith","owl:ReflexiveProperty","rdfs:domain","rdfs:range","rdfs:subPropertyOf","owl:someValuesFrom","owl:AllDisjointProperties","owl:DatatypeProperty","owl:equivalentProperty","owl:propertyDisjointWith","owl:someValuesFrom","owl:intersectionOf","rdfs:Datatype","owl:rational","owl:real","rdf:PlainLiteral","rdf:XMLLiteral","rdfs:Literal","xsd:anyURI","xsd:base64Binary","xsd:dateTime","xsd:dateTimeStamp","xsd:decimal","xsd:hexBinary","xsd:integer","xsd:Name","xsd:NCName","xsd:NMTOKEN","xsd:nonNegativeInteger","xsd:normalizedString","xsd:string","xsd:token","owl:NamedIndividual","owl:AllDifferent","owl:AnnotationProperty","owl:DeprecatedClass","owl:DeprecatedProperty"],Ne=new Map,De=0;De<je.length;De++)Ne.has(je[De])||Ne.set(je[De],De);for(var Ae=["owl:Class","owl:complementOf","owl:intersectionOf","owl:Nothing","owl:oneOf","owl:Thing","owl:unionOf","owl:AllDisjointclasses","owl:disjointWith","owl:equivalentClass","rdfs:subClassOf","owl:AlldisjointProperties","owl:AsymmetricProperty","owl:equivalentProperty","owl:FunctionalProperty","owl:InverseFunctionalProperty","owl:inverseOf","owl:IrreflexiveProperty","owl:ObjectProperty","owl:PropertyChainAxiom","owl:propertyDisjointWith","owl:SymmetricProperty","owl:TransitiveProperty","rdfs:domain","rdfs:range","rdfs:subPropertyOf","owl:allValuesFrom","owl:hasValue","owl:maxCardinality","owl:maxQualifiedCardinality","owl:someValuesFrom","owl:AllDisjointProperties","owl:DatatypeProperty","owl:equivalentProperty","owl:FunctionalProperty","owl:propertyDisjointWith","owl:allValuesFrom","owl:hasValue","owl:maxCardinality","owl:intersectionOf","rdfs:Datatype","rdf:PlainLiteral","rdf:XMLLiteral","rdfs:Literal","xsd:anyURI","xsd:base64Binary","xsd:boolean","xsd:byte","xsd:dateTime","xsd:dateTimeStamp","xsd:decimal","xsd:double","xsd:float","xsd:hexBinary","xsd:int","xsd:integer","xsd:language","xsd:long","xsd:Name","xsd:NCName","xsd:negativeInteger","xsd:NMTOKEN","xsd:nonNegativeInteger","xsd:nonPositiveInteger","xsd:nomralizedString","xsd:positiveInteger","xsd:short","xsd:string","xsd:token","xsd:unsignedByte","xsd:unsignedLong","Anonymous Individual","owl:NamedIndividual","owl:AllDifferent","owl:hasKey","owl:NegativePropertyAssertion(on Data Property)","owl:NegativePropertyAssertion(on Object Property)","owl:sameAs","owl:AnnotationProperty","owl:DeprecatedClass","owl:DeprecatedProperty"],Ie=new Map,ke=0;ke<Ae.length;ke++)Ie.has(Ae[ke])||Ie.set(Ae[ke],ke);var Se=function(){var e=Object(n.useState)(w),t=Object(i.a)(e,2),a=t[0],o=t[1],r=Object(n.useState)(v),c=Object(i.a)(r,2),m=c[0],g=c[1],b=Object(n.useState)(E),j=Object(i.a)(b,2),k=j[0],B=j[1],W=Object(n.useState)(A),H=Object(i.a)(W,2),Y=H[0],_=H[1],te=Object(n.useState)(M),ae=Object(i.a)(te,2),ne=ae[0],oe=ae[1],re=Object(n.useState)(L),se=Object(i.a)(re,2),ce=se[0],de=se[1],me=Object(n.useState)(Q),pe=Object(i.a)(me,2),ge=pe[0],ye=pe[1],he=Object(n.useState)(ee),ve=Object(i.a)(he,2),xe=(ve[0],ve[1],Object(n.useState)(le)),be=Object(i.a)(xe,2),Pe=(be[0],be[1],Object(n.useState)(J)),Oe=Object(i.a)(Pe,2),Ee=Oe[0],Ce=Oe[1],je=Object(n.useState)(ie),Ne=Object(i.a)(je,2),De=(Ne[0],Ne[1],Object(n.useState)(!1)),Ae=Object(i.a)(De,2),Ie=Ae[0],ke=Ae[1],Se=Object(n.useState)("XML"),Te=Object(i.a)(Se,2),Me=Te[0],Fe=Te[1],Be=[o,g,B,_,oe,de,ye,Ce],Ve=[a,m,k,Y,ne,ce,ge,Ee],Re=[f,x,C,I,F,q,X,$],Le=function(e){for(var t=0;t<Ve.length;t++){for(var a=[],n=we[t],l=0;l<Ve[t].length;l++)a[l]=e,0==e&&(document.getElementById(n[l]+"Text").value="",document.getElementById("selectAllInput").value=""),1==e&&(document.getElementById(n[l]+"Text").value=0!=document.getElementById("selectAllInput").value.length?document.getElementById("selectAllInput").value:1);Be[t](a)}},qe=function(e,t){for(var a=[],n=we[ue[e]],l=0;l<Ve[ue[e]].length;l++)0==t?(document.getElementById("selectAllInput"+e).value="",document.getElementById(n[l]+"Text").value=""):1==t&&(document.getElementById(n[l]+"Text").value=0!=document.getElementById("selectAllInput"+e).value.length?document.getElementById("selectAllInput"+e).value:1),a[l]=t;Be[ue[e]](a)},We=function(e){for(var t=e.target.id,a=[],n=0;n<Ve[ue[t]].length;n++)a[n]=!Ve[ue[t]][n];Be[ue[t]](a)},Ue=function(e){var t=e.target.name,a=e.target.parentElement.id;0==e.target.checked&&(document.getElementById(t+"Text").value="");var n=Object(s.a)(Ve[ue[a]]);n[Re[ue[a]][t]]=!Ve[ue[a]][Re[ue[a]][t]],Be[ue[a]](n)};return l.a.createElement("div",{className:"App"},l.a.createElement("div",{className:"row justify-content-center"},l.a.createElement("h1",null,"OWL2Bench")),l.a.createElement("hr",null),l.a.createElement("hr",null),l.a.createElement("div",{className:"row"},l.a.createElement("div",{className:" col-md-3"}),l.a.createElement("div",{className:" col-md-4"}," ",l.a.createElement("button",{class:"btn btn-success",onClick:function(){return Le(!0)}}," ","Select All"," ")," ",l.a.createElement("input",{type:"text",class:"col-md-3 mb-3",id:"selectAllInput",placeholder:"All Input"})),l.a.createElement("div",{className:" col-md-4"}," ",l.a.createElement("button",{class:"btn btn-success",onClick:function(){return Le(!1)}},"Select None")," ")),l.a.createElement("hr",null),l.a.createElement("hr",null),l.a.createElement("div",{className:"row"},l.a.createElement("div",{className:"container col-6"},l.a.createElement(d,{invertToggle:We,handleCheckboxChange:Ue,toggleParent:qe,constructs:p,initally:a,indexes:f,heading:y})),l.a.createElement("div",{className:"container col-6"},l.a.createElement(d,{invertToggle:We,handleCheckboxChange:Ue,toggleParent:qe,constructs:h,initally:m,indexes:x,heading:P}))),l.a.createElement("br",null),l.a.createElement("div",{className:"row"},l.a.createElement("div",{className:"container col-6"},l.a.createElement(d,{invertToggle:We,handleCheckboxChange:Ue,toggleParent:qe,constructs:O,initally:k,indexes:C,heading:N})),l.a.createElement("div",{className:"container col-6"},l.a.createElement(d,{invertToggle:We,handleCheckboxChange:Ue,toggleParent:qe,constructs:D,initally:Y,indexes:I,heading:S}))),l.a.createElement("br",null),l.a.createElement("div",{className:"row"},l.a.createElement("div",{className:"container col-6"},l.a.createElement(d,{invertToggle:We,handleCheckboxChange:Ue,toggleParent:qe,constructs:T,initally:ne,indexes:F,heading:V})),l.a.createElement("div",{className:"container col-6"},l.a.createElement(d,{invertToggle:We,handleCheckboxChange:Ue,toggleParent:qe,constructs:R,initally:ce,indexes:q,heading:U}))),l.a.createElement("br",null),l.a.createElement("div",{className:"row"},l.a.createElement("div",{className:"container col-6"},l.a.createElement(d,{invertToggle:We,handleCheckboxChange:Ue,toggleParent:qe,constructs:K,initally:ge,indexes:X,heading:z})),l.a.createElement("div",{className:"container col-6"},l.a.createElement(d,{invertToggle:We,handleCheckboxChange:Ue,toggleParent:qe,constructs:G,initally:Ee,indexes:$,heading:Z}))),l.a.createElement("br",null),l.a.createElement("hr",null),l.a.createElement("hr",null),l.a.createElement("div",{className:"row justify-content-around"},l.a.createElement("select",{onChange:function(e){var t=e.target;Fe(t.value)}},l.a.createElement("option",{value:"XML"},"XML"),l.a.createElement("option",{value:"RDF"},"RDF"),l.a.createElement("option",{value:"Manchester"},"Manchester"),l.a.createElement("option",{value:"Functional"},"Functional"),l.a.createElement("option",{value:"Turtle"},"Turtle")),l.a.createElement("div",{className:"form-check"},l.a.createElement("input",{class:"form-check-input",type:"checkbox",value:"",id:"flexCheckChecked",onChange:function(e){var t=e.target,a="checkbox"===t.type?t.checked:t.value;ke(a)}}),l.a.createElement("label",{class:"form-check-label",for:"flexCheckChecked"},"Extra Hierarchy, Domain And Range")),l.a.createElement("button",{className:"btn btn-primary",onClick:function(e){return function(){for(var e=[],t=!1,a=0;a<Ve.length;a++)for(var n=0;n<Ve[a].length;n++)if(1==Ve[a][n]){var l=we[a][n],o=document.getElementById(l+"Text").value;t=!0,o>=1?e.push(o):(!0,o=1,e.push(1))}else e.push(0);1==t?(console.log(Ie+" |"+Me),u.a.post("http://localhost:8080/api/send",{userValues:e,format:Me,extra:Ie}).then((function(e){alert(e.status+" All Non - User Input Checkboxes have been set 1 valaue by default "+fe)}))):alert(" Nothing Checked , Cant Generate ")}()}}," ","Generate"," ")))};Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));r.a.render(l.a.createElement(l.a.StrictMode,null,l.a.createElement(Se,null)),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then((function(e){e.unregister()})).catch((function(e){console.error(e.message)}))},6:function(e,t,a){}},[[18,1,2]]]);
//# sourceMappingURL=main.e14c15b1.chunk.js.map