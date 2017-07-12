package Model;



import javax.media.j3d.Material;
import javax.media.j3d.Texture;

public class Planeta extends CuerpoEstelar {

    private String nombre;

    public Planeta(String nombre, int radio, Texture textura, Material material, int velocidadR, int distancia, int velocidadT) {
        super(radio, textura, material, velocidadR, distancia, velocidadT);
        this.nombre = nombre;
    }

    
}
