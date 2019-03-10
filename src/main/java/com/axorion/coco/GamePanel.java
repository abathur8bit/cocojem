package com.axorion.coco;

import javax.swing.*;
import java.awt.*;

class GamePanel extends JPanel implements Tickable
{
    float interpolation;
    float ballX, ballY, lastBallX, lastBallY;
    int ballWidth, ballHeight;
    float ballXVel, ballYVel;
    float ballSpeed;

    int lastDrawX, lastDrawY;
    private int fps = 60;
    private int frameCount = 0;

    @Override
    public String toString() {
        return "GamePanel{"+
                "interpolation="+interpolation+
                ", ballX="+ballX+
                ", ballY="+ballY+
                ", lastBallX="+lastBallX+
                ", lastBallY="+lastBallY+
                ", ballWidth="+ballWidth+
                ", ballHeight="+ballHeight+
                ", ballXVel="+ballXVel+
                ", ballYVel="+ballYVel+
                ", ballSpeed="+ballSpeed+
                ", lastDrawX="+lastDrawX+
                ", lastDrawY="+lastDrawY+
                '}';
    }

    public void tick(long now) {}
    public int getFps() {return 0;}

    public GamePanel(JFrame parent)
    {
        ballX = lastBallX = 100;
        ballY = lastBallY = 100;
        ballWidth = 25;
        ballHeight = 25;
        ballSpeed = 25;
        ballXVel = (float) Math.random() * ballSpeed*2 - ballSpeed;
        ballYVel = (float) Math.random() * ballSpeed*2 - ballSpeed;
    }

    public void setInterpolation(float interp)
    {
        interpolation = interp;
    }

    public void update()
    {
        lastBallX = ballX;
        lastBallY = ballY;

        ballX += ballXVel;
        ballY += ballYVel;

        if (ballX + ballWidth/2 >= getWidth())
        {
            ballXVel *= -1;
            ballX = getWidth() - ballWidth/2;
            ballYVel = (float) Math.random() * ballSpeed*2 - ballSpeed;
        }
        else if (ballX - ballWidth/2 <= 0)
        {
            ballXVel *= -1;
            ballX = ballWidth/2;
        }

        if (ballY + ballHeight/2 >= getHeight())
        {
            ballYVel *= -1;
            ballY = getHeight() - ballHeight/2;
            ballXVel = (float) Math.random() * ballSpeed*2 - ballSpeed;
        }
        else if (ballY - ballHeight/2 <= 0)
        {
            ballYVel *= -1;
            ballY = ballHeight/2;
        }
    }

    public void paintComponent(Graphics g)
    {
        //BS way of clearing out the old rectangle to save CPU.
        g.setColor(getBackground());
        g.fillRect(lastDrawX-1, lastDrawY-1, ballWidth+2, ballHeight+2);
        g.fillRect(5, 0, 75, 30);

        g.setColor(Color.RED);
        int drawX = (int) ((ballX - lastBallX) * interpolation + lastBallX - ballWidth/2);
        int drawY = (int) ((ballY - lastBallY) * interpolation + lastBallY - ballHeight/2);
        g.fillOval(drawX, drawY, ballWidth, ballHeight);

        lastDrawX = drawX;
        lastDrawY = drawY;

        g.setColor(Color.BLACK);
        g.drawString("FPS: " + fps, 5, 10);

        frameCount++;
    }

    public int getLastDrawX() {
        return lastDrawX;
    }

    public void setLastDrawX(int lastDrawX) {
        this.lastDrawX = lastDrawX;
    }

    public int getLastDrawY() {
        return lastDrawY;
    }

    public void setLastDrawY(int lastDrawY) {
        this.lastDrawY = lastDrawY;
    }
}