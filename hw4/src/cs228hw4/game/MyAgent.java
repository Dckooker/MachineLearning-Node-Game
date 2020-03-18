/**
 * @author Devon Kooker
 */
package cs228hw4.game;

import java.awt.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cs228hw4.graph.CS228Dijkstra;
import cs228hw4.graph.DiGraph;
import cs228hw4.graph.GraphClass;
import edu.iastate.cs228.game.Agent;
import edu.iastate.cs228.game.GalaxyState;
import edu.iastate.cs228.game.SystemState;

public class MyAgent implements Agent {

	Color agentColor;
	Color enemyColor;
	int firstMove = 0;
	int changeGraph = 0;
	int setScan = 0;

	@Override
	public String getFirstName() {
		return "Devon";
	}

	@Override
	public String getLastName() {
		return "Kooker";
	}

	@Override
	public String getStuID() {
		return "568308762";
	}

	@Override
	public String getUsername() {
		return "NitroTree";
	}

	@Override
	public String getAgentName() {
		return "Mendax";
	}

	@Override
	public File getAgentImage() {
		File pic = new File("BeSquare2.jpg");
		return pic;
	}

	@Override
	public boolean inTournament() {
		return false;
	}

	@Override
	public void setColor(Color c) {
		agentColor = c;
	}

	@Override
	public void setOpponentColor(Color c) {
		enemyColor = c;
	}

	// Harder Methods
	//////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Fortify fortify; Move move; Refuel refuel; SetScan setScan; Capture capture;
	 * ContinueCapture continueCapture; Shoot shoot; NoAction noAction;
	 **/

	@Override
	public AgentAction[] getCommandTurnTournament(GalaxyState g, int energyLevel) {
		return getCommandTurnGrading(g, energyLevel);
	}

	GraphClass<SystemState> bigGraph = null;
	GraphClass<SystemState> smallGraph = null;
	boolean notInRange = false;
	boolean bigJump = false;
	boolean cap = false;
	boolean mov = false;
	boolean scanning = false;
	String prevAction = null;
	int getShot = 0;

	@Override
	public AgentAction[] getCommandTurnGrading(GalaxyState g, int energyLevel) {
		SystemState currentSys = g.getCurrentSystemFor(agentColor);
		int scannedSystems = 0;
		// bigGraph = new GraphClass<SystemState>(g);
		CS228Dijkstra<SystemState> DijkstraBig = new CS228Dijkstra<SystemState>(bigGraph);
		CS228Dijkstra<SystemState> DijkstraSmall = null;
		AgentAction[] actions = new AgentAction[3];
		SystemState NextMove = null;
		int actionNum = 0;
		boolean moveOn = false;

		Capture capture = new Capture(currentSys.getCostToCapture());
		Fortify fortify = new Fortify(2);
		Refuel refuel = new Refuel();

		while (actionNum <= 2) {

			// First Move set is a Large Scan, capture, and fortify
			if (firstMove == 0) {
				actions[actionNum] = new SetScan(20);
				actionNum++;
				setScan = 20;
				System.out.println("big Scan done");
				actions[actionNum] = new NoAction();
				actionNum++;
				actions[actionNum] = new NoAction();
				actionNum++;
				System.out.println("first move set done");
				firstMove++;
			}

			// Every other move is determined here
			else if (energyLevel != 0 && firstMove == 1) {
				if (setScan == 20) {
					bigGraph = new GraphClass<SystemState>(g);
					actions[actionNum] = new SetScan(3);
					System.out.println("setScan 3");
					actionNum++;
					setScan = 3;
				}

				if (currentSys.getOwner() != agentColor && cap == false) {
					smallGraph = new GraphClass<SystemState>(g);
					if (energyLevel < currentSys.getCostToCapture() && setScan == 3 || moveOn == false && getShot == 0) {

						System.out.println("try to shoot");
						SystemState enemySystem = g.getCurrentSystemFor(enemyColor);

						int smallGraphSize1 = smallGraph.numVerts();
						String inRangeVerts = smallGraph.getVerts();
						if (smallGraph.Verts.containsKey(enemySystem)) {
							DijkstraSmall = new CS228Dijkstra<SystemState>(smallGraph);
							smallGraph = new GraphClass<SystemState>(g);
							int smallGraphSize2 = smallGraph.numVerts();
							DijkstraSmall.run(currentSys);
							// System.out.println(DijkstraBig.getShortestPath(enemySystem));
							List<SystemState> path = DijkstraSmall.getShortestPath(enemySystem);
							String[] finalPath = new String[path.size()-1];
							String printFinalPath = "";

							for (int i = 1; i <= path.size()-1; i++) {

								finalPath[i-1] = path.get(i).getName();
								printFinalPath += path.get(i).getName() + " ";
							}

							System.out.println("Final Path: " + printFinalPath);
							actions[actionNum] = new Shoot(finalPath, 2);
							actionNum++;
							getShot++;
							System.out.println("getShot");
						}
						/**
						 * else {
						 * 
						 * if (scanning == false) {
						 * 
						 * System.out.println("not in range, need to scan"); actions[actionNum] = new
						 * SetScan(3); actionNum++; System.out.println("setScan 3"); actionNum++;
						 * scanning = true; }
						 **/

						else {
							System.out.println(" not in range, move on");
							scanning = false;
							moveOn = true;

						}

					}

					else {

						if (cap == false && mov == false || actions[0] == null) {
							actions[actionNum] = capture;
							actionNum++;
							System.out.println("capture");
							cap = true;
							mov = false;

						} else if (NextMove != null) {
							if (NextMove.getOwner() != agentColor) {
								actions[actionNum] = new Capture(NextMove.getCostToCapture());
								actionNum++;
								System.out.println("capture");
								mov = false;

							}
						}
					}
				}

				else {

					if (actionNum != 0) {
						prevAction = actions[actionNum - 1].toString();
					}
					if (mov == false || prevAction.contains("Move")) {
						smallGraph = new GraphClass<SystemState>(g);
						scannedSystems = bigGraph.numVerts();
						ArrayList<SystemState> badNeighbors = new ArrayList<SystemState>();
						// ArrayList<SystemState> goodNeighbors = new ArrayList<SystemState>();
						for (SystemState system : currentSys.getNeighbors()) {

							smallGraph = new GraphClass<SystemState>(g);
							if (system != null) {
								if (system.getOwner() != agentColor) {
									badNeighbors.add(system);
								}
							}

						}
						smallGraph = new GraphClass<SystemState>(g);
						if (badNeighbors.size() == 0) {
							actions[actionNum] = new Move(smallGraph.neighborWithLeastCost(currentSys).toString());
							NextMove = smallGraph.neighborWithLeastCost(currentSys);
							System.out.println("move to good: " + currentSys);
						} else {
							actions[actionNum] = new Move(
									smallGraph.badNeighborWithLeastCost(currentSys, badNeighbors).toString());
							NextMove = (smallGraph.badNeighborWithLeastCost(currentSys, badNeighbors));
							System.out.println("move to bad: " + currentSys);
						}
						actionNum++;
						mov = true;
						cap = false;
						getShot = 0;
					}

				}

			}

			else {
				scannedSystems = bigGraph.numVerts();
				actions[actionNum] = new Refuel();
				System.out.println("refuel ");
				actionNum++;
				bigJump = false;
				mov = false;
			}

		}

		mov = false;
		System.out.println(" ");
		return actions;

		//////////////////////////////////////////////////////////////////////////////////////////////

	}
}
