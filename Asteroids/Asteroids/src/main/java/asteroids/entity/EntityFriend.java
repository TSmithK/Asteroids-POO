
package asteroids.entity;

import asteroids.Game;
import asteroids.util.Vector2;
import java.awt.Graphics2D;

/**
 *
 * @author USRDOTECH
 */
public abstract class EntityFriend extends Entity{
    
    public EntityFriend(Vector2 vector, Vector2 velocity, double radius, int killScore){
        super(vector, velocity, radius, killScore);
    }
    
    @Override
    public abstract void draw(Graphics2D g, Game game);

    @Override
    public abstract void handleCollision(Game game, Entity other);

    @Override
    public boolean checkCollision(Entity entity) {
        return super.checkCollision(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Game game) {
        super.update(game); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean needsRemoval() {
        return super.needsRemoval(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getCollisionRadius() {
        return super.getCollisionRadius(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getRotation() {
        return super.getRotation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector2 getVelocity() {
        return super.getVelocity(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector2 getPosition() {
        return super.getPosition(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void flagForRemoval() {
        super.flagForRemoval(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getKillScore() {
        return super.getKillScore(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotate(double amount) {
        super.rotate(amount); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
