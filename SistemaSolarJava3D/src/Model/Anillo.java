package Model;


import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

public class Anillo extends BranchGroup{
    int velR;
    float radio;
    public Anillo(float tam, Texture tex, int vel){
        radio = tam;
        velR = vel;
        Material material = new Material(
            new Color3f (0.22f, 0.22f, 0.67f),   // Color ambiental
            new Color3f (0.22f, 0.22f, 0.67f),   // Color emisivo
            new Color3f (0.0f, 0.0f, 0.99f),   // Color difuso
            new Color3f (0.22f, 0.22f, 0.67f),   // Color especular
            1.0f);
         
        
        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.REPLACE);
        TransparencyAttributes transat = new TransparencyAttributes();
        transat.setTransparencyMode(TransparencyAttributes.BLENDED);
        transat.setTransparency(0f);
        
        Appearance ap = new Appearance();
        ap.setMaterial(material);
        ap.setTextureAttributes(ta);
        ap.setTexture(tex);
        ap.setTransparencyAttributes(transat);
        
        Box box = new Box(tam,0.0f,tam,Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS,ap);
        TransformGroup rotacion = crearRotacion();
        rotacion.addChild(box);
        this.addChild(rotacion);
    }
    protected TransformGroup crearRotacion () {
    // Se crea el grupo que contendrá la transformación de rotación
    // Todo lo que cuelgue de él rotará
    TransformGroup transform = new TransformGroup ();
    // Se le permite que se cambie en tiempo de ejecución
    transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    // Se crea la matriz de rotación
    Transform3D yAxis = new Transform3D ();
    // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
    Alpha value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
            velR, 0, 0, 0, 0, 0);
   
    // Se crea el interpolador de rotación, las figuras iran rotando angulo minimo 0 y maximo 2*pi
    RotationInterpolator rotator = new RotationInterpolator (value, transform, yAxis,
        0.0f, (float) Math.PI*2.0f);
    // Se le pone el entorno de activación y se activa
    rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), radio));
    rotator.setEnable(true);
    // Se cuelga del grupo de transformación y este se devuelve
    transform.addChild(rotator);
    return transform;
  }
}
