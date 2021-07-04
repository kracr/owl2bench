package owl.cs.myfirst.owlapi.Generator;

import owl.cs.myfirst.owlapi.Generator.ClassPool;
import owl.cs.myfirst.owlapi.Generator.PropertyPool;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Encapsulates different more specific resource pools.
 */

public class FeaturePool {
  private ClassPool classPool;
  private PropertyPool propertyPool;

  
  public FeaturePool(ClassPool classPool, PropertyPool propertyPool) {
    this.classPool = classPool;
    this.propertyPool = propertyPool;
  }

  public OWLClass getReusableClass() {
    return classPool.getReusableObject();
  }

  public OWLClass getReusableClass(String preferredIri) {
    return classPool.getReusableObject(preferredIri);
  }

  public OWLClass getReusableClassDifferentFrom(OWLClass... differentFrom) {
    return classPool.getResuableObjectDifferentFrom(new HashSet<>(Arrays.asList(differentFrom)));
  }

  public OWLClass getReusableClassAndRemoveFromPool() {
    return classPool.getReusableObjectAndRemoveFromPool();
  }

  public OWLClass getExclusiveClass(String name) {
    return classPool.getExclusiveObject(name);
  }

  public OWLObjectProperty getReusableProperty() {
    return propertyPool.getReusableObject();
  }

  public OWLObjectProperty getReusableProperty(String preferredIri) {
    return propertyPool.getReusableObject(preferredIri);
  }

  public OWLObjectProperty getReusablePropertyAndRemoveFromPool() {
    return propertyPool.getReusableObjectAndRemoveFromPool();
  }

  public OWLObjectProperty getExclusiveProperty(String name) {
    return propertyPool.getExclusiveObject(name);
  }

}
