package com.cg.banking.entity;

import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;

/*************************************************************************************************************
 *          @author          Sreeram
 *          Description      It is a class of LDT Converter which is used to covert the Date into LocalDate
 *                                       and vice versa automatically by Converter annotation.
 *          @version         1.0
 *          Created Date     02-APR-2020
 *************************************************************************************************************/

@Converter(autoApply = true)
public class LDTConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate ldt) {
		if (ldt != null)
			return java.sql.Date.valueOf(ldt);
		return null;
	}

	@Override
	public LocalDate convertToEntityAttribute(Date sqldt) {
		if (sqldt != null)
			return sqldt.toLocalDate();
		return null;
	}

}
