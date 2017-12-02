package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.Player;
import gg.fel.cvut.cz.facades.IUpdateManager;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WPlayer;
import java.util.stream.Stream;

//TODO implement
public class UpdatablePlayer extends Player implements
    IUpdatableContainer<WPlayer, Player> {

  private final transient WPlayer wrapped;

  public UpdatablePlayer(BWCounter bwCounter, WPlayer wrapped) {
    super(bwCounter);
    this.wrapped = wrapped;
  }

  @Override
  public WPlayer getWrappedSCInstance() {
    return wrapped;
  }

  @Override
  public Stream<? extends AContainer> update(UpdateManager internalUpdaterFacade) {
    return Stream.empty();
  }

  @Override
  public Player getContainer() {
    return this;
  }

  @Override
  public Player getCopyOfContainer(BWReplayCounter bwReplayCounter) {
    this.bwCounter = bwReplayCounter;
    return this;
  }

  @Override
  public boolean shouldBeUpdated(UpdateStrategy updateStrategy, IUpdateManager updaterFacade,
      int depth) {
    return updateStrategy.shouldBeUpdated(this, updaterFacade.getDeltaUpdate(this), depth);
  }

  @Override
  public void update(UpdateStrategy updateStrategy, IUpdateManager updaterFacade, int depth,
      int currentFrame) {
    updaterFacade.update(this, updateStrategy, depth, currentFrame);
  }

}
