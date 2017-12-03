package by.htp.speq.dao.db;

import static by.htp.speq.dao.parser.DataTypeTransformUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.cj.jdbc.CallableStatement;

import by.htp.speq.dao.CatalogData;
import by.htp.speq.entity.Accessory;
import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.station.Catalog;

public class CatalogDataMysqlImpl implements CatalogData {

	@Override
	public Catalog readDaoCatalog() {
		Catalog catalog = new Catalog();

		List<RentUnit> units = selectUnitList();
		catalog.setUnits(units);
		return catalog;
	}

	private List<RentUnit> selectUnitList() {

		ResourceBundle rb = ResourceBundle.getBundle("db_config");
		String url = rb.getString("db.url");
		String user = rb.getString("db.login");
		String pass = rb.getString("db.pass");
		String driver = rb.getString("db.driver.name");

		List<RentUnit> units = new ArrayList<RentUnit>();
		RentUnit unit = null;
		Equipment equipment = null;
		Accessory accessory = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			// Class.forName(driver);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from rent_units");

			while (rs.next()) {
				unit = new RentUnit();
				equipment = new Equipment();
				accessory = new Accessory();
				
				String type = rs.getString("type");
				
				if(type.equals("Accessory")) {
					String belongTo = rs.getString("belong_to");
					unit.setEquipment(accessory);
					accessory.setBelongTo(belongTo);
				} else {
					unit.setEquipment(equipment);
				}

				String title = rs.getString("title");
				double hour_rate = rs.getDouble("hour_rate");
				String category = rs.getString("category");

				unit.getEquipment().setType(type);
				unit.getEquipment().setTitle(title);
				unit.setHourRate(hour_rate);
				unit.getEquipment().setCategory(category);

				units.add(unit);
			}

			// PreparedStatement ps;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return units;
	}

}
