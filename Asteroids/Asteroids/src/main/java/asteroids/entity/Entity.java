package asteroids.entity;

import asteroids.entity.satelite.PowerUpSatelite;
import java.awt.Graphics2D;

import asteroids.Game;
import asteroids.WorldPanel;
import asteroids.util.Vector2;

/**
 * Represents an Entity within the game world.
 *
 * @author Brendan Jones
 *
 */
public abstract class Entity {

    /**
     * The position of this entity.
     */
    public Vector2 position;

    /**
     * The velocity of this entity.
     */
    public Vector2 velocity;

    /**
     * The rotation of this entity.
     */
    protected double rotation;

    /**
     * The collision radius.
     */
    protected double radius;

    /**
     * Flags that the entity needs to be removed from the game.
     */
    private boolean needsRemoval;

    /**
     * The number of points the player earns when this entity is destroyed.
     */
    private int killScore;

    /**
     * Creates a new Entity instance.
     *
     * @param position The position of the Entity.
     * @param velocity The velocity of the Entity.
     * @param radius The collision radius.
     * @param killScore The number of points awarded for killing this entity.
     */
    public Entity(Vector2 position, Vector2 velocity, double radius, int killScore) {
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.rotation = 0.0f;
        this.killScore = killScore;
        this.needsRemoval = false;
    }

    /**
     * Rotates this Entity by amount.
     *
     * @param amount The amount to rotate by.
     */
    public void rotate(double amount) {
        this.rotation += amount;
        this.rotation %= Math.PI * 2;
    }

    /**
     * Gets the number of points awarded for killing this entity.
     *
     * @return The kill score.
     */
    public int getKillScore() {
        return killScore;
    }

    /**
     * Flags that this Entity should be removed from the world.
     */
    public void flagForRemoval() {
        this.needsRemoval = true;
    }

    /**
     * Gets the position of this Entity.
     *
     * @return The position.
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Gets the velocity of this Entity.
     *
     * @return The velocity.
     */
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * Gets the rotation of this Entity.
     *
     * @return The rotation.
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * Gets the collision radius of this Entity.
     *
     * @return The collision radius.
     */
    public double getCollisionRadius() {
        return radius;
    }

    /**
     * Checks whether this Entity needs to be removed.
     *
     * @return Whether this Entity needs to be removed.
     */
    public boolean needsRemoval() {
        return needsRemoval;
    }

    /**
     * Updates the state of this Entity.
     *
     * @param game The game instance.
     */
    public void update(Game game) {
        position.add(velocity);
        if (position.x < 0.0f) {
            position.x += WorldPanel.WORLD_SIZE;
        }
        if (position.y < 0.0f) {
            position.y += WorldPanel.WORLD_SIZE;
        }
        // aqui verificamos a donde vamos a girar
        if (getClass() == PowerUpSatelite.class) {
            double rads = Math.toRadians(game.getPlayer().posicionActualSatelite() - 90); // 0 becomes the top
            position.x = game.getPlayer().position.x + Math.round((float) (Math.cos(rads) * 50));
            position.y = game.getPlayer().position.y + Math.round((float) (Math.sin(rads) * 50));
            return;
        }
        position.x %= WorldPanel.WORLD_SIZE;
        position.y %= WorldPanel.WORLD_SIZE;
    }

    /**
     * Determines whether two Entities have collided.
     *
     * @param entity The Entity to check against.
     * @return Whether a collision occurred.
     */
    public boolean checkCollision(Entity entity) {
        /*
		 * Here we use the Pythagorean Theorem to determine whether the two
		 * Entities are close enough to collide.
		 * 
		 * The reason we are squaring everything is because it's much, much
		 * quicker to square one variable than it is to take the square root
		 * of another. While this game is simple enough that such minor
		 * optimizations are unnecessary, it's still a good habit to get
		 * into.
         */
        double radius = entity.getCollisionRadius() + getCollisionRadius();
        return (position.getDistanceToSquared(entity.position) < radius * radius);
    }

    /**
     * Handle a collision with another Entity.
     *
     * @param game The game instance.
     * @param other The Entity that we collided with.
     */
    public abstract void handleCollision(Game game, Entity other);

    /**
     * Draw this Entity onto the window.
     *
     * @param g The Graphics instance.
     * @param game The game instance.
     */
    public abstract void draw(Graphics2D g, Game game);
}
