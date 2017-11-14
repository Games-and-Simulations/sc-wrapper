package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.api.enums.GameType;

import java.util.List;

/**
 * The abstract Game class is implemented by BWAPI and is the primary means of obtaining all game state information from Starcraft Broodwar. Game state information includes all units, resources, players, forces, bullets, terrain, fog of war, regions, etc.
 */
public interface Game {

    List<Region> getRegions();

    List<Chokepoint> getChokepoints();

    List<BaseLocation> getBaseLocations();

    List<BaseLocation> getStartLocations();

    BaseLocation getStartLocation(Player player);

    boolean isConnected(TilePosition a, TilePosition b);

    double getGroundDistance(TilePosition start, TilePosition end);

    /**
     * Retrieves the set of all teams/forces. Forces are commonly seen in Use Map Settings game types and some others such as Top vs Bottom and the team versions of game types. Returns Forceset containing all forces in the game.
     */
    List<Force> getForces();

    /**
     * Retrieves the set of all players in the match. This includes the neutral player, which owns all the resources and critters by default. Returns Playerset containing all players in the game.
     */
    List<Player> getPlayers();

    /**
     * Retrieves the set of all accessible units. If Flag::CompleteMapInformation is enabled, then the set also includes units that are not visible to the player. Note Units that are inside refineries are not included in this set. Returns Unitset containing all known units in the game.
     */
    List<Unit> getAllUnits();

    /**
     * Retrieves the set of all accessible bullets. Returns Bulletset containing all accessible Bullet objects.
     */
    List<Bullet> getBullets();

    /**
     * Retrieves the set of all accessible Nuke dots. Note Nuke dots are the red dots painted by a Ghost when using the nuclear strike ability. Returns Set of Positions giving the coordinates of nuke locations.
     */
    List<Position> getNukeDots();

    /**
     * Retrieves the Region interface object associated with a given identifier. Parameters regionID The identifier for the Region object. Returns Region interface object mapped to the given regionID. Return values nullptr if the given ID is invalid.
     */
    Region getRegion(int regionID);

    /**
     * Retrieves the GameType of the current game. Returns GameType indicating the rules of the match. See also GameType
     */
    GameType getGameType();

    /**
     * Retrieves the number of logical frames since the beginning of the match. If the game is paused, then getFrameCount will not increase. Returns Number of logical frames that have elapsed since the game started as an integer.
     */
    int getFrameCount();

    /**
     * Retrieves the logical frame rate of the game in frames per second (FPS). Example: BWAPI::Broodwar->setLocalSpeed(0); // Log and display the best logical FPS seen in the game static int bestFPS = 0; bestFPS = std::max(bestFPS, BWAPI::Broodwar->getFPS()); BWAPI::Broodwar->drawTextScreen(BWAPI::Positions::Origin, "%cBest: %d GFPS\nCurrent: %d GFPS", BWAPI::Text::White, bestFPS, BWAPI::Broodwar->getFPS()); Returns Logical frames per second that the game is currently running at as an integer. See also getAverageFPS
     */
    int getFPS();

    /**
     * Retrieves the average logical frame rate of the game in frames per second (FPS). Returns Average logical frames per second that the game is currently running at as a double. See also getFPS
     */
    double getAverageFPS();

    /**
     * Retrieves the width of the map in build tile units. Returns Width of the map in tiles. See also mapHeight
     */
    int mapWidth();

    /**
     * Retrieves the height of the map in build tile units. Returns Height of the map in tiles. See also mapHeight
     */
    int mapHeight();

    /**
     * Retrieves the file name of the currently loaded map. Returns Map file name as std::string object. See also mapPathName, mapName
     */
    String mapFileName();

    /**
     * Retrieves the full path name of the currently loaded map. Returns Map file name as std::string object. See also mapFileName, mapName
     */
    String mapPathName();

    /**
     * Retrieves the title of the currently loaded map. Returns Map title as std::string object. See also mapFileName, mapPathName
     */
    String mapName();

