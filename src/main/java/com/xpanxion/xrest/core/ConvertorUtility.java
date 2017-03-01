package com.xpanxion.xrest.core;


public class ConvertorUtility {

    /*public static void main(String[] args) {
        VelocityContext context = new VelocityContext();
        VelocityEngine ve = new VelocityEngine();
        Template template = ve.getTemplate("./src/main/resources/template/manager_java.vm");
        FileWriter fw = null;
        StringWriter sw = null;
        Configuration config = new Configuration();
        List<Element> elements = config.getElements();

        for (Element element : elements) {
            NodeList nodes = element.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) node;
                    context.put(el.getTagName(), el.getTextContent());
                }
            }
            sw = new StringWriter();
            template.merge(context, sw);
            int beginIndex = sw.toString().indexOf("public class ") + "public class ".length();
            int endIndex = sw.toString().substring(beginIndex).indexOf(" ");
            String resourceName = sw.toString().substring(beginIndex, beginIndex + endIndex).trim();
            URL filePath = Res.createFileInSourceAndGetUrl(resourceName);
            try {
                fw = new FileWriter(filePath.getPath());
                fw.append(sw.toString());
                fw.close();
            } catch (IOException ex) {
                throw new RuntimeException("Unable to find the file", ex);
            }
        }
    }*/
}
