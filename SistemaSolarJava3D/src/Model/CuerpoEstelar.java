package Model;


import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public abstract class CuerpoEstelar extends BranchGroup {

    protected int radio;

    protected Texture textura;

    protected Appearance apariencia;

    protected Sphere esfera; 

    protected int velocidadR;

    protected int distancia;

    protected int velocidadT;
    
    protected Material material;
    
    protected RotationInterpolator rotator;
    
    protected boolean rotando = true;
    
    protected TransformGroup translacion;
    protected TransformGroup desplazamiento;
    protected TransformGroup rotacion;
    
    /// El objeto que controla la rotación contínua de la figura
    protected Alpha value;
    
    protected static final BoundingSphere radioAccion = new BoundingSphere(new Point3d(0,0,0),100000);

    public CuerpoEstelar(int radio, Texture textura, Material material, int velocidadR, int distancia, int velocidadT) {
        this.radio = radio;
        this.textura = textura;
        this.velocidadR = velocidadR;
        this.distancia = distancia;
        this.velocidadT = velocidadT;
        apariencia = new Appearance();
        apariencia.setMaterial(material);
        apariencia.setTexture(textura);
        esfera = new Sphere (radio, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 100, apariencia);
        translacion = crearTranslacion ();
        desplazamiento = crearDesplazamiento();
        rotacion = crearRotacion();      
        rotacion.addChild(esfera);
        desplazamiento.addChild(rotacion);
        translacion.addChild(desplazamiento);
        esfera.setUserData(this);
        this.addChild(translacion);
    }
    
    ///Rotación sobre su propio eje
    protected TransformGroup crearRotacion () {
    // Se crea el grupo que contendrá la transformación de rotación
    // Todo lo que cuelgue de él rotará
    TransformGroup transform = new TransformGroup ();
    // Se le permite que se cambie en tiempo de ejecución
    transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    // Se crea la matriz de rotación
    Transform3D yAxis = new Transform3D ();
    // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
    value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
            velocidadR, 0, 0, 0, 0, 0);
   
    // Se crea el interpolador de rotación, las figuras iran rotando angulo minimo 0 y maximo 2*pi
    rotator = new RotationInterpolator (value, transform, yAxis,
        0.0f, (float) Math.PI*2.0f);
    // Se le pone el entorno de activación y se activa
    rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), radio));
    rotator.setEnable(true);
    // Se cuelga del grupo de transformación y este se devuelve
    transform.addChild(rotator);
    return transform;
  }
    ///Translación planetaria
    protected TransformGroup crearTranslacion () {
    // Se crea el grupo que contendrá la transformación de rotación
    // Todo lo que cuelgue de él rotará
    TransformGroup transform = new TransformGroup ();
    // Se le permite que se cambie en tiempo de ejecución
    transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    // Se crea la matriz de rotación
    Transform3D yAxis = new Transform3D ();
    // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
    value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
            velocidadT, 0, 0, 0, 0, 0);
    // Se crea el interpolador de rotación, las figuras iran rotando angulo minimo 0 y maximo 2*pi
    RotationInterpolator rotator = new RotationInterpolator (value, transform, yAxis,
        0.0f, (float) Math.PI*2.0f);
    // Se le pone el entorno de activación y se activa
    rotator.setSchedulingBounds(radioAccion);
    rotator.setEnable(true);
    // Se cuelga del grupo de transformación y este se devuelve
    transform.addChild(rotator);
    return transform;
  }
    ///Movemos el planeta hasta su punto de translación
    protected TransformGroup crearDesplazamiento () {
    // Se crea el grupo que contendrá la transformación de rotación
    // Todo lo que cuelgue de él rotará
    TransformGroup transform = new TransformGroup ();
    // Se le permite que se cambie en tiempo de ejecución
    transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    // Se crea la matriz de rotación
    Transform3D trans = new Transform3D ();
    
    trans.setTranslation(new Vector3f(distancia,.0f,.0f));
    
    // Se cuelga del grupo de transformación y este se devuelve
    transform.setTransform(trans);
    
    return transform;
  }

    public TransformGroup getTranslacion() {
        return translacion;
    }

    public TransformGroup getDesplazamiento() {
        return desplazamiento;
    }

    public TransformGroup getRotacion() {
        return rotacion;
    }

    public void setTranslacion(TransformGroup translacion) {
        this.translacion = translacion;
    }

    public void setDesplazamiento(TransformGroup desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    public void setRotacion(TransformGroup rotacion) {
        this.rotacion = rotacion;
    }
    
    public void stopStart() throws InterruptedException{
        if(rotando){
            value.pause();
            rotando = false;
        }else{
            value.resume();
            rotando = true;
        }
    }
}
