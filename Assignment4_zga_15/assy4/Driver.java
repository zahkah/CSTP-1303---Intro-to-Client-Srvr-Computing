package assy4P3;

import java.util.Random;

public class Driver {
    public static void main(String[] args) {
        Clinic clinic = new Clinic("Pet Care Clinic");

        clinic.addVet("David", "avian");
        clinic.addVet("Linda", "avian");
        clinic.addVet("Mike", "feline");
        clinic.addVet("Katherine", "feline");
        clinic.addVet("Bob", "canine");
        clinic.addVet("Chris", "canine");

        Runnable task = () -> {
            Random rand = new Random();
            String[] petNames = {"Garfield", "Rio", "Lassie", "Scooby-Doo", "Goofy"};
            String[] vetTypes = {"feline", "avian", "canine"};
            String petName = petNames[rand.nextInt(petNames.length)];
            int petAge = rand.nextInt(10) + 1;
            String vetType = vetTypes[rand.nextInt(vetTypes.length)];

            clinic.bookAppointment(petName, petAge, vetType);
        };

        // Create 10 threads and start them
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }

        // Wait for all threads to finish
        try {
            Thread.sleep(2000); // Wait for 2 seconds to ensure all threads complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clinic.printPets();
    }
}
