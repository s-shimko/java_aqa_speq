package by.htp.speq.logic;

import java.io.FileNotFoundException;

import by.htp.speq.dao.CatalogData;
import by.htp.speq.dao.db.CatalogDataMysqlImpl;
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
		
//		Catalog catalog = new CatalogDataDomImpl();
		
		logic = new CatalogDataMysqlImpl();
		

	}
	
	@Override
	public Catalog readCatalog() throws FileNotFoundException {
		Catalog catalog = logic.readDaoCatalog();
		return catalog;
	}

	@Override
	public RentedCatalog readRentedCatalog() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client takeItemInRent() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client backItemFromRent() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


}
