package com.mecavia.site.util;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mecavia.site.entity.Material;
import com.mecavia.site.entity.PurchaseRequisition;

@SuppressWarnings("serial")
@Embeddable
public class PRMId implements Serializable{
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaserequisition_id", referencedColumnName = "id")
    private PurchaseRequisition purchaseRequisition;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Material material;

}
