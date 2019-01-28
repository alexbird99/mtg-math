//package howmanysourcesgoldcards;

import java.util.Arrays.*;
import java.util.Random;

public class HowManySourcesGoldCards {

    public static void main(String[] args) {

        Deck2 deck = new Deck2();
        int NrIterations = 1000000;

        // Manually set the type of card that we're interested to cast here
        int NrGoodLandsNeededA = 2;
        int NrGoodLandsNeededB = 1;
        int TurnAllowed = 4;
        // We will look for the probability of casting a spell with CMC TurnAllowed on
        // turn TurnAllowed that requires NrGoodLandsNeeded (which is no larger than
        // TurnAllowed) colored mana of a certain color in its cost.
        // For example, for a 2WW Wrath of God, we use TurnAllowed=4 and
        // NrGoodLandsNeeded=2.

        // Initialize the contents of the deck
        int NrCards = 40;
        int NrOtherLands = 4;
        int NrDuals = 2;
        //int NrGoodLandsA = 9;
        int NrGoodLandsB = 8;
        // Note that the final element needed to describe a deck (NrGoodLands) is set
        // later, as we iterate over the various possible values

        // Declare other variables
        int CardType; // Variable used to reference the type of card drawn from the deck
        int LandsInHand; // This will describe the total amount of lands in your hand
        int DualInHand;
        int GoodLandsInHandA; // This will describe the number of lands that can produce the right color in
                              // your hand
        int GoodLandsInHandB; // This will describe the number of lands that can produce the right color in
                              // your hand
        int StartingHandSize;
        boolean Mulligan;
        boolean GoodLandOnTopA;
        boolean GoodLandOnTopB;
        boolean DualOnTop;

        for (int NrGoodLandsA = 1; NrGoodLandsA < 17; NrGoodLandsA++) {
            NrGoodLandsB = 17 - NrGoodLandsA - NrOtherLands - NrDuals;
        double CountOK = 0.0; // This will be the number of relevant games where you draw enough lands and the
                              // right colored sources
        double CountConditional = 0.0; // This will be the number of relevant games where you draw enough lands

        for (int i = 1; i <= NrIterations; i++) {

            // Draw opening 7
            deck.SetDeck(NrGoodLandsA, NrGoodLandsB, NrDuals, NrCards, NrOtherLands);
            LandsInHand = 0;
            GoodLandsInHandA = 0;
            GoodLandsInHandB = 0;
            DualInHand = 0;
            for (int j = 1; j <= 7; j++) {
                CardType = deck.DrawCard();
                if (CardType == 1) {
                    GoodLandsInHandA++;
                    LandsInHand++;
                }
                if (CardType == 2) {
                    GoodLandsInHandB++;
                    LandsInHand++;
                }
                if (CardType == 3) {
                    DualInHand++;
                    LandsInHand++;
                }
                if (CardType == 4) {
                    LandsInHand++;
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
                deck.SetDeck(NrGoodLandsA, NrGoodLandsB, NrDuals, NrCards, NrOtherLands);
                LandsInHand = 0;
                GoodLandsInHandA = 0;
                GoodLandsInHandB = 0;
                DualInHand = 0;
                for (int j = 1; j <= 6; j++) {
                    CardType = deck.DrawCard();
                    if (CardType == 1) {
                        GoodLandsInHandA++;
                        LandsInHand++;
                    }
                    if (CardType == 2) {
                        GoodLandsInHandB++;
                        LandsInHand++;
                    }
                    if (CardType == 3) {
                        DualInHand++;
                        LandsInHand++;
                    }
                    if (CardType == 4) {
                        LandsInHand++;
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
                deck.SetDeck(NrGoodLandsA, NrGoodLandsB, NrDuals, NrCards, NrOtherLands);
                LandsInHand = 0;
                GoodLandsInHandA = 0;
                GoodLandsInHandB = 0;
                DualInHand = 0;
                for (int j = 1; j <= 5; j++) {
                    CardType = deck.DrawCard();
                    if (CardType == 1) {
                        GoodLandsInHandA++;
                        LandsInHand++;
                    }
                    if (CardType == 2) {
                        GoodLandsInHandB++;
                        LandsInHand++;
                    }
                    if (CardType == 3) {
                        DualInHand++;
                        LandsInHand++;
                    }
                    if (CardType == 4) {
                        LandsInHand++;
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
                deck.SetDeck(NrGoodLandsA, NrGoodLandsB, NrDuals, NrCards, NrOtherLands);
                LandsInHand = 0;
                GoodLandsInHandA = 0;
                GoodLandsInHandB = 0;
                DualInHand = 0;
                for (int j = 1; j <= 4; j++) {
                    CardType = deck.DrawCard();
                    if (CardType == 1) {
                        GoodLandsInHandA++;
                        LandsInHand++;
                    }
                    if (CardType == 2) {
                        GoodLandsInHandB++;
                        LandsInHand++;
                    }
                    if (CardType == 3) {
                        DualInHand++;
                        LandsInHand++;
                    }
                    if (CardType == 4) {
                        LandsInHand++;
                    }
                }
                StartingHandSize = 4;
            }

            // Vancouver mulligan scry
            // If we already have enough colored sources, we leave any land on top and push
            // any non-land to the bottom
            // If we don't already have enough colored sources, we leave any land that can
            // produce the right color on top and push any other card (both off-color lands
            // and spells) to the bottom
            GoodLandOnTopA = false;
            GoodLandOnTopB = false;
            DualOnTop = false;
            if (StartingHandSize < 7) {
                CardType = deck.DrawCard();
                if (CardType == 1) {
                    GoodLandOnTopA = true;
                }
                if (CardType == 2) {
                    GoodLandOnTopB = true;
                }
                if (CardType == 3) {
                    DualOnTop = true;
                }
            }
            // Draw step for turn 2 (remember, we're on the play)
            if (TurnAllowed > 1) {
                boolean WeScriedToTop = false;
                if (GoodLandOnTopA && GoodLandsInHandA < NrGoodLandsNeededA) {
                    GoodLandsInHandA++;
                    LandsInHand++;
                    WeScriedToTop = true;
                }
                if (GoodLandOnTopB && GoodLandsInHandB < NrGoodLandsNeededB) {
                    GoodLandsInHandB++;
                    LandsInHand++;
                    WeScriedToTop = true;
                }
                if (DualOnTop) {
                    DualInHand++;
                    LandsInHand++;
                    WeScriedToTop = true;
                }
                if (!WeScriedToTop) {
                    CardType = deck.DrawCard();
                    if (CardType == 1) {
                        GoodLandsInHandA++;
                        LandsInHand++;
                    }
                    if (CardType == 2) {
                        GoodLandsInHandB++;
                        LandsInHand++;
                    }
                    if (CardType == 3) {
                        DualInHand++;
                        LandsInHand++;
                    }
                    if (CardType == 4) {
                        LandsInHand++;
                    }
                }
            }

            // For turns 3 on, draw cards for the number of turns available
            for (int turn = 3; turn <= TurnAllowed; turn++) {
                CardType = deck.DrawCard();
                if (CardType == 1) {
                    GoodLandsInHandA++;
                    LandsInHand++;
                }
                if (CardType == 2) {
                    GoodLandsInHandB++;
                    LandsInHand++;
                }
                if (CardType == 3) {
                    DualInHand++;
                    LandsInHand++;
                }
                if (CardType == 4) {
                    LandsInHand++;
                }
            }

            if (GoodLandsInHandA + DualInHand >= NrGoodLandsNeededA
                    && GoodLandsInHandB + DualInHand >= NrGoodLandsNeededB
                    && GoodLandsInHandA + GoodLandsInHandB + DualInHand >= NrGoodLandsNeededA + NrGoodLandsNeededB
                    && LandsInHand >= TurnAllowed) {
                CountOK++;
            }
            if (LandsInHand >= TurnAllowed) {
                CountConditional++;
            }

        } // end of 1,000,000 iterations

        // System.out.println("With "+NrGoodLands+" good lands:
        // Prob="+CountOK/CountConditional);
        //System.out.println(CountOK / CountConditional);
        System.out.println(NrGoodLandsA + " " + CountOK / NrIterations);

    }
}
}

class Deck2 {
    int NumberOfGoodLandsA;
    int NumberOfGoodLandsB;
    int NumberOfDuals;
    int NumberOfCards;
    int NumberOtherLands;

    void SetDeck(int NrGoodLandsA, int NrGoodLandsB, int NrDuals, int NrCards, int NrOtherLands) {
        NumberOfGoodLandsA = NrGoodLandsA;
        NumberOfGoodLandsB = NrGoodLandsB;
        NumberOfDuals = NrDuals;
        NumberOfCards = NrCards;
        NumberOtherLands = NrOtherLands;
    }

    int DrawCard() {
        Random generator = new Random();
        int RandomIntegerBetweenOneAndDeckSize = generator.nextInt(this.NumberOfCards) + 1;
        int CardType = 0;
        int GoodLandCutoffA = NumberOfGoodLandsA;
        int GoodLandCutoffB = NumberOfGoodLandsA + NumberOfGoodLandsB;
        int DualCutoff = NumberOfGoodLandsA + NumberOfGoodLandsB + NumberOfDuals;
        int OtherLandCutoff = NumberOfGoodLandsA + NumberOfGoodLandsB + NumberOfDuals + NumberOtherLands;
        if (RandomIntegerBetweenOneAndDeckSize <= GoodLandCutoffA) {
            CardType = 1;
            this.NumberOfGoodLandsA--;
            this.NumberOfCards--;
        }
        if (RandomIntegerBetweenOneAndDeckSize > GoodLandCutoffA
                && RandomIntegerBetweenOneAndDeckSize <= GoodLandCutoffB) {
            CardType = 2;
            this.NumberOfGoodLandsB--;
            this.NumberOfCards--;
        }
        if (RandomIntegerBetweenOneAndDeckSize > GoodLandCutoffB && RandomIntegerBetweenOneAndDeckSize <= DualCutoff) {
            CardType = 3;
            this.NumberOfDuals--;
            this.NumberOfCards--;
        }
        if (RandomIntegerBetweenOneAndDeckSize > DualCutoff && RandomIntegerBetweenOneAndDeckSize <= OtherLandCutoff) {
            CardType = 4;
            this.NumberOtherLands--;
            this.NumberOfCards--;
        }
        if (RandomIntegerBetweenOneAndDeckSize > OtherLandCutoff) {
            CardType = 5;
            this.NumberOfCards--;
        }
        return CardType;
    }

}