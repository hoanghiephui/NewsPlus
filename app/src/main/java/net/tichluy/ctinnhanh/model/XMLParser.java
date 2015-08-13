package net.tichluy.ctinnhanh.model;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Hoang Hiep on 02/07/2015.
 */
public class XMLParser {
    private URL mUrl;
    private Context mContext;
    public XMLParser(String url, Context context) {
        mContext = context;
        try {
            this.mUrl = new URL(url);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }//end

    public ArrayList<ItemsNews> parser(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ArrayList<ItemsNews> items = new ArrayList<>();
        ItemsNews mItems;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(this.mUrl.openConnection().getInputStream());
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("item");
            for (int i = 0; i< nodeList.getLength(); i++){
                mItems = new ItemsNews();
                Node nodes = nodeList.item(i);
                NodeList mNodeList = nodes.getChildNodes();
                for (int j = 0; j < mNodeList.getLength(); j++){
                    Node mNode = mNodeList.item(j);
                    String name = mNode.getNodeName();
                    if (name.equalsIgnoreCase("title")){
                        mItems.setTitle(mNode.getFirstChild().getNodeValue().toString());
                    }
                    else if (name.equalsIgnoreCase("description")) {
                        int deli = (mNode.getFirstChild().getNodeValue().indexOf("]"));
                        mItems.setContent(mNode.getFirstChild().getNodeValue().substring(deli + 1));

                        int image = mNode.getFirstChild().getNodeValue().indexOf("src");
                        int imageE = mNode.getFirstChild().getNodeValue().indexOf("src=&quot;");
                        if (image != -1) {
                            String urlPart = mNode.getFirstChild().getNodeValue().substring(image + 5);
                            int endImage = urlPart.indexOf("\" ");
                            int endImageE = urlPart.indexOf("?width=80");
                            int endImageD = urlPart.indexOf("'");
                            int endImageTp = urlPart.indexOf(".ashx?width=80\" ");
                            try {
                                if (endImageD <= 0){
                                    if (endImageE >0){
                                        mItems.setImage(mNode.getFirstChild().getNodeValue().substring(imageE + 10, image + 5 + endImageE));
                                        //Toast.makeText(activity, "Ok1", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        mItems.setImage(mNode.getFirstChild().getNodeValue().substring(image + 5, image + 5 + endImage));
                                        //Toast.makeText(activity, "Ok3", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                else if(endImageTp > 0){
                                    mItems.setImage(mNode.getFirstChild().getNodeValue().substring(image + 5, image + 5 + endImageTp));
                                }
                                else {
                                    mItems.setImage(mNode.getFirstChild().getNodeValue().substring(image + 5, image + 5 + endImageD));
                                    //Toast.makeText(activity, "Ok4", Toast.LENGTH_SHORT).show();
                                }
                            }catch (StringIndexOutOfBoundsException e){
                                e.printStackTrace();
                            }

                        }
                        else if (imageE != -1){
                            String urlPart = mNode.getFirstChild().getNodeValue().substring(image + 5);
                            int endImageE = urlPart.indexOf("?width=80");
                            mItems.setImage(mNode.getFirstChild().getNodeValue().substring(image + 5, image + 5 + endImageE));
                        }
                    } else if (name.equalsIgnoreCase("feedburner:origLink") || name.equalsIgnoreCase("link")) {
                        mItems.setLink(mNode.getFirstChild().getNodeValue().toString());
                    } else if (name.equalsIgnoreCase("pubDate")) {
                        mItems.setTime(mNode.getFirstChild().getNodeValue().toString());
                    }
                    else if (name.equalsIgnoreCase("guid") || name.equalsIgnoreCase("image")){
                        mItems.setImageD(mNode.getFirstChild().getNodeValue());//get image vnreview
                    }
                    else if (name.equalsIgnoreCase("generator")){
                        mItems.setContent(mNode.getFirstChild().getNodeValue());
                    }

                }
                mItems.setPic("http://media.doisongphapluat.com/no-thumbnail-img.jpg");
                items.add(mItems);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }//end
}
