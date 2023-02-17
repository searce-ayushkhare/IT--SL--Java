package com.example.hibernateinheritance;

import com.example.hibernateinheritance.entities.Cheque;
import com.example.hibernateinheritance.entities.CreditCard;
import com.example.hibernateinheritance.repositories.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HibernateinheritanceApplicationTests {

	@Autowired
	private PaymentRepository paymentRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreatePayment() {
		CreditCard cc = new CreditCard();
		cc.setId(123);
		cc.setAmount(1000d);
		cc.setCardnumber("1234567890");

		paymentRepository.save(cc);
	}

	@Test
	public void testCreateChequePayment() {
		Cheque ch = new Cheque();
		ch.setId(124);
		ch.setAmount(1000d);
		ch.setChequenumber("1234567890");

		paymentRepository.save(ch);
	}
}
