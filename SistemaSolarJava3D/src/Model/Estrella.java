package Model;


import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Light;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.Texture;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

public class Estrella extends CuerpoEstelar {
    
    
    private BranchGroup light;

    public Estrella(int radio, Texture textura,Material material ,int velocidadR, int distancia, int velocidadT) {
        super(radio, textura, material, velocidadR, distancia, velocidadT);
        light = new BranchGroup();
        BoundingSphere influencingBound = new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100000);
        Light aLight = new PointLight (new Color3f(0.7f, 0.7f, 0.7f), new Point3f(distancia,0,0), new Point3f(1,0,0));
        
        aLight.setInfluencingBounds (influencingBound);
        aLight.setCapability(Light.ALLOW_STATE_WRITE);
        aLight.setEnable (true);
        light.addChild(aLight);
        this.addChild(light);
    }
    
}
