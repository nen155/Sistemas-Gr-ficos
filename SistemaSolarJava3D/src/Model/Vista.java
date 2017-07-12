package Model;


import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.Canvas3D;

public abstract class Vista {

    protected ViewingPlatform vp;

    protected Viewer viewer;
    
    protected Canvas3D canvas;

    public Vista(Canvas3D canvas) {
        this.canvas = canvas;
         // Se crea manualmente un ViewingPlatform para poder personalizarlo y asignárselo al universo
       vp = new ViewingPlatform();
       vp.getViewPlatform().setActivationRadius(100000.0f);
       
    }

    public ViewingPlatform getVp() {
        return vp;
    }

    public void setVp(ViewingPlatform vp) {
        this.vp = vp;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public Canvas3D getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas3D canvas) {
        this.canvas = canvas;
    }
    
    
    
}
