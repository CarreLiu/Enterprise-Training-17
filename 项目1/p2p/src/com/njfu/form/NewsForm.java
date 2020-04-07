package com.njfu.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class NewsForm extends ActionForm {
	private Integer newsId;
	private String newsTitle;
	private String content;
	private Date createAt;
	private String image;
	private Integer heading;
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getHeading() {
		return heading;
	}
	public void setHeading(Integer heading) {
		this.heading = heading;
	}
	@Override
	public String toString() {
		return "NewsForm [newsId=" + newsId + ", newsTitle=" + newsTitle + ", content=" + content + ", createAt="
				+ createAt + ", image=" + image + ", heading=" + heading + "]";
	}
	
	
	
}
