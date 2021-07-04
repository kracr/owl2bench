package owl.cs.myfirst.owlapi.Generator;

import owl.cs.myfirst.owlapi.Generator.ResourcePool;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.PrefixManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * An owl class resource pool.
 */
public class ClassPool extends ResourcePool<OWLClass> {
  private static final int MAX_REUSES = 0;
  private OWLDataFactory factory;
  private PrefixManager prefixManager;

  
  public ClassPool(OWLDataFactory factory, PrefixManager prefixManager) {
    super(MAX_REUSES);
    this.factory = factory;
    this.prefixManager = prefixManager;
  }

  @Override
  public OWLClass getExclusiveObject(String name) {
    return factory.getOWLClass(name, prefixManager);
  }

  @Override
  protected String getGenericNameBase() {
    return ":Class";
  }
}