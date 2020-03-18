
package edu.iastate.cs228.game;

import java.awt.Color;
import java.io.File;
import java.util.Arrays;

/**
 * The main Agent interface you must implement.<br />
 * 
 * All Agent-implementing classes must have a default constructor (no
 * parameters). You may assume setColor() and setOpponentColor() are 
 * called only once and before your Agent is used - these would be 
 * parameters for your constructor, but for technical reasons, this cannot be done.
 */
public interface Agent
{
   /**
    * This method must return your first name.
    * 
    * @return student's first name
    */
   public String getFirstName();

   /**
    * This method must return your last name. 
    * 
    * @return student's last name
    */
   public String getLastName();

   /** 
    * This method must return your student ID. 
    * 
    * @return the student's ISU ID
    */
   public String getStuID();

   /** 
    * This method must return your username.
    * 
    * @return the students ISU username
    */
   public String getUsername();

   /** 
    * This method must return your agent's name (of your choosing). 
    * 
    * @return the agent's name
    */
   public String getAgentName();

   /**
    * Function to retrieve your image file (assuming it is present at the base
    * level of your project).
    * 
    * @return the agent's image file (if you wish to make a 128x128 icon for your
    *         agent) or null (to use the default image)
    */
   public File getAgentImage();

   /**
    * Whether your agent is a candidate for the tournament. Return true if this
    * agent is submitted for the tournament, false otherwise.
    * 
    * @return true iff you wish to enter this agent in the tournament
    */
   public boolean inTournament();

   /**
    * Function used to generate command turns from your agent.  Use the passed
    * GalaxyState to decide 3 actions for your agent.  This version
    * will only be used if this agent is run during a tournament.
    * 
    * @param g a scan of the current state of the system
    * @param energyLevel energy level corresponding to the amount of energy the agent has
    * @return an array of 3 AgentActions that indicates the agent's next 3
    *         actions if in a tournament situation. If fewer than 3 actions are
    *         specified or there are null actions, these agent moves are lost.
    */
   public AgentAction[] getCommandTurnTournament(GalaxyState g, int energyLevel);

   /**
    * Function used to generate command turns from your agent.  Use the passed
    * GalaxyState to decide 3 actions for your agent.  This version
    * will only be used if this agent is run during grading.
    * 
    * @param g a scan of the current state of the system
    * @param energyLevel energy level corresponding to the amount of energy the agent has
    * @return An array of 3 AgentActions that indicates the agents next 3
    *         actions if in an exhibition/grading situation. If fewer than 3
    *         actions are specified or there are null actions, these agent moves
    *         are lost.
    */
   public AgentAction[] getCommandTurnGrading(GalaxyState g, int energyLevel);

   /**
    * Allow the simulation to set the color of your agent. It's up to you to
    * save this information somehow.  This method is called only once, at the
    * start of each simulation.
    * 
    * @param c color your agent will appear as
    */
   public void setColor(Color c);
   
   /**
    * Allow the simulation to set the color of your agent's opponent. It's up to you to
    * save this information if you need it.  This method is called only once, at the
    * start of each simulation.
    * 
    * @param c color of your agent's opponent
    */
   public void setOpponentColor(Color c);

   /**
    * Tag interface for all agent actions
    */
   public interface AgentAction
   {
   }
   
   /**
    * The move action of an agent.  Requires the name of the destination system.
    */
   public class Move implements AgentAction
   {
      String destination;

      /**
       * Ctor that constructs a move action to the given destination.
       * 
       * @param d the name of the destination system
       */
      public Move(String d)
      {
         destination = d;
      }

      /**
       * Get the destination for this move action.
       * 
       * @return the destination system's name
       */
      public String getDestination()
      {
         return destination;
      }

      /**
       * {@inheritDoc}
       * 
       * @see java.lang.Object#toString()
       */
      @Override
      public String toString()
      {
         return "Move(" + destination + ")";
      }
   }
   
   /**
    * The refuel action of an agent.
    */
   public class Refuel implements AgentAction
   {
      /**
       * {@inheritDoc}
       * 
       * @see java.lang.Object#toString()
       */
      @Override
      public String toString()
      {
         return "Refuel()";
      }
   }

   /**
    * The fortify action of an agent.  Requires the amount of energy spent on fortifying.
    */
   public class Fortify implements AgentAction
   {
      int energySpent;

