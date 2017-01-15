/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartccmp;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author vfolomeev
 */
public class Smartccmp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("F:\\smartccmp\\original.xml");
            document.normalize();
 
            // Получаем корневой элемент
            Element root = document.getDocumentElement();
            root.normalize();
            NodeList partList=root.getElementsByTagName("CSPSMemberPartPrismatic");
            String name=root.getNodeName();
            
            System.out.println(name);
            //NodeList partList=root.getChildNodes();
            int len=partList.getLength();
           
            for(int i=1;i!=len;i++){
                System.out.println(partList.item(i).getNodeName());
                Node node=partList.item(i);
                Element element=(Element) node;
                NodeList oidList=element.getElementsByTagName("OID");
                for(int j=0;j!=oidList.getLength();j++){
                    System.out.println(oidList.item(j).getTextContent());
                    
                    
                }}
               
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
}
