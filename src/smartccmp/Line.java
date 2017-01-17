/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartccmp;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 *
 * @author vfolomeev
 */
public class Line {
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
}
