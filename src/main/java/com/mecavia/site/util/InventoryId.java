package com.mecavia.site.util;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Component
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class InventoryId implements Serializable{
	private int stockid;
	private Status status;
}
