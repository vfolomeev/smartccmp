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
public class Part {
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
    
}
