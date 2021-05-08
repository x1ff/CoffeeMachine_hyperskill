package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private static final int WATER_FOR_ESPRESSO = 250;
    private static final int MILK_FOR_ESPRESSO = 0;
    private static final int COFFEE_FOR_ESPRESSO = 16;
    private static final int PRICE_FOR_ESPRESSO = 4;

    private static final int WATER_FOR_LATTE = 350;
    private static final int MILK_FOR_LATTE = 75;
    private static final int COFFEE_FOR_LATTE = 20;
    private static final int PRICE_FOR_LATTE = 7;

    private static final int WATER_FOR_CAPPUCCINO = 200;
    private static final int MILK_FOR_CAPPUCCINO = 100;
    private static final int COFFEE_FOR_CAPPUCCINO = 12;
    private static final int PRICE_FOR_CAPPUCCINO = 6;

    private int currentMlOfWater;
    private int currentMlOfMilk;
    private int currentGOfCoffee;
    private int currentDollarsCount;
    private int currentCapsCount;
    private machineState state;

    /**
     * Create coffee machine
     * @param water init ml of water
     * @param milk init ml of milk
     * @param coffee init g of coffee beans
     * @param dollars init $ of money
     * @param cups init disposable cups
     * @param state of machine @see machineState.java enum
     */
    public CoffeeMachine(
            int water, int milk, int coffee, int dollars, int cups, machineState state) {
        this.currentMlOfWater = water;
        this.currentMlOfMilk = milk;
        this.currentGOfCoffee = coffee;
        this.currentDollarsCount = dollars;
        this.currentCapsCount = cups;
        this.state = state;
    }

    /**
     * Single public method to communicate with the coffee machine
     * @param input is what user in
     */
    public void processInput(String input) {
        switch (this.state) {
            case CHOOSING_ACTION: {
                this.doAction(input);
                break;
            }
            case CHOOSING_COFFEE: {
                this.chooseAndBuyCoffee(input);
                this.setState(machineState.CHOOSING_ACTION);
                break;
            }
            case FILLING_WHATER: {
                this.fillWater(Integer.parseInt(input));
                this.setState(machineState.FILLING_MILK);
                break;
            }
            case FILLING_MILK: {
                this.fillMilk(Integer.parseInt(input));
                this.setState(machineState.FILLING_COFFEE);
                break;
            }
            case FILLING_COFFEE: {
                this.fillCoffee(Integer.parseInt(input));
                this.setState(machineState.FILLING_CAPS);
                break;
            }
            case FILLING_CAPS: {
                this.fillCaps(Integer.parseInt(input));
                this.setState(machineState.CHOOSING_ACTION);
                break;
            }
        }
    }

    /**
     * Method parse action menu
     * @param action is what action user choose
     */
    private void doAction(String action) {
        if (isValidAction(action)) {
            if ("buy".equals(action)) {
                this.setState(machineState.CHOOSING_COFFEE);
            }
            if ("fill".equals(action)) {
                this.setState(machineState.FILLING_WHATER);
            }
            if ("take".equals(action)) {
                this.take();
            }
            if ("remaining".equals(action)) {
                this.remaining();
            }
            if ("exit".equals(action)) {
                this.setState(machineState.NO_ACTIVE);
            }
        }
    }

    /**
     * 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu
     * @param coffeeType is a type of coffee what user choose
     */
    private void chooseAndBuyCoffee(String coffeeType) {
        switch (coffeeType) {
            case "back":
                return;
            case "1":
                if (isAllowed(WATER_FOR_ESPRESSO, MILK_FOR_ESPRESSO, COFFEE_FOR_ESPRESSO)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    buyCoffee(WATER_FOR_ESPRESSO, MILK_FOR_ESPRESSO, COFFEE_FOR_ESPRESSO, PRICE_FOR_ESPRESSO);
                }
                break;
            case "2":
                if (isAllowed(WATER_FOR_LATTE, MILK_FOR_LATTE, COFFEE_FOR_LATTE)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    buyCoffee(WATER_FOR_LATTE, MILK_FOR_LATTE, COFFEE_FOR_LATTE, PRICE_FOR_LATTE);
                }
                break;
            case "3":
                if (isAllowed(WATER_FOR_CAPPUCCINO, MILK_FOR_CAPPUCCINO, COFFEE_FOR_CAPPUCCINO)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    buyCoffee(WATER_FOR_CAPPUCCINO, MILK_FOR_CAPPUCCINO, COFFEE_FOR_CAPPUCCINO, PRICE_FOR_CAPPUCCINO);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Print current resources
     */
    private void remaining() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d ml of water\n", this.currentMlOfWater);
        System.out.printf("%d ml of milk\n", this.currentMlOfMilk);
        System.out.printf("%d g of coffee beans\n", this.currentGOfCoffee);
        System.out.printf("%d disposable cups\n", this.currentCapsCount);
        System.out.printf("$%d of money\n", this.currentDollarsCount);
    }

    /**
     * Check current resources for make coffee with param
     * @param water is ml of water for compare with current
     * @param milk is ml of milk for compare with current
     * @param coffee g of coffee beans for compare with current
     * @return availability to make coffee with params
     */
    private boolean isAllowed(int water, int milk, int coffee) {
        if (water > this.currentMlOfWater) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (milk > this.currentMlOfMilk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (coffee > this.currentGOfCoffee) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }
        if (1 > this.currentCapsCount) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
        return true;
    }

    /**
     * Update current resources after sale
     * @param water is ml of water used for making cup of coffee
     * @param milk is ml of milk used for making cup of coffee
     * @param coffee g of coffee beans used for making cup of coffee
     * @param price is $ of money for making cup of coffee
     */
    private void buyCoffee(int water, int milk, int coffee, int price) {
        this.currentMlOfWater -= water;
        this.currentMlOfMilk -= milk;
        this.currentGOfCoffee -= coffee;
        this.currentDollarsCount += price;
        this.currentCapsCount--;
    }

    private void fillWater(int water) {
        this.currentMlOfWater += water;
    }

    private void fillMilk(int milk) {
        this.currentMlOfMilk += milk;
    }

    private void fillCoffee(int coffee) {
        this.currentGOfCoffee += coffee;
    }

    private void fillCaps(int capsCount) {
        this.currentCapsCount += capsCount;
    }

    private void take() {
        System.out.printf("I gave you $%d\n", currentDollarsCount);
        this.currentDollarsCount = 0;
    }

    private boolean isValidAction(String action) {
        return "buy".equals(action) || "fill".equals(action) || "take".equals(action) ||
                "remaining".equals(action) || "exit".equals(action);
    }

    private void printStateHint() {
        switch (this.state) {
            case CHOOSING_ACTION:
                System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                break;
            case CHOOSING_COFFEE:
                System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                break;
            case FILLING_WHATER:
                System.out.println("\nWrite how many ml of water you want to add:");
                break;
            case FILLING_MILK:
                System.out.println("Write how many ml of milk you want to add:");
                break;
            case FILLING_COFFEE:
                System.out.println("Write how many grams of coffee beans you want to add:");
                break;
            case FILLING_CAPS:
                System.out.println("Write how many disposable cups of coffee you want to add:");
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CoffeeMachine redmond = new CoffeeMachine(
                400, 540, 120, 550, 9, machineState.CHOOSING_ACTION);

        while (!(redmond.getState() == machineState.NO_ACTIVE)) {
            redmond.printStateHint();
            String input = sc.next();
            redmond.processInput(input);
        }
    }

    public machineState getState() {
        return state;
    }

    public void setState(machineState state) {
        this.state = state;
    }
}
