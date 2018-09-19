package contactSys_jsp_util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtil {

	public static Document getDocment(File file) {
		Document doc = null;
		try {
			if (file.exists()) {
				doc = new SAXReader().read(file);
			} else {
				doc = DocumentHelper.createDocument();
				doc.addElement("contactList");
			}
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		return doc;
	}

	public static void xmlWriter(Document doc,File file) {
		
		try {
			FileOutputStream out = new FileOutputStream(file);
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(doc);
			writer.close();
		
		} catch (IOException e) {
			throw new RuntimeException(e);
		}	
	}
}
