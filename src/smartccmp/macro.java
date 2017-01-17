// STAR-CCM+ macro: macro.java
// Written by STAR-CCM+ 11.04.010
package macro;

import java.io.IOException;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import star.common.*;
import star.base.neo.*;
import star.meshing.*;
//import *;

public class macro extends StarMacro {

class Vector3d {
    double x,y,z;
    public Vector3d(){
    this.x=0;
    this.y=0;
    this.z=0;
    } 
    public Vector3d(double x,double y,double z){
    this.x=x;
    this.y=y;
    this.z=z;
    } 
    public Vector3d(Element node){
        String xString=node.getAttribute("X");
        this.x=Double.parseDouble(xString);
        String yString=node.getAttribute("Y");
        this.y=Double.parseDouble(yString);
        String zString=node.getAttribute("Z");
        this.z=Double.parseDouble(zString);
       }
   @Override
    public String toString()
    {
        
        return "("+this.x+","+this.y+","+this.z+")";
    }
    public DoubleVector toDoubleVector(){
    
    return new DoubleVector(new double[] {this.x, this.y, this.z});
    }
}
class Line {
    Vector3d start;
    Vector3d end;
    
    public Line(){
    this.start=new Vector3d();
    this.end=new Vector3d(1.0,1.0,1.0);
    
    }
    public Line(Element node){
        NodeList startList=node.getElementsByTagName("StartPoint");
        Element startNode=(Element) startList.item(0);
        this.start=new Vector3d(startNode);
        NodeList endList=node.getElementsByTagName("EndPoint");
        Element endNode=(Element) endList.item(0);
        this.end=new Vector3d(endNode);
        
    
    }
    @Override
    public String toString()
    {
        
        return " start="+this.start+"\n end="+this.end;
    }
    public void draw( SimpleCylinderPart cyl, Units units){
        
        
        Coordinate point1 = cyl.getStartCoordinate();
        point1.setCoordinate(units, units, units, this.start.toDoubleVector());

        Coordinate point2 = cyl.getEndCoordinate();
        point2.setCoordinate(units, units, units, this.end.toDoubleVector());
        cyl.getRadius().setUnits(units);
        cyl.getRadius().setValue(0.05);
    
    
    cyl.rebuildSimpleShapePart();

    cyl.setDoNotRetessellate(false);
    
    
    
    }
}
class Part {
    Line l;
    public Part(Element node){
        
        NodeList pathList=node.getElementsByTagName("Path");
        Element path=(Element) pathList.item(0);
                
        NodeList byTwoPortsList=path.getElementsByTagName("ByTwoPorts");
        Element byTwoPorts=(Element) byTwoPortsList.item(0);
          
        this.l=new Line(byTwoPorts);
     
    
    }
    
    @Override
    public String toString()
    {
        
        return " Line="+this.l;
    }
    public void draw( SimpleCylinderPart cyl, Units units){
        this.l.draw(cyl, units);
    }
    
}
public class Structure {
    ArrayList<Part> partsList;
    
    public Structure(String file){
        try{
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(file);
            document.normalize();
 
            // Получаем корневой элемент
            Element root = document.getDocumentElement();
            root.normalize();
            NodeList partList=root.getElementsByTagName("CSPSMemberPartPrismatic");
            this.partsList=new ArrayList<Part>();
            this.partsList.clear();
            int len=partList.getLength();
            
            
            for(int i=0;i!=len;i++){
                
                Element node=(Element) partList.item(i);
                Part p= new Part(node);
                this.partsList.add(p);
                
        
            }
               
        }
        catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
    }
        catch (SAXException ex) {
            ex.printStackTrace(System.out);
    }   catch (IOException ex) {
            ex.printStackTrace(System.out);   
}
    
    
    }
    
    @Override
    public String toString(){
        String s="";
        for(Part p:this.partsList){
            s=s+p+"\n";
        }
        
        return s;
    }
    public void draw(Simulation sim){
        
        Units units = sim.getUnitsManager().getPreferredUnits(new IntVector(new int[] {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
        MeshPartFactory factory= sim.get(MeshPartFactory.class);
        
        for(Part p:this.partsList){
            SimpleCylinderPart cyl = factory.createNewCylinderPart(sim.get(SimulationPartManager.class));
            p.draw(cyl, units);
        }
    
    
    
    }
    
    
    
}
  public void execute() {
   
    execute0();
  }

  private void execute0() {
    
      
    Structure struct=new Structure("F:\\smartccmp\\original.xml");
    System.out.println(struct);
    
    Simulation sim = getActiveSimulation();
    
    struct.draw(sim);
    
    
    
    
    
    

    
 
  }
}
