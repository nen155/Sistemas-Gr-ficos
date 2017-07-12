package Model;


import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class VistaEscena extends Vista {

    public VistaEscena(Canvas3D canvas) {
        super(canvas);
        vp = new ViewingPlatform();
    vp.getViewPlatform().setActivationRadius(100000.0f);
    // La transformación de vista, dónde se está, a dónde se mira, Vup
    TransformGroup viewTransformGroup = vp.getViewPlatformTransform();
    Transform3D viewTransform3D = new Transform3D();
    viewTransform3D.lookAt (new Point3d (0,8000.0f,0), new Point3d (0,0,0), new Vector3d (0,0,-1));
    viewTransform3D.invert();
    viewTransformGroup.setTransform (viewTransform3D);
    
    // Se establece el angulo de vision a 45 grados y el plano de recorte trasero
    viewer = new Viewer (canvas);
    viewer.setViewingPlatform(vp);
    View view = viewer.getView();
    view.setPhysicalBody(new PhysicalBody());
    view.setPhysicalEnvironment(new PhysicalEnvironment());
    view.setProjectionPolicy(View.PARALLEL_PROJECTION);
    view.setScreenScalePolicy(View.SCALE_EXPLICIT) ;
    view.setScreenScale(0.00003) ;
    view.setFrontClipDistance(0.1) ;
    //view.setFieldOfView(Math.toRadians(90));
    
    view.setBackClipDistance(100000);
    }
}
