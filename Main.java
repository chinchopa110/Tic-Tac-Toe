import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.WindowConstants; 

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame("Tic Tac Toe");

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(700, 700);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        TicTacToe game = new TicTacToe();
        window.add(game);

    }
}
