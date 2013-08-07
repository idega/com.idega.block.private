package is.idega.idegaweb.egov.reykjavik.data.dao;

import java.io.Serializable;

import com.idega.core.persistence.GenericDao;

public interface ReykjavikDao extends GenericDao{
	public static final String BEAN_NAME = "reykjavikDao";

	public <T extends Serializable>T getById(Serializable id,Class<T> returnClass);
}
