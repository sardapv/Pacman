// PacActor.java
// Used for PacMan

import ch.aplu.jgamegrid.*;
import ch.aplu.util.SliderEntry;

import java.awt.event.KeyEvent;
import java.awt.*;

public class PacActor extends Actor implements GGKeyRepeatListener
{ 
  private static final int nbSprites = 4;
  private int idSprite = 0;
  private int nbPills = 0;

  public PacActor()
  {
    super(true, "sprites/pacpix.gif", nbSprites);  // Rotatable
  }

  public void keyRepeated(int keyCode)
  {
    if (isRemoved())  // Already removed
      return;
    Location next = null;
    switch (keyCode){
 
      case KeyEvent.VK_LEFT:
    	 gameGrid.playSound(GGSound.CLICK);
        next = getLocation().getNeighbourLocation(Location.WEST);
        setDirection(Location.WEST);
        break;
      case KeyEvent.VK_UP:
    	  gameGrid.playSound(GGSound.CLICK);
        next = getLocation().getNeighbourLocation(Location.NORTH);
        setDirection(Location.NORTH);
        break;
      case KeyEvent.VK_RIGHT:
    	  gameGrid.playSound(GGSound.CLICK);
        next = getLocation().getNeighbourLocation(Location.EAST);
        setDirection(Location.EAST);
        break;
      case KeyEvent.VK_DOWN:
    	  gameGrid.playSound(GGSound.CLICK);
        next = getLocation().getNeighbourLocation(Location.SOUTH);
        setDirection(Location.SOUTH);
        break;
    }
    if (next != null && canMove(next))
    {
      setLocation(next);
      eatPill(next);
    }
  }

  public void act()
  {
    show(idSprite);
    idSprite++;
    if (idSprite == nbSprites)
      idSprite = 0;
  }

  private boolean canMove(Location location)
  {
    Color c = getBackground().getColor(location);
    if (c.equals(Color.blue))
      return false;
    else
      return true;
  }

  private void eatPill(Location location)
  {
    Color c = getBackground().getColor(location);
    if (c.equals(Color.yellow))
    {
      nbPills++;
      getBackground().fillCell(location, Color.black);
      gameGrid.setTitle("# Eaten Pills: " + nbPills);
      if(nbPills == 246){
    	  
    	  delay(1000);
    	  System.exit(1);
    
      }
    }
  }
}
