package com.njfu.entity;

import java.io.Serializable;

public class LendingPeriod implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer lendingPeriodId;
	private String period;
	private Integer periodStatus;
	
	public LendingPeriod() {
		super();
	}
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
		return "LendingPeriod [lendingPeriodId=" + lendingPeriodId + ", period=" + period + ", periodStatus="
				+ periodStatus + "]";
	}
	
	
}
