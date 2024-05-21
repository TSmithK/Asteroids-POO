package asteroids.entity.satelite;

import asteroids.Game;
import asteroids.entity.Asteroid;
import asteroids.entity.Entity;
import asteroids.entity.EntityFriend;
import asteroids.entity.Player;
import asteroids.util.Vector2;
import java.awt.Color;
import java.awt.Graphics2D;

public class Poder extends EntityFriend {
    Vector2 shapePower;
    
    public Poder(Asteroid asteroid, double radius) {
        super(new Vector2(asteroid.position.x, asteroid.position.y), new Vector2(1).scale(1), 5, 0);
        shapePower = new Vector2(0.1, 0.0);
        shapePower.add(new Vector2(.2, 0.1));
    }

    @Override
    public void draw(Graphics2D g, Game game) {
        // Guarda el color actual del Graphics2D
        Color originalColor = g.getColor();

        // Establece el color a un tono de morado claro para los orbes y el escudo
        g.setColor(new Color(204, 153, 255));
        g.drawOval(-5, -5, 20, 20); // Dibuja el contorno del óvalo
        g.fillOval(-5, -5, 20, 20); // Rellena el óvalo

        // Restaura el color original para que otros elementos no se vean afectados
        g.setColor(originalColor);
    }

    @Override
    public void handleCollision(Game game, Entity other) {
        if (other.getClass() == Player.class && !game.poderActivado()) {
            game.setObtainPower(true);
            PowerUpSatelite satelite = new PowerUpSatelite(game.getPlayer());
            game.registerEntity(satelite);
            game.powerSalelite = satelite;
            flagForRemoval();
        }
    }
}
