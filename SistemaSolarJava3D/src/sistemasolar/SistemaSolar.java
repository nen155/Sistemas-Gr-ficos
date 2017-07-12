/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasolar;

import GUI.ControlWindow;
import Model.Universo;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.util.ArrayList;
import javax.media.j3d.Canvas3D;

/**
 *
 * @author Julian
 */
public class SistemaSolar {

    /**
     * @param args the command line arguments
     */
    
  public static void main(String[] args) {

    // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
    Canvas3D vistaPlanta = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    // Se le da el tamaño deseado
    vistaPlanta.setSize(600, 600);
        // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
    Canvas3D vistaLibre = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    // Se le da el tamaño deseado
    vistaLibre.setSize(600, 600);
        // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
    Canvas3D vistaLuna = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    // Se le da el tamaño deseado
    vistaLuna.setSize(600, 600);
    
            // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
    Canvas3D vistaNave = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    // Se le da el tamaño deseado
    vistaNave.setSize(600, 600);
    ArrayList<Canvas3D> canvass = new ArrayList();
    canvass.add(vistaLibre);
     canvass.add(vistaLuna);
    canvass.add(vistaPlanta);
     canvass.add(vistaNave);
    
    // Se crea el Universo con dicho Canvas3D
    Universo universe = new Universo (canvass);


    // Se crea la GUI a partir del Canvas3D y del Universo
    ControlWindow controlWindow = new ControlWindow (canvass, universe);
    // Se muestra la ventana principal de la aplicación
    controlWindow.showWindow ();
  }
    
}
