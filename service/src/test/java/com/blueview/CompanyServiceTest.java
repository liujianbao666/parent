package com.blueview;


import com.blueview.model.Company;
import com.blueview.service.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Description: HttpClientUtils工具类测试
 * 
 * @author JourWon
 * @date Created on 2018年4月19日
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyServiceTest {
	@Autowired
	CompanyService companyService;
	/**
	 * Description:
	 * 
	 * @throws Exception
	 */
	@Test
	public void testselectByPrimaryKey() throws Exception {
		Company company = new Company();
		List<Company> companys = companyService.getCompanysSelective(company);
		System.out.println(company);
	}
}
