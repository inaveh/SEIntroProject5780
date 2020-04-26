package renderer;

import elements.AmbientLight;
import elements.Camera;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test rendering abasic image
 *
 * @author Dan Zilberstein
 */
class RenderTest {

        /**
         * Produce a scene with basic 3D model and render it into a jpeg image with a
         * grid
         */
        @Test
        public void basicRenderTwoColorTest() {
            Scene scene = new Scene("Test scene");
            scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(100);
            scene.setBackground(new Color(75, 127, 255));
            scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1));

            scene.addGeometries(new Sphere(50, new Point3D(0, 0, 100)));

            scene.addGeometries(
                    new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                    new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                    new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                    new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

            ImageWriter imageWriter = new ImageWriter("base render test", 500, 500, 500, 500);
            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.printGrid(50, java.awt.Color.YELLOW);
            render.writeToImage();
        }
    }
