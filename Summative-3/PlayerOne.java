import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerOne extends Actor
{
    int deltaX = 0;
    int deltaY = 0;
    boolean isInAir = true;
    /**
     * Act - do whatever the PlayerOne wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        deltaX = 0;
        if(Greenfoot.isKeyDown("left"))
        {
            deltaX = deltaX - 5;
        }
        if(Greenfoot.isKeyDown("right"))
        {
            deltaX = deltaX + 5;
        }
        if (Greenfoot.isKeyDown("space")&& isInAir == false)
        {
            deltaY = -15;
            isInAir = true;
        }
        // update position
        setLocation(getX() + deltaX, getY() + deltaY);
        
        // check weather falling.
        if(isTouching(Brick.class))
        {
            //standing on brick don't apply gravity.
            deltaY = 0;
            isInAir = false;
        }
        else 
        {
            // Not touching the brick apply gravity
            deltaY = deltaY + 1;
            isInAir = true;
        }
    }    
}
