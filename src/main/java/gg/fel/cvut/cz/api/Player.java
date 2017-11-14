package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.api.enums.PlayerType;

import java.util.List;

/**
 * The Player represents a unique controller in the game. Each player in a match will have his or her own player instance. There is also a neutral player which owns all the neutral units (such as mineral patches and vespene geysers). See also Playerset, PlayerType, Race
 */
public interface Player {

    /**
     * Retrieves a unique ID that represents the player. Returns An integer representing the ID of the player.
     */
    int getID();

    /**
     * Retrieves the name of the player. Returns A std::string object containing the player's name. Note Don't forget to use std::string::c_str() when passing this parameter to Game::sendText and other variadic functions. Example usage: BWAPI::Player myEnemy = BWAPI::Broodwar->enemy(); if ( myEnemy != nullptr ) // Make sure there is an enemy! BWAPI::Broodwar->sendText("Prepare to be crushed, %s!", myEnemy->getName().c_str());
     */
    String getName();

    /**
     * Retrieves the set of all units that the player owns. This also includes incomplete units. Returns Reference to a Unitset containing the units. Note This does not include units that are loaded into transports, Bunkers, Refineries, Assimilators, or Extractors. Example usage: Unitset myUnits = BWAPI::Broodwar->self()->getUnits(); for ( auto u = myUnits.begin(); u != myUnits.end(); ++u ) { // Do something with your units }
     */
    List<Unit> getUnits();

    /**
     * Retrieves the race of the player. This allows you to change strategies against different races, or generalize some commands for yourself. Return values Races::Unknown If the player chose Races::Random when the game started and they have not been seen. Returns The Race that the player is using. Example usage: if ( BWAPI::Broodwar->enemy() ) { BWAPI::Race enemyRace = BWAPI::Broodwar->enemy()->getRace(); if ( enemyRace == Races::Zerg ) BWAPI::Broodwar->sendText("Do you really think you can beat me with a zergling rush?"); }
     */
    Race getRace();

    /**
     * Retrieves the player's controller type. This allows you to distinguish betweeen computer and human players. Returns The PlayerType that identifies who is controlling a player. Note Other players using BWAPI will be treated as a human player and return PlayerTypes::Player. if ( BWAPI::Broodwar->enemy() ) { if ( BWAPI::Broodwar->enemy()->getType() == PlayerTypes::Computer ) BWAPI::Broodwar << "Looks like something I can abuse!" << std::endl; }
     */
    PlayerType getType();

    /**
     * Retrieves the player's force. A force is the team that the player is playing on. Returns The Force object that the player is part of.
     */
    Force getForce();

    /**
     * Checks if this player is allied to the specified player. Parameters player The player to check alliance with. Return values true if this player is allied with player . false if this player is not allied with player. Note This function will also return false if this player is neutral or an observer, or if player is neutral or an observer. See also isEnemy
     */
    boolean isAlly(Player player);

    /**
     * Checks if this player is unallied to the specified player. Parameters player The player to check alliance with. Return values true if this player is allied with player . false if this player is not allied with player . Note This function will also return false if this player is neutral or an observer, or if player is neutral or an observer. See also isAlly
     */
    boolean isEnemy(Player player);

    /**
     * Checks if this player is the neutral player. Return values true if this player is the neutral player. false if this player is any other player.
     */
    boolean isNeutral();

    /**
     * Retrieve's the player's starting location. Returns A TilePosition containing the position of the start location. Return values TilePositions::None if the player does not have a start location. TilePositions::Unknown if an error occured while trying to retrieve the start location. See also Game::getStartLocations, Game::getLastError
     */
    TilePosition getStartLocation();

    /**
     * Checks if the player has achieved victory. Returns true if this player has achieved victory, otherwise false
     */
    boolean isVictorious();

    /**
     * Checks if the player has been defeated. Returns true if the player is defeated, otherwise false
     */
    boolean isDefeated();

    /**
     * Checks if the player has left the game. Returns true if the player has left the game, otherwise false
     */
    boolean leftGame();

    /**
     * Retrieves the current amount of minerals/ore that this player has. Note This function will return 0 if the player is inaccessible. Returns Amount of minerals that the player currently has for spending.
     */
    int minerals();

    /**
     * Retrieves the current amount of vespene gas that this player has. Note This function will return 0 if the player is inaccessible. Returns Amount of gas that the player currently has for spending.
     */
    int gas();

