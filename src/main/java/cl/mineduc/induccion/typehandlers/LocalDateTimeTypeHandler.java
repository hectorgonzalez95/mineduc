package cl.mineduc.induccion.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

/**
 * Custom TypeHandler para LocalDateTime Java 8 - Integracion MyBatis
 * @author alvaro.tellez
 *
 */
@MappedTypes(LocalDateTime.class)
public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime>{

	@Override
	public void setNonNullParameter(PreparedStatement vPs, int vI, LocalDateTime vParameter, JdbcType vJdbcType) throws SQLException {
		if (vParameter == null) {
			vPs.setTimestamp(vI, null);
		} else {
			vPs.setTimestamp(vI, Timestamp.valueOf(vParameter), GregorianCalendar.from(ZonedDateTime.of(vParameter, ZoneId.systemDefault())));
		}
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet vRs, String vColumnName) throws SQLException {
    Timestamp ts = vRs.getTimestamp(vColumnName);
    if (ts != null) {
        return LocalDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
    }
    return null;
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet vRs, int vColumnIndex) throws SQLException {
    Timestamp ts = vRs.getTimestamp(vColumnIndex);
    if (ts != null) {
        return LocalDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
    }
    return null;
	}

	@Override
	public LocalDateTime getNullableResult(CallableStatement vCs, int vColumnIndex) throws SQLException {
    Timestamp ts = vCs.getTimestamp(vColumnIndex);
    if (ts != null) {
        return LocalDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
    }
    return null;
	}
}
