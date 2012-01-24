package org.kuali.student.core.class1.type.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.student.common.entity.BaseAttributeEntity;
import org.kuali.student.common.infc.Attribute;

@Entity
@Table(name = "KSEN_ATPTYPE_ATTR")
public class AtpTypeAttributeEntity extends BaseAttributeEntity<AtpTypeEntity> {
    
    @ManyToOne
    @JoinColumn(name = "OWNER")
    private AtpTypeEntity owner;

    public AtpTypeAttributeEntity () {
    }
    
    public AtpTypeAttributeEntity(String key, String value) {
        super(key, value);
    }

    public AtpTypeAttributeEntity(Attribute att, AtpTypeEntity owner) {
        super(att);
        setOwner(owner);
    }

    @Override
    public void setOwner(AtpTypeEntity owner) {
        this.owner = owner;
        
    }

    @Override
    public AtpTypeEntity getOwner() {
        return owner;
    }
}
