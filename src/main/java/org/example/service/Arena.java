package org.example.service;

import org.example.model.Dice;
import org.example.model.Player;

public class Arena {

        private Player player1;
        private Player player2;

        public Arena(Player player1, Player player2) {
            this.player1 = player1;
            this.player2 = player2;
        }

        public void startMatch() {
            Player attacker = player1.getHealth() <= player2.getHealth() ? player1 : player2;
            Player defender = attacker == player1 ? player2 : player1;

            while (player1.isAlive() && player2.isAlive()) {
                int attackRoll = Dice.roll();
                int defendRoll = Dice.roll();

                int attackDamage = attacker.getAttack() * attackRoll;
                int defendStrength = defender.getStrength() * defendRoll;
                int netDamage = Math.max(0, attackDamage - defendStrength);

                defender.takeDamage(netDamage);

                if (defender.isAlive()) {
                    // Swap roles: defender attacks next
                    Player temp = attacker;
                    attacker = defender;
                    defender = temp;
                }
            }

            // Declare winner
            if (player1.isAlive()) {
                System.out.println("Player 1 wins!");
            } else {
                System.out.println("Player 2 wins!");
            }
        }


}
