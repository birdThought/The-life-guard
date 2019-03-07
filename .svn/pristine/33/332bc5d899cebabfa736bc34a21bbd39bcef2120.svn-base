package com.lifeshs.customer.controller.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.component.uedit.ActionEnter;
import com.lifeshs.utils.image.ImageUtilV2;

@RestController
@RequestMapping(value = "common")
public class CommonController {

	/**
     * uEidt的配置
     *
     * @author zhansi.Xu
     * @DateTime 2016年9月30日
     * @serverComment
     */
    @RequestMapping(value = "ueditConf", method = RequestMethod.GET)
    public void config(HttpServletRequest request, HttpServletResponse response, String action) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 文件上传
     * 
     * @param target
     * @param uploadFile
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "uploadFile/{target}", method = RequestMethod.POST)
    public @ResponseBody AjaxJson uploadFile(@PathVariable String target,
            @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile) throws IOException {
        AjaxJson resObject = new AjaxJson();
        resObject.setSuccess(false);
        switch (target) {
        case "img":// 图片
            String netPath = "";
            String uploadName = uploadFile.getOriginalFilename();
            String arr[] = uploadName.split("\\.");
            if (!"png".equals(arr[arr.length - 1]) && !"jpg".equals(arr[arr.length - 1])) {
                resObject.setMsg("图片类型不合法");
                break;
            }
            if (uploadFile.getSize() > 1024 * 1024) {
                resObject.setMsg("图片大小超出限制");
                break;
            }
            if (uploadFile.getSize() > 200 * 1024) {
            	int height = 600;
            	BufferedImage image = ImageIO.read(uploadFile.getInputStream());
            	if(image.getHeight() >750 && image.getHeight() <1000){
            		height = 750;
            	}else if(image.getHeight()>=1000 && image.getHeight() < 1500){
            		height = 900;
				}else if(image.getHeight()>=1500 && image.getHeight() <2000){
					height = 1200;
				}else if(image.getHeight()>=2000){
					height = 1500;
				}else{
					height = 600;
				}
            	         	
                netPath = ImageUtilV2.saveByte(uploadFile.getBytes(), "", true ,600 ,height);
            } else {
                netPath = ImageUtilV2.saveByte(uploadFile.getBytes(), "", true);
            }
            resObject.setSuccess(true);
            resObject.setObj(netPath);
            resObject.setMsg("上传文件成功");
            return resObject;
        }
        return resObject;
    }
}
