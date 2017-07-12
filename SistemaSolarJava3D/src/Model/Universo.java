package Model;


import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Light;
import javax.media.j3d.Material;
import javax.media.j3d.SpotLight;
import javax.media.j3d.Texture;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

public class Universo {

    private ArrayList<Canvas3D> canvass;

    private SimpleUniverse universe;
    
    private BranchGroup inicial;

    private BranchGroup background;

    private BranchGroup luces;
    
    
    private VistaNave vNave;
    
    private VistaEscena vEscena;
    
    private VistaLuna vLuna;
    
    private VistaLibre vLibre;
    
    

    public Universo(ArrayList<Canvas3D> canvass) {
        // Atributos de referencia
        this.canvass = canvass;
        // Se crea el universo y la rama de la vista con ese canvas
        universe = crearUniverso(canvass);
        
        // creamos el branchgroup del que colgaran todos los planetas satelites etc...
        inicial = new BranchGroup();
        inicial.setPickable(true);
        

        // Se crea la rama del fondo y se cuelga al universo
        background = crearBackground();
        
        universe.addBranchGraph(background);

         // Se crean y se añaden unos ejes de coordenadas al universo

        // Se crean y se añaden luces
        luces = crearLuces();
        universe.addBranchGraph(luces);
        //MATERIALES
        Material material = new Material(
            new Color3f (0.22f, 0.22f, 0.67f),   // Color ambiental
            new Color3f (0.22f, 0.22f, 0.67f),   // Color emisivo
            new Color3f (0.0f, 0.0f, 0.99f),   // Color difuso
            new Color3f (0.22f, 0.22f, 0.67f),   // Color especular
            1.0f);
           Material material_sol = new Material(
            new Color3f (1.0f, 0.6f, 0.0f),   // Color ambiental
            new Color3f (1.0f, 0.6f, 0.0f),   // Color emisivo
            new Color3f (0.0f, 0.0f, 0.99f),   // Color difuso
            new Color3f (1.0f, 0.6f, 0.0f),   // Color especular
            100.0f);

        //TEXTURAS
        Texture texture_sol = new TextureLoader ("imgs/sol.jpg", null).getTexture();
        Texture texture_mercurio = new TextureLoader ("imgs/mercurio.jpg", null).getTexture();
        Texture texture_venus = new TextureLoader ("imgs/venus.jpg", null).getTexture();
        Texture texture_tierra = new TextureLoader ("imgs/tierra.jpg", null).getTexture();
        Texture texture_marte = new TextureLoader ("imgs/marte.jpg", null).getTexture();
        Texture texture_jupiter = new TextureLoader ("imgs/jupiter.jpg", null).getTexture();
        Texture texture_saturno = new TextureLoader ("imgs/saturno.jpg", null).getTexture();
        Texture texture_urano = new TextureLoader ("imgs/urano.jpg", null).getTexture();
        Texture texture_neptuno = new TextureLoader ("imgs/neptuno.jpg", null).getTexture();
        Texture texture_pluton = new TextureLoader ("imgs/pluton.jpg", null).getTexture();
        //Textura Satelites
        Texture texture_luna = new TextureLoader ("imgs/luna.jpg", null).getTexture();
        Texture texture_deimos = new TextureLoader ("imgs/deimos.jpg", null).getTexture();
        Texture texture_io = new TextureLoader ("imgs/io.jpg", null).getTexture();
        Texture texture_europa = new TextureLoader ("imgs/europa.jpg", null).getTexture();
        Texture texture_calisto = new TextureLoader ("imgs/calisto.jpg", null).getTexture();
        Texture texture_titania = new TextureLoader ("imgs/titania.jpg", null).getTexture();
        Texture texture_ariel = new TextureLoader ("imgs/ariel.jpg", null).getTexture();
        Texture texture_miranda = new TextureLoader ("imgs/miranda.jpg", null).getTexture();
        Texture texture_triton = new TextureLoader ("imgs/triton.jpg", null).getTexture();
        Texture texture_anilloA = new TextureLoader ("imgs/anilloA.png", null).getTexture();
        Texture texture_anilloB = new TextureLoader ("imgs/anilloB.png", null).getTexture();
        Texture texture_anilloC = new TextureLoader ("imgs/anilloC.png", null).getTexture();
        
        int factor=4;
        // Se crea la rama de la escena y se cuelga al universo
        Estrella sol = new Estrella(1000,texture_sol,material_sol,0,0,0);
        Planeta mercurio = new Planeta("Mercurio",39,texture_mercurio, material,5000*factor,1250,4240*factor);
        Planeta venus = new Planeta("Venus",98,texture_venus, material,20000*factor,1500,4600*factor);
        Planeta tierra = new Planeta("Tierra",100,texture_tierra, material,1000*factor,2000,5000*factor);
        Planeta marte = new Planeta("Marte",50,texture_marte, material,1050*factor,2250,5880*factor);
        Planeta jupiter = new Planeta("Jupiter",500,texture_jupiter, material,400*factor,3500,20000*factor);
        Planeta saturno = new Planeta("Saturno",470,texture_saturno, material,420*factor,5500,30000*factor);
        Planeta urano = new Planeta("Urano",200,texture_urano, material,700*factor,7500,40000*factor);
        Planeta neptuno = new Planeta("Neptuno",190,texture_neptuno, material,7750*factor,8250,50000*factor);
        Planeta pluton = new Planeta("Pluton",20,texture_pluton, material,1600*factor,8600,60000*factor);
        
        //Crear Satelites y colgarlos a sus planetas
        Satelite luna = new Satelite(20,texture_luna, material, 500*factor, 160, 900*factor);
        tierra.getDesplazamiento().addChild(luna);
        luna.getDesplazamiento().addChild(vLuna.getVp());
        Satelite fobos = new Satelite(20,texture_luna, material, 560*factor, 150, 2500*factor);
        marte.getDesplazamiento().addChild(fobos);
        Satelite deimos = new Satelite(25,texture_deimos, material, 560*factor, 250, 3000*factor);
        marte.getDesplazamiento().addChild(deimos);
        Satelite io = new Satelite(40,texture_io, material, 505*factor, 600, 3250*factor);
        jupiter.getDesplazamiento().addChild(io);
        Satelite europa = new Satelite(45,texture_europa, material, 505*factor, 690, 3750*factor);
        jupiter.getDesplazamiento().addChild(europa);
        Satelite calisto = new Satelite(50,texture_calisto, material, 505*factor, 850, 2000*factor);
        jupiter.getDesplazamiento().addChild(calisto);
        Satelite titania = new Satelite(30,texture_titania, material, 505*factor, 350, 2200*factor);
        urano.getDesplazamiento().addChild(titania);
        Satelite ariel = new Satelite(20,texture_ariel, material, 530*factor, 450, 2000*factor);
        urano.getDesplazamiento().addChild(ariel);
        Satelite miranda = new Satelite(40,texture_miranda, material, 500*factor, 540, 2400*factor);
        urano.getDesplazamiento().addChild(miranda);
        Satelite triton = new Satelite(35,texture_triton, material, 515*factor, 270, 2000*factor);
        neptuno.getDesplazamiento().addChild(triton);
        
        Anillo anilloA = new Anillo(600,texture_anilloA,800*factor);
        saturno.getDesplazamiento().addChild(anilloA);
        Anillo anilloB = new Anillo(760,texture_anilloB,200*factor);
        saturno.getDesplazamiento().addChild(anilloB);
        Anillo anilloC = new Anillo(860,texture_anilloC, 600*factor);
        saturno.getDesplazamiento().addChild(anilloC);
        
        Scene modelo = null;
        ObjectFile archivo = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE  );
         try {
            modelo = archivo.load ("models\\naveEspacial\\naveEspacial.obj");
          } catch (FileNotFoundException e) {
            System.err.println (e);
            System.exit(1);
          } catch (ParsingErrorException e) {
            System.err.println (e);
            System.exit(1);
          } catch (IncorrectFormatException e) {
            System.err.println (e);
            System.exit(1);
          }
       
