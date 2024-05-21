package asteroids.entity.satelite;

import asteroids.Game;
import asteroids.entity.Asteroid;
import asteroids.entity.Entity;
import asteroids.entity.EntityFriend;
import asteroids.entity.Player;
import asteroids.util.Vector2;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Polygon;

public class PowerUpSatelite extends EntityFriend {

    private double escudoAngulo;
    private double velocidadRotacion;
    private Player player;

    public static int VIDA_SATELITE = 3;
    int vidaRestante = 0;

    public PowerUpSatelite(Player player) {
        super(new Vector2(player.position.x, player.position.y), new Vector2(1).scale(1), 15, 0);
        this.player = player;
        vidaRestante = VIDA_SATELITE;
        escudoAngulo = 0;  
        velocidadRotacion = 0;
    }

    @Override
    public void draw(Graphics2D g, Game game) {
        // Guarda la transformación y el color actual del gráfico.
        AffineTransform oldTransform = g.getTransform();
        Color originalColor = g.getColor();

        // Calcula la posición del satélite en función del ángulo de rotación.
        double distanceFromPlayer = 50; // Ajusta esta distancia según lo necesario
        double sateliteX = player.position.x + distanceFromPlayer * Math.cos(Math.toRadians(escudoAngulo));
        double sateliteY = player.position.y + distanceFromPlayer * Math.sin(Math.toRadians(escudoAngulo));

        // Aplica la transformación para rotar alrededor del jugador
        AffineTransform transform = new AffineTransform();
        transform.translate(sateliteX, sateliteY);
        transform.rotate(Math.toRadians(escudoAngulo));

        g.setTransform(transform);
        
        // Establece el color a un tono de morado claro para el escudo
        g.setColor(new Color(204, 153, 255));
        // Dibuja la estrella en la posición transformada
        g.fillPolygon(createStar(0, 0, 15, 30, 5)); // Puedes ajustar el tamaño de la estrella aquí

        // Restaura la transformación y el color original del gráfico.
        g.setTransform(oldTransform);
        g.setColor(originalColor);
    }

    private Polygon createStar(int centerX, int centerY, int innerRadius, int outerRadius, int numRays) {
        double angle = Math.PI / numRays;

        Polygon star = new Polygon();
        for (int i = 0; i < numRays * 2; i++) {
            double r = (i & 1) == 0 ? outerRadius : innerRadius;
            double x = centerX + Math.cos(i * angle) * r;
            double y = centerY + Math.sin(i * angle) * r;
            star.addPoint((int) x, (int) y);
        }
        return star;
    }

    public void rotarIzquierda() {
        escudoAngulo -= 5;  
    }

    public void rotarDerecha() {
        escudoAngulo += 5; 
    }

    public int obtenerVidaRestante() {
        return vidaRestante;
    }
    
    public void detenerRotacion() {
        velocidadRotacion = 2; 
    }

    @Override
    public void handleCollision(Game game, Entity other) {
        if (game.poderActivado() && other.getClass() == Asteroid.class) {
            if (vidaRestante == 1) {
                vidaRestante = 0;
                flagForRemoval();
                game.setObtainPower(false);
            } else {
                vidaRestante = vidaRestante - 1;
            }
        }
    }

    public void update(double deltaTime) {
        // Actualiza el ángulo del satélite
        escudoAngulo += velocidadRotacion * deltaTime;
        
        // La posición se actualiza automáticamente en draw(), así que no necesitamos hacerlo aquí.
    }
}
