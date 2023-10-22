package LLD_Snake_Ladder;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    int min = 1;
    int max = 6;
    int diceCount;

    public Dice(int diceCount) {
        this.diceCount = diceCount;
    }

    public int rollKaro() {
        int total = 0;
        int currDice = 0;

        while(currDice++ < diceCount)
            total += ThreadLocalRandom.current().nextInt(min,max+1);

        return total;
    }

}

