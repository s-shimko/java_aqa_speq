package by.htp.speq.logic;

import java.io.FileNotFoundException;

import by.htp.speq.dao.CatalogData;
import by.htp.speq.dao.db.CatalogDataMysqlImpl;
import by.htp.speq.dao.parser.dom.CatalogDataDomImpl;
import by.htp.speq.dao.parser.sTax.CatalogDataStaxImpl;
import by.htp.speq.dao.parser.sax.CatalogDataSaxImpl;
import by.htp.speq.station.Catalog;
import by.htp.speq.station.Client;
import by.htp.speq.station.RentedCatalog;

public class DaoStationLogicImpl implements StationLogic {
	
	private CatalogData logic;
	{
//		logic = new CatalogDataSaxImpl();
//		logic = new CatalogDataStaxImpl();
		logic  = new CatalogDataDomImpl();
		
//		logic = new CatalogDataMysqlImpl();
		

	}
	
	@Override
	public Catalog readCatalog() throws FileNotFoundException {
		Catalog catalog = logic.readDaoCatalog();
		return catalog;
	}

	@Override
	public RentedCatalog readRentedCatalog() throws FileNotFoundException {
		return null;
	}

	@Override
	public Client takeItemInRent() throws FileNotFoundException {
		return null;
	}

	@Override
	public Client backItemFromRent() throws FileNotFoundException {
		return null;
	}


}
