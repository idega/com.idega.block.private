package is.idega.idegaweb.egov.reykjavik.data.dao;

import is.idega.idegaweb.egov.reykjavik.data.ReykjavikEntity;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.idega.core.persistence.Param;
import com.idega.core.persistence.impl.GenericDaoImpl;
import com.idega.util.ListUtil;

@Repository(ReykjavikDao.BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ReykjavikDaoImpl extends GenericDaoImpl implements ReykjavikDao{
	
	@Override
	public <T extends Serializable>T getById(Serializable id,Class<T> returnClass){
		StringBuilder query = new StringBuilder("FROM ").append(returnClass.getName().toString())
				.append(" t WHERE (t.id = :id_prop").append(") ");
		List<T> list = getQueryInline(query.toString()).getResultList(returnClass, new Param("id_prop", id));
		if(ListUtil.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void remove(Object object){
		if(!(object instanceof ReykjavikEntity)){
			super.remove(object);
			return;
		}
		ReykjavikEntity entity = (ReykjavikEntity) object;
		Serializable id = entity.getId();
		if(id == null){
			return;
		}
		entity = getById(id, entity.getClass());
		if(entity != null){
			super.remove(entity);
		}
	}
}
