/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartccmp;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 *
 * @author vfolomeev
 */
public class Vector3d {
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
}