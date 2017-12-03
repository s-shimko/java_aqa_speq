package by.htp.speq.dao.parser.sTax;

import static by.htp.speq.dao.parser.DataTypeTransformUtil.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.htp.speq.dao.CatalogData;
import by.htp.speq.dao.parser.CatalogTagName;
import by.htp.speq.entity.Accessory;
import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.station.Catalog;

public class CatalogDataStaxImpl implements CatalogData {

	private static final String XML_FILE_PATH = "resources/rent_units.xml";
	private static final char UNDERSCORE = '_';
	private static final char DASH = '-';
	private static final String ID = "id";

	@Override
	public Catalog readDaoCatalog() {
		Catalog catalog = new Catalog();

		XMLInputFactory xmlIF = XMLInputFactory.newInstance();

		InputStream stream;

		try {

			stream = new FileInputStream(XML_FILE_PATH);
			XMLStreamReader reader = xmlIF.createXMLStreamReader(stream);

			List<RentUnit> units = processReader(reader);
			catalog.setUnits(units);

		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}

		return catalog;
	}

	private List<RentUnit> processReader(XMLStreamReader reader) {
		List<RentUnit> units = new ArrayList<>();

		RentUnit unit = null;
		CatalogTagName tag = null;
		Equipment equipment = null;
		Accessory accessory = null;

		try {
			while (reader.hasNext()) {
				int type = reader.next();
				switch (type) {
				case XMLStreamConstants.START_ELEMENT:

					tag = getTag(reader.getLocalName());
				
					switch (tag) {
					case EQUIPMENT:
						
						equipment = new Equipment();
						unit = new RentUnit();
						unit.setEquipment(equipment);
//						String idString = attributes.getValue(ID);
//						int id = Integer.parseInt(idString);
//						unit.setId(id);
						break;
						
					case ACCESSORY:
						
						accessory = new Accessory();
						unit = new RentUnit();
						unit.setEquipment(accessory);
						break;
					}
					break;

				case XMLStreamConstants.CHARACTERS:
					
					String text = reader.getText().trim();
					
					if (text.isEmpty()) {
						break;
					}

					switch (tag) {
					case EQUIPMENT:
						units.add(unit);
						break;
					case ACCESSORY:
						units.add(unit);
						break;
					case TYPE:
						unit.getEquipment().setType(text);
						break;
					case TITLE:
						unit.getEquipment().setTitle(text.toString().trim());
						break;
					case HOUR_RATE:
						String hour_rate = text.toString().trim();
						unit.setHourRate(convertPrice(hour_rate));
						break;
					case CATEGORY:
						String category = text.toString().trim();
						unit.getEquipment().setCategory(category);
						break;
					case BELONG_TO:
						String belongTo = text.toString().trim();
						accessory.setBelongTo(belongTo);
						break;
					}
					break;

				case XMLStreamConstants.END_ELEMENT:

					tag = getTag(reader.getLocalName());

					switch (tag) {
					case EQUIPMENT:
						units.add(unit);
						break;
						
					case ACCESSORY:
						units.add(unit);
						break;
					}
					break;
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		return units;
	}

	private CatalogTagName getTag(String localName) {
		String tag = localName.toUpperCase().replace(DASH, UNDERSCORE);
		CatalogTagName tagElement = CatalogTagName.valueOf(tag);
		return tagElement;
	}

}
