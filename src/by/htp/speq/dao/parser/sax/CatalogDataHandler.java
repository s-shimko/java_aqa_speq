package by.htp.speq.dao.parser.sax;

import static by.htp.speq.dao.parser.DataTypeTransformUtil.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.htp.speq.dao.parser.CatalogTagName;
import by.htp.speq.entity.Accessory;
import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.RentUnit;

public class CatalogDataHandler extends DefaultHandler {

	private StringBuilder text;
	private Equipment equipment;
	private Accessory accessory;
	private RentUnit unit;
	private List<RentUnit> units = new ArrayList<>();

	private static final char UNDERSCORE = '_';
	private static final char DASH = '-';
	private static final String ID = "id";
	
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		CatalogTagName tag = getTag(localName);
		
		switch (tag) {
		case EQUIPMENT:
			
			equipment = new Equipment();
			unit = new RentUnit();
			unit.setEquipment(equipment);
//			String idString = attributes.getValue(ID);
//			int id = Integer.parseInt(idString);
//			unit.setId(id);
			unit.getEquipment().setType(localName);
			break;
			
		case ACCESSORY:
			
			accessory = new Accessory();
			unit = new RentUnit();
			unit.setEquipment(accessory);
//			String idString = attributes.getValue(ID);
//			int id = Integer.parseInt(idString);
//			unit.setId(id);
			unit.getEquipment().setType(localName);
			break;
		}

		text = new StringBuilder();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		CatalogTagName tag = getTag(localName);
		
		switch (tag) {
		case EQUIPMENT:
			units.add(unit);
			break;
		case ACCESSORY:
			units.add(unit);
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
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		text.append(ch, start, length);
	}

	public List<RentUnit> getRentUnits() {
		return units;
	}
	
	private CatalogTagName getTag(String localName) {
		String tag = localName.toUpperCase().replace(DASH, UNDERSCORE);
		CatalogTagName tagElement = CatalogTagName.valueOf(tag);
		return tagElement;
	}
	
	

}
