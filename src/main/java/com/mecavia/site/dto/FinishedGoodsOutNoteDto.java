package com.mecavia.site.dto;

import java.util.List;

import com.mecavia.site.entity.CustomerOrder;
import com.mecavia.site.entity.User;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinishedGoodsOutNoteDto {
	private int id;
	private String code;
	private String enterddate;
	private CustomerOrder customerOrder;
	private List<FinishedGoodsOutNoteProductDto> finishedGoodsOutNoteProducts;
	private String printeddate;
	private String remark;
	private Status status;
	private User enteredUser;
}
