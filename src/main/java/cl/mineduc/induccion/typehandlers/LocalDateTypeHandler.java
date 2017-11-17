package cl.mineduc.induccion.typehandlers;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

/**
 * Custom TypeHandler para LocalDate Java 8 - Integracion MyBatis
 * @author alvaro.tellez
 *
 */
@MappedTypes(LocalDate.class)
public class LocalDateTypeHandler extends BaseTypeHandler<LocalDate> {
	/**private static Logger logger = Logger.getLogger(LocalDateTypeHandler.class);*/

	@Override
	public void setNonNullParameter(PreparedStatement vPs, int vI, LocalDate vParameter, JdbcType vJdbcType) throws SQLException {
    if (vParameter == null) {
    	vPs.setDate(vI, null);
    } else {
    	vPs.setDate(vI, Date.valueOf(vParameter));
    }
	}

	@Override
	public LocalDate getNullableResult(ResultSet vRs, String vColumnName) throws SQLException {
    Date date = vRs.getDate(vColumnName);
    if (date != null) {
        return date.toLocalDate();
    }
    return null;
	}

	@Override
	public LocalDate getNullableResult(ResultSet vRs, int vColumnIndex) throws SQLException {
    Date date = vRs.getDate(vColumnIndex);
    if (date != null) {
        return date.toLocalDate();
    }
    return null;
	}

	@Override
	public LocalDate getNullableResult(CallableStatement vCs, int vColumnIndex) throws SQLException {
    Date date = vCs.getDate(vColumnIndex);
    if (date != null) {
        return date.toLocalDate();
    }
    return null;
	}
}