    /**
     * Retrieves the cumulative amount of minerals/ore that this player has gathered since the beginning of the game, including the amount that the player starts the game with (if any). Note This function will return 0 if the player is inaccessible. Returns Cumulative amount of minerals that the player has gathered.
     */
    int gatheredMinerals();

    /**
     * Retrieves the cumulative amount of vespene gas that this player has gathered since the beginning of the game, including the amount that the player starts the game with (if any). Note This function will return 0 if the player is inaccessible. Returns Cumulative amount of gas that the player has gathered.
     */
    int gatheredGas();

    /**
     * Retrieves the cumulative amount of minerals/ore that this player has spent on repairing units since the beginning of the game. This function only applies to Terran players. Note This function will return 0 if the player is inaccessible. Returns Cumulative amount of minerals that the player has spent repairing.
     */
    int repairedMinerals();

    /**
     * Retrieves the cumulative amount of vespene gas that this player has spent on repairing units since the beginning of the game. This function only applies to Terran players. Note This function will return 0 if the player is inaccessible. Returns Cumulative amount of gas that the player has spent repairing.
     */
    int repairedGas();

    /**
     * Retrieves the cumulative amount of minerals/ore that this player has gained from refunding (cancelling) units and structures. Note This function will return 0 if the player is inaccessible. Returns Cumulative amount of minerals that the player has received from refunds.
     */
    int refundedMinerals();

    /**
     * Retrieves the cumulative amount of vespene gas that this player has gained from refunding (cancelling) units and structures. Note This function will return 0 if the player is inaccessible. Returns Cumulative amount of gas that the player has received from refunds.
     */
    int refundedGas();

    /**
     * Retrieves the cumulative amount of minerals/ore that this player has spent, excluding repairs. Note This function will return 0 if the player is inaccessible. Returns Cumulative amount of minerals that the player has spent.
     */
    int spentMinerals();

    /**
     * Retrieves the cumulative amount of vespene gas that this player has spent, excluding repairs. Note This function will return 0 if the player is inaccessible. Returns Cumulative amount of gas that the player has spent.
     */
    int spentGas();

    /**
     * Retrieves the total amount of supply the player has available for unit control. Note In Starcraft programming, the managed supply values are double than what they appear in the game. The reason for this is because Zerglings use 0.5 visible supply. In Starcraft, the supply for each race is separate. Having a Pylon and an Overlord will not give you 32 supply. It will instead give you 16 Protoss supply and 16 Zerg supply. Parameters race (optional) The race to query the total supply for. If this is omitted, then the player's current race will be used. Returns The total supply available for this player and the given race. Example usage: if ( BWAPI::Broodwar->self()->supplyUsed() + 8 >= BWAPI::Broodwar->self()->supplyTotal() ) { // Construct pylons, supply depots, or overlords } See also supplyUsed
     */
    int supplyTotal();

    int supplyTotal(Race race);

    /**
     * Retrieves the current amount of supply that the player is using for unit control. Parameters race (optional) The race to query the used supply for. If this is omitted, then the player's current race will be used. Returns The supply that is in use for this player and the given race. See also supplyTotal
     */
    int supplyUsed();

    int supplyUsed(Race race);

    /**
     * Retrieves the total number of units that the player has. If the information about the player is limited, then this function will only return the number of visible units. Parameters unit (optional) The unit type to query. UnitType macros are accepted. If this parameter is omitted, then it will use UnitTypes::AllUnits by default. Returns The total number of units of the given type that the player owns. See also visibleUnitCount, completedUnitCount, incompleteUnitCount
     */
    int allUnitCount();

    int allUnitCount(UnitType unit);

    /**
     * Retrieves the total number of strictly visible units that the player has, even if information on the player is unrestricted. Parameters unit (optional) The unit type to query. UnitType macros are accepted. If this parameter is omitted, then it will use UnitTypes::AllUnits by default. Returns The total number of units of the given type that the player owns, and is visible to the BWAPI player. See also allUnitCount, completedUnitCount, incompleteUnitCount
     */
    int visibleUnitCount();

    int visibleUnitCount(UnitType unit);