        Nave nave = new Nave(modelo.getSceneGroup());
        nave.getPosInicial().addChild(vNave.getVp());
        universe.addBranchGraph(nave);
        //Se cuelga al universo
        inicial.addChild(sol);
        inicial.addChild(mercurio);
        inicial.addChild(venus);
        inicial.addChild(tierra);
        inicial.addChild(marte);
        inicial.addChild(jupiter);
        inicial.addChild(saturno);
        inicial.addChild(urano);
        inicial.addChild(neptuno);
        inicial.addChild(pluton);

        PararRotacion stop = new PararRotacion(canvass.get(0),inicial);
        stop.setSchedulingBounds(CuerpoEstelar.radioAccion);
        inicial.addChild(stop);
        universe.addBranchGraph(inicial);
    }

    

    public SimpleUniverse crearUniverso(ArrayList<Canvas3D> canvas) {

        vLibre = new VistaLibre(canvass.get(0));
        vEscena = new VistaEscena(canvass.get(2));
        vLuna = new VistaLuna(canvass.get(1));
        vNave = new VistaNave(canvass.get(3));
        SimpleUniverse s = null;

        s = new SimpleUniverse(vLibre.getVp(), vLibre.getViewer());
        s.getLocale().addBranchGraph(vEscena.getVp());

       // Se construye y devuelve el Universo con los parametros definidos
       return s;
    }
    
    

    public BranchGroup crearBackground() {
            // Se crea el objeto para el fondo y 
    //     se le asigna un área de influencia
    Background backgroundNode = new Background ();
    backgroundNode.setApplicationBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000000.0));
    
    // Se crea un aspecto basado en la textura a mostrar
    Appearance app = new Appearance ();
    Texture texture = new TextureLoader ("imgs/back.jpg", null).getTexture();
    app.setTexture (texture);
    
    // Se hace la esfera con un determinado radio indicándole:
    //    - Que genere coordenadas de textura
    //    - Que genere las normales hacia adentro
    //    - Que tenga el aspecto creado
    Primitive sphere = new Sphere (1.0f, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD, app);
    
    // Se establece esa esfera como background
    // Es necesario que cuelgue de un BranchGroup para poder asignárselo al nodo Background
    BranchGroup bgSphere = new BranchGroup();
    bgSphere.addChild(sphere);
    backgroundNode.setGeometry(bgSphere);
    
    // Finalmente se crea el BranchGroup para devolver el Background
    BranchGroup bgBackground = new BranchGroup();
    bgBackground.addChild(backgroundNode);
    return bgBackground;    

    }

    public BranchGroup crearLuces() {
        BranchGroup lightsNode = new BranchGroup();
        BoundingSphere influencingBound = new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100.0);
        // Se crea la luz ambiente
        Light aLight = new AmbientLight (new Color3f (0.7f, 0.7f, 0.7f));
        aLight.setInfluencingBounds (influencingBound);
        aLight.setEnable(true);
        lightsNode.addChild(aLight);

        // Se crea la primera luz
        Color3f white = new Color3f (1.0f, 1.0f, 1.0f);
        Vector3f direction = new Vector3f (0f, -1f, 0f);
        Point3f position = new Point3f (4f, 0f, -4f);
        Point3f atenuation = new Point3f (1f, 0f, 0f);
        aLight = new SpotLight (white, position, atenuation, direction, 0.5f, 1f);
        aLight.setInfluencingBounds (influencingBound);
        aLight.setCapability(Light.ALLOW_STATE_WRITE);
        aLight.setEnable (true);
        lightsNode.addChild(aLight);

        return lightsNode;
    }

    public SimpleUniverse getUniverso() {
        return universe;
    }


    public void closeApp (int code) {
    System.exit (code);
    }
}
