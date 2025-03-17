package game;

import javax.swing.JLabel;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGame {

    private int turns; // To store how many turns a user has taken. Total allowed are 15.
    private CustomIcon[] icons; // Array of CustomIcon objects.
    private JLabel previousFlippedCard; // Will store reference to JLabel that was flipped first.
    private int iconsFlipped; // At a time, user is allowed to flip 2 icons at max.
    private int pairsFound; // To keep track of how many pairs found. If 6 found, user wins.

    public MemoryGame() {
        // ----- Write Task 3 Code Below This Line ----- //
    }

    // Getters

    public int getTurns() {
        return turns;
    }

    public CustomIcon getCustomIcon(int index) {
        return icons[index];
    }

    public JLabel getPreviousFlippedCard() {
        return previousFlippedCard;
    }

    public int getIconsFlipped() {
        return iconsFlipped;
    }

    public int getPairsFound() {
        return pairsFound;
    }

    // Setters

    public void incrementTurns() {
        turns = turns + 1;
    }

    public void resetTurns() {
        turns = 0;
    }

    public void setPreviousFlippedCard(JLabel previousFlippedCard) {
        this.previousFlippedCard = previousFlippedCard;
    }

    public void incrementIconsFlipped() {
        iconsFlipped = iconsFlipped + 1;
    }

    public void resetIconsFlipped() {
        iconsFlipped = 0;
    }

    public void incrementPairsFound() {
        pairsFound = pairsFound + 1;
    }

    public void resetPairsFound() {
        pairsFound = 0;
    }

    // ----- Write Task 4 Code Below This Line ----- //

}