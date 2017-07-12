package Model;


import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.Viewer;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class VistaLibre extends Vista {

    public VistaLibre(Canvas3D canvas) {
        super(canvas);
        vp.getViewPlatform().setActivationRadius(100000.0f);
        // La transformación de vista, dónde se está, a dónde se mira, Vup
        TransformGroup viewTransformGroup = vp.getViewPlatformTransform();
        Transform3D viewTransform3D = new Transform3D();
        viewTransform3D.lookAt (new Point3d (0,8000.0f,0), new Point3d (0,0,0), new Vector3d (0,0,-1));
        viewTransform3D.invert();
        viewTransformGroup.setTransform (viewTransform3D);

        // El comportamiento, para mover la camara con el raton
        OrbitBehavior orbit = new OrbitBehavior(canvas, OrbitBehavior.REVERSE_ALL);
        orbit.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 1000000.0f));
        orbit.setZoomFactor (500.0f);
        orbit.setTransFactors(500, 500);
        vp.setViewPlatformBehavior(orbit);

        // Se establece el angulo de vision a 45 grados y el plano de recorte trasero
        viewer = new Viewer (canvas);
        viewer.setViewingPlatform(vp);
        View view = viewer.getView();
        view.setFieldOfView(Math.toRadians(90));
        view.setBackClipDistance(1000000);
    
    }
    
}
