package assy4P3;

import java.util.*;

public class assy4P3 {

}


class Canine extends Veterinarian {
    public Canine(String name){
        super(name);
    }
    
    @Override
    public String getTitle() {
        return "Canine";
    }
}

class Feline extends Veterinarian {
    public Feline (String name) {
        super(name);
    }
    
    @Override
    public String getTitle() {
        return "Feline";
    }
}

class Avian extends Veterinarian {
    public Avian(String name) {
        super(name);
    }
    
    @Override
    public String getTitle() {
        return "Avian";
    }
}

abstract class Veterinarian {
    private String name;
    private boolean availability;  
    
    public Veterinarian(String name) {
        this.name = name;
        availability = true;
    }
       /* Getters */
    public String getName() {
        return name;
    }

    public boolean getAvailability() {
        return availability;
    }
    
    public void setAvailability(boolean status) {
        availability = status;
    }
    
    /*
    StringBuilder is more efficient than String for string concatenation because it allows for mutable, resizable strings, reducing memory overhead and avoiding unnecessary string object creations during concatenation.
    */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTitle()).append(":\n\tName: ").append(this.getName()).append("\n\tAvailable: ").append(this.getAvailability());
        return sb.toString();
    }
    
    abstract public String getTitle();
}
class Pet {
    private String name;
    private int age;
    private int code;
    public static int totalPets; // maintain count of pets

    public Pet(String name, int age, int code) {
        this.name = name;
        this.age = age;
        this.code = code;
        totalPets++;
    }

    /* Getters */
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getCode() {
        return code;
    }

    public String toString() {
        return ("Pet:-\n\tName: "+ name+ "\n\tAge: "+age);
    }
}


class Clinic  {
    private String name;
    private List<Pet> pets;
    private Map<String, PriorityQueue<Veterinarian>> vetMap;

    public Clinic(String name) {
        this.name = name;
        this.vetMap = new HashMap<>();
        this. pets = new ArrayList<>();
    }

    public void addVet(String name, String dep) {
        Veterinarian vet = createDoctor(name, dep);
        if (vet != null) {
            PriorityQueue<Veterinarian> vets = vetMap.getOrDefault(dep, new PriorityQueue<>(Comparator.comparing(Veterinarian::getAvailability)));
            vets.add(vet);
            vetMap.put(dep, vets);
        }
    }

    private Veterinarian createDoctor(String name, String dep) {
        switch(dep) {
            case "avian":
                return new Avian(name);
            case "feline":
                return new Feline(name);
            case "canine":
                return new Canine(name);
            default:
                System.out.println("Invalid department specified.");
                return null;
        }
    }

    /* Booking an appointment */
    public void bookAppointment(String name, int age, String vetType) {
        PriorityQueue<Veterinarian> vets = vetMap.get(vetType);
        if (vets != null && !vets.isEmpty()) {
            Veterinarian vet = vets.peek();
            if (vet != null && vet.getAvailability()) {
                System.out.println("Appointment scheduled with " + vet.getName() + " for " + name);
                vet.setAvailability(false);
                Pet pet = new Pet(name, age, Pet.totalPets + 1);
                addPet(pet);
                vets.poll();
            } else {
                System.out.println("No available doctor for the specified type:" + vetType + " for " + name);
            }
        } else {
            System.out.println("No doctor available for the specified type:" + vetType + " for " + name);
        }
    }
    
    public void addPet(Pet pet) {
        this.pets.add(pet);
    }
    
    public void printPets() {
        System.out.println("Pets in " + this.name + ":");
        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }
}


