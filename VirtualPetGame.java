
//Importing neccessary Java utilities
import java.util.ArrayList;
//Importing error check for a different output than initially displayed
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//Class to represent a Pet
class Pet {
    private String name;
    private String type;
    private int hunger;
    private int happiness;
    private int health;
    private int energy;

    // Constructor to initialise Pet attributes
    public Pet(String name, String type) {
        this.name = name;
        this.type = type;
        this.hunger = 50;
        this.happiness = 35;
        this.health = 30;
        this.energy = 35;
    }

    public String getName() {
        return name;
    }

    // Return if the Pet`s health i below <=20;
    public boolean isSick() {
        return health <= 20;
    }

    // Feed Pet and reduce hunger level
    public void feed() {
        if (isSick()) {
            System.out.println(name + " is too sick to eat. Let them rest first!");
            return;
        }
        hunger = Math.max(hunger - 30, 0);
        System.out.println(name + " the " + type + " has been fed. Hunger is now: " + hunger);
    }

    // Play with Pet, increases happiness bur reduces hunger
    public void play() {
        if (isSick()) {
            System.out.println(name + " is too sick to play. Let them rest first!");
            return;
        }
        happiness = Math.min(happiness + 20, 70);
        energy = Math.max(energy - 15, 0);
        System.out.println(name + " played. Happiness: " + happiness + ", Energy: " + energy);
    }

    // Rest with pet increases energy and health
    public void rest() {
        health = Math.min(health + 15, 100);
        energy = Math.min(energy + 20, 70);
        System.out.println(name + " rested. Health: " + health + ", Energy: " + energy);
    }

    public void updateStatus() {
        hunger = Math.min(hunger + 5, 100);
        energy = Math.max(energy - 5, 0);
        happiness = Math.max(happiness - 3, 0);
        // Statements shows that if pet is too hungry or tires loses health
        if (hunger > 80 || energy < 20) {
            health = Math.max(health - 5, 0);
        }
    }

    // Display current status of Pet
    public void showStatus() {
        System.out.println("Pet: " + name + " (" + type + ")");
        System.out.println("Hunger: " + hunger + "/100");
        System.out.println("Happiness: " + happiness + "/70");
        System.out.println("Health: " + health + "/100");
        System.out.println("Energy: " + energy + "/70");
        if (isSick()) {
            System.out.println("Status: Sick (Needs rest!)");
        } else {
            System.out.println("Status: Healthy");
        }
        System.out.println("------------------------");
    }
}

// Class User who owns Pet
class User {
    private String username;
    private List<Pet> pets;

    public User(String username) {
        this.username = username;
        this.pets = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    // Add a pet to the user`s list up to 3 pets
    public void addPet(Pet pet) {
        if (pets.size() < 3) {
            pets.add(pet);
            System.out.println(pet.getName() + " has been added to your pet list. Enjoy playing with your pet.");
        } else {
            System.out.println("You can only have up to 3 pets.");
        }
    }

    public List<Pet> getPets() {
        return pets;
    }

    // Display all Pets and their status
    public void showPets() {
        if (pets.isEmpty()) {
            System.out.println("You have no pets yet.");
        } else {
            for (Pet pet : pets) {
                pet.showStatus();
            }
        }
    }
}

// Helper class to define preselect Pet before user selects
class PetTemplate {
    String name;
    String type;

    public PetTemplate(String name, String type) {
        this.name = name;
        this.type = type;

    }
}

// Main Game class
public class VirtualPetGame {
    private static Scanner scanner = new Scanner(System.in);
    private static List<PetTemplate> availablePets = new ArrayList<>();
    // Static block for Default Pet list
    static {
        availablePets.add(new PetTemplate("Felix", "Cat"));
        availablePets.add(new PetTemplate("Pluto", "Dog"));
        availablePets.add(new PetTemplate("Rio", "Bird"));
        availablePets.add(new PetTemplate("Bunny", "Rabbit"));
        availablePets.add(new PetTemplate("Whitetooth", "Wolf"));
        availablePets.add(new PetTemplate("Leila", "Fish"));
        availablePets.add(new PetTemplate("Funky", "Monkey"));
        availablePets.add(new PetTemplate("Dio", "Dove"));
        availablePets.add(new PetTemplate("Andy", "Chinchilla"));
        availablePets.add(new PetTemplate("Teo", "Hamster"));
        availablePets.add(new PetTemplate("Paws", "Tiger"));
        availablePets.add(new PetTemplate("Kong", "Donkey"));
        availablePets.add(new PetTemplate("Flex", "Turtle"));
        availablePets.add(new PetTemplate("Kilo", "Pigeon"));
        availablePets.add(new PetTemplate("Bugsy", "Bunny"));
        availablePets.add(new PetTemplate("Kevo", "Goat"));
        availablePets.add(new PetTemplate("Snappy", "Crocodile"));
        availablePets.add(new PetTemplate("Sunny", "Canary"));
        availablePets.add(new PetTemplate("Thumper", "Ponny"));
        availablePets.add(new PetTemplate("Snowbell", "Mouse"));

    }

