package com.njfu.action;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.constant.Constant;
import com.njfu.entity.News;
import com.njfu.exception.NewsInUseException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.NewsForm;
import com.njfu.service.NewsService;

public class NewsAction extends MappingDispatchAction {
	// 用于存放临时文件的目录
	private File tempPath = null;
	// 保存上传文件的目录
	private File uploadPath = null;
	
	public void newsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		NewsService newsProxy = (NewsService) ObjectFactory.getObject("newsProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		//设置分页时当前页,页的大小
		Page<Object> page = PageHelper.startPage(pageNo, pageSize);
		List<News> list = newsProxy.findNews();
		//使用fastjson
		PageInfo<News> pageInfo = new PageInfo<News>(list);
		response.getWriter().print(JSON.toJSONString(pageInfo));
	}
	
	public void addCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//让返回值支持中文,中文乱码过滤器在这无效
		response.setContentType(Constant.CONTENT_TYPE);
		response.setCharacterEncoding(Constant.FILTER_CHARSET_UTF8);
		//调用proxy
		NewsService newsProxy = (NewsService) ObjectFactory.getObject("newsProxy");
		String newsTitle = request.getParameter("newsTitle");
		List<News> newsList = newsProxy.findByNewsTitle(newsTitle);
		PrintWriter out = response.getWriter();
		if (newsList != null && !newsList.isEmpty()) {
			out.print("该新闻标题已存在");
		}
		else {
			out.print("");
		}
	}
	
	public ActionForward newsAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//init
		// 取得临时交换目录
		tempPath = new File(request.getSession().getServletContext().getRealPath("temp"));
		// 如果目录不存在，创建一个
		if (!tempPath.exists()) {
			tempPath.mkdir();
		}
		// 取得上传路径
		uploadPath = new File(request.getSession().getServletContext().getRealPath(
				"upload/images"));
		// 如果目录不存在，创建一个
		if (!uploadPath.exists()) {
			// 可以创建多级目录
			uploadPath.mkdirs();
		}
		
		// 设置文件上传工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置文件上传块大小为4k
		factory.setSizeThreshold(4096);
		// 设置临时目录
		factory.setRepository(tempPath);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置文件大小上限为4M
		upload.setSizeMax(10240000);
		try {
			List fileItems = upload.parseRequest(request);
			// 获取表单元素目录列表
			Iterator iter = fileItems.iterator();
			// 定义元素变量
			String newsTitle = null;
			String content = null;
			String image = null;
			Integer heading = null;
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				// 如果是普通表单元素
				if (item.isFormField()) {
					if ("newsTitle".equals(item.getFieldName())) {
						newsTitle = item.getString("utf-8");
					}
					if ("content".equals(item.getFieldName())) {
						content = item.getString("utf-8");
					}
					if ("heading".equals(item.getFieldName())) {
						heading = Integer.valueOf(item.getString("utf-8"));
					}
					
				}
				// 如果是上传表单域
				else {
					// 获取文件名
					String fileName = item.getName();
					if (!fileName.isEmpty()) {
						// 获取文件后缀
						String ext = fileName.substring(fileName
								.lastIndexOf(".") + 1, fileName.length());
						// 获取新的文件名(获取一个不会重复的文件名)
						// 方法获得Calendar对象而无需实例化该对象
						Calendar calendar = Calendar.getInstance();
						// 以当前时间的毫秒值作为新的文件名称
						fileName = String.valueOf(calendar.getTimeInMillis());
						// fileName = fileName.substring(fileName.lastIndexOf("\\"),
						// fileName.length());
						String fullFilePath = uploadPath + "/" + fileName + "."
								+ ext;
						// 将上传文件写入磁盘
						File file = new File(fullFilePath);
						item.write(file);
						image = "upload/images/"+fileName+"."+ext;
	
						// 以上代码完成了图片文件的上传操作
						// 下面代码将上传过的文件生成缩略图
						// 读出刚才上传完的图片
						// 创建新的缩略图文件保存地址
						String newUrl = uploadPath + "/" + fileName + "_min." + ext;
						// 构造Image对象
						Image src = ImageIO.read(file);
						// 目标大小
						float tagSize = 200;
						// 得到原图宽和高
						int old_w = src.getWidth(null);
						int old_h = src.getHeight(null);
						// 定义新图宽和高
						int new_w = 0;
						int new_h = 0;
						// 声明临时大小
						float tempDouble;
						// 根据宽和高得到一个临时比例
						if (old_w > old_h) {
							tempDouble = old_w / tagSize;
						} else {
							tempDouble = old_h / tagSize;
						}
						// 得到新的高和宽
						new_w = Math.round(old_w / tempDouble);
						new_h = Math.round(old_h / tempDouble);
						// 创建新图对象
						BufferedImage tag = new BufferedImage(new_w, new_h,
								BufferedImage.TYPE_INT_RGB);
						// 绘制缩小后的图
						tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null);
						
						String formatName = newUrl.substring(newUrl.lastIndexOf(".") + 1);
						ImageIO.write(tag, formatName, new File(newUrl));
					}
				}
			}
			
			NewsService newsProxy = (NewsService) ObjectFactory.getObject("newsProxy");
			response.setContentType(Constant.CONTENT_TYPE);
			News news = new News();
			news.setNewsTitle(newsTitle);
			news.setContent(content);
			news.setImage(image);
			news.setHeading(heading);
			newsProxy.addNews(news);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("findAllNews");
	}
	
	public ActionForward showByNewsId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsService newsProxy = (NewsService) ObjectFactory.getObject("newsProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		String newsId = request.getParameter("newsId");
		//获取请求中传递过来的pageNo
		String pageNo = request.getParameter("pageNo");
		request.setAttribute("pageNo", pageNo);
				
		News news = newsProxy.findByNewsId(Integer.valueOf(newsId));
		request.setAttribute("news", news);
		
		return mapping.findForward("success");
	}
	
	public void modifyCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//让返回值支持中文,中文乱码过滤器在这无效
		response.setContentType(Constant.CONTENT_TYPE);
		response.setCharacterEncoding(Constant.FILTER_CHARSET_UTF8);
		//调用proxy
		NewsService newsProxy = (NewsService) ObjectFactory.getObject("newsProxy");
		Integer newsId = Integer.valueOf(request.getParameter("newsId"));
		String newsTitle = request.getParameter("newsTitle");
		List<News> newsList = newsProxy.findByNewsIdAndTitle(newsId, newsTitle);
		PrintWriter out = response.getWriter();
		if (newsList != null && !newsList.isEmpty()) {
			out.print("该新闻标题已存在");
		}
		else {
			out.print("");
		}
	}
	
	public ActionForward newsModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsService newsProxy = (NewsService) ObjectFactory.getObject("newsProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		News oldNews = null;
		News news = null;
		//init
		// 取得临时交换目录
		tempPath = new File(request.getSession().getServletContext().getRealPath("temp"));
		// 如果目录不存在，创建一个
		if (!tempPath.exists()) {
			tempPath.mkdir();
		}
		// 取得上传路径
		uploadPath = new File(request.getSession().getServletContext().getRealPath(
				"upload/images"));
		// 如果目录不存在，创建一个
		if (!uploadPath.exists()) {
			// 可以创建多级目录
			uploadPath.mkdirs();
		}
		
		// 设置文件上传工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置文件上传块大小为4k
		factory.setSizeThreshold(4096);
		// 设置临时目录
		factory.setRepository(tempPath);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置文件大小上限为4M
		upload.setSizeMax(10240000);
		try {
			List fileItems = upload.parseRequest(request);
			// 获取表单元素目录列表
			Iterator iter = fileItems.iterator();
			// 定义元素变量
			String pageNo = null;
			Integer newsId = null;
			String newsTitle = null;
			String content = null;
			String image = null;
			Integer heading = null;
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				// 如果是普通表单元素
				if (item.isFormField()) {
					if ("pageNo".equals(item.getFieldName())) {
						//获取隐藏表单域传递过来的pageNo
						pageNo = item.getString("utf-8");
						request.setAttribute("pageNo", pageNo);
					}
					if ("newsId".equals(item.getFieldName())) {
						newsId = Integer.valueOf(item.getString("utf-8"));
					}
					if ("newsTitle".equals(item.getFieldName())) {
						newsTitle = item.getString("utf-8");
					}
					if ("content".equals(item.getFieldName())) {
						content = item.getString("utf-8");
					}
					if ("heading".equals(item.getFieldName())) {
						heading = Integer.valueOf(item.getString("utf-8"));
					}
				}
				// 如果是上传表单域
				else {
					// 获取文件名
					String fileName = item.getName();
					if (!fileName.isEmpty()) {
						// 获取文件后缀
						String ext = fileName.substring(fileName
								.lastIndexOf(".") + 1, fileName.length());
						// 获取新的文件名(获取一个不会重复的文件名)
						// 方法获得Calendar对象而无需实例化该对象
						Calendar calendar = Calendar.getInstance();
						// 以当前时间的毫秒值作为新的文件名称
						fileName = String.valueOf(calendar.getTimeInMillis());
						// fileName = fileName.substring(fileName.lastIndexOf("\\"),
						// fileName.length());
						String fullFilePath = uploadPath + "/" + fileName + "."
								+ ext;
						// 将上传文件写入磁盘
						File file = new File(fullFilePath);
						item.write(file);
						image = "upload/images/"+fileName+"."+ext;
	
						// 以上代码完成了图片文件的上传操作
						// 下面代码将上传过的文件生成缩略图
						// 读出刚才上传完的图片
						// 创建新的缩略图文件保存地址
						String newUrl = uploadPath + "/" + fileName + "_min." + ext;
						// 构造Image对象
						Image src = ImageIO.read(file);
						// 目标大小
						float tagSize = 200;
						// 得到原图宽和高
						int old_w = src.getWidth(null);
						int old_h = src.getHeight(null);
						// 定义新图宽和高
						int new_w = 0;
						int new_h = 0;
						// 声明临时大小
						float tempDouble;
						// 根据宽和高得到一个临时比例
						if (old_w > old_h) {
							tempDouble = old_w / tagSize;
						} else {
							tempDouble = old_h / tagSize;
						}
						// 得到新的高和宽
						new_w = Math.round(old_w / tempDouble);
						new_h = Math.round(old_h / tempDouble);
						// 创建新图对象
						BufferedImage tag = new BufferedImage(new_w, new_h,
								BufferedImage.TYPE_INT_RGB);
						// 绘制缩小后的图
						tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null);
						
						String formatName = newUrl.substring(newUrl.lastIndexOf(".") + 1);
						ImageIO.write(tag, formatName, new File(newUrl));
					}
				}
			}
			
			//以防修改时原图被删除
			oldNews = newsProxy.findByNewsId(newsId);
			news = new News();
			if (image != null)
				news.setImage(image);
			else
				news.setImage(oldNews.getImage());
			
			news.setNewsId(newsId);
			news.setNewsTitle(newsTitle);
			news.setContent(content);
			news.setHeading(heading);
			newsProxy.modifyNews(news);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("findAllNews");
	}
	
	public ActionForward newsDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsService newsProxy = (NewsService) ObjectFactory.getObject("newsProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		String newsId = request.getParameter("newsId");
		News news = newsProxy.findByNewsId(Integer.valueOf(newsId));
		request.setAttribute("news", news);
		
		return mapping.findForward("toDetailNews");
	}
	
	public ActionForward newsDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsService newsProxy = (NewsService) ObjectFactory.getObject("newsProxy");
		response.setContentType(Constant.CONTENT_TYPE);
		String newsId = request.getParameter("newsId");
		String pageNo = request.getParameter("pageNo");
		request.setAttribute("pageNo", pageNo);
		try {
			newsProxy.removeNews(Integer.valueOf(newsId));
		} catch (NewsInUseException e) {
			request.setAttribute("errMsg", "产品使用中,无法删除");
		}
		catch (Exception e) {
			request.setAttribute("errMsg", "服务器繁忙");
		}
		
		return mapping.findForward("findAllNews");
	}
}
