package edu.iastate.cs228.game;

import java.awt.Color;

/**
 * A static copy of the state of the galaxy.
 */
public interface GalaxyState
{
   /**
    * Return all system states scanned.
    * 
    * @return Array storing references to each System’s current state.
    */
   public SystemState[] getSystems();
   
   /**
    * Retrieve the state of the system in which the agent of the given color is, if
    * that system is within your agent's scan range.
    * 
    * @param agentColor the color of the query agent
    * @return the SystemState for the system in which the given agent currently is or
    *         null if the agent isn't found in the scan
    */
   public SystemState getCurrentSystemFor(Color agentColor);
}