    // Welcome Menu for the Game
    public static void main(String[] args) {
        System.out.println("--------------------------------");
        System.out.println("Welcome to the Virtual Pet Game!");
        System.out.println("--------------------------------");
        List<User> users = new ArrayList<>();
        // User Menu to make your choice from the predefined list
        String[] usernames = { "Raynold", "Scarlet", "Michael", "Lora", "Steven" };

        for (String username : usernames) {
            users.add(new User(username));
        }

        boolean running = true;
        while (running) {
            System.out.println("\n--- User List ---");
            for (int i = 0; i < users.size(); i++) {
                System.out.println((i + 1) + ". " + users.get(i).getUsername());
            }
            System.out.println("---------------------------------");
            System.out.print("Select a user (1-" + users.size() + ") or 0 to exit: ");

            try {
                int selectedUserIndex = scanner.nextInt() - 1;
                scanner.nextLine();

                if (selectedUserIndex == -1) {
                    System.out.println("----------------------------");
                    System.out.println("Thanks for playing! Goodbye!");
                    System.out.println("----------------------------");
                    break;
                }

                if (selectedUserIndex < 0 || selectedUserIndex >= users.size()) {
                    System.out.println("Invalid selection.");
                    continue;
                }

                User currentUser = users.get(selectedUserIndex);
                if (currentUser.getPets().isEmpty()) {
                    System.out.println("\n" + currentUser.getUsername() + ", choose 3 pets from the list:");
                    int petsChosen = 0;
                    // Main Game loop
                    while (petsChosen < 3 && !availablePets.isEmpty()) {
                        // Show list of available Pets
                        System.out.println("------------------");
                        System.out.println("\nAvailable Pets:");
                        System.out.println("------------------");
                        for (int i = 0; i < availablePets.size(); i++) {
                            PetTemplate pt = availablePets.get(i);
                            System.out.println((i + 1) + ". " + pt.name + " the " + pt.type);
                        }

                        System.out.print("Select pet " + (petsChosen + 1) + " by number: ");
                        int petIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        // Validate user selection
                        if (petIndex < 0 || petIndex >= availablePets.size()) {
                            System.out.println("Invalid selection.");
                            continue;
                        }

                        PetTemplate selected = availablePets.remove(petIndex);
                        currentUser.addPet(new Pet(selected.name, selected.type));
                        petsChosen++;
                    }
                }
                // User Main menu to interact with Pets
                boolean backToMainMenu = false;
                while (!backToMainMenu) {
                    System.out.println("\n--- Menu for " + currentUser.getUsername() + " ---");
                    System.out.println("------------------------");
                    System.out.println("1. View Pet Status");
                    System.out.println("2. Feed a Pet");
                    System.out.println("3. Play with a Pet");
                    System.out.println("4. Rest a Pet");
                    System.out.println("5. Back to Main Menu");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine();// Consume new line
                    // Show Pet interactions options to choose one of them
                    switch (choice) {
                        case 1:
                            currentUser.showPets();
                            break;
                        case 2:
                            interactWithPet(currentUser, "feed");
                            break;
                        case 3:
                            interactWithPet(currentUser, "play");
                            break;
                        case 4:
                            interactWithPet(currentUser, "rest");
                            break;
                        case 5:
                            backToMainMenu = true;
                            break;
                        // Option to get back to main Menu and schoose another user
                        default:
                            System.out.println("Invalid option.");
                            // Invalid input is user enter a different output from the displayed options
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                scanner.nextLine();
                // Clear buffer
            }
        }
    }

    private static void interactWithPet(User user, String action) {
        List<Pet> pets = user.getPets();
        if (pets.isEmpty()) {
            System.out.println("You have no pets.");
            return;
        }

        for (Pet pet : pets) {
            pet.updateStatus();
        }
        // Show Pets and ask user to choose one
        System.out.println("\nSelect a pet to " + action + ":");
        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + ". " + pets.get(i).getName());
        }

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > pets.size()) {
                System.out.println("Invalid choice.");
                return;
                // Clear invalid inout from user
            }

            Pet selectedPet = pets.get(choice - 1);
            switch (action) {
                case "feed":
                    selectedPet.feed();
                    break;
                case "play":
                    selectedPet.play();
                    break;
                case "rest":
                    selectedPet.rest();
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
        }
    }
}