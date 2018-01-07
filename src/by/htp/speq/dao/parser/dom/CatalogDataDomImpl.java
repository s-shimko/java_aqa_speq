package by.htp.speq.dao.parser.dom;

import static by.htp.speq.dao.parser.DataTypeTransformUtil.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.*;

import by.htp.speq.dao.CatalogData;
import by.htp.speq.entity.Accessory;
import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.station.Catalog;

public class CatalogDataDomImpl implements CatalogData {

	private static final String XML_FILE_PATH = "resources/rent_units.xml";

	@Override
	public Catalog readDaoCatalog() {

		Catalog catalog = new Catalog();
		DOMParser parser = new DOMParser();

		// DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// DocumentBuilder db = dbf.newDocumentBuilder(XML_FILE_PATH);

		try {
			parser.parse(XML_FILE_PATH);
			Document document = parser.getDocument();
			parseDocument(document);

			List<RentUnit> unit = parseDocument(document);
			catalog.setUnits(unit);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		return catalog;
	}

	private List<RentUnit> parseDocument(Document document) {
		List<RentUnit> list = new ArrayList<RentUnit>();
		RentUnit unit = null;
		Equipment equipment = null;
		Accessory accessory = null;
		Element root = document.getDocumentElement();

		NodeList nodes_eq = root.getElementsByTagName("equipment");
		for (int i = 0; i < nodes_eq.getLength(); i++) {

			equipment = new Equipment();
			unit = new RentUnit();
			Element currentNode = (Element) nodes_eq.item(i);

			unit = setEquipmentUnit(unit, equipment, currentNode);
			list.add(unit);
		}

		NodeList nodes_acc = root.getElementsByTagName("accessory");
		for (int i = 0; i < nodes_acc.getLength(); i++) {

			accessory = new Accessory();
			unit = new RentUnit();
			Element currentNode = (Element) nodes_acc.item(i);

			unit = setAccessoryUnit(unit, accessory, currentNode);
			list.add(unit);
		}

		return list;
	}

	private static RentUnit setEquipmentUnit(RentUnit unit, Equipment equipment, Element currentNode) {
		Element element = getSingleChild(currentNode, "title");
		String title = element.getTextContent().trim();

		equipment.setTitle(title);

		element = getSingleChild(currentNode, "type");
		String type = element.getTextContent().trim();
		equipment.setType(type);

		element = getSingleChild(currentNode, "hour_rate");
		String hour_rate = element.getTextContent().trim();
		unit.setHourRate(convertPrice(hour_rate));

		element = getSingleChild(currentNode, "category");
		String category = element.getTextContent().trim();
		equipment.setCategory(category);

		unit.setEquipment(equipment);
		
		return unit;
	}

	private static RentUnit setAccessoryUnit(RentUnit unit, Accessory accessory, Element currentNode) {

		Element element = getSingleChild(currentNode, "title");
		String title = element.getTextContent().trim();

		accessory.setTitle(title);

		element = getSingleChild(currentNode, "type");
		String type = element.getTextContent().trim();
		accessory.setType(type);

		element = getSingleChild(currentNode, "hour_rate");
		String hour_rate = element.getTextContent().trim();
		unit.setHourRate(convertPrice(hour_rate));

		element = getSingleChild(currentNode, "category");
		String category = element.getTextContent().trim();
		accessory.setCategory(category);

		element = getSingleChild(currentNode, "belong_to");
		String belongTo = element.getTextContent().trim();
		accessory.setBelongTo(belongTo);

		unit.setEquipment(accessory);

		return unit;
	}

	private static Element getSingleChild(Element node, String name) {
		NodeList nodeList = node.getElementsByTagName(name);
		Element childElement = (Element) nodeList.item(0);
		return childElement;
	}

}
