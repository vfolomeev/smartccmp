/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartccmp;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author vfolomeev
 */
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
    
    
    
}
