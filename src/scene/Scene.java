package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import geometries.Sphere;
import primitives.Color;

public class Scene {
    private  String _name;
    private Color _background;
    private Camera _camera;
    private  double _distance;
    private AmbientLight _ambientLight;
    private Geometries _geometries;


    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    public Camera getCamera() {
        return _camera;
    }

    public Geometries getGeometries() {
        return _geometries;
    }

    public double getDistance() {
        return _distance;
    }


    public Scene(String _sceneName) {
        this._name = _sceneName;
        this._geometries = new Geometries();
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public void setBackground(Color _background) {
        this._background = _background;
    }

    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    public void setDistance(double _distance) {
        this._distance = _distance;
    }

    public void addGeometries(Intersectable... intersectables) {
        for (Intersectable i:intersectables ) {
            _geometries.add(i);
        }
    }

    public Color getBackground() {
        return this._background;
    }

}
