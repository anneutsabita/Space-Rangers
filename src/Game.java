import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Game {

    public static final int Meteor_DELAY = 100;

    private Boolean paused;

    private int pauseDelay;
    private double pause;
    //casting/conversion
    private int MeteorDelay = (int) pause;
    private int restartDelay;

    private Rocket rocket;
    private ArrayList<Meteor> Meteors; // ArrayList
    private Keyboard keyboard;

    public int score;
    public Boolean gameover;
    public Boolean started;

    //constructor
    public Game() {
        keyboard = Keyboard.getInstance();
        restart();
    }

    public void restart() {
        paused = false;
        started = false;
        gameover = false;

        int highScore = score;
        setHighScore(highScore);
        
        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        MeteorDelay = 0;

        rocket = new Rocket();
        Meteors = new ArrayList<Meteor>();
    }
    
    //input output
    public void setHighScore (int highScore) {
        
        //Exception Handling
        try{
        
        File file = new File("highscore.txt");
        
        file.createNewFile();
        
        PrintWriter pw = new PrintWriter(file);
                         
        pw.println(highScore);
        pw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        watchForStart();

        if (!started)
            return;

        watchForPause();
        watchForReset();

        if (paused)
            return;

        rocket.update();

        if (gameover)
            return;

        moveMeteors();
        checkForCollisions();
    }

    public ArrayList<Render> getRenders() {
        ArrayList<Render> renders = new ArrayList<Render>();
        renders.add(new Render(0, 0, "src/resources/space.jpg"));
        for (Meteor Meteor : Meteors)
            renders.add(Meteor.getRender());
        renders.add(new Render(0, 0, "src/resources/planet.png"));
        renders.add(rocket.getRender());
        return renders;
    }

    private void watchForStart() {
        if (!started && keyboard.isDown(KeyEvent.VK_SPACE)) {
            started = true;
        }
    }

    private void watchForPause() {
        if (pauseDelay > 0)
            pauseDelay--;

        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = 10;
        }
    }

    private void watchForReset() {
        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0) {
            restart();
            restartDelay = 10;
            return;
        }
    }

    private void moveMeteors() {
        MeteorDelay--;

        if (MeteorDelay < 0) {
            MeteorDelay = Meteor_DELAY;
            Meteor northMeteor = null;
            Meteor southMeteor = null;

            // Look for Meteors off the screen
            for (Meteor Meteor : Meteors) {
                if (Meteor.x - Meteor.width < 0) {
                    if (northMeteor == null) {
                        northMeteor = Meteor;
                    } else if (southMeteor == null) {
                        southMeteor = Meteor;
                        break;
                    }
                }
            }

            if (northMeteor == null) {
                Meteor Meteor = new Meteor("north");
                Meteors.add(Meteor);
                northMeteor = Meteor;
            } else {
                northMeteor.reset();
            }

            if (southMeteor == null) {
                Meteor Meteor = new Meteor("south");
                Meteors.add(Meteor);
                southMeteor = Meteor;
            } else {
                southMeteor.reset();
            }

            northMeteor.y = southMeteor.y + southMeteor.height + 175;
        }

        for (Meteor Meteor : Meteors) {
            Meteor.update();
        }
    }

    private void checkForCollisions() {

        for (Meteor Meteor : Meteors) {
            if (Meteor.collides(rocket.x, rocket.y, rocket.width, rocket.height)) {
                gameover = true;
                rocket.dead = true;
            } else if (Meteor.x == rocket.x && Meteor.orientation.equalsIgnoreCase("south")) {
                score++;
            }
        }

        // Ground + Rocket collision
        if (rocket.y + rocket.height > App.HEIGHT - 80) {
            gameover = true;
            rocket.y = App.HEIGHT - 80 - rocket.height;
        }
    }
}
