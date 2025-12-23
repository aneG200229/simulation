package simulation;

import actions.Action;
import actions.initactions.*;
import actions.turnactions.MoveCreaturesAction;
import actions.turnactions.TurnSpawnAction;
import entities.*;
import gamemap.GameMap;
import rendering.GameMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final GameMap map;
    private final GameMapRenderer gameMapRenderer;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private int turnCounter = 0;
    private volatile boolean running = false;
    private volatile boolean paused = false;
    private Thread simulationThread;


    public Simulation() {
        this.map = new GameMap(6,6);
        this.gameMapRenderer = new GameMapRenderer();

        this.initActions = new ArrayList<>(List.of(
                new InitSpawnAction((c) -> new Grass(), 4),
                new InitSpawnAction(Predator::new,2),
                new InitSpawnAction(Herbivore::new,3),
                new InitSpawnAction((c)-> new Rock(),2),
                new InitSpawnAction((c)-> new Tree(),2)
        ));

        this.turnActions = new ArrayList<>(List.of(
                new MoveCreaturesAction(),
                new TurnSpawnAction((c)-> new Grass(),4,(entity)-> entity instanceof Grass),
                new TurnSpawnAction((c) -> new Herbivore(c),3,entity -> entity instanceof Herbivore)
        ));
    }

    public void initSimulation() {
        for (Action action : initActions) {
            action.perform(map);
        }
        System.out.println("=== СИМУЛЯЦИЯ ЗАПУЩЕНА ===");
        gameMapRenderer.drawMap(map);
        System.out.println();
    }

    public void nextTurn() {
        turnCounter++;
        System.out.println("=== ХОД " + turnCounter + " ===");

        for (Action action : turnActions) {
            action.perform(map);
        }
        gameMapRenderer.drawMap(map);
        System.out.println();
    }

    public void startSimulation() {
        if (running) {
            return;
        }
        running = true;

        simulationThread = new Thread(() -> {
            while (running) {
                waitIfPaused();
                nextTurn();
                sleep(2000);
            }
        });
        simulationThread.start();
    }

    public void pauseSimulation() {
        synchronized (this) {
            paused = !paused;
            if (!paused) {
                notifyAll();
            }
        }
    }

    private void waitIfPaused() {
        synchronized (this) {
            while (paused) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void stopSimulation() {
        running = false;
        paused = false;
        synchronized (this) {
            notifyAll();
        }
    }


    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPaused() {
        return paused;
    }
}
