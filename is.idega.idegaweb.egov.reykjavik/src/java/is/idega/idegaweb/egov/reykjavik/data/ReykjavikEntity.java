package is.idega.idegaweb.egov.reykjavik.data;

import is.idega.idegaweb.egov.reykjavik.ReykjavikConstants;
import is.idega.idegaweb.egov.reykjavik.data.dao.ReykjavikDao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import com.idega.core.persistence.GenericDao;
import com.idega.util.expression.ELUtil;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name = ReykjavikConstants.DATA_PREFIX+"REYKJAVIK_ENTITY")
public class ReykjavikEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2143289918401843780L;
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;
	public Long getId() {
		return id;
	}
	
	@Transactional(readOnly = false)
	public ReykjavikEntity merge(){
		GenericDao genericDao = ELUtil.getInstance().getBean(ReykjavikDao.BEAN_NAME);
		return genericDao.merge(this);
	}
	
	public void remove(){
		ReykjavikDao reykjavikDao = ELUtil.getInstance().getBean(ReykjavikDao.BEAN_NAME);
		reykjavikDao.remove(this);
	}
}
