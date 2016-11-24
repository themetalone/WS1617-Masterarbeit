package com.github.themetalone.pandemic.utils.provider;

import java.util.Collection;
import java.util.Optional;

/**
 * @author steffen
 * @param <Identifier>
 * @param <Target>
 *
 */
public abstract class Provider<Identifier, Target extends Providable> {

  private Collection<Target> targets;

  public Provider(Collection<Target> targets) {
    this.targets = targets;
  }

  public Target get(Identifier i) {

    Optional<Target> result = this.targets.stream().filter(t -> t.getIdentifier().equals(i)).findFirst();
    if (result.isPresent())
      return result.get();
    throw new Error("No such element:" + i.toString());
  }

  public Collection<Target> getAll() {

    return this.targets;
  }

  public void add(Target t) {

    this.targets.add(t);
  }

  public void set(Collection<Target> targets) {

    this.targets = targets;
  }

}
