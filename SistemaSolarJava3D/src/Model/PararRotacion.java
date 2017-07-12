/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.pickfast.PickCanvas;
import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
import javax.media.j3d.Shape3D;
import javax.media.j3d.WakeupOnAWTEvent;

/**
 *
 * @author Julian
 */
public class PararRotacion extends Behavior{

    private WakeupOnAWTEvent condition ;
    private PickCanvas pickCanvas ;
    private Canvas3D canvas ;
    public PararRotacion (Canvas3D aCanvas,BranchGroup bg ) {
        canvas = aCanvas ;
        condition = new WakeupOnAWTEvent (MouseEvent.MOUSE_CLICKED) ;
        pickCanvas = new PickCanvas ( canvas , bg ) ;
        pickCanvas . setTolerance (1.0f ) ;
        pickCanvas . setMode ( PickInfo.PICK_GEOMETRY) ;
        pickCanvas . setFlags ( PickInfo .NODE | PickInfo .CLOSEST_GEOM_INFO) ;
        
    }

   @Override
    public void initialize() {
        setEnable ( true ) ;
        wakeupOn (condition ) ;
    }
    @Override
    public void processStimulus ( Enumeration cond ) {
        WakeupOnAWTEvent c = (WakeupOnAWTEvent) cond.nextElement ( ) ;
        AWTEvent[]e = c.getAWTEvent ( ) ;
        MouseEvent mouse = (MouseEvent)e[0] ;
        pickCanvas.setShapeLocation(mouse) ;
        PickInfo pi = pickCanvas . pickClosest() ;
        if(pi!=null){
            Shape3D shape = (Shape3D) pi.getNode() ;
            if (shape.getParent().getUserData() != null)
            {
                try {
                    ((CuerpoEstelar)shape.getParent().getUserData()).stopStart();
                } catch (InterruptedException ex) {
                    Logger.getLogger(PararRotacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        wakeupOn(condition ) ;
    }
    
}
