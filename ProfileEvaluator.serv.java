package com.wipro.ips.service;

import com.wipro.ips.entity.Applicant;
import com.wipro.ips.entity.ExternalReferral;
import com.wipro.ips.entity.InternalReferral;
import com.wipro.ips.exception.DataValidationException;

public class ProfileEvaluator 
{
	private int JobId;
	private String JobName;
	private String requiredSkill;
	private int expMinExperience;
	private int expMaxExperience;
	String agencyName;
	public int getJobId() 
	{
		return JobId;
	}
	public void setJobId(int jobId) 
	{
		JobId = jobId;
	}
	public String getJobName() 
	{
		return JobName;
	}
	public void setJobName(String jobName) 
	{
		JobName = jobName;
	}
	public String getRequiredSkill() 
	{
		return requiredSkill;
	}
	public void setRequiredSkill(String requiredSkill) 
	{
		this.requiredSkill = requiredSkill;
	}
	public int getExpMinExperience() 
	{
		return expMinExperience;
	}
	public void setExpMinExperience(int expMinExperience) 
	{
		this.expMinExperience = expMinExperience;
	}
	public int getExpMaxExperience() 
	{
		return expMaxExperience;
	}
	public void setExpMaxExperience(int expMaxExperience) 
	{
		this.expMaxExperience = expMaxExperience;
	}
	
	public Applicant createObject(char referralType, int applicantId, String applicantName, int yrsOfExp, String skills, double expSalary,  String referralInfo) throws DataValidationException
	{
		if(referralType=='I')
		{
			int employeeId=Integer.parseInt(referralInfo);
			InternalReferral i=new InternalReferral(applicantId, applicantName, yrsOfExp, skills, expSalary, employeeId);
			
			return i;
		}
		else if(referralType=='E')
		{
			
			ExternalReferral e=new ExternalReferral(applicantId, applicantName, yrsOfExp, skills, expSalary, agencyName);
			return e;
		}
		else
		{
		return null;
		}
		
	}
	
	public String validateObject(Applicant applicant) throws DataValidationException
	{
		
		
		if(applicant==null || applicant.getApplicantName()==null)
		{
			throw new DataValidationException();
		}
		if(applicant.getYrsOfExp()<=0)
		{
			return "INVALID EXPERIENCE";
		}
		if(applicant.getExpectedSalary()<=0)
		{
			return"INVALID SALARY";
		}
		else
		{
			return "VALID";
		}
	}
	
	public String processProfile(Applicant applicant)
	{
		if(applicant.getYrsOfExp()<expMinExperience || applicant.getYrsOfExp()>expMaxExperience)
		{
			return "NON ELIGIBLE EXPERIENCE";
		}
		if(applicant.getSkills()==requiredSkill)
		{
		return "NON ELIGIBLE SKILLS";
		}
		else
		{
			return "SELECTED";
		}
		
	}
	
}
