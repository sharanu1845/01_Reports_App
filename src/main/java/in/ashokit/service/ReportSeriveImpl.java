package in.ashokit.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepository;
import in.ashokit.request.SearchRequest;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportSeriveImpl implements ReportService {
	
	@Autowired //field level autowiring
	private CitizenPlanRepository planRepo;

	@Override
	public List<String> getPlanNames() {
		return planRepo.getPlanNames();   //List<String> planNames= planRepo.getPlanNames();
		                                    //return planNames;
	}

	@Override
	public List<String> getPlanStatuses() {
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity=new CitizenPlan();
	if(null!=request.getPlanName() && !"".equals(request.getPlanName())) {
		entity.setPlanName(request.getPlanName());
	}
	if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
		entity.setPlanStatus(request.getPlanStatus());
	}
	if(null!=request.getGender() && !"".equals(request.getGender())) {
		entity.setGender(request.getGender());
	}
	
	if(null!=request.getStartDate() && !"".equals(request.getStartDate())) {
		String startDate = request.getStartDate();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//convert String to localDate
				LocalDate localDate=LocalDate.parse(startDate,formatter);
		entity.setPlanStartDate(localDate);
	}
	if(null!=request.getEndDate() && !"".equals(request.getEndDate())) {
		String enddate = request.getEndDate();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//convert String to localDate
				LocalDate localDate=LocalDate.parse(enddate,formatter);
		entity.setPlanEndDate(localDate);
	}
		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exporExcel(HttpServletResponse response) throws IOException {
		Workbook workbook =  new HSSFWorkbook();
		Sheet sheet=workbook.createSheet("plans-data");
		Row headerRow=sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benefit Amount");
		
		List<CitizenPlan> records=planRepo.findAll();
		
		int dataRowIndex=1;
		
		for(CitizenPlan plan:records) {
			Row dataRow=sheet.createRow(1);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			dataRow.createCell(4).setCellValue(plan.getPlanStartDate());
			dataRow.createCell(5).setCellValue(plan.getPlanEndDate());
			if(null!=plan.getBenefitAmt()) {
				dataRow.createCell(6).setCellValue(plan.getBenefitAmt());
			}else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			dataRowIndex++;
		}
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		return true; 
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return false;
	}

}
