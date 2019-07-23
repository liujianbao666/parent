import com.blueview.WebApplication;
import com.blueview.model.Company;
import com.blueview.mq.Sender;
import com.blueview.service.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Description: HttpClientUtils工具类测试
 * 
 * @author JourWon
 * @date Created on 2018年4月19日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class )
public class CompanyServiceTest {
	@Autowired
	CompanyService companyService;

	@Autowired
	private Sender sender;
	/**
	 * Description:
	 * 
	 * @throws Exception
	 */
	@Test
	public void testselectByPrimaryKey() throws Exception {
		Company company = new Company();
		List<Company> companys = companyService.getCompanysSelective(company);
		System.out.println(companys);
	}

	@Test
	public void hello() throws Exception{
		sender.send();
	}
}
