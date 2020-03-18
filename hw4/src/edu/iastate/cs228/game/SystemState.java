package edu.iastate.cs228.game;

import java.awt.Color;

/**
 * A static copy of the state of a system.
 */
public interface SystemState
{
   /**
    * Retrieve the name of the system. This will be unique in this galaxy.
    * 
    * @return the name of this system
    */
   public String getName();

   /**
    * Retrieve color of this system.
    * 
    * @return the color of the agent that owns the system
    */
   public Color getOwner();

   /**
    * Retrieve number of generators in this system.
    * 
    * @return number of generator units in this system
    */
   public int getGeneratorCount();

   /**
    * Retrieve maximum number of generators that can appear in this system.
    * 
    * @return maximum number of generator units for a system
    */
   public int getMaximumGeneratorCount();

   /**
    * Retrieve amount of energy stored in this system.
    * 
    * @return amount of energy stored at this system
    */
   public int getEnergyStored();

   /**
    * Retrieve the cost to capture this system.
    * 
    * @return number of turns/energy units is costs to capture this system
    */
   public int getCostToCapture();

   /**
    * Retrieve the states of all neighbors reachable via wormhole from this
    * system. Systems out of scanning range but a neighbor of one that is within
    * range will appear as a null entry.
    * 
    * @return array of systems representing the neighbors of this system
    */
   public SystemState[] getNeighbors();

   /**
    * Array of costs to get from this system to the corresponding system
    * returned by getNeighbors(). If the neighbor is not scanned, cost to that
    * system will be 0 (as you did not scan this cost).
    * 
    * @return array of costs to travel to neighbors
    */
   public int[] getTravelCost();

   /**
    * Colors for agents present in the system. Empty array if no agents.
    * 
    * @return array of Colors representing agents present in this system
    */
   public Color[] getAgentsPresent();
}
