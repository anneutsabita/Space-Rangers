import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

public class Render {
    public int x;
    public int y;
    public Image image;
    public AffineTransform transform;

    //Overloading
    public Render() {
        Toolkit.getDefaultToolkit().sync();
    }

    public Render(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        this.image = Util.loadImage(imagePath);
    }
}
