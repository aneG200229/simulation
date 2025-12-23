import simulation.Simulation;

import java.util.Scanner;

public class Menu {
    private Simulation simulation;
    private Scanner scanner;

    public Menu() {
        this.simulation = new Simulation();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        simulation.initSimulation();

        while (true) {
            if (!simulation.isRunning()) {
                showStartMenu();
            } else if (simulation.isPaused()) {
                showPauseMenu();
            } else {
                showRunningMenu();
            }

            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                handleChoice(choice);
            }catch (NumberFormatException e){
                System.out.println("Это не число!");
            }


        }
    }

    private void showStartMenu() {
        System.out.println("=== МЕНЮ СИМУЛЯЦИИ ===");
        System.out.println("1. Запустить бесконечную симуляцию");
        System.out.println("2. Сделать один ход");
        System.out.println("3. Выход");
    }

    private void showRunningMenu() {
        System.out.println("=== БЕСКОНЕЧНАЯ СИМУЛЯЦИЯ ЗАПУЩЕНА ===");
        System.out.println("1. Пауза");
        System.out.println("2. Выход");
    }

    private void showPauseMenu() {
        System.out.println("=== ПАУЗА ===");
        System.out.println("1. Продолжить");
        System.out.println("2. Сделать один ход");
        System.out.println("3. Выход");
    }


    private void handleChoice(int choice) {
        if (!simulation.isRunning()) {
            handleStartMenu(choice);
        } else if (simulation.isPaused()) {
            handlePauseMenu(choice);
        } else {
            handleRunningMenu(choice);
        }
    }

    private void handleStartMenu(int choice) {
        switch (choice) {
            case 1 -> simulation.startSimulation();
            case 2 -> simulation.nextTurn();
            case 3 -> exit();
            default -> System.out.println("Неверный выбор");
        }
    }

    private void handlePauseMenu(int choice) {
        switch (choice) {
            case 1 -> simulation.pauseSimulation(); // продолжить
            case 2 -> simulation.nextTurn();
            case 3 -> exit();
            default -> System.out.println("Неверный выбор");
        }
    }


    private void handleRunningMenu(int choice) {
        switch (choice) {
            case 1 -> simulation.pauseSimulation();
            case 2 -> exit();
            default -> System.out.println("Неверный выбор");
        }
    }

    private void exit() {
        simulation.stopSimulation();
        System.out.println("Выход из симуляции");
        scanner.close();
        System.exit(0);
    }


}
