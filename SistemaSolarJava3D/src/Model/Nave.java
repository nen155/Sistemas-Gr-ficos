package Model;


import static Model.CuerpoEstelar.radioAccion;
import java.util.ArrayList;
import javax.media.j3d.Alpha;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;


public class Nave extends BranchGroup {

    private ArrayList<Point3f> puntos;

    private ArrayList<AxisAngle4f> direcciones;
    
    RotPosPathInterpolator recorrido;
    TransformGroup posInicial ;

    public Nave(BranchGroup nave) {
        this.puntos = puntos;
        this.direcciones = direcciones;
        TransformGroup escalar = escalarNave();


        escalar.addChild(nave);
        posInicial = posicionInicial();
        posInicial.addChild(escalar);
        TransformGroup recorrido = recorridoNave();
        recorrido.addChild(posInicial);
        this.addChild(recorrido);
        nave.setUserData(this);
        
        
    }

    public TransformGroup getPosInicial() {
        return posInicial;
    }

    ///Escalar la nave
    protected TransformGroup escalarNave () {
        // Se crea el grupo que contendrá la transformación de escalado
        TransformGroup transform = new TransformGroup ();
        // Se crea la matriz de escalado
        Transform3D scale = new Transform3D ();
        scale.setScale(200);
        transform.setTransform(scale);

        return transform;
    }
    protected TransformGroup posicionInicial(){
        // Se crea el grupo que contendrá la transformación de translación
        TransformGroup transform = new TransformGroup ();
        // Se crea la matriz de translación
        Transform3D trans = new Transform3D ();
        trans.setTranslation(new Vector3f(2150.0f,0,0));
        transform.setTransform(trans);

        return transform;
        
    }

    protected TransformGroup recorridoNave(){
        // Se crea el grupo que contendrá la transformación de translación
        TransformGroup transform = new TransformGroup ();
        // Se le permite que se cambie en tiempo de ejecución
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         // Se crea la matriz de 
        Transform3D t3 = new Transform3D ();
        float [] alphas = { 0.0f , 0.2f , 0.4f ,0.8f , 1.0f } ;
        Point3f [ ] positions = {
            new Point3f(0.0f , 0.0f , 0.0f ) , new Point3f(0.0f , 0.0f , 1000.0f ) ,
            new Point3f(0.0f , 0.0f , 0.0f ) , new Point3f(0.0f , 3000.0f , 0.0f ),
            new Point3f(0.0f , 0.0f , 0.0f )
        } ;
        Quat4f[ ]   rotations  = new  Quat4f [ 5 ] ;
        for(int i  = 0 ; i < 5 ;i ++) 
            rotations [i]  =  new  Quat4f  ( ) ;
        rotations[4].set(new AxisAngle4f  (0.0f , 1.0f ,0.0f ,(float) Math.toRadians(0))) ;
        rotations[3].set(new AxisAngle4f  (0.0f , 1.0f ,0.0f ,(float) Math.toRadians(90))) ;
        rotations[2].set(new AxisAngle4f  (1.0f , 1.0f ,0.0f ,(float) Math.toRadians(180))) ;
        rotations[1].set(new AxisAngle4f  (1.0f , 1.0f ,0.0f ,(float) Math.toRadians(270))) ;
        rotations[0].set(new AxisAngle4f  (0.0f , 1.0f ,0.0f ,(float) Math.toRadians(360))) ;
        Alpha value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
            20000, 0, 0, 0, 0, 0);
        recorrido = new RotPosPathInterpolator(value,transform,t3,alphas,rotations,positions);
       // Se le pone el entorno de activación y se activa
        recorrido.setSchedulingBounds(radioAccion);
        recorrido.setEnable(true);
        // Se cuelga del grupo de transformación y este se devuelve
        transform.addChild(recorrido);
        return transform;
        
    }
    
}