    /**
     * Retrieves the number of completed units that the player has. If the information about the player is limited, then this function will only return the number of visible completed units. Parameters unit (optional) The unit type to query. UnitType macros are accepted. If this parameter is omitted, then it will use UnitTypes::AllUnits by default. Returns The number of completed units of the given type that the player owns. Example usage: bool obtainNextUpgrade(BWAPI::UpgradeType upgType) { BWAPI::Player self = BWAPI::Broodwar->self(); int maxLvl = self->getMaxUpgradeLevel(upgType); int currentLvl = self->getUpgradeLevel(upgType); if ( !self->isUpgrading(upgType) && currentLvl < maxLvl && self->completedUnitCount(upgType.whatsRequired(currentLvl+1)) > 0 && self->completedUnitCount(upgType.whatUpgrades()) > 0 ) return self->getUnits().upgrade(upgType); return false; } See also allUnitCount, visibleUnitCount, incompleteUnitCount
     */
    int completedUnitCount();

    int completedUnitCount(UnitType unit);

    /**
     * Retrieves the number of incomplete units that the player has. If the information about the player is limited, then this function will only return the number of visible incomplete units. Note This function is a macro for allUnitCount() - completedUnitCount(). Parameters unit (optional) The unit type to query. UnitType macros are accepted. If this parameter is omitted, then it will use UnitTypes::AllUnits by default. Returns The number of incomplete units of the given type that the player owns. See also allUnitCount, visibleUnitCount, completedUnitCount
     */
    int incompleteUnitCount();

    int incompleteUnitCount(UnitType unit);

    /**
     * Retrieves the number units that have died for this player. Parameters unit (optional) The unit type to query. UnitType macros are accepted. If this parameter is omitted, then it will use UnitTypes::AllUnits by default. Returns The total number of units that have died throughout the game.
     */
    int deadUnitCount();

    int deadUnitCount(UnitType unit);

    /**
     * Retrieves the number units that the player has killed. Parameters unit (optional) The unit type to query. UnitType macros are accepted. If this parameter is omitted, then it will use UnitTypes::AllUnits by default. Returns The total number of units that the player has killed throughout the game.
     */
    int killedUnitCount();

    int killedUnitCount(UnitType unit);

    /**
     * Retrieves the current upgrade level that the player has attained for a given upgrade type. Parameters upgrade The UpgradeType to query. Returns The number of levels that the upgrade has been upgraded for this player. Example usage: bool obtainNextUpgrade(BWAPI::UpgradeType upgType) { BWAPI::Player self = BWAPI::Broodwar->self(); int maxLvl = self->getMaxUpgradeLevel(upgType); int currentLvl = self->getUpgradeLevel(upgType); if ( !self->isUpgrading(upgType) && currentLvl < maxLvl && self->completedUnitCount(upgType.whatsRequired(currentLvl+1)) > 0 && self->completedUnitCount(upgType.whatUpgrades()) > 0 ) return self->getUnits().upgrade(upgType); return false; } See also UnitInterface::upgrade, getMaxUpgradeLevel
     */
    int getUpgradeLevel(UpgradeType upgrade);

    /**
     * Checks if the player has already researched a given technology. Parameters tech The TechType to query. Returns true if the player has obtained the given tech, or false if they have not See also isResearching, UnitInterface::research, isResearchAvailable
     */
    boolean hasResearched(TechType tech);

    /**
     * Checks if the player is researching a given technology type. Parameters tech The TechType to query. Returns true if the player is currently researching the tech, or false otherwise See also UnitInterface::research, hasResearched
     */
    boolean isResearching(TechType tech);

    /**
     * Checks if the player is upgrading a given upgrade type. Parameters upgrade The upgrade type to query. Returns true if the player is currently upgrading the given upgrade, false otherwise Example usage: bool obtainNextUpgrade(BWAPI::UpgradeType upgType) { BWAPI::Player self = BWAPI::Broodwar->self(); int maxLvl = self->getMaxUpgradeLevel(upgType); int currentLvl = self->getUpgradeLevel(upgType); if ( !self->isUpgrading(upgType) && currentLvl < maxLvl && self->completedUnitCount(upgType.whatsRequired(currentLvl+1)) > 0 && self->completedUnitCount(upgType.whatUpgrades()) > 0 ) return self->getUnits().upgrade(upgType); return false; } See also UnitInterface::upgrade
     */
    boolean isUpgrading(UpgradeType upgrade);

    /**
     * Retrieves the maximum amount of energy that a unit type will have, taking the player's energy upgrades into consideration. Parameters unit The UnitType to retrieve the maximum energy for. Returns Maximum amount of energy that the given unit type can have.
     */
    int maxEnergy(UnitType unit);

