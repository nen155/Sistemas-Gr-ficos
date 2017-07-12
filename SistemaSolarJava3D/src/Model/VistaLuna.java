package Model;


import com.sun.j3d.utils.universe.Viewer;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;


public class VistaLuna extends Vista {
    
   
    
    public VistaLuna(Canvas3D canvas) {
        super(canvas);
       
        // La transformación de vista, dónde se está, a dónde se mira, Vup
        TransformGroup viewTransformGroup = vp.getViewPlatformTransform();
        Transform3D viewTransform3D = new Transform3D();
        viewTransform3D.lookAt (new Point3d (1,0,0), new Point3d (0,0,0), new Vector3d (0,1,0));
        viewTransform3D.invert();
        viewTransformGroup.setTransform (viewTransform3D);

        // Se establece el angulo de vision a 45 grados y el plano de recorte trasero
        viewer = new Viewer (canvas);
        viewer.setViewingPlatform(vp);
        View view = viewer.getView();
        view.setFieldOfView(Math.toRadians(90));
        view.setBackClipDistance(1000000);
    }

}
