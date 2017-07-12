package Model;

import javax.media.j3d.Material;

import javax.media.j3d.Texture;

public class Satelite extends CuerpoEstelar {

    public Satelite(int radio, Texture textura, Material material, int velocidadR, int distancia, int velocidadT) {
        super(radio, textura, material, velocidadR, distancia, velocidadT);

    }
     
}
