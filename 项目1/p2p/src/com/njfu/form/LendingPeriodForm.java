package com.njfu.form;

import org.apache.struts.action.ActionForm;

public class LendingPeriodForm extends ActionForm {
	private Integer lendingPeriodId;
	private String period;
	private Integer periodStatus;
	public Integer getLendingPeriodId() {
		return lendingPeriodId;
	}
	public void setLendingPeriodId(Integer lendingPeriodId) {
		this.lendingPeriodId = lendingPeriodId;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Integer getPeriodStatus() {
		return periodStatus;
	}
	public void setPeriodStatus(Integer periodStatus) {
		this.periodStatus = periodStatus;
	}
	@Override
	public String toString() {
		return "LendingPeriodForm [lendingPeriodId=" + lendingPeriodId + ", period=" + period + ", periodStatus="
				+ periodStatus + "]";
	}
	
	
	
}
