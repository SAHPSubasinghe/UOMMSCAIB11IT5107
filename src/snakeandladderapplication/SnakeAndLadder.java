package snakeandladderapplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class SnakeAndLadder extends JFrame implements ActionListener {

    JButton rollDice = new JButton("ROLL DICE");
    JLabel numberOntheDice = new JLabel("NUMBER ON THE DICE");
    JLabel lblNumber = new JLabel();
    JButton newGame = new JButton("NEW GAME");
    JLabel lblmsg1 = new JLabel("");
    JLabel lblmsg2 = new JLabel("");
    JLabel lblPlayer1Score = new JLabel("Player 1's Score :");
    JLabel lblPlayer2Score = new JLabel("Player 2's Score :");
    JLabel lblPlayer1Pos = new JLabel("");
    JLabel lblPlayer2Pos = new JLabel("");
    String msg = "";
    HashMap<Integer, Integer> ladder = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> snake = new HashMap<Integer, Integer>();

    int Player1Count = 0;
    int Player2Count = 0;
    int playerNumber = 2;
    int scoreP1 = 0, scoreP2 = 0;

    int w = 15, h = 15;
    int x = 0, y = 0;

    Random dies = new Random();

    public SnakeAndLadder() {
        super("SnakeAndLadder");
        try {
            //set layout
            setLayout(null);
            setVisible(true);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(10, 10, 600, 720);

            //set background image
            String path = "../Images/snakesandladdersboard.jpg";
            JLabel background = new JLabel("Image and Text", new ImageIcon(getClass().getResource(path)), JLabel.CENTER);
            background.setBounds(0, 0, 570, 570);
            add(background);

            //add components to the background
            rollDice.setBounds(10, 600, 120, 30);
            add(rollDice);
            numberOntheDice.setBounds(150, 580, 120, 30);
            add(numberOntheDice);
            lblNumber.setBounds(150, 600, 120, 30);
            lblNumber.setText(String.valueOf(Player1Count));
            add(lblNumber);
            lblmsg1.setBounds(400, 580, 120, 30);
            Font font = new Font("", Font.BOLD, 12);
            lblmsg1.setFont(font);
            lblmsg1.setText("Player 1 to play");
            add(lblmsg1);
            lblmsg2.setBounds(400, 580, 120, 30);
            add(lblmsg2);
            lblPlayer1Score.setBounds(400, 600, 200, 30);
            add(lblPlayer1Score);
            lblPlayer2Score.setBounds(400, 630, 200, 30);
            add(lblPlayer2Score);
            newGame.setBounds(10, 630, 120, 30);
            add(newGame);

            setPosition(2, Player1Count);
            lblPlayer1Pos.setBounds(x, y, w, h);
            lblPlayer1Pos.setOpaque(true);
            lblPlayer1Pos.setBackground(Color.BLUE);
            background.add(lblPlayer1Pos);

            setPosition(1, Player2Count);
            lblPlayer2Pos.setBounds(x, y, w, h);
            lblPlayer2Pos.setOpaque(true);
            lblPlayer2Pos.setBackground(Color.RED);
            background.add(lblPlayer2Pos);

            repaint();
            //Set ladders potition
            ladder.put(2, 38);
            ladder.put(7, 14);
            ladder.put(8, 31);
            ladder.put(15, 26);
            ladder.put(28, 84);
            ladder.put(21, 42);
            ladder.put(36, 44);
            ladder.put(51, 67);
            ladder.put(71, 91);
            ladder.put(87, 94);
            ladder.put(78, 98);
            //Set snakes potition
            snake.put(16, 6);
            snake.put(49, 11);
            snake.put(46, 25);
            snake.put(62, 19);
            snake.put(64, 60);
            snake.put(74, 53);
            snake.put(89, 68);
            snake.put(92, 88);
            snake.put(95, 75);
            snake.put(99, 80);

            newGame.addActionListener(this);
            rollDice.addActionListener(this);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    public static void main(String args[]) throws Throwable {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new SnakeAndLadder();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //start a new game
            if (e.getSource() == newGame) {
                Player1Count = 0;
                Player2Count = 0;
                setPosition(1, Player2Count);
                lblPlayer2Pos.setBounds(x, y, w, h);
                setPosition(2, Player1Count);
                lblPlayer1Pos.setBounds(x, y, w, h);
                rollDice.setVisible(true);
                lblmsg1.setText("");
                lblmsg1.setText("Player 1 to play");
                lblPlayer1Score.setText("");
                lblPlayer1Score.setText("Player 1's Score : ");
                lblPlayer2Score.setText("");
                lblPlayer2Score.setText("Player 2's Score : ");
                lblNumber.setText("");
                playerNumber = 2;
                repaint();
            } else if (e.getSource() == rollDice) {//Role dice
                int playAgain = playDies(playerNumber);
                if (playerNumber == 2) {
                    setPosition(2, Player1Count);
                    lblPlayer1Pos.setBounds(x, y, w, h);
                    repaint();

                    if (ladder.containsKey(Player1Count)) {//check for ladder 
                        Player1Count = ladder.get(Player1Count);
                    } else if (snake.containsKey(Player1Count)) {//check for snakes
                        Player1Count = snake.get(Player1Count);
                    }

                    setPosition(2, Player1Count);
                    lblPlayer1Pos.setBounds(x, y, w, h);
                    repaint();

                    if (Player1Count == 100) //Player 1 count = 100
                    {
                        setPosition(2, Player1Count);
                        lblPlayer1Pos.setBounds(x, y, w, h);
                        repaint();

                        rollDice.setVisible(false);
                        JOptionPane.showMessageDialog(this, "Congratulations!!! Player_1 win the Game");
                    } else if (playAgain == 0) {
                        playerNumber = 1;
                        lblmsg1.setText("Player 2 to play");
                    }
                    lblPlayer1Score.setText("");
                    lblPlayer1Score.setText("Player 1's Score : " + String.valueOf(Player1Count));

                } else if (playerNumber == 1) {
                    setPosition(1, Player2Count);
                    lblPlayer2Pos.setBounds(x, y, w, h);
                    repaint();

                    if (ladder.containsKey(Player2Count)) {//check for ladder 
                        Player2Count = ladder.get(Player2Count);
                    } else if (snake.containsKey(Player2Count)) {//check for snakes
                        Player2Count = snake.get(Player2Count);
                    }
                    setPosition(1, Player2Count);
                    lblPlayer2Pos.setBounds(x, y, w, h);
                    repaint();
                    if (Player2Count == 100) //Player 1 count = 100
                    {
                        setPosition(1, Player2Count);
                        lblPlayer2Pos.setBounds(x, y, w, h);
                        repaint();
                        rollDice.setVisible(false);
                        JOptionPane.showMessageDialog(this, "Congratulations!!! Player_2 win the Game");
                    } else if (playAgain == 0) {
                        playerNumber = 2;
                        lblmsg1.setText("Player 1 to play");
                    }
                    lblPlayer2Score.setText("");
                    lblPlayer2Score.setText("Player 2's Score : " + String.valueOf(Player2Count));
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    private int playDies(int player) {
        int playAgain = 0;
        int diesResult = 0;
        while (diesResult == 0) {
            diesResult = dies.nextInt(7);
        }
        
        if (player == 2) {
            if (Player1Count + diesResult <= 100) {
                Player1Count = Player1Count + diesResult;
                if (diesResult == 1 || diesResult == 6) {
                    playAgain = 1;
                }
                lblNumber.setText("");
                lblNumber.setText(String.valueOf(diesResult));
                lblPlayer1Score.setText("");
                lblPlayer1Score.setText("Player 1's Score : " + String.valueOf(Player1Count));
            }
        } else {

            if (Player2Count + diesResult <= 100) {
                Player2Count = Player2Count + diesResult;
                if (diesResult == 1 || diesResult == 6) {
                    playAgain = 1;
                }
                lblNumber.setText("");
                lblNumber.setText(String.valueOf(diesResult));
                lblPlayer2Score.setText("");
                lblPlayer2Score.setText("Player 2's Score : " + String.valueOf(Player2Count));
            }
        }

        return playAgain;
    }

    private void setPosition(int compOrYou, int count) {

        int xpos = 0;
        if ((count % 10) == 1 && (count > 10) && ((count / 10) % 2) != 0) {
            xpos = 10;
        } else if ((count % 10) == 1 && (count > 10) && ((count / 10) % 2) == 0) {
            xpos = 1;
        } else if ((count % 10) > 1 && (count > 10) && ((count / 10) % 2) != 0) {
            xpos = 10 - ((count % 10) - 1);
        } else {
            xpos = count % 10;
        }

        int ypos = count / 10;
        if (xpos == 0) {
            if ((count / 10) % 2 != 0) {
                xpos = 10;
            } else {
                xpos = 1;
            }
            ypos = ypos - 1;
        }
        if (compOrYou == 1) {
            x = 5 + (xpos * 57) - 57;
        } else {
            x = 25 + (xpos * 57) - 57;
        }
        y = 540 - (ypos * 57);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
