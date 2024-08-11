import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToe extends JComponent {

    private char[][] a;
    private boolean isX;

    public TicTacToe() {
        a = new char[3][3]; 
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        startGame();
    }

    public void startGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = '_';
            }
        }
        isX = true;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (a[i][j] == 'X') {
                    drawX(i, j, g);
                } else if (a[i][j] == 'O') {
                    drawO(i, j, g);
                }
            }
        }
    }

    public void drawX(int i, int j, Graphics g) {
        g.setColor(Color.RED);

        int dw = getWidth() / 3;
        int dh = getHeight() / 3;

        int x = i * dw; 
        int y = j * dh; 

        g.drawLine(x, y, x + dw, y + dh);
        g.drawLine(x, y + dh, x + dw, y);
    }

    public void drawO(int i, int j, Graphics g) {
        g.setColor(Color.RED);

        int dw = getWidth() / 3;
        int dh = getHeight() / 3;

        int x = i * dw; 
        int y = j * dh; 

        g.drawOval(x, y, dw, dh);
    }

    @Override
    protected void processMouseEvent(MouseEvent m) {
        super.processMouseEvent(m);
        if (m.getButton() == MouseEvent.BUTTON1) { 
            int x = m.getX();
            int y = m.getY();

            int i = x / (getWidth() / 3); 
            int j = y / (getHeight() / 3); 

            if (i >= 0 && i < 3 && j >= 0 && j < 3 && a[i][j] == '_') {
                a[i][j] = isX ? 'X' : 'O';
                isX = !isX;
                repaint();

                int res = is_win();
                if(res != 0){
                    if(res == 1){
                        JOptionPane.showMessageDialog(this, "The crosses won!!", "You win!", JOptionPane.INFORMATION_MESSAGE);
                    } else if(res == 2){
                        JOptionPane.showMessageDialog(this, "The zeros won!!", "You win!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Friendship won!!", "Draw!", JOptionPane.INFORMATION_MESSAGE);
                    }

                    startGame();
                    repaint();
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, getWidth(), getHeight());
        drawGrid(g);
        draw(g);
    }

    void drawGrid(Graphics g) {
        int w = getWidth();
        int h = getHeight();

        int dw = w / 3;
        int dh = h / 3;

        g.setColor(Color.BLUE);
        for (int i = 1; i < 3; i++) { 
            g.drawLine(0, dh * i, w, dh * i);
            g.drawLine(dw * i, 0, dw * i, h);
        }
    }

    public int is_win(){
        for(int i = 0; i < 3; i++){
            if(a[i][0] == 'X' && a[i][1] == 'X' && a[i][2] == 'X'){
                return 1;
            }
        }

        for(int i = 0; i < 3; i++){
            if(a[0][i] == 'X' && a[1][i] == 'X' && a[2][i] == 'X'){
                return 1;
            }
        }

        if(a[0][0] == 'X' && a[1][1] == 'X' && a[2][2] == 'X'){
            return 1;
        }

        if(a[0][2] == 'X' && a[1][1] == 'X' && a[2][0] == 'X'){
            return 1;
        }  



        for(int i = 0; i < 3; i++){
            if(a[i][0] == 'O' && a[i][1] == 'O' && a[i][2] == 'O'){
                return 2;
            }
        }

        for(int i = 0; i < 3; i++){
            if(a[0][i] == 'O' && a[1][i] == 'O' && a[2][i] == 'O'){
                return 2;
            }
        }

        if(a[0][0] == 'O' && a[1][1] == 'O' && a[2][2] == 'O'){
            return 2;
        }

        if(a[0][2] == 'O' && a[1][1] == 'O' && a[2][0] == 'O'){
            return 2;
        }   

        if(can_move()){
            return 0;
        } else {
            return -1;
        }
    }

    public boolean can_move(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(a[i][j] == '_'){
                    return true;
                }
            }
        }
        return false;
    }
}
