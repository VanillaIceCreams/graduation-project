package com.zxj.portal.pojo;

import com.zxj.pojo.TbItem;
/**
 * 本类继承TbItem，仅仅将一大堆图片链接字符串变成数组
 * @author Administrator
 *
 */
public class PortalItem extends TbItem{
	public String[] getImages(){
		String image = this.getImage();
		if(image!=null&&!image.equals("")){
			String[] imgs = image.split(",");
			return imgs;
		}
		return null;		
	} 
}
