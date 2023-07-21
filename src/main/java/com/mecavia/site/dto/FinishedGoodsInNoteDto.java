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
public class FinishedGoodsInNoteDto {
	private int id;
	private String code;
	private String panumber;
	private String padate;
	private String enterddate;
	private String remark;
	private Status status;
	private CustomerOrder customerOrder;
	private List<FinishedGoodsInNoteProductDto> finishedGoodsInNoteProducts;
	private String printeddate;
	private User enteredUser;
}
