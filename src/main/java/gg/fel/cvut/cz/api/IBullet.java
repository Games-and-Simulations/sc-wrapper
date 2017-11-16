package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.enums.BulletType;

/**
 * An interface object representing a bullet or missile spawned from an attack. The IBullet interface allows you to detect bullets, missiles, and other types of non-melee attacks or special abilities that would normally be visible through human eyes (A lurker spike or a Queen's flying parasite), allowing quicker reaction to unavoidable consequences. For example, ordering medics to restore units that are about to receive a lockdown to compensate for latency and minimize its effects. You can't know entirely which unit will be receiving a lockdown unless you can detect the lockdown missile using the IBullet class. IBullet objects are re-used after they are destroyed, however their ID is updated when it represents a new IBullet. If Flag::CompleteMapInformation is disabled, then a IBullet is accessible if and only if it is visible. Otherwise if Flag::CompleteMapInformation is enabled, then all Bullets in the game are accessible. See also IGame::getBullets, BulletInterface::exists
 */
public interface IBullet {

    /**
     * Retrieves a unique identifier for the current IBullet. Returns An integer value containing the identifier.
     */
    int getID();

    /**
     * Checks if the IBullet exists in the view of the BWAPI player. Return values true If the bullet exists or is visible. false If the bullet was destroyed or has gone out of scope. If Flag::CompleteMapInformation is disabled, and a IBullet is not visible, then the return value will be false regardless of the IBullet's true existence. This is because absolutely no state information on invisible enemy bullets is made available to the AI. If Flag::CompleteMapInformation is enabled, then this function is accurate for all IBullet information. See also isVisible, UnitInterface::exists
     */
    boolean exists();

    /**
     * Retrieves the IPlayer interface that owns the IBullet. Return values nullptr If the IPlayer object for this IBullet is inaccessible. Returns The owning IPlayer interface object.
     */
    IPlayer getPlayer();

    /**
     * Retrieves the type of this IBullet. Return values BulletTypes::Unknown if the IBullet is inaccessible. Returns A BulletType representing the IBullet's type.
     */
    BulletType getType();

    /**
     * Retrieves the IUnit interface that the IBullet spawned from. Return values nullptr If the source can not be identified or is inaccessible. Returns The owning IUnit interface object. See also getTarget
     */
    IUnit getSource();

    /**
     * Retrieves the IBullet's current position. Return values Positions::Unknown If the IBullet is inaccessible. Returns A IPosition containing the IBullet's current coordinates. See also getTargetPosition
     */
    IPosition getPosition();

    /**
     * Retrieve's the direction the IBullet is facing. If the angle is 0, then the IBullet is facing right. Return values 0.0 If the bullet is inaccessible. Returns A double representing the direction the IBullet is facing.
     */
    double getAngle();

    /**
     * Retrieves the X component of the IBullet's velocity, measured in pixels per frame. Return values 0.0 if the IBullet is inaccessible. Returns A double representing the number of pixels moved on the X axis per frame. See also getVelocityY, getAngle
     */
    double getVelocityX();

    /**
     * Retrieves the Y component of the IBullet's velocity, measured in pixels per frame. Return values 0.0 if the IBullet is inaccessible. Returns A double representing the number of pixels moved on the Y axis per frame. See also getVelocityX, getAngle
     */
    double getVelocityY();

    /**
     * Retrieves the IUnit interface that the IBullet is heading to. Return values nullptr If the IBullet's target IUnit is inaccessible, the IBullet is targetting the ground, or if the IBullet itself is inaccessible. Returns The target IUnit interface object, if one exists. See also getTargetPosition, getSource
     */
    IUnit getTarget();

    /**
     * Retrieves the target position that the IBullet is heading to. Return values Positions::Unknown If the bullet is inaccessible. Returns A IPosition indicating where the IBullet is headed. See also getTarget, getPosition
     */
    IPosition getTargetPosition();

    /**
     * Retrieves the timer that indicates the IBullet's life span. Bullets are not permanent objects, so they will often have a limited life span. This life span is measured in frames. Normally a IBullet will reach its target before being removed. Return values 0 If the IBullet is inaccessible. Returns An integer representing the remaining number of frames until the IBullet self-destructs.
     */
    int getRemoveTimer();

    /**
     * Retrieves the visibility state of the IBullet. Parameters player (optional) If this parameter is specified, then the IBullet's visibility to the given player is checked. If this parameter is omitted, then a default value of nullptr is used, which will check if the BWAPI player has vision of the IBullet. Note If player is nullptr and Broodwar->self() is also nullptr, then the visibility of the IBullet is determined by checking if at least one other player has vision of the IBullet. Return values true If the IBullet is visible to the specified player. false If the IBullet is not visible to the specified player.
     */
    boolean isVisible();

    boolean isVisible(IPlayer player);

}
