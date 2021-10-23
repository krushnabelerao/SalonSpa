package com.salonspa.example.boundary;
import static com.example.utils.TestUtils.boundaryTestFile;


import static com.example.utils.TestUtils.currentTest;
import static com.example.utils.TestUtils.yakshaAssert;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.Order;

import com.example.utils.MasterData;
import com.salonspa.example.dto.ShopDto;

@Order(3)
public class BoundaryTests {
	
	
	private static Validator validator;
	
	

    //----------------------------------------------------------------------------------------------
    @BeforeAll
    public static void setUp() {
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
	
    @Test
	public void testHibernateValidationIsAddedToCheckIfAddressIsNotNull() throws Exception {
		ShopDto note = MasterData.getShopDto();
		note.setAddress("");
		Set<ConstraintViolation<ShopDto>> violations = validator.validate(note);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfAddressIsNotLessThanFiveChars() throws Exception {
		ShopDto note = MasterData.getShopDto();
		note.setAddress("Pra");
		Set<ConstraintViolation<ShopDto>> violations = validator.validate(note);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
	}
	
	@Test
	public void testHibernateValidationIsAddedToCheckIfAddressIsNotMoreThanTwentyChars() throws Exception {
		ShopDto note = MasterData.getShopDto();
		String name = "";
		for(int i=0;i<25;i++) {
			name.concat("A");
		}
		note.setAddress(name);
		Set<ConstraintViolation<ShopDto>> violations = validator.validate(note);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
	}
	 
	@Test
	public void testHibernateValidationIsAddedToCheckIfShopDtoNameIsNotNull() throws Exception {
		ShopDto note = MasterData.getShopDto();
		note.setName("");
		Set<ConstraintViolation<ShopDto>> violations = validator.validate(note);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
			
	}
	
	@Test
	public void testHibernateValidationIsAddedToCheckIfShopDtoNameIsNotLessThanFiveChars() throws Exception {
		ShopDto note = MasterData.getShopDto();
		note.setName("Dev");
		Set<ConstraintViolation<ShopDto>> violations = validator.validate(note);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
			
	}
	
	@Test
	public void testHibernateValidationIsAddedToCheckIfShopDtoNameIsNotMoreThanTwentyChars() throws Exception {
		ShopDto note = MasterData.getShopDto();
		String name = "";
		for(int i=0;i<25;i++) {
			name.concat("A");
		}
		note.setName(name);
		Set<ConstraintViolation<ShopDto>> violations = validator.validate(note);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
			
	}
	
	@Test
	public void testHibernateValidationIsAddedToCheckIfDescriptionIsNotNull() throws Exception {
		ShopDto note = MasterData.getShopDto();
		note.setDescription("");
		Set<ConstraintViolation<ShopDto>> violations = validator.validate(note);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
			
	}
	
	@Test
	public void testHibernateValidationIsAddedToCheckIfDescriptionIsNotLessThanFiveChars() throws Exception {
		ShopDto note = MasterData.getShopDto();
		note.setDescription("Test");
		Set<ConstraintViolation<ShopDto>> violations = validator.validate(note);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
			
	}
	
	@Test
	public void testHibernateValidationIsAddedToCheckIfDescriptionIsNotMoreThanTwoHundredChars() throws Exception {
		ShopDto note = MasterData.getShopDto();
		String name = "";
		for(int i=0;i<225;i++) {
			name.concat("A");
		}
		note.setDescription(name);
		Set<ConstraintViolation<ShopDto>> violations = validator.validate(note);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
			
	}
	
	@Test
	public void testHibernateValidationIsAddedToCheckIfStatusIsNotNull() throws Exception {
		com.salonspa.example.dto.ShopDto note = MasterData.getShopDto();
		note.setStatus("");
		Set<ConstraintViolation<ShopDto>> violations = validator.validate(note);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
			
	}
}
