import java.util.Random;

public class Hero {
    //properties
    private String name;
    private int hitPoints;

    //constructors
    public Hero(String name){
        this.name = name;
        this.hitPoints = 100;
    }

    //methods
    public String getName(){
        return this.name;
    }
    public int getHitPoints(){
        return this.hitPoints;
    }
    public String toString(){
        return "Hero{name='" + name + "', hitPoints=" + hitPoints + "}";
    }
    public void attack(Hero opponent){
        Random rand = new Random();
        double intRandom = rand.nextInt(100);
        double divideHundred = intRandom/100;

        if (divideHundred < 0.5){
            opponent.hitPoints -= 10;
        }
        else{
            this.hitPoints -= 10;
        }
    }

    public void senzuBean(){
        this.hitPoints = 100;
    }

    private void fightUntilTheDeathHelper(Hero opponent){
        while (this.hitPoints > 0 && opponent.hitPoints > 0){
            attack(opponent);
        }
    }

    public String fightUntilTheDeath(Hero opponent){
        opponent.senzuBean();
        this.hitPoints = 100;
        fightUntilTheDeathHelper(opponent);
        return this.name+ ": "+this.hitPoints+"\t"+opponent.name+": "+opponent.hitPoints;
    }

    //n fights
    private int[] nFightsToTheDeathHelper(Hero opponent, int n){

        int heroCounter = 0;
        int opponentCounter = 0;
        for (int i = 0; i < n; i++){
            opponent.senzuBean();
            this.hitPoints = 100;
            fightUntilTheDeathHelper(opponent);
            //if hero loses all points, opponent wins
            if (this.hitPoints == 0){
                opponentCounter++;
            }
            //else if opponent loses all points
            else{
                heroCounter++;
            }
        }
        int[] heroVsOpponent = {heroCounter, opponentCounter};

        return heroVsOpponent;
    }

    public String nFightsToTheDeath(Hero opponent, int n){
        opponent.senzuBean();
        this.hitPoints = 100;
        int[] tally = nFightsToTheDeathHelper(opponent, n);

        if (tally[0] == tally[1]){
            return this.name+": "+tally[0]+" wins\n"+opponent.name+": "+tally[1]+" wins\n"+"OMG! It was actually a draw!";
        }
        else if (tally[0] >= tally[1]) {
            return this.name+": "+tally[0]+" wins\n"+opponent.name+": "+tally[1]+" wins\n"+this.name+" wins!";
        }
        return this.name+": "+tally[0]+" wins\n"+opponent.name+": "+tally[1]+" wins\n"+opponent.name+" wins!";
    }

    public void dramaticFightToTheDeath(Hero opponent){
        while (this.hitPoints > 0 && opponent.hitPoints > 0){
            attack(opponent);
            System.out.println(this.name+ ": "+this.hitPoints+"\t"+opponent.name+": "+opponent.hitPoints);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        if (this.hitPoints == 0){
            System.out.println(opponent.name + " wins!");
        }
        else{
            System.out.println(this.name + " wins!");
        }
    }
}