      /**
       * Ctor that constructs a fortify action using the given amount of energy
       * 
       * @param e the amount of energy to be spent on fortification
       */
      public Fortify(int e)
      {
         energySpent = e;
      }
      
      /**
       * Get the amount of energy that will be spent fortifying in this fortify action.
       * 
       * @return the amount of energy to be spent on fortification
       */
      public int getEnergySpent()
      {
         return energySpent;
      }

      /**
       * {@inheritDoc}
       * 
       * @see java.lang.Object#toString()
       */
      @Override
      public String toString()
      {
         return "Fortify(" + energySpent + ")";
      }
   }

   /**
    * The setscan action of an agent.  
    * Requires amount of energy to spend during each scan.
    */
   public class SetScan implements AgentAction
   {
      int energyToSpend;

      /**
       * Ctor that constructs a setscan action given the amount of energy to spend on scanning.
       * 
       * @param e the amount of energy to be used to scan
       */
      public SetScan(int e)
      {
         energyToSpend = e;
      }
      
      /**
       * Gets the amount of energy that will be used to scan after this scan action.
       * 
       * @return the amount of enery used to scan
       */
      public int getEnergyToSpend()
      {
         return energyToSpend;
      }

      /**
       * {@inheritDoc}
       * 
       * @see java.lang.Object#toString()
       */
      @Override
      public String toString()
      {
         return "SetScan(" + energyToSpend + ")";
      }
   }

   /**
    * The capture action of an agent.  Requires energy spent on capture.
    */
   public class Capture implements AgentAction
   {
      int energySpent;

      /**
       * Ctor that constructs a capture action that will use the given amount of energy during capture.
       * 
       * @param e the amount of energy to use on the capture
       */
      public Capture(int e)
      {
         energySpent = e;
      }

      /**
       * Gets the amount of energy spent during this capture action.
       * 
       * @return the amount of energy used during capture
       */
      public int getEnergySpent()
      {
         return energySpent;
      }
      
      /**
       * {@inheritDoc}
       * 
       * @see java.lang.Object#toString()
       */
      @Override
      public String toString()
      {
         return "Capture(" + energySpent + ")";
      }
   }

   /**
    * The continue capture action of an agent.
    */
   public class ContinueCapture implements AgentAction
   {
      /**
       * {@inheritDoc}
       * 
       * @see java.lang.Object#toString()
       */
      @Override
      public String toString()
      {
         return "ContinueCapture()";
      }
   }

   /**
    * The shoot action of an agent.  Requires energy spent on shot
    * and path from the agent's current system (exclusive)
    * to the target (inclusive).
    */
   public class Shoot implements AgentAction
   {
      int energyToSpend;
      String[] path;

      /**
       * Ctor that constructs a shoot action that represents an ion torpedo being shot
       * along the given path using the given amount of energy.
       * 
       * @param p the path the ion torpedo should take from the current system to the 
       *        target system, not including the current system itself
       * @param e the amount of energy to provide the torpedo for this shoot action
       */
      public Shoot(String[] p, int e)
      {
         path = new String[p.length];
         
         for (int s = 0; s < path.length; s++)
         {
            path[s] = p[s];
         }
         
         energyToSpend = e;
      }

      /**
       * Gets the energy provided to the ion torpedo for this shoot action.
       * 
       * @return the energy the ion torpedo is given for this shoot action.
       */
      public int getEnergyToSpend()
      {
         return energyToSpend;
      }

      /**
       * Returns the path the ion torpedo is supposed to take.  Note that this
       * returns the actual path, not a copy.
       * 
       * @return the path the ion torpedo is supposed to take
       */
      public String[] getPath()
      {
         return path;
      }
      
      /**
       * {@inheritDoc}
       * 
       * @see java.lang.Object#toString()
       */
      @Override
      public String toString()
      {
         return "Shoot(" + Arrays.toString(path) + "," + energyToSpend + ")";
      }
   }

   /**
    * The no action action of an agent.
    */
   public class NoAction implements AgentAction
   {
      /**
       * {@inheritDoc}
       * 
       * @see java.lang.Object#toString()
       */
      @Override
      public String toString() 
      {
         return "NoAction()";
      }
   }
}
