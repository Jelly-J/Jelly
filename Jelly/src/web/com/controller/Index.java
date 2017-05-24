package web.com.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import biz.service.BizAccountInfoService;
import db.model.AccountInfo;

@Controller
@RequestMapping("/index")
public class Index {
	Logger logger = Logger.getLogger(Index.class);
	@Resource
	private BizAccountInfoService bizAccountInfoService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
			String name, String password) {
		AccountInfo listAccountInfo = bizAccountInfoService.listAccountInfo();
		request.getSession().setAttribute("user", listAccountInfo);
		logger.info(listAccountInfo.getAccountName());
		HttpSession session = request.getSession();
		AccountInfo attribute = (AccountInfo) session.getAttribute("user");
		System.out.println(name);
		// new Barcode().zxingBarCode(response);
		// modelMap.addAttribute("accountName", attribute.getAccountName());
		modelMap.addAttribute("accountName", attribute.getAccountName());

		return attribute.getAccountName();
		// return "index";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uplpad(@RequestParam("file") MultipartFile file, HttpServletRequest request, ModelMap model) {
		logger.info("upload begin");
		System.out.println("开始");
		String path = request.getSession().getServletContext().getRealPath("resource\\upload");
		String fileName = file.getOriginalFilename();
		System.out.println(path);

		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();// 创建目录
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("fileUrl", "/upload/" + fileName);
		logger.info("upload end");
		return "result";
	}

}
