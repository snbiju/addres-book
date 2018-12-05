package com.address.book;

import com.address.book.model.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressBookApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void getOldestPersonFromAddressBook() {
		ResponseEntity<Address> responseEntity = this.testRestTemplate.getForEntity("/address/older", Address.class);
		Address address = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(address.getFirstName()).isEqualTo("Wes");
		assertThat(address.getLastName()).isEqualTo("Jackson");
	}
}
