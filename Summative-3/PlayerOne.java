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
    // movement speed.
    final int SPEED_X = 7;
    int gravity = 1;
    int jumpHight = -15;
    boolean isInAir = true;
    int degrees = 360/8;
    Bubble[] bubbles;
    /**
     * Act - do whatever the PlayerOne wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        bumpHead();
        movementControls();
    }    
    public void movementControls()
    {
        deltaX = 0;
        if(Greenfoot.isKeyDown("left"))
        {
            deltaX = deltaX - SPEED_X;
        }
        if(Greenfoot.isKeyDown("right"))
        {
            deltaX = deltaX + SPEED_X;
        }
        if (Greenfoot.isKeyDown("space")&& isInAir == false)
        {
            deltaY = jumpHight;
            isInAir = true;
        }
        // update position
        setLocation(getX() + deltaX, getY() + deltaY);
        applyGravity();
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
    /**
     *  if on block able to jump. if not you are not apply gravity.
     */
    public void applyGravity()
    {
        int height = getImage().getHeight();
        // checking whether falling.
        Actor platform = getOneObjectAtOffset(0, height / 2, Brick.class);
        if(platform != null)
        {
            //standing on brick don't apply gravity.
            deltaY = 0;
            isInAir = false;
            moveOnTopOfObject(platform);
        }
        else
        {
            //not touching the brick apply gravity.
            deltaY = deltaY + gravity;
            isInAir = true;
        }
    }
    /**
     *  if ObjectAtOffset collides inact stayUnderBrick.
     */
    public void bumpHead()
    {
        int height = getImage().getHeight();
        Actor bump = getOneObjectAtOffset(0, - height / 2, Brick.class);
        if (bump != null)
        {
            stayUnderBrick(bump);
        }
    }
    /**
     *  stay under the brick when collision occurs.
     */
    public void stayUnderBrick(Actor object)
    {
         int height = getImage().getHeight();
         int objectHeight = object.getImage().getHeight();
        
         setLocation(getX(), object.getY() + objectHeight / 2 +  height / 2 + 1);
    }
    /**
     *  stay on top of the Brick when standing on it.
     */
    public void moveOnTopOfObject(Actor object)
    {
        int height = getImage().getHeight();
        int objectHeight = object.getImage().getHeight();
        
        // Adjust character position so that its bottom edge is just touching top edge of object.
        setLocation(getX(), object.getY() - objectHeight / 2 - height / 2 + 1); // Plus 1 to make character sink into object by 1 pixel.
        
    }
    public void forceField()
    {
        bubbles = new Bubble[8];
        for (int i = 0; i < bubbles.length; i++)
        {
            getWorld().addObject(bubbles[i],getX(),getY());
            setRotation(i * degrees);
            move(50);
        }
    }
}
