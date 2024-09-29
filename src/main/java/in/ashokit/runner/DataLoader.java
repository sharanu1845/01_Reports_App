package in.ashokit.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	private CitizenPlanRepository repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		repo.deleteAll();
       
		//Cash Plan Data
		CitizenPlan c1=new CitizenPlan();
		
		c1.setCitizenName("John");
		c1.setGender("male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmt(5000.00);
		
		CitizenPlan c2=new CitizenPlan();
		c2.setCitizenName("Smith");
		c2.setGender("male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
		c2.setDenialReason("Rental Income");
		
		
		CitizenPlan c3=new CitizenPlan();
		c3.setCitizenName("Cathy");
		c3.setGender("female");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmt(5000.00);
		c3.setTerminatedDate(LocalDate.now());
		c3.setTerminationRsn("Employed");
		
		
		//Food Plan Data
		CitizenPlan c4=new CitizenPlan();
		c4.setCitizenName("David");
		c4.setGender("male");
		c4.setPlanName("Food");
		c4.setPlanStatus("Approved");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setBenefitAmt(4000.00);
	
		
		CitizenPlan c5=new CitizenPlan();
		c5.setCitizenName("Robert");
		c5.setGender("male");
		c5.setPlanName("Food");
		c5.setPlanStatus("Denied");
		c5.setDenialReason("Property Income");
		
		
		CitizenPlan c6=new CitizenPlan();
		c6.setCitizenName("Orlen");
		c6.setGender("female");
		c6.setPlanName("Food");
		c6.setPlanStatus("Terminated");
		c6.setPlanStartDate(LocalDate.now().minusMonths(4));
		c6.setPlanEndDate(LocalDate.now().plusMonths(6));
		c6.setBenefitAmt(5000.00);
		c6.setTerminatedDate(LocalDate.now());
		c6.setTerminationRsn("Employed");
		
		 //Medical Plan Data
		CitizenPlan c7=new CitizenPlan();
		c7.setCitizenName("Charles");
		c7.setGender("male");
		c7.setPlanName("Medical");
		c7.setPlanStatus("Approved");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		c7.setBenefitAmt(4000.00);
	
		
		CitizenPlan c8=new CitizenPlan();
		c8.setCitizenName("Buttler");
		c8.setGender("male");
		c8.setPlanName("Medical");
		c8.setPlanStatus("Denied");
		c8.setTerminationRsn("Property Income");
		
		
		CitizenPlan c9=new CitizenPlan();
		c9.setCitizenName("Neel");
		c9.setGender("female");
		c9.setPlanName("Medical");
		c9.setPlanStatus("Terminated");
		c9.setPlanStartDate(LocalDate.now().minusMonths(4));
		c9.setPlanEndDate(LocalDate.now().plusMonths(6));
		c9.setBenefitAmt(5000.00);
		c9.setTerminatedDate(LocalDate.now());
		c9.setTerminationRsn("Govt Job");
		
		 //Employment Plan Data
		CitizenPlan c10=new CitizenPlan();
		c10.setCitizenName("Steve");
		c10.setGender("male");
		c10.setPlanName("Employment");
		c10.setPlanStatus("Denied");
		c10.setPlanStartDate(LocalDate.now());
		c10.setPlanEndDate(LocalDate.now().plusMonths(6));
		c10.setBenefitAmt(4000.00);
		
		
		CitizenPlan c11=new CitizenPlan();
		c11.setCitizenName("Moris");
		c11.setGender("male");
		c11.setPlanName("Employment");
		c11.setPlanStatus("Denied");
		c11.setTerminationRsn("Property Income");
		
		
		CitizenPlan c12=new CitizenPlan();
		c12.setCitizenName("Gibs");
		c12.setGender("female");
		c12.setPlanName("Employment");
		c12.setPlanStatus("Terminated");
		c12.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmt(5000.00);
		c3.setTerminatedDate(LocalDate.now());
		c3.setTerminationRsn("Govt Job");
		
		List<CitizenPlan> list=Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
		repo.saveAll(list);
		

	}

}
