import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class FlappyBirdGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Flappy Bird");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 600);
            frame.setResizable(false);

            Game game = new Game();
            frame.add(game);
            frame.setVisible(true);

            game.startGame();
        });
    }
}

class Game extends JPanel implements KeyListener, ActionListener {
    private int birdY = 250, birdX = 100;
    private int velocity = 0;
    private boolean gameOver = false;
    private List<Pipe> pipes;
    private Timer timer;
    private int score = 0;
    private BufferedImage birdImage;

    public Game() {
        setBackground(Color.CYAN);
        pipes = new LinkedList<>();
        addKeyListener(this);
        setFocusable(true);
        loadBirdImage();

        timer = new Timer(20, this);
        generatePipes();
    }

    private void loadBirdImage() {
        try {
            birdImage = ImageIO.read(new File("download.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        timer.start();
    }

    private void generatePipes() {
        Random rand = new Random();
        int x = 500; // Vị trí bắt đầu của ống đầu tiên
        int minDistance = 250; // Minimum distance between pipes

        for (int i = 0; i < 5; i++) {
            int topHeight = rand.nextInt(250) + 50; // Đảm bảo khoảng trống đủ lớn
            pipes.add(new Pipe(x, topHeight));
            x += minDistance + Pipe.WIDTH + 50; // Khoảng cách giữa các ống, đảm bảo không quá gần
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            velocity += 1;
            birdY += velocity;

            movePipes();
            checkCollisions();
            repaint();
        }
    }

    private void movePipes() {
        List<Pipe> newPipes = new LinkedList<>();
        for (Pipe pipe : pipes) {
            pipe.x -= 5;
            if (pipe.x + Pipe.WIDTH > 0) {
                newPipes.add(pipe);
            } else {
                score++;
            }
        }

        if (!newPipes.isEmpty()) {
            Pipe lastPipe = newPipes.get(newPipes.size() - 1);
            if (lastPipe.x < 500) {
                 newPipes.add(new Pipe(500 + Pipe.WIDTH + 200, new Random().nextInt(250) + 50));
            }
        } else {
             newPipes.add(new Pipe(500 + Pipe.WIDTH + 200, new Random().nextInt(250) + 50));
        }
        
        pipes = newPipes;
    }

    private void checkCollisions() {
        if (birdY < 0 || birdY > getHeight() - 50) {
            gameOver = true;
            timer.stop();
        }

        for (Pipe pipe : pipes) {
            if (pipe.collidesWith(birdX, birdY)) {
                gameOver = true;
                timer.stop();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Vẽ chim
        if (birdImage != null) {
            g.drawImage(birdImage, birdX, birdY, 50, 50, this);
        } else {
            g.setColor(Color.YELLOW);
            g.fillOval(birdX, birdY, 50, 50);
        }

        // Vẽ ống nước
        g.setColor(Color.GREEN);
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        // Vẽ điểm số
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 20, 40);

        // Nếu game over, hiển thị thông báo
        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(Color.RED);
            g.drawString("Game Over!", 150, 300);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            velocity = -10;
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            restartGame();
        }
    }

    private void restartGame() {
        birdY = 250;
        velocity = 0;
        score = 0;
        gameOver = false;
        pipes.clear();
        generatePipes();
        timer.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

class Pipe {
    int x, topHeight;
    static final int WIDTH = 75, HEIGHT = 600, GAP = 180; // Khoảng trống rộng hơn để chim dễ bay

    public Pipe(int x, int topHeight) {
        this.x = x;
        this.topHeight = topHeight;
    }

    public void draw(Graphics g) {
        // Vẽ ống trên
        g.fillRect(x, 0, WIDTH, topHeight);

        // Vẽ ống dưới
        g.fillRect(x, topHeight + GAP, WIDTH, HEIGHT - (topHeight + GAP));
    }

    public boolean collidesWith(int birdX, int birdY) {
        return (birdX + 50 > x && birdX < x + WIDTH &&
                (birdY < topHeight || birdY + 50 > topHeight + GAP));
    }
}
