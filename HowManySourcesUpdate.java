//package howmanysourcesupdate;

import java.util.Arrays.*;
import java.util.Random;

public class HowManySourcesUpdate {

    public static void main(String[] args) {

        Deck deck = new Deck();
        int NrIterations = 1000000;

        // Manually set the type of card that we're interested to cast here
        int NrGoodLandsNeeded = 4;
        int TurnAllowed = 7;
        // We will look for the probability of casting a spell with CMC TurnAllowed on
        // turn TurnAllowed that requires NrGoodLandsNeeded (which is no larger than
        // TurnAllowed) colored mana of a certain color in its cost.
        // For example, for a 2WW Wrath of God, we use TurnAllowed=4 and
        // NrGoodLandsNeeded=2.

        // Initialize the contents of the deck
        int NrCards = 40;
        int NrLands = 17;
        // Note that the final element needed to describe a deck (NrGoodLands) is set
        // later, as we iterate over the various possible values

        // Declare other variables
        int CardType; // Variable used to reference the type of card drawn from the deck
        int LandsInHand; // This will describe the total amount of lands in your hand
        int GoodLandsInHand; // This will describe the number of lands that can produce the right color in
                             // your hand
        int StartingHandSize;
        boolean Mulligan;
        boolean ScryOnTop;
        int ScryCardType;

        for (int NrGoodLands = 1; NrGoodLands <= 17; NrGoodLands++) {

            double CountOK = 0.0; // This will be the number of relevant games where you draw enough lands and the
                                  // right colored sources
            double CountConditional = 0.0; // This will be the number of relevant games where you draw enough lands

            for (int i = 1; i <= NrIterations; i++) {

                // Draw opening 7
                deck.SetDeck(NrLands, NrGoodLands, NrCards);
                LandsInHand = 0;
                GoodLandsInHand = 0;
                for (int j = 1; j <= 7; j++) {
                    CardType = deck.DrawCard();
                    if (CardType < 3) {
                        LandsInHand++;
                    }
                    if (CardType == 1) {
                        GoodLandsInHand++;
                    }
                }
                StartingHandSize = 7;
                Mulligan = false;
                if (LandsInHand < 2) {
                    Mulligan = true;
                }
                if (LandsInHand > 5) {
                    Mulligan = true;
                }

                // Mulligan to 6
                if (Mulligan) {
                    deck.SetDeck(NrLands, NrGoodLands, NrCards);
                    LandsInHand = 0;
                    GoodLandsInHand = 0;
                    for (int j = 1; j <= 6; j++) {
                        CardType = deck.DrawCard();
                        if (CardType < 3) {
                            LandsInHand++;
                        }
                        if (CardType == 1) {
                            GoodLandsInHand++;
                        }
                    }
                    StartingHandSize = 6;
                    Mulligan = false;
                    if (LandsInHand < 2) {
                        Mulligan = true;
                    }
                    if (LandsInHand > 4) {
                        Mulligan = true;
                    }
                }

                // Mulligan to 5
                if (Mulligan) {
                    deck.SetDeck(NrLands, NrGoodLands, NrCards);
                    LandsInHand = 0;
                    GoodLandsInHand = 0;
                    for (int j = 1; j <= 5; j++) {
                        CardType = deck.DrawCard();
                        if (CardType < 3) {
                            LandsInHand++;
                        }
                        if (CardType == 1) {
                            GoodLandsInHand++;
                        }
                    }
                    StartingHandSize = 5;
                    Mulligan = false;
                    if (LandsInHand < 1) {
                        Mulligan = true;
                    }
                    if (LandsInHand > 4) {
                        Mulligan = true;
                    }
                }

                // Mulligan to 4
                if (Mulligan) {
                    deck.SetDeck(NrLands, NrGoodLands, NrCards);
                    LandsInHand = 0;
                    GoodLandsInHand = 0;
                    for (int j = 1; j <= 4; j++) {
                        CardType = deck.DrawCard();
                        if (CardType < 3) {
                            LandsInHand++;
                        }
                        if (CardType == 1) {
                            GoodLandsInHand++;
                        }
                    }
                    StartingHandSize = 4;
                }

                // Vancouver mulligan scry
                // Wwe leave any land that can produce the right color on top and push any other
                // card (both off-color lands and spells) to the bottom
                            
                ScryOnTop = false;
                ScryCardType = 0;

                if (StartingHandSize < 7) {
                    ScryCardType = deck.DrawCard();
                    
                    // leave lands on top when lands in hand < 3
                    // leave nonlands on top when lands in hand >= 3
                    if (ScryCardType < 3) {
                        if (LandsInHand < 3) {
                            ScryOnTop = true;                            
                        } else {
                            ScryOnTop = false;
                        }                    
                    } else {
                        if (LandsInHand < 3) {
                            ScryOnTop = false;
                        } else {
                            ScryOnTop = true;
                        }
                    }
                }
                // Draw step for turn 2 (remember, we're always on the play)
                if (TurnAllowed > 1) {
                    if (ScryOnTop) {  
                        if (ScryCardType < 3) {
                            LandsInHand++;
                        }
                        if (ScryCardType == 1) {
                            GoodLandsInHand++;
                        }
                    } else {
                        CardType = deck.DrawCard();
                        if (CardType < 3) {
                            LandsInHand++;
                        }
                        if (CardType == 1) {
                            GoodLandsInHand++;
                        }
                    }
                }

                // For turns 3 on, draw cards for the number of turns available
                for (int turn = 3; turn <= TurnAllowed; turn++) {
                    CardType = deck.DrawCard();
                    if (CardType < 3) {
                        LandsInHand++;
                    }
                    if (CardType == 1) {
                        GoodLandsInHand++;
                    }
                }

                if (GoodLandsInHand >= NrGoodLandsNeeded && LandsInHand >= TurnAllowed) {
                    CountOK++;
                }
                if (LandsInHand >= TurnAllowed) {
                    CountConditional++;
                }

            } // end of 1,000,000 iterations

            // System.out.println("With "+NrGoodLands+" good lands:
            // Prob="+CountOK/CountConditional);
            //System.out.println(CountOK / NrIterations);        
            System.out.println(CountOK / CountConditional);
        }
    }
}

class Deck {
    int NumberOfLands;
    int NumberOfGoodLands;
    int NumberOfCards;

    void SetDeck(int NrLands, int NrGoodLands, int NrCards) {
        NumberOfLands = NrLands;
        NumberOfGoodLands = NrGoodLands;
        NumberOfCards = NrCards;
    }

    int DrawCard() {
        Random generator = new Random();
        int RandomIntegerBetweenOneAndDeckSize = generator.nextInt(this.NumberOfCards) + 1;
        int CardType = 0;
        int GoodLandCutoff = NumberOfGoodLands;
        int LandCutoff = NumberOfLands;

        if (RandomIntegerBetweenOneAndDeckSize <= GoodLandCutoff) {
            CardType = 1;
            this.NumberOfGoodLands--;
            this.NumberOfLands--;
            this.NumberOfCards--;
        }
        if (RandomIntegerBetweenOneAndDeckSize > GoodLandCutoff && RandomIntegerBetweenOneAndDeckSize <= LandCutoff) {
            CardType = 2;
            this.NumberOfLands--;
            this.NumberOfCards--;
        }
        if (RandomIntegerBetweenOneAndDeckSize > LandCutoff) {
            CardType = 3;
            this.NumberOfCards--;
        }
        return CardType;
    }

}