    /**
     * Checks if a given tile position is buildable. This means that, if all other requirements are met, a structure can be placed on this tile. This function uses static map data. Parameters tileX The x value of the tile to check. tileY The y value of the tile to check. includeBuildings (optional) If this is true, then this function will also check if any visible structures are occupying the space. If this value is false, then it only checks the static map data for tile buildability. This value is false by default. Returns boolean identifying if the given tile position is buildable (true) or not (false). If includeBuildings was provided, then it will return false if a structure is currently occupying the tile.
     */
    boolean isBuildable(TilePosition position);

    boolean isBuildable(TilePosition position, boolean includeBuildings);

    /**
     * Checks if the given tile position has Zerg creep on it. Parameters tileX The x tile coordinate to check. tileY The y tile coordinate to check. Return values true If the given tile has creep on it. false If the given tile does not have creep, or if it is concealed by the fog of war.
     */
    boolean hasCreep(TilePosition position);

    /**
     * Checks if the given tile position if powered by an owned Protoss Pylon for an optional unit type. Parameters tileX The x tile coordinate to check. tileY The y tile coordinate to check. unitType (optional) Checks if the given UnitType will be powered if placed at the given tile position. If omitted, then only the immediate tile position is checked for power, and the function will assume that the location requires power for any unit type. Return values true if the type at the given tile position will receive power. false if the type will be unpowered at the given tile position.
     */
    boolean hasPower(TilePosition position);

    /**
     * Checks if the given unit type can be built at the given build tile position. This function checks for creep, power, and resource distance requirements in addition to the tiles' buildability and possible units obstructing the build location. Note If the type is an addon and a builer is provided, then the location of the addon will be placed 4 tiles to the right and 1 tile down from the given position. If the builder is not given, then the check for the addon will be conducted at position. Parameters position Indicates the tile position that the top left corner of the structure is intended to go. type The UnitType to check for. builder (optional) The intended unit that will build the structure. If specified, then this function will also check if there is a path to the build site and exclude the builder from the set of units that may be blocking the build site. checkExplored (optional) If this parameter is true, it will also check if the target position has been explored by the current player. This value is false by default, ignoring the explored state of the build site. Returns true indicating that the structure can be placed at the given tile position, and false if something may be obstructing the build location.
     */

    boolean canBuildHere(TilePosition position, UnitType type);

    /**
     * Checks all the requirements in order to make a given unit type for the current player. These include resources, supply, technology tree, availability, and required units. Parameters type The UnitType to check. builder (optional) The Unit that will be used to build/train the provided unit type. If this value is nullptr or excluded, then the builder will be excluded in the check. Returns true indicating that the type can be made. If builder is provided, then it is only true if builder can make the type. Otherwise it will return false, indicating that the unit type can not be made.
     */
    boolean canMake(UnitType type);

    /**
     * Checks all the requirements in order to research a given technology type for the current player. These include resources, technology tree, availability, and required units. Parameters type The TechType to check. unit (optional) The Unit that will be used to research the provided technology type. If this value is nullptr or excluded, then the unit will be excluded in the check. checkCanIssueCommandType (optional) TODO fill this in Returns true indicating that the type can be researched. If unit is provided, then it is only true if unit can research the type. Otherwise it will return false, indicating that the technology can not be researched.
     */

    boolean canResearch(TechType type);

    /**
     * Retrieves a set of all players currently observing the game. An observer is defined typically in a Use Map Settings game type as not having any impact on the game. This means an observer cannot start with any units, and cannot have any active trigger actions that create units for it. Returns Playerset containing all currently active observer players
     */
    List<Player> observers();

    /**
     * Retrieves the Actions Per Minute (APM) that the bot is producing. Parameters includeSelects (optional) If true, the return value will include selections as individual commands, otherwise it will exclude selections. This value is false by default. Returns The number of actions that the bot has executed per minute, on average.
     */
    int getAPM();

    int getAPM(boolean includeSelects);

    /**
     * Retrieves current amount of time in seconds that the game has elapsed. Returns Time, in seconds, that the game has elapsed as an integer.
     */
    int elapsedTime();

}