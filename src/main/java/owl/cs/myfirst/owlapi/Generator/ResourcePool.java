package owl.cs.myfirst.owlapi.Generator;


import org.apache.commons.lang3.mutable.MutableInt;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A generic resource pool.
 */
public abstract class ResourcePool<T> {
  private Set<T> objectPool = Collections.newSetFromMap(new ConcurrentHashMap<>());
  private int genericCounter = 0;
  private int maxReuses = Integer.MAX_VALUE;
  private Map<T, MutableInt> usageMap = new ConcurrentHashMap<>();

  public ResourcePool() {
  }

  public ResourcePool(int maxReuses) {
    this.maxReuses = maxReuses;
  }

  /**
   * @return an exlusive object with a generic name
   */
  public T getExclusiveObject() {
    return getExclusiveObject(createGenericName());
  }

  /**
   * Returns if available an object from the pool or a new one if none is available. The new object will receive a
   * generic iri.
   *
   * @return an object from the pool or if not available a new one with a generic name
   */
  public T getReusableObject() {
    return getReusableObject(createGenericName());
  }

  /**
   * Returns if available an object from the pool or a new one if none is available. The new object will receive the
   * passed iri.
   *
   * @param preferredIri the iri of a new object, if one has to be created
   * @return an object from the pool or if not available a new one
   */
  public T getReusableObject(String preferredIri) {
    return getReusableObjectDifferentFrom(preferredIri, Collections.emptySet());
  }

  public T getResuableObjectDifferentFrom(Set<T> differentFrom) {
    return getReusableObjectDifferentFrom(createGenericName(), differentFrom);
  }

  public T getReusableObjectDifferentFrom(String preferredIri, Set<T> differentFrom) {
    // try to select an existing element which is not excluded by the parameter
    if (!objectPool.isEmpty()) {
      for (T object : objectPool) {
        if (!differentFrom.contains(object)) {
          if (canBeUsedAgain(object)) {
            markAsUsed(object);
            return object;
          } else {
            removeFromPool(object);
          }
        }
      }
    }
    return createReusableObject(preferredIri);
  }

  private boolean canBeUsedAgain(T object) {
    return !usageMap.containsKey(object) || usageMap.get(object).intValue() <= maxReuses;
  }

  private void markAsUsed(T object) {
    MutableInt usages = usageMap.get(object);
    if (usages == null) {
      usageMap.put(object, new MutableInt(1));
    } else {
      usages.increment();
    }
  }

  private T createReusableObject(String preferredIri) {
    T object = getExclusiveObject(preferredIri);
    objectPool.add(object);
    markAsUsed(object);
    return object;
  }

  public T getReusableObjectAndRemoveFromPool() {
    T object = getReusableObject();
    removeFromPool(object);
    return object;
  }

  private String createGenericName() {
    return getGenericNameBase() + increaseGenericObjectCount();
  }

  protected String getGenericNameBase() {
    return "Generic";
  }

  protected int increaseGenericObjectCount() {
    return ++genericCounter;
  }

  /**
   * Removes the passed object from the pool so it can't be used for other purposes.
   *
   * @param object the object that should be removed
   * @return true, if removing was successful
   */
  public boolean removeFromPool(T object) {
    usageMap.remove(object);
    return objectPool.remove(object);
  }

  public abstract T getExclusiveObject(String name);

}