    /**
     * Retrieves the top speed of a unit type, taking the player's speed upgrades into consideration. Parameters unit The UnitType to retrieve the top speed for. Returns Top speed of the provided unit type for this player.
     */
    double topSpeed(UnitType unit);

    /**
     * Retrieves the maximum weapon range of a weapon type, taking the player's weapon upgrades into consideration. Parameters weapon The WeaponType to retrieve the maximum range for. Returns Maximum range of the given weapon type for units owned by this player.
     */
    int weaponMaxRange(WeaponType weapon);

    /**
     * Retrieves the sight range of a unit type, taking the player's sight range upgrades into consideration. Parameters unit The UnitType to retrieve the sight range for. Returns Sight range of the provided unit type for this player.
     */
    int sightRange(UnitType unit);

    /**
     * Retrieves the weapon cooldown of a unit type, taking the player's attack speed upgrades into consideration. Parameters unit The UnitType to retrieve the damage cooldown for. Returns Weapon cooldown of the provided unit type for this player.
     */
    int weaponDamageCooldown(UnitType unit);

    /**
     * Calculates the armor that a given unit type will have, including upgrades. Parameters unit The unit type to calculate armor for, using the current player's upgrades. Returns The amount of armor that the unit will have with the player's upgrades.
     */
    int armor(UnitType unit);

    /**
     * Calculates the damage that a given weapon type can deal, including upgrades. Parameters wpn The weapon type to calculate for. Returns The amount of damage that the weapon deals with this player's upgrades.
     */
    int damage(WeaponType wpn);

    /**
     * Retrieves the total unit score, as seen in the end-game score screen. Returns The player's unit score.
     */
    int getUnitScore();

    /**
     * Retrieves the total kill score, as seen in the end-game score screen. Returns The player's kill score.
     */
    int getKillScore();

    /**
     * Retrieves the total building score, as seen in the end-game score screen. Returns The player's building score.
     */
    int getBuildingScore();

    /**
     * Retrieves the total razing score, as seen in the end-game score screen. Returns The player's razing score.
     */
    int getRazingScore();

    /**
     * Retrieves the player's custom score. This score is used in Use Map Settings game types. Returns The player's custom score.
     */
    int getCustomScore();

    /**
     * Checks if the player is an observer player, typically in a Use Map Settings observer game. An observer player does not participate in the game. Returns true if the player is observing, or false if the player is capable of playing in the game.
     */
    boolean isObserver();

    /**
     * Retrieves the maximum upgrades available specific to the player. This value is only different from UpgradeType::maxRepeats in Use Map Settings games. Parameters upgrade The UpgradeType to retrieve the maximum upgrade level for. Returns Maximum upgrade level of the given upgrade type. Example usage: bool obtainNextUpgrade(BWAPI::UpgradeType upgType) { BWAPI::Player self = BWAPI::Broodwar->self(); int maxLvl = self->getMaxUpgradeLevel(upgType); int currentLvl = self->getUpgradeLevel(upgType); if ( !self->isUpgrading(upgType) && currentLvl < maxLvl && self->completedUnitCount(upgType.whatsRequired(currentLvl+1)) > 0 && self->completedUnitCount(upgType.whatUpgrades()) > 0 ) return self->getUnits().upgrade(upgType); return false; }
     */
    int getMaxUpgradeLevel(UpgradeType upgrade);

    /**
     * Checks if a technology can be researched by the player. Certain technologies may be disabled in Use Map Settings game types. Parameters tech The TechType to query. Returns true if the tech type is available to the player for research.
     */
    boolean isResearchAvailable(TechType tech);

    /**
     * Checks if a unit type can be created by the player. Certain unit types may be disabled in Use Map Settings game types. Parameters unit The UnitType to check. Returns true if the unit type is available to the player.
     */
    boolean isUnitAvailable(UnitType unit);

    /**
     * Verifies that this player satisfies a unit type requirement. This verifies complex type requirements involving morphable Zerg structures. For example, if something requires a Spire, but the player has (or is in the process of morphing) a Greater Spire, this function will identify the requirement. It is simply a convenience function that performs all of the requirement checks. Parameters unit The UnitType to check. amount (optional) The amount of units that are required. Returns true if the unit type requirements are met, and false otherwise. Since 4.1.2
     */
    boolean hasUnitTypeRequirement(UnitType unit);

    boolean hasUnitTypeRequirement(UnitType unit, int amount);

}