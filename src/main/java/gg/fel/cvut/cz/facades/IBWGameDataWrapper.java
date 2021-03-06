package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.data.readonly.Bullet;
import gg.fel.cvut.cz.data.readonly.ChokePoint;
import gg.fel.cvut.cz.data.readonly.Player;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.data.readonly.Race;
import gg.fel.cvut.cz.data.readonly.Region;
import gg.fel.cvut.cz.data.readonly.TechType;
import gg.fel.cvut.cz.data.readonly.TilePosition;
import gg.fel.cvut.cz.data.readonly.Unit;
import gg.fel.cvut.cz.data.readonly.UnitType;
import gg.fel.cvut.cz.data.readonly.UpgradeType;
import gg.fel.cvut.cz.data.readonly.WalkPosition;
import gg.fel.cvut.cz.data.readonly.WeaponType;
import gg.fel.cvut.cz.wrappers.WBaseLocation;
import gg.fel.cvut.cz.wrappers.WBullet;
import gg.fel.cvut.cz.wrappers.WChokePoint;
import gg.fel.cvut.cz.wrappers.WPlayer;
import gg.fel.cvut.cz.wrappers.WPosition;
import gg.fel.cvut.cz.wrappers.WRace;
import gg.fel.cvut.cz.wrappers.WRegion;
import gg.fel.cvut.cz.wrappers.WTechType;
import gg.fel.cvut.cz.wrappers.WTilePosition;
import gg.fel.cvut.cz.wrappers.WUnit;
import gg.fel.cvut.cz.wrappers.WUnitType;
import gg.fel.cvut.cz.wrappers.WUpgradeType;
import gg.fel.cvut.cz.wrappers.WWalkPosition;
import gg.fel.cvut.cz.wrappers.WWeaponType;
import java.util.Optional;

/**
 * Contract to get game objects
 */
public interface IBWGameDataWrapper {

  Optional<WWeaponType> getBWInstance(WeaponType container);

  Optional<WBullet> getBWInstance(Bullet container);

  Optional<WBaseLocation> getBWInstance(BaseLocation container);

  Optional<WChokePoint> getBWInstance(ChokePoint container);

  Optional<WPlayer> getBWInstance(Player container);

  Optional<WPosition> getBWInstance(Position container);

  Optional<WRace> getBWInstance(Race container);

  Optional<WRegion> getBWInstance(Region container);

  Optional<WTechType> getBWInstance(TechType container);

  Optional<WTilePosition> getBWInstance(TilePosition container);

  Optional<WUnit> getBWInstance(Unit container);

  Optional<WUnitType> getBWInstance(UnitType container);

  Optional<WUpgradeType> getBWInstance(UpgradeType container);

  Optional<WWalkPosition> getBWInstance(WalkPosition container);

}
