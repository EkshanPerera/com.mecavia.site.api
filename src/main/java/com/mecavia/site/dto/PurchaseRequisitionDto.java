package com.mecavia.site.dto;

import java.util.ArrayList;
import java.util.List;

import com.mecavia.site.entity.Client;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseRequisitionDto {
    private int id;
    private String prcode;
    private String pocode;
    private Client supplierid;
    private double totalAmount;
    private String remark;
    private String quotationno;
    private List<PurchaseRequisitionMaterialDto> purchaseRequisitionMaterials = new ArrayList<>();
    private String printeddate;
    private String prprinteddate;
    private Status status;
}
