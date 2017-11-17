package cl.mineduc.induccion.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

/**
 * Custom TypeHandler para LocalTime Java 8 - Integracion MyBatis
 * @author alvaro.tellez
 *
 */
@MappedTypes(LocalTime.class)
public class LocalTimeTypeHandler extends BaseTypeHandler<LocalTime>{

	@Override
	public void setNonNullParameter(PreparedStatement vPs, int vI, LocalTime vParameter, JdbcType vJdbcType) throws SQLException {
		if (vParameter == null) {
			vPs.setTime(vI, null);
		} else {
			vPs.setTime(vI, Time.valueOf(vParameter));
		}
	}

	@Override
	public LocalTime getNullableResult(ResultSet vRs, String vColumnName) throws SQLException {
    Time time = vRs.getTime(vColumnName);
    if (time != null) {
        return time.toLocalTime();
    }
    return null;
	}

	@Override
	public LocalTime getNullableResult(ResultSet vRs, int vColumnIndex) throws SQLException {
    Time time = vRs.getTime(vColumnIndex);
    if (time != null) {
        return time.toLocalTime();
    }
    return null;
	}

	@Override
	public LocalTime getNullableResult(CallableStatement vCs, int vColumnIndex) throws SQLException {
    Time time = vCs.getTime(vColumnIndex);
    if (time != null) {
        return time.toLocalTime();
    }
    return null;
	}
}
